package com.dlc.intelligencecarwash.mvp.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dlc.intelligencecarwash.R;
import com.dlc.intelligencecarwash.mvp.model.entity.ConsumeRecordEntity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/14.
 */

public class ConsumeRecordAdapter extends RecyclerView.Adapter<ConsumeRecordAdapter.ViewHolder>{

    private List<ConsumeRecordEntity> recordList;

    public ConsumeRecordAdapter(){
       recordList = new ArrayList<>();
       recordList.add(new ConsumeRecordEntity("20170315","456464","30:00","100"));
       recordList.add(new ConsumeRecordEntity("20170315","456464","30:00","100"));
       recordList.add(new ConsumeRecordEntity("20170315","456464","30:00","100"));
       recordList.add(new ConsumeRecordEntity("20170315","456464","30:00","100"));
       recordList.add(new ConsumeRecordEntity("20170315","456464","30:00","100"));
       recordList.add(new ConsumeRecordEntity("20170315","456464","30:00","100"));
       recordList.add(new ConsumeRecordEntity("20170315","456464","30:00","100"));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_consume_record,parent,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ConsumeRecordEntity consumeRecord = recordList.get(position);
       holder.tv_use_date.setText(consumeRecord.getUseDate());
       holder.tv_washCar_id.setText(consumeRecord.getWashCarId());
       holder.tv_use_time.setText(consumeRecord.getUseTime());
       holder.tv_money.setText(consumeRecord.getMoney());
    }

    @Override
    public int getItemCount() {
        return recordList.size();
    }


    public void setDatas(List<ConsumeRecordEntity> datas){
        recordList.clear();
        recordList = datas;

        notifyDataSetChanged();
    }

    public void addDatas(List<ConsumeRecordEntity> datas){
        recordList.addAll(datas);
        notifyDataSetChanged();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_use_date)
        TextView tv_use_date;
        @BindView(R.id.tv_washCar_id)
        TextView tv_washCar_id;
        @BindView(R.id.tv_use_time)
        TextView tv_use_time;
        @BindView(R.id.tv_money)
        TextView tv_money;

        public ViewHolder(View view){
            super(view);
            ButterKnife.bind(this,view);
        }
    }
}
