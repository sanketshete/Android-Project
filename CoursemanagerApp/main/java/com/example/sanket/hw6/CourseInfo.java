package com.example.sanket.hw6;

import android.graphics.Bitmap;

/**
 * Created by sanket on 11/8/2017.
 */

public class CourseInfo {
    String title,day,time,ampm,credithr,semister,instructorName;
    int Instructor_id,course_Id;
    private Bitmap instructorImage ;

    public Bitmap getInstructorImage() {
        return instructorImage;
    }

    public void setInstructorImage(Bitmap instructorImage) {
        this.instructorImage = instructorImage;
    }

    public String getInstructorName() {
        return instructorName;
    }

    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    public String getTitle() {
        return title;
    }

    public int getCourse_Id() {
        return course_Id;
    }

    public void setCourse_Id(int course_Id) {
        this.course_Id = course_Id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getInstructor_id() {
        return Instructor_id;
    }

    public void setInstructor_id(int instructor_id) {
        Instructor_id = instructor_id;
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

    public String getAmpm() {
        return ampm;
    }

    public void setAmpm(String ampm) {
        this.ampm = ampm;
    }

    public String getCredithr() {
        return credithr;
    }

    public void setCredithr(String credithr) {
        this.credithr = credithr;
    }

    public String getSemister() {
        return semister;
    }

    public void setSemister(String semister) {
        this.semister = semister;
    }

    @Override
    public String toString() {
        return "CourseInfo{" +
                "title='" + title + '\'' +
                ", day='" + day + '\'' +
                ", time='" + time + '\'' +
                ", ampm='" + ampm + '\'' +
                ", credithr='" + credithr + '\'' +
                ", semister='" + semister + '\'' +
                ", instructorName='" + instructorName + '\'' +
                ", Instructor_id=" + Instructor_id +
                ", course_Id=" + course_Id +
                '}';
    }
}
