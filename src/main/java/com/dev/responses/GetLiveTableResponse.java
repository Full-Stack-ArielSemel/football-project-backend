package com.dev.responses;

import com.dev.models.TeamStats;

import java.util.List;

public class GetLiveTableResponse extends BasicResponse{

    List<TeamStats> teamStats;

    public GetLiveTableResponse(boolean success, Integer errorCode , List<TeamStats> teamStats) {
        super(success, errorCode);
        this.teamStats = teamStats;
    }

    public List<TeamStats> getTeamStats() {
        return teamStats;
    }

    public void setTeamStats(List<TeamStats> teamStats) {
        this.teamStats = teamStats;
    }
}
