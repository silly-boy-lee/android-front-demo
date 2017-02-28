package com.zhulingfeng.android.network.okhttp.bean;

import com.zhulingfeng.android.network.okhttp.HttpInfo;
import com.zhulingfeng.android.network.okhttp.callback.CallbackOk;

/**
 * @ClassName: CallbackMessage
 * @Description: 响应回调信息体
 * @author Mr.Lee
 */
public class CallbackMessage extends OkMessage{

    /**
     * @FieldName: callback
     * @description: 异步请求回调接口
     */
    public CallbackOk callback;
    /**
     * @FieldName: info
     * @description: 网络请求信息封装类
     */
    public HttpInfo info;

    /**
     * @FunctionName: CallbackMessage
     * @description:  请求响应回调接口
     * @author:  Mr.Lee
     */
    public CallbackMessage(int what, CallbackOk callback, HttpInfo info) {
        this.what = what;
        this.callback = callback;
        this.info = info;
    }
}