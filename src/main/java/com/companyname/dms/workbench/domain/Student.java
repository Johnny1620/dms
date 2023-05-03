package com.companyname.dms.workbench.domain;

public class Student {
    private Long id;
    private String name;
    private String gender;
    private Integer age;
    private String grade;
    private String major;
    private String createDate;
    private String state;
    private String dormId;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", grade='" + grade + '\'' +
                ", major='" + major + '\'' +
                ", createDate='" + createDate + '\'' +
                ", state='" + state + '\'' +
                ", dormId='" + dormId + '\'' +
                '}';
    }

    public Student(Long id, String name, String gender, Integer age, String grade, String major, String createDate, String state, String dormId) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.grade = grade;
        this.major = major;
        this.createDate = createDate;
        this.state = state;
        this.dormId = dormId;
    }

    public Student() {
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDormId() {
        return dormId;
    }

    public void setDormId(String dormId) {
        this.dormId = dormId;
    }

    ;
}
