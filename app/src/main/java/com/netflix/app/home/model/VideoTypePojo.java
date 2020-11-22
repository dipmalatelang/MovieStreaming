package com.netflix.app.home.model;

import java.util.ArrayList;
import java.util.List;

public class VideoTypePojo {
    String videotype;
    List<AllVideo> vlist;

    public VideoTypePojo(String webseries, ArrayList<AllVideo> webseries1) {
    }


    public String getVideotype() {
        return videotype;
    }

    public void setVideotype(String videotype) {
        this.videotype = videotype;
    }

    public List<AllVideo> getVlist() {
        return vlist;
    }

    public void setVlist(List<AllVideo> vlist) {
        this.vlist = vlist;
    }




}
