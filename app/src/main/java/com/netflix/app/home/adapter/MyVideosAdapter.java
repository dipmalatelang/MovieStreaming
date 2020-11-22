package com.netflix.app.home.adapter;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.allattentionhere.autoplayvideos.AAH_CustomViewHolder;
import com.allattentionhere.autoplayvideos.AAH_VideosAdapter;
import com.google.android.exoplayer2.util.MimeTypes;
import com.netflix.app.R;
import com.netflix.app.home.model.AutoVideoModel;
import com.squareup.picasso.Picasso;

import java.util.List;


public class MyVideosAdapter extends AAH_VideosAdapter {
    private static final int TYPE_TEXT = 1;
    private static final int TYPE_VIDEO = 0;
    private final List<AutoVideoModel> list;
    private final Picasso picasso;

    public class MyViewHolder extends AAH_CustomViewHolder {
        final ImageView img_playback;
        final ImageView img_vol;
        boolean isMuted;

        /* renamed from: tv */
        final TextView tv;

        public MyViewHolder(View x) {
            super(x);
            tv = (TextView) x.findViewById(R.id.tv);
            img_vol = (ImageView) x.findViewById(R.id.img_vol);
            img_playback = (ImageView) x.findViewById(R.id.img_playback);
        }

        public void videoStarted() {
            super.videoStarted();
            this.img_playback.setImageResource(R.drawable.pause);
            if (this.isMuted) {
                muteVideo();
                this.img_vol.setImageResource(R.drawable.ic_baseline_music_off);
                return;
            }
            unmuteVideo();
            this.img_vol.setImageResource(R.drawable.ic_baseline_queue_music_24);
        }

        public void pauseVideo() {
            super.pauseVideo();
            this.img_playback.setImageResource(R.drawable.exo_icon_play);
        }
    }

    public class MyTextViewHolder extends AAH_CustomViewHolder {

        /* renamed from: tv */
        final TextView tv;

        public MyTextViewHolder(View x) {
            super(x);
            tv = (TextView) x.findViewById(R.id.tv);
        }
    }

    public MyVideosAdapter(List<AutoVideoModel> list_urls, Picasso p) {
        this.list = list_urls;
        this.picasso = p;
    }

    public AAH_CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 1) {
            return new MyTextViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.single_text, parent, false));
        }
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.single_card, parent, false));
    }

    public void onBindViewHolder(final AAH_CustomViewHolder holder, int position) {
        if (this.list.get(position).getName().startsWith(MimeTypes.BASE_TYPE_TEXT)) {
            ((MyTextViewHolder) holder).tv.setText(this.list.get(position).getName());
            return;
        }
        ((MyViewHolder) holder).tv.setText(this.list.get(position).getName());
        holder.setImageUrl(this.list.get(position).getImage_url());
        holder.setVideoUrl(this.list.get(position).getVideo_url());
        if (this.list.get(position).getImage_url() != null && !this.list.get(position).getImage_url().isEmpty()) {
            this.picasso.load(holder.getImageUrl()).config(Bitmap.Config.RGB_565).into(holder.getAAH_ImageView());
        }
        holder.setLooping(true);
        ((MyViewHolder) holder).img_playback.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (holder.isPlaying()) {
                    holder.pauseVideo();
                    holder.setPaused(true);
                    return;
                }
                holder.playVideo();
                holder.setPaused(false);
            }
        });
        ((MyViewHolder) holder).img_vol.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (((MyViewHolder) holder).isMuted) {
                    holder.unmuteVideo();
                    ((MyViewHolder) holder).img_vol.setImageResource(R.drawable.ic_baseline_queue_music_24);
                } else {
                    holder.muteVideo();
                    ((MyViewHolder) holder).img_vol.setImageResource(R.drawable.ic_baseline_music_off);
                }
                AAH_CustomViewHolder aAH_CustomViewHolder = holder;
                ((MyViewHolder) aAH_CustomViewHolder).isMuted = !((MyViewHolder) aAH_CustomViewHolder).isMuted;
            }
        });
        if (this.list.get(position).getVideo_url() == null) {
            ((MyViewHolder) holder).img_vol.setVisibility(View.GONE);
            ((MyViewHolder) holder).img_playback.setVisibility(View.GONE);
            return;
        }
        ((MyViewHolder) holder).img_vol.setVisibility(View.VISIBLE);
        ((MyViewHolder) holder).img_playback.setVisibility(View.VISIBLE);
    }

    public int getItemCount() {
        return this.list.size();
    }

    public int getItemViewType(int position) {
        if (this.list.get(position).getName().startsWith(MimeTypes.BASE_TYPE_TEXT)) {
            return 1;
        }
        return 0;
    }
}
