package com.example.sanket.hw6;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by sanket on 11/8/2017.
 */

public class UserCourseTable {
    static final String TABLENAME="UserCourseTable";
    static final String COLUMN_ID="_id";
    static final String User_Name ="fname";
    static final String Course_Id ="lname";


    static public void onCreate(SQLiteDatabase db){
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE "+TABLENAME+ " (");
        sb.append(COLUMN_ID+" integer primary key autoincrement, ");
        sb.append(User_Name+" text not null, ");
        sb.append(Course_Id+" integer);");

        try{

            db.execSQL(sb.toString());
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    static public void onUpgrade(SQLiteDatabase db,int oldVseion,int newVersio){
        db.execSQL("DROP TABLE IF EXIST" + TABLENAME);
        UserCourseTable.onCreate(db);

    }


}
