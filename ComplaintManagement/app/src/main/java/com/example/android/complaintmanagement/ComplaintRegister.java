package com.example.android.complaintmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ComplaintRegister extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String hostel;
    Button bstatus;
    Button scomplaint;
    String room, complaint, descrip;
    Spinner spinner, spinner3;
    EditText et13, et15;
    String abcd = "";

    DatabaseReference databaseComplaints;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.complaintregister);
        databaseComplaints= FirebaseDatabase.getInstance().getReference("Complaints");

        spinner = findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Hostels, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        hostel =  spinner.getSelectedItem().toString();
        et13 = (EditText)findViewById(R.id.rn);
        room = et13.getText().toString();
        et15 = (EditText)findViewById(R.id.description);
        descrip = et15.getText().toString();
        bstatus=(Button)findViewById(R.id.status);
        scomplaint = (Button)findViewById(R.id.sub);
        spinner3=findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter1=ArrayAdapter.createFromResource(this, R.array.complainttype, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner3.setAdapter(adapter1);
        spinner3.setOnItemSelectedListener(this);
        complaint=spinner3.getSelectedItem().toString();
        scomplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addcomplaint();

            }
        });
        bstatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sintent =  new Intent(ComplaintRegister.this, ComplaintStatus.class);
                startActivity(sintent);
            }
        });
        }

        private void addcomplaint(){
        String hname = spinner.getSelectedItem().toString();
        String rno= et13.getText().toString();
        String ctype=spinner3.getSelectedItem().toString();
        String des = et15.getText().toString();
        String state = "Pending";
        if(TextUtils.isEmpty(hname) || TextUtils.isEmpty(rno) || TextUtils.isEmpty(ctype) || TextUtils.isEmpty(des)){
            Toast.makeText(this, "FILL ALL FIELD FIRST", Toast.LENGTH_LONG).show();

        }
        else{
            String id = databaseComplaints.push().getKey();
            ComplaintLog complaintlog= new ComplaintLog(id, hname, rno, ctype, des, state, abcd);
            databaseComplaints.child(id).setValue(complaintlog);
            Toast.makeText(this, "Complaint Added", Toast.LENGTH_LONG).show();
            Intent inten= new Intent(ComplaintRegister.this, FinalComplaint.class);
            startActivity(inten);

        }
        }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}