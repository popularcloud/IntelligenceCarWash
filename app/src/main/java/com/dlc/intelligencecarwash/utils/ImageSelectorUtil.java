package com.dlc.intelligencecarwash.utils;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.dlc.intelligencecarwash.mvp.ui.adapter.GridImageAdapter;
import com.luck.picture.lib.model.FunctionConfig;
import com.luck.picture.lib.model.PictureConfig;
import com.yalantis.ucrop.entity.LocalMedia;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by YoungeTao on 2017/3/28.
 * 图片选择管理类
 */

public class ImageSelectorUtil {

    private GridImageAdapter adapter;
    public List<LocalMedia> selectMedia = new ArrayList<>();
    private Context mContext;
    private ImageSelectCallBack imageSelectCallBack;
    public ImageSelectorUtil(Context context){
        mContext = context;
    }


    public GridImageAdapter init(){
        adapter = new GridImageAdapter(mContext, onAddPicClickListener);
        adapter.setOnItemClickListener(new GridImageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                // 这里可预览图片
                PictureConfig.getPictureConfig().externalPicturePreview(mContext, position, selectMedia);
            }
        });
        return adapter;
    }

    public void initSingleAlbum(ImageSelectCallBack imageSelectCallBack){
        // 这里可预览图片
        PictureConfig.getPictureConfig().externalPicturePreview(mContext, 0, selectMedia);
        onAddPicClickListener.onAddPicClick(2, 0);
        this.imageSelectCallBack = imageSelectCallBack;
    }

    public void initSingleTakePhoto(ImageSelectCallBack imageSelectCallBack){
        // 这里可预览图片
        PictureConfig.getPictureConfig().externalPicturePreview(mContext, 0, selectMedia);
        onAddPicClickListener.onAddPicClick(3, 0);
        this.imageSelectCallBack = imageSelectCallBack;
    }

    private GridImageAdapter.onAddPicClickListener onAddPicClickListener = new GridImageAdapter.onAddPicClickListener() {
        @Override
        public void onAddPicClick(int type, int position) {
            FunctionConfig config = new FunctionConfig();
            switch (type) {
                case 0: // 进入相册
                    config.setType(1);  //1.图片选择  2.视频选择
                    config.setCompress(true); //是否压缩
                    //config.setEnablePixelCompress(true);
                    //config.setEnableQualityCompress(true);
                    config.setMaxSelectNum(6);//设置图片的最大选择
                    config.setSelectMode(1); //1.多选  2.单选
                    config.setShowCamera(true);  //显示相机
                    config.setEnablePreview(true);  //是否预览
                    config.setEnableCrop(false); //是否裁剪
                    config.setCheckNumMode(true); //选中风格  trueQQ风格
                    config.setCompressQuality(100);
                    config.setImageSpanCount(4); //每行显示的图片个数
                    config.setSelectMedia(selectMedia);  //已选图片合集
                    config.setCompressFlag(2); // 1是系统自带压缩 2是鲁班压缩
                    // 先初始化参数配置，在启动相册
                    PictureConfig.init(config);
                    PictureConfig.getPictureConfig().openPhoto(mContext, resultCallback);
                    break;
                case 1:
                    // 删除图片
                    selectMedia.remove(position);
                    adapter.notifyItemRemoved(position);
                    break;
                case 2:  //只显示相册
                    config.setType(1);
                    config.setCompress(true);
                    config.setEnablePixelCompress(true);
                    config.setEnableQualityCompress(true);
                    config.setMaxSelectNum(1);//设置图片的最大选择
                    config.setSelectMode(2); //1.多选  2.单选
                    config.setShowCamera(false); //不显示相机
                    config.setEnablePreview(true);
                    config.setEnableCrop(false);
                    config.setCheckNumMode(true);
                    config.setCompressQuality(100);
                    config.setImageSpanCount(4);
                    config.setSelectMedia(selectMedia);
                    config.setCompressFlag(2);
                    // 先初始化参数配置，在启动相册
                    PictureConfig.init(config);
                    PictureConfig.getPictureConfig().openPhoto(mContext, resultCallbackTow);
                    break;
                case 3:  //只拍照
                    config.setType(1);
                    config.setCompress(true);
                    config.setEnablePixelCompress(true);
                    config.setEnableQualityCompress(true);
                    config.setMaxSelectNum(1);//设置图片的最大选择
                    config.setSelectMode(2); //1.多选  2.单选
                    config.setShowCamera(true);
                    config.setEnablePreview(true);
                    config.setEnableCrop(false);
                    config.setCheckNumMode(true);
                    config.setCompressQuality(100);
                    config.setImageSpanCount(4);
                    config.setSelectMedia(selectMedia);
                    config.setCompressFlag(2);
                    // 先初始化参数配置，在启动摄像头
                    PictureConfig.init(config);
                    PictureConfig.getPictureConfig().startOpenCamera(mContext, resultCallbackThree);
                    break;
            }
        }
    };


    /**
     * 图片回调方法（单选相册 不拍照）
     */
    private PictureConfig.OnSelectResultCallback resultCallbackTow = new PictureConfig.OnSelectResultCallback() {
        @Override
        public void onSelectSuccess(List<LocalMedia> resultList) {
            selectMedia.clear();
            selectMedia = resultList;
            Log.i("callBack_result", selectMedia.size() + "");
            if (selectMedia != null && selectMedia.size() != 0) {
                imageSelectCallBack.selectOk(selectMedia.get(0));
            }else {
                imageSelectCallBack.selectFail("获取图片失败");
            }
        }
    };

    /**
     * 图片回调方法（只拍照）
     */
    private PictureConfig.OnSelectResultCallback resultCallbackThree = new PictureConfig.OnSelectResultCallback() {
        @Override
        public void onSelectSuccess(List<LocalMedia> resultList) {
            selectMedia.clear();
            selectMedia = resultList;
            Log.i("callBack_result", selectMedia.size() + "");
            if (selectMedia != null && selectMedia.size() != 0) {
                imageSelectCallBack.selectOk(selectMedia.get(0));
            }else{
                imageSelectCallBack.selectFail("获取图片失败");
            }
        }
    };

    /**
     * 图片回调方法( 拍照多选)
     */
    private PictureConfig.OnSelectResultCallback resultCallback = new PictureConfig.OnSelectResultCallback() {
        @Override
        public void onSelectSuccess(List<LocalMedia> resultList) {
            selectMedia = resultList;
            Log.i("callBack_result", selectMedia.size() + "");
            if (selectMedia != null) {
                adapter.setList(selectMedia);
                adapter.notifyDataSetChanged();
            }
        }
    };

    public void destroy(){
        selectMedia.clear();
    }

    public interface ImageSelectCallBack {

        void selectOk(Object obj);

        void selectFail(String failMessage);
    }
}
