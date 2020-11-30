package com.netflix.app.home.ui;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.netflix.app.R;


import com.netflix.app.home.adapter.CatMusicVideoAdapter;
import com.netflix.app.home.adapter.CatSingleVideoAdapter;
import com.netflix.app.home.adapter.CatWebseriesAdapter;
import com.netflix.app.home.adapter.MovieItemClickListener;
import com.netflix.app.home.model.AllDataPojo;
import com.netflix.app.home.adapter.SliderPagerAdapter;
import com.netflix.app.home.viewmodels.AllVideosFragmentViewModel;
import com.netflix.app.home.viewmodels.HomeFragmentViewModel;

import com.netflix.app.upcoming.Upcoming_Activity;
import com.netflix.app.utlis.SharedPrefs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;




public class HomeFragment extends Fragment implements MovieItemClickListener {

    private HomeFragmentViewModel mhomeFragmentViewModel;
    private AllVideosFragmentViewModel mallVideosFragmentViewModel;
    private ViewPager sliderpager;
    private RecyclerView main_recycler,rec_singlevideo, rec_music;
    private TabLayout indicator;
    private TextView textView_WEBSERIES,textView_SINGLEVIDEO,textView_Music;
    private ProgressBar progressBar;
    private Toolbar toolbar;
    private Button btn_singlevideo,btn_webseries, btn_music;
    public static String GETVIDEOTYPE = "videoType";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Assign variable
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.h_fragment_home_, container, false);
        iniViews(view);
        initToolbar();

        // Assign variable mhomeFragmentViewModel for HomeFragmentViewModel
        mhomeFragmentViewModel = ViewModelProviders.of(this).get(HomeFragmentViewModel.class);
        mallVideosFragmentViewModel = ViewModelProviders.of(this).get(AllVideosFragmentViewModel.class);
        // init Retrive data from Repository SlideDataRepository
        mhomeFragmentViewModel.init();
        mallVideosFragmentViewModel.init();
        // observe the changes  getSlideData
        mhomeFragmentViewModel.getSlideData().observe(this, new Observer<List<AllDataPojo>>() {
            @Override
            public void onChanged(List<AllDataPojo> allVideos) {
//                Log.d(TAG, "onChanged: " + allVideos.size());
                if (allVideos != null) {
                    SliderPagerAdapter sliderPagerAdapter = new SliderPagerAdapter(getContext(), mhomeFragmentViewModel.getSlideData().getValue());

                    sliderpager.setAdapter(sliderPagerAdapter);
                    progressBar.setVisibility(View.GONE);
                } else {
                    Toast.makeText(getContext(), "Data not found", Toast.LENGTH_SHORT).show();
                }

            }
        });

        mallVideosFragmentViewModel.getAllData().observe(this, new Observer<List<AllDataPojo>>() {
            @Override
            public void onChanged(List<AllDataPojo> allCategoryList) {

                if(allCategoryList !=null){
                    ArrayList<AllDataPojo> werbseries = new ArrayList<>();
                    ArrayList<AllDataPojo> singlevideolist = new ArrayList<>();
                    ArrayList<AllDataPojo> sortlist = new ArrayList<>();
                    ArrayList<AllDataPojo> alvdo = new ArrayList<>(mallVideosFragmentViewModel.getAllData().getValue());
                    ArrayList<AllDataPojo> allV = new ArrayList<>();
                    for (int i = alvdo.size() - 1; i >= 0; i--) {
                        allV.add(alvdo.get(i));
                    }
                    Iterator<AllDataPojo> it = allV.iterator();
                    while (it.hasNext()) {
                        AllDataPojo al = it.next();
                        if (al.getVideoType().equalsIgnoreCase("WEBSERIES")) {
                            werbseries.add(al);

                        if (werbseries.size() == 0) {
                            textView_WEBSERIES.setVisibility(View.GONE);
                            btn_webseries.setVisibility(View.GONE);
                            main_recycler.setVisibility(View.GONE);
                        } else {

                            textView_WEBSERIES.setVisibility(View.VISIBLE);
                            btn_webseries.setVisibility(View.VISIBLE);
                            main_recycler.setVisibility(View.VISIBLE);
                        }
                        }

                        else if(al.getVideoType().equalsIgnoreCase("SINGLEVIDEO"))
                        {
                            singlevideolist.add(al);
                        if (werbseries.size() == 0) {
                            btn_singlevideo.setVisibility(View.GONE);
                            textView_SINGLEVIDEO.setVisibility(View.GONE);
                            rec_singlevideo.setVisibility(View.GONE);
                        } else {
                            btn_singlevideo.setVisibility(View.VISIBLE);
                            textView_SINGLEVIDEO.setVisibility(View.VISIBLE);
                            rec_singlevideo.setVisibility(View.VISIBLE);
                        }
                    }

                        else if(al.getVideoType().equalsIgnoreCase("SORTMOVIE"))
                        {
                            sortlist.add(al);
                            if (werbseries.size() == 0) {
                                rec_music.setVisibility(View.GONE);
                                textView_Music.setVisibility(View.GONE);
                                btn_music.setVisibility(View.GONE);
                            } else {
                                rec_music.setVisibility(View.VISIBLE);
                                textView_Music.setVisibility(View.VISIBLE);
                                btn_music.setVisibility(View.VISIBLE);
                            }
                        }

                    }

                    CatWebseriesAdapter upcomingAdapter = new CatWebseriesAdapter(getContext(), werbseries,HomeFragment.this);
                    main_recycler.setAdapter(upcomingAdapter);
                    main_recycler.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

                    CatSingleVideoAdapter singleVideoAdapter = new CatSingleVideoAdapter(getContext(), singlevideolist,HomeFragment.this );
                    rec_singlevideo.setAdapter(singleVideoAdapter);
                    rec_singlevideo.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

                    CatMusicVideoAdapter catMusicVideoAdapter = new CatMusicVideoAdapter(getContext(), sortlist,HomeFragment.this );
                    rec_music.setAdapter(catMusicVideoAdapter);
                    rec_music.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));



                }
                else {
                    Toast.makeText(getContext(), "Data Not found", Toast.LENGTH_SHORT).show();
                }

            }
        });
        btn_webseries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment someFragment = new HomeWebseries_Fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.rl_fragment_container, someFragment ); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();

            }
        });
        btn_singlevideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment someFragment = new HomeSinglevideo_Fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.rl_fragment_container, someFragment ); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();

            }
        });
        btn_music.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment someFragment = new HomeSortmovies_Fragment();
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.rl_fragment_container, someFragment ); // give your fragment container id in first parameter
                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                transaction.commit();

            }
        });


        iniSlider();


        return view;
    }

    private void initToolbar() {
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);

    }

    private void iniViews(View view) {
        toolbar = getActivity().findViewById(R.id.toolbar);
        sliderpager = view.findViewById(R.id.sliderpager);
        indicator = view.findViewById(R.id.indicator);
        progressBar = view.findViewById(R.id.progressBar);
        main_recycler = view.findViewById(R.id.main_recycler);
        rec_singlevideo = view.findViewById(R.id.rec_singlevideo);
        btn_singlevideo = view.findViewById(R.id.btn_singlevideo);
        rec_music =view.findViewById(R.id.rec_music);
        btn_webseries = view.findViewById(R.id.btn_webseries);
        textView_WEBSERIES =view.findViewById(R.id.textView_WEBSERIES);
        textView_SINGLEVIDEO = view.findViewById(R.id.textView_SINGLEVIDEO);
        textView_Music =view.findViewById(R.id.textView_Music);
        btn_music =view.findViewById(R.id.btn_music);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.top_bar_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);

        SearchView searchView = new SearchView(((AppCompatActivity) getContext()).getSupportActionBar().getThemedContext());
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW | MenuItem.SHOW_AS_ACTION_IF_ROOM);
        item.setActionView(searchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {


                return true;

            }
        });
        searchView.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {

                                          }
                                      }
        );


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.t_upcoming:
                startActivity(new Intent(getContext(), Upcoming_Activity.class));
                Toast.makeText(getContext(), "click upcoming", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }





    /* ToDo iniSlider setTimer for slide */
    private void iniSlider() {

        // setup timer
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(), 4000, 6000);
        indicator.setupWithViewPager(sliderpager, true);
    }

    @Override
    public void onMovieClick(AllDataPojo video, ImageView movieImageView) {

        String cast = new String();
        for (int i = 0; i < video.getCasts().size(); i++) {
            cast = cast + video.getCasts().get(i).getName() + ",";
            System.out.println("cast" + video.getCasts().size());
        }
        String dir = new String();
        for (int i2 = 0; i2 < video.getDirectors().size(); i2++) {
            dir = dir + video.getDirectors().get(i2).getDirName() + ",";
            System.out.println("cast" + video.getDirectors().size());
        }
        String gener = new String();
        for (int i3 = 0; i3 < video.getGenres().size(); i3++) {
            gener = gener + video.getGenres().get(i3).getGenreName() + ",";
            System.out.println("cast" + video.getGenres().size());
        }

        Intent intent = new Intent(getContext(), MovieDetailActivity.class);
        // send movie information to deatilActivity
        intent.putExtra("title", video.getTitle());
        intent.putExtra("imgURL", video.getThumbs());
        intent.putExtra("imgDescription", video.getDescription());
        intent.putExtra("Cast", cast);
        intent.putExtra("Directors", dir);
        intent.putExtra("channelID", video.getChannelId());
        intent.putExtra("Geners", gener);
        intent.putExtra("videourl", video.getVdoUrl());


        Log.d("TAG", "onMovieClickclick: " + video.getTitle());

        // lets crezte the animation
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) getContext(),
                movieImageView, "sharedName");

        startActivity(intent, options.toBundle());
        // i l make a simple test to see if the click works
        Toast.makeText(getContext(), "item clicked : " + video.getChannelId(), Toast.LENGTH_LONG).show();

        SharedPrefs.getInstance().addString(GETVIDEOTYPE, video.getVideoType());
        Log.i("TAG", "VVVVVVVVVVVVVVVVVVVVVHome: "+video.getVideoType());
        // it works great


    }

    class SliderTimer extends TimerTask {

        @Override
        public void run() {
            if (getActivity() != null) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            if (sliderpager.getCurrentItem() < mhomeFragmentViewModel.getSlideData().getValue().size() - 1) {
                                sliderpager.setCurrentItem(sliderpager.getCurrentItem() + 1);
                            } else {
                                sliderpager.setCurrentItem(0);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

            }


        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}
