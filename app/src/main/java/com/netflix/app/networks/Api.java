package com.netflix.app.networks;


import com.netflix.app.home.model.AllDataPojo;
import com.netflix.app.home.model.AllDataPojo.Ep;
import com.netflix.app.home.model.TransactionModel;
import com.netflix.app.home.model.Userpojo;
import com.netflix.app.home.model.UsersResponse;

import java.util.List;
import java.util.zip.Checksum;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {
    @POST("Channel/addFollow/{channelId}")
    Call<String> addFollow(@Path("channelId") Integer num, @Query("userId") String str);

    @GET("User/getValidateUser/{name}/{password}")
    Call<UsersResponse> checkLoginCredentials(@Path("name") String str, @Path("password") String str2);

    @POST("User/createUser")
    Call<Userpojo> createUser(@Body Userpojo userpojo);

    @GET("Video/getAllVideo")
    Call<List<AllDataPojo>> getAllVideos();

    @GET("DashBoardController/getDashBoard")
    Call<List<AllDataPojo>> getBanner();

    @GET("Channel/getChannelById/{id}")
    Call<AllDataPojo> getChannelById(@Path("id") Integer num);

    @POST("User/getchechsum")
    Call<Checksum> getChecksum(@Body Checksum checksum);

    @GET("getVideoByChannel/")
    Call<List<Ep>> getEpisode();

    @GET("User/getUserByID/{id}/")
    Call<UsersResponse> getU(@Path("id") String str);

    @GET("Video/getVideoByChannel/{id}/")
    Call<List<AllDataPojo>> getVideoByChannelId(@Path("id") Integer num);

    @POST("Video/addLikes/{vodId}/{userId}")
    Call<Long> insertLike(@Path("vodId") String str, @Path("userId") String str2);

    @PATCH("Channel/updateFollow/{channelId}")
    Call<String> unFollow(@Path("channelId") Integer num, @Query("userId") String str);

    @POST("User/addTransction")
    Call<UsersResponse> userTransaction(@Body TransactionModel transactionModel);

    Call<UsersResponse> createUser();
}
