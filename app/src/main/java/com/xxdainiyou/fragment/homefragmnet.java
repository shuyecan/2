package com.xxdainiyou.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.xxdainiyou.Activity.ScenicspotresultsActivity;
import com.xxdainiyou.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class homefragmnet extends Fragment {

    View view;
    @BindView(R.id.driver_info)
    LinearLayout driverInfo;
    @BindView(R.id.lin_kaishi)
    LinearLayout linKaishi;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.home_activity, null);
            initdata();
        } else {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
        }

        return view;
    }

    private void initdata() {
        ButterKnife.bind(this, view);
    }


    @OnClick({R.id.driver_info, R.id.lin_kaishi})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.driver_info:
                break;
            case R.id.lin_kaishi:
                intent = new Intent(getActivity(),ScenicspotresultsActivity.class);
                startActivity(intent);
                break;
        }
    }
}
