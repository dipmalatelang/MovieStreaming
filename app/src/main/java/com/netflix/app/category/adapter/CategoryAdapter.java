package com.netflix.app.category.adapter;


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
import com.mikhaellopez.circularimageview.CircularImageView;
import com.netflix.app.R;
import com.netflix.app.home.adapter.MovieItemClickListener;
import com.netflix.app.home.model.AllVideo;
import com.netflix.app.home.ui.PlayMovieActivity;
import com.netflix.app.utlis.SharedPrefs;

import java.util.List;

import static com.netflix.app.home.adapter.SliderPagerAdapter.VIDEO_BANNER;
import static com.netflix.app.home.adapter.SliderPagerAdapter.VIDEO_BANNER_Name;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.MyViewHolder> {

    Context mcontext ;
    List<AllVideo> mlist ;
    MovieItemClickListener movieItemClickListener;



    public CategoryAdapter(Context mcontext, List<AllVideo> mlist, MovieItemClickListener listener) {
        this.mcontext = mcontext;
        this.mlist = mlist;
        movieItemClickListener = listener;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(mcontext).inflate(R.layout.item_category,viewGroup,false);
        return new MyViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        myViewHolder.TvTitle.setText(mlist.get(i).getTitle());
        myViewHolder.item_movie_desc.setText(mlist.get(i).getDescription());
        Glide.with(mcontext).load(mlist.get(i).getThumbs()).into(myViewHolder.ImgMovie);
        Glide.with(mcontext).load(mlist.get(i).getThumbs()).into(myViewHolder.channelLogo);



    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        private TextView TvTitle, item_movie_desc;
        private ImageView ImgMovie ;
        private CircularImageView channelLogo;


        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            TvTitle = itemView.findViewById(R.id.item_movie_title);
            ImgMovie = itemView.findViewById(R.id.item_movie_img);
            item_movie_desc = itemView.findViewById(R.id.item_movie_desc);
            channelLogo = itemView.findViewById(R.id.channelLogo);
            item_movie_desc = itemView.findViewById(R.id.item_movie_desc);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    movieItemClickListener.onMovieClick(mlist.get(getAdapterPosition()),ImgMovie);
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
