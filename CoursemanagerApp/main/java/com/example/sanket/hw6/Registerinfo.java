package com.example.sanket.hw6;

import android.graphics.Bitmap;

/**
 * Created by sanket on 11/7/2017.
 */

public class Registerinfo {

    String First_name,Last_name,Username,Passowrd;
    Bitmap image;

    public Registerinfo(String first_name, String last_name, String username, String passowrd) {
        First_name = first_name;
        Last_name = last_name;
        Username = username;
        Passowrd = passowrd;

    }

    public Registerinfo(){


    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getFirst_name() {
        return First_name;
    }

    public void setFirst_name(String first_name) {
        First_name = first_name;
    }

    public String getLast_name() {
        return Last_name;
    }

    public void setLast_name(String last_name) {
        Last_name = last_name;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassowrd() {
        return Passowrd;
    }

    public void setPassowrd(String passowrd) {
        Passowrd = passowrd;
    }
}
