package com.dev.responses;

import com.dev.models.Game;

public class UpdateScoreResponse extends BasicResponse{

    private Game game;

    public UpdateScoreResponse(boolean success, Integer errorCode, Game game) {
        super(success, errorCode);
        this.game = game;
    }

    public Game getGame() {return game;}

    public void setGame(Game game) {this.game = game;}
}
