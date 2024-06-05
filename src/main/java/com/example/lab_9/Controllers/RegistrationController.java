package com.example.lab_9.Controllers;

import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ResourceBundle;

import com.example.lab_9.DatabaseHandler;
import com.example.lab_9.SwitchScene;
import com.example.lab_9.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegistrationController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button confirmButton;

    @FXML
    private TextField loginField;

    @FXML
    private TextField nameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField surnameField;

    @FXML
    void initialize() {

        confirmButton.setOnAction(actionEvent -> {

            signUpUser();

            confirmButton.getScene().getWindow().hide();
            SwitchScene scene = new SwitchScene();
            scene.Switch("HomePage.fxml");

        });

    }

    private static String Hash(String text){
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = messageDigest.digest(text.getBytes(StandardCharsets.UTF_8));
            StringBuilder hashedText = new StringBuilder();
            for (byte i:hashedBytes){
                hashedText.append(String.format("%02x",i));
            }
            return hashedText.toString();
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    private void signUpUser() {

        DatabaseHandler databaseHandler = new DatabaseHandler();
        String name = nameField.getText();
        String surname = surnameField.getText();
        String login = loginField.getText();
        String password =  Hash(passwordField.getText()) ;
        User user = new User(name,surname,login,password);

        databaseHandler.signUpUser(user);

    }

}