package com.netflix.app.home.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import java.util.List;

public class AllDataPojo implements Parcelable, Comparable<AllDataPojo> {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("likes")
    @Expose
    private Integer likes;
    @SerializedName("channelId")
    @Expose
    private Integer channelId;
    @SerializedName("casts")
    @Expose
    private List<Cast> casts = null;
    @SerializedName("genres")
    @Expose
    private List<Genre> genres = null;
    @SerializedName("directors")
    @Expose
    private List<Director> directors = null;
    @SerializedName("eps")
    @Expose
    private List<Ep> eps = null;
    @SerializedName("writer")
    @Expose
    private List<Writer> writer = null;
    @SerializedName("views")
    @Expose
    private Integer views;
    @SerializedName("videoType")
    @Expose
    private String videoType;
    @SerializedName("thumbs")
    @Expose
    private String thumbs;
    @SerializedName("partNumber")
    @Expose
    private String partNumber;
    @SerializedName("vdoUrl")
    @Expose
    private String vdoUrl;
    @SerializedName("mThumbs")
    @Expose
    private List<MThumb> mThumbs = null;
    @SerializedName("duration")
    @Expose
    private Object duration;

    protected AllDataPojo(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readInt();
        }
        title = in.readString();
        description = in.readString();
        if (in.readByte() == 0) {
            likes = null;
        } else {
            likes = in.readInt();
        }
        if (in.readByte() == 0) {
            channelId = null;
        } else {
            channelId = in.readInt();
        }
        if (in.readByte() == 0) {
            views = null;
        } else {
            views = in.readInt();
        }
        videoType = in.readString();
        thumbs = in.readString();
        partNumber = in.readString();
        vdoUrl = in.readString();
    }

    public static final Creator<AllDataPojo> CREATOR = new Creator<AllDataPojo>() {
        @Override
        public AllDataPojo createFromParcel(Parcel in) {
            return new AllDataPojo(in);
        }

        @Override
        public AllDataPojo[] newArray(int size) {
            return new AllDataPojo[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public List<Cast> getCasts() {
        return casts;
    }

    public void setCasts(List<Cast> casts) {
        this.casts = casts;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<Director> getDirectors() {
        return directors;
    }

    public void setDirectors(List<Director> directors) {
        this.directors = directors;
    }

    public List<Ep> getEps() {
        return eps;
    }

    public void setEps(List<Ep> eps) {
        this.eps = eps;
    }

    public List<Writer> getWriter() {
        return writer;
    }

    public void setWriter(List<Writer> writer) {
        this.writer = writer;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public String getVideoType() {
        return videoType;
    }

    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }

    public String getThumbs() {
        return thumbs;
    }

    public void setThumbs(String thumbs) {
        this.thumbs = thumbs;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getVdoUrl() {
        return vdoUrl;
    }

    public void setVdoUrl(String vdoUrl) {
        this.vdoUrl = vdoUrl;
    }

    public List<MThumb> getMThumbs() {
        return mThumbs;
    }

    public void setMThumbs(List<MThumb> mThumbs) {
        this.mThumbs = mThumbs;
    }

    public Object getDuration() {
        return duration;
    }

    public void setDuration(Object duration) {
        this.duration = duration;
    }

    @Override
    public int compareTo(AllDataPojo o) {
        return this.getViews().compareTo(o.getViews());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(id);
        }
        dest.writeString(title);
        dest.writeString(description);
        if (likes == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(likes);
        }
        if (channelId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(channelId);
        }
        if (views == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(views);
        }
        dest.writeString(videoType);
        dest.writeString(thumbs);
        dest.writeString(partNumber);
        dest.writeString(vdoUrl);
    }

    public class Cast {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("imageUrl")
        @Expose
        private String imageUrl;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

    }
    public class Director {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("dirName")
        @Expose
        private String dirName;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getDirName() {
            return dirName;
        }

        public void setDirName(String dirName) {
            this.dirName = dirName;
        }

    }
    public class Ep {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("thumbs")
        @Expose
        private String thumbs;
        @SerializedName("vdoUrl")
        @Expose
        private String vdoUrl;
        @SerializedName("episNumber")
        @Expose
        private String episNumber;
        @SerializedName("submitDate")
        @Expose
        private String submitDate;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getThumbs() {
            return thumbs;
        }

        public void setThumbs(String thumbs) {
            this.thumbs = thumbs;
        }

        public String getVdoUrl() {
            return vdoUrl;
        }

        public void setVdoUrl(String vdoUrl) {
            this.vdoUrl = vdoUrl;
        }

        public String getEpisNumber() {
            return episNumber;
        }

        public void setEpisNumber(String episNumber) {
            this.episNumber = episNumber;
        }

        public String getSubmitDate() {
            return submitDate;
        }

        public void setSubmitDate(String submitDate) {
            this.submitDate = submitDate;
        }

    }
    public class Genre {

        @SerializedName("genreName")
        @Expose
        private String genreName;

        public String getGenreName() {
            return genreName;
        }

        public void setGenreName(String genreName) {
            this.genreName = genreName;
        }

    }

    public class MThumb {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("thumbSize")
        @Expose
        private String thumbSize;
        @SerializedName("thumb")
        @Expose
        private String thumb;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getThumbSize() {
            return thumbSize;
        }

        public void setThumbSize(String thumbSize) {
            this.thumbSize = thumbSize;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

    }

    public class Writer {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("writerName")
        @Expose
        private String writerName;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getWriterName() {
            return writerName;
        }

        public void setWriterName(String writerName) {
            this.writerName = writerName;
        }

    }

}