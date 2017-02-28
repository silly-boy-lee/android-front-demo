package com.zhulingfeng.android.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName: DateUtil
 * @description: 日期操作工具类.
 * @author:  Mr.Lee
 */
@SuppressWarnings({"unused"})
public class DateUtil {
    /**
     * @FieldName: FORMAT_Y
     * @description: 英文简写如：2010
     */
    public static String FORMAT_Y = "yyyy";
    /**
     * @FieldName: FORMAT_HM
     * @description: 英文简写如：12:01
     */
    public static String FORMAT_HM = "HH:mm";
    /**
     * @FieldName: FORMAT_MDHM
     * @description: 英文简写如：1-12 12:01
     */
    public static String FORMAT_MDHM = "MM-dd HH:mm";
    /**
     * @FieldName: FORMAT_YMD
     * @description: 英文简写（默认）如：2010-12-01
     */
    public static String FORMAT_YMD = "yyyy-MM-dd";
    /**
     * @FieldName: FORMAT_YMDHM
     * @description: 英文全称  如：2010-12-01 23:15
     */
    public static String FORMAT_YMDHM = "yyyy-MM-dd HH:mm";
    /**
     * @FieldName: FORMAT_YMDHMS
     * @description: 英文全称  如：2010-12-01 23:15:06
     */
    public static String FORMAT_YMDHMS = "yyyy-MM-dd HH:mm:ss";
    /**
     * @FieldName: FORMAT_FULL
     * @description: 精确到毫秒的完整时间    如：yyyy-MM-dd HH:mm:ss.S
     */
    public static String FORMAT_FULL = "yyyy-MM-dd HH:mm:ss.S";
    /**
     * @FieldName: FORMAT_FULL_SN
     * @description: 精确到毫秒的完整时间    如：yyyy-MM-dd HH:mm:ss.S
     */
    public static String FORMAT_FULL_SN = "yyyyMMddHHmmssS";
    /**
     * @FieldName: FORMAT_FULL_SN
     * @description: 中文简写  如：2010年12月01日
     */
    public static String FORMAT_YMD_CN = "yyyy年MM月dd日";
    /**
     * @FieldName: FORMAT_YMDH_CN
     * @description: 中文简写  如：2010年12月01日  12时
     */
    public static String FORMAT_YMDH_CN = "yyyy年MM月dd日 HH时";
    /**
     * @FieldName: FORMAT_YMDHM_CN
     * @description: 中文简写  如：2010年12月01日  12时12分
     */
    public static String FORMAT_YMDHM_CN = "yyyy年MM月dd日 HH时mm分";
    /**
     * @FieldName: FORMAT_YMDHMS_CN
     * @description: 中文全称  如：2010年12月01日  23时15分06秒
     */
    public static String FORMAT_YMDHMS_CN = "yyyy年MM月dd日  HH时mm分ss秒";
    /**
     * @FieldName: FORMAT_FULL_CN
     * @description: 精确到毫秒的完整中文时间
     */
    public static String FORMAT_FULL_CN = "yyyy年MM月dd日  HH时mm分ss秒SSS毫秒";
    /**
     * @FieldName: calendar
     * @description: 日历对象
     */
    public static Calendar calendar = null;
    /**
     * @FieldName: FORMAT
     * @description: 格式化对象
     */
    private static final String FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * @MethodName: str2Date
     * @description: 将日期字符串转找为日期对象
     * @author:  Mr.Lee
     * @param str 日期字符串
     * @return Date
     */
    public static Date str2Date(String str) {
        return str2Date(str, null);
    }

    /**
     * @MethodName: str2Date
     * @description: 将日期字符串转找为日期对象
     * @author:  Mr.Lee
     * @param str 日期字符串
     * @return Date
     */
    public static Date str2Date(String str, String format) {
        if (str == null || str.length() == 0) {
            return null;
        }
        if (format == null || format.length() == 0) {
            format = FORMAT;
        }
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            date = sdf.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * @MethodName: str2Calendar
     * @description: 将字符串转化成Calendar对象
     * @author:  Mr.Lee
     * @param str 日历字符串
     * @return Calendar
     */
    public static Calendar str2Calendar(String str) {
        return str2Calendar(str, null);
    }

    /**
     * @MethodName: str2Calendar
     * @description: 将字符串转化成Calendar对象
     * @author:  Mr.Lee
     * @param str 日历字符串
     * @param format 格式化字符串
     * @return Calendar
     */
    public static Calendar str2Calendar(String str, String format) {
        Date date = str2Date(str, format);
        if (date == null) {
            return null;
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c;
    }

    /**
     * @MethodName: date2Str
     * @description:
     * @author:  Mr.Lee
     * @param c
     * @return
     */
    public static String date2Str(Calendar c) {// yyyy-MM-dd HH:mm:ss
        return date2Str(c, null);
    }

    /**
     * @MethodName: date2Str
     * @description:
     * @author:  Mr.Lee
     * @param c
     * @param format
     * @return
     */
    public static String date2Str(Calendar c, String format) {
        if (c == null) {
            return null;
        }
        return date2Str(c.getTime(), format);
    }

    /**
     * @MethodName:
     * @description:
     * @author:  Mr.Lee
     * @param d
     * @return
     */
    public static String date2Str(Date d) {// yyyy-MM-dd HH:mm:ss
        return date2Str(d, null);
    }

    /**
     * @MethodName: date2Str
     * @description:
     * @author:  Mr.Lee
     * @param d
     * @param format
     * @return
     */
    public static String date2Str(Date d, String format) {// yyyy-MM-dd HH:mm:ss
        if (d == null) {
            return null;
        }
        if (format == null || format.length() == 0) {
            format = FORMAT;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String s = sdf.format(d);
        return s;
    }

    /**
     * @MethodName: getCurDateStr
     * @description: 以yyyy-mm-dd hh:mm:ss格式返回当前日期字符串
     * @author:  Mr.Lee
     * @return yyyy-mm-dd hh:mm:ss格式返回当前日期字符串
     */
    public static String getCurDateStr() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        return c.get(Calendar.YEAR) + "-" + (c.get(Calendar.MONTH)<10 ? "0" + c.get(Calendar.MONTH):c.get(Calendar.MONTH) + 1) + "-" +
                (c.get(Calendar.DAY_OF_MONTH) <10 ? "0" + c.get(Calendar.DAY_OF_MONTH) :c.get(Calendar.DAY_OF_MONTH))+ "-" +
                (c.get(Calendar.HOUR_OF_DAY) <10 ? "0" + c.get(Calendar.HOUR_OF_DAY) :c.get(Calendar.HOUR_OF_DAY))+ ":" +
                (c.get(Calendar.MINUTE) <10 ? "0" + c.get(Calendar.MINUTE) :c.get(Calendar.MINUTE))+ ":" +
                (c.get(Calendar.SECOND) <10 ? "0" + c.get(Calendar.SECOND) :c.get(Calendar.SECOND));
    }

    /**
     * @MethodName: getCurDateStr
     * @description: 获得当前日期的字符串格式
     * @author:  Mr.Lee
     * @param format    格式化的类型
     * @return  返回格式化之后的事件
     */
    public static String getCurDateStr(String format) {
        Calendar c = Calendar.getInstance();
        return date2Str(c, format);
    }

    /**
     * @MethodName:
     * @description:
     * @author:  Mr.Lee
     * @param time 当前的时间
     * @return  格式到秒
     */
    public static String getMillon(long time) {
        return new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(time);
    }

    /**
     * @MethodName: getDay
     * @description:
     * @author:  Mr.Lee
     * @param time  当前的时间
     * @return  当前的天
     */
    public static String getDay(long time) {
        return new SimpleDateFormat("yyyy-MM-dd").format(time);
    }

    /**
     * @MethodName: getSMillon
     * @description:
     * @author:  Mr.Lee
     * @param time 时间
     * @return 返回一个毫秒
     */
    public static String getSMillon(long time) {
        return new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss-SSS").format(time);
    }

    /**
     * @MethodName: addMonth
     * @description: 在日期上增加数个整月
     * @author:  Mr.Lee
     * @param date 日期
     * @param n 要增加的月数
     * @return   增加数个整月
     */
    public static Date addMonth(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, n);
        return cal.getTime();
    }

    /**
     * @MethodName: addDay
     * @description: 在日期上增加天数
     * @author:  Mr.Lee
     * @param date 日期
     * @param n 要增加的天数
     * @return   增加之后的天数
     */
    public static Date addDay(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, n);
        return cal.getTime();
    }

    /**
     * @MethodName: getNextHour
     * @description: 获取距现在某一小时的时刻
     * @author:  Mr.Lee
     * @param format 格式化时间的格式
     * @param h 距现在的小时 例如：h=-1为上一个小时，h=1为下一个小时
     * @return  获取距现在某一小时的时刻
     */
    public static String getNextHour(String format, int h) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        Date date = new Date();
        date.setTime(date.getTime() + h * 60 * 60 * 1000);
        return sdf.format(date);
    }

    /**
     * @MethodName: getTimeString
     * @description: 获取时间戳
     * @author:  Mr.Lee
     * @return 获取时间戳
     */
    public static String getTimeString() {
        SimpleDateFormat df = new SimpleDateFormat(FORMAT_FULL);
        Calendar calendar = Calendar.getInstance();
        return df.format(calendar.getTime());

    }
    /**
     * @MethodName: getMonth
     * @description: 返回月
     * @author:  Mr.Lee
     * @param date Date 日期
     * @return 返回月份
     */
    public static int getMonth(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * @MethodName: getDay
     * @description: 返回日
     * @author:  Mr.Lee
     * @param date Date 日期
     * @return 返回日份
     */
    public static int getDay(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * @MethodName: getHour
     * @description: 返回小时
     * @author:  Mr.Lee
     * @param date 日期
     * @return 返回小时
     */
    public static int getHour(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * @MethodName: getMinute
     * @description: 返回分
     * @author:  Mr.Lee
     * @param date 日期
     * @return 返回分钟
     */
    public static int getMinute(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }

    /**
     * @MethodName: getDatePattern
     * @description: 获得默认的 date pattern
     * @author:  Mr.Lee
     * @return  默认的格式
     */
    public static String getDatePattern() {
        return FORMAT_YMDHMS;
    }

    /**
     * @MethodName: getSecond
     * @description: 返回秒钟
     * @author:  Mr.Lee
     * @param date Date 日期
     * @return 返回秒钟
     */
    public static int getSecond(Date date) {

        calendar.setTime(date);
        return calendar.get(Calendar.SECOND);
    }

    /**
     * @MethodName: parse
     * @description: 使用预设格式提取字符串日期
     * @author:  Mr.Lee
     * @param strDate 日期字符串
     * @return 提取字符串的日期
     */
    public static Date parse(String strDate) {
        return parse(strDate, getDatePattern());
    }

    /**
     * @MethodName: getMillis
     * @description: 返回毫秒
     * @author:  Mr.Lee
     * @param date 日期
     * @return 返回毫秒
     */
    public static long getMillis(Date date) {
        calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getTimeInMillis();
    }

    /**
     * @MethodName: countDays
     * @description: 按默认格式的字符串距离今天的天数
     * @author:  Mr.Lee
     * @param date 日期字符串
     * @return 按默认格式的字符串距离今天的天数
     */
    public static int countDays(String date) {
        long t = Calendar.getInstance().getTime().getTime();
        Calendar c = Calendar.getInstance();
        c.setTime(parse(date));
        long t1 = c.getTime().getTime();
        return (int) (t / 1000 - t1 / 1000) / 3600 / 24;

    }

    /**
     * @MethodName:
     * @description: 使用用户格式提取字符串日期
     * @author:  Mr.Lee
     * @param strDate 日期字符串
     * @param pattern 日期格式
     * @return  提取字符串日期
     */
    public static Date parse(String strDate, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        try {
            return df.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @MethodName: countDays
     * @description: 按用户格式字符串距离今天的天数
     * @author:  Mr.Lee
     * @param date 日期字符串
     * @param format 日期格式
     * @return  按用户格式字符串距离今天的天数
     */
    public static int countDays(String date, String format) {
        long t = Calendar.getInstance().getTime().getTime();
        Calendar c = Calendar.getInstance();
        c.setTime(parse(date, format));
        long t1 = c.getTime().getTime();
        return (int) (t / 1000 - t1 / 1000) / 3600 / 24;

    }
}
