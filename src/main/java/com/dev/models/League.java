package com.dev.models;

import javax.persistence.*;

@Entity
@Table(name="leagues")

public class League {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="league_id")
    private Integer leagueId;

    @Column
    private String name;

    @Column
    private String logo;

    public League(){}

    public League(Integer id ,String name, String logo){
        this.leagueId = id;
        this.name=name;
        this.logo=logo;
    }
    public League (String name, String logo){
        this.name = name;
        this.logo = logo;
    }

    public Integer getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(Integer leagueId) {
        this.leagueId = leagueId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }
}
