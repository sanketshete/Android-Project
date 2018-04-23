package com.example.sanket.sqllite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DatabaseDataManager databaseDataManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseDataManager = new DatabaseDataManager(this);
        databaseDataManager.saveNote(new Note("note1","note1 text"));
        databaseDataManager.saveNote(new Note("note2","note2 text"));
        databaseDataManager.saveNote(new Note("note3","note3 text"));
        List<Note> notes = databaseDataManager.getAllNote();
        Log.d("demo",notes.toString());
    }

    @Override
    protected void onDestroy() {
        databaseDataManager.close();
        super.onDestroy();

    }
}
