package com.zhulingfeng.android.util.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.zhulingfeng.android.util.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


/**
 * @ClassName: AssetDatabaseOpenHelper
 * @description: 数据库操作辅助工具类,用于从Asset文件夹下面读取数据库文件
 * <p>
 *     <br>android应用的数据库一般存放在/data/data/package_name/databases路径下，</br>
 *     本工具类实现了在打开一个(可读，读/写)数据连接时，如果目录/data/data/package_name/databases</br>
 *     没有指定名称的数据库，则从工程目录assets文件夹中将指定数据库复制到该路径下，</br>
 *     如果assets文件夹下也没有指定的数据库文件，则抛出异常</br>
 * </p>
 * <p>
 *     在应用的开发过程中，有时需要预先在数据库存储部分数据
 *     将数据库放置在assets文件夹中，并在应用第一次使用时复制到默认的数据库位置
 *     即：data/data/app_package/databases/ 下
 * </p>
 * <ul>
 *     <li>可以自动将数据库从assets文件夹中复制到/data/data/package_name/databases路径下</li>
 *     <li>可以像使用SQLiteDatabase一样使用此数据库，通过{@link #getWritableDatabase()}方法</li>
 *     来创建/打开一个可读写的数据库，通过{@link #getReadableDatabase()} 方法来创建/打开一个可读数据库</li>
 * </ul>
 * @author:  Mr.Lee
 */
@SuppressWarnings({"unused"})
public class AssetDatabaseOpenHelper {

    /**
     * @FieldName: context
     * @description: 应用上下文
     */
    private Context context;

    /**
     * @FieldName: databaseName
     * @description: 数据库名称
     */
    private String  databaseName;

    /**
     * @FunctionName: AssetDatabaseOpenHelper
     * @description: 构造方法
     * @author:  Mr.Lee
     */
    public AssetDatabaseOpenHelper(Context context, String databaseName) {
        this.context = context;
        this.databaseName = databaseName;
    }

     /**
      * @FunctionName: getWritableDatabase
      * @description: 创建/打开一个读/写的数据库
      * @author:  Mr.Lee
      * @return  返回数据库的对象
      * @throws RuntimeException 如果不能从assets文件夹中复制数据库将抛出此异常
      * @throws SQLiteException 如果数据库不能打开将抛出此异常
      */
    public synchronized SQLiteDatabase getWritableDatabase() {
        //从默认的数据库存放位置（/data/data/package_name/databases）读取数据库文件
        File dbFile = context.getDatabasePath(databaseName);
        // 如果不存在相应的数据库文件，则从Assets文件夹中去复制相应的文件
        if (dbFile != null && !dbFile.exists()) {
            try {
                copyDatabase(dbFile);
            } catch (IOException e) {
                throw new RuntimeException("Error creating source database", e);
            }
        }
        return SQLiteDatabase.openDatabase(dbFile.getPath(), null, SQLiteDatabase.OPEN_READWRITE);
    }

    /**
     * @FunctionName: getReadableDatabase
     * @description: 创建/打开一个读数据库
     * @author:  Mr.Lee
     * @return  返回数据库的对象
     * @throws RuntimeException 如果不能从assets文件夹中复制数据库将抛出此异常
     * @throws SQLiteException 如果数据库不能打开将抛出此异常
     */
    public synchronized SQLiteDatabase getReadableDatabase() {
        //从默认的数据库存放位置（/data/data/package_name/databases）读取数据库文件
        File dbFile = context.getDatabasePath(databaseName);
        // 如果不存在相应的数据库文件，则从Assets文件夹中去复制相应的文件
        if (dbFile != null && !dbFile.exists()) {
            try {
                copyDatabase(dbFile);
            } catch (IOException e) {
                throw new RuntimeException("Error creating source database", e);
            }
        }
        return SQLiteDatabase.openDatabase(dbFile.getPath(), null, SQLiteDatabase.OPEN_READONLY);
    }


    /**
     * @FunctionName: getDatabaseName
     * @description: 返回数据库名称
     * @author:  Mr.Lee
     */
    public String getDatabaseName() {
        return databaseName;
    }

    /**
     * @FunctionName: copyDatabase
     * @description: 从assets文件夹中复制数据库文件
     * @author:  Mr.Lee
     * @param dbFile 目标文件
     * @throws IOException
     */
    private void copyDatabase(File dbFile) throws IOException {
        InputStream stream = context.getAssets().open(databaseName);
        FileUtils.writeFile(dbFile, stream);
        stream.close();
    }

    /**
     * @FunctionName: getFromAssets
     * @description: 从assets文件夹中读取文件并获取数据
     * @author:  Mr.Lee
     * @param context ctx 上下文
     * @param fileName 文件名
     * @return 资源文件信息
     */
    public static String getFromAssets(String fileName,Context context) {
        try {
            InputStreamReader inputReader = new InputStreamReader(context.getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            String Result = "";
            while ((line = bufReader.readLine()) != null) {
                Result += line;
            }
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
