package com.netflix.app.home.viewmodels;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.netflix.app.home.model.AllVideo;
import com.netflix.app.home.repositories.AllVideoRepository;
import com.netflix.app.home.repositories.SlideDataRepository;

import java.util.List;

public class AllVideosFragmentViewModel extends ViewModel {

    private MutableLiveData<List<AllVideo>> mallvideoPojo;

    public  void init(){

        if(mallvideoPojo !=null){
            return;
        }
        AllVideoRepository mRepo = AllVideoRepository.getInstance();
        mallvideoPojo = mRepo.getAllVideoDataImage();
    }


    public LiveData<List<AllVideo>> getAllData(){
        return  mallvideoPojo;
    }
}
