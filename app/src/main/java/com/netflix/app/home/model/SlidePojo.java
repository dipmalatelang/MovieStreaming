package com.netflix.app.home.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SlidePojo {
    @SerializedName("Thumbnail")
    @Expose
    private String thumbnail;
    @SerializedName("Id")
    @Expose
    private String id;

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
