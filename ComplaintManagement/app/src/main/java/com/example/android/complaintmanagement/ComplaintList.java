package com.example.android.complaintmanagement;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class ComplaintList extends ArrayAdapter<ComplaintLog> {

    private Activity context;
    private List<ComplaintLog> complaintList;

    public ComplaintList(Activity context, List<ComplaintLog> complaintList){
        super(context, R.layout.list_layout, complaintList);
        this.context = context;
        this.complaintList= complaintList;
        }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View viewlistitem = inflater.inflate(R.layout.list_layout, null, true);
        TextView ctypetext = (TextView)viewlistitem.findViewById(R.id.ctype);
        TextView hnametext =(TextView)viewlistitem.findViewById(R.id.hname);
        TextView rnumbertext = (TextView)viewlistitem.findViewById(R.id.rnumber);
        TextView statusviewtext = (TextView)viewlistitem.findViewById(R.id.statusview);
        TextView feedbacktext = (TextView)viewlistitem.findViewById(R.id.feedbackview) ;

        ComplaintLog complaintlog = complaintList.get(position);
        ctypetext.setText(complaintlog.getComplaintdivision());
        hnametext.setText(complaintlog.getHostelname());
        rnumbertext.setText(complaintlog.getRoomno());
        statusviewtext.setText(complaintlog.getStat());
        feedbacktext.setText(complaintlog.getCfeedback());

        return viewlistitem;
    }
}
