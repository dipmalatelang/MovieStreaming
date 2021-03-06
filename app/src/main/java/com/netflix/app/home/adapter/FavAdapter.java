package com.netflix.app.home.adapter;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.netflix.app.R;
import com.netflix.app.utlis.FavDB;
import com.netflix.app.home.model.FavItem;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.ViewHolder> {

    private Context context;
    private List<FavItem> favItemList;
    private FavDB favDB;


    public FavAdapter(Context context, List<FavItem> favItemList) {
        this.context = context;
        this.favItemList = favItemList;
    }

    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fav_item,
                parent, false);
        favDB = new FavDB(context);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NotNull ViewHolder holder, int position) {
        holder.favTextView.setText(favItemList.get(position).getItem_title());
        Log.d("TAG", "onBindViewHoldername: "+favItemList.get(position).getItem_title());
        Glide.with(context).load(favItemList.get(position).getItem_image()).into(holder.favImageView);
    }

    @Override
    public int getItemCount() {
        return favItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView favTextView;
        Button favBtn;
        ImageView favImageView;

        public ViewHolder(@NotNull View itemView) {
            super(itemView);
            favTextView = itemView.findViewById(R.id.favTextView);
            favBtn = itemView.findViewById(R.id.favBtn);
            favImageView = itemView.findViewById(R.id.favImageView);

            //remove from fav after click
            favBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    final FavItem favItem = favItemList.get(position);
                    favDB.remove_fav(favItem.getKey_id());
                    removeItem(position);

                }
            });
        }
    }

    private void removeItem(int position) {
        favItemList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position,favItemList.size());
    }
}
