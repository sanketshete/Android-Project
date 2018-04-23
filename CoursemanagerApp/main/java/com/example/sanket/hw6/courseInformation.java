package com.example.sanket.hw6;

/**
 * Created by sanket on 11/7/2017.
 */

public class courseInformation {
    String Title,InstructorName,day,time, credit, semester;
    String amPm ;

    public String getAmPm() {
        return amPm;
    }

    public void setAmPm(String amPm) {
        this.amPm = amPm;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getInstructorName() {
        return InstructorName;
    }

    public void setInstructorName(String instructorName) {
        InstructorName = instructorName;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    @Override
    public String toString() {
        return "courseInformation{" +
                "Title='" + Title + '\'' +
                ", InstructorName='" + InstructorName + '\'' +
                ", day='" + day + '\'' +
                ", time='" + time + '\'' +
                ", credit='" + credit + '\'' +
                ", semester='" + semester + '\'' +
                ", amPm='" + amPm + '\'' +
                '}';
    }
}
