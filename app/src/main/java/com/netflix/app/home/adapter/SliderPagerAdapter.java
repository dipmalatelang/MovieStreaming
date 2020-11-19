package com.netflix.app.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.netflix.app.R;
import com.netflix.app.home.model.AllVideo;
import com.netflix.app.home.model.SlidePojo;
import com.netflix.app.home.ui.PlayMovieActivity;
import com.netflix.app.utlis.FavDB;
import com.netflix.app.utlis.SharedPrefs;


import java.util.List;

public class SliderPagerAdapter extends PagerAdapter {
    private Context mContext;
    private List<AllVideo> list;
    private LayoutInflater layoutInflater;
    public static String VIDEO_BANNER = "bannervideo";
    public static String VIDEO_BANNER_Name = "bannervideoname";
    String bannerurl;
    String bannername;

    private FavDB favDB;



    public SliderPagerAdapter(Context mContext, List<AllVideo> list)
    {
        this.mContext = mContext;
        this.list = list;
    }



    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slide_item,null);
        ImageView img = view.findViewById(R.id.slide_img);
        Button btn_share,btn_addfav;
        FloatingActionButton floatingActionButton;
        btn_addfav= view.findViewById(R.id.btn_addfav);
        btn_share = view.findViewById(R.id.btn_share);
        floatingActionButton = view.findViewById(R.id.floatingActionButton);
        Glide.with(mContext).load(list.get(position).getThumbs()).into(img);

        btn_addfav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favDB  = new FavDB(mContext);

                favDB.insertIntoTheDatabase("",list.get(position).getThumbs(),list.get(position).getLikes(),"1");

                Toast.makeText(mContext, "Favourite has been Saved  "+ position, Toast.LENGTH_SHORT).show();

            }
        });
        btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Here is the share content body";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                mContext.startActivity(Intent.createChooser(sharingIntent, "Share via"));
            }
        });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                list.get(position).getVdoUrl();

                 bannerurl =list.get(position).getVdoUrl();
                 bannername = list.get(position).getTitle();
                 SharedPrefs.getInstance().addString(VIDEO_BANNER, bannerurl);
                 SharedPrefs.getInstance().addString(VIDEO_BANNER_Name,bannername);
                 Log.d("TAG", "onClickvideo: "+bannername);
                 mContext.startActivity(new Intent(mContext, PlayMovieActivity.class));



            }
        });

        ViewPager viewPager=(ViewPager)container;
        viewPager.addView(view);
        return view;


    }
    @Override
    public int getCount() {
        return list.size();
    }


    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ViewPager viewPager=(ViewPager)container;
        View view=(View)object;
        viewPager.removeView(view);

    }



}
