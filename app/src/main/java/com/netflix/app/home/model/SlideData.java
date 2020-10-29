package com.netflix.app.home.model;

public class SlideData {
    private int Image ;
    private String Title;
    public SlideData(int image, String title) {
        Image = image;
        Title = title;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }


}
