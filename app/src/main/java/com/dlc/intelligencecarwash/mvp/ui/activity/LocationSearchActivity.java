package com.dlc.intelligencecarwash.mvp.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.core.PoiItem;
import com.amap.api.services.geocoder.GeocodeResult;
import com.amap.api.services.geocoder.GeocodeSearch;
import com.amap.api.services.geocoder.RegeocodeQuery;
import com.amap.api.services.geocoder.RegeocodeResult;
import com.amap.api.services.poisearch.PoiResult;
import com.amap.api.services.poisearch.PoiSearch;
import com.dlc.intelligencecarwash.R;
import com.dlc.intelligencecarwash.base.BaseActivity;
import com.dlc.intelligencecarwash.mvp.model.entity.SearchLocationEntity;
import com.dlc.intelligencecarwash.mvp.ui.adapter.LocationSearchAdapter;
import com.dlc.intelligencecarwash.widget.FullyLinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LocationSearchActivity extends BaseActivity  implements PoiSearch.OnPoiSearchListener,GeocodeSearch.OnGeocodeSearchListener {

    @BindView(R.id.et_search)
    EditText et_search;
    @BindView(R.id.tv_location)
    TextView tv_location;
    @BindView(R.id.rv_common)
    RecyclerView mRecyclerView;
    private LocationSearchAdapter mLocationSearchAdapter;
    private PoiSearch.Query query;
    private PoiSearch poiSearch;
    private String searchText;
    private int presentPageNumber = 0;
    private int lastVisibleItem;
    private FullyLinearLayoutManager f;
    private GeocodeSearch geocoderSearch;

    public static void toStartActivity(Activity activity,double latitude,double longitude){
        Intent myIntent = new Intent(activity,LocationSearchActivity.class);
        myIntent.putExtra("latitude",latitude);
        myIntent.putExtra("longitude",longitude);
        activity.startActivity(new Intent(myIntent));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_search);
        ButterKnife.bind(this);
        initUI();
    }

    private void initUI() {

        double latitude = getIntent().getDoubleExtra("latitude",0);
        double longitude = getIntent().getDoubleExtra("longitude",0);
        f = new FullyLinearLayoutManager(this);
        mRecyclerView.setLayoutManager(f);
        mLocationSearchAdapter = new LocationSearchAdapter(this);
        mRecyclerView.setAdapter(mLocationSearchAdapter);

        geocoderSearch = new GeocodeSearch(this);
        geocoderSearch.setOnGeocodeSearchListener(this);
        // 第一个参数表示一个Latlng，第二参数表示范围多少米，第三个参数表示是火系坐标系还是GPS原生坐标系
        RegeocodeQuery query = new RegeocodeQuery(new LatLonPoint(latitude,longitude), 200,GeocodeSearch.AMAP);

        geocoderSearch.getFromLocationAsyn(query);
        
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                searchText = editable.toString().trim();
                presentPageNumber = 0;
                search(editable.toString().trim());
            }
        });

        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && (lastVisibleItem + 1) ==(mLocationSearchAdapter.getItemCount())) {
                    if(searchText != null && !"".equals(searchText)){
                        presentPageNumber++;
                        search(searchText);
                    }

                }
            }
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem =f.findLastVisibleItemPosition();
            }
        });

    }

    private void search(String searchContent){

        //keyWord表示搜索字符串，
        //第二个参数表示POI搜索类型，二者选填其一，
        //POI搜索类型共分为以下20种：汽车服务|汽车销售|
        //汽车维修|摩托车服务|餐饮服务|购物服务|生活服务|体育休闲服务|医疗保健服务|
        //住宿服务|风景名胜|商务住宅|政府机构及社会团体|科教文化服务|交通设施服务|
        //金融保险服务|公司企业|道路附属设施|地名地址信息|公共设施
        //cityCode表示POI搜索区域，可以是城市编码也可以是城市名称，也可以传空字符串，空字符串代表全国在全国范围内进行搜索
        String poiType = "汽车服务|汽车销售|汽车维修|摩托车服务|餐饮服务|购物服务|生活服务|体育休闲服务|医疗保健服务|住宿服务|风景名胜|商务住宅|政府机构及社会团体|科教文化服务|交通设施服务|金融保险服务|公司企业|道路附属设施|地名地址信息|公共设施";
        query = new PoiSearch.Query(searchContent,"","东莞");
        query.setPageSize(10);// 设置每页最多返回多少条poiitem
        query.setPageNum(presentPageNumber);//设置查询页码

        poiSearch = new PoiSearch(this, query);
        poiSearch.setOnPoiSearchListener(this);

        //提交异步查询请求
        poiSearch.searchPOIAsyn();
    }


    @Override
    public void onPoiSearched(PoiResult poiResult, int rCode) {
        if(rCode == 1000){
           List<SearchLocationEntity> searchList = new ArrayList<>();
           List<PoiItem> list = poiResult.getPois();
            for(PoiItem poiTtem : list){
                SearchLocationEntity searchLocation = new SearchLocationEntity(poiTtem.getTitle(),poiTtem.getProvinceName()+poiTtem.getCityName()+poiTtem.getBusinessArea(),poiTtem.getLatLonPoint().getLatitude(),poiTtem.getLatLonPoint().getLongitude());
                searchList.add(searchLocation);
            }
            if(presentPageNumber == 0){
                mLocationSearchAdapter.setDatas(searchList);
                Log.d("aaa",list.size()+"1");
            }else{
                mLocationSearchAdapter.addDatas(searchList);
                Log.d("aaa",list.size()+"1");
            }

        }
    }

    @Override
    public void onPoiItemSearched(PoiItem poiItem, int rCode) {

    }

    @Override
    public void onRegeocodeSearched(RegeocodeResult regeocodeResult, int iCode) {
        if(iCode == 1000){
           String address = regeocodeResult.getRegeocodeAddress().getFormatAddress();
            tv_location.setText("我的位置:"+address);
        }
    }

    @Override
    public void onGeocodeSearched(GeocodeResult geocodeResult, int i) {

    }
}
