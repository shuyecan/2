package com.xxdainiyou.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;

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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenicspotresults);
        ButterKnife.bind(this);
        initdata();
    }

    private void initdata() {
        List<Memorandbeen> memorandbeens = LitePal.findAll(Memorandbeen.class);
        list.addAll(memorandbeens);
        memoraapther = new Memoraapther(list,getApplicationContext());
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recJingdian.setLayoutManager(layoutManager);
        recJingdian.setAdapter(memoraapther);
    }
}
