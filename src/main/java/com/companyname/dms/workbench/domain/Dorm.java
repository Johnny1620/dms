package com.companyname.dms.workbench.domain;

public class Dorm {

    private String dormId;
    private String type;
    private Integer residue;
    private String phone;
    private String buildName;



    @Override
    public String toString() {
        return "Dorm{" +
                "dormId='" + dormId + '\'' +
                ", type='" + type + '\'' +
                ", residue='" + residue + '\'' +
                ", phone='" + phone + '\'' +
                ", buildName='" + buildName + '\'' +
                '}';
    }

    public Dorm(String dormId, String type, Integer residue, String phone, String buildName) {
        this.dormId = dormId;
        this.type = type;
        this.residue = residue;
        this.phone = phone;
        this.buildName = buildName;
    }

    public Dorm() {
    }

    public String getDormId() {
        return dormId;
    }

    public void setDormId(String dormId) {
        this.dormId = dormId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getResidue() {
        return residue;
    }

    public void setResidue(Integer residue) {
        this.residue = residue;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBuildName() {
        return buildName;
    }

    public void setBuildName(String buildName) {
        this.buildName = buildName;
    }
}
