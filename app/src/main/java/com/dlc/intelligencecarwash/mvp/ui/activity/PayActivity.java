package com.dlc.intelligencecarwash.mvp.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.dlc.intelligencecarwash.R;
import com.dlc.intelligencecarwash.base.BaseActivity;

import butterknife.ButterKnife;

/**
 * Created by YoungeTao on 2017/4/14
 * QQ 2276559259.
 * gmail youngetao@gmail.com
 * 支付
 */
public class PayActivity extends BaseActivity {

    public static void toStartActivity(Activity activity){
        activity.startActivity(new Intent(activity,PayActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        ButterKnife.bind(this);
    }
}
