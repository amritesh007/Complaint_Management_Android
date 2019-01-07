package com.example.android.complaintmanagement;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ComplaintStatus extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String hostel1, roomn;
    Spinner spin;
    EditText et;
    String fhname, frn;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint_status);
        spin = (Spinner)findViewById(R.id.spinn1);
        ArrayAdapter<CharSequence> spinadap = ArrayAdapter.createFromResource(this, R.array.Hostels, android.R.layout.simple_spinner_item);
        spinadap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(spinadap);
        spin.setOnItemSelectedListener(this);
        hostel1 = spin.getSelectedItem().toString();
        et =(EditText)findViewById(R.id.et1);
        roomn = et.getText().toString();
        b=(Button)findViewById(R.id.bttn);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                defineclick();
           }
        });



    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private  void defineclick(){
        String s1 = spin.getSelectedItem().toString();
        String s2 = et.getText().toString();
        if(TextUtils.isEmpty(s1) || TextUtils.isEmpty(s2)){
            Toast.makeText(this, "Fill all Field First", Toast.LENGTH_LONG).show();
        }
        else {
            Intent myint = new Intent(ComplaintStatus.this, status.class);
            Bundle bundle = new Bundle();
            bundle.putString("hh", s1);
            bundle.putString("rr", s2);
            myint.putExtras(bundle);
            startActivity(myint);
        }


    }
}
