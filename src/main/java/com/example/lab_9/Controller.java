package com.example.lab_9;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.example.lab_9.Animations.Shake;
import com.example.lab_9.Controllers.NotesListController;
import com.example.lab_9.DatabaseHandler;
import com.example.lab_9.SwitchScene;
import com.example.lab_9.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;


public class Controller {
    private Parent r;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label errorLabel;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button signInButton;

    @FXML
    private Hyperlink signUpLink;

    @FXML
    void initialize() {

        signInButton.setOnAction(actionEvent -> {

            String loginText = loginField.getText().trim();
            String loginPassword = Hash(passwordField.getText().trim());

            if (!loginText.equals("") && !loginPassword.equals("")){
                loginUser(loginText, loginPassword);
            } else {
                errorLabel.setText("Uncorrected data");
            }

        });

        signUpLink.setOnAction(actionEvent -> {

            signUpLink.getScene().getWindow().hide();

            SwitchScene scene = new SwitchScene();
            scene.Switch("RegistrationForm.fxml");

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

    private void loginUser(String loginText, String loginPassword){

        DatabaseHandler databaseHandler = new DatabaseHandler();
        User user = new User();
        user.setLogin(loginText);
        user.setPassword(loginPassword);
        ResultSet resultSet = databaseHandler.getUser(user);

        int counter = 0;

        try {
            while (resultSet.next()){
                counter++;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }

        if (counter >= 1){

            String log = loginField.getText();
            String pas = Hash(passwordField.getText());
            signInButton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("NotesList.fxml"));

            try {
                r = loader.load();
                NotesListController notesListController = loader.getController();
                notesListController.displayData(log, pas);
            } catch (IOException e){
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

        } else {
            Shake userLogin = new Shake(loginField);
            Shake userPassword = new Shake(passwordField);
            userLogin.playAnimation();
            userPassword.playAnimation();
        }

    }

}