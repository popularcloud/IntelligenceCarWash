package com.dlc.intelligencecarwash.mvp.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.dlc.intelligencecarwash.R;
import com.dlc.intelligencecarwash.base.BaseActivity;
import com.dlc.intelligencecarwash.mvp.model.entity.UserInfoEntity;
import com.dlc.intelligencecarwash.utils.SharedPreUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by YoungeTao on 2017/4/13
 * QQ 2276559259.
 * gmail youngetao@gmail.com
 * 我的
 */
public class MineActivity extends BaseActivity {


    @BindView(R.id.tv_name)
    TextView tv_name;

    public static void toStartActivity(Activity activity){
        activity.startActivity(new Intent(activity,MineActivity.class));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);
        ButterKnife.bind(this);

        initData();
    }

    private void initData() {
       UserInfoEntity userInfo = SharedPreUtil.getInstance().getUser();
        tv_name.setText(userInfo.getNickName());
    }

    @OnClick({R.id.tv_recharge,R.id.ll_consume_record,R.id.ll_call_customer,R.id.ll_user_guide,R.id.civ_header,R.id.ll_my_campaign})
    public void onBtnClick(View view){
        switch (view.getId()){
            case R.id.tv_recharge:
                RechargeActivity.toStartActivity(MineActivity.this);
                break;
            case R.id.ll_consume_record:
                ConsumeRecordActivity.toStartActivity(MineActivity.this);
                break;
            case R.id.ll_call_customer:
                CustomerServiceActivity.toStartActivity(MineActivity.this);
                break;
            case R.id.ll_user_guide:
                CommonWebViewActivity.toStartActivity(this,getString(R.string.user_guide));
                break;
            case R.id.civ_header:
                PersonalDataActivity.toStartActivity(MineActivity.this);
                break;
            case R.id.ll_my_campaign:
                MyCampaignActivity.toStartActivity(MineActivity.this);
                break;
        }
    }
}
