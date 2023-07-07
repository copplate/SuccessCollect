package com.example.okpprac;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.okpprac.databinding.ActivityCookieTestBinding;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class CookieTestActivity extends AppCompatActivity {
    private ActivityCookieTestBinding binding;
    private OkHttpClient okHttpClient;//请求器
    List<Cookie> cookies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cookie_test);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cookie_test);



        binding.btnTestCookie.setOnClickListener(v -> {
            //登录后会在cookie中返回账号密码，只要在客户端做cookie持久化存储即可自动登录验证。
            //玩安卓用户名：copplate2023       密码：123456abc
            FormBody formBody = new FormBody.Builder().add("username", "copplate2023")
                    .add("password","123456abc")
                    .build();
            okHttpClient = new OkHttpClient.Builder()
                    .cookieJar(new CookieJar() {
                        @Override
                        public void saveFromResponse(@NonNull HttpUrl httpUrl, @NonNull List<Cookie> list) {
                            cookies = list;
                        }

                        @NonNull
                        @Override
                        public List<Cookie> loadForRequest(@NonNull HttpUrl httpUrl) {
                            if (httpUrl.host().equals("https://www.wanandroid.com")) {
                                return cookies;
                            }
                            return null;
                        }
                    }).build();


            //okp默认创建的request是get请求
            //post请求必须让我们传递一个requestBody，也就是请求体
            Request request = new Request.Builder().url("https://www.wanandroid.com/user/login").post(formBody).build();
            Call call = okHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {

                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    if (response.isSuccessful()) {
                        Log.d("vicePageAdapter", "run: ---555---" + response.body().string());
                    }
                }
            });


            request = new Request.Builder().url("https://www.wanandroid.com/user/login").build();
            call = okHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(@NonNull Call call, @NonNull IOException e) {

                }

                @Override
                public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                    if (response.isSuccessful()) {
                        Log.d("vicePageAdapter", "run: ---555---" + response.body().string());
                    }
                }
            });

        });
    }
}