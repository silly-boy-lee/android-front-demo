package com.zhulingfeng.android.util;


import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;

/**
 * @ClassName: AnimationUtils
 * @description: 动画工具类
 * @author:  Mr.Lee
 */
@SuppressWarnings({"unused"})
public final class AnimationUtils {

    /**
     * @MethodName: AnimationUtils
     * @description: 表示此工具类不能被实例化
     * @author:  Mr.Lee
     */
    private AnimationUtils() {
        throw new Error("Do not need instantiate!");
    }

    /**
     * @FieldName: DEFAULT_ANIMATION_DURATION
     * @description: 默认动画持续时间
     */
    public static final long DEFAULT_ANIMATION_DURATION = 400;

    /**
     * @MethodName: getRotateAnimation
     * @description: 获取一个旋转动画
     * @author:  Mr.Lee
     * @param fromDegrees       开始角度
     * @param toDegrees         结束角度
     * @param pivotXType        X轴的伸缩模式，可以取值为ABSOLUTE、RELATIVE_TO_SELF、RELATIVE_TO_PARENT
     * @param pivotXValue       旋转中心点X轴坐标
     * @param pivotYType        Y轴的伸缩模式，可以取值为ABSOLUTE、RELATIVE_TO_SELF、RELATIVE_TO_PARENT
     * @param pivotYValue       旋转中心点Y轴坐标
     * @param durationMillis    持续时间
     * @param animationListener 动画监听器
     * @return 旋转动画
     */
    public static RotateAnimation getRotateAnimation(float fromDegrees, float toDegrees, int pivotXType, float pivotXValue, int pivotYType, float pivotYValue, long durationMillis, AnimationListener animationListener) {
        RotateAnimation rotateAnimation = new RotateAnimation(fromDegrees,toDegrees, pivotXType, pivotXValue, pivotYType, pivotYValue);
        rotateAnimation.setDuration(durationMillis);
        if (animationListener != null) {
            rotateAnimation.setAnimationListener(animationListener);
        }
        return rotateAnimation;
    }

    /**
     * @MethodName: getRotateAnimationByCenter
     * @description: 获取一个根据视图自身中心点旋转360度的动画
     * @author:  Mr.Lee
     * @param durationMillis    动画持续时间
     * @param animationListener 动画监听器
     * @return 旋转动画
     */
    public static RotateAnimation getRotateAnimationByCenter(long durationMillis, AnimationListener animationListener) {
        return getRotateAnimation(0f, 360f, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f, durationMillis,animationListener);
    }

    /**
     * @MethodName: 获取一个根据中心点旋转360度的动画
     * @description:
     * @author:  Mr.Lee
     * @param duration 动画持续时间
     * @return 旋转动画
     */
    public static RotateAnimation getRotateAnimationByCenter(long duration) {
        return getRotateAnimationByCenter(duration, null);
    }

    /**
     * @MethodName: getRotateAnimationByCenter
     * @description: 获取一个根据视图自身中心点旋转的动画，动画持续时间为默认时间
     * @author:  Mr.Lee
     * @param animationListener 动画监听器
     * @return 旋转动画
     */
    public static RotateAnimation getRotateAnimationByCenter(AnimationListener animationListener) {
        return getRotateAnimationByCenter(DEFAULT_ANIMATION_DURATION, animationListener);
    }

    /**
     * @MethodName: getRotateAnimationByCenter
     * @description: 获取一个根据视图自身中心点旋转的动画，动画持续时间为默认时间
     * @author:  Mr.Lee
     * @return 一个根据中心点旋转的动画，默认持续时间为DEFAULT_ANIMATION_DURATION
     */
    public static RotateAnimation getRotateAnimationByCenter() {
        return getRotateAnimationByCenter(DEFAULT_ANIMATION_DURATION, null);
    }

    /**
     * @MethodName: 获取一个透明度渐变动画
     * @description: getAlphaAnimation
     * @author:  Mr.Lee
     * @param fromAlpha         开始时的透明度
     * @param toAlpha           结束时的透明度都
     * @param durationMillis    持续时间
     * @param animationListener 动画监听器
     * @return 透明度渐变动画
     */
    public static AlphaAnimation getAlphaAnimation(float fromAlpha, float toAlpha, long durationMillis, AnimationListener animationListener) {
        AlphaAnimation alphaAnimation = new AlphaAnimation(fromAlpha, toAlpha);
        alphaAnimation.setDuration(durationMillis);
        if (animationListener != null) {
            alphaAnimation.setAnimationListener(animationListener);
        }
        return alphaAnimation;
    }

    /**
     * @MethodName: getAlphaAnimation
     * @description: 获取一个透明度渐变动画
     * @author:  Mr.Lee
     * @param fromAlpha      起始透明度
     * @param toAlpha        结束透明度
     * @param durationMillis 动画持续时间
     * @return 透明度渐变动画
     */
    public static AlphaAnimation getAlphaAnimation(float fromAlpha, float toAlpha, long durationMillis) {
        return getAlphaAnimation(fromAlpha, toAlpha, durationMillis, null);
    }

    /**
     * @MethodName: getAlphaAnimation
     * @description: 获取一个透明度渐变动画,动画持续时间采取默认值
     * @author:  Mr.Lee
     * @param fromAlpha         起始透明度
     * @param toAlpha           结束透明度
     * @param animationListener 动画监听器
     * @return 一个透明度渐变动画，默认持续时间为DEFAULT_ANIMATION_DURATION
     */
    public static AlphaAnimation getAlphaAnimation(float fromAlpha, float toAlpha, AnimationListener animationListener) {
        return getAlphaAnimation(fromAlpha, toAlpha, DEFAULT_ANIMATION_DURATION,animationListener);
    }

    /**
     * @MethodName: getAlphaAnimation
     * @description: 获取一个透明度渐变动画，动画持续时间采取默认值
     * @author:  Mr.Lee
     * @param fromAlpha 起始透明度
     * @param toAlpha   结束透明度
     * @return 一个透明度渐变动画，默认持续时间为DEFAULT_ANIMATION_DURATION
     */
    public static AlphaAnimation getAlphaAnimation(float fromAlpha, float toAlpha) {
        return getAlphaAnimation(fromAlpha, toAlpha, DEFAULT_ANIMATION_DURATION,null);
    }

    /**
     * @MethodName: getHiddenAlphaAnimation
     * @description: 获取一个由完全显示变为不可见的透明度渐变动画
     * @author:  Mr.Lee
     * @param durationMillis    持续时间
     * @param animationListener 动画监听器
     * @return 一个由完全显示变为不可见的透明度渐变动画
     */
    public static AlphaAnimation getHiddenAlphaAnimation(long durationMillis, AnimationListener animationListener) {
        return getAlphaAnimation(1.0f, 0.0f, durationMillis, animationListener);
    }

    /**
     * @MethodName: getHiddenAlphaAnimation
     * @description: 获取一个由完全显示变为不可见的透明度渐变动画
     * @author:  Mr.Lee
     * @param durationMillis 持续时间
     * @return 一个由完全显示变为不可见的透明度渐变动画
     */
    public static AlphaAnimation getHiddenAlphaAnimation(long durationMillis) {
        return getHiddenAlphaAnimation(durationMillis, null);
    }

    /**
     * @MethodName: getHiddenAlphaAnimation
     * @description: 获取一个由完全显示变为不可见的透明度渐变动画,动画持续时间使用默认值
     * @author:  Mr.Lee
     * @param animationListener 动画监听器
     * @return 一个由完全显示变为不可见的透明度渐变动画，默认持续时间为DEFAULT_ANIMATION_DURATION
     */
    public static AlphaAnimation getHiddenAlphaAnimation(AnimationListener animationListener) {
        return getHiddenAlphaAnimation(DEFAULT_ANIMATION_DURATION,animationListener);
    }

    /**
     * @MethodName: getHiddenAlphaAnimation
     * @description: 获取一个由完全显示变为不可见的透明度渐变动画
     * @author:  Mr.Lee
     * @return 一个由完全显示变为不可见的透明度渐变动画，默认持续时间为DEFAULT_ANIMATION_DURATION
     */
    public static AlphaAnimation getHiddenAlphaAnimation() {
        return getHiddenAlphaAnimation(DEFAULT_ANIMATION_DURATION, null);
    }

    /**
     * @MethodName: getShowAlphaAnimation
     * @description: 获取一个由不可见变为完全显示的透明度渐变动画
     * @author:  Mr.Lee
     * @param durationMillis    动画持续时间
     * @param animationListener 动画监听器
     * @return 一个由不可见变为完全显示的透明度渐变动画
     */
    public static AlphaAnimation getShowAlphaAnimation(long durationMillis, AnimationListener animationListener) {
        return getAlphaAnimation(0.0f, 1.0f, durationMillis, animationListener);
    }

    /**
     * @MethodName:  getShowAlphaAnimation
     * @description:  获取一个由不可见变为完全显示的透明度渐变动画
     * @author:  Mr.Lee
     * @param durationMillis 持续时间
     * @return 一个由不可见变为完全显示的透明度渐变动画
     */
    public static AlphaAnimation getShowAlphaAnimation(long durationMillis) {
        return getAlphaAnimation(0.0f, 1.0f, durationMillis, null);
    }

    /**
     * @MethodName: getShowAlphaAnimation
     * @description: 获取一个由不可见变为完全显示的透明度渐变动画，动画持续时间采用默认值
     * @author:  Mr.Lee
     * @param animationListener 动画监听器
     * @return 一个由不可见变为完全显示的透明度渐变动画，默认持续时间为DEFAULT_ANIMATION_DURATION
     */
    public static AlphaAnimation getShowAlphaAnimation(AnimationListener animationListener) {
        return getAlphaAnimation(0.0f, 1.0f, DEFAULT_ANIMATION_DURATION,animationListener);
    }

    /**
     * @MethodName: getShowAlphaAnimation
     * @description: 获取一个由不可见变为完全显示的透明度渐变动画，动画持续时间采用默认值
     * @author:  Mr.Lee
     * @return 一个由不可见变为完全显示的透明度渐变动画，默认持续时间为DEFAULT_ANIMATION_DURATION
     */
    public static AlphaAnimation getShowAlphaAnimation() {
        return getAlphaAnimation(0.0f, 1.0f, DEFAULT_ANIMATION_DURATION, null);
    }

    /**
     * @MethodName: getLessenScaleAnimation
     * @description: 获取一个缩小动画,由大放小至消失
     * @author:  Mr.Lee
     * @param durationMillis   时间
     * @param animationListener  监听
     * @return 一个缩小动画
     */
    public static ScaleAnimation getLessenScaleAnimation(long durationMillis, AnimationListener animationListener) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 0.0f, 1.0f,
                0.0f, ScaleAnimation.RELATIVE_TO_SELF,
                ScaleAnimation.RELATIVE_TO_SELF);
        scaleAnimation.setDuration(durationMillis);
        if (null != animationListener){
            scaleAnimation.setAnimationListener(animationListener);
        }
        return scaleAnimation;
    }

    /**
     * @MethodName: getLessenScaleAnimation
     * @description: 获取一个缩小动画,由大放小至消失
     * @author:  Mr.Lee
     * @param durationMillis 动画持续时间
     * @return 缩小动画
     */
    public static ScaleAnimation getLessenScaleAnimation(long durationMillis) {
        return getLessenScaleAnimation(durationMillis, null);
    }

    /**
     * @MethodName: getLessenScaleAnimation
     * @description: 获取一个缩小动画,由大放小至消失，动画持续时间采用默认值
     * @author:  Mr.Lee
     * @param animationListener  监听
     * @return 返回一个缩小的动画
     */
    public static ScaleAnimation getLessenScaleAnimation(AnimationListener animationListener) {
        return getLessenScaleAnimation(DEFAULT_ANIMATION_DURATION,animationListener);
    }

    /**
     * @MethodName: getAmplificationAnimation
     * @description: 获取一个放大动画，由小到大直到其原始大小
     * @author:  Mr.Lee
     * @param durationMillis   时间
     * @param animationListener  监听
     * @return 返回一个放大的效果
     */
    public static ScaleAnimation getAmplificationAnimation(long durationMillis, AnimationListener animationListener) {
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f, 1.0f, 0.0f,
                1.0f, ScaleAnimation.RELATIVE_TO_SELF,
                ScaleAnimation.RELATIVE_TO_SELF);
        scaleAnimation.setDuration(durationMillis);
        scaleAnimation.setAnimationListener(animationListener);
        return scaleAnimation;
    }

    /**
     * @MethodName: getAmplificationAnimation
     * @description: 获取一个放大动画，由小到大直到其原始大小
     * @author:  Mr.Lee
     * @param durationMillis   时间
     * @return 返回一个放大的效果
     */
    public static ScaleAnimation getAmplificationAnimation(long durationMillis) {
        return getAmplificationAnimation(durationMillis, null);

    }

    /**
     * @MethodName: getAmplificationAnimation
     * @description: 获取一个放大动画，由小到大直到其原始大小
     * @author:  Mr.Lee
     * @param animationListener  监听
     * @return 返回一个放大的效果
     */
    public static ScaleAnimation getAmplificationAnimation(AnimationListener animationListener) {
        return getAmplificationAnimation(DEFAULT_ANIMATION_DURATION,
                animationListener);
    }
}
