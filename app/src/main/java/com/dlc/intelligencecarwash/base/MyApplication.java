package com.dlc.intelligencecarwash.base;

import android.app.Application;

import com.dlc.intelligencecarwash.utils.SharedPreUtil;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

/**
 * Created by YoungeTao on 2017/4/14
 * QQ 2276559259.
 * gmail youngetao@gmail.com
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        ZXingLibrary.initDisplayOpinion(this);
        SharedPreUtil.initSharedPreference(getApplicationContext());
    }
}
