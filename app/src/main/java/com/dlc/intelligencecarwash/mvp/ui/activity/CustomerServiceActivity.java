package com.dlc.intelligencecarwash.mvp.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.dlc.intelligencecarwash.R;
import com.dlc.intelligencecarwash.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by YoungeTao on 2017/4/14
 * QQ 2276559259.
 * gmail youngetao@gmail.com
 * 联系客服
 */
public class CustomerServiceActivity extends BaseActivity {

    public static void toStartActivity(Activity activity){
        activity.startActivity(new Intent(activity,CustomerServiceActivity.class));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_service);
        ButterKnife.bind(this);

        initUI();
    }

    private void initUI() {
        tvActionTitle.setText(R.string.contact_customer_service);
    }

    @OnClick(R.id.rl_call_phone)
    public void onBtnClick(View view){
        switch (view.getId()){
            case R.id.rl_call_phone:
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+"13659889666"));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
        }
    }


}
