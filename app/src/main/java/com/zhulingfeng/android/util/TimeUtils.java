package com.zhulingfeng.android.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName: TimeUtils
 * @description: 时间工具类
 * @author:  Mr.Lee
 */
@SuppressWarnings({"unused"})
public class TimeUtils {

    /**
     * @FieldName: DEFAULT_DATE_FORMAT
     * @description: 日期时间格式化对象
     */
    public static final SimpleDateFormat DEFAULT_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /**
     * @FieldName: DATE_FORMAT_DATE
     * @description: 日期格式化对象
     */
    public static final SimpleDateFormat DATE_FORMAT_DATE    = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * @MethodName: TimeUtils
     * @description: 将构造方法私有化，通过JNI方式实例化此类
     * @author:  Mr.Lee
     */
    private TimeUtils() {
        throw new AssertionError();
    }

    /**
     * @MethodName: getTime
     * @description: 将时间的毫秒值转换成指定格式的日期字符串
     * @author:  Mr.Lee
     * @param timeInMillis timeInMillis
     * @param dateFormat   dateFormat
     * @return String
     */
    public static String getTime(long timeInMillis, SimpleDateFormat dateFormat) {
        return dateFormat.format(new Date(timeInMillis));
    }

    /**
     * @MethodName: getTime
     * @description: 将时间的毫秒值转换成指定格式的日期字符串
     * @author:  Mr.Lee
     * @param timeInMillis time
     * @return String
     */
    public static String getTime(long timeInMillis) {
        return getTime(timeInMillis, DEFAULT_DATE_FORMAT);
    }

    /**
     * @MethodName: getCurrentTimeInLong
     * @description: 取当前系统时间的毫秒值
     * @author:  Mr.Lee
     * @return 返回当前系统时间的毫秒值
     */
    public static long getCurrentTimeInLong() {
        return System.currentTimeMillis();
    }

    /**
     * @MethodName: getCurrentTimeInString
     * @description: 取当前系统日期时间字符串
     * @author:  Mr.Lee
     * @return 返回当前系统时间字符串
     */
    public static String getCurrentTimeInString() {
        return getTime(getCurrentTimeInLong());
    }

    /**
     * @MethodName: getCurrentTimeInString
     * @description: 以指定的日期格式返回当前系统时间字符串
     * @author:  Mr.Lee
     * @param dateFormat    dateFormat
     * @return  String
     */
    public static String getCurrentTimeInString(SimpleDateFormat dateFormat) {
        return getTime(getCurrentTimeInLong(), dateFormat);
    }
}
