package game.ui;

import game.model.Singleton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.control.Button;
import javafx.stage.Stage;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

    @FXML
    ImageView imageView = new ImageView();

    @FXML
    Button board = new Button();

    @FXML
    Button play = new Button();

    @FXML
    Button exit = new Button();

    @FXML
    TextField player1 = new TextField();

    @FXML
    TextField player2 = new TextField();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String uri = "file:" + FrontlineDuel.class.getResource("bg.gif").getPath();
        Image image = new Image(uri);
        imageView.setImage(image);
        play.setStyle("    -fx-background-color: \n" +
                "        #000000,\n" +
                "        linear-gradient(#7ebcea, #2f4b8f),\n" +
                "        linear-gradient(#426ab7, #263e75),\n" +
                "        linear-gradient(#395cab, #223768);\n" +
                "    -fx-background-insets: 0,1,2,3;\n" +
                "    -fx-background-radius: 3,2,2,2;\n" +
                "    -fx-padding: 12 30 12 30;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-font-size: 12px;\n");

        board.setStyle("    -fx-background-color: \n" +
                "        #000000,\n" +
                "        linear-gradient(#7ebcea, #2f4b8f),\n" +
                "        linear-gradient(#426ab7, #263e75),\n" +
                "        linear-gradient(#395cab, #223768);\n" +
                "    -fx-background-insets: 0,1,2,3;\n" +
                "    -fx-background-radius: 3,2,2,2;\n" +
                "    -fx-padding: 12 30 12 30;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-font-size: 12px;\n");

        exit.setStyle("    -fx-background-color: \n" +
                "        #000000,\n" +
                "        linear-gradient(#7ebcea, #2f4b8f),\n" +
                "        linear-gradient(#426ab7, #263e75),\n" +
                "        linear-gradient(#395cab, #223768);\n" +
                "    -fx-background-insets: 0,1,2,3;\n" +
                "    -fx-background-radius: 3,2,2,2;\n" +
                "    -fx-padding: 12 30 12 30;\n" +
                "    -fx-text-fill: white;\n" +
                "    -fx-font-size: 12px;\n");

    }

    @FXML
    private void clickPlay(){
        if(player1.getText()!=null&&!player1.getText().equals("")&&player2.getText()!=null&&!player2.getText().equals("")){
            Singleton.getInstance().createPlayers(player1.getText(),player2.getText());
            Stage stage = (Stage) play.getScene().getWindow();
            stage.close();
            FrontlineDuel.showWindow("canvasView.fxml");
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR, "Ambos campos de nombres deben estar llenos");
            alert.show();
        }

    }

    @FXML
    private void exit(){
        Stage stage = (Stage) play.getScene().getWindow();
        stage.close();
    }
}
