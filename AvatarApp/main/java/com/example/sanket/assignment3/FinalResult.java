package com.example.sanket.assignment3;

import java.io.Serializable;

/**
 * Created by sanket on 9/11/2017.
 */

public class FinalResult implements Serializable{

    String name,email,department,mood;
    int moodId,avtarId;

    public  FinalResult(String name,String email,String department,String mood,int moodId,int avtarId){

        this.name = name;
        this.email = email;
        this.department = department;
        this.mood = mood;
        this.moodId = moodId;
        this.avtarId = avtarId;
    }
}
