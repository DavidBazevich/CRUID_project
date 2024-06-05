package com.example.lab_9;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SwitchScene {

    public void Switch(String link){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource(link));

        try {
            loader.load();
        } catch (IOException e){
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

    }

}