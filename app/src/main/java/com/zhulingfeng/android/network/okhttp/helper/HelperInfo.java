package com.zhulingfeng.android.network.okhttp.helper;

import com.zhulingfeng.android.network.okhttp.OkHttpUtil;
import com.zhulingfeng.android.network.okhttp.interceptor.ExceptionInterceptor;
import com.zhulingfeng.android.network.okhttp.interceptor.ResultInterceptor;

import java.util.List;

import okhttp3.OkHttpClient;

/**
 * @ClassName: HelperInfo
 * @Description: 业务类信息体
 * @author zhousf
 */
public class HelperInfo {

    /**
     * @FieldName: LogTAG
     * @description: 打印日志标识
     */
    private String LogTAG;
    /**
     * @FieldName: timeStamp
     * @description: 时间戳
     */
    private String timeStamp;
    /**
     * @FieldName: showHttpLog
     * @description: 是否显示Http请求日志
     */
    private boolean showHttpLog;
    /**
     * @FieldName: okHttpUtil
     * @description: okHttp网络请求工具类
     */
    private OkHttpUtil okHttpUtil;
    /**
     * @FieldName: isDefault
     * @description: 是否默认请求
     */
    private boolean isDefault;
    /**
     * @FieldName: clientBuilder
     * @description: okHttp网络请求客户端构建器
     */
    private OkHttpClient.Builder clientBuilder;
    /**
     * @FieldName: requestTag
     * @description: 请求标识
     */
    private String requestTag;
    /**
     * @FieldName: resultInterceptors
     * @description: 请求结果拦截器
     */
    private List<ResultInterceptor> resultInterceptors;
    /**
     * @FieldName: exceptionInterceptors
     * @description: 请求链路异常拦截器
     */
    private List<ExceptionInterceptor> exceptionInterceptors;
    /**
     * @FieldName: downloadFileDir
     * @description: 下载文件保存目录
     */
    private String downloadFileDir;


    public String getLogTAG() {
        return LogTAG;
    }

    public void setLogTAG(String logTAG) {
        LogTAG = logTAG;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public boolean isShowHttpLog() {
        return showHttpLog;
    }

    public void setShowHttpLog(boolean showHttpLog) {
        this.showHttpLog = showHttpLog;
    }

    public String getRequestTag() {
        return requestTag;
    }

    public void setRequestTag(String requestTag) {
        this.requestTag = requestTag;
    }

    public List<ResultInterceptor> getResultInterceptors() {
        return resultInterceptors;
    }

    public void setResultInterceptors(List<ResultInterceptor> resultInterceptors) {
        this.resultInterceptors = resultInterceptors;
    }

    public List<ExceptionInterceptor> getExceptionInterceptors() {
        return exceptionInterceptors;
    }

    public void setExceptionInterceptors(List<ExceptionInterceptor> exceptionInterceptors) {
        this.exceptionInterceptors = exceptionInterceptors;
    }

    public String getDownloadFileDir() {
        return downloadFileDir;
    }

    public void setDownloadFileDir(String downloadFileDir) {
        this.downloadFileDir = downloadFileDir;
    }

    public OkHttpClient.Builder getClientBuilder() {
        return clientBuilder;
    }

    public void setClientBuilder(OkHttpClient.Builder clientBuilder) {
        this.clientBuilder = clientBuilder;
    }

    public OkHttpUtil getOkHttpUtil() {
        return okHttpUtil;
    }

    public void setOkHttpUtil(OkHttpUtil okHttpUtil) {
        this.okHttpUtil = okHttpUtil;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

}
