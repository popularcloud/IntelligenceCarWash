package com.dlc.intelligencecarwash.mvp.presenter.impl;

import com.dlc.intelligencecarwash.R;
import com.dlc.intelligencecarwash.mvp.model.entity.UserInfoEntity;
import com.dlc.intelligencecarwash.mvp.presenter.LoginPresenter;
import com.dlc.intelligencecarwash.mvp.ui.view.ILoginView;
import com.dlc.intelligencecarwash.utils.SharedPreUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by YoungeTao on 2017/4/17
 * QQ 2276559259.
 * gmail youngetao@gmail.com
 */

public class LoginPresenterImpl implements LoginPresenter{

    private ILoginView loginView;
    private String errorText;

    public LoginPresenterImpl(ILoginView loginView){
        this.loginView = loginView;
    }


    @Override
    public void getVerificationCode(String phone) {

        //验证表单数据
        if(CheckedForm(phone,null)){
            //后台成功发送验证码
            loginView.onVerifyCodeSendSuccess();
        }else{
            loginView.showErrorToast(errorText);
        }

    }

    @Override
    public void login(String phone, String code) {
        //验证表单数据
        if(CheckedForm(phone,code==null?"":code)){
            //后台成功登录
            loginView.loginSuccess( new UserInfoEntity("懒洋洋",null,"12966668888",500d));
        }else{
            loginView.showErrorToast(errorText);
        }
    }

    private boolean CheckedForm(String phone,String code) {
        if(phone == null || "".equals(phone)){
            errorText = "请输入您的手机号码";
            return false;
        }

        if(!isMobile(phone)){
            errorText = "请输入正确的手机号码";
            return false;
        }

        if(code != null && "".equals(code)){
            errorText = "请输入短信验证码";
            return false;
        }
        return true;
    }

    /**
     * 手机号验证
     */
    public static boolean isMobile(final String str) {
        Pattern p = null;
        Matcher m = null;
        boolean b = false;
        p = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
        m = p.matcher(str);
        b = m.matches();
        return b;
    }
}
