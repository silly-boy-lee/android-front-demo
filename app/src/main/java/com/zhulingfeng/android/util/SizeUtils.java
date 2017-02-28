package com.zhulingfeng.android.util;

/**
 * @ClassName: SizeUtils
 * @description: 资源空间大小常量集
 * @author:  Mr.Lee
 */
@SuppressWarnings({"unused"})
public class SizeUtils {

    /**
     * @FieldName: GB_2_BYTE
     * @description: 1GB = 1073741824 Byte
     */
    public static final long GB_2_BYTE = 1073741824;
    /**
     * @FieldName: MB_2_BYTE
     * @description: 1GB = 1048576 Byte
     */
    public static final long MB_2_BYTE = 1048576;
    /**
     * @FieldName:KB_2_BYTE
     * @description: 1GB = 1024 Byte
     */
    public static final long KB_2_BYTE = 1024;

    private SizeUtils() {
        throw new AssertionError();
    }
}
