package com.chocolatedevelopers.stuganizer.lists;

public class TimeTableDetails {
    String lecturerName, courseName, startTime, endTime, venueName;
    int id;

    public TimeTableDetails(String lecturerName, String courseName, String startTime, String endTIme, String venueName) {
        this.lecturerName = lecturerName;
        this.courseName = courseName;
        this.startTime = startTime;
        this.endTime = endTIme;
        this.venueName = venueName;
    }

    public TimeTableDetails(int id, String lecturerName, String courseName, String startTime, String endTIme, String venueName) {
        this.id = id;
        this.lecturerName = lecturerName;
        this.courseName = courseName;
        this.startTime = startTime;
        this.endTime = endTIme;
        this.venueName = venueName;
    }

    public int getId() {
        return id;
    }
    public String getLecturerName() {
        return  lecturerName;
    }
    public  String getCourseName() {
        return courseName;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getVenueName() {
        return venueName;
    }
}
