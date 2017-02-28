package com.zhulingfeng.android.util;


/**
 * @ClassName: SystemUtils
 * @description: 系统工具箱
 * @author:  Mr.Lee
 */
@SuppressWarnings({"unused"})
public class SystemUtils {

    /**
     * @FieldName: DEFAULT_THREAD_POOL_SIZE
     * @description: 系统默认的线程池大小
     */
    public static final int DEFAULT_THREAD_POOL_SIZE = getDefaultThreadPoolSize();

    /**
     * @MethodName: SystemUtils
     * @description: 构造方法
     * @author:  Mr.Lee
     */
    private SystemUtils() {
        throw new AssertionError();
    }

    /**
     * @MethodName: getDefaultThreadPoolSize
     * @description: 获取线程池的个数
     * @author:  Mr.Lee
     * @return int 线程池的个数
     */
    public static int getDefaultThreadPoolSize() {
        return getDefaultThreadPoolSize(8);
    }

    /**
     * @MethodName: getDefaultThreadPoolSize
     * @description: 返回默认推荐的线程池个数
     * @author:  Mr.Lee
     * @param max  最大线程数
     * @return if 2 * availableProcessors + 1 less than max, return it, else return max;
     */
    public static int getDefaultThreadPoolSize(int max) {
        int availableProcessors = 2 * Runtime.getRuntime().availableProcessors() + 1;
        return availableProcessors > max ? max : availableProcessors;
    }
}
