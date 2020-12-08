package com.netflix.app.home.repositories;



import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.netflix.app.home.model.UsersResponse;
import com.netflix.app.networks.Api;
import com.netflix.app.networks.ApiClient;
import com.netflix.app.utlis.RetrofitClient;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterRepository {

    private static RegisterRepository instance;
    Api api;


    public static RegisterRepository getInstance() {
        if (instance == null) {
            instance = new RegisterRepository();
        }
        return instance;
    }

    private RegisterRepository() {
        api = ApiClient.createApiCall();
    }


//    public void getRegisterData () {
//
//        RetrofitClient.getInstance().getApi().createUser().enqueue(new Callback<UsersResponse>() {
//            @Override
//            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
//                if (response.code() == 200) {
//                    Log.d("TAG", "onResponse: "+response.code());
//            } else if (response.code() == 409) {
//                    Log.d("TAG", "onResponse: "+response.code());
//
//              }
//            }
//
//            @Override
//            public void onFailure(Call<UsersResponse> call, Throwable t) {
//
//            }
//        });

    public MutableLiveData<UsersResponse> getRegisterData() {
        MutableLiveData<UsersResponse> data = new MutableLiveData<>();
        api.createUser().enqueue(new Callback<UsersResponse>() {
            @Override
            public void onResponse(Call<UsersResponse> call, Response<UsersResponse> response) {
                if (response.code() == 200) {
                    data.postValue(response.body());
                } else if (response.code() == 409) {
                    data.postValue(response.body());

                }

            }

            @Override
            public void onFailure(Call<UsersResponse> call, Throwable t) {
                data.postValue(null);
            }
        });

        return data;
    }


    }
