package com.zhulingfeng.android.util;

import android.os.Build;
import java.io.File;

/**
 * @ClassName: OSUtils
 * @description: Android系统工具箱
 * @author:  Mr.Lee
 */
public class OSUtils {
    /**
     * @MethodName: isRoot
     * @description: 根据/system/bin/或/system/xbin目录下是否存在su文件判断是否已ROOT
     * @author:  Mr.Lee
     * @return true：已ROOT
     */
    public static boolean isRoot() {
        try {
            return new File("/system/bin/su").exists() || new File("/system/xbin/su").exists();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * @MethodName: isAPI14
     * @description: 判断当前系统是否是Android4.0
     * @author:  Mr.Lee
     */
	public static int isAPI14(){
		return Build.VERSION.SDK_INT - 14;
	}
}