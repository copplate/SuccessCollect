package com.shangeyun.ucropprac;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.yalantis.ucrop.UCrop;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private Button btn;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.btn);
        iv = findViewById(R.id.iv);
        /*try {
            FileOutputStream output = openFileOutput("data", Context.MODE_PRIVATE);
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(output));
            bufferedWriter.write("哈哈");
            bufferedWriter.flush();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
//        Glide.with(this).load("/storage/emulated/0/Pictures/haixing2.jpeg").into(iv);
        Glide.with(this).load("file://" + getExternalFilesDir("Pictures").getPath() + "/haixing3.jpeg").into(iv);
        Uri sourceUri = Uri.parse("file://" + getExternalFilesDir("Pictures").getPath() + "/haixing3.jpeg");
//        Uri sourceUri = Uri.parse("/storage/emulated/0/Pictures/haixing2.jpeg");
        Uri destinationUri = Uri.parse("file://" + getExternalFilesDir("Pictures").getAbsolutePath() + "/cropPhoto.jpeg");
        File file = new File(destinationUri.getPath());
        btn.setOnClickListener(v -> {
            if (file.exists()) {
                file.delete();
            }
            UCrop.of(sourceUri, destinationUri)
                    .withAspectRatio(10, 11)
                    .withMaxResultSize(100, 110)
                    .withOptions(new UCrop.Options())
                    .start(MainActivity.this);
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == UCrop.REQUEST_CROP) {
            Uri resultUri = UCrop.getOutput(data);
            Log.d("tiktok", "onActivityResult: ------resultUri--------" + resultUri.toString());
            //这里不能用glide，因为路径相同，glide会直接从缓存读取图片
            Bitmap bitmap = BitmapFactory.decodeFile(resultUri.getPath());
            iv.setImageBitmap(bitmap);

//            Glide.with(this).load(resultUri).into(iv);


//            Glide.with(this).load(getExternalFilesDir("").getAbsolutePath()+"/cropPhoto").into(iv);
//            Glide.with(this).load("content://media/external/images/media/1000000036").into(iv);
        } else if (resultCode == UCrop.RESULT_ERROR) {
            final Throwable cropError = UCrop.getError(data);
        }


    }
}