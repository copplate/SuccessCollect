package com.example.rxretrofitprac;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.example.rxretrofitprac.bean.Temp;
import com.example.rxretrofitprac.util.RetrofitUtils;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RxActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);

        RetroService retroService = RetrofitUtils.getService(RetroService.class);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://124.132.142.251:83/") // 设置API的基本URL
                .addConverterFactory(GsonConverterFactory.create()) // 添加Gson转换器
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create()) //RxJava3 适配器
                .build();
        RetroService retroService2 = retrofit.create(RetroService.class);
        Observable<Temp> videoNameObservable = retroService.getVideoName();
        videoNameObservable.subscribeOn(Schedulers.io())      // 在IO线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread())    // 回到主线程 处理请求结果
                .subscribe(new Observer<Temp>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Log.d("tiktok", "onSubscribe: -----");

                    }

                    @Override
                    public void onNext(@NonNull Temp temp) {
                        Log.d("tiktok", "onNext: -----" + temp.getName()[0]);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("tiktok", "onError: -----" + e);

                    }

                    @Override
                    public void onComplete() {
                        Log.d("tiktok", "onComplete: -----");

                    }
                });
    }
}