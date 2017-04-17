package com.dlc.intelligencecarwash.mvp.ui.activity;

import android.Manifest;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.dlc.intelligencecarwash.R;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by YoungeTao on 2017/4/14
 * QQ 2276559259.
 * gmail youngetao@gmail.com
 * 欢迎页
 */
public class WelcomeActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.tv_start)
    public void onBtnClick(View view){
        switch (view.getId()){
            case R.id.tv_start:
                //检查权限
                checkedPermission();
                break;
        }

    }

    /**
     * 检查权限
     */
    private void checkedPermission() {
        //所要申请的权限
        String[] perms = {Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.CAMERA};
        if (EasyPermissions.hasPermissions(this, perms)) {//检查是否获取该权限
            MainActivity.toStartActivity(this);
        } else {
            /**
             * 第二个参数是被拒绝后再次申请该权限的解释
             * 第三个参数是请求码
             * 第四个参数是要申请的权限
             */
            EasyPermissions.requestPermissions(this, "必要的权限", 0, perms);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //把申请权限的回调交由EasyPermissions处理
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        MainActivity.toStartActivity(this);
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        Toast.makeText(this,"权限申请失败!",Toast.LENGTH_SHORT).show();
        MainActivity.toStartActivity(this);
    }
}
