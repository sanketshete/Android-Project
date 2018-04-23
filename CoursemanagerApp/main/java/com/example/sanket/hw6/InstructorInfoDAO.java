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
 * Created by sanket on 11/7/2017.
 */

public class InstructorInfoDAO {
    private SQLiteDatabase db;
    public InstructorInfoDAO(SQLiteDatabase db){

        this.db=db;
    }

    public long saveinstructor(InstructorInfo instructorInfo)
    {
        byte[] data = getBitmapAsByteArray(instructorInfo.getImage());
        ContentValues values = new ContentValues();
        values.put(InstructorTable.FIRST_NAME,instructorInfo.getInstructor_fname());
        values.put(InstructorTable.LAST_NAME,instructorInfo.getInstructor_lname());
        values.put(InstructorTable.EMAIL,instructorInfo.getInstructor_email());
        values.put(InstructorTable.WEBSITE,instructorInfo.getInstructor_website());
        values.put(InstructorTable.INSTR_IMAGE,data);
        return db.insert(InstructorTable.TABLENAME,null,values);
    }

    public InstructorInfo getInstructor(Integer id){
        Registerinfo instructorInfo=null;
        Cursor c=db.rawQuery("SELECT * FROM "+InstructorTable.TABLENAME + " WHERE "+InstructorTable.COLUMN_ID+"="+id,null);
        if(c!=null && c.moveToFirst()){
            InstructorInfo instructorInfo1 =buildInstructorInfoFromCursor(c);
            if(!c.isClosed())
                c.close();
            return instructorInfo1;
        }
        return null;
    }

    public List<InstructorInfo> getAll(){
        List<InstructorInfo> instructorInfo1 = new ArrayList<InstructorInfo>();
        Cursor c=db.query(InstructorTable.TABLENAME,new String[]{InstructorTable.COLUMN_ID,
                InstructorTable.FIRST_NAME,InstructorTable.LAST_NAME,InstructorTable.EMAIL,InstructorTable.WEBSITE,InstructorTable.INSTR_IMAGE},null,null,null,null,null);
        if(c!=null && c.moveToFirst()){
            do{
                InstructorInfo instructorInfo =buildInstructorInfoFromCursor(c);
                if(instructorInfo!=null){
                    instructorInfo1.add(instructorInfo);
                }
            }while (c.moveToNext());
            if(!c.isClosed())
                c.close();

        }
        return instructorInfo1;
    }

    public boolean deleteInstructor(int id)
    {
        return db.delete(InstructorTable.TABLENAME,InstructorTable.COLUMN_ID +"=?",new String[]{String.valueOf(id)})>0;
    }

    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }

    // convert from byte array to bitmap
    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

    private InstructorInfo buildInstructorInfoFromCursor(Cursor c){
        InstructorInfo instructorInfo=null;
        if(c!=null){
            instructorInfo=new InstructorInfo();
            instructorInfo.setInstr_ID(c.getInt(0));
            instructorInfo.setInstructor_fname(c.getString(1));
            instructorInfo.setInstructor_lname(c.getString(2));
            instructorInfo.setInstructor_email(c.getString(3));
            instructorInfo.setInstructor_website(c.getString(4));
            instructorInfo.setImage(getImage(c.getBlob(5)));
        }
        return instructorInfo;
    }

}
