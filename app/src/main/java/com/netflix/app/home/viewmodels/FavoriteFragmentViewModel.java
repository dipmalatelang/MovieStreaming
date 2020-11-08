package com.netflix.app.home.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.netflix.app.home.model.FavItem;
import com.netflix.app.home.repositories.FavoriteDataRepository;

import java.util.List;

public class FavoriteFragmentViewModel extends ViewModel {

    private MutableLiveData<List<FavItem>> mFavItemList;

    public void init(){
        if(mFavItemList != null){
            return;
        }
        FavoriteDataRepository mRepo = FavoriteDataRepository.getInstance();
        mFavItemList = mRepo.getFavItemData();
    }
    public LiveData<List<FavItem>> getFavData(){
        return mFavItemList;
    }
}
