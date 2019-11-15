package com.qf.domain;

import java.util.Date;

public class Deduct {
    private Integer deductId;

    private Double propertyFee;

    private Double hotFee;

    private Date deductTime;

    private Date warnTime;

    public Deduct(Integer deductId, Double propertyFee, Double hotFee, Date deductTime, Date warnTime) {
        this.deductId = deductId;
        this.propertyFee = propertyFee;
        this.hotFee = hotFee;
        this.deductTime = deductTime;
        this.warnTime = warnTime;
    }

    public Deduct() {
        super();
    }

    public Integer getDeductId() {
        return deductId;
    }

    public void setDeductId(Integer deductId) {
        this.deductId = deductId;
    }

    public Double getPropertyFee() {
        return propertyFee;
    }

    public void setPropertyFee(Double propertyFee) {
        this.propertyFee = propertyFee;
    }

    public Double getHotFee() {
        return hotFee;
    }

    public void setHotFee(Double hotFee) {
        this.hotFee = hotFee;
    }

    public Date getDeductTime() {
        return deductTime;
    }

    public void setDeductTime(Date deductTime) {
        this.deductTime = deductTime;
    }

    public Date getWarnTime() {
        return warnTime;
    }

    public void setWarnTime(Date warnTime) {
        this.warnTime = warnTime;
    }
}