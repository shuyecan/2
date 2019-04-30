package com.xxdainiyou;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.xxdainiyou.fragment.homefragmnet;
import com.xxdainiyou.fragment.mudidifragmnet;
import com.xxdainiyou.fragment.myfragment;
import com.xxdainiyou.fragment.quanzifragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.mian_viewpage)
    ViewPager mianViewpage;
    List<Fragment> fragments =new ArrayList<>();
    BottomNavigationView navigation;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.home:
                    mianViewpage.setCurrentItem(0,false);
                    return true;
                case R.id.mudidi:
                    mianViewpage.setCurrentItem(1,false);
                    return true;
                case R.id.quanzhi:
                    mianViewpage.setCurrentItem(2,false);
                    return true;
                case R.id.my:
                    mianViewpage.setCurrentItem(3,false);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initview();
         navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void initview() {
        inittooler();
        fragments.add(new homefragmnet());
        fragments.add(new mudidifragmnet());
        fragments.add(new quanzifragment());
        fragments.add(new myfragment());
        mianViewpage.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragments.get(i);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        });

        mianViewpage.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                navigation.getMenu().getItem(i).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void inittooler() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("襄襄旅游");
        setSupportActionBar(toolbar);
    }

}
