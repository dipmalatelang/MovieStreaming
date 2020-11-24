package com.netflix.app.home.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.lang.annotation.Annotation;
import java.util.ArrayList;


public class AllVideo implements SerializedName, Parcelable , Comparable<AllVideo> {


    public static final Creator<AllVideo> CREATOR = new Creator<AllVideo>() {
        public AllVideo createFromParcel(Parcel in) {
            return new AllVideo(in);
        }

        public AllVideo[] newArray(int size) {
            return new AllVideo[size];
        }
    };
    @SerializedName("casts")
    private ArrayList<CastModel> castModels;
    @SerializedName("channelId")
    private Integer channelId;
    @SerializedName("description")
    private String description;
    @SerializedName("directors")
    private ArrayList<DirectorModel> directorModels;
    @SerializedName("eps")
    private ArrayList<EpisodeModel> episodeModels;
    @SerializedName("genres")
    private ArrayList<GenresModel> genresModels;
    @SerializedName("id")


    private String id;
    @SerializedName("likes")
    private String likes;
    @SerializedName("thumbs")
    private String thumbs;
    @SerializedName("title")
    private String title;
    @SerializedName("vdoUrl")
    private String vdoUrl;
    @SerializedName("videoType")
    private String videoType;
    @SerializedName("view")
    private String views;

    public AllVideo(String id, String title2, String description2, String likes2, Integer channelId2, String views2, String videoType2, String thumbs2, String vdoUrl2, ArrayList<EpisodeModel> episodeModels2, ArrayList<CastModel> castModels2, ArrayList<GenresModel> genresModels2, ArrayList<DirectorModel> directorModels2) {
        id = id;
        title = title2;
        description = description2;
        likes = likes2;
        channelId = channelId2;
        views = views2;
        videoType = videoType2;
        thumbs = thumbs2;
        vdoUrl = vdoUrl2;
        episodeModels = episodeModels2;
       castModels = castModels2;
       genresModels = genresModels2;
       directorModels = directorModels2;
    }

    protected AllVideo(Parcel in) {
        id = in.readString();
        this.title = in.readString();
        this.description = in.readString();
        this.likes = in.readString();
        if (in.readByte() == 0) {
            this.channelId = null;
        } else {
            this.channelId = Integer.valueOf(in.readInt());
        }
        this.views = in.readString();
        this.videoType = in.readString();
        this.thumbs = in.readString();
        this.vdoUrl = in.readString();
        this.episodeModels = in.createTypedArrayList(EpisodeModel.CREATOR);
        this.castModels = in.createTypedArrayList(CastModel.CREATOR);
        this.genresModels = in.createTypedArrayList(GenresModel.CREATOR);
        this.directorModels = in.createTypedArrayList(DirectorModel.CREATOR);
    }


    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.likes);
        if (this.channelId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(this.channelId.intValue());
        }
        dest.writeString(this.views);
        dest.writeString(this.videoType);
        dest.writeString(this.thumbs);
        dest.writeString(this.vdoUrl);
        dest.writeTypedList(this.episodeModels);
        dest.writeTypedList(this.castModels);
        dest.writeTypedList(this.genresModels);
        dest.writeTypedList(this.directorModels);
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


    public void setId(String id) {
        this.id = id;
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

    public String getLikes() {
        return this.likes;
    }

    public void setLikes(String likes2) {
        this.likes = likes2;
    }

    public Integer getChannelId() {
        return this.channelId;
    }

    public void setChannelId(Integer channelId2) {
        this.channelId = channelId2;
    }

    public String getViews() {
        return this.views;
    }

    public void setViews(String views2) {
        this.views = views2;
    }

    public String getVideoType() {
        return this.videoType;
    }

    public void setVideoType(String videoType2) {
        this.videoType = videoType2;
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

    public ArrayList<EpisodeModel> getEpisodeModels() {
        return this.episodeModels;
    }

    public void setEpisodeModels(ArrayList<EpisodeModel> episodeModels2) {
        this.episodeModels = episodeModels2;
    }

    public ArrayList<CastModel> getCastModels() {
        return this.castModels;
    }

    public void setCastModels(ArrayList<CastModel> castModels2) {
        this.castModels = castModels2;
    }

    public ArrayList<GenresModel> getGenresModels() {
        return this.genresModels;
    }

    public void setGenresModels(ArrayList<GenresModel> genresModels2) {
        this.genresModels = genresModels2;
    }

    public ArrayList<DirectorModel> getDirectorModels() {
        return this.directorModels;
    }

    public void setDirectorModels(ArrayList<DirectorModel> directorModels2) {
        this.directorModels = directorModels2;
    }


    @Override
    public int compareTo(AllVideo o) {
        return this.getViews().compareTo(o.getViews());
    }



}

