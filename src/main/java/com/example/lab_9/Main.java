package com.example.lab_9;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("HomePage.fxml"));
        stage.setScene(new Scene(root, 500, 700));
        stage.show();
    }

    public static void main(String[] args){
        launch();
    }

}