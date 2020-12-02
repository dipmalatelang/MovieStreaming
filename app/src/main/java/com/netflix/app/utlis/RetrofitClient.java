package com.netflix.app.utlis;

import com.netflix.app.networks.Api;
import com.netflix.app.networks.Constant;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static RetrofitClient mInstance;
    private Retrofit retrofit = new Retrofit.Builder().baseUrl(Constant.BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

    private RetrofitClient() {
    }

    public static synchronized RetrofitClient getInstance() {
        RetrofitClient retrofitClient;
        synchronized (RetrofitClient.class) {
            if (mInstance == null) {
                mInstance = new RetrofitClient();
            }
            retrofitClient = mInstance;
        }
        return retrofitClient;
    }

    public Api getApi() {
        return (Api) this.retrofit.create(Api.class);
    }
}
