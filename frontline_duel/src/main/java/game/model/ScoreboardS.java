package game.model;

import game.ui.PlayerS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ScoreboardS {

    private ArrayList<PlayerS> scoreBoard;

    private static ScoreboardS instance = new ScoreboardS();

    public ScoreboardS(){
        scoreBoard = new ArrayList<>();
    }

    public void insert(PlayerS p){

        if(search(p)==null){
            scoreBoard.add(p);
        }else wonAGame(search(p));;
        scoreBoard.sort(new Comparator<PlayerS>() {
            @Override
            public int compare(PlayerS o1, PlayerS o2) {
                return o1.getGamesWon() - o2.getGamesWon();
            }
        });
        Collections.reverse(scoreBoard);

    }

    public PlayerS search(PlayerS p){
        for(PlayerS x : scoreBoard){
            if(p.getName().equals(x.getName()))
                return x;
        }
        return null;
    }

    public void wonAGame(PlayerS p){
        p.setGamesWon(p.getGamesWon()+1);
    }

    public static ScoreboardS getInstance() {
        return instance;
    }

    public ArrayList<PlayerS> getPlayers(){
        return scoreBoard;
    }
}
