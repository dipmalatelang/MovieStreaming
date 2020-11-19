package com.netflix.app.networks;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*TODO Create ApiCall  for Slider image url to get APi Data*/
public class ApiClient {
    public static final String BASE_API = "http://35.224.23.178:8886/";
    public static Retrofit retrofit = null;


    public static Api getApiData() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        return  new Retrofit.Builder()
                .baseUrl(BASE_API)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(Api.class);

    }

         public static Api createApiCall(){
          return getApiData();
}



}