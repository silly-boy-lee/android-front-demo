package com.zhulingfeng.android.util;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * @ClassName: NestedGridView
 * @description: 专门嵌套使用的GridView，重写其onMeasure()方法使其显示所有数据不会出现滚动条
 * @author:  Mr.Lee
 */
@SuppressWarnings({"unused"})
public class NestedGridView extends GridView {

    /**
     * @MethodName: NestedGridView
     * @description: 构造方法
     * @author:  Mr.Lee
     */
    public NestedGridView(Context context, AttributeSet attrs) {
        super(context, attrs);   
    }   

    /**
     * @MethodName: NestedGridView
     * @description: 构造方法
     * @author:  Mr.Lee
     */
    public NestedGridView(Context context) {
        super(context);   
    }   

    /**
     * @MethodName: NestedGridView
     * @description: 构造方法
     * @author:  Mr.Lee
     */
    public NestedGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);   
    } 
    
    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {   
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);   
    }   
}
