package com.example.sanket.assignment3;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Display_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_);

       if(getIntent().getExtras() !=null){

           FinalResult fr = (FinalResult) getIntent().getExtras().getSerializable(MainActivity.Display_Result_Key);

           ImageView iv = (ImageView)findViewById(R.id.imageView) ;
           iv.setImageDrawable(getResources().getDrawable(fr.avtarId));
           TextView tv = (TextView)findViewById(R.id.NameValue) ;
           tv.setText(fr.name);
           TextView deptTv = (TextView)findViewById(R.id.Department_value) ;
           deptTv.setText(fr.department);

           TextView emailTv = (TextView)findViewById(R.id.Email_Value) ;
           emailTv.setText(fr.email);

           TextView moodTv = (TextView)findViewById(R.id.moodStatus) ;
           moodTv.setText(fr.mood);

           ImageView moodiv = (ImageView)findViewById(R.id.moodIcon) ;
           moodiv.setImageDrawable(getResources().getDrawable(fr.moodId));

       }

    }
}
