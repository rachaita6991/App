package com.example.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class offlinechallenge extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offlinechallenge);
        getSupportActionBar().setTitle("Offline Challenge");
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_launcher);
        IntentIntegrator integrator = new IntentIntegrator(this);
        integrator.initiateScan();
        integrator.addExtra("PROMPT_MESSAGE", "Please scan the QR Code provided by your bank");
    }
    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            // handle scan result
            ((TextView) findViewById(R.id.textView46)).setText(result.getContents());
        }

    }
    public void Bio(View v){
    }
}
