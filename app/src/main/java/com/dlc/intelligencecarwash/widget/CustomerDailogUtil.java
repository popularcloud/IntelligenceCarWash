package com.dlc.intelligencecarwash.widget;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.dlc.intelligencecarwash.R;


/**
 * Created by YoungeTao on 2017/3/29
 * QQ 2276559259.
 */

public class CustomerDailogUtil {

    static AlertDialog.Builder builder;

    public static AlertDialog.Builder initNickName(final Context context){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("修改昵称");
        //通过布局填充器获login_layout
        View view = LayoutInflater.from(context).inflate(
                R.layout.customer_dailog_common, null);
        builder.setView(view);
        builder.setCancelable(false);//设置为不可取消
        //设置正面按钮，并做事件处理
        builder.setPositiveButton("修改", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(context, "修改成功",Toast.LENGTH_SHORT).show();
                dialogInterface.dismiss();
            }
        });
        //设置反面按钮，并做事件处理
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
       return builder;
    }

    public static void destroy(){
        builder = null;
    }

}
