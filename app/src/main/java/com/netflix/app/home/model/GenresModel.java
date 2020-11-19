package com.netflix.app.home.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.lang.annotation.Annotation;

public class GenresModel implements SerializedName, Parcelable {
    public static final Creator<GenresModel> CREATOR = new Creator<GenresModel>() {
        public GenresModel createFromParcel(Parcel in) {
            return new GenresModel(in);
        }

        public GenresModel[] newArray(int size) {
            return new GenresModel[size];
        }
    };
    @SerializedName("genreName")
    private String genreName;

    public GenresModel(String genreName2) {
        this.genreName = genreName2;
    }

    protected GenresModel(Parcel in) {
        this.genreName = in.readString();
    }

    public String getGenreName() {
        return this.genreName;
    }

    public void setGenreName(String genreName2) {
        this.genreName = genreName2;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.genreName);
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
