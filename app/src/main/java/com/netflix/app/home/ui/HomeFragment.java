package com.netflix.app.home.ui;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;


import android.os.Bundle;


import androidx.fragment.app.Fragment;


import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;




import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;



import com.google.android.material.tabs.TabLayout;
import com.netflix.app.R;
import com.netflix.app.home.adapter.MainRecyclerAdapter;
import com.netflix.app.home.adapter.MovieAdapter;
import com.netflix.app.home.adapter.MovieItemClickListener;
import com.netflix.app.home.adapter.SlidePojo;
import com.netflix.app.home.adapter.SliderPagerAdapter;
import com.netflix.app.home.model.AllCategory;
import com.netflix.app.home.model.CategoryItem;
import com.netflix.app.home.model.MovieData;

import com.netflix.app.utlis.ApiCall;
import com.netflix.app.utlis.ApiInterface;
import com.netflix.app.utlis.DataSources;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.ContentValues.TAG;


public class HomeFragment extends Fragment implements MovieItemClickListener {
    private List<SlidePojo> Imgslide;
    MainRecyclerAdapter mainRecyclerAdapter;
    RecyclerView mainCategoryRecycler;
    private ViewPager sliderpager;
    private TabLayout indicator;
    private RecyclerView movie_recyclerview ;
    private ProgressBar progressBar;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.h_fragment_home_, container, false);

        iniViews(view);
        iniSlider();
        getSliderData();
//        InMovies();


//        Tv_seeall.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Fragment someFragment = new HomeVideoPlay_Fragment();
//                FragmentTransaction transaction = getFragmentManager().beginTransaction();
//                transaction.replace(R.id.rl_fragment_container, someFragment ); // give your fragment container id in first parameter
//                transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
//                transaction.commit();
//            }
//        });

        return  view;
    }

//    private void InMovies() {
//        MovieAdapter movieAdapter = new MovieAdapter(getContext(), DataSources.getmovie(),HomeFragment.this);
//        movie_recyclerview.setAdapter(movieAdapter);
//        movie_recyclerview.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));
//    }



    private void iniViews(View view) {
        sliderpager = view.findViewById(R.id.sliderpager);
        indicator = view.findViewById(R.id.indicator);
//        movie_recyclerview = view.findViewById(R.id.movie_recyclerview);
//        Tv_seeall = view.findViewById(R.id.Tv_seeall);
        mainCategoryRecycler = view.findViewById(R.id.main_recycler);
        progressBar =view.findViewById(R.id.progressBar);


        List<CategoryItem> categoryItemList = new ArrayList<>();
        categoryItemList.add(new CategoryItem(1, R.drawable.hollywood5));
        categoryItemList.add(new CategoryItem(1, R.drawable.hollywood4));
        categoryItemList.add(new CategoryItem(1, R.drawable.hollywood5));
        categoryItemList.add(new CategoryItem(1, R.drawable.hollywood4));
        categoryItemList.add(new CategoryItem(1, R.drawable.hollywood5));
        categoryItemList.add(new CategoryItem(1, R.drawable.hollywood4));

        // added in second category
        List<CategoryItem> categoryItemList2 = new ArrayList<>();
        categoryItemList2.add(new CategoryItem(1, R.drawable.bestofoscar1));
        categoryItemList2.add(new CategoryItem(1, R.drawable.bestofoscar2));
        categoryItemList2.add(new CategoryItem(1, R.drawable.bestofoscar1));
        categoryItemList2.add(new CategoryItem(1, R.drawable.bestofoscar2));
        categoryItemList2.add(new CategoryItem(1, R.drawable.bestofoscar1));
        categoryItemList2.add(new CategoryItem(1, R.drawable.bestofoscar2));

        // added in 3rd category
        List<CategoryItem> categoryItemList3 = new ArrayList<>();
        categoryItemList3.add(new CategoryItem(1, R.drawable.moviedubbedinhindi1));
        categoryItemList3.add(new CategoryItem(1, R.drawable.moviedubbedinhindi2));
        categoryItemList3.add(new CategoryItem(1, R.drawable.moviedubbedinhindi1));
        categoryItemList3.add(new CategoryItem(1, R.drawable.moviedubbedinhindi2));
        categoryItemList3.add(new CategoryItem(1, R.drawable.moviedubbedinhindi1));
        categoryItemList3.add(new CategoryItem(1, R.drawable.moviedubbedinhindi2));
        List<AllCategory> allCategoryList = new ArrayList<>();
        allCategoryList.add(new AllCategory("Hollywood", categoryItemList));
        allCategoryList.add(new AllCategory("Best of Oscars", categoryItemList2));
        allCategoryList.add(new AllCategory("Movies Dubbed in Hindi", categoryItemList3));


        setMainCategoryRecycler(allCategoryList);
    }

    private void setMainCategoryRecycler(List<AllCategory> allCategoryList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        mainCategoryRecycler.setLayoutManager(layoutManager);
        mainRecyclerAdapter = new MainRecyclerAdapter(getContext(), allCategoryList,HomeFragment.this);
        mainCategoryRecycler.setAdapter(mainRecyclerAdapter);
    }






    public void onMovieClick(CategoryItem movie, ImageView movieImageView) {


        Intent intent = new Intent(getContext(),MovieDetailActivity.class);
        // send movie information to deatilActivity
        intent.putExtra("title",movie.getItemId());
        intent.putExtra("imgURL",movie.getImageUrl());
//        intent.putExtra("imgCover",movie.getCoverPhoto());
        // lets crezte the animation
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation((Activity) getContext(),
                movieImageView,"sharedName");

        startActivity(intent,options.toBundle());



        // i l make a simple test to see if the click works

        Toast.makeText(getContext(),"item clicked : " + movie.getItemId(),Toast.LENGTH_LONG).show();
        // it works great


    }

    //getSliderDataAPiforslideimage
    public void getSliderData() {
        String slider = "slide";
        ApiInterface apiInterface = ApiCall.getApiData().create(ApiInterface.class);
        Call<List<SlidePojo>> call = apiInterface.getSlideData(slider);
        call.enqueue(new Callback<List<SlidePojo>>() {
            @Override
            public void onResponse(Call<List<SlidePojo>> call, Response<List<SlidePojo>> response) {
                if (response.code() ==200){
                    for(int i=0;i<response.body().size();i++)
                    {
                        Log.d(TAG, "onResponsesizeofjsondata: "+response.body().size());
                        Imgslide =response.body();
                        SliderPagerAdapter adapter = new SliderPagerAdapter(getContext(),Imgslide);
                        sliderpager.setAdapter(adapter);
                        progressBar.setVisibility(View.GONE);


                    }
                }
            }

            @Override
            public void onFailure(Call<List<SlidePojo>> call, Throwable t) {

            }
        });


    }
    private void iniSlider() {

        // setup timer
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(),4000,6000);
        indicator.setupWithViewPager(sliderpager,true);
    }




    class SliderTimer extends TimerTask {

        @Override
        public void run() {
            if (getActivity() != null) {
                getActivity().runOnUiThread(new Runnable(){
                    @Override
                    public void run(){
                        if (sliderpager.getCurrentItem()<Imgslide.size()-1) {
                            sliderpager.setCurrentItem(sliderpager.getCurrentItem()+1);
                        }
                        else
                            sliderpager.setCurrentItem(0);
                    }
                });

            }



        }
    }

}
