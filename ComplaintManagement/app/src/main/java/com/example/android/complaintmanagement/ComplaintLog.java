package com.example.android.complaintmanagement;

public class ComplaintLog {
    String complaintid;
    String hostelname;
    String roomno;
    String complaintdivision;
    String describe;
    String stat;
    String cfeedback;


    public ComplaintLog() {

    }

    public ComplaintLog(String complaintid, String hostelname, String roomno, String complaintdivision, String describe, String stat, String cfeedback) {
        this.complaintid = complaintid;
        this.hostelname = hostelname;
        this.roomno = roomno;
        this.complaintdivision = complaintdivision;
        this.describe = describe;
        this.stat = stat;
        this.cfeedback= cfeedback;
    }

    public String getComplaintid() {
        return complaintid;
    }

    public String getHostelname() {
        return hostelname;
    }

    public String getRoomno() {
        return roomno;
    }

    public String getComplaintdivision() {
        return complaintdivision;
    }

    public String getDescribe() {
        return describe;
    }

    public String getStat() { return stat; }

    public String getCfeedback() {
        return cfeedback;
    }
}
