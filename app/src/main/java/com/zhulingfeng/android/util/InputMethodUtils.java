package com.zhulingfeng.android.util;

import android.app.Activity;
import android.content.Context;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * @ClassName: InputMethodUtils
 * @description: 软键盘
 * @author:  Mr.Lee
 */
@SuppressWarnings({"unused"})
public class InputMethodUtils {
    /**
     * @MethodName: openSoftKeyboard
     * @description: 为给定的编辑器开启软键盘
     * @author:  Mr.Lee
     * @param editText 给定的编辑器
     */
    public static void openSoftKeyboard(Context context, EditText editText) {
        editText.requestFocus();
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.showSoftInput(editText,InputMethodManager.SHOW_IMPLICIT);
        ViewUtils.setEditTextSelectionToEnd(editText);
    }

    /**
     * @MethodName: closeSoftKeyboard
     * @description: 关闭软键盘
     * @author:  Mr.Lee
     * @param activity
     */
    public static void closeSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        //如果软键盘已经开启
        if (inputMethodManager.isActive()) {
            inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(),InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    /**
     * @MethodName: toggleSoftKeyboardState
     * @description:  切换软键盘的状态
     * @author:  Mr.Lee
     * @param context
     */
    public static void toggleSoftKeyboardState(Context context) {
        ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).toggleSoftInput(
                InputMethodManager.SHOW_IMPLICIT,
                InputMethodManager.HIDE_NOT_ALWAYS);
    }

    /**
     * @MethodName: keyBoxIsShow
     * @description: 判断隐藏软键盘是否弹出,弹出就隐藏
     * @author:  Mr.Lee
     * @param mActivity
     * @return
     */
    public boolean keyBoxIsShow(Activity mActivity) {
        if (mActivity.getWindow().getAttributes().softInputMode == WindowManager.LayoutParams.SOFT_INPUT_STATE_UNSPECIFIED) {
            //隐藏软键盘
            mActivity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
            return true;
        }
        else {
            return false;
        }
    }
}
