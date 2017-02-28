package com.zhulingfeng.android.util;

import android.content.Context;
import android.widget.Toast;

/**
 * @ClassName: ToastUtils
 * @description: Toast工具类
 * @author:  Mr.Lee
 */
@SuppressWarnings({"unused"})
public class ToastUtils {
    /**
     * @FunctionName: toast
     * @description: 显示Toast信息
     * @author:  Mr.Lee
     * @param msg 信息字符串
     */
    public static void toast(Context ctx,String msg) {
        Toast.makeText(ctx, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * @FunctionName: toast
     * @description: 显示Toast信息
     * @author:  Mr.Lee
     * @param msgId 信息资源id
     */
    public static void toast(Context ctx,int msgId) {
        Toast.makeText(ctx, msgId, Toast.LENGTH_SHORT).show();
    }

    /**
     * @FunctionName: longToast
     * @description: 长时间显示Toast信息
     * @author:  Mr.Lee
     * @param msg 信息字符串
     */
    public static void longToast(Context ctx,String msg) {
        Toast.makeText(ctx, msg, Toast.LENGTH_LONG).show();
    }

    /**
     * @FunctionName: longToast
     * @description: 长时间显示Toast信息
     * @author:  Mr.Lee
     * @param msgId 信息资源id
     */
    public static void longToast(Context ctx,int msgId){
        Toast.makeText(ctx, msgId, Toast.LENGTH_LONG).show();
    }
}
