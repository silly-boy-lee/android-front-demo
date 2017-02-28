package com.zhulingfeng.android.util.db;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;

import com.zhulingfeng.android.util.FileUtils;
import com.zhulingfeng.android.util.LogUtil;

/**
 * @ClassName: DatabaseExportUtils
 * @description:
 * @author:  Mr.Lee
 */
@SuppressWarnings({"unused"})
public final class DatabaseExportUtils {
    /**
     * @FieldName: DEBUG
     * @description:
     */
    private static final boolean DEBUG = true;
    /**
     * @FieldName: TAG
     * @description: 日志标记
     */
    private static final String TAG = "DatabaseExportUtils";

    /**
     * @MethodName: DatabaseExportUtils
     * @description: 私有构造方法
     * @author:  Mr.Lee
     */
    private DatabaseExportUtils() {
        throw new Error("Do not need instantiate!");
    }

    /**
     * @MethodName: startExportDatabase
     * @description: 开始导出数据 此操作比较耗时,建议在线程中进行
     * @author:  Mr.Lee
     * @param context      上下文
     * @param targetFile   目标文件
     * @param databaseName 要拷贝的数据库文件名
     * @return 是否倒出成功
     */
    public boolean startExportDatabase(Context context, String targetFile,String databaseName) {
        if (DEBUG) {
            LogUtil.e(TAG,"数据库文件导出开始",true);
        }
        if (!Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            if (DEBUG) {
                LogUtil.e(TAG,"存储设备加载失败",true);
            }
            return false;
        }
        String sourceFilePath = Environment.getDataDirectory() + "/data/"
                + context.getPackageName() + "/databases/" + databaseName;
        String destFilePath = Environment.getExternalStorageDirectory()
                + (TextUtils.isEmpty(targetFile) ? (context.getPackageName() + ".db")
                                                 : targetFile);
        boolean isCopySuccess = FileUtils.copyFile(sourceFilePath, destFilePath);
        if (DEBUG) {
            if (isCopySuccess) {
                LogUtil.e(TAG,"数据库文件导出完成",true);
            } else {
                LogUtil.e(TAG,"数据库文件导出失败",true);
            }
        }
        return isCopySuccess;
    }
}