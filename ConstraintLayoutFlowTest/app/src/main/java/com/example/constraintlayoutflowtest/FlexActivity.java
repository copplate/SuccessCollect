package com.example.constraintlayoutflowtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;

public class FlexActivity extends AppCompatActivity {

    private FlexboxLayout fbl;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flex);
        fbl = findViewById(R.id.fbl);
        TextView tv1 = new TextView(this);
        TextView tv2 = new TextView(this);
        tv1.setText("人生");
        tv2.setText("对酒");
//        tv1.setLayoutParams(new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        tv1.setBackgroundColor(getResources().getColor(R.color.purple_200));
        tv2.setBackgroundColor(getResources().getColor(R.color.purple_200));

        fbl.addView(tv1);
        fbl.addView(tv2);


    }
}