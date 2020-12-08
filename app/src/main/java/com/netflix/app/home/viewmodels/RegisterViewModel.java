package com.netflix.app.home.viewmodels;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.netflix.app.home.model.UsersResponse;

import com.netflix.app.home.repositories.RegisterRepository;


public class RegisterViewModel extends ViewModel {

    private MutableLiveData<UsersResponse> mallvideoPojo;

    public  void init(){

        if(mallvideoPojo !=null){
            return;
        }
        RegisterRepository mRepo = RegisterRepository.getInstance();
        mallvideoPojo = mRepo.getRegisterData();
    }


    public LiveData<UsersResponse> getUserData(){
        return  mallvideoPojo;
    }
}
