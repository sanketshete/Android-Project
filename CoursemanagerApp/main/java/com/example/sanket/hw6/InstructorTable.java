package com.example.sanket.hw6;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by sanket on 11/7/2017.
 */

public class InstructorTable {

    static final String TABLENAME="instructorTable";
    static final String COLUMN_ID="_id";
    static final String FIRST_NAME ="fname";
    static final String LAST_NAME ="lname";
    static final String EMAIL ="email";
    static final String INSTR_IMAGE ="instructorImage";
    static final String WEBSITE ="website";

    static public void onCreate(SQLiteDatabase db){
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE "+TABLENAME+ " (");
        sb.append(COLUMN_ID+" integer primary key autoincrement, ");
        sb.append(FIRST_NAME+" text not null, ");
        sb.append(LAST_NAME+" text not null, ");
        sb.append(EMAIL+" text not null, ");
        sb.append(WEBSITE+" text not null, ");
        sb.append(INSTR_IMAGE+" blob not null); ");

        try{

            db.execSQL(sb.toString());
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    static public void onUpgrade(SQLiteDatabase db,int oldVseion,int newVersio){
        db.execSQL("DROP TABLE IF EXIST" + TABLENAME);
        InstructorTable.onCreate(db);

    }
}
