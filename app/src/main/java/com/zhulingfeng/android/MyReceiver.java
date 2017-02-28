package com.zhulingfeng.android;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.zhulingfeng.android.util.LogUtil;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String msg = intent.getStringExtra("msg");
        LogUtil.e(msg,true);
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
