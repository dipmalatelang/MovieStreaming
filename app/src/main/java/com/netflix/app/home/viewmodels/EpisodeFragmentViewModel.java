package com.netflix.app.home.viewmodels;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.netflix.app.home.model.AllDataPojo.Ep;
import com.netflix.app.home.repositories.AllVideoRepository;
import com.netflix.app.home.repositories.EpisodeVideoRepository;

import java.util.List;

public class EpisodeFragmentViewModel extends ViewModel {

    private MutableLiveData<List<Ep>> mallvideoPojo;

    public  void init(){

        if(mallvideoPojo !=null){
            return;
        }
        EpisodeVideoRepository mRepo = EpisodeVideoRepository.getInstance();
        mallvideoPojo = mRepo.getEpisode();
    }


    public LiveData<List<Ep>> getAllEpisodeData(){
        return  mallvideoPojo;
    }
}
