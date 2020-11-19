package com.netflix.app.home.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.lang.annotation.Annotation;

public class DirectorModel implements SerializedName, Parcelable {
    public static final Creator<DirectorModel> CREATOR = new Creator<DirectorModel>() {
        public DirectorModel createFromParcel(Parcel in) {
            return new DirectorModel(in);
        }

        public DirectorModel[] newArray(int size) {
            return new DirectorModel[size];
        }
    };
    @SerializedName("dirName")
    private String dirName;
    @SerializedName("id")

    /* renamed from: id */
    private String f4399id;

    public DirectorModel(String id, String dirName2) {
        this.f4399id = id;
        this.dirName = dirName2;
    }

    protected DirectorModel(Parcel in) {
        this.f4399id = in.readString();
        this.dirName = in.readString();
    }

    public String getId() {
        return this.f4399id;
    }

    public void setId(String id) {
        this.f4399id = id;
    }

    public String getDirName() {
        return this.dirName;
    }

    public void setDirName(String dirName2) {
        this.dirName = dirName2;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f4399id);
        parcel.writeString(this.dirName);
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
