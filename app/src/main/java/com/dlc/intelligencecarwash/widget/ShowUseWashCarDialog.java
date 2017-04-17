package com.dlc.intelligencecarwash.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.dlc.intelligencecarwash.R;
import com.dlc.intelligencecarwash.mvp.ui.activity.PayActivity;

/**
 * Created by YoungeTao on 2017/4/15
 * QQ 2276559259.
 * gmail youngetao@gmail.com
 */

public class ShowUseWashCarDialog extends Dialog{

    private Context context;
    private ClickListenerInterface clickListenerInterface;
    private TextView tvSubmit;

    public ShowUseWashCarDialog(Context context) {
        super(context,R.style.ActionSheetDialogStyle);
        this.context = context;
     }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        init();
    }

    public void init() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dialog_use_wash_car, null);
        setContentView(view);

        tvSubmit = (TextView) view.findViewById(R.id.tv_submit);
        tvSubmit.setOnClickListener(new clickListener());
        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 0.8); // 高度设置为屏幕的0.6
        dialogWindow.setAttributes(lp);
    }

    public void setClicklistener(ClickListenerInterface clickListenerInterface) {
        this.clickListenerInterface = clickListenerInterface;
    }

    private class clickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            String text = (String) ((TextView)v).getText();
            switch (text) {
                case "开始使用":
                   // clickListenerInterface.doConfirm();
                    tvSubmit.setText("结束");
                    break;
                case "结束":
                    // clickListenerInterface.doConfirm();
                    tvSubmit.setText("支付");
                    break;
                case "支付":
                     clickListenerInterface.doConfirm();
                    break;
            }
        }

    }

    public interface ClickListenerInterface {
        public void doConfirm();
        public void doCancel();
    }
}
