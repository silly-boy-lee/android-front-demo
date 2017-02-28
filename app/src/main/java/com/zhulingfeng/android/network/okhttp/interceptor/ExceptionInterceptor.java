package com.zhulingfeng.android.network.okhttp.interceptor;

import com.zhulingfeng.android.network.okhttp.HttpInfo;

/**
 * 请求链路异常（非业务逻辑）拦截器
 * @author zhousf
 */
public interface ExceptionInterceptor {

    HttpInfo intercept(HttpInfo info) throws Exception;

}
