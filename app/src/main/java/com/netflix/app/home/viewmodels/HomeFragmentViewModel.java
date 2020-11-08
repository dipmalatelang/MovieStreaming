package com.netflix.app.home.viewmodels;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.netflix.app.home.model.SlidePojo;
import com.netflix.app.home.repositories.SlideDataRepository;

import java.util.List;

public class HomeFragmentViewModel extends ViewModel {

    private MutableLiveData<List<SlidePojo>> mSlidePojo;

    public  void init(String slider){

        if(mSlidePojo !=null){
            return;
        }
        SlideDataRepository mRepo = SlideDataRepository.getInstance();
        mSlidePojo = mRepo.getSlideDataImage(slider);
    }


    public LiveData<List<SlidePojo>> getSlideData(){
        return  mSlidePojo;
    }
}
