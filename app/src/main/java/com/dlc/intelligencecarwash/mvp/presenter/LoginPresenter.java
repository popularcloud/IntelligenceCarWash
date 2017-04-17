package com.dlc.intelligencecarwash.mvp.presenter;

/**
 * Created by YoungeTao on 2017/4/17
 * QQ 2276559259.
 * gmail youngetao@gmail.com
 */

public interface LoginPresenter {

    /**
     * 获取短信验证码
     * @param phone 手机号
     */
    void getVerificationCode(String phone);

    /**
     * 登录
     * @param phone 手机号
     * @param code 短信验证码
     */
    void login(String phone,String code);

}
