package game.ui;

import game.model.PlayerS;
import game.model.ScoreboardS;
import javafx.beans.InvalidationListener;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.stage.Stage;



import java.net.URL;
import java.util.*;

public class ScoreboardController implements Initializable {


    @FXML
    private Canvas canvas;

    private GraphicsContext gc;
    String uri = "file:"+FrontlineDuel.class.getResource("leaderFondo.jpg").getPath();
    private Image image = new Image(uri);

    @FXML
    private TableColumn<Integer, Integer> position;

    @FXML
    private TableView<PlayerS> tableView;
    @FXML
    private TableColumn<PlayerS, String>  name;
    @FXML
    private TableColumn<PlayerS, Integer> score;

    private ScoreboardS scoreboard;

    @FXML
    Button exit = new Button();

    @FXML
    Button play = new Button();

    private ObservableList<PlayerS> list = new ObservableList<PlayerS>() {
        @Override
        public void addListener(ListChangeListener<? super PlayerS> listChangeListener) {

        }

        @Override
        public void removeListener(ListChangeListener<? super PlayerS> listChangeListener) {

        }

        @Override
        public boolean addAll(PlayerS... playerS) {
            return false;
        }

        @Override
        public boolean setAll(PlayerS... playerS) {
            return false;
        }

        @Override
        public boolean setAll(Collection<? extends PlayerS> collection) {
            return false;
        }

        @Override
        public boolean removeAll(PlayerS... playerS) {
            return false;
        }

        @Override
        public boolean retainAll(PlayerS... playerS) {
            return false;
        }

        @Override
        public void remove(int i, int i1) {

        }

        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @Override
        public Iterator<PlayerS> iterator() {
            return null;
        }

        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return null;
        }

        @Override
        public boolean add(PlayerS playerS) {
            return false;
        }

        @Override
        public boolean remove(Object o) {
            return false;
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean addAll(Collection<? extends PlayerS> c) {
            return false;
        }

        @Override
        public boolean addAll(int index, Collection<? extends PlayerS> c) {
            return false;
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public PlayerS get(int index) {
            return null;
        }

        @Override
        public PlayerS set(int index, PlayerS element) {
            return null;
        }

        @Override
        public void add(int index, PlayerS element) {

        }

        @Override
        public PlayerS remove(int index) {
            return null;
        }

        @Override
        public int indexOf(Object o) {
            return 0;
        }

        @Override
        public int lastIndexOf(Object o) {
            return 0;
        }

        @Override
        public ListIterator<PlayerS> listIterator() {
            return null;
        }

        @Override
        public ListIterator<PlayerS> listIterator(int index) {
            return null;
        }

        @Override
        public List<PlayerS> subList(int fromIndex, int toIndex) {
            return null;
        }

        @Override
        public void addListener(InvalidationListener invalidationListener) {

        }

        @Override
        public void removeListener(InvalidationListener invalidationListener) {

        }
    };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gc = canvas.getGraphicsContext2D();
        canvas.setFocusTraversable(true);
        drawBackground();
        actualize();
        PlayerS player = new PlayerS("Samuel");
        player.setGamesWon(1);
        list.add(player);
        tableView.setItems(list);



    }

    public void drawBackground(){
        gc.save();
        gc.drawImage(image, 0, 0, 700, 700);
        gc.restore();
    }

    public void actualize(){
        scoreboard = ScoreboardS.getInstance();
       list.addAll(scoreboard.getPlayers()) ;
    }

   /* public void updateSb(){
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

    }*/

    @FXML
    public void clickPlay(){
        Stage stage = (Stage) play.getScene().getWindow();
        stage.close();
        FrontlineDuel.showWindow("start.fxml");
    }
    @FXML
    public void clickExit(){
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }



}
