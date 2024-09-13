package com.dev.controllers;

import com.dev.objects.Game;
import com.dev.objects.Team;
import com.dev.objects.TeamStats;
import com.dev.responses.BasicResponse;
import com.dev.responses.GetLiveTableResponse;
import com.dev.responses.GetTableResponse;
import com.dev.utils.Persist;
import com.dev.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
public class TableController {

    @Autowired
    private Utils utils;
    @Autowired
    private Persist persist;

    @PostConstruct
    public void init () {}

    @RequestMapping(value = "/get-general-table-by-league-id", method = RequestMethod.GET)
    public BasicResponse getTable(int leagueID){
        BasicResponse response;
        List<Game> endedMatches = persist.getAllFinishGamesByLeagueID(leagueID);
        List<Team> teams = persist.getAllTeamsByLeagueID(leagueID);
        List<TeamStats> teamStats = utils.calculateTable(teams,endedMatches);
        response = new GetTableResponse(true,null,teamStats);
        return response;
    }

    @RequestMapping(value = "/get-live-table-by-league-id", method = RequestMethod.GET)
    public BasicResponse getLiveTableByLeagueID(int leagueID){
        List<TeamStats> teamStats;
        BasicResponse baseResponse;
        List<Game> allMatches = persist.getAllGamesByLeagueID(leagueID);
        List<Team> teams = persist.getAllTeamsByLeagueID(leagueID);
        teamStats = utils.calculateTable(teams,allMatches);
        baseResponse = new GetLiveTableResponse(true,null,teamStats);
        return baseResponse;
    }
}
