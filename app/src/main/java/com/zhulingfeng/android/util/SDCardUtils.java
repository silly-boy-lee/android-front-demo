package com.zhulingfeng.android.util;

import android.os.Environment;
import java.io.File;

/**
 * @ClassName: SDCardUtils
 * @description: SD卡工具
 * @author:  Mr.Lee
 */
@SuppressWarnings({"unused"})
public class SDCardUtils {
    /**
     * @MethodName: getState
     * @description: 获取SD卡的状态
     * @author:  Mr.Lee
     * @return sd卡状态
     */
    public static String getState() {
        return Environment.getExternalStorageState();
    }

    /**
     * @MethodName: isAvailable
     * @description: 检测SD卡是否可用
     * @author:  Mr.Lee
     * @return 只有当SD卡已经安装并且准备好了才返回true，否则返回false
     */
    public static boolean isAvailable() {
        return getState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * @MethodName: getRootDirectory
     * @description: 获取SD卡的根目录
     * @author:  Mr.Lee
     * @return 不存在SD卡则返回null
     */
    public static File getRootDirectory() {
        return isAvailable() ? Environment.getExternalStorageDirectory() : null;
    }

    /**
     * @MethodName: getRootPath
     * @description: 获取SD卡的根路径
     * @author:  Mr.Lee
     * @return 不存在SD卡则返回null
     */
    public static String getRootPath() {
        File rootDirectory = getRootDirectory();
        return rootDirectory != null ? rootDirectory.getPath() : null;
    }

    /**
     * @MethodName: getSDPath
     * @description: 获取sd卡路径
     * @author:  Mr.Lee
     * @return Stringpath
     */
    public static String getSDPath(){
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState()
                                         .equals(Environment.MEDIA_MOUNTED);   //判断sd卡是否存在
        if   (sdCardExist)
        {
            sdDir = Environment.getExternalStorageDirectory();//获取跟目录
        }
        return sdDir.toString();
    }
}
