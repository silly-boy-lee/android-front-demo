package com.zhulingfeng.android.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * @ClassName: DensityUtil
 * @description:
 * @author:  Mr.Lee
 */
@SuppressWarnings({"unused"})
public class DensityUtil {
	/**
	 * @FieldName: deviceWidthHeight
	 * @description: 设备宽高数组
	 */
	private static int[] deviceWidthHeight = new int[2];

	/**
	 * @MethodName: getDeviceInfo
	 * @description: 取设备信息
	 * @author:  Mr.Lee
	 */
	public static int[] getDeviceInfo(Context context) {
		if ((deviceWidthHeight[0] == 0) && (deviceWidthHeight[1] == 0)) {
			DisplayMetrics metrics = new DisplayMetrics();
			((Activity) context).getWindowManager().getDefaultDisplay()
					.getMetrics(metrics);
			deviceWidthHeight[0] = metrics.widthPixels;
			deviceWidthHeight[1] = metrics.heightPixels;
		}
		return deviceWidthHeight;
	}

	/**
	 * @MethodName:
	 * @description:
	 * @author:  Mr.Lee
	 * @param context 上下文
	 * @param dpValue dp数值
	 * @return dp to  px
	 */
	public static int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * @MethodName: getScreenSize
	 * @description: 获取屏幕尺寸
	 * @author:  Mr.Lee
	 * @param context
	 * @return
	 */
	@SuppressWarnings("deprecation")
	@TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
	public static Point getScreenSize(Context context){
		WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = windowManager.getDefaultDisplay();
		if(Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR2){
			return new Point(display.getWidth(), display.getHeight());
		}else{
			Point point = new Point();
			display.getSize(point);
			return point;
		}
	}

	/**
	 * @MethodName: px2dip
	 * @description:
	 * @author:  Mr.Lee
	 * @param context    上下文
	 * @param pxValue  px的数值
	 * @return  px to dp
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);

	}
}
