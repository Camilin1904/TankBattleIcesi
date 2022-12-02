package game.ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class WinController implements Initializable {

    @FXML
    ImageView imageView = new ImageView();

    @FXML
    Button board = new Button();

    @FXML
    Button play = new Button();

    public PlayerS player;

    public static WinController instance =  new WinController();

    @FXML
    Label avatar = new Label();

    @FXML
    Label winner = new Label();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String uri = "file:" + FrontlineDuel.class.getResource("winner.gif").getPath();
        Image image = new Image(uri);
        imageView.setImage(image);
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

        board.setStyle("-fx-padding: 8 15 15 15;\n" +
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

        avatar.setText(instance.getPlayer().getName());
        setFond();

    }

    @FXML
    private void clickPlay(){
        Stage stage = (Stage) play.getScene().getWindow();
        stage.close();
        FrontlineDuel.showWindow("start.fxml");

    }
    @FXML
    private void clickBoard(){
        Stage stage = (Stage) play.getScene().getWindow();
        stage.close();
        FrontlineDuel.showWindow("leaderBoard.fxml");

    }

    public void setPlayer(PlayerS player) {
        this.player = player;
    }

    public PlayerS getPlayer() {
        return player;
    }

    public WinController getInstance() {
        return instance;
    }

    public void setInstance(WinController instance) {
        WinController.instance = instance;
    }

    InputStream is1 = getClass().getResourceAsStream("MP16REG.ttf");
    private Font pixeman1 = Font.loadFont(is1, 40);

    public void setFond(){
        winner.setFont(pixeman1);
        avatar.setFont(pixeman1);
    }
}
