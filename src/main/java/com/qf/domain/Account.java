package com.qf.domain;

public class Account {
    private Integer accountId;

    private Integer userId;

    private Double money;

    private Double parkFee;

    public Account(Integer accountId, Integer userId, Double money, Double parkFee) {
        this.accountId = accountId;
        this.userId = userId;
        this.money = money;
        this.parkFee = parkFee;
    }

    public Account() {
        super();
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Double getParkFee() {
        return parkFee;
    }

    public void setParkFee(Double parkFee) {
        this.parkFee = parkFee;
    }
}