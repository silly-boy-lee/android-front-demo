package com.zhulingfeng.android.util;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;


/**
 * @ClassName: StreamUtils
 * @description: 流转换成字符串
 * @author:  Mr.Lee
 */
@SuppressWarnings({"unused"})
public class StreamUtils {

    /**
     * @MethodName: 将资源输入流中的数据转换成字符串
     * @description: streamToString
     * @author:  Mr.Lee
     * @param inputStream inputStream
     * @return 字符串转换之后的
     */
    public static String streamToString(InputStream inputStream) {
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = inputStream.read(buffer)) != -1) {
                out.write(buffer, 0, len);
                out.flush();
            }

            String result = out.toString();
            out.close();
            inputStream.close();
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
