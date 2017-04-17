package com.dlc.intelligencecarwash.mvp.model;

import com.dlc.intelligencecarwash.base.CommonResult;

/**
 * Created by YoungeTao on 2017/4/17
 * QQ 2276559259.
 * gmail youngetao@gmail.com
 */

public interface LoginModel {

    CommonResult login(String phone,String code);
}
