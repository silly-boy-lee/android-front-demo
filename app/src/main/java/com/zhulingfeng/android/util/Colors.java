package com.zhulingfeng.android.util;

/**
 * @ClassName: Colors
 * @description: 颜色常量类 包括常用的色值
 * @author:  Mr.Lee
 */
@SuppressWarnings({"unused"})
public final class Colors {

    /**
     * @MethodName:
     * @description: 
     * @author:  Mr.Lee
     */
    private Colors() {
        throw new Error("Do not need instantiate!");
    }

    /**
     * @FieldName:WHITE
     * @description:白色
     */
    public static final int WHITE = 0xffffffff;
    /**
     * @FieldName: WHITE_TRANSLUCENT
     * @description: 白色 - 半透明
     */
    public static final int WHITE_TRANSLUCENT = 0x80ffffff;
    /**
     * @FieldName: BLACK
     * @description: 黑色
     */
    public static final int BLACK = 0xff000000;
    /**
     * @FieldName: BLACK_TRANSLUCENT
     * @description: 黑色 - 半透明
     */
    public static final int BLACK_TRANSLUCENT = 0x80000000;
    /**
     * @FieldName: TRANSPARENT
     * @description: 透明
     */
    public static final int TRANSPARENT = 0x00000000;
    /**
     * @FieldName: RED
     * @description: 红色
     */
    public static final int RED = 0xffff0000;
    /**
     * @FieldName: RED_TRANSLUCENT
     * @description: 红色 - 半透明
     */
    public static final int RED_TRANSLUCENT = 0x80ff0000;
    /**
     * @FieldName: RED_DARK
     * @description: 红色 - 深的
     */
    public static final int RED_DARK = 0xff8b0000;
    /**
     * @FieldName: RED_DARK_TRANSLUCENT
     * @description: 红色 - 深的 - 半透明
     */
    public static final int RED_DARK_TRANSLUCENT = 0x808b0000;
    /**
     * @FieldName: GREEN
     * @description: 绿色
     */
    public static final int GREEN = 0xff00ff00;
    /**
     * @FieldName: GREEN_TRANSLUCENT
     * @description: 绿色 - 半透明
     */
    public static final int GREEN_TRANSLUCENT = 0x8000ff00;
    /**
     * @FieldName: GREEN_DARK
     * @description: 绿色 - 深的
     */
    public static final int GREEN_DARK = 0xff003300;
    /**
     * @FieldName: GREEN_DARK_TRANSLUCENT
     * @description: 绿色 - 深的 - 半透明
     */
    public static final int GREEN_DARK_TRANSLUCENT = 0x80003300;
    /**
     * @FieldName: GREEN_LIGHT
     * @description: 绿色 - 浅的
     */
    public static final int GREEN_LIGHT = 0xffccffcc;
    /**
     * @FieldName: GREEN_LIGHT_TRANSLUCENT
     * @description: 绿色 - 浅的 - 半透明
     */
    public static final int GREEN_LIGHT_TRANSLUCENT = 0x80ccffcc;
    /**
     * @FieldName: BLUE
     * @description: 蓝色
     */
    public static final int BLUE = 0xff0000ff;
    /**
     * @FieldName: BLUE_TRANSLUCENT
     * @description: 蓝色 - 半透明
     */
    public static final int BLUE_TRANSLUCENT = 0x800000ff;
    /**
     * @FieldName: BLUE_DARK
     * @description: 蓝色 - 深的
     */
    public static final int BLUE_DARK = 0xff00008b;
    /**
     * @FieldName: BLUE_DARK_TRANSLUCENT
     * @description: 蓝色 - 深的 - 半透明
     */
    public static final int BLUE_DARK_TRANSLUCENT = 0x8000008b;
    /**
     * @FieldName: BLUE_LIGHT
     * @description: 蓝色 - 浅的
     */
    public static final int BLUE_LIGHT = 0xff36a5E3;
    /**
     * @FieldName: BLUE_LIGHT_TRANSLUCENT
     * @description: 蓝色 - 浅的 - 半透明
     */
    public static final int BLUE_LIGHT_TRANSLUCENT = 0x8036a5E3;
    /**
     * @FieldName: SKYBLUE
     * @description: 天蓝
     */
    public static final int SKYBLUE = 0xff87ceeb;
    /**
     * @FieldName: SKYBLUE_TRANSLUCENT
     * @description: 天蓝 - 半透明
     */
    public static final int SKYBLUE_TRANSLUCENT = 0x8087ceeb;
    /**
     * @FieldName: SKYBLUE_DARK
     * @description: 天蓝 - 深的
     */
    public static final int SKYBLUE_DARK = 0xff00bfff;
    /**
     * @FieldName: SKYBLUE_DARK_TRANSLUCENT
     * @description: 天蓝 - 深的 - 半透明
     */
    public static final int SKYBLUE_DARK_TRANSLUCENT = 0x8000bfff;
    /**
     * @FieldName: SKYBLUE_LIGHT
     * @description: 天蓝 - 浅的
     */
    public static final int SKYBLUE_LIGHT = 0xff87cefa;
    /**
     * @FieldName: SKYBLUE_LIGHT_TRANSLUCENT
     * @description: 天蓝 - 浅的 - 半透明
     */
    public static final int SKYBLUE_LIGHT_TRANSLUCENT = 0x8087cefa;
    /**
     * @FieldName: GRAY
     * @description: 灰色
     */
    public static final int GRAY = 0xff969696;
    /**
     * @FieldName: GRAY_TRANSLUCENT
     * @description: 灰色 - 半透明
     */
    public static final int GRAY_TRANSLUCENT = 0x80969696;
    /**
     * @FieldName: GRAY_DARK
     * @description: 灰色 - 深的
     */
    public static final int GRAY_DARK = 0xffa9a9a9;
    /**
     * @FieldName: GRAY_DARK_TRANSLUCENT
     * @description: 灰色 - 深的 - 半透明
     */
    public static final int GRAY_DARK_TRANSLUCENT = 0x80a9a9a9;
    /**
     * @FieldName: GRAY_DIM
     * @description: 灰色 - 暗的
     */
    public static final int GRAY_DIM = 0xff696969;
    /**
     * @FieldName: GRAY_DIM_TRANSLUCENT
     * @description: 灰色 - 暗的 - 半透明
     */
    public static final int GRAY_DIM_TRANSLUCENT = 0x80696969;
    /**
     * @FieldName: GRAY_LIGHT
     * @description: 灰色 - 浅的
     */
    public static final int GRAY_LIGHT = 0xffd3d3d3;
    /**
     * @FieldName: GRAY_LIGHT_TRANSLUCENT
     * @description: 灰色 - 浅的 - 半透明
     */
    public static final int GRAY_LIGHT_TRANSLUCENT = 0x80d3d3d3;
    /**
     * @FieldName: ORANGE
     * @description: 橙色
     */
    public static final int ORANGE = 0xffffa500;
    /**
     * @FieldName: ORANGE_TRANSLUCENT
     * @description:  橙色 - 半透明
     */
    public static final int ORANGE_TRANSLUCENT = 0x80ffa500;
    /**
     * @FieldName: ORANGE_DARK
     * @description: 橙色 - 深的
     */
    public static final int ORANGE_DARK = 0xffff8800;
    /**
     * @FieldName: ORANGE_DARK_TRANSLUCENT
     * @description: 橙色 - 深的 - 半透明
     */
    public static final int ORANGE_DARK_TRANSLUCENT = 0x80ff8800;
    /**
     * @FieldName: ORANGE_LIGHT
     * @description: 橙色 - 浅的
     */
    public static final int ORANGE_LIGHT = 0xffffbb33;
    /**
     * @FieldName: ORANGE_LIGHT_TRANSLUCENT
     * @description: 橙色 - 浅的 - 半透明
     */
    public static final int ORANGE_LIGHT_TRANSLUCENT = 0x80ffbb33;
    /**
     * @FieldName: GOLD
     * @description: 金色
     */
    public static final int GOLD = 0xffffd700;
    /**
     * @FieldName: GOLD_TRANSLUCENT
     * @description: 金色 - 半透明
     */
    public static final int GOLD_TRANSLUCENT = 0x80ffd700;
    /**
     * @FieldName: PINK
     * @description: 粉色
     */
    public static final int PINK = 0xffffc0cb;
    /**
     * @FieldName: PINK_TRANSLUCENT
     * @description: 粉色 - 半透明
     */
    public static final int PINK_TRANSLUCENT = 0x80ffc0cb;
    /**
     * @FieldName: FUCHSIA
     * @description: 紫红色
     */
    public static final int FUCHSIA = 0xffff00ff;
    /**
     * @FieldName: FUCHSIA_TRANSLUCENT
     * @description: 紫红色 - 半透明
     */
    public static final int FUCHSIA_TRANSLUCENT = 0x80ff00ff;
    /**
     * @FieldName: GRAYWHITE
     * @description: 灰白色
     */
    public static final int GRAYWHITE = 0xfff2f2f2;
    /**
     * @FieldName: GRAYWHITE_TRANSLUCENT
     * @description: 灰白色 - 半透明
     */
    public static final int GRAYWHITE_TRANSLUCENT = 0x80f2f2f2;
    /**
     * @FieldName: PURPLE
     * @description: 紫色
     */
    public static final int PURPLE = 0xff800080;
    /**
     * @FieldName: PURPLE_TRANSLUCENT
     * @description: 紫色 - 半透明
     */
    public static final int PURPLE_TRANSLUCENT = 0x80800080;
    /**
     * @FieldName: CYAN
     * @description: 青色
     */
    public static final int CYAN = 0xff00ffff;
    /**
     * @FieldName: CYAN_TRANSLUCENT
     * @description: 青色 - 半透明
     */
    public static final int CYAN_TRANSLUCENT = 0x8000ffff;
    /**
     * @FieldName: CYAN_DARK
     * @description: 青色 - 深的
     */
    public static final int CYAN_DARK = 0xff008b8b;
    /**
     * @FieldName: CYAN_DARK_TRANSLUCENT
     * @description: 青色 - 深的 - 半透明
     */
    public static final int CYAN_DARK_TRANSLUCENT = 0x80008b8b;
    /**
     * @FieldName: YELLOW
     * @description: 黄色
     */
    public static final int YELLOW = 0xffffff00;
    /**
     * @FieldName: YELLOW_TRANSLUCENT
     * @description: 黄色 - 半透明
     */
    public static final int YELLOW_TRANSLUCENT = 0x80ffff00;
    /**
     * @FieldName: YELLOW_LIGHT
     * @description: 黄色 - 浅的
     */
    public static final int YELLOW_LIGHT = 0xffffffe0;
    /**
     * @FieldName: YELLOW_LIGHT_TRANSLUCENT
     * @description: 黄色 - 浅的 - 半透明
     */
    public static final int YELLOW_LIGHT_TRANSLUCENT = 0x80ffffe0;
    /**
     * @FieldName: CHOCOLATE
     * @description: 巧克力色
     */
    public static final int CHOCOLATE = 0xffd2691e;
    /**
     * @FieldName: CHOCOLATE_TRANSLUCENT
     * @description: 巧克力色 - 半透明
     */
    public static final int CHOCOLATE_TRANSLUCENT = 0x80d2691e;
    /**
     * @FieldName: TOMATO
     * @description: 番茄色
     */
    public static final int TOMATO = 0xffff6347;
    /**
     * @FieldName: TOMATO_TRANSLUCENT
     * @description: 番茄色 - 半透明
     */
    public static final int TOMATO_TRANSLUCENT = 0x80ff6347;
    /**
     * @FieldName: ORANGERED
     * @description: 橙红色
     */
    public static final int ORANGERED = 0xffff4500;
    /**
     * @FieldName: ORANGERED_TRANSLUCENT
     * @description: 橙红色 - 半透明
     */
    public static final int ORANGERED_TRANSLUCENT = 0x80ff4500;
    /**
     * @FieldName: SILVER
     * @description: 银白色
     */
    public static final int SILVER = 0xffc0c0c0;
    /**
     * @FieldName: SILVER_TRANSLUCENT
     * @description: 银白色 - 半透明
     */
    public static final int SILVER_TRANSLUCENT = 0x80c0c0c0;
    /**
     * @FieldName: 高光
     * @description: HIGHLIGHT
     */
    public static final int HIGHLIGHT = 0x33ffffff;
    /**
     * @FieldName: LOWLIGHT
     * @description: 低光
     */
    public static final int LOWLIGHT = 0x33000000;

}