package com.dlc.intelligencecarwash.mvp.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dlc.intelligencecarwash.R;
import com.dlc.intelligencecarwash.base.BaseActivity;
import com.dlc.intelligencecarwash.mvp.model.entity.UserInfoEntity;
import com.dlc.intelligencecarwash.mvp.presenter.LoginPresenter;
import com.dlc.intelligencecarwash.mvp.presenter.impl.LoginPresenterImpl;
import com.dlc.intelligencecarwash.mvp.ui.view.ILoginView;
import com.dlc.intelligencecarwash.utils.SharedPreUtil;
import com.dlc.intelligencecarwash.widget.ExtendedEditText;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by YoungeTao on 2017/4/13
 * QQ 2276559259.
 * gmail youngetao@gmail.com
 */

public class LoginActivity extends BaseActivity implements ILoginView{


    @BindView(R.id.tv_get_verification_code)
    TextView tv_get_verification_code;
    @BindView(R.id.et_phone)
    ExtendedEditText et_phone;
    @BindView(R.id.et_verification_code)
    ExtendedEditText et_verification_code;

    public Handler mUIHandler = new Handler();
    // 验证码计时器
    public Timer mSMSTimer = new Timer();
    //默认倒计时时间
    private int VERIFICATION_CODE_TIME_INTERVAL = 60;
    // 倒计时
    public int mVerifyCodeTime = 0;
    private LoginPresenter presenter;

    public static void toStartActivity(Activity activity){
        activity.startActivity(new Intent(activity,LoginActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initUI();
    }

    /**
     * 初始化UI
     */
    private void initUI() {
        presenter = new LoginPresenterImpl(this);
        tvActionTitle.setText(R.string.start_use);
        mUIHandler = new Handler();
    }

    @OnClick({R.id.tv_submit,R.id.tv_get_verification_code})
    public void onBtnClick(View view){
        switch (view.getId()){
            case R.id.tv_submit:
               presenter.login(et_phone.getText().toString().trim(),et_verification_code.getText().toString().trim());
                break;
            case R.id.tv_get_verification_code:
                presenter.getVerificationCode(et_phone.getText().toString().trim());
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mUIHandler.removeCallbacksAndMessages(null);
    }


    /**
     * 验证码计时器每一跳都会回调此方法
     */
    @Override
    public void onVerifyCodeSendSuccess() {
        // 先停止之前的，如果有
        stopVerificationCodeTimer();
        // 重置计时器时间
        mVerifyCodeTime = VERIFICATION_CODE_TIME_INTERVAL;
        // 初始化计时器
        mSMSTimer = new Timer();
        // 计时器每一秒回调
        final Runnable timerTickCallback = new Runnable() {
            @Override
            public void run() {
                onVerifyCodeTimerTick();
            }
        };
        // 初始化计时器任务
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                mUIHandler.post(timerTickCallback);
            }
        };
        // 开始执行
        mSMSTimer.schedule(task, 0, 1000);
    }

    @Override
    public void loginSuccess(UserInfoEntity userInfoEntity) {
        SharedPreUtil.getInstance().putUser(userInfoEntity);
        MineActivity.toStartActivity(LoginActivity.this);
    }

    @Override
    public void showErrorToast(final String error) {
        Toast.makeText(LoginActivity.this,error,Toast.LENGTH_SHORT).show();
    }

    /**
     * 结束倒计时，可再次获取验证码
     */
    public void stopVerificationCodeTimer() {
        if (mSMSTimer != null) {
            mSMSTimer.cancel();
            mSMSTimer = null;
        }
        mVerifyCodeTime = 0;
    }

    /**
     * 验证码计时器每一跳都会回调此方法
     */
    public void onVerifyCodeTimerTick() {
        GradientDrawable gradientDrawable = (GradientDrawable)tv_get_verification_code.getBackground();
        if (mVerifyCodeTime > 0) {
            mVerifyCodeTime -= 1;
            tv_get_verification_code.setText(mVerifyCodeTime + "s 后" +getResources().getString(R.string.send_agin));
            gradientDrawable.setColor(getResources().getColor(R.color.txt_gray));
            tv_get_verification_code.setEnabled(false);
        } else {
            tv_get_verification_code.setText(getResources().getString(R.string.send_code));
            gradientDrawable.setColor(getResources().getColor(R.color.colorPrimary));
            tv_get_verification_code.setEnabled(true);
        }
    }
}
