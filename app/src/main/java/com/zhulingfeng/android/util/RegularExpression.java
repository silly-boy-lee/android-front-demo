package com.zhulingfeng.android.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName: RegularExpression
 * @description: 正则验证相关操作的类
 * @author:  Mr.Lee
 */
@SuppressWarnings({"unused"})
public class RegularExpression {

	/**
	 * @FieldName: DESC_NORMALTEXT
	 * @description: 普通文本描述
	 */
	public final static String DESC_NORMALTEXT = "不能包含特殊字符，且不能为空.";

	/**
	 * @MethodName: canMatch
	 * @description: 正则验证
	 * @author:  Mr.Lee
	 * @param toCheckStr 待验证的字符串
	 * @param patternStr 验证格式字符串
	 * @return 是否通过验证
	 */
	public static boolean canMatch(String toCheckStr, String patternStr) {
		Pattern pattern = Pattern.compile(patternStr);
		Matcher matcher = pattern.matcher(toCheckStr);
		if(!matcher.matches()) {
			return false;
		}
		return true;
	}
	 
	/**
	 * @MethodName: isNumeric
	 * @description: 验证是否为整数
	 * @author:  Mr.Lee
	 * @param toCheckStr 待验证的字符串
	 * @return 是否通过验证
	 */
	public static boolean isNumeric(String toCheckStr) {
		return canMatch(toCheckStr, "[0-9][0-9]*");
	}

	/**
	 * @MethodName: isNumOrChar
	 * @description: 验证是否为整数或字母.
	 * @author:  Mr.Lee
	 */
	public static boolean isNumOrChar(String toCheckStr) {
		return canMatch(toCheckStr, "[a-zA-Z0-9][a-zA-Z0-9]*");
	}

	/**
	 * @MethodName: isIDCard
	 * @description: 验证是否为身份证号
	 * @author:  Mr.Lee
	 * @param toCheckStr 待验证的字符串
	 * @return 是否通过验证
	 */
	public static boolean isIDCard(String toCheckStr) {
		String isIDCard1 = "^(([0-9]{14}[x0-9]{1})|([0-9]{17}[x0-9]{1}))$";
		return canMatch(toCheckStr, isIDCard1);
	}

	/**
	 * @MethodName: isTeleNo
	 * @description: 验证是否为电话号码
	 * @author:  Mr.Lee
	 * @param toCheckStr 待验证的字符串
	 * @return 是否通过验证
	 */
	public static boolean isTeleNo(String toCheckStr) {
		String patternStr = "(^[0-9]{3,4}\\-[0-9]{3,8}$)|(^[0-9]{3,8}$)|(^\\([0-9]{3,4}\\)[0-9]{3,8}$)|(^0{0,1}13[0-9]{9}$)";
		return canMatch(toCheckStr, patternStr);
	}

	/**
	 * @MethodName: isUserName
	 * @description: 验证是否为合法的用户名. 用户名只能由汉字、数字、字母、下划线组成，且不能为空.
	 * @author:  Mr.Lee
	 * @param toCheckStr 待验证的字符串
	 * @return 是否通过验证
	 */
	public static boolean isUserName(String toCheckStr) {
		String patternStr = "^[a-zA-Z0-9_\u4e00-\u9fa5]+$";
		return canMatch(toCheckStr, patternStr);
	}

	/**
	 * @MethodName: isCH
	 * @description: 验证是否为汉字.
	 * @author:  Mr.Lee
	 * @param toCheckStr 待验证的字符串
	 * @return 是否通过验证
	 */
	public static boolean isCH(String toCheckStr) {
		String patternStr = "^[\u4e00-\u9fa5]+$";
		return canMatch(toCheckStr, patternStr);
	}

	/**
	 * @MethodName: isNormalText
	 * @description: 验证是否为正常的文本内容. 内容只能为：汉字、数字、字母、下划线、 中文标点符号 英文标点符号，且不能为空.
	 * @author:  Mr.Lee
	 * @param toCheckStr 待验证的字符串
	 * @return 是否通过验证
	 */
	public static boolean isNormalText(String toCheckStr) {
		String patternStr = "^[a-zA-Z0-9_\u4e00-\u9fa5" // 汉字、数字、字母、下划线
				// 中文标点符号（。 ； ， ： “ ”（ ） 、 ！ ？ 《 》）
				+ "\u3002\uff1b\uff0c\uff1a\u201c\u201d\uff08\uff09\u3001\uff01\uff1f\u300a\u300b"
				// 英文标点符号（. ; , : ' ( ) / ! ? < >）
				+ "\u002e\u003b\u002c\u003a\u0027\u0028\u0029\u002f\u0021\u003f\u003c\u003e\r\n"
				+ "]+$";
		return canMatch(toCheckStr, patternStr);
	}

	/**
	 * @MethodName:
	 * @description: 验证是否为Url的文本内容. 内容只能为：数字、字母、英文标点符号（. : / ），且不能为空.
	 * @author:  Mr.Lee
	 * @param toCheckStr 待验证的字符串
	 * @return 是否通过验证
	 */
	public static boolean isUrlText(String toCheckStr) {
		String patternStr = "^[a-zA-Z0-9" // 数字、字母
				// 英文标点符号（. : /）
				+ "\u002e\u003a\u002f"
				+ "]+$";
		return canMatch(toCheckStr, patternStr);
	}

	/**
	 * @MethodName: checkRoomNumber
	 * @description: 判断房间号是否符合规范：例如102,1202... 先判断3位或者4位的数字
	 * @author:  Mr.Lee
	 * @param roomNumber  roomNumber
	 * @return   boolean
	 */
	public static boolean checkRoomNumber(String roomNumber) {
		String regex = "^\\d{3,4}$";
		return Pattern.matches(regex, roomNumber);
	}

	/**
	 * @MethodName: hideIdentityID
	 * @description: 将身份证后六位隐藏,不显示
	 * @author:  Mr.Lee
	 * @param identityID  identityID
	 * @return  String
	 */
	public static String hideIdentityID(String identityID) {
		if(identityID != null && identityID.length() > 6) {
			identityID = identityID.substring(0, identityID.length() - 6)
					+ "******";
		}
		return identityID;
	}

	/**
	 * @MethodName:isPostalCode
	 * @description: 是否规范的邮编
	 * @author:  Mr.Lee
	 * @param toCheckStr  toCheckStr
	 * @return  是否规范的邮编
	 * @param toCheckStr  toCheckStr
	 * @return  是否规范的邮编
	 */
	public static boolean isPostalCode(String toCheckStr) {
		return isNumeric(toCheckStr) && toCheckStr.length() == 6;
	}

	/**
	 * @MethodName: isEmail
	 * @description: 邮箱验证
	 * @author:  Mr.Lee
	 * @param toCheckStr  toCheckStr
	 * @return  邮箱验证
	 */
	public static boolean isEmail(String toCheckStr) {
		String patternStr = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
		return canMatch(toCheckStr, patternStr);
	}

	/**
	 * @MethodName:
	 * @description: 办公电话验证 格式：区号(可选)-主机号-分机号(可选)
	 * @author:  Mr.Lee
	 * @param toCheckStr  toCheckStr
	 * @return   办公电话验证 格式：区号(可选)-主机号-分机号(可选)
	 */
	public static boolean isWorkPhone(String toCheckStr) {
		String patternStr = "(^[0-9]{3,4}-[0-9]{7,8}-[0-9]{3,4}$)|(^[0-9]{3,4}-[0-9]{7,8}$)|(^[0-9]{7,8}-[0-9]{3,4}$)|(^[0-9]{7,8}$)";
		return canMatch(toCheckStr, patternStr);
	}

	/**
	 * @MethodName: isPhoneNumber
	 * @description:常用固定电话验证 格式：区号(可选)-主机号
	 * @author:  Mr.Lee
	 * @param toCheckStr  toCheckStr
	 * @return   常用固定电话验证 格式：区号(可选)-主机号
	 */
	public static boolean isPhoneNumber(String toCheckStr) {
		String patternStr = "(^[0-9]{3,4}-[0-9]{7,8}$)|(^[0-9]{7,8}$)";
		return canMatch(toCheckStr, patternStr);
	}

	/**
	 * @MethodName: isTelephone
	 * @description: 是否为规范的手机电话号码 ，以13/15/18开头
	 * @author:  Mr.Lee
	 * @param toCheckStr  toCheckStr
	 * @return  是否为规范的手机电话号码 ，以13/15/18开头
	 */
	public static boolean isTelephone(String toCheckStr) {
		String patternStr = "(^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$)";
		return canMatch(toCheckStr, patternStr);
	}

	/**
	 * @MethodName: isDateyyMMddHHmmss
	 * @description: 日期格式是否为yyyy-mm-dd hh:mm:ss
	 * @author:  Mr.Lee
	 */
	public static boolean isDateyyMMddHHmmss(String toCheckStr) {
		return canMatch(toCheckStr, "([1-2])([0-9]{3})([0-1])([0-9])([0-3])([0-9])([0-2])([0-9])([0-5])([0-9])([0-5])([0-9])");
	}
}
