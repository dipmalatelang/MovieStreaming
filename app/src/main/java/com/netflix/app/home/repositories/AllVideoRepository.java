package com.netflix.app.home.repositories;

import androidx.lifecycle.MutableLiveData;

import com.netflix.app.home.model.AllVideo;
import com.netflix.app.networks.Api;
import com.netflix.app.networks.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllVideoRepository {

    private static AllVideoRepository instance;
    Api api;


    public static AllVideoRepository getInstance() {
        if (instance == null) {
            instance = new AllVideoRepository();
        }
        return instance;
    }

    private AllVideoRepository() {
        api = ApiClient.createApiCall();
    }

    public MutableLiveData<List<AllVideo>> getAllVideoDataImage() {
        MutableLiveData<List<AllVideo>> data = new MutableLiveData<>();
        api.getAllVideos().enqueue(new Callback<List<AllVideo>>() {
            @Override
            public void onResponse(Call<List<AllVideo>> call, Response<List<AllVideo>> response) {
                if (response.code() == 200) {
                    data.postValue(response.body());
                } else {
                    data.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<AllVideo>> call, Throwable t) {
                data.postValue(null);
            }
        });
        return data;
    }




}