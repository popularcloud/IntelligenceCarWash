package com.dlc.intelligencecarwash.mvp.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dlc.intelligencecarwash.R;
import com.dlc.intelligencecarwash.base.BaseActivity;
import com.dlc.intelligencecarwash.mvp.ui.adapter.ConsumeRecordAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by YoungeTao on 2017/4/13
 * QQ 2276559259.
 * gmail youngetao@gmail.com
 * 消费记录
 */
public class ConsumeRecordActivity extends BaseActivity {

    @BindView(R.id.rl_record)
    RecyclerView mRecyclerView;
    ConsumeRecordAdapter mConsumeRecordAdapter;

    public static void toStartActivity(Activity activity){
        activity.startActivity(new Intent(activity,ConsumeRecordActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consume_record);
        ButterKnife.bind(this);

        initUI();
    }

    private void initUI() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mConsumeRecordAdapter = new ConsumeRecordAdapter();
        mRecyclerView.setAdapter(mConsumeRecordAdapter);
    }
}
