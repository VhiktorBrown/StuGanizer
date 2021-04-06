package com.chocolatedevelopers.stuganizer.lists;

public class ComputationModel {
    String courseIndex, creditLoad, grade;

    public ComputationModel(String courseIndex, String creditLoad, String grade) {
        this.courseIndex = courseIndex;
        this.creditLoad = creditLoad;
        this.grade = grade;
    }

    public ComputationModel() {
    }

    public void setCourseIndex(String courseIndex) {
        this.courseIndex = courseIndex;
    }

    public void setCreditLoad(String creditLoad) {
        this.creditLoad = creditLoad;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getCourseIndex() {
        return courseIndex;
    }

    public String getCreditLoad() {
        return creditLoad;
    }

    public String getGrade() {
        return grade;
    }
}
