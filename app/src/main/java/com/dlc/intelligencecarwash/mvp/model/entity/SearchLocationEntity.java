package com.dlc.intelligencecarwash.mvp.model.entity;

/**
 * Created by YoungeTao on 2017/4/15
 * QQ 2276559259.
 * gmail youngetao@gmail.com
 */

public class SearchLocationEntity {

    private String title;
    private String address;
    private double latitude;
    private double longitude;

    public SearchLocationEntity(String title, String address, double latitude, double longitude) {
        this.title = title;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
