package com.zhulingfeng.android.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


/**
 * @ClassName: WiFiUtil
 * @description: 无线网络工具类
 * @author:  Mr.Lee
 */
@SuppressWarnings({"unused"})
public class WiFiUtil {

    /**
     * @MethodName: isWifiConnected
     * @description: 判断是否连接WIFI
     * @author:  Mr.Lee
     * @param context  上下文
     * @return  boolean
     */
    public static boolean isWifiConnected(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(
                Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiNetworkInfo = connectivityManager.getNetworkInfo(
                ConnectivityManager.TYPE_WIFI);
        if (wifiNetworkInfo.isConnected()) {
            return true;
        }
        return false;
    }
}
