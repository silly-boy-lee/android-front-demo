package com.zhulingfeng.android.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * 将字符串进行DES加密解密
 * 
 * @version	0.1	20091106
 * @author	史先方
 */
/**
 * @ClassName: DES
 * @description:
 * @author:  Mr.Lee
 */
@SuppressWarnings({"unused"})
public class DES {
	/**
	 * @FieldName: KEY
	 * @description: 加密KEY
	 */
	private static final byte[] KEY = "7;9Ku7;:84VG*B78".getBytes();
	/**
	 * @FieldName: ALGORITHM
	 * @description: 算法
	 */
	private static final String ALGORITHM = "DES";
	/**
	 * @FieldName: IV
	 * @description: IV
	 */
	private static final byte[] IV = "sHjrydLq".getBytes();
	/**
	 * @FieldName: TRANSFORMATION
	 * @description: TRANSFORMATION
	 */
	private static final String TRANSFORMATION = "DES/CBC/PKCS5Padding";
	
	private int code = 0;
	
	public DES() {
	}

	/**
	 * @MethodName: DES
	 * @description: 构造函数
	 * @author:  Mr.Lee
	 * @param code 加密方式：0-“ISO-8859-1”编码，1-base64编码，其它-默认编码（utf-8）
	 */
	public DES(int code) {
		this.code = code;
	}

	/**
	 * @MethodName: encrypt
	 * @description: 将字符串进行DES加密
	 * @author:  Mr.Lee
	 * @param source 未加密源字符串
	 * @return 加密后字符串
	 */
	public String encrypt(String source)  {
		byte[] retByte = null;
		// Create SecretKey object
		DESKeySpec dks = null;
		try {
			dks = new DESKeySpec(KEY);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
			SecretKey securekey = keyFactory.generateSecret(dks);

			// Create IvParameterSpec object with initialization vector
			IvParameterSpec spec = new IvParameterSpec(IV);

			// Create Cipter object
			Cipher cipher = Cipher.getInstance(TRANSFORMATION);

			// Initialize Cipher object
			cipher.init(Cipher.ENCRYPT_MODE, securekey, spec);

			// Decrypting data
			retByte = cipher.doFinal(source.getBytes());

			String result = "";
			if (code == 0) {
				result = new String(retByte, "ISO-8859-1");
			} else if (code == 1) {
				result = Base64.encodeToString(retByte,false);
			} else {
				result = new String(retByte);
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	/**
	 * @MethodName: decrypt
	 * @description: 将DES加密的字符串解密
	 * @author:  Mr.Lee
	 * @param encrypted 加密过的字符串
	 * @return 未加密源字符串
	 */
	public String decrypt(String encrypted) {
		byte[] retByte = null;

		// Create SecretKey object
		DESKeySpec dks = null;
		try {
			dks = new DESKeySpec(KEY);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
			SecretKey securekey = keyFactory.generateSecret(dks);

			// Create IvParameterSpec object with initialization vector
			IvParameterSpec spec = new IvParameterSpec(IV);

			// Create Cipter object
			Cipher cipher = Cipher.getInstance(TRANSFORMATION);

			// Initialize Cipher object
			cipher.init(Cipher.DECRYPT_MODE, securekey, spec);

			if (code == 0) {
				retByte = encrypted.getBytes("ISO-8859-1");
			} else if (code == 1) {
				retByte = Base64.decode(encrypted);
			} else {
				retByte = encrypted.getBytes();
			}

			// Decrypting data
			retByte = cipher.doFinal(retByte);
			return new String(retByte, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
	
}
