package com.xxdainiyou.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xxdainiyou.Activity.LoginActivity;
import com.xxdainiyou.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class myfragment extends Fragment {
    View view;
    @BindView(R.id.driver_header)
    ImageView driverHeader;
    @BindView(R.id.username)
    TextView username;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.my_activity, null);
            ButterKnife.bind(this, view);
            initview();
        } else {
            ViewGroup parent = (ViewGroup) view.getParent();
            if (parent != null) {
                parent.removeView(view);
            }
        }

        return view;
    }

    private void initview() {

    }


    @OnClick({R.id.driver_header, R.id.username})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.driver_header:
                break;
            case R.id.username:
                Intent intent =new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
                break;
        }
    }
}
