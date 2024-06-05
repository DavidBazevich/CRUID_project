package com.example.lab_9.Controllers;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.example.lab_9.DatabaseHandler;
import com.example.lab_9.Note;
import com.example.lab_9.SwitchScene;
import com.example.lab_9.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

public class NotesListController implements Initializable{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button addButton;

    @FXML
    private Button deleteButton;

    @FXML
    private ListView<String> listArea;

    @FXML
    private Button signOutButton;

    @FXML
    private Label nameField;

    @FXML
    private Label nameNote;

    @FXML
    private Button saveButton;

    @FXML
    private Label surnameField;

    @FXML
    private TextArea textNote;

    public void displayData(String login, String password){

        DatabaseHandler databaseHandler = new DatabaseHandler();
        Note note = new Note();
        User user = new User();
        note.setLogin(login);
        user.setLogin(login);
        user.setPassword(password);

        ResultSet resultSet = databaseHandler.getUser(user);
        ResultSet resultSet1 = databaseHandler.getNotes(note);

        try {

            while (resultSet.next()){
                nameField.setText(resultSet.getString(2));
                surnameField.setText(resultSet.getString(3));
            }

            while (resultSet1.next()){

                listArea.getItems().add(resultSet1.getString(1));
                listArea.getSelectionModel().selectedItemProperty().addListener((observableValue, s, t1) -> {

                    nameNote.setText(t1);
                    ResultSet resultSet2 = databaseHandler.getNoteData(t1);

                    try {
                        while (resultSet2.next()){
                            textNote.setText(resultSet2.getString(1));
                        }
                    } catch (SQLException e){
                        e.printStackTrace();
                    }

                });

                //ADD BUTTON
                addButton.setOnAction(actionEvent -> {
                    TextInputDialog dialog = new TextInputDialog();
                    dialog.setHeaderText("NOTE NAME");
                    dialog.setContentText("Name: ");

                    Optional<String> optional = dialog.showAndWait();

                    optional.ifPresent(lang-> {
                        note.setName(lang);
                        databaseHandler.addNote(note);
                        if (!listArea.getItems().contains(lang)){
                            listArea.getItems().add(lang);
                        }
                    });

                });

                //SAVE BUTTON
                saveButton.setOnAction(actionEvent -> {
                    databaseHandler.rewriteNote(textNote.getText(), nameNote.getText());
                });

                //DEL BUTTON
                deleteButton.setOnAction(actionEvent -> {
                    databaseHandler.deleteNote(nameNote.getText());
                    listArea.getItems().remove(nameNote.getText());
                });

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        signOutButton.setOnAction(actionEvent -> {
            signOutButton.getScene().getWindow().hide();
            SwitchScene scene = new SwitchScene();
            scene.Switch("HomePage.fxml");
        });

    }

}