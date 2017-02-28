package com.zhulingfeng.android.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.telephony.TelephonyManager;
import java.io.File;

/**
 * @ClassName: PhoneUtil
 * @description: 手机组件调用工具类
 * @author: Mr.Lee
 */
@SuppressWarnings({"unused"})
public final class PhoneUtil {

    /**
     * @FieldName: lastClickTime
     * @description: 最后点击操作发生时刻
     */
    private static long lastClickTime;

    /**
     * @MethodName: PhoneUtil
     * @description: 构造方法
     * @author:  Mr.Lee
     */
    private PhoneUtil() {
        throw new Error("Do not need instantiate!");
    }

    /**
     * @MethodName: sendMessage
     * @description: 调用系统发短信界面
     * @author:  Mr.Lee
     * @param activity    Activity
     * @param phoneNumber 手机号码
     * @param smsContent  短信内容
     */
    public static void sendMessage(Context activity, String phoneNumber, String smsContent) {
        if (phoneNumber == null || phoneNumber.length() < 4) {
            return;
        }
        Uri uri = Uri.parse("smsto:" + phoneNumber);
        Intent it = new Intent(Intent.ACTION_SENDTO, uri);
        it.putExtra("sms_body", smsContent);
        it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(it);
    }

    /**
     * @MethodName: isFastDoubleClick
     * @description: 判断是否为连击
     * @author:  Mr.Lee
     * @return 如果是连续点击则返回true,否则返回false
     */
    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 500) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    /**
     * @MethodName: getMobileModel
     * @description: 获取手机型号
     * @author:  Mr.Lee
     * @param context 上下文
     * @return 手机型号
     */
    public static String getMobileModel(Context context) {
        try {
            String model = android.os.Build.MODEL; // 手机型号
            return model;
        } catch (Exception e) {
            return "未知";
        }
    }

    /**
     * @MethodName: getMobileBrand
     * @description: 获取手机品牌
     * @author:  Mr.Lee
     * @param context 上下文
     * @return String
     */
    public static String getMobileBrand(Context context) {
        try {
            String brand = android.os.Build.BRAND; // android系统版本号
            return brand;
        } catch (Exception e) {
            return "未知";
        }
    }
    
    /**
     * @MethodName: toTakePhoto
     * @description: 拍照打开照相机！
     * @author:  Mr.Lee
     * @param requestcode 返回值
     * @param activity    上下文
     * @param fileName    生成的图片文件的路径
     */
    public static void toTakePhoto(int requestcode, Activity activity, String fileName) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra("camerasensortype", 2);// 调用前置摄像头
        intent.putExtra("autofocus", true);// 自动对焦
        intent.putExtra("fullScreen", false);// 全屏
        intent.putExtra("showActionIcons", false);
        try {//创建一个当前任务id的文件然后里面存放任务的照片的和路径！这主文件的名字是用uuid到时候在用任务id去查路径！
            File file = new File(fileName);
            Uri uri = Uri.fromFile(file);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            activity.startActivityForResult(intent, requestcode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * @MethodName: toTakePicture
     * @description: 打开相册
     * @author:  Mr.Lee
     * @param requestcode 响应码
     * @param activity    上下文
     */
    public static void toTakePicture(int requestcode, Activity activity) {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,"image/*");
        activity.startActivityForResult(intent, requestcode);
    }

    /**
     * @MethodName: getContactsNameAndNumber
     * @description: 获取所有联系人的姓名和电话号码，需要READ_CONTACTS权限
     * @author:  Mr.Lee
     * @param context 上下文
     * @return Cursor。姓名：CommonDataKinds.Phone.DISPLAY_NAME；号码：CommonDataKinds.Phone.NUMBER
     */
    public static Cursor getContactsNameAndNumber(Context context) {
        return context.getContentResolver().query(
                ContactsContract.CommonDataKinds.Phone.CONTENT_URI, new String[]{
                        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER}, null, null, ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " COLLATE LOCALIZED ASC");
    }

    /**
     * @MethodName: getMobilePhoneNumber
     * @description: 获取手机号码
     * @author:  Mr.Lee
     * @param context 上下文
     * @return 手机号码，手机号码不一定能获取到
     */
    public static String getMobilePhoneNumber(Context context) {
        return ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE)).getLine1Number();
    }

}
