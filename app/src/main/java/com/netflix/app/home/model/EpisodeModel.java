package com.netflix.app.home.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.lang.annotation.Annotation;

public class EpisodeModel implements SerializedName, Parcelable {
    public static final Creator<EpisodeModel> CREATOR = new Creator<EpisodeModel>() {
        public EpisodeModel createFromParcel(Parcel in) {
            return new EpisodeModel(in);
        }

        public EpisodeModel[] newArray(int size) {
            return new EpisodeModel[size];
        }
    };
    @SerializedName("description")
    private String description;
    @SerializedName("episNumber")
    private String episNumber;
    @SerializedName("id")

    /* renamed from: id */
    private String f4400id;
    @SerializedName("submitDate")
    private String submitDate;
    @SerializedName("thumbs")
    private String thumbs;
    @SerializedName("title")
    private String title;
    @SerializedName("vdoUrl")
    private String vdoUrl;

    public EpisodeModel(String id, String title2, String description2, String thumbs2, String vdoUrl2, String episNumber2, String submitDate2) {
        this.f4400id = id;
        this.title = title2;
        this.description = description2;
        this.thumbs = thumbs2;
        this.vdoUrl = vdoUrl2;
        this.episNumber = episNumber2;
        this.submitDate = submitDate2;
    }

    public String getId() {
        return this.f4400id;
    }

    public void setId(String id) {
        this.f4400id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title2) {
        this.title = title2;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description2) {
        this.description = description2;
    }

    public String getThumbs() {
        return this.thumbs;
    }

    public void setThumbs(String thumbs2) {
        this.thumbs = thumbs2;
    }

    public String getVdoUrl() {
        return this.vdoUrl;
    }

    public void setVdoUrl(String vdoUrl2) {
        this.vdoUrl = vdoUrl2;
    }

    public String getEpisNumber() {
        return this.episNumber;
    }

    public void setEpisNumber(String episNumber2) {
        this.episNumber = episNumber2;
    }

    public String getSubmitDate() {
        return this.submitDate;
    }

    public void setSubmitDate(String submitDate2) {
        this.submitDate = submitDate2;
    }

    protected EpisodeModel(Parcel in) {
        this.f4400id = in.readString();
        this.title = in.readString();
        this.description = in.readString();
        this.thumbs = in.readString();
        this.vdoUrl = in.readString();
        this.episNumber = in.readString();
        this.submitDate = in.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.f4400id);
        parcel.writeString(this.title);
        parcel.writeString(this.description);
        parcel.writeString(this.thumbs);
        parcel.writeString(this.vdoUrl);
        parcel.writeString(this.episNumber);
        parcel.writeString(this.submitDate);
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
