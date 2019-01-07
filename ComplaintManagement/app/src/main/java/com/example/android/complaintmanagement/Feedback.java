package com.example.android.complaintmanagement;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class Feedback extends AppCompatActivity {
    EditText fdbcktxt;
    Button sbmtbtn;
    String fdbck;
    String hosteln, roomn;
    String dfid, dfdbck;
    String dhosteln, droomn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        fdbcktxt= (EditText)findViewById(R.id.fdbck);
        fdbck=fdbcktxt.getText().toString();
        sbmtbtn=(Button)findViewById(R.id.submit);
        Bundle b = getIntent().getExtras();
        hosteln = b.getString("hn");
        roomn = b .getString("rn");
        final FirebaseDatabase mdatabase1= FirebaseDatabase.getInstance();
        final DatabaseReference dref = mdatabase1.getReference();
        sbmtbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(fdbcktxt.getText().toString())){
                    emptytst();
                }
                else {
                    Query query = dref.child("Complaints");
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot data: dataSnapshot.getChildren()){
                                ComplaintLog cl=data.getValue(ComplaintLog.class);
                                dhosteln = cl.getHostelname();
                                droomn = cl.getRoomno();
                                if(dhosteln.equals(hosteln) && droomn.equals(roomn)){
                                    dfid = cl.getComplaintid();
                                    dfdbck = cl.getCfeedback();
                                    Map<String, Object> updates = new HashMap<String,Object>();
                                    updates.put("cfeedback", fdbcktxt.getText().toString());
                                    dref.child("Complaints").child(dfid).updateChildren(updates);
                                    sbmtfdbcktst();
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }
        });
    }

    public void emptytst(){
        Toast.makeText(this, "Feedback cannot be Empty!", Toast.LENGTH_LONG).show();
    }



    private  void sbmtfdbcktst(){

        Toast.makeText(this, "Thank You for Your Feedback", Toast.LENGTH_LONG).show();
        Intent it = new Intent(Feedback.this, MainActivity.class);
        startActivity(it);

    }
    private void sbmthome(){
        Toast.makeText(this, "Feedback alreay Submitted", Toast.LENGTH_LONG).show();
        sbmtbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inte = new Intent(Feedback.this, MainActivity.class);
                startActivity(inte);
            }
        });

    }
}
