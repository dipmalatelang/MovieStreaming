package com.netflix.app.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.netflix.app.R;
import com.netflix.app.home.model.MovieData;

import java.util.List;

public class SeasonAdapter extends RecyclerView.Adapter<SeasonAdapter.MyViewHolder>{
    Context mcontext;
    List<MovieData> mlist;

    public SeasonAdapter(Context mcontext, List<MovieData> mlist) {
        this.mcontext = mcontext;
        this.mlist = mlist;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mcontext).inflate(R.layout.item_season,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int i) {
        holder.textView_title.setText(mlist.get(i).getTitle());
        holder.item_image.setImageResource(mlist.get(i).getThumbnail());

    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private ImageView item_image;
        private TextView textView_title;
        private TextView textView_desc;


        public MyViewHolder(@NonNull View itemView) {

        super(itemView);
            textView_title = itemView.findViewById(R.id.textView_title);
            textView_desc = itemView.findViewById(R.id.textView_desc);
            item_image = itemView.findViewById(R.id.item_image);
    }
}

}
