package com.dlc.intelligencecarwash.mvp.ui.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dlc.intelligencecarwash.R;
import com.dlc.intelligencecarwash.mvp.model.entity.CampaignEntity;
import com.dlc.intelligencecarwash.mvp.ui.activity.CommonWebViewActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/3/14.
 */

public class CampaignAdapter extends RecyclerView.Adapter<CampaignAdapter.ViewHolder>{

    private List<CampaignEntity> campaignEntityList;
    private Activity activity;

    public CampaignAdapter(Activity activity){
        this.activity = activity;
       campaignEntityList = new ArrayList<>();
       campaignEntityList.add(new CampaignEntity("新年新气象","彩丽所有员工祝大家新年大吉，万事如意","2017-01-01",""));
       campaignEntityList.add(new CampaignEntity("新年新气象","彩丽所有员工祝大家新年大吉，万事如意","2017-01-01",""));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_campaign,parent,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
       CampaignEntity myMessage = campaignEntityList.get(position);
       holder.mTitle.setText(myMessage.getTitle());
       holder.mDetail.setText(myMessage.getDetail());
       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               CommonWebViewActivity.toStartActivity(activity,"活动详情");
           }
       });
    }

    @Override
    public int getItemCount() {
        return campaignEntityList.size();
    }


    public void setDatas(List<CampaignEntity> datas){
        campaignEntityList.clear();
        campaignEntityList = datas;

        notifyDataSetChanged();
    }

    public void addDatas(List<CampaignEntity> datas){
        campaignEntityList.addAll(datas);
        notifyDataSetChanged();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_title)
        TextView mTitle;
        @BindView(R.id.tv_detail)
        TextView mDetail;
        @BindView(R.id.iv_image)
        ImageView mImage;
        public ViewHolder(View view){
            super(view);
            ButterKnife.bind(this,view);
        }
    }
}
