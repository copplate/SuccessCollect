package com.example.gaodeprac;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.amap.api.services.core.AMapException;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;

import java.util.ArrayList;

public class SearchByKeyWordActivity extends AppCompatActivity {//根据关键字搜索Poi
    private EditText etSearchPlaceContent;
    private PoiSearch.Query query;
    private PoiSearch poiSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_by_key_word);
        etSearchPlaceContent = findViewById(R.id.et_search_place_content);
        query = new PoiSearch.Query("KTV", "", "济南");
        //keyWord表示搜索字符串，
        //第二个参数表示POI搜索类型，二者选填其一，选用POI搜索类型时建议填写类型代码，码表可以参考下方（而非文字）
        //cityCode表示POI搜索区域，可以是城市编码也可以是城市名称，也可以传空字符串，空字符串代表全国在全国范围内进行搜索
        query.setPageSize(10);// 设置每页最多返回多少条poiitem
        query.setPageNum(1);//设置查询页码

        try {
            poiSearch = new PoiSearch(this, query);
        } catch (AMapException e) {
            e.printStackTrace();
        }

        poiSearch.setOnPoiSearchListener(new PoiSearch.OnPoiSearchListener() {
            @Override
            public void onPoiSearched(PoiResult poiResult, int i) {
                ArrayList<PoiItem> pois = poiResult.getPois();
                if (pois != null) {
                    for (int j = 0; j < pois.size(); j++) {
                        Log.d("tiktok", "onPoiSearched: ----pois.get(0).getTitle()------" + pois.get(j).getTitle()

                        );
                    }

                }
            }

            @Override
            public void onPoiItemSearched(PoiItem poiItem, int i) {

            }
        });
        poiSearch.searchPOIAsyn();


    }
}