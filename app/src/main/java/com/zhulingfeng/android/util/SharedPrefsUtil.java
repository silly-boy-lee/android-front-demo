package com.zhulingfeng.android.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @ClassName: SharedPrefsUtil
 * @description:  SharedPrefsUtil工具类
 * @author:  Mr.Lee
 */
@SuppressWarnings({"unused"})
public class SharedPrefsUtil {



    /**
     * @MethodName: setValue
     * @description: 保存数据
     * @author:  Mr.Lee
     * @param ctx 上下文
     * @param fileName sharedPreferences文件名，存放在/data/data/<package name>/shared prefs/目录下
     * @param key 键
     * @param value 值
     */
    public static void setValue(Context ctx,String fileName,String key,Object value){
        SharedPreferences.Editor editor = getEditor(ctx,fileName);
        String type = value.getClass().getSimpleName();
        if ("String".equals(type)){
            editor.putString(key,(String)value);
        }else if("Integer".equals(type)){
            editor.putInt(key, (Integer)value);
        }
        else if("Boolean".equals(type)){
            editor.putBoolean(key, (Boolean)value);
        }
        else if("Float".equals(type)){
            editor.putFloat(key, (Float)value);
        }
        else if("Long".equals(type)){
            editor.putLong(key, (Long)value);
        }
        editor.commit();
    }

    /**
     * @MethodName:
     * @description:
     * @author:  Mr.Lee
     * @param ctx 上下文
     * @param fileName sharedPreferences文件名，存放在/data/data/<package name>/shared prefs/目录下
     * @param key 键
     * @param defautlValue  保存的具体类型数据的默认值
     * @return
     */
    public static Object getValue(Context ctx,String fileName,String key,Object defautlValue){
        SharedPreferences sp = getSharedPreferences(ctx,fileName);
        String type = defautlValue.getClass().getSimpleName();

        if("String".equals(type)){
            return sp.getString(key, (String)defautlValue);
        }
        else if("Integer".equals(type)){
            return sp.getInt(key, (Integer)defautlValue);
        }
        else if("Boolean".equals(type)){
            return sp.getBoolean(key, (Boolean)defautlValue);
        }
        else if("Float".equals(type)){
            return sp.getFloat(key, (Float)defautlValue);
        }
        else if("Long".equals(type)){
            return sp.getLong(key, (Long)defautlValue);
        }
        return null;
    }

    /**
     * @MethodName: getEditor
     * @description: 获取Editor实例
     * @author:  Mr.Lee
     * @param ctx 上下文
     * @param fileName 文件名
     * @return 获取Editor实例
     */
    private static SharedPreferences.Editor getEditor(Context ctx,String fileName){
        return getSharedPreferences(ctx,fileName).edit();
    }

    /**
     * @MethodName: getEditor
     * @description: 获取SharedPreferences实例
     * @author:  Mr.Lee
     * @param ctx 上下文
     * @param fileName 文件名
     * @return 获取Editor实例
     */
    private static SharedPreferences getSharedPreferences(Context ctx,String fileName){
        return ctx.getSharedPreferences(fileName,Context.MODE_PRIVATE);
    }
}
