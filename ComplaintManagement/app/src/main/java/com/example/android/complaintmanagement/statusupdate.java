package com.example.android.complaintmanagement;

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

import java.util.HashMap;
import java.util.Map;

public class statusupdate extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Button updatebtn;

    String dhname, droomn, dfid;

    int c=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final EditText e1, e2;
         final String hosteln, roomn, statusn;
        final Spinner spin;
         final FirebaseDatabase mdatabase1= FirebaseDatabase.getInstance();
         final DatabaseReference dref = mdatabase1.getReference();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statusupdate);
        spin = (Spinner)findViewById(R.id.spinn1);
        ArrayAdapter<CharSequence> spinadap = ArrayAdapter.createFromResource(this, R.array.Hostels, android.R.layout.simple_spinner_item);
        spinadap.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(spinadap);
        spin.setOnItemSelectedListener(this);
        updatebtn =(Button)findViewById(R.id.bttn);
        e1=(EditText)findViewById(R.id.roomnumber);
        e2=(EditText)findViewById(R.id.newstatus);
        hosteln = spin.getSelectedItem().toString();
        roomn = e1.getText().toString();
        statusn=e2.getText().toString();





        updatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(e1.getText().toString()) || TextUtils.isEmpty(spin.getSelectedItem().toString()) || TextUtils.isEmpty(e2.getText().toString())){
                    showToast();
                }
                else{
                    Query query = dref.child("Complaints");
                    query.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            for (DataSnapshot data: dataSnapshot.getChildren()){
                                ComplaintLog cl= data.getValue(ComplaintLog.class);
                                dhname = cl.getHostelname();
                                droomn=cl.getRoomno();
                                dfid =cl.getComplaintid();
                                if(dhname.equals(spin.getSelectedItem().toString()) && droomn.equals(e1.getText().toString())){
                                    //dref.child("Compliants").child(dfid);
                                    Map<String, Object> updates = new HashMap<String,Object>();
                                    updates.put("stat", e2.getText().toString());
                                    dref.child("Complaints").child(dfid).updateChildren(updates);
                                    c=1;

                                }

                            }
                            if(c==1){
                                success();
                            }
                            else{
                                notsuccess();
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

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    public  void showToast(){
        Toast.makeText(this, "Fill All Field First", Toast.LENGTH_LONG).show();
    }

    public void success(){
        Toast.makeText(this, "Status Updated Successfully", Toast.LENGTH_LONG).show();
    }

    public void notsuccess(){
        Toast.makeText(this, "Status Update failed", Toast.LENGTH_LONG).show();
    }
    public void hell(String a){
        Toast.makeText(this, a, Toast.LENGTH_LONG).show();

    }
}
