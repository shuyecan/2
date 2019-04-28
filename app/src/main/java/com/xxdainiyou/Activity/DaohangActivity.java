package com.xxdainiyou.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.baidu.mapapi.map.MapView;
import com.xxdainiyou.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DaohangActivity extends AppCompatActivity {

    @BindView(R.id.bmapView)
    MapView bmapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daohang);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {

    }
}
