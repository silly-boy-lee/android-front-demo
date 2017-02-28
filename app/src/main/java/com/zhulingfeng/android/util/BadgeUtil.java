package com.zhulingfeng.android.util;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


/**
 * @ClassName: BadgeUtil
 * @description: 角标工具类，应用图标显示角标
 * @author:  Mr.Lee
 */
@SuppressWarnings({"unused"})
public class BadgeUtil {

    /**
     * <p> 在使用此类时，将要申请以下权限
     *   <uses-permission android:name="com.android.launcher.permission.INSTALL_SHORTCUT"/>
         <uses-permission android:name="com.android.launcher.permission.UNINSTALL_SHORTCUT"/>
         <uses-permission android:name="com.android.launcher.permission.READ_SETTINGS"/>
         <uses-permission android:name="com.android.launcher.permission.WRITE_SETTINGS"/>
         <uses-permission android:name="com.android.launcher2.permission.READ_SETTINGS"/>
         <uses-permission android:name="com.android.launcher2.permission.WRITE_SETTINGS"/>
         <uses-permission android:name="com.android.launcher3.permission.READ_SETTINGS"/>
         <uses-permission android:name="com.android.launcher3.permission.WRITE_SETTINGS"/>
         <uses-permission android:name="org.adw.launcher.permission.READ_SETTINGS"/>
         <uses-permission android:name="org.adw.launcher.permission.WRITE_SETTINGS"/>
         <uses-permission android:name="com.htc.launcher.permission.READ_SETTINGS"/>
         <uses-permission android:name="com.htc.launcher.permission.WRITE_SETTINGS"/>
         <uses-permission android:name="com.qihoo360.launcher.permission.READ_SETTINGS"/>
         <uses-permission android:name="com.qihoo360.launcher.permission.WRITE_SETTINGS"/>
         <uses-permission android:name="com.lge.launcher.permission.READ_SETTINGS"/>
         <uses-permission android:name="com.lge.launcher.permission.WRITE_SETTINGS"/>
         <uses-permission android:name="net.qihoo.launcher.permission.READ_SETTINGS"/>
         <uses-permission android:name="net.qihoo.launcher.permission.WRITE_SETTINGS"/>
         <uses-permission android:name="org.adwfreak.launcher.permission.READ_SETTINGS"/>
         <uses-permission android:name="org.adwfreak.launcher.permission.WRITE_SETTINGS"/>
         <uses-permission android:name="org.adw.launcher_donut.permission.READ_SETTINGS"/>
         <uses-permission android:name="org.adw.launcher_donut.permission.WRITE_SETTINGS"/>
         <uses-permission android:name="com.huawei.launcher3.permission.READ_SETTINGS"/>
         <uses-permission android:name="com.huawei.launcher3.permission.WRITE_SETTINGS"/>
         <uses-permission android:name="com.fede.launcher.permission.READ_SETTINGS"/>
         <uses-permission android:name="com.fede.launcher.permission.WRITE_SETTINGS"/>
         <uses-permission android:name="com.sec.android.app.twlauncher.settings.READ_SETTINGS"/>
         <uses-permission android:name="com.sec.android.app.twlauncher.settings.WRITE_SETTINGS"/>
         <uses-permission android:name="com.anddoes.launcher.permission.READ_SETTINGS"/>
         <uses-permission android:name="com.anddoes.launcher.permission.WRITE_SETTINGS"/>
         <uses-permission android:name="com.tencent.qqlauncher.permission.READ_SETTINGS"/>
         <uses-permission android:name="com.tencent.qqlauncher.permission.WRITE_SETTINGS"/>
         <uses-permission android:name="com.huawei.launcher2.permission.READ_SETTINGS"/>
         <uses-permission android:name="com.huawei.launcher2.permission.WRITE_SETTINGS"/>
         <uses-permission android:name="com.android.mylauncher.permission.READ_SETTINGS"/>
         <uses-permission android:name="com.android.mylauncher.permission.WRITE_SETTINGS"/>
         <uses-permission android:name="com.ebproductions.android.launcher.permission.READ_SETTINGS"/>
         <uses-permission android:name="com.ebproductions.android.launcher.permission.WRITE_SETTINGS"/>
         <uses-permission android:name="com.oppo.launcher.permission.READ_SETTINGS"/>
         <uses-permission android:name="com.oppo.launcher.permission.WRITE_SETTINGS"/>
         <uses-permission android:name="com.miui.mihome2.permission.READ_SETTINGS"/>
         <uses-permission android:name="com.miui.mihome2.permission.WRITE_SETTINGS"/>
         <uses-permission android:name="com.huawei.android.launcher.permission.READ_SETTINGS"/>
         <uses-permission android:name="com.huawei.android.launcher.permission.WRITE_SETTINGS"/>
         <uses-permission android:name="telecom.mdesk.permission.READ_SETTINGS"/>
         <uses-permission android:name="telecom.mdesk.permission.WRITE_SETTINGS"/>
         <uses-permission android:name="dianxin.permission.ACCESS_LAUNCHER_DATA"/>
         <uses-permission android:name="android.hardware.sensor.accelerometer"/>
     * </p>
     */

    /**
     * @MethodName: setBadgeCount
     * @description: 设置应用快捷方式Badge(图标)目前支持Launcher:
     * @author:  Mr.Lee
     * @param context 上下文
     * @param count badge的数值
     * @param icon 应用程序图标
     */
    public static void setBadgeCount(Context context, int count,int icon) {
        if (count <= 0) {
            count = 0;
        } else {
            count = Math.max(0, Math.min(count, 99));
        }
        //根据手机生产厂商设置Badge
        if (Build.MANUFACTURER.equalsIgnoreCase("xiaomi")) {
            //小米
            setBadgeOfMIUI(context, count,icon);
        } else if (Build.MANUFACTURER.equalsIgnoreCase("sony")) {
            //索尼
            setBadgeOfSony(context, count);
        } else if (Build.MANUFACTURER.toLowerCase().contains("samsung") || Build.MANUFACTURER.toLowerCase().contains("lg")) {
            // 三星或LG
            setBadgeOfSumsung(context, count);
        } else if (Build.MANUFACTURER.toLowerCase().contains("htc")) {
            // HTC
            setBadgeOfHTC(context, count);
        } else if (Build.MANUFACTURER.toLowerCase().contains("nova")) {
            //华为
            setBadgeOfNova(context, count);
        } else {
            Toast.makeText(context, "Not Found Support Launcher" + Build.MANUFACTURER.toLowerCase(), Toast.LENGTH_LONG).show();
        }
    }

    /**
     * @FunctionName: setBadgeOfMIUI
     * @description: 设置MIUI智能设备应用程序图标的的Badge
     * @author:  Mr.Lee
     * @param context 上下文
     * @param count   badge的数值
     * @param icon    应用程序图标
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private static void setBadgeOfMIUI(Context context, int count, int icon) {
        LogUtil.d("启动器: MIUI",true);
        NotificationManager mNotificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        Notification.Builder builder = new Notification.Builder(context)
                .setContentTitle("title").setContentText("text").setSmallIcon(icon);
        Notification notification = builder.build();
        try {
            Field field = notification.getClass().getDeclaredField("extraNotification");
            Object extraNotification = field.get(notification);
            Method method = extraNotification.getClass().getDeclaredMethod("setMessageCount", int.class);
            method.invoke(extraNotification, count);
        } catch (Exception e) {
            e.printStackTrace();
        }
        mNotificationManager.notify(0, notification);
    }

    /**
     * @FunctionName: setBadgeOfSony
     * @description: 设置索尼智能设备应用程序图标的Badge
     * <br>需添加权限：<uses-permission android:name="com.sonyericsson.home.permission.BROADCAST_BADGE" /></br>
     * @author:  Mr.Lee
     * @param context context
     * @param count   count
     */
    private static void setBadgeOfSony(Context context, int count) {
        String launcherClassName = AppUtils.getLauncherClassName(context);
        if (launcherClassName == null) {
            return;
        }
        boolean isShow = true;
        if (count == 0) {
            isShow = false;
        }
        Intent localIntent = new Intent();
        localIntent.setAction("com.sonyericsson.home.action.UPDATE_BADGE");
        //是否显示
        localIntent.putExtra("com.sonyericsson.home.intent.extra.badge.SHOW_MESSAGE", isShow);
        //启动页
        localIntent.putExtra("com.sonyericsson.home.intent.extra.badge.ACTIVITY_NAME", launcherClassName);
        //数字
        localIntent.putExtra("com.sonyericsson.home.intent.extra.badge.MESSAGE", String.valueOf(count));
        //包名
        localIntent.putExtra("com.sonyericsson.home.intent.extra.badge.PACKAGE_NAME", context.getPackageName());
        context.sendBroadcast(localIntent);
    }

    /**
     * @FunctionName: setBadgeOfSumsung
     * @description: 设置三星或LB智能设备的Badge
     * @author:  Mr.Lee
     * @param context context 上下文
     * @param count   count badge数值
     */
    private static void setBadgeOfSumsung(Context context, int count) {
        // 获取你当前的应用
        String launcherClassName = AppUtils.getLauncherClassName(context);
        if (launcherClassName == null) {
            return;
        }
        Intent intent = new Intent("android.intent.action.BADGE_COUNT_UPDATE");
        intent.putExtra("badge_count", count);
        intent.putExtra("badge_count_package_name", context.getPackageName());
        intent.putExtra("badge_count_class_name", launcherClassName);
        context.sendBroadcast(intent);
    }

    /**
     * @FunctionName: setBadgeOfHTC
     * @description: 设置HTC智能设备的Badge
     * @author:  Mr.Lee
     * @param context context 上下文
     * @param count   count badge数值
     */
    private static void setBadgeOfHTC(Context context, int count) {
        Intent intentNotification = new Intent("com.htc.launcher.action.SET_NOTIFICATION");
        ComponentName localComponentName = new ComponentName(context.getPackageName(),
                AppUtils.getLauncherClassName(context));
        intentNotification.putExtra("com.htc.launcher.extra.COMPONENT", localComponentName.flattenToShortString());
        intentNotification.putExtra("com.htc.launcher.extra.COUNT", count);
        context.sendBroadcast(intentNotification);

        Intent intentShortcut = new Intent("com.htc.launcher.action.UPDATE_SHORTCUT");
        intentShortcut.putExtra("packagename", context.getPackageName());
        intentShortcut.putExtra("count", count);
        context.sendBroadcast(intentShortcut);
    }

    /**
     * @FunctionName: setBadgeOfNova
     * @description: 设置Nova(华为)智能设备的Badge
     * @author:  Mr.Lee
     * @param context context 上下文
     * @param count   count badge数值
     */
    private static void setBadgeOfNova(Context context, int count) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("tag", context.getPackageName() + "/" + AppUtils.getLauncherClassName(context));
        contentValues.put("count", count);
        context.getContentResolver().insert(Uri.parse("content://com.teslacoilsw.notifier/unread_count"),
                contentValues);
    }

    /**
     * @FunctionName: setBadgeOfMadMode
     * @description: 以疯狂模式设置角标
     * @author:  Mr.Lee
     * @param context 上下文
     * @param count 角标badge值
     * @param packageName 包名
     * @param className 类名
     */
    public static void setBadgeOfMadMode(Context context, int count, String packageName, String className) {
        Intent intent = new Intent("android.intent.action.BADGE_COUNT_UPDATE");
        intent.putExtra("badge_count", count);
        intent.putExtra("badge_count_package_name", packageName);
        intent.putExtra("badge_count_class_name", className);
        context.sendBroadcast(intent);
    }

    /**
     * @FunctionName: resetBadgeCount
     * @description: 重置Badge
     * @author:  Mr.Lee
     * @param context 上下文
     * @param icon    图标
     */
    public static void resetBadgeCount(Context context,int icon) {
        setBadgeCount(context, 0,icon);
    }
}