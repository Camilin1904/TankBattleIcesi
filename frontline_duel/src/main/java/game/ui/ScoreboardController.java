package game.ui;

import game.model.PlayerS;
import game.model.ScoreboardS;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


import javax.swing.table.TableColumn;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;

public class ScoreboardController implements Initializable {


    @FXML
    private Canvas canvas;

    private GraphicsContext gc;
    String uri = "file:"+FrontlineDuel.class.getResource("leaderFondo.jpeg").getPath();
    private Image image = new Image(uri);

    @FXML
    private TableColumn position;
    @FXML
    private TableColumn name;
    @FXML
    private TableColumn score;

    private ScoreboardS scoreboard;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gc = canvas.getGraphicsContext2D();
        canvas.setFocusTraversable(true);
        drawBackground();
    }

    public void drawBackground(){
        gc.save();
        gc.drawImage(image, 0, 0, 700, 700);
        gc.restore();
    }

    public void updateSb(){
        ArrayList<PlayerS> players = ScoreboardS.getInstance().getPlayers();
        if(players!=null) {
            if (players.size() >= 1 && players.get(0) != null) {
                name1.setText(players.get(0).getName());
                score1.setText("won: " + players.get(0).getGamesWon() + "");
            }
            if (players.size() >= 2 && players.get(1) != null) {
                name2.setText(players.get(1).getName());
                score2.setText("won: " + players.get(1).getGamesWon() + "");
            }
            if (players.size() >= 3 && players.get(2) != null) {
                name3.setText(players.get(2).getName());
                score3.setText("won: " + players.get(2).getGamesWon() + "");
            }
            if (players.size() >= 4 && players.get(3) != null) {
                name4.setText(players.get(3).getName());
                score4.setText("won: " + players.get(3).getGamesWon() + "");
            }
            if (players.size() >= 5 && players.get(4) != null) {
                name5.setText(players.get(4).getName());
                score5.setText("won: " + players.get(4).getGamesWon() + "");
            }
            if (players.size() >= 6 && players.get(5) != null) {
                name6.setText(players.get(5).getName());
                score6.setText("won: " + players.get(5).getGamesWon() + "");
            }
            if (players.size() >= 7 && players.get(6) != null) {
                name7.setText(players.get(6).getName());
                score7.setText("won: " + players.get(6).getGamesWon() + "");
            }
            if (players.size() >= 8 && players.get(7) != null) {
                name8.setText(players.get(7).getName());
                score8.setText("won: " + players.get(7).getGamesWon() + "");
            }
            if (players.size() >= 9 && players.get(8) != null) {
                name9.setText(players.get(8).getName());
                score9.setText("won: " + players.get(8).getGamesWon() + "");
            }
            if (players.size() >= 10 && players.get(9) != null) {
                name10.setText(players.get(9).getName());
                score10.setText("won: " + players.get(9).getGamesWon() + "");
            }
        }

    }


}
