package com.example.sanket.hw6;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sanket on 10/23/2017.
 */

public class DatabaseOpenHelper extends SQLiteOpenHelper{

    static final String DB_NAME="myuser.db";
    static final int DB_VERSION =2;

    public DatabaseOpenHelper(Context context) {
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        RegisterTable.onCreate(sqLiteDatabase);
        InstructorTable.onCreate(sqLiteDatabase);
        courseTable.onCreate(sqLiteDatabase);
        UserCourseTable.onCreate(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVesrion, int newVersion) {

        RegisterTable.onUpgrade(sqLiteDatabase,oldVesrion,newVersion);
    }


}
