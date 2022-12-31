package com.monopoly.monopolyprojetoo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    private static final int WIDTH=1100;
    private static final int HEIGHT=1100;

    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));


        stage.setTitle("Monopoly - Projet OO");
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        stage.setScene(new Scene(new StackPane(canvas)));
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}