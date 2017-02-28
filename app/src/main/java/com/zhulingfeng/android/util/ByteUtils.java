package com.zhulingfeng.android.util;

import java.io.FileOutputStream;
import java.math.BigInteger;


/**
 * @ClassName: ByteUtils
 * @description: 字节工具类
 * @author:  Mr.Lee
 */
@SuppressWarnings({"unused"})
public class ByteUtils {

	/**
	 * @FunctionName: byteJiaMi
	 * @description: 位移加密
	 * @author:  Mr.Lee
	 * @param bytes 待处理字节数组
	 */
	public static void byteJiaMi(byte[] bytes){
		for (int w = 0; w < bytes.length; w++){
			int a = bytes[w];
			a = ~a;
			bytes[w] = (byte)a;
		}
	}
	
	/**
	 * @FunctionName: cutOut
	 * @description: 从bytes上截取一段
	 * @author:  Mr.Lee
	 * @param bytes 母体
	 * @param off 起始
	 * @param length 个数
	 * @return byte[]
	 */
	public static byte[] cutOut(byte[] bytes, int off, int length){
		byte[] bytess = new byte[length];
		System.arraycopy(bytes, off, bytess, 0, length);
		return bytess;
	}
	
	/**
	 * @FunctionName:
	 * @description: 将字节转换为二进制字符串
	 * @author:  Mr.Lee
	 * @param bytes 字节数组
	 * @return 二进制字符串
	 */
	public static String byteToBit(byte... bytes){
		StringBuffer sb = new StringBuffer();
		int z, len;
		String str;
		for(int w = 0; w < bytes.length ; w++){
			z = bytes[w]; 
			z |= 256;
			str = Integer.toBinaryString(z);
			len = str.length(); 
			sb.append(str.substring(len-8, len));
		}
		return sb.toString();
	}


	/**
	 * @FunctionName: getHex
	 * @description: 字节数组转换成16进制字符串
	 * @author:  Mr.Lee
	 * @param raw
	 * @return
	 */
	public static String getHex(byte [] raw ) {
		String HEXES = "0123456789ABCDEF";
	    if ( raw == null ) {
	      return null;
	    }
	    final StringBuilder hex = new StringBuilder( 2 * raw.length );
	    for ( final byte b : raw ) {
	      hex.append(HEXES.charAt((b & 0xF0) >> 4))
	         .append(HEXES.charAt((b & 0x0F)));
	    }
	    return hex.toString();
	}

	/**
	 * @FunctionName: valueOf
	 * @description: 将一个short转换成字节数组
	 * @author:  Mr.Lee
	 * @param sh short
	 * @return 字节数组
	 */
	public static byte[] valueOf(short sh){
		byte[] shortBuf = new byte[2];
		for(int i=0;i<2;i++) {
			int offset = (shortBuf.length - 1 -i)*8;
			shortBuf[i] = (byte)((sh>>>offset)&0xff);
		}
		return shortBuf;
	}

	/**
	 * @FunctionName: valueOf
	 * @description: 将一个int转换成字节数组
	 * @author:  Mr.Lee
	 * @param in int
	 * @return 字节数组
	 */
	public static byte[] valueOf(int in){
		byte[] b = new byte[4];
	    for (int i = 0; i < 4; i++) {
	    	int offset = (b.length - 1 - i) * 8;
	    	b[i] = (byte) ((in >>> offset) & 0xFF);
	    }
	    return b;
	}

	/**
	 * @FunctionName: toSaveHexString
	 * @description: 将字符串装换成2进制，并且写入本地
	 * @author:  Mr.Lee
	 * @param s
	 * @param path
	 * @return
	 */
	public static boolean toSaveHexString(String s,String path) {
		BigInteger key = new BigInteger(s, 16);
		byte[] newKey = subBytes(key.toByteArray(), 1, 16);//需要截取会多出00
		try {
			FileOutputStream outputStream = new FileOutputStream(path);
			outputStream.write(newKey);
			outputStream.close();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * @FunctionName: subBytes
	 * @description: 从一个byte[]数组中截取一部分
	 * @author:  Mr.Lee
	 * @param src
	 * @param begin
	 * @param count
	 * @return
	 */
	public static byte[] subBytes ( byte[] src, int begin, int count){
		byte[] bs = new byte[count];
		for (int i = begin; i < begin + count; i++) {
			bs[i - begin] = src[i];
		}
		return bs;
	}
}
