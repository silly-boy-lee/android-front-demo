package com.zhulingfeng.android.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

public class NetWorkUtils {
    /**
     * 网络类型 - 无连接
     */
    public static final int NETWORK_TYPE_NO_CONNECTION = -1231545315;

    /**
     * @FieldName: NETWORK_TYPE_WIFI
     * @description: wifi网络类型
     */
    public static final String NETWORK_TYPE_WIFI = "wifi";
    /**
     * @FieldName: NETWORK_TYPE_3G
     * @description: 3G网络类型
     */
    public static final String NETWORK_TYPE_3G = "eg";
    /**
     * @FieldName: NETWORK_TYPE_2G
     * @description: 2G网络类型
     */
    public static final String NETWORK_TYPE_2G = "2g";
    /**
     * @FieldName: NETWORK_TYPE_WAP
     * @description: WAP网络类型
     */
    public static final String NETWORK_TYPE_WAP = "wap";
    /**
     * @FieldName: NETWORK_TYPE_UNKNOWN
     * @description: 未知网络
     */
    public static final String NETWORK_TYPE_UNKNOWN = "unknown";
    /**
     * @FieldName: NETWORK_TYPE_DISCONNECT
     * @description: 网络未连接
     */
    public static final String NETWORK_TYPE_DISCONNECT = "disconnect";

    /**
     * @MethodName: getNetworkType
     * @description: 取网络类型
     * @author:  Mr.Lee
     * @param context context
     * @return
     */
    public static int getNetworkType(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(
                Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager == null ? null: connectivityManager.getActiveNetworkInfo();
        return networkInfo == null ? -1 : networkInfo.getType();
    }

    /**
     * @MethodName: getNetworkTypeName
     * @description: 取网络类型名
     * @author:  Mr.Lee
     * @param context 上下文
     * @return 返回网络类型名
     */
    public static String getNetworkTypeName(Context context) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo;
        String type = NETWORK_TYPE_DISCONNECT;
        if (manager == null ||(networkInfo = manager.getActiveNetworkInfo()) == null) {
            return type;
        }
        ;

        if (networkInfo.isConnected()) {
            String typeName = networkInfo.getTypeName();
            if ("WIFI".equalsIgnoreCase(typeName)) {
                type = NETWORK_TYPE_WIFI;
            }
            else if ("MOBILE".equalsIgnoreCase(typeName)) {
                String proxyHost = android.net.Proxy.getDefaultHost();
                type = TextUtils.isEmpty(proxyHost)
                       ? (isFastMobileNetwork(context)
                          ? NETWORK_TYPE_3G
                          : NETWORK_TYPE_2G)
                       : NETWORK_TYPE_WAP;
            }
            else {
                type = NETWORK_TYPE_UNKNOWN;
            }
        }
        return type;
    }

    /**
     * @MethodName: isFastMobileNetwork
     * @description: 检测是否是快速移动网络
     * @author:  Mr.Lee
     * @param context 上下文
     * @return
     */
    private static boolean isFastMobileNetwork(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(
                Context.TELEPHONY_SERVICE);
        if (telephonyManager == null) {
            return false;
        }

        switch (telephonyManager.getNetworkType()) {
            case TelephonyManager.NETWORK_TYPE_1xRTT:
                return false;
            case TelephonyManager.NETWORK_TYPE_CDMA:
                return false;
            case TelephonyManager.NETWORK_TYPE_EDGE:
                return false;
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
                return true;
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
                return true;
            case TelephonyManager.NETWORK_TYPE_GPRS:
                return false;
            case TelephonyManager.NETWORK_TYPE_HSDPA:
                return true;
            case TelephonyManager.NETWORK_TYPE_HSPA:
                return true;
            case TelephonyManager.NETWORK_TYPE_HSUPA:
                return true;
            case TelephonyManager.NETWORK_TYPE_UMTS:
                return true;
            case TelephonyManager.NETWORK_TYPE_EHRPD:
                return true;
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
                return true;
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                return true;
            case TelephonyManager.NETWORK_TYPE_IDEN:
                return false;
            case TelephonyManager.NETWORK_TYPE_LTE:
                return true;
            case TelephonyManager.NETWORK_TYPE_UNKNOWN:
                return false;
            default:
                return false;
        }
    }

    /**
     * @MethodName: getCurrentNetworkState
     * @description: 获取当前网络的状态
     * @author:  Mr.Lee
     * @param context 上下文
     * @return 当前网络的状态。具体类型可参照NetworkInfo.State.CONNECTED、NetworkInfo.State.CONNECTED.DISCONNECTED等字段。当前没有网络连接时返回null
     */
    public static NetworkInfo.State getCurrentNetworkState(Context context) {
        NetworkInfo networkInfo = ((ConnectivityManager) context.getSystemService(
                Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return networkInfo != null ? networkInfo.getState() : null;
    }

    /**
     * @MethodName: getCurrentNetworkType
     * @description: 获取当前网络的类型
     * @author:  Mr.Lee
     * @param context 上下文
     * @return 当前网络的类型。具体类型可参照ConnectivityManager中的TYPE_BLUETOOTH、TYPE_MOBILE、TYPE_WIFI等字段。当前没有网络连接时返回NetworkUtils.NETWORK_TYPE_NO_CONNECTION
     */
    public static int getCurrentNetworkType(Context context) {
        NetworkInfo networkInfo
                = ((ConnectivityManager) context.getSystemService(
                Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return networkInfo != null
               ? networkInfo.getType()
               : NETWORK_TYPE_NO_CONNECTION;
    }

    /**
     * @MethodName: getCurrentNetworkSubtype
     * @description: 获取当前网络的具体类型
     * @author:  Mr.Lee
     * @param context 上下文
     * @return 当前网络的具体类型。具体类型可参照TelephonyManager中的NETWORK_TYPE_1xRTT、NETWORK_TYPE_CDMA等字段。当前没有网络连接时返回NetworkUtils.NETWORK_TYPE_NO_CONNECTION
     */
    public static int getCurrentNetworkSubtype(Context context) {
        NetworkInfo networkInfo
                = ((ConnectivityManager) context.getSystemService(
                Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return networkInfo != null
               ? networkInfo.getSubtype()
               : NETWORK_TYPE_NO_CONNECTION;
    }

    /**
     * @MethodName: isConnectedByState
     * @description: 判断当前网络是否已经连接
     * @author:  Mr.Lee
     * @param context 上下文
     * @return 当前网络是否已经连接。false：尚未连接
     */
    public static boolean isConnectedByState(Context context) {
        return getCurrentNetworkState(context) == NetworkInfo.State.CONNECTED;
    }

    /**
     * @MethodName: isConnectingByState
     * @description: 判断当前网络是否正在连接
     * @author:  Mr.Lee
     * @param context 上下文
     * @return 当前网络是否正在连接
     */
    public static boolean isConnectingByState(Context context) {
        return getCurrentNetworkState(context) == NetworkInfo.State.CONNECTING;
    }

    /**
     * @MethodName: isDisconnectedByState
     * @description: 判断当前网络是否已经断开
     * @author:  Mr.Lee
     * @param context 上下文
     * @return 当前网络是否已经断开
     */
    public static boolean isDisconnectedByState(Context context) {
        return getCurrentNetworkState(context) == NetworkInfo.State.DISCONNECTED;
    }

    /**
     * @MethodName: isDisconnectingByState
     * @description: 判断当前网络是否正在断开
     * @author:  Mr.Lee
     * @param context 上下文
     * @return 当前网络是否正在断开
     */
    public static boolean isDisconnectingByState(Context context) {
        return getCurrentNetworkState(context) ==
                NetworkInfo.State.DISCONNECTING;
    }

    /**
     * @MethodName: isSuspendedByState
     * @description: 判断当前网络是否已经暂停
     * @author:  Mr.Lee
     * @param context 上下文
     * @return 当前网络是否已经暂停
     */
    public static boolean isSuspendedByState(Context context) {
        return getCurrentNetworkState(context) == NetworkInfo.State.SUSPENDED;
    }

    /**
     * @MethodName: isUnknownByState
     * @description: 判断当前网络是否处于未知状态中
     * @author:  Mr.Lee
     * @param context 上下文
     * @return 当前网络是否处于未知状态中
     */
    public static boolean isUnknownByState(Context context) {
        return getCurrentNetworkState(context) == NetworkInfo.State.UNKNOWN;
    }

    /**
     * @MethodName: isBluetoothByType
     * @description: 判断当前网络的类型是否是蓝牙
     * @author:  Mr.Lee
     * @param context 上下文
     * @return 当前网络的类型是否是蓝牙。false：当前没有网络连接或者网络类型不是蓝牙
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public static boolean isBluetoothByType(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2) {
            return false;
        }
        else {
            return getCurrentNetworkType(context) ==
                    ConnectivityManager.TYPE_BLUETOOTH;
        }
    }

    /**
     * @MethodName: isDummyByType
     * @description: 判断当前网络的类型是否是虚拟网络
     * @author:  Mr.Lee
     * @param context 上下文
     * @return 当前网络的类型是否是虚拟网络。false：当前没有网络连接或者网络类型不是虚拟网络
     */
    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public static boolean isDummyByType(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2) {
            return false;
        }
        else {
            return getCurrentNetworkType(context) ==
                    ConnectivityManager.TYPE_DUMMY;
        }
    }

    /**
     * @MethodName: isEthernetByType
     * @description: 判断当前网络的类型是否是ETHERNET
     * @author:  Mr.Lee
     * @param context 上下文
     * @return 当前网络的类型是否是ETHERNET。false：当前没有网络连接或者网络类型不是ETHERNET
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public static boolean isEthernetByType(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2) {
            return false;
        }
        else {
            return getCurrentNetworkType(context) ==
                    ConnectivityManager.TYPE_ETHERNET;
        }
    }

    /**
     * @MethodName: isMobileByType
     * @description: 判断当前网络的类型是否是移动网络
     * @author:  Mr.Lee
     * @param context 上下文
     * @return 当前网络的类型是否是移动网络。false：当前没有网络连接或者网络类型不是移动网络
     */
    public static boolean isMobileByType(Context context) {
        return getCurrentNetworkType(context) ==
                ConnectivityManager.TYPE_MOBILE;
    }

    /**
     * @MethodName: isMobileDunByType
     * @description: 判断当前网络的类型是否是MobileDun
     * @author:  Mr.Lee
     * @param context 上下文
     * @return 当前网络的类型是否是MobileDun。false：当前没有网络连接或者网络类型不是MobileDun
     */
    public static boolean isMobileDunByType(Context context) {
        return getCurrentNetworkType(context) ==
                ConnectivityManager.TYPE_MOBILE_DUN;
    }

    /**
     * @MethodName: isMobileHipriByType
     * @description: 判断当前网络的类型是否是MobileHipri
     * @author:  Mr.Lee
     * @param context 上下文
     * @return 当前网络的类型是否是MobileHipri。false：当前没有网络连接或者网络类型不是MobileHipri
     */
    public static boolean isMobileHipriByType(Context context) {
        return getCurrentNetworkType(context) == ConnectivityManager.TYPE_MOBILE_HIPRI;
    }

    /**
     * @MethodName: isMobileMmsByType
     * @description: 判断当前网络的类型是否是MobileMms
     * @author:  Mr.Lee
     * @param context 上下文
     * @return 当前网络的类型是否是MobileMms。false：当前没有网络连接或者网络类型不是MobileMms
     */
    public static boolean isMobileMmsByType(Context context) {
        return getCurrentNetworkType(context) ==
                ConnectivityManager.TYPE_MOBILE_MMS;
    }

    /**
     * @MethodName:
     * @description: 判断当前网络的类型是否是MobileSupl
     * @author:  Mr.Lee
     * @param context 上下文
     * @return 当前网络的类型是否是MobileSupl。false：当前没有网络连接或者网络类型不是MobileSupl
     */
    public static boolean isMobileSuplByType(Context context) {
        return getCurrentNetworkType(context) ==
                ConnectivityManager.TYPE_MOBILE_SUPL;
    }
    
    /**
     * @MethodName: isWifiByType
     * @description: 判断当前网络的类型是否是Wifi
     * @author:  Mr.Lee
     * @param context 上下文
     * @return 当前网络的类型是否是Wifi。false：当前没有网络连接或者网络类型不是wifi
     */
    public static boolean isWifiByType(Context context) {
        return getCurrentNetworkType(context) == ConnectivityManager.TYPE_WIFI;
    }
     
    /**
     * @MethodName: isWimaxByType
     * @description: 判断当前网络的类型是否是Wimax
     * @author:  Mr.Lee
     * @param context 上下文
     * @return 当前网络的类型是否是Wimax。false：当前没有网络连接或者网络类型不是Wimax
     */
    public static boolean isWimaxByType(Context context) {
        return getCurrentNetworkType(context) == ConnectivityManager.TYPE_WIMAX;
    }
    
    /**
     * @MethodName: is1XRTTBySubtype
     * @description: 判断当前网络的具体类型是否是1XRTT
     * @author:  Mr.Lee
     * @param context 上下文
     * @return false：当前网络的具体类型是否是1XRTT。false：当前没有网络连接或者具体类型不是1XRTT
     */
    public static boolean is1XRTTBySubtype(Context context) {
        return getCurrentNetworkSubtype(context) ==
                TelephonyManager.NETWORK_TYPE_1xRTT;
    }
     
    /**
     * @MethodName: isCDMABySubtype
     * @description: 判断当前网络的具体类型是否是CDMA（Either IS95A or IS95B）
     * @author:  Mr.Lee
     * @param context 上下文
     * @return false：当前网络的具体类型是否是CDMA。false：当前没有网络连接或者具体类型不是CDMA
     */
    public static boolean isCDMABySubtype(Context context) {
        return getCurrentNetworkSubtype(context) ==
                TelephonyManager.NETWORK_TYPE_CDMA;
    }
    
    /**
     * @MethodName: isEDGEBySubtype
     * @description: 判断当前网络的具体类型是否是EDGE
     * @author:  Mr.Lee
     * @param context 上下文
     * @return false：当前网络的具体类型是否是EDGE。false：当前没有网络连接或者具体类型不是EDGE
     */
    public static boolean isEDGEBySubtype(Context context) {
        return getCurrentNetworkSubtype(context) == TelephonyManager.NETWORK_TYPE_EDGE;
    }
     
    /**
     * @MethodName: isEHRPDBySubtype
     * @description: 判断当前网络的具体类型是否是EHRPD
     * @author:  Mr.Lee
     * @param context 上下文
     * @return false：当前网络的具体类型是否是EHRPD。false：当前没有网络连接或者具体类型不是EHRPD
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static boolean isEHRPDBySubtype(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            return false;
        }
        else {
            return getCurrentNetworkSubtype(context) ==
                    TelephonyManager.NETWORK_TYPE_EHRPD;
        }
    }

    /**
     * @MethodName:  isEVDO_0BySubtype
     * @description: 判断当前网络的具体类型是否是EVDO_0
     * @author:  Mr.Lee
     * @param context 上下文
     * @return false：当前网络的具体类型是否是EVDO_0。false：当前没有网络连接或者具体类型不是EVDO_0
     */
    public static boolean isEVDO_0BySubtype(Context context) {
        return getCurrentNetworkSubtype(context) ==
                TelephonyManager.NETWORK_TYPE_EVDO_0;
    }

    /**
     * @MethodName: isEVDO_ABySubtype
     * @description: 判断当前网络的具体类型是否是EVDO_A
     * @author:  Mr.Lee
     * @param context 上下文
     * @return false：当前网络的具体类型是否是EVDO_A。false：当前没有网络连接或者具体类型不是EVDO_A
     */
    public static boolean isEVDO_ABySubtype(Context context) {
        return getCurrentNetworkSubtype(context) == TelephonyManager.NETWORK_TYPE_EVDO_A;
    }

    /**
     * @MethodName: isEVDO_BBySubtype
     * @description: 判断当前网络的具体类型是否是EDGE
     * @author:  Mr.Lee
     * @param context 上下文
     * @return false：当前网络的具体类型是否是EVDO_B。false：当前没有网络连接或者具体类型不是EVDO_B
     */
    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    public static boolean isEVDO_BBySubtype(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.GINGERBREAD) {
            return false;
        }
        else {
            return getCurrentNetworkSubtype(context) ==
                    TelephonyManager.NETWORK_TYPE_EVDO_B;
        }
    }

    /**
     * @MethodName: isGPRSBySubtype
     * @description: 判断当前网络的具体类型是否是GPRS
     * @author:  Mr.Lee
     * @param context 上下文
     * @return false：当前网络的具体类型是否是GPRS。false：当前没有网络连接或者具体类型不是GPRS
     */
    public static boolean isGPRSBySubtype(Context context) {
        return getCurrentNetworkSubtype(context) ==
                TelephonyManager.NETWORK_TYPE_GPRS;
    }

    /**
     * @MethodName: isHSDPABySubtype
     * @description: 判断当前网络的具体类型是否是HSDPA
     * @author:  Mr.Lee
     * @param context 上下文
     * @return false：当前网络的具体类型是否是HSDPA。false：当前没有网络连接或者具体类型不是HSDPA
     */
    public static boolean isHSDPABySubtype(Context context) {
        return getCurrentNetworkSubtype(context) == TelephonyManager.NETWORK_TYPE_HSDPA;
    }

    /**
     * @MethodName: isHSPABySubtype
     * @description: 判断当前网络的具体类型是否是HSPA
     * @author:  Mr.Lee
     * @param context 上下文
     * @return false：当前网络的具体类型是否是HSPA。false：当前没有网络连接或者具体类型不是HSPA
     */
    public static boolean isHSPABySubtype(Context context) {
        return getCurrentNetworkSubtype(context) == TelephonyManager.NETWORK_TYPE_HSPA;
    }

    /**
     * @MethodName: isHSPAPBySubtype
     * @description: 判断当前网络的具体类型是否是HSPAP
     * @author:  Mr.Lee
     * @param context 上下文
     * @return false：当前网络的具体类型是否是HSPAP。false：当前没有网络连接或者具体类型不是HSPAP
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public static boolean isHSPAPBySubtype(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2) {
            return false;
        }
        else {
            return getCurrentNetworkSubtype(context) ==
                    TelephonyManager.NETWORK_TYPE_HSPAP;
        }
    }

    /**
     * @MethodName: isHSUPABySubtype
     * @description: 判断当前网络的具体类型是否是HSUPA
     * @author:  Mr.Lee
     * @param context 上下文
     * @return false：当前网络的具体类型是否是HSUPA。false：当前没有网络连接或者具体类型不是HSUPA
     */
    public static boolean isHSUPABySubtype(Context context) {
        return getCurrentNetworkSubtype(context) ==
                TelephonyManager.NETWORK_TYPE_HSUPA;
    }

    /**
     * @MethodName: isIDENBySubtype
     * @description: 判断当前网络的具体类型是否是IDEN
     * @author:  Mr.Lee
     * @param context 上下文
     * @return false：当前网络的具体类型是否是IDEN。false：当前没有网络连接或者具体类型不是IDEN
     */
    public static boolean isIDENBySubtype(Context context) {
        return getCurrentNetworkSubtype(context) ==
                TelephonyManager.NETWORK_TYPE_IDEN;
    }

    /**
     * @MethodName: isLTEBySubtype
     * @description: 判断当前网络的具体类型是否是LTE
     * @author:  Mr.Lee
     * @param context 上下文
     * @return false：当前网络的具体类型是否是LTE。false：当前没有网络连接或者具体类型不是LTE
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public static boolean isLTEBySubtype(Context context) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB) {
            return false;
        }
        else {
            return getCurrentNetworkSubtype(context) ==
                    TelephonyManager.NETWORK_TYPE_LTE;
        }
    }

    /**
     * @MethodName: isUMTSBySubtype
     * @description: 判断当前网络的具体类型是否是UMTS
     * @author:  Mr.Lee
     * @param context 上下文
     * @return false：当前网络的具体类型是否是UMTS。false：当前没有网络连接或者具体类型不是UMTS
     */
    public static boolean isUMTSBySubtype(Context context) {
        return getCurrentNetworkSubtype(context) ==
                TelephonyManager.NETWORK_TYPE_UMTS;
    }

    /**
     * @MethodName: isUNKNOWNBySubtype
     * @description: 判断当前网络的具体类型是否是UNKNOWN
     * @author:  Mr.Lee
     * @param context 上下文
     * @return false：当前网络的具体类型是否是UNKNOWN。false：当前没有网络连接或者具体类型不是UNKNOWN
     */
    public static boolean isUNKNOWNBySubtype(Context context) {
        return getCurrentNetworkSubtype(context) ==
                TelephonyManager.NETWORK_TYPE_UNKNOWN;
    }

    /**
     * @MethodName: isChinaMobile2G
     * @description: 判断当前网络是否是中国移动2G网络
     * @author:  Mr.Lee
     * @param context 上下文
     * @return false：不是中国移动2G网络或者当前没有网络连接
     */
    public static boolean isChinaMobile2G(Context context) {
        return isEDGEBySubtype(context);
    }

    /**
     * @MethodName: isChinaUnicom2G
     * @description: 判断当前网络是否是中国联通2G网络
     * @author:  Mr.Lee
     * @param context 上下文
     * @return false：不是中国联通2G网络或者当前没有网络连接
     */
    public static boolean isChinaUnicom2G(Context context) {
        return isGPRSBySubtype(context);
    }

    /**
     * @MethodName: isChinaUnicom3G
     * @description: 判断当前网络是否是中国联通3G网络
     * @author:  Mr.Lee
     * @param context 上下文
     * @return false：不是中国联通3G网络或者当前没有网络连接
     */
    public static boolean isChinaUnicom3G(Context context) {
        return isHSDPABySubtype(context) || isUMTSBySubtype(context);
    }

    /**
     * @MethodName: isChinaTelecom2G
     * @description: 判断当前网络是否是中国电信2G网络
     * @author:  Mr.Lee
     * @param context 上下文
     * @return false：不是中国电信2G网络或者当前没有网络连接
     */
    public static boolean isChinaTelecom2G(Context context) {
        return isCDMABySubtype(context);
    }

    /**
     * @MethodName: isChinaTelecom3G
     * @description: 判断当前网络是否是中国电信3G网络
     * @author:  Mr.Lee
     * @param context 上下文
     * @return false：不是中国电信3G网络或者当前没有网络连接
     */
    public static boolean isChinaTelecom3G(Context context) {
        return isEVDO_0BySubtype(context) || isEVDO_ABySubtype(context) ||
                isEVDO_BBySubtype(context);
    }

    /**
     * @MethodName: getWifiState
     * @description: 获取Wifi的状态，需要ACCESS_WIFI_STATE权限
     * @author:  Mr.Lee
     * @param context 上下文
     * @return 取值为WifiManager中的WIFI_STATE_ENABLED、WIFI_STATE_ENABLING、WIFI_STATE_DISABLED、WIFI_STATE_DISABLING、WIFI_STATE_UNKNOWN之一
     * @throws Exception 没有找到wifi设备
     */
    public static int getWifiState(Context context) throws Exception {
        WifiManager wifiManager = ((WifiManager) context.getSystemService(
                Context.WIFI_SERVICE));
        if (wifiManager != null) {
            return wifiManager.getWifiState();
        }
        else {
            throw new Exception("wifi device not found!");
        }
    }

    /**
     * @MethodName: isWifiOpen
     * @description: 判断Wifi是否打开，需要ACCESS_WIFI_STATE权限
     * @author:  Mr.Lee
     * @param context 上下文
     * @return true：打开；false：关闭
     */
    public static boolean isWifiOpen(Context context) throws Exception {
        int wifiState = getWifiState(context);
        return wifiState == WifiManager.WIFI_STATE_ENABLED ||
                       wifiState == WifiManager.WIFI_STATE_ENABLING ? true: false;
    }

    /**
     * @MethodName: setWifi
     * @description: 设置Wifi，需要CHANGE_WIFI_STATE权限
     * @author:  Mr.Lee
     * @param context 上下文
     * @param enable wifi状态
     * @return 设置是否成功
     */
    public static boolean setWifi(Context context, boolean enable)throws Exception {
        //如果当前wifi的状态和要设置的状态不一样
        if (isWifiOpen(context) != enable) {
            ((WifiManager) context.getSystemService(
                    Context.WIFI_SERVICE)).setWifiEnabled(enable);
        }
        return true;
    }

    /**
     * @MethodName: isMobileNetworkOpen
     * @description: 判断移动网络是否打开，需要ACCESS_NETWORK_STATE权限
     * @author:  Mr.Lee
     * @param context 上下文
     * @return true：打开；false：关闭
     */
    public static boolean isMobileNetworkOpen(Context context) {
        return (((ConnectivityManager) context.getSystemService(
                Context.CONNECTIVITY_SERVICE)).getNetworkInfo(
                ConnectivityManager.TYPE_MOBILE)).isConnected();
    }

    /**
     * @MethodName: getIpAddress
     * @description: 获取本机IP地址
     * @author:  Mr.Lee
     * @return null：没有网络连接
     */
    public static String getIpAddress() {
        try {
            NetworkInterface nerworkInterface;
            InetAddress inetAddress;
            for (Enumeration<NetworkInterface> en
                 = NetworkInterface.getNetworkInterfaces();
                 en.hasMoreElements(); ) {
                nerworkInterface = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr
                     = nerworkInterface.getInetAddresses();
                     enumIpAddr.hasMoreElements(); ) {
                    inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
            return null;
        } catch (SocketException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
