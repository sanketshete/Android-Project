package com.example.sanket.hw6;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by sanket on 11/8/2017.
 */

public class courseTable {
    static final String TABLENAME="courseTable";
    static final String COLUMN_ID ="_id";
    static final String TITLE ="title";
    static final String INSTR_ID ="InstrId";
    static final String DAY ="day";
    static final String TIME ="time";
    static final String AMPM ="ampm";
    static final String CREDIT_HR ="credithr";
    static final String SEMESTER ="semester";

    static public void onCreate(SQLiteDatabase db){
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE "+TABLENAME+ " (");
        sb.append(COLUMN_ID+" integer primary key autoincrement, ");
        sb.append(TITLE+" text not null, ");
        sb.append(INSTR_ID+" integer, ");
        sb.append(DAY+" text not null, ");
        sb.append(TIME+" text not null, ");
        sb.append(AMPM+" text not null, ");
        sb.append(CREDIT_HR+" text not null, ");
        sb.append(SEMESTER+" text not null ); ");

        try{
            db.execSQL(sb.toString());
        }catch (SQLException ex){
            ex.printStackTrace();
        }
    }
    static public void onUpgrade(SQLiteDatabase db,int oldVseion,int newVersio){
        db.execSQL("DROP TABLE IF EXIST" + TABLENAME);
        courseTable.onCreate(db);

    }

}
