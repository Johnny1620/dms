package com.companyname.dms.workbench.domain;

public class MoveInto {

    private Long id;
    private Long stuId;
    private String moveIntDate;

    @Override
    public String toString() {
        return "MoveInto{" +
                "id=" + id +
                ", stu_id=" + stuId +
                ", moveIntDate='" + moveIntDate + '\'' +
                '}';
    }

    public MoveInto(Long id, Long stuId, String moveIntDate) {
        this.id = id;
        this.stuId = stuId;
        this.moveIntDate = moveIntDate;
    }

    public MoveInto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStuId() {
        return stuId;
    }

    public void setStuId(Long stuId) {
        this.stuId = stuId;
    }

    public String getMoveIntDate() {
        return moveIntDate;
    }

    public void setMoveIntDate(String moveIntDate) {
        this.moveIntDate = moveIntDate;
    }
}
