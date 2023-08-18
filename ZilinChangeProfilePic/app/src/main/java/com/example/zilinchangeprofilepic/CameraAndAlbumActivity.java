package com.example.zilinchangeprofilepic;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CameraAndAlbumActivity extends AppCompatActivity {
    private Button btnOpenAlbum;
    private ImageView ivHeadPhoto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_and_album);
        btnOpenAlbum = findViewById(R.id.btn_open_album);
        ivHeadPhoto = findViewById(R.id.iv);
        btnOpenAlbum.setOnClickListener(v -> {//相册
            //动态权限申请是在6.0以上才有的，ContextCompat内部就帮我们做了版本的兼容
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                //真正的去打开相册
                openAlbum();
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
            }
        });

    }
    private ActivityResultLauncher<Intent> resultLauncherAlbum = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if (result.getResultCode() == RESULT_OK) {
                Intent data = result.getData();
                if (data != null) {
                    //api19之前的写法
                    if (Build.VERSION.SDK_INT < 19) {
                        handleImageBeforeApi19(data);
                    } else {
                        handleImageOnApi19(data);
                    }
                }
            }
        }
    });
    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");//隐式意图
//        startActivityForResult(intent, REQUEST_CODE_CHOOSE);
        resultLauncherAlbum.launch(intent);

    }
    private void handleImageBeforeApi19(Intent data) {
        Uri uri = data.getData();//这个uri直接是可以在contentProvider中查询的uri，然后就是getImagePath()查询
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);
    }
    private String getImagePath(Uri uri, String selection) {//通过一个uri查询出来它对应的一个路径
        String path = null;//首先我们要知道我们最终要获得的是文件的一个路径
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int i = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
                path = cursor.getString(i);
            }
            cursor.close();//不关闭会造成内存泄漏
        }
        return path;
    }

    private void displayImage(String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            ivHeadPhoto.setImageBitmap(bitmap);
        }
    }
    @TargetApi(19)
    private void handleImageOnApi19(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(this, uri)) {//是不是文档类型的uri,如果是这个类型的，就可以通过DocumentId来获取
            String documentId = DocumentsContract.getDocumentId(uri);

            //DocumentId还有细分的类型
            if (TextUtils.equals(uri.getAuthority(), "com.android.providers.media.documents")) {//媒体类型
                String id = documentId.split(":")[1];
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if (TextUtils.equals(uri.getAuthority(), "com.android.providers.downloads.documents")) {
                //要对这种返回msf:27的做特殊处理
                //不然content://downloads/public_downloads/27,这种链接也读不出来
                if (documentId != null && documentId.startsWith("msf:")) {
                    resolveMSFContent(uri, documentId);
                    return;
                } else if (documentId != null) {
                    resolveNoMSFContent(uri, documentId);
                    return;
                }
                Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(documentId));
                imagePath = getImagePath(contentUri, null);
            }

        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            //不区分大小写，判断是不是content类型。其实吧，这里判断content类型和file类型就是那个开头的content:\\
            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            //这个更简单，直接getPath()
            imagePath = uri.getPath();
        }
        displayImage(imagePath);
    }

    private void resolveNoMSFContent(Uri uri, String documentId) {
        File file = new File(this.getCacheDir(), "temp_file" + this.getContentResolver().getType(uri).split("/")[1]);
        try {
            InputStream inputStream = this.getContentResolver().openInputStream(uri);
            OutputStream outputStream = new FileOutputStream(file);
            byte[] buffer = new byte[4 * 1024];
            int read;
            while ((read = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, read);
            }
            outputStream.flush();
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            ivHeadPhoto.setImageBitmap(bitmap);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void resolveMSFContent(Uri uri, String documentId) {
        String s1 = getCacheDir().getAbsolutePath();
        String s2 = getContentResolver().getType(uri);
        String s3 = getContentResolver().getType(uri).split("/")[1];
        Log.d("tiktok", "resolveMSFContent: ------s1------" + s1);
        Log.d("tiktok", "resolveMSFContent: ------s2------" + s2);
        Log.d("tiktok", "resolveMSFContent: ------s3------" + s3);
        /*
        ------s1------/data/user/0/com.example.zilinchangeprofilepic/cache
        ------s2------image/jpeg
        ------s3------jpeg
        -----file---/data/user/0/com.example.zilinchangeprofilepic/cache/temp_filejpeg
         */

        File file = new File(this.getCacheDir(), "temp_file" + this.getContentResolver().getType(uri).split("/")[1]);
        Log.d("tiktok", "resolveMSFContent: --------" + file.getAbsolutePath());
        try {
            InputStream inputStream = this.getContentResolver().openInputStream(uri);
            OutputStream outputStream = new FileOutputStream(file);
            byte[] buffer = new byte[4 * 1024];
            int read;
            while ((read = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, read);
            }
            outputStream.flush();
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            ivHeadPhoto.setImageBitmap(bitmap);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}