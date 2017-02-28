package com.zhulingfeng.android.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


/**
 * @ClassName: ViewUtils
 * @description: 视图工具箱
 * @author:  Mr.Lee
 */
@SuppressWarnings({"unused"})
public class ViewUtils {

    /**
     * @FieldName: CLASS_NAME_GRID_VIEW
     * @description: 网格视图类名
     */
    private static final String CLASS_NAME_GRID_VIEW
            = "android.widget.GridView";
    /**
     * @FieldName: FIELD_NAME_VERTICAL_SPACING
     * @description: 
     */
    private static final String FIELD_NAME_VERTICAL_SPACING
            = "mVerticalSpacing";


    /**
     * @MethodName: ViewUtils
     * @description: 将构造方法私有化，通过JNI方式实例化此类
     * @author:  Mr.Lee
     */
    private ViewUtils() {
        throw new AssertionError();
    }
    
    /**
     * @MethodName: 通过listview中每一外item子项的高度来计算listview的高度并返回
     * @description: getListViewHeightBasedOnChildren
     * @author:  Mr.Lee
     * @param view view
     * @return int 返回listview的高度
     */
    public static int getListViewHeightBasedOnChildren(ListView view) {
        int height = getAbsListViewHeightBasedOnChildren(view);
        ListAdapter adapter;
        int adapterCount;
        if (view != null && (adapter = view.getAdapter()) != null &&
                (adapterCount = adapter.getCount()) > 0) {
            height += view.getDividerHeight() * (adapterCount - 1);
        }
        return height;
    }
     
    /**
     * @MethodName: getGridViewVerticalSpacing
     * @description: 
     * @author:  Mr.Lee
     * @param view view
     * @return int
     */
    public static int getGridViewVerticalSpacing(GridView view) {
        // get mVerticalSpacing by android.widget.GridView
        Class<?> demo = null;
        int verticalSpacing = 0;
        try {
            demo = Class.forName(CLASS_NAME_GRID_VIEW);
            Field field = demo.getDeclaredField(FIELD_NAME_VERTICAL_SPACING);
            field.setAccessible(true);
            verticalSpacing = (Integer) field.get(view);
            return verticalSpacing;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return verticalSpacing;
    }

    /**
     * @MethodName: 通过AbsListView中每个item子项的高度来计算AbsListView的高度
     * @description: 
     * @author:  Mr.Lee
     * @param view view
     * @return int
     */
    public static int getAbsListViewHeightBasedOnChildren(AbsListView view) {
        ListAdapter adapter;
        if (view == null || (adapter = view.getAdapter()) == null) {
            return 0;
        }

        int height = 0;
        for (int i = 0; i < adapter.getCount(); i++) {
            View item = adapter.getView(i, null, view);
            if (item instanceof ViewGroup) {
                item.setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT,
                        LayoutParams.WRAP_CONTENT));
            }
            item.measure(0, 0);
            height += item.getMeasuredHeight();
        }
        height += view.getPaddingTop() + view.getPaddingBottom();
        return height;
    }

    /**
     * @MethodName: setViewHeight
     * @description: 设置视图高度
     * @author:  Mr.Lee
     * @param view view
     * @param height height
     */
    public static void setViewHeight(View view, int height) {
        if (view == null) {
            return;
        }

        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.height = height;
    }

    /**
     * @MethodName: setListViewHeightBasedOnChildren
     * @description: 基于listview中每个item子项的高度来设置listview的高度
     * @author:  Mr.Lee
     * @param view listview
     */
    public static void setListViewHeightBasedOnChildren(ListView view) {
        setViewHeight(view, getListViewHeightBasedOnChildren(view));
    }

    /**
     * @MethodName: setAbsListViewHeightBasedOnChildren
     * @description: 设置absListview的高度
     * @author:  Mr.Lee
     * @param view AbsListView
     */
    public static void setAbsListViewHeightBasedOnChildren(AbsListView view) {
        setViewHeight(view, getAbsListViewHeightBasedOnChildren(view));
    }

    /**
     * @MethodName: setSearchViewOnClickListener
     * @description: 给SearchView注册点击监听事件
     * @author:  Mr.Lee
     * @param v
     * @param listener
     */
    public static void setSearchViewOnClickListener(View v, OnClickListener listener) {
        if (v instanceof ViewGroup) {
            ViewGroup group = (ViewGroup) v;
            int count = group.getChildCount();
            for (int i = 0; i < count; i++) {
                View child = group.getChildAt(i);
                if (child instanceof LinearLayout ||
                        child instanceof RelativeLayout) {
                    setSearchViewOnClickListener(child, listener);
                }

                if (child instanceof TextView) {
                    TextView text = (TextView) child;
                    text.setFocusable(false);
                }
                child.setOnClickListener(listener);
            }
        }
    }


    /**
     * @MethodName: getDescendants
     * @description: 从指定布局容器中返回特定类型视图的集合列表
     * @author:  Mr.Lee
     * @param <T> 泛型
     * @param parent 视图布局管理器
     * @param filter 返回视图的类型
     * @param includeSubClass 返回的视图集合中是否包含filter类型的子类型
     * @return 返回视力集合
     */
    public static <T extends View> List<T> getDescendants(ViewGroup parent, Class<T> filter, boolean includeSubClass) {

        List<T> descendedViewList = new ArrayList<T>();
        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            Class<? extends View> childsClass = child.getClass();
            if ((includeSubClass && filter.isAssignableFrom(childsClass)) ||
                    (!includeSubClass && childsClass == filter)) {
                descendedViewList.add(filter.cast(child));
            }
            if (child instanceof ViewGroup) {
                descendedViewList.addAll(
                        getDescendants((ViewGroup) child, filter,
                                includeSubClass));
            }
        }
        return descendedViewList;
    }


    /**
     * @MethodName: measureView
     * @description: 手动测量布局大小
     * @author:  Mr.Lee
     * @param view 被测量的布局
     * @param width 布局默认宽度
     * @param height 布局默认高度
     * 示例： measureView(view, ViewGroup.LayoutParams.MATCH_PARENT,
     * ViewGroup.LayoutParams.WRAP_CONTENT);
     */
    public static void measureView(View view, int width, int height) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        if (params == null) {
            params = new ViewGroup.LayoutParams(width, height);
        }
        int mWidth = ViewGroup.getChildMeasureSpec(0, 0, params.width);

        int mHeight;
        int tempHeight = params.height;
        if (tempHeight > 0) {
            mHeight = View.MeasureSpec.makeMeasureSpec(tempHeight,
                    View.MeasureSpec.EXACTLY);
        }
        else {
            mHeight = View.MeasureSpec.makeMeasureSpec(0,
                    View.MeasureSpec.UNSPECIFIED);
        }
        view.measure(mWidth, mHeight);
    }

    /**
     * @MethodName: setMarginLeft
     * @description: 设置View的左侧外边距
     * @author:  Mr.Lee
     * @param view 要设置外边距的View
     * @param left 左侧外边距
     */
    public static void setMarginLeft(View view, int left) {
        setMargins(view, left, 0, 0, 0);
    }

    /**
     * @MethodName: setMarginTop
     * @description: 设置View的顶部外边距
     * @author:  Mr.Lee
     * @param view 要设置外边距的View
     * @param top 顶部外边距
     */
    public static void setMarginTop(View view, int top) {
        setMargins(view, 0, top, 0, 0);
    }
    
    /**
     * @MethodName: setMarginRight
     * @description: 设置View的右侧外边距
     * @author:  Mr.Lee
     * @param view 要设置外边距的View
     * @param right 右侧外边距
     */
    public static void setMarginRight(View view, int right) {
        setMargins(view, 0, 0, right, 0);
    }


    /**
     * @MethodName: setMarginBottom
     * @description: 设置View的底部外边距
     * @author:  Mr.Lee
     * @param view 要设置外边距的View
     * @param bottom 底部外边距
     */
    public static void setMarginBottom(View view, int bottom) {
        setMargins(view, 0, 0, 0, bottom);
    }

    /**
     * @MethodName: setMargins
     * @description: 设置View的外边距(Margins)
     * @author:  Mr.Lee
     * @param view 要设置外边距的View
     * @param left 左侧外边距
     * @param top 顶部外边距
     * @param right 右侧外边距
     * @param bottom 底部外边距
     */
    public static void setMargins(View view, int left, int top, int right, int bottom) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view
                    .getLayoutParams();
            p.setMargins(left, top, right, bottom);
            view.requestLayout();       //请求重绘
        }
    }

    /**
     * @MethodName: createLinearLayout
     * @description: 获取一个LinearLayout
     * @author:  Mr.Lee
     * @param context 上下文
     * @param orientation 流向
     * @param width 宽
     * @param height 高
     * @return LinearLayout
     *
     */
    public static LinearLayout createLinearLayout(Context context, int orientation, int width, int height) {
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(orientation);
        linearLayout.setLayoutParams(
                new LinearLayout.LayoutParams(width, height));
        return linearLayout;
    }

    /**
     * @MethodName: 获取一个LinearLayout
     * @description: createLinearLayout
     * @author:  Mr.Lee
     * @param context 上下文
     * @param orientation 流向
     * @param width 宽
     * @param height 高
     * @param weight 权重
     * @return LinearLayout
     */
    public static LinearLayout createLinearLayout(Context context, int orientation, int width, int height, int weight) {
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(orientation);
        linearLayout.setLayoutParams(
                new LinearLayout.LayoutParams(width, height, weight));
        return linearLayout;
    }



    /**
     * @MethodName: 根据ListView的所有子项的高度设置其高度
     * @description: setListViewHeightByAllChildrenViewHeight
     * @author:  Mr.Lee
     * @param listView
     */
    public static void setListViewHeightByAllChildrenViewHeight(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter != null) {
            int totalHeight = 0;
            for (int i = 0; i < listAdapter.getCount(); i++) {
                View listItem = listAdapter.getView(i, null, listView);
                listItem.measure(0, 0);
                totalHeight += listItem.getMeasuredHeight();
            }

            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalHeight + (listView.getDividerHeight() *
                    (listAdapter.getCount() - 1));
            ((ViewGroup.MarginLayoutParams) params).setMargins(10, 10, 10, 10);
            listView.setLayoutParams(params);
        }
    }

    /**
     * @MethodName: addViewHeight
     * @description: 将给定视图的高度增加一点
     * @author:  Mr.Lee
     * @param view 给定的视图
     * @param increasedAmount 增加多少
     */
    public static void addViewHeight(View view, int increasedAmount) {
        ViewGroup.LayoutParams headerLayoutParams
                = (ViewGroup.LayoutParams) view.getLayoutParams();
        headerLayoutParams.height += increasedAmount;
        view.setLayoutParams(headerLayoutParams);
    }

    /**
     * @MethodName: setViewWidth
     * @description: 设置给定视图的宽度
     * @author:  Mr.Lee
     * @param view 给定的视图
     * @param newWidth 新的宽度
     */
    public static void setViewWidth(View view, int newWidth) {
        ViewGroup.LayoutParams headerLayoutParams
                = (ViewGroup.LayoutParams) view.getLayoutParams();
        headerLayoutParams.width = newWidth;
        view.setLayoutParams(headerLayoutParams);
    }

    /**
     * @MethodName: addViewWidth
     * @description: 将给定视图的宽度增加一点
     * @author:  Mr.Lee
     * @param view 给定的视图
     * @param increasedAmount 增加多少
     */
    public static void addViewWidth(View view, int increasedAmount) {
        ViewGroup.LayoutParams headerLayoutParams
                = (ViewGroup.LayoutParams) view.getLayoutParams();
        headerLayoutParams.width += increasedAmount;
        view.setLayoutParams(headerLayoutParams);
    }

    /**
     * @MethodName: getLinearLayoutBottomMargin
     * @description: 获取流布局的底部外边距
     * @author:  Mr.Lee
     * @param linearLayout
     */
    public static int getLinearLayoutBottomMargin(LinearLayout linearLayout) {
        return ((LinearLayout.LayoutParams) linearLayout.getLayoutParams()).bottomMargin;
    }

    /**
     * @MethodName: setLinearLayoutBottomMargin
     * @description: 设置流布局的底部外边距
     * @author:  Mr.Lee
     * @param linearLayout
     * @param newBottomMargin 新设置的底部外边距的值
     */
    public static void setLinearLayoutBottomMargin(LinearLayout linearLayout, int newBottomMargin) {
        LinearLayout.LayoutParams lp
                = (LinearLayout.LayoutParams) linearLayout.getLayoutParams();
        lp.bottomMargin = newBottomMargin;
        linearLayout.setLayoutParams(lp);
    }


    /**
     * @MethodName: getLinearLayoutHiehgt
     * @description: 获取流布局的高度
     * @author:  Mr.Lee
     * @param linearLayout
     */
    public static int getLinearLayoutHiehgt(LinearLayout linearLayout) {
        return ((LinearLayout.LayoutParams) linearLayout.getLayoutParams()).height;
    }

    /**
     * @MethodName: setEditTextSelectionToEnd
     * @description: 设置输入框的光标到末尾
     * @author:  Mr.Lee
     * @param editText
     */
    public static final void setEditTextSelectionToEnd(EditText editText) {
        Editable editable = editText.getEditableText();
        Selection.setSelection((Spannable) editable,
                editable.toString().length());
    }

    /**
     * @MethodName: measure
     * @description:  执行测量，执行完成之后只需调用View的getMeasuredXXX()方法即可获取测量结果
     * @author:  Mr.Lee
     * @param view
     * @return
     */
    public static final View measure(View view) {
        ViewGroup.LayoutParams p = view.getLayoutParams();
        if (p == null) {
            p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0, p.width);
        int lpHeight = p.height;
        int childHeightSpec;
        if (lpHeight > 0) {
            childHeightSpec = View.MeasureSpec.makeMeasureSpec(lpHeight,
                    View.MeasureSpec.EXACTLY);
        }
        else {
            childHeightSpec = View.MeasureSpec.makeMeasureSpec(0,
                    View.MeasureSpec.UNSPECIFIED);
        }
        view.measure(childWidthSpec, childHeightSpec);
        return view;
    }

    /**
     * @MethodName: getMeasuredHeight
     * @description: 获取给定视图的测量高度
     * @author:  Mr.Lee
     * @param view
     */
    public static final int getMeasuredHeight(View view) {
        return measure(view).getMeasuredHeight();
    }

    /**
     * @MethodName: getMeasuredWidth
     * @description: 获取给定视图的测量宽度
     * @author:  Mr.Lee
     * @param view
     */
    public static final int getMeasuredWidth(View view) {
        return measure(view).getMeasuredWidth();
    }

    /**
     * @MethodName: getRelativeRect
     * @description: 获取视图1相对于视图2的位置，注意在屏幕上看起来视图1应该被视图2包含，但是视图1和视图并不一定是绝对的父子关系也可以是兄弟关系，只是一个大一个小而已
     * @author:  Mr.Lee
     * @param view1
     * @param view2
     * @return
     */
    public static final Rect getRelativeRect(View view1, View view2) {
        Rect childViewGlobalRect = new Rect();
        Rect parentViewGlobalRect = new Rect();
        view1.getGlobalVisibleRect(childViewGlobalRect);
        view2.getGlobalVisibleRect(parentViewGlobalRect);
        return new Rect(childViewGlobalRect.left - parentViewGlobalRect.left,
                childViewGlobalRect.top - parentViewGlobalRect.top,
                childViewGlobalRect.right - parentViewGlobalRect.left,
                childViewGlobalRect.bottom - parentViewGlobalRect.top);
    }


    /**
     * @MethodName: removeOnGlobalLayoutListener
     * @description: 删除监听器
     * @author:  Mr.Lee
     * @param viewTreeObserver
     * @param onGlobalLayoutListener
     */
    @SuppressWarnings("deprecation") @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public static final void removeOnGlobalLayoutListener(ViewTreeObserver viewTreeObserver, ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            viewTreeObserver.removeGlobalOnLayoutListener(
                    onGlobalLayoutListener);
        }
        else {
            viewTreeObserver.removeOnGlobalLayoutListener(
                    onGlobalLayoutListener);
        }
    }


    /**
     * @MethodName: zoomView
     * @description: 绽放视图
     * @author:  Mr.Lee
     * @param view
     * @param scaleX
     * @param scaleY
     * @param originalSize
     */
    public static void zoomView(View view, float scaleX, float scaleY, Point originalSize) {
        int width = (int) (originalSize.x * scaleX);
        int height = (int) (originalSize.y * scaleY);
        ViewGroup.LayoutParams viewGroupParams = view.getLayoutParams();
        if (viewGroupParams != null) {
            viewGroupParams.width = width;
            viewGroupParams.height = height;
        }
        else {
            viewGroupParams = new ViewGroup.LayoutParams(width, height);
        }
        view.setLayoutParams(viewGroupParams);
    }

    /**
     * @MethodName: zoomView
     * @description: 缩放视图
     * @author:  Mr.Lee
     * @param view
     * @param scaleX
     * @param scaleY
     */
    public static void zoomView(View view, float scaleX, float scaleY) {
        zoomView(view, scaleX, scaleY,
                new Point(view.getWidth(), view.getHeight()));
    }


    /**
     * @MethodName: zoomView
     * @description: 缩放视图
     * @author:  Mr.Lee
     * @param view
     * @param scale 缩放比例
     * @param originalSize
     */
    public static void zoomView(View view, float scale, Point originalSize) {
        zoomView(view, scale, scale, originalSize);
    }

    /**
     * @MethodName: zoomView
     * @description: 缩放视图
     * @author:  Mr.Lee
     * @param view
     * @param scale
     */
    public static void zoomView(View view, float scale) {
        zoomView(view, scale, scale,
                new Point(view.getWidth(), view.getHeight()));
    }
}
