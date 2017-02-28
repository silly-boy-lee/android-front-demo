package com.zhulingfeng.android.util;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.view.Surface;
import android.view.Window;
import android.view.WindowManager;

/**
 * @ClassName: WindowUtils
 * @description: 窗口工具箱
 * @author:  Mr.Lee
 */
@SuppressWarnings({"unused"})
public final class WindowUtils {

    /**
     * @MethodName: WindowUtils
     * @description: 私有化构造方法，使其只能通过JNI方式被实例化此类
     * @author:  Mr.Lee
     */
    private WindowUtils() {
        throw new Error("Do not need instantiate!");
    }

    /**
     * @MethodName: getDisplayRotation
     * @description: 获取当前窗口的旋转角度
     * @author:  Mr.Lee
     * @param activity activity
     * @return  int
     */
    public static int getDisplayRotation(Activity activity) {
        switch (activity.getWindowManager().getDefaultDisplay().getRotation()) {
            case Surface.ROTATION_0:
                return 0;
            case Surface.ROTATION_90:
                return 90;
            case Surface.ROTATION_180:
                return 180;
            case Surface.ROTATION_270:
                return 270;
            default:
                return 0;
        }
    }

    /**
     * @MethodName: isLandscape
     * @description: 检测当前是否是横屏
     * @author:  Mr.Lee
     * @param context  context
     * @return  boolean
     */
    public static final boolean isLandscape(Context context) {
        return context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
    }

    /**
     * @MethodName: isPortrait
     * @description: 检测当前是否是竖屏
     * @author:  Mr.Lee
     * @param context  context
     * @return   boolean
     */
    public static final boolean isPortrait(Context context) {
        return context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
    }

    /**
     * @MethodName: dimBackground
     * @description: 调整窗口的透明度  1.0f,0.5f 变暗
     * @author:  Mr.Lee
     * @param from  from>=0&&from<=1.0f
     * @param to  to>=0&&to<=1.0f
     * @param context  当前的activity
     */
    public static void dimBackground(final float from, final float to, Activity context) {
        final Window window = context.getWindow();
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(from, to);
        valueAnimator.setDuration(500);
        valueAnimator.addUpdateListener(
                new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        WindowManager.LayoutParams params
                                = window.getAttributes();
                        params.alpha = (Float) animation.getAnimatedValue();
                        window.setAttributes(params);
                    }
                });
        valueAnimator.start();
    }
}