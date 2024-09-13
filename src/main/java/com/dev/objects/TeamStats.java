package com.dev.objects;


public class TeamStats extends Team {

    int totalGames;
    int goalsFor;
    int goalsAgainst;
    int numberOfWins;
    int numberOfLoses;
    int numberOfDraws;
    int goalsBalance;
    int points;


    public TeamStats(int id ,String name, String icon,League league){
        super(id,name,icon,league);
        this.goalsFor=0;
        this.goalsAgainst=0;
        this.numberOfWins=0;
        this.numberOfLoses=0;
        this.numberOfDraws=0;
        this.points=0;
        this.goalsBalance = 0;
        this.totalGames=0;
    }

    public int getGoalsBalance() {
        return goalsBalance;
    }

    public void setGoalsBalance(int goalsBalance) {
        this.goalsBalance = goalsBalance;
    }

    public int getTotalGames() {
        return totalGames;
    }

    public void setTotalGames(int totalGames) {
        this.totalGames = totalGames;
    }

    public int getGoalsFor() {
        return goalsFor;
    }

    public void setGoalsFor(int goalsFor) {
        this.goalsFor = goalsFor;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(int goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    public int getNumberOfWins() {
        return numberOfWins;
    }

    public void setNumberOfWins(int numberOfWins) {
        this.numberOfWins = numberOfWins;
    }

    public int getNumberOfLoses() {
        return numberOfLoses;
    }

    public void setNumberOfLoses(int numberOfLoses) {
        this.numberOfLoses = numberOfLoses;
    }

    public int getNumberOfDraws() {
        return numberOfDraws;
    }

    public void setNumberOfDraws(int numberOfDraws) {
        this.numberOfDraws = numberOfDraws;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
