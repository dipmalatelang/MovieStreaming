package com.netflix.app.home.repositories;

import androidx.lifecycle.MutableLiveData;

import com.netflix.app.home.model.AllDataPojo;
import com.netflix.app.home.model.AllDataPojo.Ep;
import com.netflix.app.networks.Api;
import com.netflix.app.networks.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EpisodeVideoRepository {

    private static EpisodeVideoRepository instance;
    Api api;


    public static EpisodeVideoRepository getInstance() {
        if (instance == null) {
            instance = new EpisodeVideoRepository();
        }
        return instance;
    }

    private EpisodeVideoRepository() {
        api = ApiClient.createApiCall();
    }

    public MutableLiveData<List<Ep>> getEpisode() {
        MutableLiveData<List<Ep>> data = new MutableLiveData<>();
        api.getEpisode().enqueue(new Callback<List<Ep>>() {
            @Override
            public void onResponse(Call<List<Ep>> call, Response<List<Ep>> response) {
                if (response.code() == 200) {
                    data.postValue(response.body());
                } else {
                    data.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<Ep>> call, Throwable t) {
                data.postValue(null);
            }
        });
        return data;
    }




}
