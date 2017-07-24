package com.sanqing.entity;

import java.util.Date;

public class Complaint {
    private Integer id;

    private Integer aid;

    private String complaintMerchant;

    private Date time;

    private String title;

    private String complaintClasses;

    private String photo;

    private String description;

    private String linkman;

    private String contact;

    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getComplaintMerchant() {
        return complaintMerchant;
    }

    public void setComplaintMerchant(String complaintMerchant) {
        this.complaintMerchant = complaintMerchant;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComplaintClasses() {
        return complaintClasses;
    }

    public void setComplaintClasses(String complaintClasses) {
        this.complaintClasses = complaintClasses;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}