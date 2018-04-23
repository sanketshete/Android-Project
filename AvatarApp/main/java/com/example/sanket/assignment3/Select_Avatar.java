package com.example.sanket.assignment3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Select_Avatar extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_avatar);
    }

    public void onClickofImage(View view){

        Intent intent = new Intent();
        int avatarId = 0 ;
        int viewId = view.getId() ;
        if(viewId == R.id.M1) {
            avatarId = R.drawable.avatar_m_1 ;
        }else if(viewId == R.id.M2){
            avatarId = R.drawable.avatar_m_2 ;
        }else if(viewId == R.id.M3){
            avatarId = R.drawable.avatar_m_3 ;
        }else if(viewId == R.id.W1){
            avatarId = R.drawable.avatar_f_1 ;
        }else if(viewId == R.id.W2){
            avatarId = R.drawable.avatar_f_2 ;
        }else if(viewId == R.id.W3){
            avatarId = R.drawable.avatar_f_3 ;
        }
        intent.putExtra(MainActivity.IMAGE,avatarId);
        setResult(RESULT_OK,intent);

        finish();
    }
}
