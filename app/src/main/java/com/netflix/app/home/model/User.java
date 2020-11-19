package com.netflix.app.home.model;

import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("createdAt")
    private String createdAt;
    @SerializedName("email")
    private String email;
    @SerializedName("id")

    /* renamed from: id */
    private String f4402id;
    @SerializedName("name")
    private String name;
    @SerializedName("password")
    private String password;
    @SerializedName("payments")
    private UserPayment payments;
    @SerializedName("phone")
    private String phone;
    @SerializedName("profileUri")
    private String profileUri;
    @SerializedName("suscriptionDate")
    private String suscriptionDate;
    @SerializedName("updatedAt")
    private String updatedAt;
    @SerializedName("userType")
    private String userType;

    public User(String string) {
    }

    public User(String id, String name2, String email2, String password2, String phone2, String profileUri2, String createdAt2, String updatedAt2, String suscriptionDate2, UserPayment userPayments, String userType2) {
        this.f4402id = id;
        this.name = name2;
        this.email = email2;
        this.password = password2;
        this.phone = phone2;
        this.profileUri = profileUri2;
        this.createdAt = createdAt2;
        this.updatedAt = updatedAt2;
        this.suscriptionDate = suscriptionDate2;
        this.payments = userPayments;
        this.userType = userType2;
    }

    public String getId() {
        return this.f4402id;
    }

    public void setId(String id) {
        this.f4402id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name2) {
        this.name = name2;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email2) {
        this.email = email2;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone2) {
        this.phone = phone2;
    }

    public String getProfileUri() {
        return this.profileUri;
    }

    public void setProfileUri(String profileUri2) {
        this.profileUri = profileUri2;
    }

    public String getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(String createdAt2) {
        this.createdAt = createdAt2;
    }

    public String getUpdatedAt() {
        return this.updatedAt;
    }

    public void setUpdatedAt(String updatedAt2) {
        this.updatedAt = updatedAt2;
    }

    public String getSuscriptionDate() {
        return this.suscriptionDate;
    }

    public void setSuscriptionDate(String suscriptionDate2) {
        this.suscriptionDate = suscriptionDate2;
    }

    public UserPayment getUserPayments() {
        return this.payments;
    }

    public void setUserPayments(UserPayment userPayments) {
        this.payments = userPayments;
    }

    public String getUserType() {
        return this.userType;
    }

    public void setUserType(String userType2) {
        this.userType = userType2;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password2) {
        this.password = password2;
    }

    public String toString() {
        return "User{id='" + this.f4402id + '\'' + ", name='" + this.name + '\'' + ", email='" + this.email + '\'' + ", phone='" + this.phone + '\'' + ", profileUri='" + this.profileUri + '\'' + ", createdAt='" + this.createdAt + '\'' + ", updatedAt='" + this.updatedAt + '\'' + ", suscriptionDate='" + this.suscriptionDate + '\'' + ", userPayments=" + this.payments + ", userType='" + this.userType + '\'' + '}';
    }
}
