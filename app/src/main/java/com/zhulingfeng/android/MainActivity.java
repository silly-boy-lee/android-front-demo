package com.zhulingfeng.android;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhulingfeng.android.base.BaseActivity;
import com.zhulingfeng.android.util.BadgeUtil;
import com.zhulingfeng.android.util.LogUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    public static final String TAG = "MainActivity";


    @BindView(R.id.start)
    public Button startBtn;
    @BindView(R.id.stop)
    public Button stopBtn;
    @BindView(R.id.img)
    public ImageView img;
    @BindView(R.id.resp_txt)
    public TextView restTxt;

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }



    @OnClick({R.id.start,R.id.stop})
    public void onClick(View view){
        try{
            switch (view.getId()){
                case R.id.start:
                    install();
                    break;
                case R.id.stop:
                    unInstall();
                    break;
                default:
                    break;
            }
        }catch (Exception e){
            LogUtil.e(e.getMessage(),true);
        }

    }

    private void install(){
        BadgeUtil.setBadgeCount(this,7,R.mipmap.ic_launcher);
    }

    private void unInstall(){

    }

}
