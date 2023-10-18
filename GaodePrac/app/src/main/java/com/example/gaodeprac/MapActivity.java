package com.example.gaodeprac;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.amap.api.location.AMapLocation;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.MapsInitializer;
import com.amap.api.maps.model.MyLocationStyle;

public class MapActivity extends AppCompatActivity {
    private MapView mMapView;
    private AMap aMap = null;
    private Button btnSearch,btnErrorCode,btnLocate,btnSearchByKeyWord;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map); //获取地图控件引用
        MapsInitializer.updatePrivacyShow(this,true,true);
        MapsInitializer.updatePrivacyAgree(this,true);
        mMapView = (MapView)findViewById(R.id.map);
        btnSearch = findViewById(R.id.btn_search);
        btnErrorCode = findViewById(R.id.btn_error_code);
        btnLocate = findViewById(R.id.btn_locate);
        btnSearchByKeyWord = findViewById(R.id.btn_search_by_key_word);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);

        btnSearch.setOnClickListener(v -> {
            Intent intent = new Intent(MapActivity.this, SearchActivity.class);
            startActivity(intent);
        });
        btnErrorCode.setOnClickListener(v -> {//获取错误码
            AMapLocation aMapLocation = new AMapLocation("sss");
            int errorCode1 = aMapLocation.getErrorCode();
            Log.d("tiktok", "onCreate: -----errorCode1-----" + errorCode1);
        });
        btnLocate.setOnClickListener(v -> {//前往定位界面
            Intent intent = new Intent(MapActivity.this, LocateActivity.class);
            startActivity(intent);
        });
        btnSearchByKeyWord.setOnClickListener(v -> {
            Intent intent = new Intent(MapActivity.this, SearchByKeyWordActivity.class);
            startActivity(intent);
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
        //初始化地图控制器对象
        if (aMap == null) {
            aMap = mMapView.getMap();
        }
        MyLocationStyle myLocationStyle;
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类
        // myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.getUiSettings().setMyLocationButtonEnabled(true);//设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        aMap.moveCamera(CameraUpdateFactory.zoomTo(15));//当定位到自身的时候，地图对应缩放到15。

    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }
}