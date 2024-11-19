package com.dev.controllers;
import com.dev.models.Team;
import com.dev.utils.Persist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TeamController {
    @Autowired
    private Persist persist;

    @RequestMapping(value = "/get-teams-by-league-id", method = RequestMethod.GET)
    public List<Team>getAllTeams(int leagueID) {
        return persist.getAllTeamsByLeagueID(leagueID);
    }


    @RequestMapping(value = "/checking-live-by-teamid")
    public boolean checkLiveByTeamID(int teamID){
        return persist.isLiveByTeamID(teamID);
    }

}
