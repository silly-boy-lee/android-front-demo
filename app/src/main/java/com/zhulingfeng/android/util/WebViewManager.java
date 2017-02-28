package com.zhulingfeng.android.util;

import android.annotation.SuppressLint;
import android.webkit.WebSettings;
import android.webkit.WebView;


/**
 * @ClassName: WebViewManager
 * @description: WebView管理器，提供常用设置
 * @author:  Mr.Lee
 */
@SuppressWarnings({"unused"})
public class WebViewManager {

    /**
     * @FieldName: webView
     * @description: WebView对象
     */
	private WebView webView;
    /**
     * @FieldName: webSettings
     * @description: WebSettings对象
     */
	private WebSettings webSettings;

    /**
     * @MethodName: WebViewManager
     * @description: 构造方法
     * @author:  Mr.Lee
     * @param webView
     */
	public WebViewManager(WebView webView){
        this.webView = webView;
        webSettings = webView.getSettings();
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
	}

    /**
     * @MethodName: enableAdaptive
     * @description: 开启自适应功能
     * @author:  Mr.Lee
     */
    public WebViewManager enableAdaptive(){
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        return this;
    }

    /**
     * @MethodName: disableAdaptive
     * @description: 禁用自适应功能
     * @author:  Mr.Lee
     */
    public WebViewManager disableAdaptive(){
        webSettings.setUseWideViewPort(true);
        webSettings.setLoadWithOverviewMode(true);
        return this;
    }

    /**
     * @MethodName: enableZoom
     * @description: 开启缩放功能
     * @author:  Mr.Lee
     */
    public WebViewManager enableZoom(){
        webSettings.setSupportZoom(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setBuiltInZoomControls(true);
        return this;
    }

    /**
     * @MethodName: disableZoom
     * @description: 禁用缩放功能
     * @author:  Mr.Lee
     */
    public WebViewManager disableZoom(){
        webSettings.setSupportZoom(false);
        webSettings.setUseWideViewPort(false);
        webSettings.setBuiltInZoomControls(false);
        return this;
    }

    /**
     * @MethodName: enableJavaScript
     * @description: 开启JavaScript
     * @author:  Mr.Lee
     */
    @SuppressLint("SetJavaScriptEnabled")
    public WebViewManager enableJavaScript(){
        webSettings.setJavaScriptEnabled(true);
        return this;
    }

    /**
     * @MethodName: disableJavaScript
     * @description: 禁用JavaScript
     * @author:  Mr.Lee
     */
    public WebViewManager disableJavaScript(){
        webSettings.setJavaScriptEnabled(false);
        return this;
    }

    /**
     * @MethodName: enableJavaScriptOpenWindowsAutomatically
     * @description: 开启JavaScript自动弹窗
     * @author:  Mr.Lee
     */
    public WebViewManager enableJavaScriptOpenWindowsAutomatically(){
    	webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
    	return this;
    }

    /**
     * @MethodName: disableJavaScriptOpenWindowsAutomatically
     * @description: 禁用JavaScript自动弹窗
     * @author:  Mr.Lee
     */
    public WebViewManager disableJavaScriptOpenWindowsAutomatically(){
    	webSettings.setJavaScriptCanOpenWindowsAutomatically(false);
    	return this;
    }

    /**
     * @MethodName: goBack
     * @description: 返回
     * @author:  Mr.Lee
     * @return true：已经返回，false：到头了没法返回了
     */
	public boolean goBack(){
		if(webView.canGoBack()){
			webView.goBack();
			return true;
		}else{
			return false;
		}
	}
}