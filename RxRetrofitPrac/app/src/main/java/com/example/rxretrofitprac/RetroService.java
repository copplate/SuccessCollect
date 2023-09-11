package com.example.rxretrofitprac;

import com.example.rxretrofitprac.bean.Temp;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;

public interface RetroService {
    @GET("video")
    Observable<Temp> getVideoName();

}
