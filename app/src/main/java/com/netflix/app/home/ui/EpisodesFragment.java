package com.netflix.app.home.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.netflix.app.R;
import com.netflix.app.databinding.FragmentEpisodesBinding;
import com.netflix.app.home.adapter.EpoItemClickListener;
import com.netflix.app.home.adapter.EpisodeAdapter;
import com.netflix.app.home.model.AllDataPojo;
import com.netflix.app.home.model.AllDataPojo.Ep;
import com.netflix.app.home.viewmodels.AllVideosFragmentViewModel;
import com.netflix.app.utlis.SharedPrefs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static android.content.ContentValues.TAG;
import static com.netflix.app.home.adapter.SliderPagerAdapter.VIDEO_BANNER;
import static com.netflix.app.home.adapter.SliderPagerAdapter.VIDEO_BANNER_Name;
import static com.netflix.app.home.ui.MovieDetailActivity.VIDEO_channelID;


public class EpisodesFragment extends Fragment implements EpoItemClickListener {
    private FragmentEpisodesBinding binding;
    private View view;
    String EPI_NAME = "VideoURlName";
    private int CHANNEL_ID;
    private AllVideosFragmentViewModel mallVideosFragmentViewModel;
    String name;
    int channelid;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_episodes, container, false);
        view = binding.getRoot();
        EPI_NAME = SharedPrefs.getInstance().getString(VIDEO_BANNER_Name, name);
        CHANNEL_ID = SharedPrefs.getInstance().getInt(VIDEO_channelID, channelid);
        mallVideosFragmentViewModel = ViewModelProviders.of(this).get(AllVideosFragmentViewModel.class);
        mallVideosFragmentViewModel.init();

        mallVideosFragmentViewModel.getAllData().observe(this, new Observer<List<AllDataPojo>>() {
            @Override
            public void onChanged(List<AllDataPojo> allCategoryList) {

                if (allCategoryList != null) {
                    ArrayList<AllDataPojo> werbseries = new ArrayList<>();
                    ArrayList<Ep> allWerbseries = new ArrayList<>();
                    ArrayList<AllDataPojo> spinnerlist = new ArrayList<>();
                    List<String> titleEpSize = new ArrayList<>();

                    ArrayList<AllDataPojo> alvdo = new ArrayList<>(mallVideosFragmentViewModel.getAllData().getValue());
                    ArrayList<AllDataPojo> allV = new ArrayList<>();
                    for (int i = alvdo.size() - 1; i >= 0; i--) {
                        allV.add(alvdo.get(i));
                    }
                    Iterator<AllDataPojo> it = allV.iterator();
                    while (it.hasNext()) {
                        AllDataPojo al = it.next();

                        if (al.getVideoType().equalsIgnoreCase("WEBSERIES")) {

                            if (al.getTitle().equalsIgnoreCase(EPI_NAME)) {
                                werbseries.add(al);

                                allWerbseries.addAll(al.getEps());
                                Log.d(TAG, "Episode: " + allWerbseries.size());
                                if (werbseries.size() == 0) {
                                    binding.recyclerviewSeason.setVisibility(View.GONE);
                                } else {
                                    binding.recyclerviewSeason.setVisibility(View.VISIBLE);
                                }
                            }


                            if (al.getChannelId().toString().equalsIgnoreCase(String.valueOf(CHANNEL_ID))) {
                                spinnerlist.add(al);
                                titleEpSize.add(al.getTitle() + " -" + al.getEps().size());
                                Log.i(TAG, "onChangedchannelid: " + titleEpSize.add(al.getTitle() + " -" + al.getEps().size()));

                            }
                        }

                        Log.i(TAG, "onChanged: " + spinnerlist.size());

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line, titleEpSize);
                        binding.spinner.setAdapter(adapter); // this will set list of values to spinner
                        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                String selectedTitle = titleEpSize.get(position).trim();
                                String[] vals = selectedTitle.split(" -");
                                for (int i = 0; i < spinnerlist.size(); i++) {
                                    if (spinnerlist.get(i).getTitle().equalsIgnoreCase(vals[0]) && (al.getTitle().equalsIgnoreCase(EPI_NAME))) {
                                        Log.i(TAG, "onItemSelected: " + vals[0]);
                                        EpisodeAdapter episodeAdapter = new EpisodeAdapter(getContext(), allWerbseries, EpisodesFragment.this);
                                        binding.recyclerviewSeason.setAdapter(episodeAdapter);
                                        binding.recyclerviewSeason.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

                                    }
                                }

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {

                            }
                        });

                    }

                    EpisodeAdapter episodeAdapter = new EpisodeAdapter(getContext(), allWerbseries, EpisodesFragment.this);
                    binding.recyclerviewSeason.setAdapter(episodeAdapter);
                    binding.recyclerviewSeason.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));


                } else {
                    Toast.makeText(getContext(), "Data Not found", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;
    }




    @Override
    public void onMovieClick(Ep movie, ImageView movieImageView) {
        String bannerurl =movie.getVdoUrl();
        String  bannername = movie.getTitle();
        SharedPrefs.getInstance().addString(VIDEO_BANNER, bannerurl);
        SharedPrefs.getInstance().addString(VIDEO_BANNER_Name,bannername);
        startActivity(new Intent(getContext(), PlayMovieActivity.class));
    }
}
