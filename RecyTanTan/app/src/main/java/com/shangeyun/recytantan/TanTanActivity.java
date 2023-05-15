package com.shangeyun.recytantan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.shangeyun.recytantan.adapter.MyCardStackAdapter;
import com.shangeyun.recytantan.callback.SlideCardStackCallBack2;
import com.shangeyun.recytantan.layoutmanager.CardStack3LayoutManager;

public class TanTanActivity extends AppCompatActivity {

    private RecyclerView rlv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tan_tan);
        rlv = findViewById(R.id.rlv);
        MyCardStackAdapter myCardStackAdapter = new MyCardStackAdapter(this);
        CardStack3LayoutManager linearLayoutManager = new CardStack3LayoutManager();
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rlv.setLayoutManager(linearLayoutManager);
        rlv.setAdapter(myCardStackAdapter);
        SlideCardStackCallBack2<String> stringSlideCardStackCallBack2 = new SlideCardStackCallBack2<String>(myCardStackAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(stringSlideCardStackCallBack2);
        itemTouchHelper.attachToRecyclerView(rlv);
    }
}