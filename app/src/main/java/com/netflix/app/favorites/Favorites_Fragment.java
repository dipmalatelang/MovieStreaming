package com.netflix.app.favorites;


import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.netflix.app.R;
import com.netflix.app.databinding.HFavoriteFragmentBinding;
import com.netflix.app.home.adapter.FavAdapter;
import com.netflix.app.home.adapter.MovieItemClickListener;
import com.netflix.app.home.model.CategoryItem;
import com.netflix.app.home.viewmodels.FavoriteFragmentViewModel;
import com.netflix.app.utlis.FavDB;
import com.netflix.app.home.model.FavItem;

import java.util.ArrayList;
import java.util.List;

public class Favorites_Fragment extends Fragment implements MovieItemClickListener {
    private List<FavItem> favItemList = new ArrayList<>();
    private FavAdapter favAdapter;
    private FavDB favDB;
    private HFavoriteFragmentBinding binding;
    private View view;

    private FavoriteFragmentViewModel mfavoriteFragmentViewModel;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater,R.layout.h_favorite_fragment,container,false);
        view = binding.getRoot();

        mfavoriteFragmentViewModel = ViewModelProviders.of(this).get(FavoriteFragmentViewModel.class);
        // init Retrive data from Repository SlideDataRepository
        mfavoriteFragmentViewModel.init();
        mfavoriteFragmentViewModel.getFavData().observe(this, new Observer<List<FavItem>>() {
            @Override
            public void onChanged(List<FavItem> favItems) {

            }
        });

        favDB = new FavDB(getContext());
        binding.favoriteRecyclerview.setHasFixedSize(true);
        binding.favoriteRecyclerview.setLayoutManager(new GridLayoutManager(getContext(),3,GridLayoutManager.VERTICAL,false));

        /* ToDo loadData for favorites */
        loadData();

        /* ToDo add item touch helper */
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(binding.favoriteRecyclerview); // set swipe to recyclerview
        return view;
    }




    private void loadData() {
        if (favItemList != null) {
            favItemList.clear();
        }
        SQLiteDatabase db = favDB.getReadableDatabase();
        Cursor cursor = favDB.select_all_favorite_list();
        try {
            while (cursor.moveToNext()) {
//                String title = cursor.getString(cursor.getColumnIndex(FavDB.ITEM_TITLE));
                String id = cursor.getString(cursor.getColumnIndex(FavDB.KEY_ID));
                String image = cursor.getString(cursor.getColumnIndex(FavDB.ITEM_IMAGE));
                FavItem favItem = new FavItem("Movie name", id, image);
                Toast.makeText(getContext(), ""+image, Toast.LENGTH_SHORT).show();
                favItemList.add(favItem);
            }
        } finally {
            if (cursor != null && cursor.isClosed())
                cursor.close();
            db.close();
        }

        favAdapter = new FavAdapter(getActivity(), favItemList);
        binding.favoriteRecyclerview.setAdapter(favAdapter);

    }

    // remove item after swipe
    private ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            final int position = viewHolder.getAdapterPosition(); // get position which is swipe
            final FavItem favItem = favItemList.get(position);
            if (direction == ItemTouchHelper.LEFT) { //if swipe left
                favAdapter.notifyItemRemoved(position); // item removed from recyclerview
                favItemList.remove(position); //then remove item
                favDB.remove_fav(favItem.getKey_id()); // remove item from database
            }
        }
    };


    @Override
    public void onMovieClick(CategoryItem movie, ImageView movieImageView) {

    }
}

