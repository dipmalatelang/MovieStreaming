package com.netflix.app.networks;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*TODO Create ApiCall  for Slider image url to get APi Data*/
public class ApiClient {
    public static final String BASE_API = "http://www.netonplay.in/";
    public static Retrofit retrofit = null;


    public static ApiInterface getApiData() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        return  new Retrofit.Builder()
                .baseUrl(BASE_API)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(ApiInterface.class);

    }

         public static ApiInterface createApiCall(){
          return getApiData();
}



}