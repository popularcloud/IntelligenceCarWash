package com.dlc.intelligencecarwash.mvp.model;

import com.dlc.intelligencecarwash.base.CommonResult;

/**
 * Created by YoungeTao on 2017/4/17
 * QQ 2276559259.
 * gmail youngetao@gmail.com
 */

public interface MainModel {

    /**
     *
     * @param latitude 纬度
     * @param longitude 经度
     * @param distance 范围(米)
     */
    CommonResult getCarWashDatas(Double latitude,Double longitude,int distance);
}
