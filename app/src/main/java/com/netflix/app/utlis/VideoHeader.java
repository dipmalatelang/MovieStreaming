package com.netflix.app.utlis;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.netflix.app.R;

import com.netflix.app.home.adapter.VideoArrayAdapter.ItemType;

public class VideoHeader implements  VideoTypeItem{

    String videotypename;

    public VideoHeader(String videotypename) {
        this.videotypename = videotypename;
    }

    @Override
    public int getViewType() {
        return ItemType.HEADER_ITEM.ordinal();
    }

    @Override
    public View getView(LayoutInflater layoutInflater, View view) {
        View v ;
        if(view == null){
            v = layoutInflater.inflate(R.layout.main_recycler_row_item,null);
        } else {
            v = view;

        }
        TextView cat_title = v.findViewById(R.id.cat_title);

        cat_title.setText(videotypename);

        return v;
    }
}
