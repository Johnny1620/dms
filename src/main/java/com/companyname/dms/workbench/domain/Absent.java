package com.companyname.dms.workbench.domain;

public class Absent {
    private String stuName;
    private String grade;
    private String reason;
    private String date;

    @Override
    public String toString() {
        return "Absent{" +
                "stuName='" + stuName + '\'' +
                ", grade='" + grade + '\'' +
                ", reason='" + reason + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public Absent(String stuName, String grade, String reason, String date) {
        this.stuName = stuName;
        this.grade = grade;
        this.reason = reason;
        this.date = date;
    }

    public Absent() {
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
