package com.zhulingfeng.android.network.okhttp.callback;

import com.zhulingfeng.android.network.okhttp.HttpInfo;

/**
 * 进度回调抽象类
 * @author zhousf
 */
public abstract class ProgressCallbackAbs {

    public abstract void onResponseMain(String filePath, HttpInfo info);

    public abstract void onResponseSync(String filePath, HttpInfo info);

    public abstract void onProgressAsync(int percent, long bytesWritten, long contentLength, boolean done);

    public abstract void onProgressMain(int percent, long bytesWritten, long contentLength, boolean done);

}
