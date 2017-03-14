package com.zhulingfeng.android.util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.util.Log;

import java.util.Calendar;

/**
 * @ClassName: AlarmTimerUtil
 * @description: 闹钟定时器
 * <br>在特定时间执行可重复执行某一动作</br>
 * @author: Mr.Lee
 */
@SuppressWarnings({"unused"})
public class AlarmTimerUtil {


    /**
     * @InterfaceName: ComponentFlag
     * @description: 组件类别常量
     * @author:  Mr.Leedsfdsfs
     */
    interface ComponentFlag {
        String activity = "activity";
        String broadcast = "broadcast";
        String service = "service";
    }

    /**
     * @InterfaceName: AlarmTimerUtil
     * @description: 调用方法类别常量
     * @author: Mr.Lee
     */
    interface InvokeMethod {
        String set = "set";
        String setRepeating = "setRepeating";
        String setInexactRepeating = "setInexactRepeating";
        String setWindow = "setWindow";
        String setExact = "setExact";
    }


    /**
     * @FieldName: alarmManager
     * @description: 闹钟提示服务管理对象，管理系统级别的提示服务
     */
    private static AlarmManager alarmManager;
    /**
     * @FieldName: pendingIntent
     * @description: PendingIntent对象
     */
    private static PendingIntent pendingIntent;

    /**
     * @MethodName: setActivity
     * @description: 发送一次闹钟提示,启动指定活动, 支持相对时间和绝对时间
     * @author: Mr.Lee
     * @param ctx       上下文
     * @param intent    闹钟响应动作
     * @param startTime  时间间隔
     * @param type      闹钟的类型，
     *                  <br>常用的有5个值：AlarmManager.ELAPSED_REALTIME、 AlarmManager.ELAPSED_REALTIME_WAKEUP、AlarmManager.RTC、 AlarmManager.RTC_WAKEUP、AlarmManager.POWER_OFF_WAKEUP。</br>
     * @param isElapsed 是否采用相对时间
     *                  <br>true:闹钟使用相对时间（相对于系统启动开始）false: 闹钟使用绝对时间，即当前系统时间</br>
     * @return
     */
    public static AlarmManager setActivity(Context ctx, Intent intent, long startTime, int type, boolean isElapsed){
        return alarm(ctx,intent,startTime,0,type,isElapsed,ComponentFlag.activity,InvokeMethod.set);
    }

    /**
     * @MethodName: setRepeatingActivity
     * @description: 设置重复闹钟，定期启动指定活动，支持相对时间和绝对时间
     * @author: Mr.Lee
     * @param ctx          上下文
     * @param intent       闹钟响应动作
     * @param startTime    闹钟提示开始时间(单位：秒)
     * @param intervalTime 时间间隔(单位：秒)
     *                     <br>当值大于6000(1分钟)时，以实际值作为间隔，当值小于6000时，以1分钟作为间隔</br>
     * @param type         闹钟的类型，
     *                     <br>常用的有5个值：AlarmManager.ELAPSED_REALTIME、 AlarmManager.ELAPSED_REALTIME_WAKEUP、AlarmManager.RTC、 AlarmManager.RTC_WAKEUP、AlarmManager.POWER_OFF_WAKEUP。</br>
     * @param isElapsed    是否采用相对时间
     *                     <br>true:闹钟使用相对时间（相对于系统启动开始）false: 闹钟使用绝对时间，即当前系统时间</br>
     * @return
     */
    public static AlarmManager setRepeatingActivity(Context ctx, Intent intent, long startTime, long intervalTime, int type, boolean isElapsed) {
        return alarm(ctx,intent,startTime,intervalTime,type,isElapsed,ComponentFlag.activity,InvokeMethod.setRepeating);
    }

    /**
     * @MethodName: setInexactRepeatActivity
     * @description: 设置重复闹钟，定期启动指定活动，支持相对时间和绝对时间,间隔时间不固定
     * @author: Mr.Lee
     * @param ctx          上下文
     * @param intent       闹钟响应动作
     * @param startTime    闹钟提示开始时间(单位：秒)
     * @param intervalTime 时间间隔(单位：秒)
     *                     <br>当值大于6000(1分钟)时，以实际值作为间隔，当值小于6000时，以1分钟作为间隔</br>
     * @param type         闹钟的类型，
     *                     <br>常用的有5个值：AlarmManager.ELAPSED_REALTIME、 AlarmManager.ELAPSED_REALTIME_WAKEUP、AlarmManager.RTC、 AlarmManager.RTC_WAKEUP、AlarmManager.POWER_OFF_WAKEUP。</br>
     * @param isElapsed    是否采用相对时间
     *                     <br>true:闹钟使用相对时间（相对于系统启动开始）false: 闹钟使用绝对时间，即当前系统时间</br>
     * @return
     */
    public static AlarmManager setInexactRepeatActivity(Context ctx,Intent intent,long startTime,long intervalTime,int type,boolean isElapsed){
        return alarm(ctx,intent,startTime,intervalTime,type,isElapsed,ComponentFlag.activity,InvokeMethod.setInexactRepeating);
    }


    /**
     * @param ctx       上下文
     * @param intent    闹钟响应动作
     * @param startTime  时间间隔
     * @param type      闹钟的类型，
     *                  <br>常用的有5个值：AlarmManager.ELAPSED_REALTIME、 AlarmManager.ELAPSED_REALTIME_WAKEUP、AlarmManager.RTC、 AlarmManager.RTC_WAKEUP、AlarmManager.POWER_OFF_WAKEUP。</br>
     * @param isElapsed 是否采用相对时间
     *                  <br>true:闹钟使用相对时间（相对于系统启动开始）false: 闹钟使用绝对时间，即当前系统时间</br>
     * @return
     * @MethodName: sendDisposableBoradcast
     * @description: 发送一次性广播闹钟提示, 支持相对时间和绝对时间
     * @author: Mr.Lee
     */
    public static AlarmManager setBroadcast(Context ctx, Intent intent, long startTime, int type, boolean isElapsed) {
        return alarm(ctx,intent,startTime,0,type,isElapsed,ComponentFlag.broadcast,InvokeMethod.set);
    }

    /**
     * @MethodName: setRepeatBroadcast
     * @description: 设置重复闹钟，支持相对时间和绝对时间
     * @author: Mr.Lee
     * @param ctx          上下文
     * @param intent       闹钟响应动作
     * @param startTime    闹钟提示开始时间(单位：秒)
     * @param intervalTime 时间间隔(单位：秒)
     *                     <br>当值大于6000(1分钟)时，以实际值作为间隔，当值小于6000时，以1分钟作为间隔</br>
     * @param type         闹钟的类型，
     *                     <br>常用的有5个值：AlarmManager.ELAPSED_REALTIME、 AlarmManager.ELAPSED_REALTIME_WAKEUP、AlarmManager.RTC、 AlarmManager.RTC_WAKEUP、AlarmManager.POWER_OFF_WAKEUP。</br>
     * @param isElapsed    是否采用相对时间
     *                     <br>true:闹钟使用相对时间（相对于系统启动开始）false: 闹钟使用绝对时间，即当前系统时间</br>
     * @return
     */
    public static AlarmManager setRepeatingBroadcast(Context ctx, Intent intent, long startTime, long intervalTime, int type, boolean isElapsed) {
        return alarm(ctx,intent,startTime,intervalTime,type,isElapsed,ComponentFlag.broadcast,InvokeMethod.setRepeating);
    }

    /**
     * @MethodName: setInexactRepeatBroadCast
     * @description: 设置重复闹钟，支持相对时间和绝对时间,间隔时间不固定
     * @author: Mr.Lee
     * @param ctx          上下文
     * @param intent       闹钟响应动作
     * @param startTime    闹钟提示开始时间(单位：秒)
     * @param intervalTime 时间间隔(单位：秒)
     *                     <br>当值大于6000(1分钟)时，以实际值作为间隔，当值小于6000时，以1分钟作为间隔</br>
     * @param type         闹钟的类型，
     *                     <br>常用的有5个值：AlarmManager.ELAPSED_REALTIME、 AlarmManager.ELAPSED_REALTIME_WAKEUP、AlarmManager.RTC、 AlarmManager.RTC_WAKEUP、AlarmManager.POWER_OFF_WAKEUP。</br>
     * @param isElapsed    是否采用相对时间
     *                     <br>true:闹钟使用相对时间（相对于系统启动开始）false: 闹钟使用绝对时间，即当前系统时间</br>
     * @return
     */
    public static AlarmManager setInexactRepeatBroadCast(Context ctx,Intent intent,long startTime,long intervalTime,int type,boolean isElapsed){
        return alarm(ctx,intent,startTime,intervalTime,type,isElapsed,ComponentFlag.broadcast,InvokeMethod.setInexactRepeating);
    }

    /**
     * @MethodName: setService
     * @description: 发送一次闹钟提示,启动指定服务, 支持相对时间和绝对时间
     * @author: Mr.Lee
     * @param ctx       上下文
     * @param intent    闹钟响应动作
     * @param startTime  时间间隔
     * @param type      闹钟的类型，
     *                  <br>常用的有5个值：AlarmManager.ELAPSED_REALTIME、 AlarmManager.ELAPSED_REALTIME_WAKEUP、AlarmManager.RTC、 AlarmManager.RTC_WAKEUP、AlarmManager.POWER_OFF_WAKEUP。</br>
     * @param isElapsed 是否采用相对时间
     *                  <br>true:闹钟使用相对时间（相对于系统启动开始）false: 闹钟使用绝对时间，即当前系统时间</br>
     * @return
     */
    public static AlarmManager setService(Context ctx, Intent intent, long startTime, int type, boolean isElapsed){
        return alarm(ctx,intent,startTime,0,type,isElapsed,ComponentFlag.service,InvokeMethod.set);
    }

    /**
     * @MethodName: setRepeatingService
     * @description: 设置重复闹钟，定期启动指定服务，支持相对时间和绝对时间
     * @author: Mr.Lee
     * @param ctx          上下文
     * @param intent       闹钟响应动作
     * @param startTime    闹钟提示开始时间(单位：秒)
     * @param intervalTime 时间间隔(单位：秒)
     *                     <br>当值大于6000(1分钟)时，以实际值作为间隔，当值小于6000时，以1分钟作为间隔</br>
     * @param type         闹钟的类型，
     *                     <br>常用的有5个值：AlarmManager.ELAPSED_REALTIME、 AlarmManager.ELAPSED_REALTIME_WAKEUP、AlarmManager.RTC、 AlarmManager.RTC_WAKEUP、AlarmManager.POWER_OFF_WAKEUP。</br>
     * @param isElapsed    是否采用相对时间
     *                     <br>true:闹钟使用相对时间（相对于系统启动开始）false: 闹钟使用绝对时间，即当前系统时间</br>
     * @return
     */
    public static AlarmManager setRepeatingService(Context ctx, Intent intent, long startTime, long intervalTime, int type, boolean isElapsed) {
        return alarm(ctx,intent,startTime,intervalTime,type,isElapsed,ComponentFlag.service,InvokeMethod.setRepeating);
    }

    /**
     * @MethodName: setInexactRepeatService
     * @description: 设置重复闹钟，定期启动指定服务，支持相对时间和绝对时间,间隔时间不固定
     * @author: Mr.Lee
     * @param ctx          上下文
     * @param intent       闹钟响应动作
     * @param startTime    闹钟提示开始时间(单位：秒)
     * @param intervalTime 时间间隔(单位：秒)
     *                     <br>当值大于6000(1分钟)时，以实际值作为间隔，当值小于6000时，以1分钟作为间隔</br>
     * @param type         闹钟的类型，
     *                     <br>常用的有5个值：AlarmManager.ELAPSED_REALTIME、 AlarmManager.ELAPSED_REALTIME_WAKEUP、AlarmManager.RTC、 AlarmManager.RTC_WAKEUP、AlarmManager.POWER_OFF_WAKEUP。</br>
     * @param isElapsed    是否采用相对时间
     *                     <br>true:闹钟使用相对时间（相对于系统启动开始）false: 闹钟使用绝对时间，即当前系统时间</br>
     * @return
     */
    public static AlarmManager setInexactRepeatService(Context ctx,Intent intent,long startTime,long intervalTime,int type,boolean isElapsed){
        return alarm(ctx,intent,startTime,intervalTime,type,isElapsed,ComponentFlag.service,InvokeMethod.setInexactRepeating);
    }

    /**
     * @MethodName: alarm
     * @description: 设置闹钟提示服务
     * @author:  Mr.Lee
     * @param ctx 上下文
     * @param intent 闹钟响应动作
     * @param startTime 闹钟提示开始时间(单位：秒)
     * @param intervalTime 时间间隔(单位：秒)
     * <br>当值大于6000(1分钟)时，以实际值作为间隔，当值小于6000时，以1分钟作为间隔</br>
     * @param type  闹钟的类型，
     *  <br>常用的有5个值：AlarmManager.ELAPSED_REALTIME、 AlarmManager.ELAPSED_REALTIME_WAKEUP、AlarmManager.RTC、 AlarmManager.RTC_WAKEUP、AlarmManager.POWER_OFF_WAKEUP。</br>
     * @param isElapsed 是否采用相对时间
     * <br>true:闹钟使用相对时间（相对于系统启动开始）false: 闹钟使用绝对时间，即当前系统时间</br>
     * @param componentFlag 响应组件类别常量
     * @param invokeMethod 调用方法类别常量
     * @return AlarmManager
     */
    private static AlarmManager alarm(Context ctx, Intent intent, long startTime, long intervalTime, int type, boolean isElapsed, String componentFlag, String invokeMethod) {
        switch (componentFlag) {
            case ComponentFlag.activity:
                pendingIntent = PendingIntent.getActivity(ctx, 0, intent, 0);
                break;
            case ComponentFlag.broadcast:
                pendingIntent = PendingIntent.getBroadcast(ctx, 0, intent, 0);
                break;
            case ComponentFlag.service:
                pendingIntent = PendingIntent.getService(ctx, 0, intent, 0);
                break;
            default:
                break;
        }
        alarmManager = (AlarmManager) ctx.getSystemService(Context.ALARM_SERVICE);

        long startT = 0;
        if (isElapsed) {
            if (type == 3 || type == 2) {
                startT = SystemClock.elapsedRealtime() + startTime;
                switch (invokeMethod) {
                    case InvokeMethod.set:
                        alarmManager.set(type, startT, pendingIntent);
                        break;
                    case InvokeMethod.setRepeating:
                        alarmManager.setRepeating(type, startT, intervalTime, pendingIntent);
                        break;
                    case InvokeMethod.setInexactRepeating:
                        alarmManager.setInexactRepeating(type, startT, intervalTime, pendingIntent);
                        break;
                    default:
                        break;
                }
            }
        } else {
            if (type == 1 || type == 0 || type == 4) {
                startT = System.currentTimeMillis() + startTime;
                switch (invokeMethod) {
                    case InvokeMethod.set:
                        alarmManager.set(type, startT, pendingIntent);
                        break;
                    case InvokeMethod.setRepeating:
                        alarmManager.setRepeating(type, startT, intervalTime, pendingIntent);
                        break;
                    case InvokeMethod.setInexactRepeating:
                        alarmManager.setInexactRepeating(type, startT, intervalTime, pendingIntent);
                        break;
                    default:
                        break;
                }
            }
        }
        return alarmManager;
    }

    /**
     * @MethodName: destoryAlarmManager
     * @description: 当应用程序退出时，取消指定闹钟服务
     * @author: Mr.Lee
     */
    public static void destoryAlarmManager() {
        if (null != alarmManager) {
            alarmManager.cancel(pendingIntent);
        }
    }

    /**
     * @MethodName: setMidnightAlarm
     * @description: 设置午夜定时器, 午夜12点发送广播, MIDNIGHT_ALARM_FILTER. 实际测试可能会有一分钟左右的偏差.
     * @author: Mr.Lee
     * @param ctx    应用上下文
     * @param action 闹钟响应的动作
     */
    public static void setMidnightAlarm(Context ctx, String action) {
        Context appCtx = ctx.getApplicationContext();
        Intent intent = new Intent(action);
        PendingIntent pi = PendingIntent.getBroadcast(appCtx, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager alarmManager = (AlarmManager) appCtx.getSystemService(appCtx.ALARM_SERVICE);

        // 午夜12点的标准计时, 来源于SO, 实际测试可能会有一分钟左右的偏差.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.AM_PM, Calendar.AM);
        calendar.add(Calendar.DAY_OF_MONTH, 1);

        // 显示剩余时间
        long now = Calendar.getInstance().getTimeInMillis();
        Log.e("剩余时间(秒): ", ((calendar.getTimeInMillis() - now) / 1000) + "");

        // 设置之前先取消前一个PendingIntent
        alarmManager.cancel(pi);
        // 设置每一天的计时器
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY, pi);
    }
}
