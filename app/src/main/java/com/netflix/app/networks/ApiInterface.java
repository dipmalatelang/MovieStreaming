package com.netflix.app.networks;


import com.netflix.app.home.model.AllDataPojo;
import com.netflix.app.home.model.UsersResponse;
import com.netflix.app.home.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/*TODO Create ApiInterface  for Slider image*/
public interface ApiInterface {
    @FormUrlEncoded
    @POST("Api/Slider.php")
    Call<List<AllDataPojo>> getSlideData(@Field(value = "Slider", encoded = true) String Slider);

    @POST("/User/createUser")
    Call<UsersResponse> getRegister(@Body User user);
}
