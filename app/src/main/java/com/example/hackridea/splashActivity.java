package com.example.hackridea;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

public class splashActivity extends AppCompatActivity {

    SharedPreferences sp;
    String login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        sp = this.getSharedPreferences("com.example.hackridea",MODE_PRIVATE);

        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(sp.contains("login")){
                    Intent go = new Intent(splashActivity.this,MainActivity.class);
                    startActivity(go);
                    finish();
                }
                else{
                    Intent go = new Intent(splashActivity.this,loginActivity.class);
                    startActivity(go);
                    finish();
                }
            }
        },100);


    }
}
