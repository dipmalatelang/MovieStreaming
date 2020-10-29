package com.netflix.app.utlis;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.netflix.app.R;
import com.netflix.app.home.adapter.MovieAdapter;
import com.netflix.app.home.model.MovieData;

import java.util.ArrayList;
import java.util.List;

public class DataSources {
    public static List<MovieData> getmovie() {
        List<MovieData> lstMovies = new ArrayList<>();
        lstMovies.add(new MovieData("Nutcracker", R.drawable.mov2));
        lstMovies.add(new MovieData("Moana", R.drawable.moana, R.drawable.spidercover));
        lstMovies.add(new MovieData("The Martian", R.drawable.themartian));
        lstMovies.add(new MovieData("Black Panther", R.drawable.blackp, R.drawable.spidercover));
        lstMovies.add(new MovieData("The Martian", R.drawable.themartian));
        lstMovies.add(new MovieData("The Martian", R.drawable.themartian));
return lstMovies;

    }
}
