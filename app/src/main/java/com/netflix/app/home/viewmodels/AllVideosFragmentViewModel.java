package com.netflix.app.home.viewmodels;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.netflix.app.home.model.AllDataPojo;
import com.netflix.app.home.repositories.AllVideoRepository;

import java.util.List;

public class AllVideosFragmentViewModel extends ViewModel {

    private MutableLiveData<List<AllDataPojo>> mallvideoPojo;

    public  void init(){

        if(mallvideoPojo !=null){
            return;
        }
        AllVideoRepository mRepo = AllVideoRepository.getInstance();
        mallvideoPojo = mRepo.getAllVideoDataImage();
    }


    public LiveData<List<AllDataPojo>> getAllData(){
        return  mallvideoPojo;
    }
}
