package com.netflix.app.upcoming;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.netflix.app.R;
import com.netflix.app.home.adapter.MovieItemClickListener;
import com.netflix.app.home.model.AllVideo;
import com.netflix.app.home.ui.PlayMovieActivity;
import com.netflix.app.utlis.SharedPrefs;


import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.netflix.app.home.adapter.SliderPagerAdapter.VIDEO_BANNER;
import static com.netflix.app.home.adapter.SliderPagerAdapter.VIDEO_BANNER_Name;

public class UpcomingAdapter extends RecyclerView.Adapter<UpcomingAdapter.MyViewHolder> {

    Context mcontext;
    private ArrayList<AllVideo> mlist = new ArrayList<>();
    MovieItemClickListener movieItemClickListener;


    public UpcomingAdapter(Context mcontext, ArrayList<AllVideo> mlist, MovieItemClickListener listener) {
        this.mcontext = mcontext;
        this.mlist = mlist;
        movieItemClickListener = listener;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.item_upcoming, parent, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String str_title = mlist.get(position).getTitle();
        Log.d("TAG", "onBindViewHolder: " + str_title);
        String strDesc = mlist.get(position).getDescription();
        holder.title.setText(str_title);
        holder.desc.setText(strDesc);
        Log.d("TAG", "onBindViewHolder: " + strDesc);
        Glide.with(mcontext).load(Uri.parse(this.mlist.get(position).getThumbs())).into(holder.thumbs);
        Glide.with(mcontext).load(Uri.parse(this.mlist.get(position).getThumbs())).into(holder.channel1);

        holder.shareLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Here is the share content body";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                mcontext.startActivity(Intent.createChooser(sharingIntent, "Share via"));

            }
        });


    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView title, desc;
        private ImageView thumbs, remind;
        private RelativeLayout remShareLay;
        private CircleImageView channel1;
        private LinearLayout shareLay, remindLay;




        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            desc = itemView.findViewById(R.id.description);
            thumbs = itemView.findViewById(R.id.thumb_img);
            remShareLay = itemView.findViewById(R.id.imgLay);
            shareLay = itemView.findViewById(R.id.shareLay);
            channel1 = itemView.findViewById(R.id.channelLogo1);
            remindLay = itemView.findViewById(R.id.remindLay);
            remind = itemView.findViewById(R.id.reminde);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    movieItemClickListener.onMovieClick(mlist.get(getAdapterPosition()),thumbs);
                    Log.d("TAG", "getAdapterPosition: "+mlist.get(getAdapterPosition()));
                    mlist.get(getAdapterPosition()).getVdoUrl();

                    String bannerurl =mlist.get(getAdapterPosition()).getVdoUrl();
                    String  bannername = mlist.get(getAdapterPosition()).getTitle();
                    SharedPrefs.getInstance().addString(VIDEO_BANNER, bannerurl);
                    SharedPrefs.getInstance().addString(VIDEO_BANNER_Name,bannername);
                    mcontext.startActivity(new Intent(mcontext, PlayMovieActivity.class));
                    Log.d("TAG", "onClickvideo: "+bannername);
                    Toast.makeText(mcontext, "Click "+mlist.get(getAdapterPosition()), Toast.LENGTH_SHORT).show();


                }
            });

        }
    }
}
