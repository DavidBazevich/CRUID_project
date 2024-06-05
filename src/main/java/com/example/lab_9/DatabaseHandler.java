package com.example.lab_9;

import com.example.lab_9.Constants.Configs;
import com.example.lab_9.Constants.Const;

import java.sql.*;

public class DatabaseHandler extends Configs {
    Connection dbConnection;
    public Connection getDbConnection() throws ClassNotFoundException, SQLException{

        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPassword);

        return dbConnection;
    }

    public void signUpUser(User user){

        String insert = "INSERT INTO " + Const.USER_TABLE + "(" + Const.USER_NAME + "," + Const.USER_SURNAME + ","
                + Const.USER_LOGIN + "," + Const.USER_PASSWORD + ")" + "VALUES(?,?,?,?) ";

        String createEmptyNote = "INSERT INTO " + Const.NOTE_TABLE +"(" + Const.NOTE_NAME + ", " + Const.NOTE_LOGIN + ")"
                + " VALUES ('Note_1', ?)";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.executeUpdate();

            PreparedStatement preparedStatement1 = getDbConnection().prepareStatement(createEmptyNote);
            preparedStatement1.setString(1, user.getLogin());
            preparedStatement1.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public ResultSet getUser(User user){

        ResultSet resultSet = null;

        String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE " + Const.USER_LOGIN + "=? AND "
                + Const.USER_PASSWORD + "=?";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resultSet;

    }

    public ResultSet getNotes(Note note){

        ResultSet resultSet = null;

        String select = "SELECT " + Const.NOTE_NAME + ", " + Const.NOTE_DATA + " FROM " + Const.NOTE_TABLE + " WHERE " + Const.NOTE_LOGIN + "=?";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
            preparedStatement.setString(1, note.getLogin());
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resultSet;

    }

    public ResultSet getNoteData(String note){

        ResultSet resultSet = null;

        String select = "SELECT " + Const.NOTE_DATA + " FROM " + Const.NOTE_TABLE + " WHERE " + Const.NOTE_NAME + "=?";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
            preparedStatement.setString(1, note);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return resultSet;

    }

    public void addNote(Note note){

        String insert = "INSERT INTO " + Const.NOTE_TABLE +"(" + Const.NOTE_NAME + ", " + Const.NOTE_LOGIN + ")" + " VALUES (?, ?)";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
            preparedStatement.setString(1, note.getName());
            preparedStatement.setString(2, note.getLogin());
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void  deleteNote(String name){

        String drop = "DELETE FROM " + Const.NOTE_TABLE + " WHERE " + Const.NOTE_NAME + "=?";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(drop);
            preparedStatement.setString(1, name);
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void rewriteNote(String data, String name){

        String update = "UPDATE " + Const.NOTE_TABLE + " SET " + Const.NOTE_DATA + " = ? WHERE " + Const.NOTE_NAME + " = ?";

        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(update);
            preparedStatement.setString(1, data);
            preparedStatement.setString(2, name);
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}