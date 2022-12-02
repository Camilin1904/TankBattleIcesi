package game.ui;

import game.Read.ToJsonReader;
import game.model.ScoreboardS;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    private TableColumn<PlayerS, Integer> position;

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

    private ObservableList<PlayerS> list = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        play.setStyle("-fx-padding: 8 15 15 15;\n" +
                "    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n" +
                "    -fx-background-radius: 8;\n" +
                "    -fx-background-color:\n" +
                "    linear-gradient(from 0% 93% to 0% 100%, #a34313 0%, #903b12 100%),\n" +
                "        #9d4024,\n" +
                "                #d86e3a,\n" +
                "    radial-gradient(center 50% 50%, radius 100%, #d86e3a, #c54e2c);\n" +
                "    -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-font-size: 1.1em;");

        exit.setStyle("-fx-padding: 8 15 15 15;\n" +
                "    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n" +
                "    -fx-background-radius: 8;\n" +
                "    -fx-background-color:\n" +
                "    linear-gradient(from 0% 93% to 0% 100%, #a34313 0%, #903b12 100%),\n" +
                "        #9d4024,\n" +
                "                #d86e3a,\n" +
                "    radial-gradient(center 50% 50%, radius 100%, #d86e3a, #c54e2c);\n" +
                "    -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-font-size: 1.1em;");

        gc = canvas.getGraphicsContext2D();
        canvas.setFocusTraversable(true);
        drawBackground();

        position.setCellValueFactory(new PropertyValueFactory<>("position"));

        name.setCellValueFactory(new PropertyValueFactory<>("name"));

        score.setCellValueFactory(new PropertyValueFactory<>("wonGames"));

        actualize();
        System.out.println(list.size());
        tableView.setItems(list);
        System.out.println("Se supone que ya");

    }

    public void drawBackground(){
        gc.save();
        gc.drawImage(image, 0, 0, 700, 700);
        gc.restore();
    }

    public void actualize(){
        scoreboard = ScoreboardS.getInstance();
        list.addAll(scoreboard.getPlayers());
    }


    @FXML
    public void clickPlay(){
        Stage stage = (Stage) play.getScene().getWindow();
        stage.close();
        FrontlineDuel.showWindow("start.fxml");
    }
    @FXML
    public void clickExit(){
        scoreboard = ScoreboardS.getInstance();
        scoreboard.exportar();
        Stage stage = (Stage) exit.getScene().getWindow();
        stage.close();
    }





}
