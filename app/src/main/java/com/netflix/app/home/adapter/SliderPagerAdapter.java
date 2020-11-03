package com.netflix.app.home.adapter;

import android.content.Context;
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
import com.netflix.app.R;
import com.netflix.app.home.model.SlidePojo;
import com.netflix.app.utlis.FavDB;
import com.netflix.app.utlis.FavItem;


import java.util.ArrayList;
import java.util.List;

public class SliderPagerAdapter extends PagerAdapter {
    private Context mContext;
    private List<SlidePojo> list;
    private LayoutInflater layoutInflater;

    private FavDB favDB;



    public SliderPagerAdapter(Context mContext, List<SlidePojo> list)
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
        btn_addfav= view.findViewById(R.id.btn_addfav);
        btn_share = view.findViewById(R.id.btn_share);
        Glide.with(mContext).load(list.get(position).getThumbnail()).into(img);

        btn_addfav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                favDB  = new FavDB(mContext);

                favDB.insertIntoTheDatabase("",list.get(position).getThumbnail(),list.get(position).getId(),"1");

                Toast.makeText(mContext, "Favourite has been Saved  "+ position, Toast.LENGTH_SHORT).show();

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
