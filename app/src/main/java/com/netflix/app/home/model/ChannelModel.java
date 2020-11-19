package com.netflix.app.home.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import java.lang.annotation.Annotation;
import java.util.ArrayList;

public class ChannelModel implements SerializedName, Parcelable {
    public static final Creator<ChannelModel> CREATOR = new Creator<ChannelModel>() {
        public ChannelModel createFromParcel(Parcel in) {
            return new ChannelModel(in);
        }

        public ChannelModel[] newArray(int size) {
            return new ChannelModel[size];
        }
    };
    @SerializedName("description")
    private String description;
    @SerializedName("id")

    /* renamed from: id */
    private Integer f4398id;
    @SerializedName("profileUrl")
    private String profileUrl;
    @SerializedName("subscribers")
    private ArrayList<String> subscribers;
    @SerializedName("title")
    private String title;

    public ChannelModel(Integer id, String title2, String description2, String profileUrl2, ArrayList<String> subscribers2) {
        this.f4398id = id;
        this.title = title2;
        this.description = description2;
        this.profileUrl = profileUrl2;
        this.subscribers = subscribers2;
    }

    protected ChannelModel(Parcel in) {
        if (in.readByte() == 0) {
            this.f4398id = null;
        } else {
            this.f4398id = Integer.valueOf(in.readInt());
        }
        this.title = in.readString();
        this.description = in.readString();
        this.profileUrl = in.readString();
        this.subscribers = in.createStringArrayList();
    }

    public void writeToParcel(Parcel dest, int flags) {
        if (this.f4398id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(this.f4398id.intValue());
        }
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.profileUrl);
        dest.writeStringList(this.subscribers);
    }

    public int describeContents() {
        return 0;
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
        return this.f4398id;
    }

    public void setId(Integer id) {
        this.f4398id = id;
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

    public String getProfileUrl() {
        return this.profileUrl;
    }

    public void setProfileUrl(String profileUrl2) {
        this.profileUrl = profileUrl2;
    }

    public ArrayList<String> getSubscribers() {
        return this.subscribers;
    }

    public void setSubscribers(ArrayList<String> subscribers2) {
        this.subscribers = subscribers2;
    }
}
