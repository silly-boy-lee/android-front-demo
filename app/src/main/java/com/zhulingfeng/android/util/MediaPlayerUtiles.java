package com.zhulingfeng.android.util;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.util.Log;
 
/**
 * @ClassName: MediaPlayerUtiles
 * @description: 播放资源目录下音频文件
 * @author:  Mr.Lee
 */
@SuppressWarnings({"unused"})
public class MediaPlayerUtiles {
    /**
     * @FieldName: mMediaPlayer
     * @description: 媒体播放器
     */
    static MediaPlayer mMediaPlayer;

    /**
     * @MethodName: getMediaPlayer
     * @description: 取媒体播放器对象
     * @author:  Mr.Lee
     */
    public static MediaPlayer getMediaPlayer() {
        if (null == mMediaPlayer) {
            mMediaPlayer = new MediaPlayer();
        }
        return mMediaPlayer;
    }

    /**
     * @MethodName: playAudio
     * @description: 播放音频
     * @author:  Mr.Lee
     * @param mContext 上下文
     * @param fileName 文件名
     */
    public static void playAudio(Context mContext, String fileName) {
        try {
            stopAudio();//如果正在播放就停止
            AssetManager assetManager = mContext.getAssets();
            AssetFileDescriptor afd = assetManager.openFd(fileName);
            MediaPlayer mediaPlayer = getMediaPlayer();
            mediaPlayer.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
            mediaPlayer.setLooping(false);//循环播放
            mediaPlayer.prepare();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override public void onPrepared(MediaPlayer mediaPlayer) {
                    mediaPlayer.start();
                }
            });
        } catch (Exception e) {
            Log.e("播放音频失败","");
        }
    }

    /**
     * @MethodName: stopAudio
     * @description: 停止播放音频
     * @author:  Mr.Lee
     */
    public static void stopAudio(){
        try {
            if (null!=mMediaPlayer){
                if (mMediaPlayer.isPlaying()){
                    mMediaPlayer.pause();
                    mMediaPlayer.reset();
                    mMediaPlayer.stop();
                }
            }
        }catch (Exception e){
            Log.e("stopAudio",e.getMessage());
        }


    }

}