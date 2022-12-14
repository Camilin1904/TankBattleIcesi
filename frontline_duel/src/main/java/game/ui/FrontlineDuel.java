package game.ui;

import game.Read.ToJsonReader;
import game.model.Scoreboard;
import game.model.ScoreboardS;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class FrontlineDuel extends Application {

    private static Scene scene;

    private ScoreboardS score = ScoreboardS.getInstance();

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("start"), 700, 700);
        stage.setScene(scene);
        stage.show();
        importar();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(FrontlineDuel.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void showWindow(String fxml) {
        FXMLLoader fxmlLoader = new FXMLLoader(FrontlineDuel.class.getResource(fxml));
        try {
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void importar(){
        score.importar();
    }

}