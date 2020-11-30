package com.netflix.app.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.netflix.app.R;

import com.netflix.app.home.model.AllDataPojo.Ep;
import com.netflix.app.home.ui.PlayMovieActivity;
import com.netflix.app.utlis.SharedPrefs;

import java.util.List;

import static com.netflix.app.home.adapter.SliderPagerAdapter.VIDEO_BANNER;
import static com.netflix.app.home.adapter.SliderPagerAdapter.VIDEO_BANNER_Name;

public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.MyViewHolder>{
    Context mcontext;
    List<Ep> mlist;

    EpoItemClickListener epoItemClickListener;

    public EpisodeAdapter(Context mcontext, List<Ep> mlist, EpoItemClickListener epoItemClickListener) {
        this.mcontext = mcontext;
        this.mlist = mlist;
        this.epoItemClickListener = epoItemClickListener;
    }





    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.item_season,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        String str_title = mlist.get(i).getTitle();
        String str_part = mlist.get(i).getDescription();
        holder.textView_title.setText(str_title);
        holder.textView_desc.setText("Part-" + str_part);
        Glide.with(mcontext).load(mlist.get(i).getThumbs()).into(holder.item_image);



    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView item_image;
        private TextView textView_title;
        private TextView textView_desc;
        private FloatingActionButton play_fab;


        public MyViewHolder(@NonNull View itemView) {

        super(itemView);
            textView_title = itemView.findViewById(R.id.textView_title);
            textView_desc = itemView.findViewById(R.id.textView_desc);
            item_image = itemView.findViewById(R.id.item_image);
            play_fab = itemView.findViewById(R.id.play_fab);



            play_fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    epoItemClickListener.onMovieClick(mlist.get(getAdapterPosition()),item_image);
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
