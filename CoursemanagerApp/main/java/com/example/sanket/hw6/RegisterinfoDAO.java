package com.example.sanket.hw6;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sanket on 10/23/2017.
 */

public class RegisterinfoDAO {
private SQLiteDatabase db;
    public RegisterinfoDAO(SQLiteDatabase db){
        this.db=db;
    }

    public long save(Registerinfo registerinfo)
    {
        byte[] data = getBitmapAsByteArray(registerinfo.getImage());
        ContentValues values = new ContentValues();
        values.put(RegisterTable.USER_NAME,registerinfo.getUsername());
        values.put(RegisterTable.PASSWORD,registerinfo.getPassowrd());
        values.put(RegisterTable.FIRST_NAME,registerinfo.getFirst_name());
        values.put(RegisterTable.LAST_NAME,registerinfo.getLast_name());
        values.put(RegisterTable.USER_Image,data);

        return db.insert(RegisterTable.TABLENAME,null,values);
    }

    public boolean get(String uname, String password){

        Registerinfo registerinfo=null;
        Cursor c=db.rawQuery("SELECT * FROM "+RegisterTable.TABLENAME + " WHERE "+RegisterTable.USER_NAME+" =? " +" & "+ RegisterTable.PASSWORD +" =?",new String[]{uname,password});
        if(c!=null && c.moveToFirst()){
            if(!c.isClosed())
            c.close();
            return true;
        }
return false;
    }

    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }

}
