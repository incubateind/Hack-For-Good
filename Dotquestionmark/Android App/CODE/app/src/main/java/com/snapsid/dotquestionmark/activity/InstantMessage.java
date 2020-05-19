package com.snapsid.dotquestionmark.activity;

public class InstantMessage {

    private String message;
    private String email;
    private String city;

    public InstantMessage(String city, String message, String email) {
        this.message = message;
        this.email = email;
        this.city = city;
    }

    public InstantMessage() {
    }

    public String getCity() {
        return city;
    }
    public String getEmail() {
        return email;
    }
    public String getName() {
        return message;
    }




}
