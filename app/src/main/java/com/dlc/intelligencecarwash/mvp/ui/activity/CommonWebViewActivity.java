package com.dlc.intelligencecarwash.mvp.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import com.dlc.intelligencecarwash.R;
import com.dlc.intelligencecarwash.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CommonWebViewActivity extends BaseActivity {

    @BindView(R.id.wv_common)
    WebView mWebView;

    public static void toStartActivity(Activity activity,String title){
        Intent myIntent = new Intent(activity,CommonWebViewActivity.class);
        myIntent.putExtra("title",title);
        activity.startActivity(myIntent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_web_view);
        ButterKnife.bind(this);
        initUI();
    }

    private void initUI() {

        String title = getIntent().getStringExtra("title");
        if(title != null){
            tvActionTitle.setText(title);
        }
        mWebView.loadUrl("https://worktile.com/terms");
    }
}
