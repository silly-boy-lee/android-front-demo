package com.zhulingfeng.android.util;

import android.util.Log;

import com.zhulingfeng.android.constant.SysConst;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * @ClassName: AESUtil
 * @description: AES加密解密工具类
 * @author:  Mr.Lee
 */
@SuppressWarnings({"unused"})
public class AESUtil {

    /**
     * @FieldName: TAG
     * @description: 日志标记
     */
    public static final String TAG = "AESUtil";

    /**
     * @FieldName: KEY_LENGTH
     * @description: 密钥长度
     */
    public static final int KEY_LENGTH = 16;

    /**
     * @FieldName: AES_KEY
     * @description: AES加密解密密钥
     */
    public static final String AES_KEY = "aaaaaaaaaaaaaaaa";

    /**
     * @FunctionName: encrypt
     * @description: AES加密
     * <br>1:AES对sStr进行AES加密，得到已加密的encrypted字节数组  2.对已加密的encrypted字节数组进行Base64二次加密 3.返回二次加密后的密文</br>
     * @author:  Mr.Lee
     * @param sStr 加密字符串
     * @param sKey 密钥
     * @return 返回经Base64二次加密后的密文
     */
    public static String encrypt(String sStr,String sKey) throws Exception{
        //判断密钥是否为null
        if (null == sKey){
            Log.e(TAG,"Key为空null");
            return null;
        }
        //判断密码长度是否为16位
        if (sKey.length() != KEY_LENGTH){
            Log.e(TAG,"Key长度不是"+KEY_LENGTH+"位");
            return null;
        }
        byte[] raw = sKey.getBytes(SysConst.DEFAULT_ENCODE);
        SecretKeySpec sKeySpec = new SecretKeySpec(raw,"AES");
        //"算法/模式/补码方式"
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        //使用CBC模式，需要一个向量iv，可增加加密算法的强度
        IvParameterSpec iv = new IvParameterSpec("1234567890123456".getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, sKeySpec, iv);
        byte[] encrypted = cipher.doFinal(sStr.getBytes());
        //此处使用BASE64做转码功能，同时能起到2次加密的作用。
        return Base64_2.encode(encrypted);
    }

    /**
     * @FunctionName: decrypt
     * @description: AES解密
     * <br>1:对sStr进行Base64解密，得到第一次解密的密文字节数组encrypted1  2.对密文字节数组encrypted1进行AES二次解密 3.返回二次解密后的密文</br>
     * @author:  Mr.Lee
     * @param sStr 解密字符串
     * @param sKey 密钥
     * @return 解密后的字符串
     */
    public static String decrypt(String sStr,String sKey) throws Exception{
        try{
            //判断密钥是否为null
            if (null == sKey){
                Log.e(TAG,"Key为空null");
                return null;
            }
            //判断密码长度是否为16位
            if (sKey.length() != KEY_LENGTH){
                Log.e(TAG,"Key长度不是"+KEY_LENGTH+"位");
                return null;
            }
            byte[] raw = sKey.getBytes("ASCII");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec("1234567890123456".getBytes());
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] encrypted1 = Base64_2.decode(sStr);//先用base64解密
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original);
                return originalString;
            } catch (Exception e) {
                System.out.println(e.toString());
                return null;
            }
        }catch (Exception e){
            System.out.println(e.toString());
            return null;
        }
    }

}
