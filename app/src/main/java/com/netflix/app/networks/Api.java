package com.netflix.app.networks;


import com.netflix.app.home.model.AllVideo;
import com.netflix.app.home.model.ChannelModel;
import com.netflix.app.home.model.EpisodeModel;
import com.netflix.app.home.model.TransactionModel;
import com.netflix.app.home.model.User;

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
    Call<User> checkLoginCredentials(@Path("name") String str, @Path("password") String str2);

    @POST("User/createUser")
    Call<User> createUser(@Body User user);

    @GET("Video/getAllVideo")
    Call<List<AllVideo>> getAllVideos();

    @GET("DashBoardController/getDashBoard")
    Call<List<AllVideo>> getBanner();

    @GET("Channel/getChannelById/{id}")
    Call<ChannelModel> getChannelById(@Path("id") Integer num);

    @POST("User/getchechsum")
    Call<Checksum> getChecksum(@Body Checksum checksum);

    @GET("getVideoByChannel/")
    Call<List<EpisodeModel>> getEpisode();

    @GET("User/getUserByID/{id}/")
    Call<User> getU(@Path("id") String str);

    @GET("Video/getVideoByChannel/{id}/")
    Call<List<AllVideo>> getVideoByChannelId(@Path("id") Integer num);

    @POST("Video/addLikes/{vodId}/{userId}")
    Call<Long> insertLike(@Path("vodId") String str, @Path("userId") String str2);

    @PATCH("Channel/updateFollow/{channelId}")
    Call<String> unFollow(@Path("channelId") Integer num, @Query("userId") String str);

    @POST("User/addTransction")
    Call<User> userTransaction(@Body TransactionModel transactionModel);
}
