package com.xxdainiyou.Activity;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xxdainiyou.R;
import com.xxdainiyou.been.Memorandbeen;

import butterknife.BindView;
import butterknife.ButterKnife;

public class XiangqinActivity extends AppCompatActivity {

    Memorandbeen memorandbeen;
    @BindView(R.id.text_xiangq)
    TextView textXiangq;
    @BindView(R.id.img_xinagqing)
    ImageView imgXinagqing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiangqin);
        ButterKnife.bind(this);
        memorandbeen = (Memorandbeen) getIntent().getSerializableExtra("memora");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(memorandbeen.getAddress());
        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        assert ab != null;
        ab.setDisplayHomeAsUpEnabled(true);
        textXiangq.setText(memorandbeen.getContent());
        Glide.with(getApplicationContext()).load(memorandbeen.getImg()).into(imgXinagqing);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }
}
