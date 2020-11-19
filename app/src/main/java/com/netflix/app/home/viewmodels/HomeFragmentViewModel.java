package com.netflix.app.home.viewmodels;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.netflix.app.home.model.AllVideo;
import com.netflix.app.home.repositories.SlideDataRepository;

import java.util.List;

public class HomeFragmentViewModel extends ViewModel {

    private MutableLiveData<List<AllVideo>> mSlidePojo;

    public  void init(){

        if(mSlidePojo !=null){
            return;
        }
        SlideDataRepository mRepo = SlideDataRepository.getInstance();
        mSlidePojo = mRepo.getSlideDataImage();
    }


    public LiveData<List<AllVideo>> getSlideData(){
        return  mSlidePojo;
    }
}
