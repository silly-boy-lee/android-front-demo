package com.zhulingfeng.android.util;

import android.content.Context;
import android.provider.Telephony;
import android.widget.Toast;
import java.util.Locale;


/**
 * @ClassName: DoubleClickExitDetector
 * @description: 双击退出识别器
 * @author:  Mr.Lee
 */
@SuppressWarnings({"unused"})
public class DoubleClickExitDetector {
	/**
	 * @FieldName:
	 * @description:
	 */
	public static String DEFAULT_HINT_MESSAGE_CHINA = "再按一次退出程序";
	/**
	 * @FieldName:
	 * @description:
	 */
	public static String DEFAULT_HINT_MESSAGE_OTHER = "Press again to exit the program";
	/**
	 * @FieldName:
	 * @description: 有效的间隔时间，单位毫秒
	 */
	private int effectiveIntervalTime;
	/**
	 * @FieldName:
	 * @description: 上次点击时间
	 */
	private long lastClickTime;
	/**
	 * @FieldName:
	 * @description:
	 */
	private String hintMessage;
	/**
	 * @FieldName:
	 * @description: 提示消息
	 */
	private Context context;

	/**
	 * @MethodName: DoubleClickExitDetector
	 * @description: 创建一个双击退出识别器
	 * @author:  Mr.Lee
	 * @param context Androdi上下文
	 * @param hintMessage 提示消息
	 * @param effectiveIntervalTime 有效间隔时间
	 */
	public DoubleClickExitDetector(Context context, String hintMessage, int effectiveIntervalTime) {
		this.context = context;
		this.hintMessage = hintMessage;
		this.effectiveIntervalTime = effectiveIntervalTime;
	}

	/**
	 * @MethodName: DoubleClickExitDetector
	 * @description: 创建一个双击退出识别器，有效间隔时间默认为2000毫秒
	 * @author:  Mr.Lee
	 * @param context Androdi上下文
	 * @param hintMessage 提示消息
	 */
	public DoubleClickExitDetector(Context context, String hintContent) {
		this(context, hintContent, 2000);
	}

	/**
	 * @MethodName: DoubleClickExitDetector
	 * @description: 创建一个双击退出识别器，中国环境下默认提示消息为“再按一次退出程序”，其它环境下默认提示消息为“Press again to exit the program”；有效间隔时间默认为2000毫秒
	 * @author:  Mr.Lee
	 * @param context Androdi上下文
	 */
	public DoubleClickExitDetector(Context context) {
		this(context, Locale.CHINA.equals(Locale.getDefault()) ? DEFAULT_HINT_MESSAGE_CHINA : DEFAULT_HINT_MESSAGE_OTHER, 2000);
	}

	/**
	 * @MethodName: click
	 * @description: 点击，你需要重写Activity的onBackPressed()方法，先调用此方法，如果返回true就执行父类的onBackPressed()方法来关闭Activity否则不执行
	 * @author:  Mr.Lee
	 * @return 当两次点击时间间隔小于有效间隔时间时就会返回true，否则返回false
	 */
	public boolean click(){
		long currentTime = System.currentTimeMillis();
		boolean result = (currentTime-lastClickTime) < effectiveIntervalTime;
		lastClickTime = currentTime;
		if(!result){
			Toast.makeText(context, hintMessage, Toast.LENGTH_SHORT).show();
		}
		return result;
	}

	/**
	 * @MethodName: setEffectiveIntervalTime
	 * @description: 设置有效间隔时间，单位毫秒
	 * @author:  Mr.Lee
	 * @param effectiveIntervalTime 效间隔时间，单位毫秒。当两次点击时间间隔小于有效间隔时间click()方法就会返回true
	 */
	public void setEffectiveIntervalTime(int effectiveIntervalTime) {
		this.effectiveIntervalTime = effectiveIntervalTime;
	}

	/**
	 * @MethodName: setHintMessage
	 * @description: 设置提示消息
	 * @author:  Mr.Lee
	 * @param hintMessage 当前点击同上次点击时间间隔大于有效间隔时间click()方法就会返回false，并且显示提示消息
	 */
	public void setHintMessage(String hintMessage) {
		this.hintMessage = hintMessage;
	}
}
