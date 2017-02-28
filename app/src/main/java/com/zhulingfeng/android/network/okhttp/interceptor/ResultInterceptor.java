package com.zhulingfeng.android.network.okhttp.interceptor;

import com.zhulingfeng.android.network.okhttp.HttpInfo;

/**
 * 请求结果拦截器
 * @author zhousf
 */
public interface ResultInterceptor {

    HttpInfo intercept(HttpInfo info) throws Exception;

}
