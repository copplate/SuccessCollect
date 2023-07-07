package com.example.okpprac;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.textclassifier.TextLinks;
import android.widget.Button;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private OkHttpClient okHttpClient;//请求器
    private Button btnGetSync, btnGetASync, btnPostSync, btnPostASync,btnPostJson;
    public static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        okHttpClient = new OkHttpClient();
        btnGetSync = findViewById(R.id.btn_get_sync);
        btnGetASync = findViewById(R.id.btn_get_async);
        btnPostSync = findViewById(R.id.btn_post_sync);
        btnPostASync = findViewById(R.id.btn_post_async);
        btnPostJson = findViewById(R.id.btn_post_json);


        btnGetSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        //得到一个Request的请求对象，再把请求对象交给请求工具okHttpClient
                        Request request = new Request.Builder().url("https://www.httpbin.org/get").build();
                        //准备好请求的call对象
                        Call call = okHttpClient.newCall(request);
                        try {
                            Response response = call.execute();
                            Log.d("vicePageAdapter", "run: ------" + response.body().string());
                            Log.d("vicePageAdapter", "run: ---222222---" + response.body().toString());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }.start();
            }
        });

        btnGetASync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Request request = new Request.Builder().url("https://www.httpbin.org/get").build();
                //准备好请求的call对象
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        if (response.isSuccessful()) {
                            Log.d("vicePageAdapter", "run: ---333---" + response.body().string());
                        }
                    }
                });
            }
        });

        btnPostSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FormBody formBody = new FormBody.Builder().add("thief", "xi").build();

                //okp默认创建的request是get请求
                //post请求必须让我们传递一个requestBody，也就是请求体
                Request request = new Request.Builder().url("https://www.httpbin.org/post").post(formBody).build();


            }
        });

        btnPostASync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FormBody formBody = new FormBody.Builder().add("thief", "xi").build();

                //okp默认创建的request是get请求
                //post请求必须让我们传递一个requestBody，也就是请求体
                Request request = new Request.Builder().url("https://www.httpbin.org/post").post(formBody).build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        if (response.isSuccessful()) {
                            Log.d("tiktok", "run: ---555---" + response.body().string());
                        }
                    }
                });
            }

        });
        /**
         * 使用okhttp向post接口通过raw格式提交json字符串
         * */
        btnPostJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FormBody formBody = new FormBody.Builder().add("thief", "xi").build();
                String json = "{\n" +
                        "\t\"password\": \"123\",\n" +
                        "\t\"type\": \"商户\",\n" +
                        "\t\"telephone\": 17864266932\n" +
                        "}";
                RequestBody body = RequestBody.create(json, JSON);

                //okp默认创建的request是get请求
                //post请求必须让我们传递一个requestBody，也就是请求体
                Request request = new Request.Builder().url("http://124.132.142.251:83/register/").post(body).build();
                Call call = okHttpClient.newCall(request);
                call.enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                        Log.d("tiktok", "onFailure: --------" + e);
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        if (response.isSuccessful()) {
                            Log.d("tiktok", "run: ---555---" + response.body().string());
                        }
                    }
                });
            }

        });



    }
}