package game.ui;

import game.model.ScoreboardS;
import game.model.Singleton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javax.sound.sampled.*;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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

    private ScoreboardS score = ScoreboardS.getInstance();

    @FXML
    Label boat = new Label();

    public void ReproducirSonido(){

        try {
            String path = FrontlineDuel.class.getResource("opening.wav").getPath();
            File file = new File(path);
            System.out.println(file.exists());
            System.out.println(file.getAbsolutePath());
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file.getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch(UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            System.out.println("Error al reproducir el sonido.");
        }
    }



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
        setFond();
        ReproducirSonido();
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
        score.exportar();
        Stage stage = (Stage) play.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void clickBoard(){
        Stage stage = (Stage) play.getScene().getWindow();
        stage.close();
        FrontlineDuel.showWindow("leaderBoard.fxml");

    }

    InputStream is1 = getClass().getResourceAsStream("MP16REG.ttf");
    private Font pixeman1 = Font.loadFont(is1, 55);

    public void setFond(){
        boat.setFont(pixeman1);

    }
}
