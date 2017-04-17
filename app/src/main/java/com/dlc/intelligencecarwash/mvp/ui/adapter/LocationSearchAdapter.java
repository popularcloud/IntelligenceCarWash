package com.dlc.intelligencecarwash.mvp.ui.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dlc.intelligencecarwash.R;
import com.dlc.intelligencecarwash.base.Constants;
import com.dlc.intelligencecarwash.mvp.model.entity.SearchLocationEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/14.
 */

public class LocationSearchAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<SearchLocationEntity> searchLocationEntities;
    private Activity activity;
    private View view;

    public LocationSearchAdapter(Activity activity){
        this.activity = activity;
        searchLocationEntities = new ArrayList<>();
    }

    @Override
    public int getItemViewType(int position) {
        if (position + 1 == getItemCount()) { // 最后一个item设置为footerView
            return Constants.ITEMTYPE.FOOTER;
        }else{
            return Constants.ITEMTYPE.ITEM;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == Constants.ITEMTYPE.ITEM){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_location_search,parent,false);
            return new ItemViewHolder(view);
        }else{
            view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_footer,parent,false);
            return new FootViewHolder(view);
        }
      
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(position + 1 == getItemCount()){
            FootViewHolder footViewHolder = (FootViewHolder) holder;
            if(getItemCount() < 10){
                footViewHolder.itemView.setVisibility(View.GONE);
            }else{
                footViewHolder.itemView.setVisibility(View.VISIBLE);
            }
            footViewHolder.tv_load_message.setText("数据加载中...");
        }else{
            final SearchLocationEntity mySearchLocation = searchLocationEntities.get(position);
            ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
            itemViewHolder.mTitle.setText(mySearchLocation.getTitle());
            itemViewHolder.mAddress.setText(mySearchLocation.getAddress());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent myIntent = new Intent();
                    myIntent.putExtra("latitude",mySearchLocation.getLatitude());
                    myIntent.putExtra("longitude",mySearchLocation.getLongitude());
                    myIntent.putExtra("title",mySearchLocation.getTitle());
                    activity.setResult(0,myIntent);
                    activity.finish();
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return searchLocationEntities.size();
    }


    public void setDatas(List<SearchLocationEntity> datas){
        searchLocationEntities.clear();
        searchLocationEntities = datas;

        notifyDataSetChanged();
    }

    public void addDatas(List<SearchLocationEntity> datas){
        searchLocationEntities.addAll(datas);
        notifyDataSetChanged();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView mTitle;
        @BindView(R.id.tv_address)
        TextView mAddress;
        @BindView(R.id.iv_image)
        ImageView mImage;
        public ItemViewHolder(View view){
            super(view);
            ButterKnife.bind(this,view);
        }
    }

    /**
     * 底部FootView布局
     */
    public static class FootViewHolder extends  RecyclerView.ViewHolder{

        @BindView(R.id.tv_load_message)
        public TextView tv_load_message;
        public FootViewHolder(View view) {
            super(view);
            ButterKnife.bind(this,view);
        }
    }
}
