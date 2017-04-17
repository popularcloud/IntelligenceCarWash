package com.dlc.intelligencecarwash.mvp.ui.view;

import com.amap.api.maps.model.MarkerOptions;

import java.util.List;

/**
 * Created by YoungeTao on 2017/4/17
 * QQ 2276559259.
 * gmail youngetao@gmail.com
 */

public interface IMainView {

    /**
     * 将marker添加到地图上
     * @param markerOptionsList
     */
    void setMarkerToMap(List<MarkerOptions> markerOptionsList);

}
