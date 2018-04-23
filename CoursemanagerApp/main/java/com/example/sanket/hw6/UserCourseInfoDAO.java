package com.example.sanket.hw6;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sanket on 11/8/2017.
 */

public class UserCourseInfoDAO {
    private SQLiteDatabase db;
    public UserCourseInfoDAO(SQLiteDatabase db){

        this.db=db;
    }

    public long saveuserCourseInfo(UserCourseInfo userCourseInfo)
    {
        ContentValues values = new ContentValues();
        values.put(UserCourseTable.User_Name,userCourseInfo.getUsername());
        values.put(UserCourseTable.Course_Id,userCourseInfo.getCourseId());
        return db.insert(UserCourseTable.TABLENAME,null,values);
    }

    public int getcourseId(String username){
        UserCourseInfo userCourseInfo=null;
        Cursor c=db.rawQuery("SELECT * FROM "+UserCourseTable.TABLENAME + " WHERE "+UserCourseTable.User_Name+"="+username,null);
        if(c!=null && c.moveToFirst()){
            UserCourseInfo userCourseInfo1 =buildUserCourseInfoFromCursor(c);
            if(!c.isClosed())
                c.close();
            return userCourseInfo1.getCourseId();
        }
        return -1;
    }

    private UserCourseInfo buildUserCourseInfoFromCursor(Cursor c){
        UserCourseInfo userCourseInfo=null;
        if(c!=null){
            userCourseInfo=new UserCourseInfo();
            userCourseInfo.setUserCourseID(c.getInt(0));
            userCourseInfo.setUsername(c.getString(1));
            userCourseInfo.setCourseId(c.getInt(2));
        }
        return userCourseInfo;
    }

}
