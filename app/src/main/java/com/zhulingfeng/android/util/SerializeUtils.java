package com.zhulingfeng.android.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @ClassName: SerializeUtils
 * @description: 对象序列化和反序列化工具
 * @author:  Mr.Lee
 */
@SuppressWarnings({"unused"})
public class SerializeUtils {

    /**
     * @MethodName: SerializeUtils
     * @description: 构造方法
     * @author:  Mr.Lee
     */
    private SerializeUtils() {
        throw new AssertionError();
    }

    /**
     * @MethodName: deserialization
     * @description: 从文件中反序列化出对象
     * @author:  Mr.Lee
     * @param filePath file path
     * @return 反序列化出来的对象
     * @throws RuntimeException 方法执行出错时招聘运行时异常
     */
    public static Object deserialization(String filePath) {
        ObjectInputStream in = null;
        try {
            in = new ObjectInputStream(new FileInputStream(filePath));
            Object o = in.readObject();
            in.close();
            return o;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("FileNotFoundException occurred. ", e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("ClassNotFoundException occurred. ", e);
        } catch (IOException e) {
            throw new RuntimeException("IOException occurred. ", e);
        } finally {
            IOUtils.close(in);
        }
    }

    /**
     * @MethodName: serialization
     * @description: 序列化对象到文件
     * @author:  Mr.Lee
     * @param filePath 文件路径
     * @param obj 待序列化对象
     * @throws RuntimeException 方法执行出错时抛出运行时异常
     */
    public static void serialization(String filePath, Object obj) {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream(filePath));
            out.writeObject(obj);
            out.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException("FileNotFoundException occurred. ", e);
        } catch (IOException e) {
            throw new RuntimeException("IOException occurred. ", e);
        } finally {
            IOUtils.close(out);
        }
    }

}
