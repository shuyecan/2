package com.xxdainiyou;


import android.app.Application;


import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.xxdainiyou.been.Memorandbeen;

import org.litepal.LitePal;

import java.util.List;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(this);
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);
        LitePal.initialize(this);
        initdata();
    }

    private void initdata() {
        List<Memorandbeen> memorandbeen = LitePal.findAll(Memorandbeen.class);
        if(memorandbeen.size()==0){
            Memorandbeen memorandbeen1 = new Memorandbeen();
            memorandbeen1.setAddress("古隆中");
            memorandbeen1.setImg("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1557027837&di=237bcd1e198a6090fa836998635dad1f&imgtype=jpg&er=1&src=http%3A%2F%2Fwww.cnr.cn%2Fhubei%2Fzt%2Fzjelt%2Fjdt%2F20170504%2FW020170504533460424239.jpg");
            memorandbeen1.setContent(getResources().getString(R.string.glz));
            memorandbeen1.setJing(112.036198);
            memorandbeen1.setWeidu(31.996338);
            memorandbeen1.save();
            Memorandbeen memorandbeen2 = new Memorandbeen();
            memorandbeen2.setAddress("诸葛草庐亭");
            memorandbeen2.setImg("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=874965120,1345343421&fm=26&gp=0.jpg");
            memorandbeen2.setContent(getResources().getString(R.string.clt));
            memorandbeen2.setJing(112.038574);
            memorandbeen2.setWeidu(31.996495);
            memorandbeen2.save();

            Memorandbeen memorandbeen3 = new Memorandbeen();
            memorandbeen3.setAddress("三顾堂");
            memorandbeen3.setImg("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=4093678104,786047476&fm=26&gp=0.jpg");
            memorandbeen3.setContent(getResources().getString(R.string.sgt));
            memorandbeen3.setJing(112.038574);
            memorandbeen3.setWeidu(31.996495);
            memorandbeen3.save();

            Memorandbeen memorandbeen4 = new Memorandbeen();
            memorandbeen4.setAddress("卧龙深处");
            memorandbeen4.setImg("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=42536880,1806684648&fm=26&gp=0.jpg");
            memorandbeen4.setContent(getResources().getString(R.string.wlsc));
            memorandbeen4.setJing(112.037632);
            memorandbeen4.setWeidu(31.997386);
            memorandbeen4.save();
        }
    }


}
