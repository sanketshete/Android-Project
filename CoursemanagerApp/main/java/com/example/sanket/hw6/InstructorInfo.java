package com.example.sanket.hw6;

import android.graphics.Bitmap;

/**
 * Created by sanket on 11/7/2017.
 */

public class InstructorInfo {

    String Instructor_fname,Instructor_lname,Instructor_email,Instructor_website;
    Bitmap image;
    int instr_ID;

    public int getInstr_ID() {
        return instr_ID;
    }

    public void setInstr_ID(int instr_ID) {
        this.instr_ID = instr_ID;
    }

    boolean isChecked = false ;

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

    //hii
    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getInstructor_fname() {
        return Instructor_fname;
    }

    public void setInstructor_fname(String instructor_fname) {
        Instructor_fname = instructor_fname;
    }

    public String getInstructor_lname() {
        return Instructor_lname;
    }

    public void setInstructor_lname(String instructor_lname) {
        Instructor_lname = instructor_lname;
    }

    public String getInstructor_email() {
        return Instructor_email;
    }

    public void setInstructor_email(String instructor_email) {
        Instructor_email = instructor_email;
    }

    public String getInstructor_website() {
        return Instructor_website;
    }

    public void setInstructor_website(String instructor_website) {
        Instructor_website = instructor_website;
    }

    @Override
    public String toString() {
        return "InstructorInfo{" +
                "Instructor_fname='" + Instructor_fname + '\'' +
                ", Instructor_lname='" + Instructor_lname + '\'' +
                ", Instructor_email='" + Instructor_email + '\'' +
                ", Instructor_website='" + Instructor_website + '\'' +
                ", image=" + image +
                ", isChecked=" + isChecked +
                '}';
    }
}
