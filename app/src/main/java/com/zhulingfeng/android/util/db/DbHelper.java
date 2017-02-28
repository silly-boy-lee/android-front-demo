package com.zhulingfeng.android.util.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.zhulingfeng.android.util.AppUtils;
import com.zhulingfeng.android.util.ToastUtils;

/**
 * @ClassName: DbHelper
 * @description: 数据库辅助工具类
 * @author:  Mr.Lee
 */
@SuppressWarnings({"unused"})
public class DbHelper extends SQLiteOpenHelper {

    /**
     * @MethodName: DbHelper
     * @description: 构造方法，初始化DbHelper
     * @author:  Mr.Lee
     * @param context 上下文
     */
    public DbHelper(Context context) {
        super(context, DbConstants.DB_NAME, null, DbConstants.DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.beginTransaction();
        try {
            /*在此处执行建表语句*/
            db.execSQL(DbConstants.CREATE_DEPT_TABLE_SQL.toString());
            db.setTransactionSuccessful();
            ToastUtils.toast(AppUtils.INSTANCE,"已经创建");
        } finally {
            db.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
