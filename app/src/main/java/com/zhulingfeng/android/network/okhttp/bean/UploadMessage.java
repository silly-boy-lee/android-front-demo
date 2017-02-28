package com.zhulingfeng.android.network.okhttp.bean;

import com.zhulingfeng.android.network.okhttp.HttpInfo;
import com.zhulingfeng.android.network.okhttp.callback.ProgressCallback;

/**
 * 上传响应回调信息体
 * @author zhousf
 */
public class UploadMessage extends OkMessage{

    public String filePath;
    public HttpInfo info;
    public ProgressCallback progressCallback;

    public UploadMessage(int what, String filePath, HttpInfo info, ProgressCallback progressCallback) {
        this.what = what;
        this.filePath = filePath;
        this.info = info;
        this.progressCallback = progressCallback;
    }
}