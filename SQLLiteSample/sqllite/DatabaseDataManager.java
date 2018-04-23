package com.example.sanket.sqllite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.security.PrivateKey;
import java.util.List;

/**
 * Created by sanket on 10/23/2017.
 */

public class DatabaseDataManager {

   private Context context;
    private DatabaseOpenHelper databaseOpenHelper;
    private SQLiteDatabase db;
    private NoteDAO noteDAO;

    public DatabaseDataManager(Context context){
        this.context=context;
        databaseOpenHelper = new DatabaseOpenHelper(this.context);
        db= databaseOpenHelper.getWritableDatabase();
        noteDAO=new NoteDAO(db);
    }

    public void close(){
        if(db!=null){
            db.close();
        }
    }
    public NoteDAO getNoteDAO(){

        return this.noteDAO;
    }
    public long saveNote(Note note){
        return this.noteDAO.save(note);
    }

    public boolean updateNote(Note note){
        return this.noteDAO.update(note);
    }

    public boolean DeleteNote(Note note){
        return this.noteDAO.delete(note);
    }

    public Note getNote(long id){
        return this.noteDAO.get(id);
    }
    public List<Note> getAllNote(){
        return this.noteDAO.getAll();
    }

}
