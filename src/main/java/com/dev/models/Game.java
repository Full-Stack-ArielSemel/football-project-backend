package com.dev.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name="games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="game_id")
    private Integer game_Id;

    @Column(name="is_live")
    private boolean isLive;

    @Column(name="score_home_team")
    private int scoreHomeTeam;

    @Column(name="score_away_team")
    private int scoreAwayTeam;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn (name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name ="home_team_id")
    private Team homeTeam;
    
    @ManyToOne
    @JoinColumn(name = "away_team_id")
    private Team awayTeam;

    @ManyToOne
    @JoinColumn(name="league_id")
    private League league;

    @Column(name = "start_date")
    private String startDate;


    public Game(){}

    public Game(User user, Team homeTeam, Team awayTeam,League league) {

        this.isLive = true;
        this.scoreHomeTeam = 0;
        this.scoreAwayTeam = 0;
        this.user = user;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.league = league;
        this.startDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy, HH:mm"));
    }

    public int getGame_Id() {
        return game_Id;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public int getScoreHomeTeam() {
        return scoreHomeTeam;
    }

    public void setScoreHomeTeam(int scoreHomeTeam) {
        this.scoreHomeTeam = scoreHomeTeam;
    }

    public int getScoreAwayTeam() {
        return scoreAwayTeam;
    }

    public void setScoreAwayTeam(int scoreAwayTeam) {
        this.scoreAwayTeam = scoreAwayTeam;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }
    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public String getStartDate() {
        return this.startDate;
    }
}
