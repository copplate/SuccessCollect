package com.example.okpprac;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;

import com.example.okpprac.databinding.ActivityCookieTestBinding;
import com.example.okpprac.databinding.ActivityRetrofitBinding;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RetrofitActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private ActivityRetrofitBinding binding;
    private HttpBinService httpBinService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_retrofit);

        retrofit = new Retrofit.Builder().baseUrl("https://www.httpbin.org/").build();
        httpBinService = retrofit.create(HttpBinService.class);


        binding.btnPostAsync.setOnClickListener(v -> {

            //第三步：接口实现类对象调用对应方法获得响应
            retrofit2.Call<ResponseBody> call = httpBinService.post("lance", "123");
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    try {
                        Log.d("vicePageAdapter", "onResponse: ---555---" + response.body().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });

        });

    }
}