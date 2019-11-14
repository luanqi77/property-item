package com.qf.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "apply")
public class Apply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer applyId;

    private Integer userId;

    @Column(name = "apply_deso")
    private String applyDeso;

    @Column(name = "apply_time")
    private Date applyTime;

    private Integer status;

    @Column(name = "staff_name")
    private String staffName;

    public Apply(Integer applyId, Integer userId, String applyDeso, Date applyTime, Integer status, String staffName) {
        this.applyId = applyId;
        this.userId = userId;
        this.applyDeso = applyDeso;
        this.applyTime = applyTime;
        this.status = status;
        this.staffName = staffName;
    }

    public Apply() {
        super();
    }

    public Integer getApplyId() {
        return applyId;
    }

    public void setApplyId(Integer applyId) {
        this.applyId = applyId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getApplyDeso() {
        return applyDeso;
    }

    public void setApplyDeso(String applyDeso) {
        this.applyDeso = applyDeso == null ? null : applyDeso.trim();
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName == null ? null : staffName.trim();
    }
}