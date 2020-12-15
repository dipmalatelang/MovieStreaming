package com.netflix.app.home.model;

import java.io.Serializable;

public class UserDetails implements Serializable {

    private String id, fullname, email, gmail, phone, time, imageURL, securityType, securityCode;

    public UserDetails(String ID, String FullName, String Email, String Gmail, String Phone, String Time, String imageURL, String securityType, String securityCode) {
        this.id = ID;
        this.fullname = FullName;
        this.email = Email;
        this.gmail = Gmail;
        this.phone = Phone;
        this.time = Time;
        this.imageURL = imageURL;
        this.securityType = securityType;
        this.securityCode = securityCode;

    }

    public String getSecurityType() {
        return securityType;
    }

    public void setSecurityType(String securityType) {
        this.securityType = securityType;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }


    public UserDetails() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }
}
