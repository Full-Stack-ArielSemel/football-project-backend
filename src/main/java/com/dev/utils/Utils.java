package com.dev.utils;

import com.dev.models.Game;
import com.dev.models.Team;
import com.dev.models.TeamStats;
import org.springframework.stereotype.Component;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import static com.dev.utils.Constants.MINIMAL_PASSWORD_LENGTH;
import static com.dev.utils.Constants.MINIMAL_USERNAME_LENGTH;

@Component
public class Utils {

    public String createHash (String username, String password) {
        String raw = String.format("%s_%s", username, password);
        String myHash = null;

        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(raw.getBytes());
            byte[] digest = md.digest();
            myHash = DatatypeConverter.printHexBinary(digest).toUpperCase();
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        return myHash;
    }

    public boolean validUserNameSize(String username){
        return username.length()>=MINIMAL_USERNAME_LENGTH;
    }

    public boolean validPasswordSize (String password) {
        return password.length()>=MINIMAL_PASSWORD_LENGTH;
    }

    public boolean isUsernameContainsLetter(String username){
        return Pattern.compile("[a-zA-Z]").matcher(username).find();
    }

    public boolean isPasswordContainsCapitalLetter(String password){
        return Pattern.compile("[A-Z]").matcher(password).find();
    }

    public boolean isPasswordContainsLowerLetter(String password){
        return Pattern.compile("[a-z]").matcher(password).find();
    }

    public boolean isPasswordContainsDigit(String password){
        return Pattern.compile("[0-9]").matcher(password).find();
    }

    public boolean isUsernameStartWithLetter(String username){
        return Character.isLetter(username.charAt(0));
    }

    public boolean isPasswordStartsWithLetterOrDigit(String password) {
        return Character.isLetterOrDigit(password.charAt(0));
    }
    public boolean isUsernameContainsSpaces(String username) {
        return username != null && username.contains(" ");
    }
    
    public boolean isPasswordContainsSpaces(String password) {
        return password != null && password.contains(" ");
    }


    public List<TeamStats> calculateTable(List<Team> teams, List<Game>games) {
        List<TeamStats> teamStats = new ArrayList<>();
        teamStats = initializeTeams(teams, teamStats);
        for (Game game : games) {
            Integer winnerTeamId = null;
            Integer loserTeamId = null;
            Integer winnerTeamGoals = null;
            Integer loserTeamGoals = null;
            int team1Id = game.getHomeTeam().getId();
            int team2Id = game.getAwayTeam().getId();
            int team1Goals = game.getScoreHomeTeam();
            int team2Goals = game.getScoreAwayTeam();
            if (team1Goals == team2Goals) {
                updateTeamStats(teamStats, team1Id, team2Id, team1Goals, team2Goals);
            } else {
                winnerTeamId = team1Goals > team2Goals ? team1Id : team2Id;
                loserTeamId = winnerTeamId == team1Id ? team2Id : team1Id;
                winnerTeamGoals = winnerTeamId == team1Id ? team1Goals : team2Goals;
                loserTeamGoals = winnerTeamGoals == team1Goals ? team2Goals : team1Goals;
                updateTeamStats(teamStats, winnerTeamId, loserTeamId, winnerTeamGoals, loserTeamGoals);
            }
        }
        return teamStats;
    }

    public void updateTeamStats(List<TeamStats> teamStatsList, int winnerTeamId, int loserTeamId, int winnerTeamGoals, int loserTeamGoals) {
        int updatedTeams = 0;
        MatchType matchType;
        int balance = winnerTeamGoals - loserTeamGoals;
        while (updatedTeams < 2) {
            for (TeamStats teamStats : teamStatsList) {
                if (teamStats.getId() == winnerTeamId) {
                    matchType = winnerTeamGoals == loserTeamGoals ? MatchType.DRAW : MatchType.WIN;
                    teamStats.setGoalsFor(teamStats.getGoalsFor() + winnerTeamGoals);
                    teamStats.setGoalsAgainst(teamStats.getGoalsAgainst() + loserTeamGoals);
                    teamStats.setTotalGames(teamStats.getTotalGames() + 1);
                    teamStats.setGoalsBalance(teamStats.getGoalsBalance() + balance);
                    switch (matchType) {
                        case DRAW:
                            teamStats.setNumberOfDraws(teamStats.getNumberOfDraws() + 1);
                            teamStats.setPoints(teamStats.getPoints() + 1);
                            break;
                        case WIN:
                            teamStats.setNumberOfWins(teamStats.getNumberOfWins() + 1);
                            teamStats.setPoints(teamStats.getPoints() + 3);
                            break;
                    }
                    updatedTeams++;
                }
                if (teamStats.getId() == loserTeamId) {
                    matchType = winnerTeamGoals == loserTeamGoals ? MatchType.DRAW : MatchType.LOSE;
                    teamStats.setGoalsFor(teamStats.getGoalsFor() + loserTeamGoals);
                    teamStats.setGoalsAgainst(teamStats.getGoalsAgainst() + winnerTeamGoals);
                    teamStats.setTotalGames(teamStats.getTotalGames() + 1);
                    teamStats.setGoalsBalance(teamStats.getGoalsBalance() + (balance * (-1)));

                    switch (matchType) {
                        case DRAW:
                            teamStats.setNumberOfDraws(teamStats.getNumberOfDraws() + 1);
                            teamStats.setPoints(teamStats.getPoints() + 1);
                            break;
                        case LOSE:
                            teamStats.setNumberOfLoses(teamStats.getNumberOfLoses() + 1);
                            // lose 0 points
                            break;
                    }
                    updatedTeams++;
                }
            }
        }
    }

    public List<TeamStats> initializeTeams(List<Team> teams, List<TeamStats> teamStats) {
        for (Team team : teams) {
            teamStats.add(new TeamStats(team.getId(), team.getName(), team.getIcon(),team.getLeague()));
        }
        return teamStats;
    }
}
