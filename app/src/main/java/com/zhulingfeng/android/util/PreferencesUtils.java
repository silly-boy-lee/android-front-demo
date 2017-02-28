package com.zhulingfeng.android.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @ClassName: PreferencesUtils
 * @description: 应用参数工具类
 * @author:  Mr.Lee
 */
@SuppressWarnings({"unused"})
public class PreferencesUtils {

    /**
     * @FieldName: PREFERENCE_NAME
     * @description: 参数名
     */
    public static String PREFERENCE_NAME = "TrineaAndroidCommon";

    /**
     * @MethodName: 构造方法
     * @description:
     * @author:  Mr.Lee
     */
    private PreferencesUtils() {
        throw new AssertionError();
    }

    /**
     * @MethodName: putString
     * @description: 设置参数值
     * @author:  Mr.Lee
     * @param context   上下文
     * @param key 要修改或设置的参数的key
     * @param value 要修改或设置的参数值
     * @return  如果资源能写入到存储中，返回true,否则返回false
     */
    public static boolean putString(Context context, String key, String value) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        return editor.commit();
    }

    /**
     * get string preferences
     * 
     * @param context  context
     * @param key The name of the preference to retrieve
     * @return The preference value if it exists, or null. Throws ClassCastException if there is a preference with this
     *         name that is not a string
     * @see #getString(Context, String, String)
     */
    /**
     * @MethodName: getString
     * @description: 取指定key对应的参数值
     * @author:  Mr.Lee
     * @param context  上下文
     * @param key 要检索的参数key
     * @return
     */
    public static String getString(Context context, String key) {
        return getString(context, key, null);
    }

    /**
     * @MethodName: getString
     * @description: 取指定参数值
     * @author:  Mr.Lee
     * @param context  上下文
     * @param key 要查找的参数key
     * @param defaultValue 如果指定的参数值不存在时，返回的默认值
     * @return 如果参数值存在，则返回参数值，如果不存在，则返回默认值，如果参数值不是字符串时，则返回类型转换异常
     */
    public static String getString(Context context, String key, String defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return settings.getString(key, defaultValue);
    }

    /**
     * @MethodName: putInt
     * @description: 设置整形参数值
     * @author:  Mr.Lee
     * @param context  上下文
     * @param key 要修改（设置）的参数key
     * @param value 参数值
     * @return 如果参数设置成功，则返回true,否则返回false
     */
    public static boolean putInt(Context context, String key, int value) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt(key, value);
        return editor.commit();
    }

    /**
     * @MethodName: getInt
     * @description: 取得一个整形参数值
     * @author:  Mr.Lee
     * @param context  上下文
     * @param key 要查找的参数key
     * @return 如果参数值存在返回参数值，否则返回-1，如果参数值不是一个整数则抛出类型转换异常
     */
    public static int getInt(Context context, String key) {
        return getInt(context, key, -1);
    }

    /**
     * @MethodName: getInt
     * @description: 取一个整形参数值
     * @author:  Mr.Lee
     * @param context  上下文
     * @param key 要查找的参数key
     * @param defaultValue 如果要查找的参数key不存在时返回的默认值
     * @return 如果参数值存在，则返回参数值，否则返回默认值，如果参数值不是一个整形，则抛出类型转换异常
     */
    public static int getInt(Context context, String key, int defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return settings.getInt(key, defaultValue);
    }


    /**
     * @MethodName: putLong
     * @description: 设置一个长整形参数
     * @author:  Mr.Lee
     * @param context  上下文
     * @param key （设置）的参数key
     * @param value 参数值
     * @return 如果参数设置成功，则返回true,否则返回false
     */
    public static boolean putLong(Context context, String key, long value) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putLong(key, value);
        return editor.commit();
    }

    /**
     * @MethodName: getLong
     * @description: 取一个长整形参数值
     * @author:  Mr.Lee
     * @param context  上下文
     * @param key 要查找的参数key
     * @return 如果参数值存在返回参数值，否则返回-1，如果参数值不是一个整数则抛出类型转换异常
     */
    public static long getLong(Context context, String key) {
        return getLong(context, key, -1);
    }

    /**
     * @MethodName: getLong
     * @description: 取一个长整形参数值
     * @author:  Mr.Lee
     * @param context  上下文
     * @param key 要查找的参数key
     * @param defaultValue 如果要查找的参数key不存在时返回的默认值
     * @return 如果参数值存在返回参数值，否则返回-1，如果参数值不是一个整数则抛出类型转换异常
     */
    public static long getLong(Context context, String key, long defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return settings.getLong(key, defaultValue);
    }

    /**
     * @MethodName: putFloat
     * @description: 设置(修改)浮点型参数
     * @author:  Mr.Lee
     * @param context  上下文
     * @param key 要设置（修改）的参数key
     * @param value 参数值
     * @return 如果参数设置或修改成功，则返回true,否则返回false
     */
    public static boolean putFloat(Context context, String key, float value) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putFloat(key, value);
        return editor.commit();
    }

    /**
     * @MethodName: getFloat
     * @description: 取一个浮点型参数
     * @author:  Mr.Lee
     * @param context  上下文
     * @param key 要查找的参数key
     * @return 如果参数值存在返回参数值，否则返回-1，如果参数值不是浮点数据类型，则抛出类型转换异常
     */
    public static float getFloat(Context context, String key) {
        return getFloat(context, key, -1);
    }

    /**
     * @MethodName: getFloat
     * @description: 取一个浮点型参数
     * @author:  Mr.Lee
     * @param context  上下文
     * @param key 要查找的参数key
     * @param defaultValue 如果指定参数不存在时返回的默认值
     * @return 如果参数值存在则返回参数值，否则返回默认值，如果参数值不是浮点数据类型，则抛出类型转换异常
     */
    public static float getFloat(Context context, String key, float defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return settings.getFloat(key, defaultValue);
    }

    /**
     * @MethodName: putBoolean
     * @description: 设置一个布尔类型的参数
     * @author:  Mr.Lee
     * @param context  上下文
     * @param key 要设置（修改）的参数key
     * @param value 要设置(修改)的参数值
     * @return 如果设置成功则返回true,否则返回false
     */
    public static boolean putBoolean(Context context, String key, boolean value) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean(key, value);
        return editor.commit();
    }

    /**
     * @MethodName: getBoolean
     * @description: 取布尔类型的参数值
     * @author:  Mr.Lee
     * @param context  上下文
     * @param key 要查找的参数key
     * @return 如果参数值存在则返回参数值，否则返回false,如果参数值不是布尔数据类型则抛出类型转换异常
     */
    public static boolean getBoolean(Context context, String key) {
        return getBoolean(context, key, false);
    }

    /**
     * @MethodName: getBoolean
     * @description: 取布尔类型的参数值
     * @author:  Mr.Lee
     * @param context  上下文
     * @param key 要查找的参数key
     * @param defaultValue 如果指定的参数值不存在时返回的默认参数值
     * @return 如果参数值存在则返回参数值，否则返回默认值，如果参数值不是布尔类型，则抛出类型转换异常
     */
    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        return settings.getBoolean(key, defaultValue);
    }
}
