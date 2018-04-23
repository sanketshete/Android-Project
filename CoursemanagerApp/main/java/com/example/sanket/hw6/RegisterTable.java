package com.example.sanket.hw6;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by sanket on 10/23/2017.
 */

public class RegisterTable {
    static final String TABLENAME="registerTable";
    static final String FIRST_NAME ="fname";
    static final String LAST_NAME ="lname";
    static final String USER_NAME ="uname";
    static final String USER_Image ="uimage";
    static final String PASSWORD ="password";

    static public void onCreate(SQLiteDatabase db){
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE "+TABLENAME+ " (");
        sb.append(USER_NAME+" text primary key, ");
        sb.append(PASSWORD+" text not null, ");
        sb.append(FIRST_NAME+" text not null, ");
        sb.append(LAST_NAME+" text not null);");
        sb.append(USER_Image+" blob not null, ");
        try{
            db.execSQL(sb.toString());
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    static public void onUpgrade(SQLiteDatabase db,int oldVseion,int newVersio){
        db.execSQL("DROP TABLE IF EXIST" + TABLENAME);
        RegisterTable.onCreate(db);

    }
}
