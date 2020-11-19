package com.netflix.app.home.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.lang.annotation.Annotation;

class CastModel  implements SerializedName, Parcelable {

    public static final Parcelable.Creator<CastModel> CREATOR = new Parcelable.Creator<CastModel>() {
        public CastModel createFromParcel(Parcel in) {
            return new CastModel(in);
        }

        public CastModel[] newArray(int size) {
            return new CastModel[size];
        }
    };
    @SerializedName("id")

    /* renamed from: id */
    private String f4397id;
    @SerializedName("imageUrl")
    private String imageUrl;
    @SerializedName("name")
    private String name;

    public CastModel(String id, String name2, String imageUrl2) {
        this.f4397id = id;
        this.name = name2;
        this.imageUrl = imageUrl2;
    }

    protected CastModel(Parcel in) {
        this.f4397id = in.readString();
        this.name = in.readString();
        this.imageUrl = in.readString();
    }

    public String getId() {
        return this.f4397id;
    }

    public void setId(String id) {
        this.f4397id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name2) {
        this.name = name2;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl2) {
        this.imageUrl = imageUrl2;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f4397id);
        parcel.writeString(this.name);
        parcel.writeString(this.imageUrl);
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
