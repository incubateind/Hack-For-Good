package com.snapsid.dotquestionmark.activity;

public class InstandMessage {

    private String message;
    private String author;
    private String address;
    private String phone;
    private String city;
    private String date;

    private String lat, lon;

    public String getDate() {
        return date;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getLat() {
        return lat;
    }

    public String getLon() {
        return lon;
    }

    public InstandMessage(String author, String phone, String address, String city, String message, String date, String lat, String lon ) {
        this.message = message;
        this.author = author;

        this.phone = phone;
        this.address = address;
        this.city=city;
        this.date=date;

        this.lat=lat;
        this.lon=lon;

    }

    public InstandMessage() {
    }

    public String getMessage() {
        return message;
    }

    public String getAuthor() {
        return author;
    }
}
