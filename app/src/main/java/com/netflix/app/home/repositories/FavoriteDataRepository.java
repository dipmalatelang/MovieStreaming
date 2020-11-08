package com.netflix.app.home.repositories;

import androidx.lifecycle.MutableLiveData;

import com.netflix.app.home.model.FavItem;

import java.util.List;

public class FavoriteDataRepository {
    private static FavoriteDataRepository instance;

    public static FavoriteDataRepository getInstance(){

        if (instance == null){
            instance = new FavoriteDataRepository();
        }
        return instance;
    }
    public MutableLiveData<List<FavItem>> getFavItemData(){
        MutableLiveData<List<FavItem>> data = new MutableLiveData<>();
//        data.setValue();
        return data;
    }
}
