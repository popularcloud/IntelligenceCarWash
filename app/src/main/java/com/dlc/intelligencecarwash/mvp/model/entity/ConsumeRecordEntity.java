package com.dlc.intelligencecarwash.mvp.model.entity;

/**
 * Created by YoungeTao on 2017/4/13
 * QQ 2276559259.
 * gmail youngetao@gmail.com
 */

public class ConsumeRecordEntity {

    private String useDate;
    private String washCarId;
    private String useTime;
    private String money;

    public ConsumeRecordEntity(String useDate, String washCarId, String useTime, String money) {
        this.useDate = useDate;
        this.washCarId = washCarId;
        this.useTime = useTime;
        this.money = money;
    }

    public String getUseDate() {
        return useDate;
    }

    public void setUseDate(String useDate) {
        this.useDate = useDate;
    }

    public String getWashCarId() {
        return washCarId;
    }

    public void setWashCarId(String washCarId) {
        this.washCarId = washCarId;
    }

    public String getUseTime() {
        return useTime;
    }

    public void setUseTime(String useTime) {
        this.useTime = useTime;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
