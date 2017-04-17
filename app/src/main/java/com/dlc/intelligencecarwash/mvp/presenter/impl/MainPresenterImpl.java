package com.dlc.intelligencecarwash.mvp.presenter.impl;

import android.content.Context;
import android.graphics.BitmapFactory;

import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.dlc.intelligencecarwash.R;
import com.dlc.intelligencecarwash.mvp.presenter.MainPresenter;
import com.dlc.intelligencecarwash.mvp.ui.view.IMainView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YoungeTao on 2017/4/17
 * QQ 2276559259.
 * gmail youngetao@gmail.com
 */

public class MainPresenterImpl implements MainPresenter {

    private IMainView mainView;

    public MainPresenterImpl(IMainView mainView){
        this.mainView = mainView;
    }

    @Override
    public void getCarWashDatas(Double latitude, Double longitude, int distance) {

        List<MarkerOptions> markerList = new ArrayList<>();
        Context context = (Context) mainView;
        LatLng latLng1 = new LatLng(22.983056,113.734466);
        MarkerOptions markerOption1 = new MarkerOptions();
        markerOption1.position(latLng1);
        markerOption1.title("东莞市").snippet("南城车站：22.983056,113.734466");
        markerOption1.draggable(true);//设置Marker可拖动
        markerOption1.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                .decodeResource(context.getResources(), R.mipmap.ic_car)));
        // 将Marker设置为贴地显示，可以双指下拉地图查看效果
        markerOption1.setFlat(true);//设置marker平贴地图效果

        LatLng latLng2 = new LatLng(22.988784,113.737878);
        MarkerOptions markerOption2 = new MarkerOptions();
        markerOption2.position(latLng2);
        markerOption2.title("东莞市").snippet("嘉荣超市：22.988784,113.737878");
        markerOption2.draggable(true);//设置Marker可拖动
        markerOption2.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                .decodeResource(context.getResources(),R.mipmap.ic_car)));
        // 将Marker设置为贴地显示，可以双指下拉地图查看效果
        markerOption2.setFlat(true);//设置marker平贴地图效果

        LatLng latLng3 = new LatLng(22.989377,113.717686);
        MarkerOptions markerOption3 = new MarkerOptions();
        markerOption3.position(latLng3);
        markerOption3.title("东莞市").snippet("南城体育公园：22.987362,113.753928");
        markerOption3.draggable(true);//设置Marker可拖动
        markerOption3.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                .decodeResource(context.getResources(),R.mipmap.ic_car)));
        // 将Marker设置为贴地显示，可以双指下拉地图查看效果
        markerOption1.setFlat(true);//设置marker平贴地图效果
        markerList.add(markerOption1);
        markerList.add(markerOption2);
        markerList.add(markerOption3);

        mainView.setMarkerToMap(markerList);
    }
}
