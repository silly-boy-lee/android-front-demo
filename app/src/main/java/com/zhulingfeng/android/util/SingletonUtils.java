package com.zhulingfeng.android.util;


/**
 * @ClassName: SingletonUtils
 * @description: 单例辅助工具类，延迟实例化
 * @author:  Mr.Lee
 */
@SuppressWarnings({"unused"})
public abstract class SingletonUtils<T> {

    /**
     * @FieldName: instance
     * @description:
     */
    private T instance;

    /**
     * @FieldName:
     * @description:
     */
    protected abstract T newInstance();

    /**
     * @MethodName: getInstance
     * @description: 获取一个单例对象
     * @author:  Mr.Lee
     */
    public final T getInstance() {
        if (instance == null) {
            synchronized (SingletonUtils.class) {
                if (instance == null) {
                    instance = newInstance();
                }
            }
        }
        return instance;
    }
}
