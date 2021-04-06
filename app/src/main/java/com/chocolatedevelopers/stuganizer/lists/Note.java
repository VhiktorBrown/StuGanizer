package com.chocolatedevelopers.stuganizer.lists;

public class Note {
    long id;
    String title, details, date, time;

    public Note(){}
    public Note(String title, String details, String date, String time) {
        this.title = title;
        this.details = details;
        this.date = date;
        this.time = time;
    }

    public Note(long id, String title, String details, String date, String time) {
        this.id = id;
        this.title = title;
        this.details = details;
        this.date = date;
        this.time = time;
    }

    public void setId(long id) {
        this.id = id;
    }
    public long getId() {
        return id;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getDetails() {
        return details;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }
}
