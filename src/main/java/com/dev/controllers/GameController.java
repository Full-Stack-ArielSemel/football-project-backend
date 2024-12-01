
package com.dev.controllers;
import com.dev.models.Game;
import com.dev.models.User;
import com.dev.responses.BasicResponse;
import com.dev.responses.AddGameResponse;
import com.dev.responses.UpdateScoreResponse;
import com.dev.utils.Persist;
import com.dev.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static com.dev.utils.Errors.*;
import java.util.List;
import static com.dev.utils.Errors.ERROR_NO_SUCH_TOKEN;

@RestController
public class GameController {

    @Autowired
    private Utils utils;

    @Autowired
    private Persist persist;

    @RequestMapping(value = "/get-all-games-by-league_id")
    public List<Game>getAllGames(int leagueID){
        List<Game> allGames = persist.getAllGamesByLeagueID(leagueID);
        return allGames;
    }

    @RequestMapping(value = "/get-all-live-games-by-user-and-league_id")
    public List<Game>getAllLiveGamesByUser(String token,int leagueID){
        List<Game> userLiveGames = persist.getAllLiveGamesByUserAndLeagueID(token,leagueID);
        return userLiveGames;
    }

    @RequestMapping(value ="/get-all-live-games")
    public List<Game>getAllLiveGames(){
        List<Game>allLiveGames = persist.getAllLiveGames();
        return allLiveGames;
    }

    @RequestMapping(value ="/get-all-live-games-by-league_id")
    public List<Game>getAllLiveGamesByLeague(int leagueID){
        List<Game>allLiveGames = persist.getAllLiveGamesByLeagueID(leagueID);
        return allLiveGames;
    }

    @RequestMapping(value ="/get-all-finish-games-by-league")
    public List<Game>getAllFinishGames(int leagueID){
        List<Game>finishGames = persist.getAllFinishGamesByLeagueID(leagueID);
        return finishGames;
    }


   @RequestMapping(value = "/new-game")

    public BasicResponse addNEwGame(String token , int homeTeamID , int awayTeamID, int leagueID){

       if(homeTeamID==awayTeamID){
           return new BasicResponse(false,ERROR_CHOOSING_TWO_IDENTICAL_TEAMS);
       }
       User user = persist.getUserByToken(token);
       if(user==null){
           return new BasicResponse(false,ERROR_NO_SUCH_TOKEN);
       }
       if(persist.LeagueDoesntExist(leagueID)){
           return new BasicResponse(false,ERROR_LEAGUE_DOESNT_EXIST);
       }
       if(persist.TeamDoesntExist(homeTeamID)){
           return new BasicResponse(false,ERROR_HOME_TEAM_DOESNT_EXIST);
       }

       if(persist.TeamDoesntExist(awayTeamID)){
           return new BasicResponse(false,ERROR_AWAY_TEAM_DOESNT_EXIST);
       }
       if(persist.isLiveByTeamID(homeTeamID)){
            return new BasicResponse(false,ERROR_HOME_TEAM_ALREADY_LIVE);
        }
       if(persist.isLiveByTeamID(awayTeamID)){
           return new BasicResponse(false,ERROR_AWAY_TEAM_ALREADY_LIVE);
       }
       if(persist.isTeamNotMatchToSpecificLeague(homeTeamID,leagueID)){
           return new BasicResponse(false,ERROR_HOME_TEAM_DOESNT_EXIST_IN_THIS_LEAGUE);
       }
       if(persist.isTeamNotMatchToSpecificLeague(awayTeamID,leagueID)){
           return new BasicResponse(false,ERROR_AWAY_TEAM_DOESNT_EXIST_IN_THIS_LEAGUE);
       }
       Game game = persist.addGame(user,homeTeamID,awayTeamID,leagueID);
       persist.saveGame(game);
       return new AddGameResponse(true,null, game);
   }

   @RequestMapping(value = "/update-score-home-team")

    public BasicResponse updateScoreHomeTeam(int gameID){

        if(persist.isLiveByGameID(gameID)){
            return new BasicResponse(false,ERROR_GAME_NOT_IN_LIVE);
        }
        else{
            Game game = persist.updateScoreHomeTeam(gameID);
            return new UpdateScoreResponse(true,null,game);
        }
   }

    @RequestMapping(value = "/update-score-away-team")

    public BasicResponse updateScoreAwayTeam(int gameID){

        if(persist.isLiveByGameID(gameID)){
            return new BasicResponse(false,ERROR_GAME_NOT_IN_LIVE);
        }
        else{
            Game game = persist.updateScoreAwayTeam(gameID);
            return new UpdateScoreResponse(true,null,game);
        }
    }

    @RequestMapping(value = "/end-game")

    public Game endGame (int gameID){

        return persist.endGame(gameID);
    }

}
