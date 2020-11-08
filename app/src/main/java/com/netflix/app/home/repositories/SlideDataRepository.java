package com.netflix.app.home.repositories;

import androidx.lifecycle.MutableLiveData;

import com.netflix.app.home.model.SlidePojo;
import com.netflix.app.networks.ApiClient;
import com.netflix.app.networks.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SlideDataRepository {

    private static SlideDataRepository instance;
    ApiInterface apiInterface;


    public static SlideDataRepository getInstance() {
        if (instance == null) {
            instance = new SlideDataRepository();
        }
        return instance;
    }

    private SlideDataRepository() {
        apiInterface = ApiClient.createApiCall();
    }

    public MutableLiveData<List<SlidePojo>> getSlideDataImage(String slider) {
        MutableLiveData<List<SlidePojo>> data = new MutableLiveData<>();
        apiInterface.getSlideData(slider).enqueue(new Callback<List<SlidePojo>>() {
            @Override
            public void onResponse(Call<List<SlidePojo>> call, Response<List<SlidePojo>> response) {
                if (response.code() == 200) {
                    data.postValue(response.body());
                } else {
                    data.postValue(null);
                }
            }

            @Override
            public void onFailure(Call<List<SlidePojo>> call, Throwable t) {
                data.postValue(null);
            }
        });
        return data;
    }


}
