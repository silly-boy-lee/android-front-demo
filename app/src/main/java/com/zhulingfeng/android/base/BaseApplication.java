package com.zhulingfeng.android.base;

import android.app.Application;
import android.os.Environment;

import com.zhulingfeng.android.constant.SysConst;
import com.zhulingfeng.android.network.okhttp.OkHttpUtil;
import com.zhulingfeng.android.network.okhttp.annotation.CacheLevel;
import com.zhulingfeng.android.network.okhttp.annotation.CacheType;
import com.zhulingfeng.android.network.okhttp.cookie.PersistentCookieJar;
import com.zhulingfeng.android.network.okhttp.cookie.cache.SetCookieCache;
import com.zhulingfeng.android.network.okhttp.cookie.persistence.SharedPrefsCookiePersistor;

import org.litepal.LitePalApplication;

/**
 * Application
 * 1、初始化全局OkHttpUtil
 * @author zhousf
 */
public class BaseApplication extends LitePalApplication {

    public static BaseApplication baseApplication;

    public static BaseApplication getApplication() {
        return baseApplication;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        baseApplication = this;
        //初始化OkHttpUtil
        initOkHttpUtil();
    }


    @Override
    public void onTerminate() {
        super.onTerminate();
    }

    /**
     * @FunctionName: initOkHttpUtil
     * @description: 初始化Okhttp请求工具类
     * @author:  Mr.Lee
     */
    private void initOkHttpUtil(){
        String downloadFileDir = Environment.getExternalStorageDirectory().getPath() + SysConst.DEFAULT_DOWNLOAD_DIR;
        OkHttpUtil.init(this)
                .setConnectTimeout(30)//连接超时时间
                .setWriteTimeout(30)//写超时时间
                .setReadTimeout(30)//读超时时间
                .setMaxCacheSize(10 * 1024 * 1024)//缓存空间大小
                .setCacheLevel(CacheLevel.FIRST_LEVEL)//缓存等级
                .setCacheType(CacheType.FORCE_NETWORK)//缓存类型
                .setShowHttpLog(true)//显示请求日志
                .setShowLifecycleLog(false)//显示Activity销毁日志
                .setRetryOnConnectionFailure(false)//失败后不自动重连
                .setDownloadFileDir(downloadFileDir)//文件下载保存目录
                .addResultInterceptor(HttpInterceptor.ResultInterceptor)//请求结果拦截器
                .addExceptionInterceptor(HttpInterceptor.ExceptionInterceptor)//请求链路异常拦截器
                .setCookieJar(new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(this)))//持久化cookie
                .build();
    }



}
