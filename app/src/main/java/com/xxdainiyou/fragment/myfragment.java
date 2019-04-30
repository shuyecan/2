package com.xxdainiyou.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xxdainiyou.Activity.LoginActivity;
import com.xxdainiyou.Activity.UserInfoActivity;
import com.xxdainiyou.R;
import com.xxdainiyou.been.UserBeen;

import org.litepal.LitePal;

import java.util.List;

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

    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.user_info_referrer)
    LinearLayout userInfoReferrer;
    @BindView(R.id.user_loginout)
    Button userLoginout;
    UserBeen userBeen;
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
        List<UserBeen> userBeenList = LitePal.where("islogin = ?", "true").find(UserBeen.class);
         userBeen = userBeenList.get(0);
        username.setText(userBeenList.get(0).getUsername());
        phone.setText(userBeenList.get(0).getPhone());
    }


    @OnClick({R.id.user_info_referrer, R.id.user_loginout})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.user_info_referrer:
                intent= new Intent(getActivity(),UserInfoActivity.class);
                startActivityForResult(intent,200);
                break;
            case R.id.user_loginout:
                userBeen.setIslogin("");
                userBeen.save();
                intent= new Intent(getActivity(),LoginActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        List<UserBeen> userBeenList = LitePal.where("islogin = ?", "true").find(UserBeen.class);
        userBeen = userBeenList.get(0);
        username.setText(userBeenList.get(0).getUsername());
        phone.setText(userBeenList.get(0).getPhone());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        userBeen.setIslogin("");
        userBeen.save();
        Intent intent = new Intent(getActivity(),LoginActivity.class);
        startActivity(intent);
    }
}
