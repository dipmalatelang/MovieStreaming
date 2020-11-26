package com.netflix.app.home.repositories;

import androidx.lifecycle.MutableLiveData;

import com.netflix.app.home.model.AllDataPojo;

import com.netflix.app.networks.Api;
import com.netflix.app.networks.ApiClient;


import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SlideDataRepository {

    private static SlideDataRepository instance;
    Api api;


    public static SlideDataRepository getInstance() {
        if (instance == null) {
            instance = new SlideDataRepository();
        }
        return instance;
    }

    private SlideDataRepository() {
        api = ApiClient.createApiCall();
    }

    public MutableLiveData<List<AllDataPojo>> getSlideDataImage() {
        MutableLiveData<List<AllDataPojo>> data = new MutableLiveData<>();
        api.getBanner().enqueue(new Callback<List<AllDataPojo>>() {
            @Override
            public void onResponse(Call<List<AllDataPojo>> call, Response<List<AllDataPojo>> response) {
                if (response.code() == 200) {
                    data.postValue(response.body());
                } else {
                    data.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<AllDataPojo>> call, Throwable t) {
                data.postValue(null);
            }
        });
        return data;
    }


}
