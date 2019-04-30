package com.xxdainiyou.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xxdainiyou.Activity.NotActivity;
import com.xxdainiyou.Activity.ScenicspotresultsActivity;
import com.xxdainiyou.Activity.XiangqinActivity;
import com.xxdainiyou.R;
import com.xxdainiyou.been.Memorandbeen;

import org.litepal.LitePal;
import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class homefragmnet extends Fragment {

    View view;
    @BindView(R.id.lin_kaishi)
    LinearLayout linKaishi;
    @BindView(R.id.main_card_item1)
    CardView main_card_item1;
    @BindView(R.id.img_jd)
    ImageView img_jd;
    @BindView(R.id.text_jd_title)
    TextView text_jd_title;
    @BindView(R.id.main_card_item2)
    CardView main_card_item2;
    @BindView(R.id.img_jd2)
    ImageView img_jd2;
    @BindView(R.id.text_jd_title2)
    TextView text_jd_title2;
    @BindView(R.id.lin_xingchen)
    LinearLayout lin_xingchen;
    @BindView(R.id.lin_gonglue)
    LinearLayout lin_gonglue;
    @BindView(R.id.lin_tianqi)
    LinearLayout lin_tianqi;
    List<Memorandbeen> memorandbeens;
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
         memorandbeens = LitePal.findAll(Memorandbeen.class);
        Glide.with(getActivity()).load(memorandbeens.get(0).getImg()).into(img_jd);
        Glide.with(getActivity()).load(memorandbeens.get(1).getImg()).into(img_jd2);
        text_jd_title.setText(memorandbeens.get(0).getAddress());
        text_jd_title2.setText(memorandbeens.get(1).getAddress());
    }


    @OnClick({ R.id.lin_kaishi,R.id.main_card_item2,R.id.main_card_item1,R.id.lin_xingchen,R.id.lin_gonglue,R.id.lin_tianqi})
    public void onViewClicked(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.lin_kaishi:
                intent = new Intent(getActivity(),ScenicspotresultsActivity.class);
                startActivity(intent);
                break;
            case R.id.main_card_item1:
                intent = new Intent(getActivity(),XiangqinActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("memora",memorandbeens.get(0));
                intent.putExtras(bundle);
                startActivity(intent);
                break;
            case R.id.main_card_item2:
                intent = new Intent(getActivity(),XiangqinActivity.class);
                Bundle bundle2 = new Bundle();
                bundle2.putSerializable("memora",memorandbeens.get(1));
                intent.putExtras(bundle2);
                startActivity(intent);
                break;
            case R.id.lin_xingchen:
                intent = new Intent(getActivity(),NotActivity.class);
                startActivity(intent);
                break;

            case R.id.lin_gonglue:
                intent = new Intent(getActivity(),NotActivity.class);
                startActivity(intent);
                break;
            case   R.id.lin_tianqi:
                intent = new Intent(getActivity(),NotActivity.class);
                startActivity(intent);
                break;
        }
    }
}
