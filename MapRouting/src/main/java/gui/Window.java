package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Window extends Application {


    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();
        CustomPane pane = new CustomPane(stage);
        root.setRight(pane.getPane());
        root.setCenter(pane.getCenterPane());
        Scene scene = new Scene(root);
        stage.setTitle("Map Routing");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
