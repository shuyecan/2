package com.xxdainiyou.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.widget.ImageView;
import android.widget.Toast;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.walknavi.WalkNavigateHelper;
import com.baidu.mapapi.walknavi.adapter.IWEngineInitListener;
import com.baidu.mapapi.walknavi.adapter.IWRoutePlanListener;
import com.baidu.mapapi.walknavi.model.WalkRoutePlanError;
import com.baidu.mapapi.walknavi.params.WalkNaviLaunchParam;
import com.xxdainiyou.R;
import com.xxdainiyou.apther.Memoraapther;
import com.xxdainiyou.been.Memorandbeen;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScenicspotresultsActivity extends AppCompatActivity {

    @BindView(R.id.search_query_byname)
    SearchView searchQueryByname;
    @BindView(R.id.rec_jingdian)
    RecyclerView recJingdian;
    List<Memorandbeen> list = new ArrayList<>();
    Memoraapther memoraapther;
    LatLng startPt;
    LatLng endPt;
    WalkNaviLaunchParam mParam;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenicspotresults);
        ButterKnife.bind(this);
        initview();
        initdata();
    }

    private void initview() {
        WalkNavigateHelper.getInstance().initNaviEngine(this, new IWEngineInitListener() {

            @Override
            public void engineInitSuccess() {
                //引擎初始化成功的回调
//                routeWalkPlanWithParam();
            }

            @Override
            public void engineInitFail() {
                //引擎初始化失败的回调
            }
        });

    }

    private void initdata() {
        searchQueryByname.setIconifiedByDefault(false);
        searchQueryByname.setSubmitButtonEnabled(true);
        ImageView iv_submit = searchQueryByname.findViewById(R.id.search_go_btn);
        iv_submit.setImageResource(R.drawable.ic_check_black_24dp);
        List<Memorandbeen> memorandbeens = LitePal.findAll(Memorandbeen.class);
        list.addAll(memorandbeens);
        memoraapther = new Memoraapther(list,getApplicationContext());
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recJingdian.setLayoutManager(layoutManager);
        recJingdian.setAdapter(memoraapther);
    }


}
