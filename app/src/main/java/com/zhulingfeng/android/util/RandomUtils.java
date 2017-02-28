package com.zhulingfeng.android.util;

import java.util.Random;


/**
 * @ClassName: RandomUtils
 * @description:
 * @author:  Mr.Lee
 */
@SuppressWarnings({"unused"})
public class RandomUtils {
    /**
     * @FieldName: NUMBERS_AND_LETTERS
     * @description: 字母和数字
     */
    public static final String NUMBERS_AND_LETTERS = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    /**
     * @FieldName: NUMBERS
     * @description: 数字
     */
    public static final String NUMBERS  = "0123456789";
    /**
     * @FieldName: 大写字母和小写字母
     * @description: LETTERS
     */
    public static final String LETTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    /**
     * @FieldName: CAPITAL_LETTERS
     * @description: 大写字母
     */
    public static final String CAPITAL_LETTERS     = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    /**
     * @FieldName: LOWER_CASE_LETTERS
     * @description: 小写字母
     */
    public static final String LOWER_CASE_LETTERS  = "abcdefghijklmnopqrstuvwxyz";

    /**
     * @MethodName: RandomUtils
     * @description: 构造方法
     * @author:  Mr.Lee
     */
    private RandomUtils() {
        throw new AssertionError();
    }

    /**
     * @MethodName: getRandomNumbersAndLetters
     * @description: 取固定长度的字符串，该字符串由大、小写字母和数字组成
     * @author:  Mr.Lee
     * @param length  length
     * @return  String
     */
    public static String getRandomNumbersAndLetters(int length) {
        return getRandom(NUMBERS_AND_LETTERS, length);
    }
     
    /**
     * @MethodName: getRandomNumbers
     * @description: 取固定长度的随机字符串，由数字组成
     * @author:  Mr.Lee
     * @param length  length
     * @return  String
     */
    public static String getRandomNumbers(int length) {
        return getRandom(NUMBERS, length);
    }

    /**
     * @MethodName: getRandomLetters
     * @description: 取固定长度的随机字符串，由大小写字母组成
     * @author:  Mr.Lee
     * @param length  length
     * @return  String
     */
    public static String getRandomLetters(int length) {
        return getRandom(LETTERS, length);
    }

    /**
     * @MethodName: getRandomCapitalLetters
     * @description: 取固定长度的随机字符串，由大写字母组成
     * @author:  Mr.Lee
     * @param length  length
     * @return  String
     */
    public static String getRandomCapitalLetters(int length) {
        return getRandom(CAPITAL_LETTERS, length);
    }

    /**
     * @MethodName:
     * @description: 取固定长度的随机字符串，由小写字母组成
     * @author:  Mr.Lee
     * @param length  length
     * @return  String
     */
    public static String getRandomLowerCaseLetters(int length) {
        return getRandom(LOWER_CASE_LETTERS, length);
    }

    /**
     * @MethodName: getRandom
     * @description: 从资源文件中获取指定长度的随机字符串，来源于source中的资源字符
     * @author:  Mr.Lee
     * @param source  source
     * @param length  length
     * @return
     */
    public static String getRandom(String source, int length) {
        return StringUtils.isEmpty(source) ? null : getRandom(source.toCharArray(), length);
    }

    /**
     * @MethodName: getRandom
     * @description: 取固定长度的随机字符串，来源于sorcechar中的字符
     * @author:  Mr.Lee
     * @param sourceChar
     * @param length
     * @return
     */
    public static String getRandom(char[] sourceChar, int length) {
        if (sourceChar == null || sourceChar.length == 0 || length < 0) {
            return null;
        }

        StringBuilder str = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            str.append(sourceChar[random.nextInt(sourceChar.length)]);
        }
        return str.toString();
    }

    /**
     * @MethodName: getRandom
     * @description: 返回一个0到max之间的随机整数
     * @author:  Mr.Lee
     * @param max  接受的数值
     * @return  返回一个随机的数值
     */
    public static int getRandom(int max) {
        return getRandom(0, max);
    }

    /**
     * @MethodName: getRandom
     * @description: 取指定min与max之间的一个随机整数
     * @author:  Mr.Lee
     * @param min  最小
     * @param max  最大
     * @return  返回一个范围的数值
     */
    public static int getRandom(int min, int max) {

        if (min > max) {
            return 0;
        }
        if (min == max) {
            return min;
        }
        return min + new Random().nextInt(max - min);
    }

    /**
     * @MethodName: shuffle
     * @description: 洗牌算法，用默认的随机源置换指定的数组
     * @author:  Mr.Lee
     * @param objArray  数组
     * @return 从新的数组
     */
    public static boolean shuffle(Object[] objArray) {
        if (objArray == null) {
            return false;
        }

        return shuffle(objArray, getRandom(objArray.length));
    }

    /**
     * @MethodName: shuffle
     * @description: 洗牌算法，随机置换指定的数组
     * @author:  Mr.Lee
     * @param objArray  数组
     * @param shuffleCount  洗的个数
     * @return  是否成功
     */
    public static boolean shuffle(Object[] objArray, int shuffleCount) {
        int length;
        if (objArray == null || shuffleCount < 0 || (length = objArray.length) < shuffleCount) {
            return false;
        }

        for (int i = 1; i <= shuffleCount; i++) {
            int random = getRandom(length - i);
            Object temp = objArray[length - i];
            objArray[length - i] = objArray[random];
            objArray[random] = temp;
        }
        return true;
    }

    /**
     * @MethodName: shuffle
     * @description: 洗牌算法，用默认的随机源置换指定的数组
     * @author:  Mr.Lee
     * @param intArray  数组
     * @return  洗牌之后
     */
    public static int[] shuffle(int[] intArray) {
        if (intArray == null) {
            return null;
        }
        return shuffle(intArray, getRandom(intArray.length));
    }

    /**
     * @MethodName: shuffle
     * @description: 洗牌算法，随机置换指定的数组
     * @author:  Mr.Lee
     * @param intArray   数组
     * @param shuffleCount  范围
     * @return  新的数组
     */
    public static int[] shuffle(int[] intArray, int shuffleCount) {
        int length;
        if (intArray == null || shuffleCount < 0 || (length = intArray.length) < shuffleCount) {
            return null;
        }

        int[] out = new int[shuffleCount];
        for (int i = 1; i <= shuffleCount; i++) {
            int random = getRandom(length - i);
            out[i - 1] = intArray[random];
            int temp = intArray[length - i];
            intArray[length - i] = intArray[random];
            intArray[random] = temp;
        }
        return out;
    }
}
