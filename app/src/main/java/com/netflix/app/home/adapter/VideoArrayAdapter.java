package com.netflix.app.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.netflix.app.utlis.VideoTypeItem;

import java.util.List;

public class VideoArrayAdapter extends ArrayAdapter<VideoTypeItem> {

    private LayoutInflater inflater;


    public VideoArrayAdapter(@NonNull Context context, List<VideoTypeItem> videoTypeItemList) {
        super(context, 0,videoTypeItemList);
        inflater = LayoutInflater.from(context);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
         return getItem(position).getView(inflater, convertView);
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).getViewType();
    }

    @Override
    public int getViewTypeCount() {
        return ItemType.values().length;
    }
    public enum ItemType{
        HEADER_ITEM, VIDEO_ITEM

    }
}
