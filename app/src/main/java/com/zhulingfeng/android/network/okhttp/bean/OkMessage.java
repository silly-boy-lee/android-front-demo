package com.zhulingfeng.android.network.okhttp.bean;


import android.os.Message;

import java.io.Serializable;

/**
 * @ClassName: OkMessage
 * @description: Handler信息体基类
 * @author:  Mr.Lee
 */
public class OkMessage implements Serializable {

    public int what;

    public Message build(){
        Message msg = new Message();
        msg.what = this.what;
        msg.obj = this;
        return msg;
    }

}
