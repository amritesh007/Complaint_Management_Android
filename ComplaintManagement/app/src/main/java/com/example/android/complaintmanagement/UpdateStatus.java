package com.example.android.complaintmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UpdateStatus extends AppCompatActivity {

    Button logn;
    EditText uid, pass;
    String s1, s2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_status);

        logn = (Button)findViewById(R.id.lgin);
        uid =   (EditText)findViewById(R.id.et1);
        pass = (EditText)findViewById(R.id.et2);
        logn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                s1 = uid.getText().toString();
                s2 = pass.getText().toString();
                if(s1.equalsIgnoreCase("user") && s2.equals("4321")){
                    Intent myintent = new Intent(UpdateStatus.this, userlogin.class);
                    startActivity(myintent);
                }
                else {
                    Toast toast = Toast.makeText(getApplicationContext(),"Wrong User Id or Password", Toast.LENGTH_SHORT);
                    toast.show();
                }

            }
        });


    }
}
