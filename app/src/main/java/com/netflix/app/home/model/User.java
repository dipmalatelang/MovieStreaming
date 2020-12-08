package com.netflix.app.home.model;



import java.io.Serializable;


public class User implements Serializable {


    public String getExpdate() {
        return expdate;
    }

    public void setExpdate(String expdate) {
        this.expdate = expdate;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getProfileUri() {
        return profileUri;
    }

    public void setProfileUri(String profileUri) {
        this.profileUri = profileUri;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getSuscriptionDate() {
        return suscriptionDate;
    }

    public void setSuscriptionDate(String suscriptionDate) {
        this.suscriptionDate = suscriptionDate;
    }

    public UserPayment getPayments() {
        return payments;
    }

    public void setPayments(UserPayment payments) {
        this.payments = payments;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobilecode() {
        return mobilecode;
    }

    public void setMobilecode(String mobilecode) {
        this.mobilecode = mobilecode;
    }

    private  String expdate;
    private  String password;
    private String profileUri;
    private  String createdAt;
    private String updatedAt;
    private String suscriptionDate;
    private UserPayment payments;
    private String userType;


    private String id;
    private String username;
    private String email;
    private String gmail;
    private String name;
    private String phone;

    public User(String uid, String username, String toLowerCase, String s, String s1, String s2) {
    }





    public User(String id, String username, String email, String gmail, String name, String phone, String mobilecode) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.gmail = gmail;
        this.name = name;
        this.phone = phone;
        this.mobilecode = mobilecode;
    }

    private String mobilecode;

    public User(){

    }


    public User(String id, String name, String email, String password, String phone, String profileUri, String createdAt, String updatedAt, String suscriptionDate, UserPayment userPayments, String userType, String expdate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.profileUri = profileUri;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.suscriptionDate = suscriptionDate;
        this.payments = userPayments;
        this.userType = userType;
        this.expdate = expdate;
    }

    public String toString() {
        return "User{id='" + this.id + '\'' + ", name='" + this.name + '\'' + ", email='" + this.email + '\'' + ", phone='" + this.phone + '\'' + ", profileUri='" + this.profileUri + '\'' + ", createdAt='" + this.createdAt + '\'' + ", updatedAt='" + this.updatedAt + '\'' + ", suscriptionDate='" + this.suscriptionDate + '\'' + ", userPayments=" + this.payments + ", userType='" + this.userType + '\'' + ", expdate='" + this.expdate + '\'' + '}';
    }




}
