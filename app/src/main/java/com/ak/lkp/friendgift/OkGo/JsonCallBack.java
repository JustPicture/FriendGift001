package com.ak.lkp.friendgift.OkGo;


import android.app.Activity;

import com.ak.lkp.friendgift.Entity.LkpResponse;
import com.ak.lkp.friendgift.Utils.Convert;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.request.BaseRequest;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Response;

/**
 * Created by likunpeng on 2017/1/3.
 */

public abstract class JsonCallBack<T> extends AbsCallback<T> {

//    Handler mHandler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            if (msg.what == 100){
//            CustomDialog customDialog = new CustomDialog(mActivity);
//            customDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_TOAST);
//            customDialog.show();
//            }
//        }
//    };

    Activity mActivity;
    public JsonCallBack(Activity activity) {
        this.mActivity = activity;
    }

    @Override
    public void onBefore(BaseRequest request) {
        super.onBefore(request);
        // 主要用于在所有请求之前添加公共的请求头或请求参数
        // 例如登录授权的 token
        // 使用的设备信息
        // 可以随意添加,也可以什么都不传
        // 还可以在这里对所有的参数进行加密，均在这里实现
//        request.headers("header1", "HeaderValue1")//
//                .params("params1", "ParamsValue1")//
//                .params("token", "3215sdf13ad1f65asd4f3ads1f");
    }


    @Override
    public T convertSuccess(Response response) throws Exception {
        Type genType = getClass().getGenericSuperclass();
        Type type = ((ParameterizedType) genType).getActualTypeArguments()[0];

        String string = response.body().string();
        LkpResponse result = Convert.fromJson(string, type);
        response.close();
        if (result.code == 200) {
            return (T) result;
        } else if (result.code == 201) {
//            Message message = new Message();
//            message.what = 100;
//            Bundle bundle = new Bundle();
//            bundle.putString("yichang","异地登录");
//            message.setData(bundle);
//          mHandler.sendMessage(message);
            throw new IllegalArgumentException("异地登录");
        } else if (result.code == 203) {
            throw new IllegalArgumentException("举个错误例子");
        } else {
            throw new IllegalArgumentException(result.message);
        }
    }

}
