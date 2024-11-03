package com.dev.objects;

import javax.persistence.*;

@Entity
@Table(name="teams")

    public class Team{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer team_id;
    @Column
    private String name;
    @Column
    private String icon;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="league_id")
    private League league;

    public Team(){}

    public Team (int team_id ,String name, String icon,League league) {

        this.league = league;
        this.team_id = team_id;
        this.name = name;
        this.icon=icon;
    }

    public Team(String name ,String icon,League league) {
        this.league = league;
        this.name = name;
        this.icon=icon;

    }

    public String getName() {return this.name;}

    public void setName(String name) {this.name = name;}

    public int getId() {return this.team_id;}

    public void setId(int team_id) {this.team_id = team_id;}

    public String getIcon() {return this.icon;}

    public void setIcon(String icon) {this.icon = icon;}

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }
}

