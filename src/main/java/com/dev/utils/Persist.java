package com.dev.utils;

import com.dev.objects.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.*;

@Component
public class Persist {

    private Connection connection;
    private final SessionFactory sessionFactory;

    @Value("${DB_URL}")
    private String dbUrl;

    @Value("${DB_USERNAME}")
    private String dbUsername;

    @Value("${DB_PASSWORD}")
    private String dbPassword;

    @Autowired
    public Persist(SessionFactory sessionFactory) {this.sessionFactory = sessionFactory;}

    @PostConstruct
    public void createConnectionToDatabase() {
        try {
            this.connection = DriverManager.getConnection(dbUrl, dbUsername, dbPassword);
            System.out.println("Successfully connected to DB");

            insertStaticDataIfNecessary();

        } catch (SQLException e) {
            System.err.println("Failed to connect to the database: " + e.getMessage());
            e.printStackTrace();
        }
    }
    private void insertStaticDataIfNecessary() {
        if (!staticDataExists()) {
            List<League> leagues = readLeaguesFromFile();  // Read leagues first
            if (leagues != null && !leagues.isEmpty()) {
                Session session = sessionFactory.openSession();
                Transaction transaction = session.beginTransaction();
                try {
                    for (League league : leagues) {
                        session.save(league);
                    }
                    transaction.commit();
                    System.out.println("Leagues initialized successfully.");
                } catch (Exception e) {
                    transaction.rollback();
                    e.printStackTrace();
                } finally {
                    session.close();
                }
            }

            // Now read teams and assign league ids
            List<Team> teams = readTeamsFromFile();
            if (teams != null && !teams.isEmpty()) {
                Session session = sessionFactory.openSession();
                Transaction transaction = session.beginTransaction();
                try {
                    for (Team team : teams) {
                        League league = session.get(League.class, team.getLeague().getLeagueId()); // Get the league using league_id
                        team.setLeague(league); // Set the league in the team
                        session.save(team);
                    }
                    transaction.commit();
                    System.out.println("Teams initialized successfully.");
                } catch (Exception e) {
                    transaction.rollback();
                    e.printStackTrace();
                } finally {
                    session.close();
                }
            }
        } else {
            System.out.println("Static Data already exists. Skipping initialization...");
        }
    }

    private List<League> readLeaguesFromFile() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File file = new File("src/main/resources/leagues.json");
            return objectMapper.readValue(file, new TypeReference<>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private List<Team> readTeamsFromFile() {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File file = new File("src/main/resources/teams.json");
            return objectMapper.readValue(file, new TypeReference<List<Team>>() {});
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private boolean staticDataExists() {
        Session session = sessionFactory.openSession();
        long count = (long) session.createQuery("SELECT COUNT(t) FROM Team t").uniqueResult();
        session.close();
        return count > 0;
    }

//User Persist...

    public void createUser(User user) {sessionFactory.openSession().save(user);}
    public List<User> getAllUsers() {

      return sessionFactory.openSession().
             createQuery(" FROM User ").list();
    }
    public User getUserByToken(String token){
        User found;
        Session session = sessionFactory.openSession();
        found = (User)session.createQuery("FROM User WHERE token = :token").
                setParameter("token",token).
                uniqueResult();
        session.close();
        return found;
    }

    public User getUserByUsername(String username){
        User found;
        Session session = sessionFactory.openSession();
        found = (User) session.createQuery("FROM User WHERE username = :username").
                setParameter("username",username).
                uniqueResult();
        session.close();
        return found;
    }

    public boolean usernameAvailable(String username) {
        boolean available = false;
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(
                    "SELECT user_id " + "FROM users " + "WHERE username = ?");
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                available = false;
            } else {
                available = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return available;
    }


    public String getUserByCreds(String username, String token) {
        String response = null;
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(
                    "SELECT * FROM Users WHERE username = ? AND token = ?");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, token);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                response = token;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return response;
    }
   public User getUserByUsernameAndToken(String username , String token){
        User found;
        Session session = sessionFactory.openSession();
        found = (User)session.createQuery("FROM User WHERE username = :username " +
                "AND token = :token")
                .setParameter("username" , username)
                .setParameter("token" , token)
                .uniqueResult();
        session.close();
        return found;
   }

    //Team Persist...
    public List<Team> getAllTeamsByLeagueID(int leagueID) {
        return sessionFactory.openSession().
                createQuery(" FROM Team WHERE league.leagueId=:leagueID")
                .setParameter("leagueID",leagueID).list();
    }

    public Team getTeamByTeamId(int teamID){

        return (Team) sessionFactory.openSession().
                createQuery("FROM Team WHERE team_id = :teamID").
                setParameter("teamID", teamID).uniqueResult();
    }

    public boolean isTeamNotMatchToSpecificLeague(int teamID , int leagueID){

        List <Team> teams = sessionFactory.openSession().createQuery
                        ("FROM Team WHERE team_id=:teamID AND league.leagueId=:leagueID").
                setParameter("teamID",teamID).setParameter("leagueID",leagueID).list();

        return teams.isEmpty();
    }
    public boolean TeamDoesntExist(int teamID){

        List <Team> teams = sessionFactory.openSession().createQuery
                ("FROM Team WHERE team_id=:teamID").setParameter
                ("teamID",teamID).list();

        return teams.isEmpty();
    }


//Game persist...

    public void saveGame(Game game){
        sessionFactory.openSession().save(game);
    }
    public Game addGame(User user ,int homeTeamID, int awayTeamID, int leagueID) {

        League league = getLeagueByLeagueID(leagueID);
        Team homeTeam = getTeamByTeamId(homeTeamID);
        Team awayTeam = getTeamByTeamId(awayTeamID);

        return new Game(user, homeTeam, awayTeam, league);
    }

    public boolean isLiveByTeamID(int teamID){

        List<Game> games = sessionFactory.openSession().createQuery
                ("FROM Game WHERE isLive=TRUE AND (homeTeam.team_id=:teamID OR awayTeam.team_id=:teamID)").
                setParameter("teamID",teamID).list();

        return !games.isEmpty();
    }

    public boolean isLiveByGameID(int gameID){

        List<Game> games = sessionFactory.openSession().createQuery
                        ("FROM Game WHERE isLive=TRUE AND game_Id=:gameID").
                setParameter("gameID",gameID).list();

        return games.isEmpty();
    }

    public List<Game>getAllGamesByLeagueID(int leagueID){
        return sessionFactory.openSession().
                createQuery(" FROM Game WHERE league.leagueId=:leagueID")
                .setParameter("leagueID",leagueID).list();
    }

    public List<Game>getAllLiveGamesByUserAndLeagueID(String token,int leagueID) {

        return sessionFactory.openSession().createQuery
                        ("FROM Game WHERE isLive=TRUE AND user.token = :token AND league.leagueId=:leagueID")
                .setParameter("token", token)
                .setParameter("leagueID",leagueID).list();
    }

    public List<Game>getAllLiveGames(){
        return sessionFactory.openSession().createQuery
                ("FROM Game WHERE isLive=TRUE ORDER BY league.leagueId").list();
    }
    public List<Game>getAllLiveGamesByLeagueID(int leagueID){
        return sessionFactory.openSession().createQuery
                ("FROM Game WHERE isLive=TRUE AND league.leagueId=:leagueID")
                .setParameter("leagueID",leagueID).list();
    }

    public List<Game>getAllFinishGamesByLeagueID(int leagueID){
        return sessionFactory.openSession().createQuery
                ("FROM Game WHERE isLive=FALSE AND league.leagueId=:leagueID")
                .setParameter("leagueID",leagueID).list();
    }

    public Game updateScoreHomeTeam(int gameID){

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Game game = session.get(Game.class,gameID);
        game.setScoreHomeTeam(game.getScoreHomeTeam()+1);
        session.update(game);
        transaction.commit();
        session.close();
        return game;
    }

    public Game updateScoreAwayTeam(int gameID){

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Game game = session.get(Game.class,gameID);
        game.setScoreAwayTeam(game.getScoreAwayTeam()+1);
        session.update(game);
        transaction.commit();
        session.close();
        return game;
    }

    public Game endGame(int gameID){

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Game game = session.get(Game.class,gameID);
        game.setLive(false);
        session.update(game);
        transaction.commit();
        session.close();
        return game;
    }

    // League persist...
    public List<League>getAllLeagues(){

        return sessionFactory.openSession().createQuery
                ("FROM League ").list();
    }

    public boolean LeagueDoesntExist(int leagueID){

        League league = (League) sessionFactory.openSession().createQuery
                ("FROM League WHERE leagueId=:leagueID").setParameter
                ("leagueID",leagueID).uniqueResult();

        return league==null;
    }

    public League getLeagueByLeagueID(int leagueID){

        return (League) sessionFactory.openSession().createQuery
                ("FROM League WHERE leagueId=:leagueID").
                setParameter("leagueID",leagueID).uniqueResult();
    }
}
