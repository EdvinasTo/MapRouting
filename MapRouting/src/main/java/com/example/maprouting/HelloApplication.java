package com.example.maprouting;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Group root = new Group();
        //FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(root, 320, 240, Color.OLIVE);
        stage.setTitle("Map Routing");
        Text text = new Text("1");
        text.setX(100);
        text.setY(100);
        //text.set
        text.setFill(Color.WHITE);
        Circle circle = new Circle(100, 100, 100, Color.BLACK);
        //circle.setFill(Color.SKYBLUE);
        Line line = new Line();
        line.setStartX(100);
        line.setStartY(100);
        line.setEndX(400);
        line.setEndY(600);

        circle.setRadius(50);
        root.getChildren().add(circle);
        root.getChildren().add(text);
        root.getChildren().add(line);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}