package com.example.controller;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;


public class BankTxnid extends AppCompatActivity {
                    final String BASE_URL = "http://192.168.1.25:8081/registration/accountValidation";

           @Override
           protected void onCreate(Bundle savedInstanceState) {
               super.onCreate(savedInstanceState);
               setContentView(R.layout.activity_bank_txnid);
               getSupportActionBar().setTitle("Bank Account Validation");
               getSupportActionBar().setDisplayUseLogoEnabled(true);
               getSupportActionBar().setDisplayShowHomeEnabled(true);
               getSupportActionBar().setIcon(R.drawable.ic_launcher);
                 //    getActionBar().setDisplayUseLogoEnabled(true);
                //    getActionBar().setTitle("My new title");
               //    getActionBar().setIcon(R.drawable.ic_launcher);

           }
           public void sendMessage(View v){
               final TextView textView = (TextView) findViewById(R.id.editText3);

               RequestQueue queue = Volley.newRequestQueue(this);
               StringRequest stringRequest = new StringRequest(Request.Method.POST, BASE_URL,
                       new Response.Listener<String>() {
                           @Override
                           public void onResponse(String response) {
                               EditText branchName = (EditText) findViewById(R.id.editText);
                               EditText branchNumber   = (EditText)findViewById(R.id.editText2);
                               EditText accountNumber   = (EditText)findViewById(R.id.editText3);
                    //           Log.d("Response", response);
                               Intent intent = new Intent(BankTxnid.this, DevConf.class);
                               intent.putExtra("branchNumber", branchNumber.getText().toString());
                               intent.putExtra("accountNumber", accountNumber.getText().toString());
                               intent.putExtra("cipherKey", StringUtils.substringBetween(response, "<cipherKey>", "</cipherKey>"));
                               intent.putExtra("accountStatus",StringUtils.substringBetween(response, "<accountValidationMessage>", "</accountValidationMessage>"));
                               startActivity(intent);
              //                 System.out.println(StringUtils.substringBetween(response, "<cipherKey>", "</cipherKey>"));
                           }
                       }, new Response.ErrorListener() {
                   @Override
                   public void onErrorResponse(VolleyError error) {
                       textView.setText("That didn't work!");
                   }
           })
               {
                   @Override
                   public String getBodyContentType()
                   {
                       return "application/xml";
                   }
                   @Override
                   public byte[] getBody() throws AuthFailureError {
                       EditText branchName = (EditText) findViewById(R.id.editText);
                       EditText branchNumber   = (EditText)findViewById(R.id.editText2);
                       EditText accountNumber   = (EditText)findViewById(R.id.editText3);
                       String postData = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                               "<AccountValidationRequest>\n" +
                               "\t<bankName>MockBank</bankName>\n" +
                               "\t<branchName>"+branchName.getText().toString()+"</branchName>\n" +
                               "\t<branchNumber>"+branchNumber.getText().toString()+"</branchNumber>\n" +
                               "\t<accountNumber>"+accountNumber.getText().toString()+"</accountNumber>\n" +
                               "\t<IP>"+getLocalIpAddress()+"</IP>\n" +
                               "\t<serialNumber>"+android.os.Build.MODEL+"</serialNumber>\n" +
                               "</AccountValidationRequest>"; // TODO get your final output
                       try {
                           return postData == null ? null :
                                   postData.getBytes(getParamsEncoding());
                       } catch (UnsupportedEncodingException uee) {
                           // TODO consider if some other action should be taken
                           return null;
                       }
                   }
                   public String getLocalIpAddress(){
                       try {
                           for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
                                en.hasMoreElements();) {
                               NetworkInterface intf = en.nextElement();
                               for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                                   InetAddress inetAddress = enumIpAddr.nextElement();
                                   if (!inetAddress.isLoopbackAddress()) {
                                       return inetAddress.getHostAddress();
                                   }
                               }
                           }
                       } catch (Exception ex) {
                           Log.e("IP Address", ex.toString());
                       }
                       return null;
                   }
               };
               queue.add(stringRequest);
       }
}