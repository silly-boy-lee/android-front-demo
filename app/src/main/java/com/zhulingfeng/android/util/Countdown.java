package com.zhulingfeng.android.util;

import android.os.Handler;
import android.widget.TextView;

/**
 * @ClassName: Countdown
 * @description: 倒计时器
 * @author:  Mr.Lee
 */
@SuppressWarnings({"unused"})
public class Countdown implements Runnable {
	/**
	 * @FieldName:
	 * @description:
	 */
	private int remainingSeconds;
	/**
	 * @FieldName:
	 * @description:
	 */
	private int currentRemainingSeconds;
	/**
	 * @FieldName:
	 * @description:
	 */
    private boolean running;
	/**
	 * @FieldName:
	 * @description:
	 */
	private String defaultText;
	/**
	 * @FieldName:
	 * @description:
	 */
	private String countdownText;
	/**
	 * @FieldName:
	 * @description:
	 */
	private TextView showTextView;
	/**
	 * @FieldName:
	 * @description:
	 */
	private Handler handler;
	/**
	 * @FieldName:
	 * @description:
	 */
	private CountdownListener countdownListener;
	/**
	 * @FieldName:
	 * @description:
	 */
    private TextViewGetListener textViewGetListener;

	/**
	 * @MethodName: Countdown
	 * @description: 创建一个倒计时器
	 * @author:  Mr.Lee
	 * @param showTextView 显示倒计时的文本视图
	 * @param countdownText 倒计时中显示的内容，例如："%s秒后重新获取验证码"，在倒计时的过程中会用剩余描述替换%s
	 * @param remainingSeconds 倒计时秒数，例如：60，就是从60开始倒计时一直到0结束
	 */
    public Countdown(TextView showTextView, String countdownText, int remainingSeconds){
        this.showTextView = showTextView;
        this.countdownText = countdownText;
        this.remainingSeconds = remainingSeconds;
        this.handler = new Handler();
    }

	/**
	 * @MethodName: Countdown
	 * @description: 创建一个倒计时器
	 * @author:  Mr.Lee
	 * @param textViewGetListener 显示倒计时的文本视图获取监听器
	 * @param countdownText 倒计时中显示的内容，例如："%s秒后重新获取验证码"，在倒计时的过程中会用剩余描述替换%s
	 * @param remainingSeconds 倒计时秒数，例如：60，就是从60开始倒计时一直到0结束
	 */
    public Countdown(TextViewGetListener textViewGetListener, String countdownText, int remainingSeconds){
        this.textViewGetListener = textViewGetListener;
        this.countdownText = countdownText;
        this.remainingSeconds = remainingSeconds;
        this.handler = new Handler();
    }

	/**
	 * @MethodName: Countdown
	 * @description: 创建一个倒计时器，默认60秒
	 * @author:  Mr.Lee
	 * @param showTextView 显示倒计时的文本视图
	 * @param countdownText 倒计时中显示的内容，例如："%s秒后重新获取验证码"，在倒计时的过程中会用剩余描述替换%s
	 */
	public Countdown(TextView showTextView, String countdownText){
		this(showTextView, countdownText, 60);
	}

	/**
	 * @MethodName:
	 * @description: 创建一个倒计时器，默认60秒
	 * @author:  Mr.Lee
	 * @param textViewGetListener 显示倒计时的文本视图获取监听器
	 * @param countdownText 倒计时中显示的内容，例如："%s秒后重新获取验证码"，在倒计时的过程中会用剩余描述替换%s
	 */
    public Countdown(TextViewGetListener textViewGetListener, String countdownText){
        this(textViewGetListener, countdownText, 60);
    }

	/**
	 * @MethodName: getShowTextView
	 * @description: 获取显示的TextView
	 * @author:  Mr.Lee
	 */
    private TextView getShowTextView(){
        if(showTextView != null){
            return showTextView;
        }

        if(textViewGetListener != null){
            return textViewGetListener.OnGetShowTextView();
        }

        return null;
    }
	
	@Override
	public void run() {
		if(currentRemainingSeconds > 0){
            getShowTextView().setEnabled(false);
            getShowTextView().setText(
					String.format(countdownText, currentRemainingSeconds));
            if(countdownListener != null){
                countdownListener.onUpdate(currentRemainingSeconds);
            }
            currentRemainingSeconds--;
            handler.postDelayed(this, 1000);
		}else{
			stop();
		}
	}

	/**
	 * @MethodName:
	 * @description:
	 * @author:  Mr.Lee
	 */
	public void start(){
        defaultText = (String) getShowTextView().getText();
		currentRemainingSeconds = remainingSeconds;
        handler.removeCallbacks(this);
		handler.post(this);
		if(countdownListener != null){
			countdownListener.onStart();
		}
        running = true;
	}

	/**
	 * @MethodName:
	 * @description:
	 * @author:  Mr.Lee
	 */
	public void stop(){
		getShowTextView().setEnabled(true);
		getShowTextView().setText(defaultText);
		handler.removeCallbacks(this);
		if(countdownListener != null){
			countdownListener.onFinish();
		}
        running = false;
	}

    public boolean isRunning() {
        return running;
    }

    public int getRemainingSeconds() {
		return remainingSeconds;
	}

	public String getCountdownText() {
		return countdownText;
	}

	public void setCountdownText(String countdownText) {
		this.countdownText = countdownText;
	}

	public void setCurrentRemainingSeconds(int currentRemainingSeconds) {
		this.currentRemainingSeconds = currentRemainingSeconds;
	}

	public void setCountdownListener(CountdownListener countdownListener) {
		this.countdownListener = countdownListener;
	}

	/**
	 * @ClassName: CountdownListener
	 * @description: 倒计时监听器
	 * @author:  Mr.Lee
	 */
	public interface CountdownListener{
		/**
		 * 当倒计时开始
		 */
		void onStart();

        /**
		 * 当倒计时结束
		 */
		void onFinish();

        /**
         * 更新
         * @param currentRemainingSeconds 剩余时间
         */
		void onUpdate(int currentRemainingSeconds);
	}

	/**
	 * @ClassName: TextViewGetListener
	 * @description:
	 * @author:  Mr.Lee
	 */
    public interface TextViewGetListener{
        public TextView OnGetShowTextView();
    }
}
