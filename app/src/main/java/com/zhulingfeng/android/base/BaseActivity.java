package com.zhulingfeng.android.base;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @ClassName: BaseActivity
 * @description: Activity基类
 * @author:  Mr.Lee
 */
@SuppressWarnings({"unused"})
public abstract class BaseActivity extends AppCompatActivity {
    /**
     * @FieldName: mUnbinder
     * @description: 小黄刀组件绑定器
     */
    private Unbinder mUnbinder;

    /**
     * @MethodName: initLayout
     * @description: 抽象方法，初始化布局容器
     * @author:  Mr.Lee
     */
    protected abstract int initLayout();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /*在些方法中完成布局文件的加载和小黄刀组件的初始化*/
        super.onCreate(savedInstanceState);
        setContentView(initLayout());
        mUnbinder = ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        /*在些方法中完成Activity和小黄刀组件的销毁工作*/
        mUnbinder.unbind();
        super.onDestroy();
    }
}
