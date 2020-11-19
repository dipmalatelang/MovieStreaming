package com.netflix.app.home.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.lang.annotation.Annotation;

public class UserPayment implements SerializedName, Parcelable {
    public static final Creator<UserPayment> CREATOR = new Creator<UserPayment>() {
        public UserPayment createFromParcel(Parcel in) {
            return new UserPayment(in);
        }

        public UserPayment[] newArray(int size) {
            return new UserPayment[size];
        }
    };
    @SerializedName("amount")
    private String amount;
    @SerializedName("createdAt")
    private String createdAt;
    @SerializedName("id")

    /* renamed from: id */
    private String f4403id;
    @SerializedName("paymentDate")
    private String paymentDate;
    @SerializedName("subscriptionType")
    private String subscriptionType;

    public UserPayment(String id, String amount2, String subscriptionType2, String createdAt2, String paymentDate2) {
        this.f4403id = id;
        this.amount = amount2;
        this.subscriptionType = subscriptionType2;
        this.createdAt = createdAt2;
        this.paymentDate = paymentDate2;
    }

    public String getId() {
        return this.f4403id;
    }

    public void setId(String id) {
        this.f4403id = id;
    }

    public String getAmount() {
        return this.amount;
    }

    public void setAmount(String amount2) {
        this.amount = amount2;
    }

    public String getSubscriptionType() {
        return this.subscriptionType;
    }

    public void setSubscriptionType(String subscriptionType2) {
        this.subscriptionType = subscriptionType2;
    }

    public String getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(String createdAt2) {
        this.createdAt = createdAt2;
    }

    public String getPaymentDate() {
        return this.paymentDate;
    }

    public void setPaymentDate(String paymentDate2) {
        this.paymentDate = paymentDate2;
    }

    public UserPayment(Parcel in) {
        this.f4403id = in.readString();
        this.amount = in.readString();
        this.subscriptionType = in.readString();
        this.createdAt = in.readString();
        this.paymentDate = in.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f4403id);
        parcel.writeString(this.amount);
        parcel.writeString(this.subscriptionType);
        parcel.writeString(this.createdAt);
        parcel.writeString(this.paymentDate);
    }

    public String value() {
        return null;
    }

    public String[] alternate() {
        return new String[0];
    }

    public Class<? extends Annotation> annotationType() {
        return null;
    }
}
