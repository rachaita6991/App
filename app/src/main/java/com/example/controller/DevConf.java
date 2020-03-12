package com.example.controller;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class DevConf extends AppCompatActivity {
    String cipherKey;
    String branchNumber;
    String accountNumber;
    String accountStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fingerprint);
        cipherKey = getIntent().getStringExtra("cipherKey");
        branchNumber = getIntent().getStringExtra("branchNumber");
        accountNumber = getIntent().getStringExtra("accountNumber");
        accountStatus = getIntent().getStringExtra("accountStatus");
        getSupportActionBar().setTitle("Device Validation");
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_launcher);
        TextView bankBranch = (TextView) findViewById(R.id.textView13);
        TextView bankAccount = (TextView) findViewById(R.id.textView17);
        TextView bankAccountStatus = (TextView) findViewById(R.id.textView18);
        TextView onlineOTP = (TextView) findViewById(R.id.textView20);
        bankBranch.setText(branchNumber);
        bankAccount.setText(accountNumber);
        bankAccountStatus.setText(accountStatus);
        onlineOTP.setText(cipherKey);
        ((TextView) findViewById(R.id.textView24)).setText(Build.BOARD);
        ((TextView) findViewById(R.id.textView25)).setText(Build.BRAND);
        ((TextView) findViewById(R.id.textView27)).setText(Build.DEVICE);
        ((TextView) findViewById(R.id.textView29)).setText(Build.FINGERPRINT);
        ((TextView) findViewById(R.id.textView31)).setText(Build.HARDWARE);
        ((TextView) findViewById(R.id.textView33)).setText(Build.HOST);
        ((TextView) findViewById(R.id.textView36)).setText(Build.ID);
        ((TextView) findViewById(R.id.textView38)).setText(Build.MANUFACTURER);
        ((TextView) findViewById(R.id.textView39)).setText(Build.MODEL);
        ((TextView) findViewById(R.id.textView42)).setText(Build.PRODUCT);
    }
    public void QRIntent(View v){
        Intent intent = new Intent(DevConf.this, offlinechallenge.class);
        startActivity(intent);
    }
}

