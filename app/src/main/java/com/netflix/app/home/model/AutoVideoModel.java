package com.netflix.app.home.model;

public class AutoVideoModel {
    private final String image_url;
    private final String name;
    private String video_url;

    public AutoVideoModel(String video_url2, String image_url2, String name2) {
        this.video_url = video_url2;
        this.image_url = image_url2;
        this.name = name2;
    }

    public AutoVideoModel(String image_url2, String name2) {
        this.image_url = image_url2;
        this.name = name2;
    }

    public AutoVideoModel(String name2) {
        this.image_url = null;
        this.name = name2;
    }

    public String getImage_url() {
        return this.image_url;
    }

    public String getVideo_url() {
        return this.video_url;
    }

    public String getName() {
        return this.name;
    }
}
