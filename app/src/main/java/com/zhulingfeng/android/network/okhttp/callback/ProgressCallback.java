package com.zhulingfeng.android.network.okhttp.callback;

import com.zhulingfeng.android.network.okhttp.HttpInfo;

/**
 * @ClassName: ProgressCallback
 * @Description: 进度回调
 * @author Mr.Lee
 */
public class ProgressCallback extends ProgressCallbackAbs {
    /**
     * @FunctionName: onResponseMain
     * @description: 异步UI线程：返回请求结果
     * @author:  Mr.Lee
     * @param filePath 文件路径
     * @param info 结果信息类
     */
    @Override
    public void onResponseMain(String filePath, HttpInfo info) {

    }

    /**
     * @FunctionName: onResponseSync
     * @description: 同步非UI线程：返回请求结果
     * @param filePath 文件路径
     * @param info 结果信息类
     * @author:  Mr.Lee
     */
    @Override
    public void onResponseSync(String filePath, HttpInfo info) {

    }
    /**
     * @FunctionName: onProgressAsync
     * @description: 非UI线程：除了更新ProgressBar进度外不要进行其他UI操作
     * @author:  Mr.Lee
     * @param percent 已经写入的百分比
     * @param bytesWritten 已经写入的字节数
     * @param contentLength 文件总长度
     * @param done 是否完成即：bytesWritten==contentLength
     */
    @Override
    public void onProgressAsync(int percent, long bytesWritten, long contentLength, boolean done) {

    }

    /**
     * @FunctionName: onProgressMain
     * @description: UI线程：可以直接操作UI
     * @author:  Mr.Lee
     * @param percent 已经写入的百分比
     * @param bytesWritten 已经写入的字节数
     * @param contentLength 文件总长度
     * @param done 是否完成即：bytesWritten==contentLength
     */
    @Override
    public void onProgressMain(int percent, long bytesWritten, long contentLength, boolean done) {

    }


}
