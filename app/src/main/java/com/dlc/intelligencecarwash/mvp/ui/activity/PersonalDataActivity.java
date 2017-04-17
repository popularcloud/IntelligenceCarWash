package com.dlc.intelligencecarwash.mvp.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dlc.intelligencecarwash.R;
import com.dlc.intelligencecarwash.base.BaseActivity;
import com.dlc.intelligencecarwash.utils.ImageSelectorUtil;
import com.dlc.intelligencecarwash.widget.ActionSheetDialog;
import com.dlc.intelligencecarwash.widget.CustomerDailogUtil;
import com.yalantis.ucrop.entity.LocalMedia;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by YoungeTao on 2017/4/14
 * QQ 2276559259.
 * gmail youngetao@gmail.com
 * 个人资料
 */
public class PersonalDataActivity extends BaseActivity{

    @BindView(R.id.civ_header)
    CircleImageView civ_header;
    //dialog项
    private ActionSheetDialog mDialog;

    public static void toStartActivity(Activity activity){
        activity.startActivity(new Intent(activity,PersonalDataActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_data);
        ButterKnife.bind(this);
        initUI();
    }

    private void initUI() {
        tvActionTitle.setText(R.string.personal_data);
    }

    @OnClick({R.id.rl_header,R.id.rl_nick_name})
    public void onBtnClick(View view){
        switch (view.getId()){
            case R.id.rl_header:
                mDialog = initDialog();
                avatarDialog(mDialog);
                break;
            case R.id.rl_nick_name:
                CustomerDailogUtil.initNickName(this).show();
                break;
        }
    }

    /**
     * dialog项
     * @return
     */
    private ActionSheetDialog initDialog() {
        return new ActionSheetDialog(PersonalDataActivity.this).builder().setCancelable(false).setCanceledOnTouchOutside(true);
    }

    /**
     * 选择头像
     * @param dialog
     */
    private void avatarDialog(ActionSheetDialog dialog) {
        dialog.addSheetItem("拍照",
                ActionSheetDialog.SheetItemColor.Blue,
                new ActionSheetDialog.OnSheetItemClickListener() {
                    public void onClick(int which)
                    {
                        // 拍照
                        new ImageSelectorUtil(PersonalDataActivity.this).initSingleTakePhoto(new ImageSelectorUtil.ImageSelectCallBack() {
                            @Override
                            public void selectOk(Object obj) {
                                LocalMedia localMedia = (LocalMedia) obj;
                                Glide.with(PersonalDataActivity.this)
                                        .load(localMedia.getPath())
                                        .asBitmap().centerCrop()
                                        .placeholder(R.mipmap.ic_default_head)
                                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                                        .into(civ_header);
                            }

                            @Override
                            public void selectFail(String failMessage) {
                                Toast.makeText(PersonalDataActivity.this,failMessage,Toast.LENGTH_SHORT);
                            }
                        });
                    }
                })
                .addSheetItem("从相册选择",
                        ActionSheetDialog.SheetItemColor.Blue,
                        new ActionSheetDialog.OnSheetItemClickListener() {
                            public void onClick(int which)
                            {
                                // 相册选取
                                new ImageSelectorUtil(PersonalDataActivity.this).initSingleAlbum(new ImageSelectorUtil.ImageSelectCallBack() {
                                    @Override
                                    public void selectOk(Object obj) {
                                        LocalMedia localMedia = (LocalMedia) obj;
                                        Glide.with(PersonalDataActivity.this)
                                                .load(localMedia.getPath())
                                                .asBitmap().centerCrop()
                                                .placeholder(R.mipmap.ic_default_head)
                                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                                .into(civ_header);
                                    }

                                    @Override
                                    public void selectFail(String failMessage) {
                                        Toast.makeText(PersonalDataActivity.this,failMessage,Toast.LENGTH_SHORT);
                                    }
                                });

                            }
                        }).show();
    }


   /* @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {

    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {

    }*/
}
