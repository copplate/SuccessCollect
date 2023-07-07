package com.example.okpprac;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

//第一步，根据http接口来创建java接口
//第二步，创建Retrofit对象，并生成接口实现类对象
public interface HttpBinService {

    //https://www.httpbin.org/post?xxx=username
    @POST("post")
    @FormUrlEncoded
    Call<ResponseBody> post2(@Field("xxx")String username, String pwd);


    @POST("post")
    @FormUrlEncoded
    Call<ResponseBody> post(@Field("username")String username, @Field("password")String pwd);


    @GET("get")
    Call<ResponseBody> get(@Query("username") String username, @Query("password")String pwd);

}
