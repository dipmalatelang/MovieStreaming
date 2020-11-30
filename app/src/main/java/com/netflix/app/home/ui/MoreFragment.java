package com.netflix.app.home.ui;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.netflix.app.R;
import com.netflix.app.databinding.FragmentMoreBinding;
import com.netflix.app.home.adapter.CatWebseriesAdapter;
import com.netflix.app.home.adapter.MoreAdapter;
import com.netflix.app.home.adapter.MovieItemClickListener;
import com.netflix.app.home.model.AllDataPojo;
import com.netflix.app.home.viewmodels.AllVideosFragmentViewModel;
import com.netflix.app.utlis.SharedPrefs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;



import static com.netflix.app.home.ui.HomeFragment.GETVIDEOTYPE;


public class MoreFragment extends Fragment implements MovieItemClickListener {
    private FragmentMoreBinding binding;
    private View view;

    private AllVideosFragmentViewModel mallVideosFragmentViewModel;



    public static String MORE_VIDEOTYPE = "VideoType";
    String videotype;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_more, container, false);
        view = binding.getRoot();
        /* ToDo for more like recycler view Data */

        MORE_VIDEOTYPE = SharedPrefs.getInstance().getString(GETVIDEOTYPE, videotype);
        Log.i("", "mmmmmmmmmmmmmvvvvvvvvvvvvvtttttttt: "+MORE_VIDEOTYPE);
        mallVideosFragmentViewModel = ViewModelProviders.of(this).get(AllVideosFragmentViewModel.class);
        mallVideosFragmentViewModel.init();

        mallVideosFragmentViewModel.getAllData().observe(this, new Observer<List<AllDataPojo>>() {
            @Override
            public void onChanged(List<AllDataPojo> allCategoryList) {

                if (allCategoryList != null) {
                    ArrayList<AllDataPojo> morelikelist = new ArrayList<>();
                    ArrayList<AllDataPojo> alvdo = new ArrayList<>(mallVideosFragmentViewModel.getAllData().getValue());
                    ArrayList<AllDataPojo> allV = new ArrayList<>();
                    for (int i = alvdo.size() - 1; i >= 0; i--) {
                        allV.add(alvdo.get(i));
                    }
                    Iterator<AllDataPojo> it = allV.iterator();
                    while (it.hasNext()) {
                        AllDataPojo al = it.next();
                        if (al.getVideoType().equalsIgnoreCase(MORE_VIDEOTYPE)) {
                            morelikelist.add(al);

                        }
                        }

                    MoreAdapter moreAdapter = new MoreAdapter(getContext(), morelikelist,MoreFragment.this);
                    binding.recyclerviewMore.setAdapter(moreAdapter);
                    binding.recyclerviewMore.setLayoutManager(new GridLayoutManager(getContext(), 3, GridLayoutManager.VERTICAL, false));


                } else {
                    Toast.makeText(getContext(), "Data Not found", Toast.LENGTH_SHORT).show();
                }

            }
        });


        return view;
    }



    @Override
    public void onMovieClick(AllDataPojo movie, ImageView movieImageView) {

    }
}
