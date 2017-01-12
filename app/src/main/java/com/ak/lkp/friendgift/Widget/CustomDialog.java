package com.ak.lkp.friendgift.Widget;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.ak.lkp.friendgift.R;
import com.ak.lkp.friendgift.UI.LoginActivity;

/**
 * Created by likunpeng on 2016/12/7.
 */

public class CustomDialog extends Dialog implements View.OnClickListener{
    Context mContext;
    private TextView cancle;
    private TextView makeSure;


    public CustomDialog(Context context) {

        super(context, R.style.dialog);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutInflater inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.custom_dialog, null);
        this.setContentView(layout);
        cancle = (TextView) layout.findViewById(R.id.cancle);
        makeSure = (TextView) layout.findViewById(R.id.makesure);
        cancle.setOnClickListener(this);
        makeSure.setOnClickListener(this);
        setCancelable(false);//禁止外围点击消失
        Window dialogWindow = this.getWindow();
        DisplayMetrics metric = new DisplayMetrics();
        WindowManager mWindowManager  = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        mWindowManager.getDefaultDisplay().getMetrics(metric);
        int width = metric.widthPixels; // 屏幕宽度（像素）-
        int height = metric.heightPixels; // 屏幕宽度（像素）
        WindowManager.LayoutParams p = dialogWindow.getAttributes(); // 获取对话框当前的参数值
        p.height = (int) (height * 0.3); // 高度设置为屏幕的0.6
        p.width = (int) (width * 0.65); // 宽度设置为屏幕的0.65
        dialogWindow.getDecorView().setPadding(0, 0, 0, 0);
        dialogWindow.setAttributes(p);
    }

    @Override
    public void onClick(View view) {
            switch (view.getId()){
                case R.id.cancle:
                    dismiss();
                    break;
                case R.id.makesure:
                    Intent intent = new Intent(mContext,LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);
                    dismiss();
                    break;
                default:
                    break;
            }
    }
}
