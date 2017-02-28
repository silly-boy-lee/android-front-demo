package com.zhulingfeng.android.util;

import android.content.Context;

import com.zhulingfeng.android.constant.SysConst;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * @ClassName: AssetsUtil
 * @description: Assets用于对Assets文件夹中的资源进行访问
 * @author:  Mr.Lee
 */
@SuppressWarnings({"unused"})
public class AssetsUtil {

    /**
     * @MethodName: getFromAssets
     * @description: 从assets文件夹中读取文件并获取数据
     * @author:  Mr.Lee
     * @param ctx 上下文
     * @param fileName 文件名
     * @return 返回文件内容
     */
    public static String getFromAssets(Context ctx,String fileName){
        String result = "";
        try{
            InputStream is = ctx.getResources().getAssets().open(fileName);
            //获取文件的字节数
            int len = is.available();
            //创建byte数组
            byte[] buffer = new byte[len];
            //将文件中的数据读取到byte数组中
            is.read(buffer);
            result = new String(buffer, SysConst.DEFAULT_ENCODE);
        }catch (Exception e){
            e.printStackTrace();
        }
        return  result;
    }

    /**
     * @MethodName: copyFilesFastest
     * @description: 从assets目录中复制整个文件夹内容到新的目录
     * @author:  Mr.Lee
     * @param ctx 上下文
     * @param oldPath 原文件路径 如:aa
     * @param newPath 复制后的文件新路径 如：/data/data/com.zhulingfeng.android/
     */
    public static void copyFilesFastest(Context ctx,String oldPath,String newPath){
        try{
            String[] fileNames = ctx.getAssets().list(oldPath);
            if (fileNames.length > 0){
                //如果是目录
                File file = new File(newPath);
                file.mkdirs();
                //如果文件夹不为空，则递归
                for (String fileName :fileNames) {
                    copyFilesFastest(ctx,oldPath + "/" + fileName,newPath + "/" + fileName);
                }
            }else {
                //如果是文件
                InputStream is = ctx.getAssets().open(oldPath);
                FileOutputStream fos = new FileOutputStream(new File(newPath));
                byte[] buffer = new byte[1024];
                int byteCount = 0;
                //循环从输入流中读取buffer字节
                while ((byteCount = is.read(buffer)) != -1){
                    //将读取的输入写入到输入流
                    fos.write(buffer,0,byteCount);
                }
                //刷新缓冲区
                fos.flush();
                is.close();
                fos.close();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * @MethodName: getFileList
     * @description: 取指定文件夹下面的文件列表
     * @author:  Mr.Lee
     * @param ctx 上下文
     * @param isDebug 调试模式标志
     * @param path 文件夹路径 路径 "./"  或""  表示Assets的根目录
     * @param extName 扩展名"*" ：表示返回所有 ;".apk" 返回apk的文件
     * @return 文件列表
     */
    public static ArrayList<File> getFileList(Context ctx,boolean isDebug,String path,String extName){
        ArrayList<File> mFileList = new ArrayList<>();
        String[] fileNames;
        try{
            fileNames = ctx.getResources().getAssets().list(path);
            for (int i = 0;i < fileNames.length;i++){
                if (fileNames[i].toLowerCase().endsWith(extName) || extName.equals("*")){
                    File file = new File(fileNames[i]);
                    mFileList.add(file);

                    //如果是调试模式，则打印信息
                    if (isDebug){
                        LogUtil.e(fileNames[i],isDebug);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return mFileList;

    }

    /**
     * @MethodName: getFileStrings
     * @description: 取指定文件夹下面的文件名列表
     * @author:  Mr.Lee
     * @param ctx 上下文
     * @param isDebug 调试模式标志
     * @param path 文件夹路径 路径 "./"  或""  表示Assets的根目录
     * @param extName 扩展名"*" ：表示返回所有 ;".apk" 返回apk的文件
     * @return
     */
    public static String[] getFileStrings(Context ctx,boolean isDebug,String path,String extName){
        String[] fileNames;
        ArrayList<String> fileStrings = new ArrayList<>();
        //文件夹下的文件数
        int count = 0;
        try{
            fileNames = ctx.getResources().getAssets().list(path);
            for (int i =0;i < fileNames.length;i++){
                if (fileNames[i].toLowerCase().equals(extName) || extName.equals("*")){
                    fileStrings.add(fileNames[i]);
                    count++;

                    if (isDebug){
                        LogUtil.e(fileNames[i],isDebug);
                    }
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return (String[]) fileStrings.toArray(new String[0]);
    }


}
