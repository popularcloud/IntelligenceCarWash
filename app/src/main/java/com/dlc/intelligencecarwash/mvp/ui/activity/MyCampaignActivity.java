package com.dlc.intelligencecarwash.mvp.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dlc.intelligencecarwash.R;
import com.dlc.intelligencecarwash.base.BaseActivity;
import com.dlc.intelligencecarwash.mvp.ui.adapter.CampaignAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by YoungeTao on 2017/4/13
 * QQ 2276559259.
 * gmail youngetao@gmail.com
 * 我的活动
 */
public class MyCampaignActivity extends BaseActivity {

    @BindView(R.id.rv_common)
    RecyclerView rv_common;

    private CampaignAdapter mCampaignAdapter;

    public static void toStartActivity(Activity activity){
        activity.startActivity(new Intent(activity,MyCampaignActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_campaign);
        ButterKnife.bind(this);
        initUI();
    }

    private void initUI() {
        tvActionTitle.setText(R.string.my_campaign);
        rv_common.setLayoutManager(new LinearLayoutManager(this));
        mCampaignAdapter = new CampaignAdapter(this);
        rv_common.setAdapter(mCampaignAdapter);
    }
}
