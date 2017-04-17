package com.dlc.intelligencecarwash.base;

import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.dlc.intelligencecarwash.R;

import butterknife.BindView;

/**
 * Created by YoungeTao on 2017/3/16.
 * QQ 2276559259.
 * gmail youngetao@gmail.com
 */

public class BaseActivity extends AppCompatActivity {

    @BindView(R.id.tv_action_title)
    public TextView tvActionTitle;

    /**
     * 回退
     * @param view
     */
    public void onClickBack(View view){
        finish();
    }

    /**
     * 系统回退按钮事件
     */
    @Override
    public void onBackPressed() {
        onClickBack(new View(this));
    }

    /**
     * 提示信息
     * @param msg
     */
    protected void showToast(String msg) {
        Toast.makeText(BaseActivity.this, msg, Toast.LENGTH_LONG).show();
    }

}
