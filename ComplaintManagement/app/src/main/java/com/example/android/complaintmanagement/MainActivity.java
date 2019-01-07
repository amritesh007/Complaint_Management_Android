package com.example.android.complaintmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button button4, button3;
    Button updates;
    Button officelogin;

    EditText et11, et12;
    String uid="";
    String pass="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);
        button4 = (Button)findViewById(R.id.b4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent=new Intent(MainActivity.this, ComplaintRegister.class);
                startActivity(myIntent);
            }
        });
        updates = (Button)findViewById(R.id.updates);
        updates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, UpdateStatus.class);
            }
        });
        et11 =(EditText)findViewById(R.id.et1);
        et12 = (EditText)findViewById(R.id.et2);
        button3=(Button)findViewById(R.id.b3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uid = et11.getText().toString();
                pass = et12.getText().toString();
                if(uid.equals("abcd") && pass.equals("1234")) {
                    Intent myIntent = new Intent(MainActivity.this, Login.class);
                    startActivity(myIntent);
                }
                else{
                   Toast toast = Toast.makeText(getApplicationContext(),"Wrong User Id or Password", Toast.LENGTH_SHORT);
                   toast.show();
                }
                }
            });

        officelogin = (Button)findViewById(R.id.updates);
        officelogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(MainActivity.this, UpdateStatus.class);
                startActivity(myintent);
            }
        });

        Button info = (Button)findViewById(R.id.info1);
        info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(MainActivity.this, infoactiv.class);
                startActivity(myintent);
            }
        });



    }
}
