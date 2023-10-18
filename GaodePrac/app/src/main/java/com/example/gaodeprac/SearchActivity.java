package com.example.gaodeprac;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Poi;
import com.amap.api.navi.AMapNavi;
import com.amap.api.navi.AmapNaviPage;
import com.amap.api.navi.AmapNaviParams;
import com.amap.api.navi.AmapNaviType;
import com.amap.api.navi.AmapPageType;
import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.InputtipsQuery;
import com.amap.api.services.help.Tip;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.example.gaodeprac.adapter.RecySearchPoiHintAdapter;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity {//带有输入内容自动提示功能的，搜索
    private EditText etSearchPlaceContent;
    private Inputtips inputTips;
    private PoiSearch.Query query;
    private PoiSearch poiSearch;
    private AMapNavi aMapNavi;
    private RecyclerView rlvSearchPoiHint;//搜索提示的列表
    private RecySearchPoiHintAdapter recySearchPoiHintAdapter;
    private List<Tip> tips = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        etSearchPlaceContent = findViewById(R.id.et_search_place_content);
        rlvSearchPoiHint = findViewById(R.id.rlv_search_poi_hint);


        recySearchPoiHintAdapter = new RecySearchPoiHintAdapter(tips, this);
        rlvSearchPoiHint.setAdapter(recySearchPoiHintAdapter);
        rlvSearchPoiHint.setLayoutManager(new LinearLayoutManager(this));

        etSearchPlaceContent.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (String.valueOf(s) == null) {
                    return;
                }
                String searchContent = s + "";
                Log.d("tiktok", "onTextChanged: ---------" + searchContent);
                //第二个参数传入null或者“”代表在全国进行检索，否则按照传入的city进行检索
                InputtipsQuery inputquery = new InputtipsQuery(String.valueOf(s), "济南");
//                InputtipsQuery inputquery = new InputtipsQuery(s + "", "济南");
                inputquery.setCityLimit(true);//限制在当前城市
                inputTips.setQuery(inputquery);
                inputTips.requestInputtipsAsyn();
                /*query = new PoiSearch.Query(searchContent + "", "", null);
                //keyWord表示搜索字符串，
                //第二个参数表示POI搜索类型，二者选填其一，选用POI搜索类型时建议填写类型代码，码表可以参考下方（而非文字）
                //cityCode表示POI搜索区域，可以是城市编码也可以是城市名称，也可以传空字符串，空字符串代表全国在全国范围内进行搜索
                query.setPageSize(10);// 设置每页最多返回多少条poiitem
                query.setPageNum(1);//设置查询页码
                poiSearch.searchPOIAsyn();*/
            }

            @Override
            public void afterTextChanged(Editable s) {
//                Log.d("tiktok", "afterTextChanged: -----------" + s);
            }
        });

        recySearchPoiHintAdapter.setOnSearchPoiHintClickListener(new RecySearchPoiHintAdapter.OnSearchPoiHintClickListener() {
            @Override
            public void click(Poi poi, LatLonPoint latLonPoint) {
                poi.getPoiId();
                gotoGaoDeMap(latLonPoint,poi.getName());

                //构建导航组件配置类，没有传入起点，所以起点默认为 “我的位置”
                //第二个List<Poi> list 传null表示我们不需要有特别的途径点
                /*AmapNaviParams params = new AmapNaviParams(null, null, poi, AmapNaviType.DRIVER, AmapPageType.ROUTE);
                //启动导航组件
                AmapNaviPage.getInstance().showRouteActivity(getApplicationContext(), params, null);*/
            }
        });

        try {
            poiSearch = new PoiSearch(this, query);
        } catch (AMapException e) {
            e.printStackTrace();
        }
        poiSearch.setOnPoiSearchListener(new PoiSearch.OnPoiSearchListener() {
            @Override
            public void onPoiSearched(PoiResult poiResult, int rCode) {
                List<PoiItem> pois = poiResult.getPois();
                Log.d("tiktok", "onPoiSearched: --poiResult.getPois()--------" + pois + "--rCode--" + rCode);
            }

            @Override
            public void onPoiItemSearched(PoiItem poiItem, int i) {
                Log.d("tiktok", "onPoiItemSearched: -------" + poiItem);
            }
        });

        inputTips = new Inputtips(SearchActivity.this, (InputtipsQuery)null);
        inputTips.setInputtipsListener(new Inputtips.InputtipsListener() {
            @Override
            public void onGetInputtips(List<Tip> list, int i) {
                Log.d("tiktok", "onGetInputtips: -------------" + list);
                if (list != null) {
                    Log.d("tiktok", "onGetInputtips: ---list.get(0).getAddress()---" + list.get(0).getAddress());
                }
//                recySearchPoiHintAdapter.setData(list);
                tips.clear();
                tips.addAll(list);
                recySearchPoiHintAdapter.notifyDataSetChanged();
            }
        });
    }

    private void gotoGaoDeMap(LatLonPoint latLonPoint,String poiName) {//去高德地图标点
//        LatLng endPoint = BD2GCJ(new LatLng(31.220567, 121.395174));//转换坐标系
        StringBuffer sb = new StringBuffer("androidamap://viewMap")
                .append("?sourceApplication=").append(getString(R.string.app_name))
                /*.append("&lat=").append(endPoint.latitude)
                .append("&lon=").append(endPoint.longitude)*/
                .append("&lat=").append(latLonPoint.getLatitude())
                .append("&lon=").append(latLonPoint.getLongitude())
                .append("&poiname=").append(poiName)
                .append("&dev=0");
        Intent intent = new Intent();
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setPackage("com.autonavi.minimap");
        intent.setData(Uri.parse(sb.toString()));
        startActivity(intent);
    }

    private void gotoGaoDeMapPaintTrack(LatLonPoint latLonPoint) {//去高德地图标点并绘制路径(路径规划)
//        LatLng endPoint = BD2GCJ(new LatLng(31.220567, 121.395174));//转换坐标系
        StringBuffer sb = new StringBuffer("androidamap://viewMap")
                .append("?sourceApplication=").append(getString(R.string.app_name))
                /*.append("&lat=").append(endPoint.latitude)
                .append("&lon=").append(endPoint.longitude)*/
                .append("&lat=").append(latLonPoint.getLatitude())
                .append("&lon=").append(latLonPoint.getLongitude())
                .append("&did=").append(latLonPoint.getLongitude())
                .append("&dev=0");
        Intent intent = new Intent();
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setPackage("com.autonavi.minimap");
        intent.setData(Uri.parse(sb.toString()));
        startActivity(intent);
    }

}