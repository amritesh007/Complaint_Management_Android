package com.example.android.complaintmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class userlogin extends AppCompatActivity {
    ImageButton imgbtn1, imgbtn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userlogin);
        imgbtn1 =(ImageButton)findViewById(R.id.img1);
        imgbtn2 = (ImageButton)findViewById(R.id.img2);
        imgbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(userlogin.this, statusupdate.class);
                startActivity(myintent);
            }
        });

        imgbtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(userlogin.this, Login.class);
                startActivity(myintent);
            }
        });

    }
}
