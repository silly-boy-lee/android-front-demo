package com.zhulingfeng.android.util;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * IO utils
 *
 * @author Vladislav Bauer
 */
/**
 * @ClassName: IOUtils
 * @description: IO工具类
 * @author:  Mr.Lee
 */
@SuppressWarnings({"unused"})
public class IOUtils {

    /**
     * @MethodName: IOUtils
     * @description: 
     * @author:  Mr.Lee
     */
    private IOUtils() {
        throw new AssertionError();
    }
     
    /**
     * @MethodName: close
     * @description: 关闭可关闭的对象资源
     * @author:  Mr.Lee
     * @param closeable closeable object
     */
    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                throw new RuntimeException("IOException occurred. ", e);
            }
        }
    }

    /**
     * @MethodName: closeQuietly
     * @description: 
     * @author:  Mr.Lee
     * @param closeable closeable object
     */
    public static void closeQuietly(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                // Ignored
            }
        }
    }

    /**
     * @MethodName: saveTextValue
     * @description: 保存文本
     * @author:  Mr.Lee
     * @param fileName  文件名字
     * @param content  内容
     * @param append  是否累加
     * @return  是否成功
     */
    public static boolean saveTextValue(String fileName, String content, boolean append) {
        try {
            File textFile = new File(fileName);
            if (!append && textFile.exists()) textFile.delete();
            FileOutputStream os = new FileOutputStream(textFile);
            os.write(content.getBytes("UTF-8"));
            os.close();
        } catch (Exception ee) {
            return false;
        }
        return true;
    }

    /**
     * @MethodName: deleteAllFile
     * @description: 删除目录下所有文件
     * @author:  Mr.Lee
     * @param Path    路径
     */
    public static void deleteAllFile(String Path) {
        // 删除目录下所有文件
        File path = new File(Path);
        File files[] = path.listFiles();
        if (files != null) {
            for (File tfi : files) {
                if (tfi.isDirectory()) {
                    System.out.println(tfi.getName());
                }
                else {
                    tfi.delete();
                }
            }
        }
    }
}
