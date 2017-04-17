package com.dlc.intelligencecarwash.mvp.ui.view;

import com.dlc.intelligencecarwash.mvp.model.entity.UserInfoEntity;

/**
 * Created by YoungeTao on 2017/4/17
 * QQ 2276559259.
 * gmail youngetao@gmail.com
 */

public interface ILoginView {

    /**
     * 后台成功发送验证码后刷新UI
     */
    void onVerifyCodeSendSuccess();

    /**
     * 后台成功发送验证码后刷新UI
     */
    void loginSuccess(UserInfoEntity userInfoEntity);

    /**
     * 显示错误的toast提示
     * @param error
     */
    void showErrorToast(String error);
}
