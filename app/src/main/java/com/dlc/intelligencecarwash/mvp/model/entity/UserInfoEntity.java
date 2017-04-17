package com.dlc.intelligencecarwash.mvp.model.entity;

import java.io.Serializable;

/**
 * Created by YoungeTao on 2017/4/17
 * QQ 2276559259.
 * gmail youngetao@gmail.com
 * 用户信息类实体
 */

public class UserInfoEntity implements Serializable{

    private String nickName;
    private String headerImg;
    private String phone;
    private Double accountAmount; //账号余额

    public UserInfoEntity(){}

    public UserInfoEntity(String nickName, String headerImg, String phone,Double accountAmount) {
        this.nickName = nickName;
        this.headerImg = headerImg;
        this.phone = phone;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getHeaderImg() {
        return headerImg;
    }

    public void setHeaderImg(String headerImg) {
        this.headerImg = headerImg;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Double getAccountAmount() {
        return accountAmount;
    }

    public void setAccountAmount(Double accountAmount) {
        this.accountAmount = accountAmount;
    }
}
