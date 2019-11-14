package com.qf.domain;

public class House {
    private Integer houseId;

    private Integer userId;

    private Integer build;

    private Double area;

    private String style;

    private Integer floor;

    private Integer room;

    private String toward;

    private Double hotFee;

    private Double propertyFee;

    public House(Integer houseId, Integer userId, Integer build, Double area, String style, Integer floor, Integer room, String toward, Double hotFee, Double propertyFee) {
        this.houseId = houseId;
        this.userId = userId;
        this.build = build;
        this.area = area;
        this.style = style;
        this.floor = floor;
        this.room = room;
        this.toward = toward;
        this.hotFee = hotFee;
        this.propertyFee = propertyFee;
    }

    public House() {
        super();
    }

    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBuild() {
        return build;
    }

    public void setBuild(Integer build) {
        this.build = build;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style == null ? null : style.trim();
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getRoom() {
        return room;
    }

    public void setRoom(Integer room) {
        this.room = room;
    }

    public String getToward() {
        return toward;
    }

    public void setToward(String toward) {
        this.toward = toward == null ? null : toward.trim();
    }

    public Double getHotFee() {
        return hotFee;
    }

    public void setHotFee(Double hotFee) {
        this.hotFee = hotFee;
    }

    public Double getPropertyFee() {
        return propertyFee;
    }

    public void setPropertyFee(Double propertyFee) {
        this.propertyFee = propertyFee;
    }
}