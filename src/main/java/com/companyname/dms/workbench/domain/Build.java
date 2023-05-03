package com.companyname.dms.workbench.domain;

public class Build {

    private Long id; //自然主键
    private String name; //B1 B2 A1 A2
    private String info;
    private Long accountId; //管理员
    private String date;

    @Override
    public String toString() {
        return "build{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", info='" + info + '\'' +
                ", account_id='" + accountId + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public Build(Long id, String name, String info, Long accountId, String date) {
        this.id = id;
        this.name = name;
        this.info = info;
        this.accountId = accountId;
        this.date = date;
    }

    public Build() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Long getAccount_id() {
        return accountId;
    }

    public void setAccount_id(Long account_id) {
        this.accountId = account_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
