package com.dlc.intelligencecarwash.mvp.model.entity;

/**
 * Created by YoungeTao on 2017/4/14
 * QQ 2276559259.
 * gmail youngetao@gmail.com
 */

public class CampaignEntity {

    private String title;
    private String detail;
    private String time;
    private String imageUrl;

    public CampaignEntity(String title, String detail, String time, String imageUrl) {
        this.title = title;
        this.detail = detail;
        this.time = time;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
