package com.example.sanket.assignment3;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final static String IMAGE ="Image",Display_Result_Key ="Display_Result";
    final static int REQ_CODE =100;
    public String department,mood;
    public int moodId = R.drawable.angry,avtarId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.avatar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Select_Avatar.class);
                startActivityForResult(intent,REQ_CODE);

            }
        });

      SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                ImageView avtarResult = (ImageView) findViewById(R.id.avtarResult);
                Drawable drawable = drawable = getResources().getDrawable(R.drawable.select_avatar) ; ;
               if(i == 0){
                    drawable = getResources().getDrawable(R.drawable.angry) ;
                     mood = "I am happy";
                   moodId = R.drawable.angry ;
               }else if(i==1){
                    drawable = getResources().getDrawable(R.drawable.sad) ;
                   mood = "I am Sad";
                   moodId = R.drawable.sad ;
               }else if(i==2){
                   drawable = getResources().getDrawable(R.drawable.happy) ;
                   mood = "I am happy";
                   moodId = R.drawable.happy ;
               }else if(i==3){
                   drawable = getResources().getDrawable(R.drawable.awesome) ;
                   mood = "I am Awesome";
                   moodId = R.drawable.awesome ;

               }
                avtarResult.setImageDrawable(drawable);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        ((RadioGroup)findViewById(R.id.rg)).setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                if(i == R.id.CS) {
                    department = "CS" ;
                }else if(i == R.id.SIS) {
                    department = "SIS" ;
                }else if(i == R.id.BIO) {
                    department = "BIO" ;
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == REQ_CODE){
            if(resultCode == RESULT_OK){
             int imageId = data.getExtras().getInt(IMAGE);
              ImageView iv =(ImageView) findViewById(R.id.avatar);
                Drawable drawable = getResources().getDrawable(imageId);
                iv.setImageDrawable(drawable);
                avtarId = imageId ;
            }
        }
    }


    public void onClickOFSubmit(View view){

        EditText name1 = (EditText)findViewById(R.id.Name);
        String nameVal = name1.getText().toString() ;
        if("".equalsIgnoreCase(nameVal.trim())) {
            Toast.makeText(this,"Name Cannot be empty",Toast.LENGTH_LONG).show() ;
        return ;
        }
        EditText email = (EditText)findViewById(R.id.Email);
        String emailVal = email.getText().toString() ;
        if(!Patterns.EMAIL_ADDRESS.matcher(emailVal.trim()).matches()) {
            Toast.makeText(this,"Invalid Email",Toast.LENGTH_LONG).show() ;
            return ;
        }


        Intent intent = new Intent(MainActivity.this,Display_Activity.class);
        FinalResult fr = new FinalResult(name1.getText().toString(),email.getText().toString(),department,mood,moodId,avtarId);
        intent.putExtra(Display_Result_Key,fr);
        startActivity(intent);
    }

}
