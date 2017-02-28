package com.zhulingfeng.android.util; /**
 * Copyright 2014 Zhenguo Jin
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.math.BigDecimal;

/**
 * @ClassName: DataCleanManager
 * @description: 本应用数据清除管理器,
 * 主要功能有清除内/外缓存，清除数据库，清除sharedPreference，清除files和清除自定义目录
 * @author:  Mr.Lee
 */
@SuppressWarnings({"unused"})
public class DataCleanManager {
    /**
     * @MethodName: cleanInternalCache
     * @description: 清除本应用内部缓存(/data/data/com.xxx.xxx/cache)
     * @author:  Mr.Lee
     * @param context 上下文
     */
    public static void cleanInternalCache(Context context) {
        deleteFilesByDirectory(context.getCacheDir());
    }

    /**
     * @MethodName: cleanDatabases
     * @description: 清除本应用所有数据库(/data/data/com.xxx.xxx/databases)
     * @author:  Mr.Lee
     * @param context 上下文
     */
    public static void cleanDatabases(Context context) {
        deleteFilesByDirectory(new File(context.getFilesDir().getPath()
                + context.getPackageName() + "/databases"));
    }

    /**
     * @MethodName: cleanSharedPreference
     * @description: 清除本应用SharedPreference(/data/data/com.xxx.xxx/shared_prefs)
     * @author:  Mr.Lee
     * @param context 上下文
     */
    public static void cleanSharedPreference(Context context) {
        deleteFilesByDirectory(new File(context.getFilesDir().getPath()
                + context.getPackageName() + "/shared_prefs"));
    }

    /**
     * @MethodName: cleanDatabaseByName
     * @description: 按名字清除本应用数据库
     * @author:  Mr.Lee
     * @param context 上下文
     * @param dbName 数据库名称
     */
    public static void cleanDatabaseByName(Context context, String dbName) {
        context.deleteDatabase(dbName);
    }

    /**
     * @MethodName: cleanFiles
     * @description: 清除/data/data/com.xxx.xxx/files下的内容
     * @author:  Mr.Lee
     * @param context 上下文
     */
    public static void cleanFiles(Context context) {
        deleteFilesByDirectory(context.getFilesDir());
    }

    /**
     * @MethodName: cleanExternalCache
     * @description: 清除外部cache下的内容(/mnt/sdcard/android/data/com.xxx.xxx/cache)
     * @author:  Mr.Lee
     * @param context 上下文
     */
    public static void cleanExternalCache(Context context) {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            deleteFilesByDirectory(context.getExternalCacheDir());
        }
    }

    /**
     * @MethodName: cleanCustomCache
     * @description: 清除自定义路径下的文件，使用需小心，请不要误删。而且只支持目录下的文件删除
     * @author:  Mr.Lee
     * @param filePath 文件路径
     */
    public static void cleanCustomCache(String filePath) {
        deleteFilesByDirectory(new File(filePath));
    }

    /**
     * @MethodName: cleanApplicationData
     * @description: 清除本应用所有的数据
     * @author:  Mr.Lee
     * @param context 上下文
     * @param filePath 文件路径
     */
    public static void cleanApplicationData(Context context, String... filePath) {
        cleanInternalCache(context);
        cleanExternalCache(context);
        cleanDatabases(context);
        cleanSharedPreference(context);
        cleanFiles(context);
        for (String fp : filePath) {
            cleanCustomCache(fp);
        }
    }

    /**
     * @MethodName: deleteFilesByDirectory
     * @description: 删除方法 这里只会删除某个文件夹下的文件，如果传入的directory是个文件，将不做处理
     * @author:  Mr.Lee
     * @param directory 文件夹File对象
     */
    private static void deleteFilesByDirectory(File directory) {
        if (directory != null && directory.exists() && directory.isDirectory()) {
            for (File item : directory.listFiles()) {
                item.delete();
            }
        }
    }

    /**
     * @MethodName: getFolderSize
     * @description:  获取文件
     * <br>Context.getExternalFilesDir() --> SDCard/Android/data/你的应用的包名/files/ 目录，一般放一些长时间保存的数据</br>
     * <br>Context.getExternalCacheDir() --> SDCard/Android/data/你的应用包名/cache/目录，一般存放临时缓存数据</br>
     * @author:  Mr.Lee
     */
    public static long getFolderSize(File file) throws Exception {
        long size = 0;
        try {
            File[] fileList = file.listFiles();
            for (int i = 0; i < fileList.length; i++) {
                // 如果下面还有文件
                if (fileList[i].isDirectory()) {
                    size = size + getFolderSize(fileList[i]);
                } else {
                    size = size + fileList[i].length();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return size;
    }

    /**
     * @MethodName: getCacheSize
     * @description:
     * @author:  Mr.Lee
     * @param file
     * @return
     * @throws Exception
     */
    public static String getCacheSize(File file) throws Exception {
        return getFormatSize(getFolderSize(file));
    }

    /**
     * @MethodName: getFormatSize
     * @description:
     * @author:  Mr.Lee
     * @param size 传入的大小
     * @return  格式化单位返回格式化之后的值
     */
    public static String getFormatSize(double size) {
        double kiloByte = size / 1024;
        if (kiloByte < 1) {
            return size + "Byte";
        }

        double megaByte = kiloByte / 1024;
        if (megaByte < 1) {
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP)
                          .toPlainString() + "KB";
        }

        double gigaByte = megaByte / 1024;
        if (gigaByte < 1) {
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP)
                          .toPlainString() + "MB";
        }

        double teraBytes = gigaByte / 1024;
        if (teraBytes < 1) {
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP)
                          .toPlainString() + "GB";
        }
        BigDecimal result4 = new BigDecimal(teraBytes);
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString()
                + "TB";
    }

}