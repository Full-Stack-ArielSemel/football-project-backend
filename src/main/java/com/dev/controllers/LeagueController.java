package com.dev.controllers;

import com.dev.objects.League;
import com.dev.utils.Persist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LeagueController {

    @Autowired
    private Persist persist;

    @RequestMapping(value = "/get-all-leagues", method = RequestMethod.GET)
    public List<League> getAllLeagues() {
        return persist.getAllLeagues();
    }

    @RequestMapping(value = "/get-league-by-league-id",method = RequestMethod.GET)
    public League getLeagueByLeagueID(int leagueID){
        return persist.getLeagueByLeagueID(leagueID);
    }

}
