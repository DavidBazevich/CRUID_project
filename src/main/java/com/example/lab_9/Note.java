package com.example.lab_9;

public class Note {

    private String name;
    private String data;
    private String login;

    public Note(String name, String data, String login) {
        this.name = name;
        this.data = data;
        this.login = login;
    }

    public Note(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

}