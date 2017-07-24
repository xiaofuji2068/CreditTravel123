package com.sanqing.entity;

import java.util.Date;

public class Subscribe {
    private Integer orderId;

    private Integer aid;

    private String status;

    private String linkman;

    private String phone;

    private Date subscribeTime;

    private Date resideTime;

    private Date departureTime;

    private String dinersNum;

    private String stayNum;

    private String services;

    private String signIn;

    private String remarks;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getSubscribeTime() {
        return subscribeTime;
    }

    public void setSubscribeTime(Date subscribeTime) {
        this.subscribeTime = subscribeTime;
    }

    public Date getResideTime() {
        return resideTime;
    }

    public void setResideTime(Date resideTime) {
        this.resideTime = resideTime;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public String getDinersNum() {
        return dinersNum;
    }

    public void setDinersNum(String dinersNum) {
        this.dinersNum = dinersNum;
    }

    public String getStayNum() {
        return stayNum;
    }

    public void setStayNum(String stayNum) {
        this.stayNum = stayNum;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public String getSignIn() {
        return signIn;
    }

    public void setSignIn(String signIn) {
        this.signIn = signIn;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}