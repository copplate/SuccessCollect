package com.example.rxretrofitprac.util;


import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {
    private static Retrofit retrofit;
    //双重检查法获取实例对象
    public static <T> T getService(Class<T> serviceClass){
        if(retrofit == null){
            synchronized (RetrofitUtils.class){
                if(retrofit == null){
                    retrofit = new Retrofit.Builder()
                            .baseUrl("http://124.132.142.251:83/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .addCallAdapterFactory(RxJava3CallAdapterFactory.create()) //RxJava3 适配器
                            .build();
                }
            }
        }
        return retrofit.create(serviceClass);
    }
    //私有化构造方法
    private RetrofitUtils(){

    }
}
