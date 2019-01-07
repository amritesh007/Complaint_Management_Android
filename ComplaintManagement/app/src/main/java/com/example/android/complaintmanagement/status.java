package com.example.android.complaintmanagement;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.ActionProvider;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class status extends AppCompatActivity  {
    String scdivide, shname, sstat, srn, sdes;
    String ohname, orn;
    TextView drn, dhname, dstatus, dctype, ddes;
    Button bhome;
    int c=1;
    String fdbck;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        drn = (TextView)findViewById(R.id.txtrn);
        dhname = (TextView)findViewById(R.id.txthn);
        dstatus = (TextView)findViewById(R.id.txtstatus);
        dctype=(TextView)findViewById(R.id.txtctype);
        ddes = (TextView)findViewById(R.id.txtdes);
        bhome = (Button)findViewById(R.id.btnhome);
        DatabaseReference mdatabase = FirebaseDatabase.getInstance().getReference();
        Bundle bundle = getIntent().getExtras();
        ohname = bundle.getString("hh");
        orn = bundle.getString("rr");
        Query query = mdatabase.child("Complaints");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot data: dataSnapshot.getChildren()) {
                    ComplaintLog cl = data.getValue(ComplaintLog.class);
                    shname = cl.getHostelname();
                    srn = cl.getRoomno();
                    fdbck=cl.getCfeedback();
                    if (shname.equals(ohname) && srn.equals(orn)) {
                        scdivide = cl.getComplaintdivision();
                        sstat = cl.getStat();
                        sdes = cl.getDescribe();
                        drn.setText(orn);
                        dhname.setText(ohname);
                        dstatus.setText(sstat);
                        dctype.setText(scdivide);
                        ddes.setText(sdes);
                        c=0;
                        if(sstat.equalsIgnoreCase("pending")){
                            bhome.setText("Home");
                            bhome.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent newintent = new Intent(status.this, MainActivity.class);
                                    startActivity(newintent);
                                }
                            });
                        }
                        else if (!TextUtils.isEmpty(fdbck)&& !sstat.equalsIgnoreCase("pending")){
                            bhome.setText("Home");
                            bhome.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent newintent = new Intent(status.this, MainActivity.class);
                                    startActivity(newintent);
                                }
                            });

                        }

                        else{
                            bhome.setText("Feedback");
                            bhome.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent tintent = new Intent(status.this, Feedback.class);
                                    Bundle bundle = new Bundle();
                                    bundle.putString("hn", ohname);
                                    bundle.putString("rn", orn);
                                    tintent.putExtras(bundle);
                                    startActivity(tintent);
                                }
                            });
                        }
                    }


                }
                if(c!=0){
                    drn.setText(orn);
                    dhname.setText(ohname);
                    dstatus.setText("Not Registered");
                    dctype.setText("Not Found");
                    ddes.setText("Not Found");
                    bhome.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent i = new Intent(status.this, MainActivity.class);
                            startActivity(i);
                        }
                    });

                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });







    }



}
