package com.example.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {
    private static int SPLASH_SCREEN_TIME_OUT=2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("IdentityShack - Splash Screen");
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_launcher);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent BankTxnid=new Intent(MainActivity.this,
                        BankTxnid.class);
                startActivity(BankTxnid);
                finish();
            }
        }, SPLASH_SCREEN_TIME_OUT);
    }
}
