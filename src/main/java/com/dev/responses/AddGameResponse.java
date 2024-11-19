package com.dev.responses;

import com.dev.models.Game;

public class AddGameResponse extends BasicResponse {

    private Game game;

    public AddGameResponse(boolean success, Integer errorCode, Game game) {
        super(success, errorCode);
        this.game = game;
    }

    public Game getGame() {return game;}

    public void setGame(Game game) {this.game = game;}
}
