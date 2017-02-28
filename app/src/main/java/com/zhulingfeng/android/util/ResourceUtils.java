package com.zhulingfeng.android.util;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


/**
 * @ClassName: ResourceUtils
 * @description:
 * @author:  Mr.Lee
 */
@SuppressWarnings({"unused"})
public class ResourceUtils {
    
    /**
     * @MethodName: ResourceUtils
     * @description: 将构造方法私有化，通过JNI方式实例化此类
     * @author:  Mr.Lee
     */
    private ResourceUtils() {
        throw new AssertionError();
    }
    /**
     * @MethodName: geFileFromAssets
     * @description: 使用ACCESS_STREAMING模式获取一个asset文件路径，此路径可作为一个assets来访问一个已经绑定了应用程序的资源，即文件被assets目录取代
     * @author:  Mr.Lee
     * @param context  上下文
     * @param fileName 将要找开的asset的名字，此名字可以有层次结构
     * @return  String
     */
    public static String geFileFromAssets(Context context, String fileName) {
        if (context == null || StringUtils.isEmpty(fileName)) {
            return null;
        }

        StringBuilder s = new StringBuilder("");
        try {
            InputStreamReader in = new InputStreamReader(context.getResources().getAssets().open(fileName));
            BufferedReader br = new BufferedReader(in);
            String line;
            while ((line = br.readLine()) != null) {
                s.append(line);
            }
            return s.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @MethodName: geFileFromRaw
     * @description: 获取原生资源内容，只能被用于asset文件资源的即它可以打开一个drawable,sound和原生资源，对字符串和颜色资源不起效果
     * @author:  Mr.Lee
     * @param context  context
     * @param resId 由appt工具生成的资源标识符
     * @return  String
     */
    public static String geFileFromRaw(Context context, int resId) {
        if (context == null) {
            return null;
        }

        StringBuilder s = new StringBuilder();
        try {
            InputStreamReader in = new InputStreamReader(context.getResources().openRawResource(resId));
            BufferedReader br = new BufferedReader(in);
            String line;
            while ((line = br.readLine()) != null) {
                s.append(line);
            }
            return s.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @MethodName: geFileToListFromAssets
     * @description: 取指定资源下的文件
     * @author:  Mr.Lee
     * @param context 上下文
     * @param fileName  文件的名字
     * @return     返回资源下的文件
     */
    public static List<String> geFileToListFromAssets(Context context, String fileName) {
        if (context == null || StringUtils.isEmpty(fileName)) {
            return null;
        }

        List<String> fileContent = new ArrayList<String>();
        try {
            InputStreamReader in = new InputStreamReader(context.getResources().getAssets().open(fileName));
            BufferedReader br = new BufferedReader(in);
            String line;
            while ((line = br.readLine()) != null) {
                fileContent.add(line);
            }
            br.close();
            return fileContent;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @MethodName: geFileToListFromRaw
     * @description: 取raw下的文件
     * @author:  Mr.Lee
     * @param context  上下文
     * @param resId id
     * @return   raw下的文件
     */
    public static List<String> geFileToListFromRaw(Context context, int resId) {
        if (context == null) {
            return null;
        }

        List<String> fileContent = new ArrayList<String>();
        BufferedReader reader = null;
        try {
            InputStreamReader in = new InputStreamReader(context.getResources().openRawResource(resId));
            reader = new BufferedReader(in);
            String line = null;
            while ((line = reader.readLine()) != null) {
                fileContent.add(line);
            }
            reader.close();
            return fileContent;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
