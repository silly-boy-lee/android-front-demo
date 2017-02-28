package com.zhulingfeng.android.util;

import android.os.Handler;

/**
 * @ClassName: LoopTimer
 * @description: 循环定时器
 * @author:  Mr.Lee
 */
@SuppressWarnings({"unused"})
public class LoopTimer {
	/**
	 * @FieldName:intervalMillis
	 * @description:间隔时间
	 */
	private int intervalMillis;
	/**
	 * @FieldName: running
	 * @description: 运行状态
	 */
	private boolean running;
	/**
	 * @FieldName: handler
	 * @description: 消息处理器
	 */
	private Handler handler;
	/**
	 * @FieldName: executeRunnable
	 * @description: 执行Runnable
	 */
	private ExecuteRunnable executeRunnable;

	/**
	 * @MethodName: LoopTimer
	 * @description: 创建一个循环定时器
	 * @author:  Mr.Lee
	 * @param handler
	 * @param runnable
	 * @param intervalMillis
	 */
	public LoopTimer(Handler handler, Runnable runnable, int intervalMillis) {
		this.handler = handler;
		this.intervalMillis = intervalMillis;
		executeRunnable = new ExecuteRunnable(runnable);
	}

	/**
	 * @MethodName: LoopTimer
	 * @description: 创建一个循环定时器
	 * @author:  Mr.Lee
	 * @param runnable
	 * @param intervalMillis
	 */
	public LoopTimer(Runnable runnable, int intervalMillis) {
		this(new Handler(), runnable, intervalMillis);
	}

	/**
	 * @MethodName: start
	 * @description: 立即启动循环定时器
	 * @author:  Mr.Lee
	 */
	public void start(){
		running = true;
		handler.removeCallbacks(executeRunnable);
		handler.post(executeRunnable);
	}

	/**
	 * @MethodName: delayStart
	 * @description: 延迟指定间隔毫秒启动
	 * @author:  Mr.Lee
	 */
	public void delayStart(){
		running = true;
		handler.removeCallbacks(executeRunnable);
		handler.postDelayed(executeRunnable, intervalMillis);
	}

	/**
	 * @MethodName: stop
	 * @description: 立即停止
	 * @author:  Mr.Lee
	 */
	public void stop(){
		running = false;
		handler.removeCallbacks(executeRunnable);
	}

	/**
	 * @MethodName: getIntervalMillis
	 * @description: 获取间隔毫秒
	 * @author:  Mr.Lee
	 * @return
	 */
	public int getIntervalMillis() {
		return intervalMillis;
	}

	/**
	 * @MethodName: setIntervalMillis
	 * @description: 设置间隔毫秒
	 * @author:  Mr.Lee
	 * @param intervalMillis
	 */
	public void setIntervalMillis(int intervalMillis) {
		this.intervalMillis = intervalMillis;
	}

	/**
	 * @MethodName: isRunning
	 * @description: 检测定时器是否正在运行
	 * @author:  Mr.Lee
	 * @return
	 */
	public boolean isRunning() {
		return running;
	}

	/**
	 * @MethodName: setHandler
	 * @description: 设置消息处理器
	 * @author:  Mr.Lee
	 * @param handler
	 */
	public void setHandler(Handler handler) {
		this.handler = handler;
	}

	/**
	 * @MethodName: setRunnable
	 * @description: 设置执行内容
	 * @author:  Mr.Lee
	 * @param runnable
	 */
	public void setRunnable(Runnable runnable) {
		executeRunnable.setRunnable(runnable);
	}

	/**
	 * @MethodName: ExecuteRunnable
	 * @description: 执行Runnable
	 * @author:  Mr.Lee
	 */
	private class ExecuteRunnable implements Runnable {
		private Runnable runnable;
		
		public ExecuteRunnable(Runnable runnable){
			this.runnable = runnable;
		}
		
		@Override
		public void run() {
			if(running && runnable != null){
				runnable.run();
			}
		}

		public void setRunnable(Runnable runnable) {
			this.runnable = runnable;
		}
	}
}
