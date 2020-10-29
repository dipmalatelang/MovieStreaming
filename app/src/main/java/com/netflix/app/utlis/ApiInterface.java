package com.netflix.app.utlis;



import com.netflix.app.home.adapter.SlidePojo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface ApiInterface {
    @FormUrlEncoded
    @POST("Api/Slider.php")
    Call<List<SlidePojo>> getSlideData(@Field(value = "Slider", encoded = true) String Slider);





}
