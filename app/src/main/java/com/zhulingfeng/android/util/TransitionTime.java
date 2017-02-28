package com.zhulingfeng.android.util;

import android.util.Log;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 用来计算显示的时间是多久之前的！
 */
@SuppressWarnings({"unused"})
public class TransitionTime {

    /**
     * @FieldName: endDate
     * @description: 结束日期
     */
    private Date endDate;
    /**
     * @FieldName: formatBuilder
     * @description: 日期格式化对象
     */
    private static SimpleDateFormat formatBuilder;
    /**
     * @FieldName: WEEKDAYS
     * @description: 一周的天数
     */
    public static final int WEEKDAYS = 7;
    /**
     * @FieldName: WEEK
     * @description: 星期数组
     */
    public static String[] WEEK = { "周日", "周一", "周二", "周三", "周四", "周五", "周六" };

    /**
     * @FieldName: timeMills
     * @description: 时间的毫秒值
     */
    public long timeMills = 0;

    /**
     * @MethodName: TransitionTime
     * @description: 构造方法
     * @author:  Mr.Lee
     */
    public TransitionTime(long endTime) {
        endDate = new Date(endTime);
    }

    /**
     * @MethodName: convert
     * @description: 时间转换
     * @author:  Mr.Lee
     * @param time    时间
     * @param timeFormat 时间的格式 eg: yyyy-MM-dd hh:mm:ss
     * @return  String
     */
    public String convert(String time, String timeFormat) {
        timeMills = Long.parseLong(time);
        Date date = new Date(timeMills);
        String strs = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(timeFormat);
            strs = sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strs;
    }

    /**
     * @MethodName: twoDateDistance
     * @description: 计算机两个日期之间的时间差
     * @author:  Mr.Lee
     * @param startTime  开始的事件
     * @return  返回距离发帖时间的时间差
     */
    public String twoDateDistance(String startTime) {
        if (startTime.equals("")) {
            return "";
        }
        timeMills = Long.parseLong(startTime);
        Date startDate = new Date(timeMills);
        if (startDate == null || endDate == null) {
            return null;
        }
        long timeLong = endDate.getTime() - startDate.getTime();
        if (timeLong <= 0) {
            return "刚刚";
        }
        else if (timeLong < 60 * 1000) {
            return timeLong / 1000 + "秒前";
        }
        else if (timeLong < 60 * 60 * 1000) {
            timeLong = timeLong / 1000 / 60;
            return timeLong + "分钟前";
        }
        else if (timeLong < 60 * 60 * 24 * 1000) {
            timeLong = timeLong / 60 / 60 / 1000;
            return timeLong + "小时前";
        }
        else if (timeLong < 60 * 60 * 24 * 1000 * 7) {
            timeLong = timeLong / 1000 / 60 / 60 / 24;
            return timeLong + "天前";
        }
        else if (timeLong < 60 * 60 * 24 * 1000 * 7 * 4) {
            timeLong = timeLong / 1000 / 60 / 60 / 24 / 7;
            return timeLong + "周前";
        }
        else {
            timeLong = timeLong / 1000 / 60 / 60 / 24;
            return timeLong + "天前";
        }
    }

    /**
     * @MethodName: getTimeDesc
     * @description: UTM转换成日期描述，如三周前，上午，昨天等
     * @author:  Mr.Lee
     * @param milliseconds milliseconds
     * @param isShowWeek 是否采用周的形式显示  true 显示为3周前，false 则显示为时间格式mm-dd
     * @return  如三周前，上午，昨天等
     */
    public static String getTimeDesc(long milliseconds,boolean isShowWeek) {
        StringBuffer sb = new StringBuffer();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliseconds);
        long hour = calendar.get(Calendar.HOUR_OF_DAY);

        calendar.setTimeInMillis(System.currentTimeMillis());
        long hourNow = calendar.get(Calendar.HOUR_OF_DAY);
        Log.v("---------->---", System.currentTimeMillis() + "----------"+ milliseconds);
        long datetime = System.currentTimeMillis() - (milliseconds);
        long day = (long) Math.floor(datetime / 24 / 60 / 60 / 1000.0f) + (hourNow<hour?1:0);// 天前

        if (day <= 7) {// 一周内
            if (day == 0) {// 今天
                if(hour <=4){
                    sb.append(" 凌晨 ");
                }else if(hour >4 && hour <=6){
                    sb.append(" 早上 ");
                }else if(hour >6 && hour <=11){
                    sb.append(" 上午 ");
                }else if(hour >11 && hour <=13){
                    sb.append(" 中午 ");
                }else if(hour >13 && hour <=18){
                    sb.append(" 下午 ");
                }else if(hour >18 && hour <=19){
                    sb.append(" 傍晚 ");
                }else if(hour >19 && hour <=24){
                    sb.append(" 晚上 ");
                }else{
                    sb.append("今天 ");
                }
            } else if (day == 1) {// 昨天
                sb.append(" 昨天 ");
            } else if (day == 2) {// 前天
                sb.append(" 前天 ");
            } else {
                sb.append(" " + DateToWeek(milliseconds) +" ");
            }
        } else {// 一周之前
            if(isShowWeek){
                sb.append((day%7==0?(day/7):(day/7+1)) + "周前");
            }else{
                formatBuilder = new SimpleDateFormat("MM-dd");
                String time = formatBuilder.format(milliseconds);
                sb.append(time);
            }
        }
        Log.v("sb---", sb.toString() + "");
        return sb.toString();

    }

    /**
     * @MethodName: getTimeDesc
     * @description: UTM转换成日期描述，如三周前，上午，昨天等
     * @author:  Mr.Lee
     * @param milliseconds    时间
     * @return UTM转换成日期描述，如三周前，上午，昨天等
     */
    public static String getTimeDesc(long milliseconds) {
        return getTimeDesc(milliseconds,true);
    }

    /**
     * @MethodName: getDisplayTime
     * @description: UTM转换成日期 ,hh:mm
     * @author:  Mr.Lee
     * @param milliseconds  milliseconds
     * @return UTM转换成日期 ,hh:mm
     */
    public static String getDisplayTime(long milliseconds) {
        formatBuilder = new SimpleDateFormat("HH:mm");
        String time = formatBuilder.format(milliseconds);
        return time;
    }

    /**
     * @MethodName: getDisplayTimeAndDesc
     * @description: UTM转换成带描述的日期
     * @author:  Mr.Lee
     * @param milliseconds  milliseconds
     * @return   UTM转换成带描述的日期
     */
    public static String getDisplayTimeAndDesc(long milliseconds) {
        formatBuilder = new SimpleDateFormat("HH:mm");
        String time = formatBuilder.format(milliseconds);
        StringBuffer sb = new StringBuffer();
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliseconds);
        long hour = calendar.get(Calendar.HOUR_OF_DAY);
        Log.v("---------->---", System.currentTimeMillis() + "----------"+ milliseconds);
        long datetime = System.currentTimeMillis() - (milliseconds);
        long day = (long) Math.ceil(datetime / 24 / 60 / 60 / 1000.0f);// 天前
        Log.v("day---hour---time---", day + "----" + hour + "-----" + time);

        if (day <= 7) {// 一周内
            if (day == 0) {// 今天
                if(hour <=4){
                    sb.append(" 凌晨 " + time);
                }else if(hour >4 && hour <=6){
                    sb.append(" 早上 " + time);
                }else if(hour >6 && hour <=11){
                    sb.append(" 上午 " + time);
                }else if(hour >11 && hour <=13){
                    sb.append(" 中午 " + time);
                }else if(hour >13 && hour <=18){
                    sb.append(" 下午 " + time);
                }else if(hour >18 && hour <=19){
                    sb.append(" 傍晚 " + time);
                }else if(hour >19 && hour <=24){
                    sb.append(" 晚上 " + time);
                }else{
                    sb.append("今天 " + time);
                }
            } else if (day == 1) {// 昨天
                sb.append("昨天 " + time);
            } else if (day == 2) {// 前天
                sb.append("前天 " + time);
            } else {
                sb.append(DateToWeek(milliseconds) + time);
            }
        } else {// 一周之前
            sb.append(day % 7 + "周前");
        }
        Log.v("sb---", sb.toString() + "");
        return sb.toString();

    }

    /**
     * @MethodName: DateToWeek
     * @description: 日期变量转成对应的星期字符串
     * @author:  Mr.Lee
     * @param milliseconds    data
     * @return  日期变量转成对应的星期字符串
     */
    public static String DateToWeek(long milliseconds) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliseconds);
        int dayIndex = calendar.get(Calendar.DAY_OF_WEEK);
        if (dayIndex < 1 || dayIndex > WEEKDAYS) {
            return null;
        }
        return WEEK[dayIndex - 1];
    }

    /**
     * @MethodName: diffDateAsDesc
     * @description: 将时间间隔转换成描述性字符串，如2天前，3月1天后等
     * @author:  Mr.Lee
     * @param toDate 相对的日期
     * @param isFull 是否全部显示 true 全部显示 false 简单显示
     * @return  将时间间隔转换成描述性字符串，如2天前，3月1天后等。
     */
    public static String diffDateAsDesc(Date toDate, boolean isFull) {
        String diffDesc = "";
        String fix = "";
        Long diffTime;
        Date curDate = new Date();
        if(curDate.getTime() > toDate.getTime()) {
            diffTime = curDate.getTime() - toDate.getTime();
            fix = "前";
        } else {
            diffTime = toDate.getTime() - curDate.getTime();
            fix = "后";
        }

        //换算成分钟数，防止Int溢出。
        diffTime = diffTime/1000/60;

        Long year = diffTime/(60 * 24 * 30 * 12);
        diffTime = diffTime % (60 * 24 * 30 * 12);
        if(year > 0) {
            diffDesc = diffDesc + year + "年";
            if(!isFull) {
                return diffDesc + fix;
            }
        }

        Long month = diffTime/(60 * 24 * 30);
        diffTime = diffTime % (60 * 24 * 30);
        if(month > 0) {
            diffDesc = diffDesc + month + "月";
            if(!isFull) {
                return diffDesc + fix;
            }
        }

        Long day = diffTime / 60 / 24;
        diffTime = diffTime % (60 * 24);
        if(day > 0) {
            diffDesc = diffDesc + day + "天";
            if(!isFull) {
                return diffDesc + fix;
            }
        }

        Long hour = diffTime / (60);
        diffTime = diffTime % (60);
        if(hour > 0) {
            diffDesc = diffDesc + hour + "时";
            if(!isFull) {
                return diffDesc + fix;
            }
        }

        Long minitue = diffTime ;
        if(minitue > 0) {
            diffDesc = diffDesc + minitue + "分";
            if(!isFull) {
                return diffDesc + fix;
            }
        }

        return diffDesc + fix;
    }
}
