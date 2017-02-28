package com.zhulingfeng.android.util;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @ClassName: AntiEmulatorUtiles
 * @description: 检测模拟器工具类
 * @author:  Mr.Lee
 */
@SuppressWarnings({"unused"})
public class AntiEmulatorUtiles {

    /**
     * @FieldName: known_pipes
     * @description: 已知通道列表
     */
    private static String[] known_pipes={
            "/dev/socket/qemud",
            "/dev/qemu_pipe"
    };

    /**
     * @FieldName: known_qemu_drivers
     * @description: 已知的qemu驱动程序列表
     */
    private static String[] known_qemu_drivers = {
            "goldfish"
    };

    /**
     * @FieldName: 已知的文件列表
     * @description: known_files
     */
    private static String[] known_files = {
            "/system/lib/libc_malloc_debug_qemu.so",
            "/sys/qemu_trace",
            "/system/bin/qemu-props"
    };

    /**
     * @FieldName: known_numbers
     * @description: 已知的电话列表
     */
    private static String[] known_numbers = { "15555215554", "15555215556",
            "15555215558", "15555215560", "15555215562", "15555215564",
            "15555215566", "15555215568", "15555215570", "15555215572",
            "15555215574", "15555215576", "15555215578", "15555215580",
            "15555215582", "15555215584", };

    /**
     * @FieldName: known_device_ids
     * @description: 已知的设备id列表
     */
    private static String[] known_device_ids = {
            "000000000000000" // 默认ID
    };

    /**
     * @FieldName: known_imsi_ids
     * @description: 已知的默认imsi id列表
     */
    private static String[] known_imsi_ids = {
            "310260000000000" // 默认的 imsi id
    };

    /**
     * @MethodName: checkPipes
     * @description: 检测通道路径是否存在
     * @author:  Mr.Lee
     * @return true 表示通道路径存在 false 表示通道路径不存在
     */
    public static boolean checkPipes(){
        for(int i = 0; i < known_pipes.length; i++){
            String pipes = known_pipes[i];
            File qemu_socket = new File(pipes);
            if(qemu_socket.exists()){
                Log.v("Result:", "Find pipes!");
                return true;
            }
        }
        Log.i("Result:", "Not Find pipes!");
        return false;
    }

    /**
     * @MethodName: checkQEmuDriverFile
     * @description: 检测驱动程序文件是否存在
     * @author:  Mr.Lee
     * @return true 驱动程序文件存在  false 驱动程序文件不存在
     */
    public static Boolean checkQEmuDriverFile(){
        File driver_file = new File("/proc/tty/drivers");
        if(driver_file.exists() && driver_file.canRead()){
            byte[] data = new byte[1024];  //(int)driver_file.length()
            try {
                InputStream inStream = new FileInputStream(driver_file);
                inStream.read(data);
                inStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            String driver_data = new String(data);
            for(String known_qemu_driver : AntiEmulatorUtiles.known_qemu_drivers){
                if(driver_data.indexOf(known_qemu_driver) != -1){
                    Log.i("Result:", "Find know_qemu_drivers!");
                    return true;
                }
            }
        }
        Log.i("Result:", "Not Find known_qemu_drivers!");
        return false;
    }

    /**
     * @MethodName: CheckEmulatorFiles
     * @description: 检测模拟器上特有的几个文件是否存在
     * @author:  Mr.Lee
     * @return true 存在模拟器文件 false 不存在模拟器文件
     */
    public static Boolean CheckEmulatorFiles(){
        for(int i = 0; i < known_files.length; i++){
            String file_name = known_files[i];
            File qemu_file = new File(file_name);
            if(qemu_file.exists()){
                Log.v("Result:", "Find Emulator Files!");
                return true;
            }
        }
        Log.v("Result:", "Not Find Emulator Files!");
        return false;
    }

    /**
     * @MethodName: CheckPhoneNumber
     * @description: 检测模拟器默认的电话号码
     * @author:  Mr.Lee
     * @param context 应用程序上下文
     * @return true 模拟器存在默认的电话号码  false 模拟器不存在默认的电话号码
     */
    public static Boolean CheckPhoneNumber(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);

        String phonenumber = telephonyManager.getLine1Number();

        for (String number : known_numbers) {
            if (number.equalsIgnoreCase(phonenumber)) {
                Log.v("Result:", "Find PhoneNumber!");
                return true;
            }
        }
        Log.v("Result:", "Not Find PhoneNumber!");
        return false;
    }

    /**
     * @MethodName: CheckImsiIDS
     * @description: 检测imsi id是不是“310260000000000”
     * @author:  Mr.Lee
     * @param context 应用程序上下文
     * @return true imsi id是“310260000000000”  false imsi id不是“310260000000000”
     */
    public static Boolean CheckImsiIDS(Context context){
        TelephonyManager telephonyManager = (TelephonyManager)
                context.getSystemService(Context.TELEPHONY_SERVICE);

        String imsi_ids = telephonyManager.getSubscriberId();

        for (String know_imsi : known_imsi_ids) {
            if (know_imsi.equalsIgnoreCase(imsi_ids)) {
                Log.v("Result:", "Find imsi ids: 310260000000000!");
                return true;
            }
        }
        Log.v("Result:", "Not Find imsi ids: 310260000000000!");
        return false;
    }

    /**
     * @MethodName: CheckEmulatorBuild
     * @description: 检测手机上的一些硬件信息
     * @author:  Mr.Lee
     * @param context 应用程序上下文
     * @return true 硬件信息存在 false 硬件信息不完善
     */
    public static Boolean CheckEmulatorBuild(Context context){
        String BOARD = android.os.Build.BOARD;
        String BOOTLOADER = android.os.Build.BOOTLOADER;
        String BRAND = android.os.Build.BRAND;
        String DEVICE = android.os.Build.DEVICE;
        String HARDWARE = android.os.Build.HARDWARE;
        String MODEL = android.os.Build.MODEL;
        String PRODUCT = android.os.Build.PRODUCT;
        if (BOARD == "unknown" || BOOTLOADER == "unknown"
                || BRAND == "generic" || DEVICE == "generic"
                || MODEL == "sdk" || PRODUCT == "sdk"
                || HARDWARE == "goldfish")
        {
            Log.v("Result:", "Find Emulator by EmulatorBuild!");
            return true;
        }
        Log.v("Result:", "Not Find Emulator by EmulatorBuild!");
        return false;
    }

}
