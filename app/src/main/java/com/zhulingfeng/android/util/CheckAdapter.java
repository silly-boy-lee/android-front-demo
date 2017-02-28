package com.zhulingfeng.android.util;

import android.view.View;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * @ClassName: CheckAdapter
 * @description: 列表选择适配器
 * <h2>选择适配器</h2>
 * <br>准备工作：
 * <br>第一步：创建你的Item并实现CheckAdapter.CheckItem接口
 * <br>第二步：创建你的适配器并继承于CheckAdapter并同时通过泛型指定Item类型
 * <br>第三步：在构造函数中传给父类Item集合
 * <br>第四步：在getView()方法中调用handleCheckBox()方法处理CheckBox
 * <br>第五步：在ListView的OnItemClickListener中调用clickIitem()方法处理点击事件
 * <br>至此，全部准备工作已经完结
 * <br>
 * <br>接下来你可以：
 * <br>调用setSingleMode()方法修改选择模式为单选模式（默认是多选模式）
 * <br>调用enableCheckMode()方法开启选择模式
 * <br>调用cancelCheckMode()方法取消选择模式
 * <br>调用isEnableCheckMode()方法判断是否正处于选择模式
 * <br>调用getCheckedItems()方法获取全部选中的项
 * <br>调用deleteCheckedItems()方法删除全部选中的项
 * @author:  Mr.Lee
 */
@SuppressWarnings({"unused"})
public abstract class CheckAdapter<T extends CheckAdapter.CheckItem> extends BaseAdapter {
	/**
	 * @FieldName: items
	 * @description: 列表项
	 */
	private List<T> items;
	/**
	 * @FieldName: enabledCheckMode
	 * @description: 激活选择模式
	 */
	private boolean enabledCheckMode;
    /**
     * @FieldName: singleMode
     * @description: 单选模式
     */
	private boolean singleMode;
    /**
     * @FieldName: currentCheckedPosition
     * @description: 当前选择位置
     */
	private int currentCheckedPosition = -1;

    /**
     * @FunctionName: CheckAdapter
     * @description: 构造方法
     * @author:  Mr.Lee
     */
	public CheckAdapter(List<T> items) {
		this.items = items;
	}

    /**
     * @FunctionName: getCount
     * @description: 返回列表项的长度
     * @author:  Mr.Lee
     */
	@Override
	public int getCount() {
		return items != null ? items.size() : 0;
	}

    /**
     * @FunctionName: getItem
     * @description: 取指定列表项
     * @author:  Mr.Lee
     * @param position 列表项索引
     * @return
     */
	@Override
	public Object getItem(int position) {
		return items.get(position);
	}

    /**
     * @FunctionName: getItemId
     * @description: 取指定列表项索引id
     * @author:  Mr.Lee
     * @param position
     * @return
     */
	@Override
	public long getItemId(int position) {
		return position;
	}

    /**
     * @FunctionName: getItems
     * @description: 返回指定列表项
     * @author:  Mr.Lee
     * @return
     */
	public List<T> getItems() {
		return items;
	}

    /**
     * @FunctionName:
     * @description:
     * @author:  Mr.Lee
     */
	public void setItems(List<T> items) {
		this.items = items;
		notifyDataSetChanged();
	}
	
    /**
     * @FunctionName: isSingleMode
     * @description: 是否是单选模式
     * @author:  Mr.Lee
     * @return
     */
	public boolean isSingleMode() {
		return singleMode;
	}

    /**
     * @FunctionName: setSingleMode
     * @description: 设置单选模式，默认是复选模式
     * @author:  Mr.Lee
     * @param singleMode
     */
	public void setSingleMode(boolean singleMode) {
		this.singleMode = singleMode;
	}


    /**
     * @FunctionName: handleCompoundButton
     * @description: 处理复选框
     * @author:  Mr.Lee
     * @param compoundButton
     * @param t
     */
	public void handleCompoundButton(CompoundButton compoundButton, T t){
		compoundButton.setChecked(t.isChecked());
		compoundButton.setVisibility(isEnabledCheckMode() ? View.VISIBLE : View.GONE);
	}
	

    /**
     * @FunctionName: enableCheckMode
     * @description: 激活选择模式
     * @author:  Mr.Lee
     */
	public void enableCheckMode(){
		if(enabledCheckMode){
			return;
		}
		enabledCheckMode = true;
		notifyDataSetChanged();
	}


    /**
     * @FunctionName: cancelCheckMode
     * @description: 取消选择模式
     * @author:  Mr.Lee
     */
	public void cancelCheckMode(){
		if(!enabledCheckMode){
			return;
		}
		enabledCheckMode = false;
		for(T item : items){
			item.setChecked(false);
		}
		notifyDataSetChanged();
	}
	

    /**
     * @FunctionName: isEnabledCheckMode
     * @description: 判定是否已激活选择模式
     * @author:  Mr.Lee
     * @return
     */
	public boolean isEnabledCheckMode() {
		return enabledCheckMode;
	}
	
    /**
     * @FunctionName: clickItem
     * @description:点击了某一项
     * @author:  Mr.Lee
     * @param postion
     * @return true：已经激活了选择模式并且设置成功；false：尚未激活选择模式并且设置失败
     */
	public boolean clickItem(int postion){
		if(isEnabledCheckMode()){
			if(postion < items.size()){
				if(singleMode){
					if(currentCheckedPosition == -1 || currentCheckedPosition == postion){
						T item = items.get(postion); 
						item.setChecked(!item.isChecked());
					}else{
						items.get(currentCheckedPosition).setChecked(false);
						items.get(postion).setChecked(true);
					}
					currentCheckedPosition = postion;
				}else{
					T item = items.get(postion); 
					item.setChecked(!item.isChecked());
				}
				notifyDataSetChanged();
			}
			return true;
		}else{
			return false;
		}
	}
	
    /**
     * @FunctionName: getCheckedItems
     * @description: 获取选中的项
     * @author:  Mr.Lee
     * @return
     */
	public List<T> getCheckedItems(){
		List<T> checkedItems = new ArrayList<T>();
		for(T item : items){
			if(item.isChecked()){
				checkedItems.add(item);
			}
		}
		return checkedItems;
	}

    /**
     * @FunctionName: deleteCheckedItems
     * @description: 删除选中的项
     * @author:  Mr.Lee
     * @return
     */
	public List<T> deleteCheckedItems(){
		List<T> checkedItems = new ArrayList<T>();
		Iterator<T> iterator = items.iterator();
		T item;
		while(iterator.hasNext()){
			item = iterator.next();
			if(item.isChecked()){
				checkedItems.add(item);
				iterator.remove();
			}
		}
		notifyDataSetChanged();
		return checkedItems;
	}

    /**
     * @ClassName: CheckItem
     * @description: 列表选择适配器内部接口
     * @author:  Mr.Lee
     */
	public interface CheckItem {
        boolean isChecked();
        void setChecked(boolean checked);
	}
}
