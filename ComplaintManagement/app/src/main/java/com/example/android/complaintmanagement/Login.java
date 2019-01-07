package com.example.android.complaintmanagement;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Login extends AppCompatActivity {
    ListView listComplaints;
    DatabaseReference databaseComplaints;
    List<ComplaintLog> complaints;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        databaseComplaints= FirebaseDatabase.getInstance().getReference("Complaints");
        listComplaints = (ListView)findViewById(R.id.listComplaint);
        complaints = new ArrayList<>();

    }

    @Override
    protected void onStart() {
        super.onStart();
        databaseComplaints.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                complaints.clear();

                for(DataSnapshot complaintSnapshot: dataSnapshot.getChildren()){

                    ComplaintLog complaintlog= complaintSnapshot.getValue(ComplaintLog.class);
                    complaints.add(complaintlog);
                }
                ComplaintList adapter = new ComplaintList(Login.this, complaints);
                listComplaints.setAdapter(adapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



    }
}
