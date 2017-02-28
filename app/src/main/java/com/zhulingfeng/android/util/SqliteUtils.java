package com.zhulingfeng.android.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.zhulingfeng.android.util.db.DbHelper;

/**
 * @ClassName: SqliteUtils
 * @description: 工具类
 * @author:  Mr.Lee
 */
@SuppressWarnings({"unused"})
public class SqliteUtils {

    /**
     * @FieldName: instance
     * @description: 实例对象
     */
    private static volatile SqliteUtils instance;
    /**
     * @FieldName: dbHelper
     * @description: 数据库辅助工具类
     */
    private DbHelper dbHelper;
    /**
     * @FieldName: db
     * @description: SQLiteDatabase对象
     */
    private SQLiteDatabase db;

    /**
     * @MethodName: SqliteUtils
     * @description: 构造方法
     * @author:  Mr.Lee
     * @param context 上下文
     */
    private SqliteUtils(Context context) {
        dbHelper = new DbHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    /**
     * @MethodName: getInstance
     * @description: 获取SqliteUtils实例对象
     * @author:  Mr.Lee
     * @param context 上下文
     * @return SqliteUtils
     */
    public static SqliteUtils getInstance(Context context) {
        if (instance == null) {
            synchronized (SqliteUtils.class) {
                if (instance == null) {
                    instance = new SqliteUtils(context);
                }
            }
        }
        return instance;
    }

    /**
     * @MethodName: getDb
     * @description: 获取SQLiteDatabase数据库对象
     * @author:  Mr.Lee
     * @return  返回SQLiteDatabase数据库对象
     */
    public SQLiteDatabase getDb() {
        return db;
    }
}
