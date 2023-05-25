package com.shangeyun.chartprac1.app;


import android.app.Application;
import android.content.Context;



/**
 * Created by dingzuoqiang on 2017/07/10.
 * Email: 530858106@qq.com
 */

public class MyApplicaion extends Application {
    public static Context applicationContext;
    public static MyApplicaion instance;

    public MyApplicaion() {
        instance = this;
    }

    public static Context getContext() {
        return MyApplicaion.instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = this;
        initApp();

    }


    private void initApp() {
//        LogUtil.setAppEnvEnum(AppEnvHelper.currentEnv());

    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        MultiDex.install(this);
    }


}
