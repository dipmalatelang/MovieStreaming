package com.netflix.app.home.ui;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.netflix.app.R;
import com.netflix.app.databinding.FragmentMoreBinding;
import com.netflix.app.home.adapter.MoreAdapter;
import com.netflix.app.home.adapter.MovieItemClickListener;
import com.netflix.app.home.model.AllVideo;
import com.netflix.app.home.model.CategoryItem;
import com.netflix.app.utlis.DataSources;


public class MoreFragment extends Fragment implements MovieItemClickListener {
    private FragmentMoreBinding binding;
    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_more,container,false);
        view =binding.getRoot();
        /* ToDo for more like recycler view Data */
        iniFavorites();

        return view;
    }
    private void iniFavorites(){
        MoreAdapter moreAdapter = new MoreAdapter(getContext(), DataSources.getmovie(), MoreFragment.this);
        binding.recyclerviewMore.setAdapter(moreAdapter);
        binding.recyclerviewMore.setLayoutManager(new GridLayoutManager(getContext(),3,GridLayoutManager.VERTICAL,false));
    }


    @Override
    public void onMovieClick(AllVideo movie, ImageView movieImageView) {

    }
}
