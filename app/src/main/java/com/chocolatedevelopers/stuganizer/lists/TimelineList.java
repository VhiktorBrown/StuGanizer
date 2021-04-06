package com.chocolatedevelopers.stuganizer.lists;

public class TimelineList {
    int id;
    String courseName, grade;

    public TimelineList(String grade) {
        this.grade = grade;
    }
    public TimelineList(String courseName, String grade) {
        this.courseName = courseName;
        this.grade = grade;
    }
    public TimelineList(int id, String courseName, String grade) {
        this.id = id;
        this.courseName = courseName;
        this.grade = grade;
    }

    public int getId() {
        return id;
    }
    public String getCourseName() {
        return courseName;
    }
    public String getGrade() {
        return grade;
    }
}
