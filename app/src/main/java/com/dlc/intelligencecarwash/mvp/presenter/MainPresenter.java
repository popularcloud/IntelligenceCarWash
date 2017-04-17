package com.dlc.intelligencecarwash.mvp.presenter;

/**
 * Created by YoungeTao on 2017/4/17
 * QQ 2276559259.
 * gmail youngetao@gmail.com
 */

public interface MainPresenter {

    /**
     *
     * @param latitude 纬度
     * @param longitude 经度
     * @param distance 范围(米)
     */
    void getCarWashDatas(Double latitude,Double longitude,int distance);
}
