module game.ui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires com.google.gson;


    opens game.ui to javafx.fxml, com.google.gson;
    //opens game.ui to com.google.gson;
    exports game.ui;
}
