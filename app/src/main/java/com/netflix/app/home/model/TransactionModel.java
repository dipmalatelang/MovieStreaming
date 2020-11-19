package com.netflix.app.home.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.lang.annotation.Annotation;

public class TransactionModel implements SerializedName, Parcelable {
    public static final Creator<TransactionModel> CREATOR = new Creator<TransactionModel>() {
        public TransactionModel createFromParcel(Parcel in) {
            return new TransactionModel(in);
        }

        public TransactionModel[] newArray(int size) {
            return new TransactionModel[size];
        }
    };
    @SerializedName("bankname")
    private String BANKNAME;
    @SerializedName("banktxnid")
    private String BANKTXNID;
    @SerializedName("checksumhash")
    private String CHECKSUMHASH;
    @SerializedName("currency")
    private String CURRENCY;
    @SerializedName("gatewayname")
    private String GATEWAYNAME;
    @SerializedName("mid")
    private String MID;
    @SerializedName("orderid")
    private String ORDERID;
    @SerializedName("paymentmode")
    private String PAYMENTMODE;
    @SerializedName("rescode")
    private String RESPCODE;
    @SerializedName("respmsg")
    private String RESPMSG;
    @SerializedName("status")
    private String STATUS;
    @SerializedName("subType")
    private String SUBSCRIPTION_TYPE;
    @SerializedName("txnamount")
    private String TXNAMOUNT;
    @SerializedName("txndate")
    private String TXNDATE;
    @SerializedName("txnid")
    private String TXNID;
    @SerializedName("userId")
    private String USER_ID;
    @SerializedName("id")

    /* renamed from: id */
    private Integer f4401id;

    public TransactionModel(Integer id, String STATUS2, String CHECKSUMHASH2, String BANKNAME2, String ORDERID2, String TXNAMOUNT2, String TXNDATE2, String MID2, String TXNID2, String RESPCODE2, String PAYMENTMODE2, String BANKTXNID2, String CURRENCY2, String GATEWAYNAME2, String RESPMSG2, String USER_ID2, String SUBSCRIPTION_TYPE2) {
        this.f4401id = id;
        this.STATUS = STATUS2;
        this.CHECKSUMHASH = CHECKSUMHASH2;
        this.BANKNAME = BANKNAME2;
        this.ORDERID = ORDERID2;
        this.TXNAMOUNT = TXNAMOUNT2;
        this.TXNDATE = TXNDATE2;
        this.MID = MID2;
        this.TXNID = TXNID2;
        this.RESPCODE = RESPCODE2;
        this.PAYMENTMODE = PAYMENTMODE2;
        this.BANKTXNID = BANKTXNID2;
        this.CURRENCY = CURRENCY2;
        this.GATEWAYNAME = GATEWAYNAME2;
        this.RESPMSG = RESPMSG2;
        this.USER_ID = USER_ID2;
        this.SUBSCRIPTION_TYPE = SUBSCRIPTION_TYPE2;
    }

    protected TransactionModel(Parcel in) {
        this.f4401id = Integer.valueOf(in.readInt());
        this.STATUS = in.readString();
        this.CHECKSUMHASH = in.readString();
        this.BANKNAME = in.readString();
        this.ORDERID = in.readString();
        this.TXNAMOUNT = in.readString();
        this.TXNDATE = in.readString();
        this.MID = in.readString();
        this.TXNID = in.readString();
        this.RESPCODE = in.readString();
        this.PAYMENTMODE = in.readString();
        this.BANKTXNID = in.readString();
        this.CURRENCY = in.readString();
        this.GATEWAYNAME = in.readString();
        this.RESPMSG = in.readString();
        this.USER_ID = in.readString();
        this.SUBSCRIPTION_TYPE = in.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.f4401id.intValue());
        parcel.writeString(this.STATUS);
        parcel.writeString(this.CHECKSUMHASH);
        parcel.writeString(this.BANKNAME);
        parcel.writeString(this.ORDERID);
        parcel.writeString(this.TXNAMOUNT);
        parcel.writeString(this.TXNDATE);
        parcel.writeString(this.MID);
        parcel.writeString(this.TXNID);
        parcel.writeString(this.RESPCODE);
        parcel.writeString(this.PAYMENTMODE);
        parcel.writeString(this.BANKTXNID);
        parcel.writeString(this.CURRENCY);
        parcel.writeString(this.GATEWAYNAME);
        parcel.writeString(this.RESPMSG);
        parcel.writeString(this.USER_ID);
        parcel.writeString(this.SUBSCRIPTION_TYPE);
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

    public Integer getId() {
        return this.f4401id;
    }

    public void setId(Integer id) {
        this.f4401id = id;
    }

    public String getSTATUS() {
        return this.STATUS;
    }

    public void setSTATUS(String STATUS2) {
        this.STATUS = STATUS2;
    }

    public String getCHECKSUMHASH() {
        return this.CHECKSUMHASH;
    }

    public void setCHECKSUMHASH(String CHECKSUMHASH2) {
        this.CHECKSUMHASH = CHECKSUMHASH2;
    }

    public String getBANKNAME() {
        return this.BANKNAME;
    }

    public void setBANKNAME(String BANKNAME2) {
        this.BANKNAME = BANKNAME2;
    }

    public String getORDERID() {
        return this.ORDERID;
    }

    public void setORDERID(String ORDERID2) {
        this.ORDERID = ORDERID2;
    }

    public String getTXNAMOUNT() {
        return this.TXNAMOUNT;
    }

    public void setTXNAMOUNT(String TXNAMOUNT2) {
        this.TXNAMOUNT = TXNAMOUNT2;
    }

    public String getTXNDATE() {
        return this.TXNDATE;
    }

    public void setTXNDATE(String TXNDATE2) {
        this.TXNDATE = TXNDATE2;
    }

    public String getMID() {
        return this.MID;
    }

    public void setMID(String MID2) {
        this.MID = MID2;
    }

    public String getTXNID() {
        return this.TXNID;
    }

    public void setTXNID(String TXNID2) {
        this.TXNID = TXNID2;
    }

    public String getRESPCODE() {
        return this.RESPCODE;
    }

    public void setRESPCODE(String RESPCODE2) {
        this.RESPCODE = RESPCODE2;
    }

    public String getPAYMENTMODE() {
        return this.PAYMENTMODE;
    }

    public void setPAYMENTMODE(String PAYMENTMODE2) {
        this.PAYMENTMODE = PAYMENTMODE2;
    }

    public String getBANKTXNID() {
        return this.BANKTXNID;
    }

    public void setBANKTXNID(String BANKTXNID2) {
        this.BANKTXNID = BANKTXNID2;
    }

    public String getCURRENCY() {
        return this.CURRENCY;
    }

    public void setCURRENCY(String CURRENCY2) {
        this.CURRENCY = CURRENCY2;
    }

    public String getGATEWAYNAME() {
        return this.GATEWAYNAME;
    }

    public void setGATEWAYNAME(String GATEWAYNAME2) {
        this.GATEWAYNAME = GATEWAYNAME2;
    }

    public String getRESPMSG() {
        return this.RESPMSG;
    }

    public void setRESPMSG(String RESPMSG2) {
        this.RESPMSG = RESPMSG2;
    }

    public String getUSER_ID() {
        return this.USER_ID;
    }

    public void setUSER_ID(String USER_ID2) {
        this.USER_ID = USER_ID2;
    }

    public String getSUBSCRIPTION_TYPE() {
        return this.SUBSCRIPTION_TYPE;
    }

    public void setSUBSCRIPTION_TYPE(String SUBSCRIPTION_TYPE2) {
        this.SUBSCRIPTION_TYPE = SUBSCRIPTION_TYPE2;
    }
}
