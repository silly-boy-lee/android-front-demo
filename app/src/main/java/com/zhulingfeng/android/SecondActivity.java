package com.zhulingfeng.android;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.zhulingfeng.android.base.BaseActivity;
import com.zhulingfeng.android.util.LogUtil;

import butterknife.BindView;

public class SecondActivity extends BaseActivity {



    @BindView(R.id.txt)
    public  TextView txt;

    @Override
    protected int initLayout() {
       return R.layout.activity_second;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Toast.makeText(this, getIntent().getStringExtra("msg"), Toast.LENGTH_SHORT).show();
        LogUtil.d(getIntent().getStringExtra("msg"),true);
        txt.setText(getIntent().getStringExtra("msg"));
    }


}
