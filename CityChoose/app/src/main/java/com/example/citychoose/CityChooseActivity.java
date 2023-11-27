package com.example.citychoose;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.wheelpicker.AdministrativeMap;
import com.wheelpicker.AdministrativeUtil;
import com.wheelpicker.DataPicker;
import com.wheelpicker.OnCascadeWheelListener;
import com.wheelpicker.OnMultiDataPickListener;
import com.wheelpicker.PickOption;

import java.util.ArrayList;
import java.util.List;

public class CityChooseActivity extends AppCompatActivity {
    private Button btnChooseCity;
    private List<Integer> mCascadeInitIndexNoArea = new ArrayList<Integer>();
    private AdministrativeMap mAdministrativeMap;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_choose);
        btnChooseCity = findViewById(R.id.btn_choose_city);
        mContext = this;
        btnChooseCity.setOnClickListener(v -> {
            pickCity(AdministrativeUtil.PROVINCE_CITY, mCascadeInitIndexNoArea);
        });
    }

    private void pickCity(int mode, final List<Integer> initIndex) {
        if (mAdministrativeMap == null) {
            mAdministrativeMap = AdministrativeUtil.loadCity(CityChooseActivity.this);
        }

        PickOption option = getPickDefaultOptionBuilder(mContext)
                .setMiddleTitleText("请选择城市")
                .setFlingAnimFactor(0.4f)
                .setVisibleItemCount(7)
                .setItemTextSize(mContext.getResources().getDimensionPixelSize(com.wheelpicker.R.dimen.font_24px))
                .setItemLineColor(0xFF558800)
                .build();

        DataPicker.pickData(mContext, initIndex,
                AdministrativeUtil.getPickData(mAdministrativeMap, initIndex, mode), option,
                new OnMultiDataPickListener() {
                    @Override
                    public void onDataPicked(List indexArr, List val, List data) {
                        String s = indexArr.toString() + ":" + val.toString();
                        Log.d("tiktok", "onDataPicked: -----s-------" + s);
                        Toast.makeText(CityChooseActivity.this, s, Toast.LENGTH_SHORT).show();
                        initIndex.clear();
                        initIndex.addAll(indexArr);
                    }
                }, new OnCascadeWheelListener<List<?>>() {

                    @Override
                    public List<?> onCascade(int wheelIndex, List<Integer> itemIndex) {
                        //级联数据
                        if (wheelIndex == 0) {
                            return mAdministrativeMap.provinces.get(itemIndex.get(0)).city;
                        } else if (wheelIndex == 1) {
                            return mAdministrativeMap.provinces.get(itemIndex.get(0)).city.get(itemIndex.get(1)).areas;
                        }

                        return null;
                    }
                });
    }

    private PickOption.Builder getPickDefaultOptionBuilder(Context context) {
        return PickOption.getPickDefaultOptionBuilder(context)
                .setLeftTitleColor(0xFF1233DD)
                .setRightTitleColor(0xFF1233DD)
                .setMiddleTitleColor(0xFF333333)
                .setTitleBackground(0XFFDDDDDD)
                .setLeftTitleText("取消")
                .setRightTitleText("确定");
    }


}