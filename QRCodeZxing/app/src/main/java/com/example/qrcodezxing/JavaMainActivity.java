package com.example.qrcodezxing;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.qrcodezxing.databinding.ActivityJavaMainBinding;
import com.yzq.zxinglibrary.android.CaptureActivity;
import com.yzq.zxinglibrary.bean.ZxingConfig;
import com.yzq.zxinglibrary.common.Constant;

public class JavaMainActivity extends AppCompatActivity {
    private ActivityJavaMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_main);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_java_main);
        binding.btnScan.setOnClickListener(v -> {
            Intent intent = new Intent(JavaMainActivity.this, CaptureActivity.class);
            /*ZxingConfig是配置类
             *可以设置是否显示底部布局，闪光灯，相册，
             * 是否播放提示音  震动
             * 设置扫描框颜色等
             * 也可以不传这个参数
             * */
            ZxingConfig config = new ZxingConfig();
            config.setPlayBeep(true);//是否播放扫描声音 默认为true
            config.setShake(true);//是否震动  默认为true
            config.setDecodeBarCode(true);//是否扫描条形码 默认为true
            config.setReactColor(R.color.white);//设置扫描框四个角的颜色 默认为白色
            config.setFrameLineColor(R.color.white);//设置扫描框边框颜色 默认无色
            config.setScanLineColor(R.color.white);//设置扫描线的颜色 默认白色
            config.setFullScreenScan(false);//是否全屏扫描  默认为true  设为false则只会在扫描框中扫描
            intent.putExtra(Constant.INTENT_ZXING_CONFIG, config);
//            startActivityForResult(intent, REQUEST_CODE_SCAN);
            resultLauncherLogin.launch(intent);
        });

    }

    //通过这个方式跳转到登录界面，可以在返回时显示我的界面。
    private ActivityResultLauncher<Intent> resultLauncherLogin = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            Intent data = result.getData();
            /*if (result.getResultCode() == MainActivity.this.RESULT_OK) {
            }*/
            if (data != null) {
                String content = data.getStringExtra(Constant.CODED_CONTENT);
                Log.d("tiktok", "onActivityResult: --------" + content);
            }

        }
    });
}