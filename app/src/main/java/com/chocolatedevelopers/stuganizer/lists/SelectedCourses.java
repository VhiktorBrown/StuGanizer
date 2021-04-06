package com.chocolatedevelopers.stuganizer.lists;

public class SelectedCourses {
    String courseTitle;
    public SelectedCourses(String courseName) {
        this.courseTitle = courseName;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

}
