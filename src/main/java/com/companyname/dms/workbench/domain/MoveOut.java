package com.companyname.dms.workbench.domain;

public class MoveOut {

    private Long id;
    private Long stuId;
    private String moveOutDate;



    @Override
    public String toString() {
        return "MoveOut{" +
                "id=" + id +
                ", stuId=" + stuId +
                ", moveOutDate='" + moveOutDate + '\'' +
                '}';
    }

    public MoveOut(Long id, Long stuId, String moveOutDate) {
        this.id = id;
        this.stuId = stuId;
        this.moveOutDate = moveOutDate;
    }

    public MoveOut() {
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

    public String getMoveOutDate() {
        return moveOutDate;
    }

    public void setMoveOutDate(String moveOutDate) {
        this.moveOutDate = moveOutDate;
    }
}
