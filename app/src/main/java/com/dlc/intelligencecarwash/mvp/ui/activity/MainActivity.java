package com.dlc.intelligencecarwash.mvp.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.Circle;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.dlc.intelligencecarwash.R;
import com.dlc.intelligencecarwash.base.BaseActivity;
import com.dlc.intelligencecarwash.base.Constants;
import com.dlc.intelligencecarwash.mvp.presenter.MainPresenter;
import com.dlc.intelligencecarwash.mvp.presenter.impl.MainPresenterImpl;
import com.dlc.intelligencecarwash.mvp.ui.view.IMainView;
import com.dlc.intelligencecarwash.widget.ShowUseWashCarDialog;
import com.uuzuche.lib_zxing.activity.CaptureActivity;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by YoungeTao on 2017/4/13
 * QQ 2276559259.
 * gmail youngetao@gmail.com
 * 首页
 */

public class MainActivity extends BaseActivity implements AMap.OnMyLocationChangeListener,IMainView{

    @BindView(R.id.map)
    MapView mMapView = null; //地图对象
    AMap aMap = null; //地图控制器对象
    private List<MarkerOptions> markerList; //markeroptions集合
    private Double latitude; //纬度
    private Double longitude; //经度
    private Circle circle;  //圆形范围遮罩
    private MainPresenter mainPresenter;

    public static void toStartActivity(Activity activity){
        activity.startActivity(new Intent(activity,MainActivity.class));
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        markerList = new ArrayList();
        mainPresenter =new MainPresenterImpl(this);
        initUI(savedInstanceState);
    }

    private void initUI(Bundle savedInstanceState) {
        //初始化地图
        initMap(savedInstanceState);

    }

    /**
     * 初始化地图
     */
    private void initMap(Bundle savedInstanceState) {
        //创建地图
        mMapView.onCreate(savedInstanceState);
        //获取地图控制器对象
        if (aMap == null) {
            aMap = mMapView.getMap();
        }
        //设置地图缩放级别 参考值为3-19，不支持自定义
        aMap.moveCamera(CameraUpdateFactory.zoomTo(12));
        startLocation();
    }

    /**
     * 开启定位
     */
    private void startLocation(){
        //设置自定义定位
        MyLocationStyle myLocationStyle;
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATE);
        myLocationStyle.interval(10000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.getUiSettings().setMyLocationButtonEnabled(true);//设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        myLocationStyle.myLocationIcon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                .decodeResource(getResources(),R.mipmap.ic_location)));
        //设置SDK 自带定位消息监听
        aMap.setOnMyLocationChangeListener(this);
    }

    /**
     * 显示圆形范围遮罩
     */
    private void showCircleMask(){
        if(aMap != null && latitude != null  && latitude != null){

            if(circle != null){
                circle.remove();
            }
            //绘制圆形区域(以当前位置为中心)
            LatLng latLng = new LatLng(latitude,longitude);
            circle = aMap.addCircle(
                    new CircleOptions().
                            center(latLng).
                            radius(4000).
                            fillColor(Color.argb(35, 1, 1, 1)).
                            strokeColor(Color.argb(35, 1, 1, 1)).
                            strokeWidth(15));
        }
    }

    @OnClick({R.id.iv_mine,R.id.tv_scan,R.id.iv_search})
    public void onBtnClick(View view){
        switch (view.getId()){
            case R.id.iv_mine:  //我的
                LoginActivity.toStartActivity(MainActivity.this);
                break;
            case R.id.iv_search: //搜索
                Intent myIntent = new Intent(this,LocationSearchActivity.class);
                myIntent.putExtra("latitude",latitude);
                myIntent.putExtra("longitude",longitude);
                startActivityForResult(myIntent, Constants.REQUESTCODE.SEARCHLOCATION);
                break;
            case R.id.tv_scan: //扫描
                Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
                startActivityForResult(intent, Constants.REQUESTCODE.REQUESTZXING);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        /**
         * 处理二维码扫描结果
         */
        if (requestCode == Constants.REQUESTCODE.REQUESTZXING) {
            if (null != data) {//处理扫描结果（在界面上显示）
                Bundle bundle = data.getExtras();
                if (bundle == null) {
                    return;
                }
                if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_SUCCESS) {
                    String result = bundle.getString(CodeUtils.RESULT_STRING);
                    Toast.makeText(this, "解析结果:" + result, Toast.LENGTH_LONG).show();

                    final ShowUseWashCarDialog confirmDialog = new ShowUseWashCarDialog(MainActivity.this);
                    confirmDialog.show();
                    confirmDialog.setClicklistener(new ShowUseWashCarDialog.ClickListenerInterface() {
                        @Override
                        public void doConfirm() {
                            confirmDialog.dismiss();
                            PayActivity.toStartActivity(MainActivity.this);
                        }

                        @Override
                        public void doCancel() {
                            confirmDialog.dismiss();
                        }
                    });

                } else if (bundle.getInt(CodeUtils.RESULT_TYPE) == CodeUtils.RESULT_FAILED) {
                    Toast.makeText(MainActivity.this, "解析二维码失败", Toast.LENGTH_LONG).show();
                }
            }
        }else if(requestCode == Constants.REQUESTCODE.SEARCHLOCATION){  //搜索位置
            if(data == null){
                return;
            }
            latitude = data.getDoubleExtra("latitude",0);
            longitude = data.getDoubleExtra("longitude",0);
            String title = data.getStringExtra("title");
            aMap.clear();
            LatLng latLng1 = new LatLng(latitude,longitude);
            MarkerOptions markerOption1 = new MarkerOptions();
            markerOption1.position(latLng1);
            markerOption1.title("东莞市").snippet(title);
            markerOption1.draggable(true);//设置Marker可拖动
            markerOption1.icon(BitmapDescriptorFactory.fromBitmap(BitmapFactory
                    .decodeResource(getResources(),R.mipmap.ic_location)));
            // 将Marker设置为贴地显示，可以双指下拉地图查看效果
            markerOption1.setFlat(true);//设置marker平贴地图效果
            aMap.addMarker(markerOption1);

            showCircleMask();
            //参数依次是：视角调整区域的中心点坐标、希望调整到的缩放级别、俯仰角0°~45°（垂直与地图时为0）、偏航角 0~360° (正北方为0)
            CameraUpdate mCameraUpdate = CameraUpdateFactory.newCameraPosition(new CameraPosition(new LatLng(latitude,longitude),12,0,0));
            aMap.moveCamera(mCameraUpdate);
        }
    }

    @Override
    public void onMyLocationChange(Location location) {
        if(location != null){
            latitude = location.getLatitude();
            longitude = location.getLongitude();
            showCircleMask();
            mainPresenter.getCarWashDatas(latitude,longitude,4000);
        }
    }

    @Override
    public void setMarkerToMap(List<MarkerOptions> markerOptionsList) {
        markerList = markerOptionsList;
        //加载marker到地图
        for (MarkerOptions m : markerList){
            aMap.addMarker(m);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }
}
