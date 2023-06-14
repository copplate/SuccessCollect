package com.example.fragmentprac;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.fragmentprac.fragment.FirstSignFragment;
import com.example.fragmentprac.fragment.SecondSignFragment;

public class MainActivity extends AppCompatActivity {//一个很棒的fragment添加的展示

    private Button btnAddFragment;
    private Button btnAddFragment2;
    private Button btnAddFragment3;
    private Button passDataByInterface;
    private Button btnGotoViewpager;
    private Button passDataByClass;
    private TextView tvActivity;
    private int sign = 0;

    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAddFragment = findViewById(R.id.btn_add_fragment);
        btnAddFragment2 = findViewById(R.id.btn_add_fragment2);
        btnAddFragment3 = findViewById(R.id.btn_add_fragment3);
        btnGotoViewpager = findViewById(R.id.btn_goto_viewpager);
        passDataByInterface = findViewById(R.id.btn_passdata_by_interface);
        passDataByClass = findViewById(R.id.btn_passdata_by_class);
        tvActivity = findViewById(R.id.tv_activity);

        btnAddFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = MainActivity.this.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.fcv, BlankFragment.class,null ).commit();
            }
        });

        btnAddFragment2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = MainActivity.this.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.fcv, BlankFragment.class,null )
                        .addToBackStack("myFragment")
                        .setReorderingAllowed(true)
                        .commit();
            }
        });

        btnAddFragment3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = MainActivity.this.getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                Log.d("tiktok", "onClick: ---------" + sign);
                switch (sign % 2) {
                    case 0:
                        FirstSignFragment firstSignFragment = new FirstSignFragment();
                        fragmentTransaction.replace(R.id.fcv, firstSignFragment).commit();
                        sign++;
                        break;
                    case 1:
                        SecondSignFragment secondSignFragment= new SecondSignFragment();
                        fragmentTransaction.replace(R.id.fcv, secondSignFragment).commit();
                        sign++;
                        break;
                }


            }
        });
        btnGotoViewpager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ImageViewPagerActivity.class);
                startActivity(intent);
            }
        });

    //**********Activity通过接口向Fragment传数据**************
        //自己理解：说这是利用了编程语言的特性是因为，编程语言在执行类的方法的时候会
        // 走到类实例的内部，只不过这个内部恰巧在fragment里
        passDataByInterface.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mDataChangeListener != null) {
                    mDataChangeListener.onDataChange("amazing,这是通过接口传递的数据");

                    String s1 = "";
                    String s2 = "";
                    String s3 = "";
                    int id = 0;
                    switch (id) {
                        case 1:
                            s1 = "123";

                            break;
                        case 2:
                            s1 = "abc";
                            s1 = "jkl";
                            break;
                    }
                }
            }
        });

        /*passDataByClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mStringChangeListener != null) {
                    mStringChangeListener.onStringChange("这是通过类传递的数据");
                }
            }
        });*/
    }

    private OnDataChangeListener mDataChangeListener;

    public void setOnDataChangeListener(OnDataChangeListener dataChangeListener) {
        mDataChangeListener = dataChangeListener;
    }

    interface OnDataChangeListener {
        void onDataChange(String data);
    }
    //自研(失败)：通过类传数据
    /*private onStringChangeListener mStringChangeListener;

    public void setOnStringChangeListener(onStringChangeListener stringChangeListener) {
        mStringChangeListener = stringChangeListener;
    }
    public class onStringChangeListener {
        void onStringChange(String data) {

        }
    }*/


    //**********Activity通过接口向Fragment传数据**************


    public void setTvActivity(String s) {//Fragment调用此方法来向传数据(Fragment通过普通方法向Activity传数据)
        tvActivity.setText(s);
    }

}