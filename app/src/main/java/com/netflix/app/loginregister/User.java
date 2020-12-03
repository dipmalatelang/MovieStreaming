package com.netflix.app.loginregister;

import java.io.Serializable;

public class User implements Serializable {

    private String id;
    private String username;

    public User(String uid, String username, String toLowerCase, String s, String s1, String s2) {
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

    private String email;
    private String gmail;
    private String name;
    private String phone;

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
}
