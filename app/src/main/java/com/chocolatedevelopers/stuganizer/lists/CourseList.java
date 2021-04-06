package com.chocolatedevelopers.stuganizer.lists;

public class CourseList {
    int id;
    String courseName, creditLoad;

    public CourseList(String creditLoad) {
        this.creditLoad = creditLoad;
    }
    public CourseList(String courseName, String creditLoad) {
        this.courseName = courseName;
        this.creditLoad = creditLoad;
    }

    public CourseList(int id, String courseName, String creditLoad) {
        this.id = id;
        this.courseName = courseName;
        this.creditLoad = creditLoad;
    }

    public int getId() {
        return id;
    }
    public String getCourseName() {
        return courseName;
    }
    public String getCreditLoad() {
        return creditLoad;
    }
}
