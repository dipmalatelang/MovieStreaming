package com.netflix.app.home.ui;

import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.allattentionhere.autoplayvideos.AAH_CustomRecyclerView;
import com.netflix.app.R;
import com.netflix.app.home.adapter.MyVideosAdapter;
import com.netflix.app.home.model.AutoVideoModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class TrendingFragment extends Fragment {
    private final List<AutoVideoModel> modelList = new ArrayList();
    AAH_CustomRecyclerView recyclerView;
    private View root_view;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_tranding, container, false);
        this.root_view = inflate;
        this.recyclerView =  inflate.findViewById(R.id.rv_home);
        Picasso p = Picasso.with(getContext());
        this.modelList.add(new AutoVideoModel("https://gamotranceott.s3.ap-south-1.amazonaws.com/7Sargam+Records/Videos/5+-+Kahe+Dehiya+Mota+Raha+H.mp4", "https://gamotranceott.s3.ap-south-1.amazonaws.com/7Sargam+Records/Image/5+-+Kahe+Dehiya+Mota+Raha+H.jpg", "Kahe Dehiya Mota Raha"));
        this.modelList.add(new AutoVideoModel("https://gamotranceott.s3.ap-south-1.amazonaws.com/7Sargam+Records/Geet+Records+Bhojpuri/Video/20+-+Mammi+Abhi+Sutal+Naikhe.mp4", "https://gamotranceott.s3.ap-south-1.amazonaws.com/7Sargam+Records/Geet+Records+Bhojpuri/Image/20+-+Mammi+Abhi+Sutal+Naikhe.jpg", "Manya Manib Singh"));
        this.modelList.add(new AutoVideoModel("https://gamotranceott.s3.ap-south-1.amazonaws.com/7Sargam+Records/Geet+Records+Bhojpuri/Video/21+-+Piya+Sadi+Me+Kadi.mp4", "https://gamotranceott.s3.ap-south-1.amazonaws.com/7Sargam+Records/Geet+Records+Bhojpuri/Image/21+-+Piya+Sadi+Me+Kadi.jpg", "Chadli Jawani Hamre"));
        this.modelList.add(new AutoVideoModel("https://gamotranceott.s3.ap-south-1.amazonaws.com/7Sargam+Records/Geet+Records+Bhojpuri/Video/22+-+chadli+jawani+hamre.mp4", "https://gamotranceott.s3.ap-south-1.amazonaws.com/7Sargam+Records/Image/5+-+Kahe+Dehiya+Mota+Raha+H.jpg", "Kahe Dehiya Mota Raha"));
        this.modelList.add(new AutoVideoModel("https://gamotranceott.s3.ap-south-1.amazonaws.com/7Sargam+Records/Geet+Records+Bhojpuri/Video/26+-+Jawani+Tadpata.mp4", "https://img-l3.xnxx-cdn.com/videos/thumbs169lll/d1/49/20/d14920f62c07ba9fd6d331699f058664/d14920f62c07ba9fd6d331699f058664.8.jpg", "Kahe Dehiya Mota Raha"));
        this.modelList.add(new AutoVideoModel("https://gamotranceott.s3.ap-south-1.amazonaws.com/7Sargam+Records/Videos/5+-+Kahe+Dehiya+Mota+Raha+H.mp4", "https://gamotranceott.s3.ap-south-1.amazonaws.com/7Sargam+Records/Image/5+-+Kahe+Dehiya+Mota+Raha+H.jpg", "Kahe Dehiya Mota Raha"));
        this.modelList.add(new AutoVideoModel("https://gamotranceott.s3.ap-south-1.amazonaws.com/7Sargam+Records/Geet+Records+Bhojpuri/Video/20+-+Mammi+Abhi+Sutal+Naikhe.mp4", "https://gamotranceott.s3.ap-south-1.amazonaws.com/7Sargam+Records/Geet+Records+Bhojpuri/Image/20+-+Mammi+Abhi+Sutal+Naikhe.jpg", "Manya Manib Singh"));
        this.modelList.add(new AutoVideoModel("https://gamotranceott.s3.ap-south-1.amazonaws.com/7Sargam+Records/Geet+Records+Bhojpuri/Video/21+-+Piya+Sadi+Me+Kadi.mp4", "https://gamotranceott.s3.ap-south-1.amazonaws.com/7Sargam+Records/Geet+Records+Bhojpuri/Image/21+-+Piya+Sadi+Me+Kadi.jpg", "Chadli Jawani Hamre"));
        this.modelList.add(new AutoVideoModel("https://gamotranceott.s3.ap-south-1.amazonaws.com/7Sargam+Records/Geet+Records+Bhojpuri/Video/22+-+chadli+jawani+hamre.mp4", "https://gamotranceott.s3.ap-south-1.amazonaws.com/7Sargam+Records/Image/5+-+Kahe+Dehiya+Mota+Raha+H.jpg", "Kahe Dehiya Mota Raha"));
        this.modelList.add(new AutoVideoModel("https://gamotranceott.s3.ap-south-1.amazonaws.com/7Sargam+Records/Geet+Records+Bhojpuri/Video/24+-+Bhatar+Seji+Par+Hafe+Lagal.mp4", "https://gamotranceott.s3.ap-south-1.amazonaws.com/7Sargam+Records/Geet+Records+Bhojpuri/Image/24+-+Bhatar+Seji+Par+Hafe+Lagal.jpg", "Nahi Rahale Yaar"));
        this.modelList.add(new AutoVideoModel("https://gamotranceott.s3.ap-south-1.amazonaws.com/7Sargam+Records/Geet+Records+Bhojpuri/Video/20+-+Mammi+Abhi+Sutal+Naikhe.mp4", "https://gamotranceott.s3.ap-south-1.amazonaws.com/7Sargam+Records/Geet+Records+Bhojpuri/Image/20+-+Mammi+Abhi+Sutal+Naikhe.jpg", "Manya Manib Singh"));
        this.modelList.add(new AutoVideoModel("https://gamotranceott.s3.ap-south-1.amazonaws.com/7Sargam+Records/Geet+Records+Bhojpuri/Video/21+-+Piya+Sadi+Me+Kadi.mp4", "https://gamotranceott.s3.ap-south-1.amazonaws.com/7Sargam+Records/Geet+Records+Bhojpuri/Image/21+-+Piya+Sadi+Me+Kadi.jpg", "Chadli Jawani Hamre"));
        this.modelList.add(new AutoVideoModel("https://gamotranceott.s3.ap-south-1.amazonaws.com/7Sargam+Records/Geet+Records+Bhojpuri/Video/22+-+chadli+jawani+hamre.mp4", "https://gamotranceott.s3.ap-south-1.amazonaws.com/7Sargam+Records/Image/5+-+Kahe+Dehiya+Mota+Raha+H.jpg", "Kahe Dehiya Mota Raha"));
        this.modelList.add(new AutoVideoModel("https://gamotranceott.s3.ap-south-1.amazonaws.com/7Sargam+Records/Geet+Records+Bhojpuri/Video/24+-+Bhatar+Seji+Par+Hafe+Lagal.mp4", "https://gamotranceott.s3.ap-south-1.amazonaws.com/7Sargam+Records/Geet+Records+Bhojpuri/Image/24+-+Bhatar+Seji+Par+Hafe+Lagal.jpg", "Nahi Rahale Yaar"));
        MyVideosAdapter mAdapter = new MyVideosAdapter(this.modelList, p);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.recyclerView.setItemAnimator(new DefaultItemAnimator());
        this.recyclerView.setActivity(getActivity());
        this.recyclerView.setPlayOnlyFirstVideo(true);
        this.recyclerView.setCheckForMp4(false);
        AAH_CustomRecyclerView aAH_CustomRecyclerView = this.recyclerView;
        aAH_CustomRecyclerView.setDownloadPath(Environment.getExternalStorageDirectory() + "/MyVideo");
        this.recyclerView.setDownloadVideos(true);
        this.recyclerView.setVisiblePercent(50.0f);
        List<String> urls = new ArrayList<>();
        for (AutoVideoModel object : this.modelList) {
            if (object.getVideo_url() != null && object.getVideo_url().contains("http")) {
                urls.add(object.getVideo_url());
            }
        }
        this.recyclerView.preDownload(urls);
        this.recyclerView.setAdapter(mAdapter);
        this.recyclerView.smoothScrollBy(0, 1);
        this.recyclerView.smoothScrollBy(0, -1);
        return this.root_view;
    }

    public void onStop() {
        super.onStop();
        this.recyclerView.stopVideos();
    }
}
