package com.xxdainiyou.Activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.sug.OnGetSuggestionResultListener;
import com.baidu.mapapi.search.sug.SuggestionResult;
import com.baidu.mapapi.search.sug.SuggestionSearch;
import com.baidu.mapapi.search.sug.SuggestionSearchOption;
import com.baidu.mapapi.walknavi.WalkNavigateHelper;
import com.baidu.mapapi.walknavi.adapter.IWEngineInitListener;
import com.baidu.mapapi.walknavi.adapter.IWRoutePlanListener;
import com.baidu.mapapi.walknavi.model.WalkRoutePlanError;
import com.baidu.mapapi.walknavi.params.WalkNaviLaunchParam;
import com.baidu.mapapi.walknavi.params.WalkRouteNodeInfo;
import com.xxdainiyou.R;
import com.xxdainiyou.apther.Memoraapther;
import com.xxdainiyou.apther.QuerybynameAdapter;
import com.xxdainiyou.been.Memorandbeen;
import com.xxdainiyou.been.Mylocation;

import org.litepal.LitePal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.view.Gravity.BOTTOM;

public class ScenicspotresultsActivity extends AppCompatActivity {

    @BindView(R.id.search_query_byname)
    SearchView searchQueryByname;
    @BindView(R.id.rec_jingdian)
    RecyclerView recJingdian;
    List<Memorandbeen> list = new ArrayList<>();
    Memoraapther memoraapther;
    LatLng startPt;
    LatLng endPt;
    PopupWindow window;
    RecyclerView recyclerView;
    MediaPlayer mediaPlayer;
    double chaju = 0;
    List<Memorandbeen> resultlist = new ArrayList<>();
    QuerybynameAdapter adapter;
    private WalkNaviLaunchParam walkParam;
    private SuggestionSearch mSuggestionSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenicspotresults);
        ButterKnife.bind(this);
        initpop();
        initdata();
    }



    private void initdata() {
        mSuggestionSearch = SuggestionSearch.newInstance();

        OnGetSuggestionResultListener listener = new OnGetSuggestionResultListener() {
            @Override
            public void onGetSuggestionResult(SuggestionResult suggestionResult) {
                resultlist.clear();
                if(suggestionResult.getAllSuggestions()!=null) {
                    for (int i = 0; i < suggestionResult.getAllSuggestions().size(); i++) {
                        try{
                            Memorandbeen memorandbeen = new Memorandbeen();
                            memorandbeen.setWeidu(suggestionResult.getAllSuggestions().get(i).getPt().latitude);
                            memorandbeen.setJing(suggestionResult.getAllSuggestions().get(i).getPt().longitude);
                            memorandbeen.setAddress(suggestionResult.getAllSuggestions().get(i).getCity() + " " +
                                    suggestionResult.getAllSuggestions().get(i).getKey());
                            resultlist.add(memorandbeen);
                        }catch (Exception e){

                        }
                    }
                }
                setqueryname(resultlist);
            }
        };
        mSuggestionSearch.setOnGetSuggestionResultListener(listener);

        adapter.setOnItem(new QuerybynameAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, Memorandbeen boss) {
                List<Mylocation> mylocations =LitePal.where("lou = ?","1").find(Mylocation.class);
                LatLng startPt = new LatLng(mylocations.get(0).getWeidu(),mylocations.get(0).getJingdu());
                LatLng endPt = new LatLng(boss.getWeidu(), boss.getJing());
                WalkRouteNodeInfo walkStartNode = new WalkRouteNodeInfo();
                walkStartNode.setLocation(startPt);
                WalkRouteNodeInfo walkEndNode = new WalkRouteNodeInfo();
                walkEndNode.setLocation(endPt);
                walkParam = new WalkNaviLaunchParam().startNodeInfo(walkStartNode).endNodeInfo(walkEndNode);
                startWalkNavi();
            }
        });


        searchQueryByname.setIconifiedByDefault(false);
        searchQueryByname.setSubmitButtonEnabled(true);
        ImageView iv_submit = searchQueryByname.findViewById(R.id.search_go_btn);
        iv_submit.setImageResource(R.drawable.ic_check_black_24dp);




        searchQueryByname.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                searchQueryByname.clearFocus();
                window.dismiss();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                mSuggestionSearch.requestSuggestion(new SuggestionSearchOption()
                        .city("湖北")
                        .keyword(s));
                return false;
            }
        });


        List<Memorandbeen> memorandbeens = LitePal.findAll(Memorandbeen.class);
        List<Mylocation> mylocations =LitePal.where("lou = ?","1").find(Mylocation.class);
        double myjing = mylocations.get(0).getJingdu();
        double mywei = mylocations.get(0).getWeidu();
        for (int i =-0;i<memorandbeens.size();i++){
            double jingdu = memorandbeens.get(i).getJing();
            double weidu = memorandbeens.get(i).getWeidu();
            double jingducha = jingdu - myjing;
            double weiducha = weidu - mywei;
            double juli = (jingducha*jingducha + weiducha*weiducha);
            memorandbeens.get(i).setJuli(juli);
        }
        Collections.sort(memorandbeens);
        list.addAll(memorandbeens);
        memoraapther = new Memoraapther(list,getApplicationContext());
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recJingdian.setLayoutManager(layoutManager);
        recJingdian.setAdapter(memoraapther);

        memoraapther.setOnPlayClickListener(new Memoraapther.OnPlayClickListener() {
            @Override
            public void onItemClick(Memorandbeen position) {
                List<Mylocation> mylocations =LitePal.where("lou = ?","1").find(Mylocation.class);
                startPt = new LatLng(mylocations.get(0).getWeidu(),mylocations.get(0).getJingdu());
                endPt = new LatLng(position.getWeidu(), position.getJing());
                WalkRouteNodeInfo walkStartNode = new WalkRouteNodeInfo();
                walkStartNode.setLocation(startPt);
                WalkRouteNodeInfo walkEndNode = new WalkRouteNodeInfo();
                walkEndNode.setLocation(endPt);
                walkParam = new WalkNaviLaunchParam().startNodeInfo(walkStartNode).endNodeInfo(walkEndNode);
                startWalkNavi();
            }

            @Override
            public void onXiangClick(Memorandbeen m) {
                Intent intent = new Intent(ScenicspotresultsActivity.this,XiangqinActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("memora",m);
                intent.putExtras(bundle);
                startActivity(intent);
            }

            @Override
            public void onYuying(Memorandbeen m) {
                if(mediaPlayer!=null){
                    mediaPlayer.stop();
                }
                if("诸葛草庐亭".equals(m.getAddress())){
                    mediaPlayer =  MediaPlayer.create(getApplicationContext(),R.raw.caolu);
                }else if("古隆中".equals(m.getAddress())){
                    mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.gulongzhong);
                }else if("三顾堂".equals(m.getAddress())){
                    mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.sangu);
                }else if("卧龙深处".equals(m.getAddress())){
                    mediaPlayer = MediaPlayer.create(getApplicationContext(),R.raw.wolong);
                }else {
                    return;
                }
                mediaPlayer.setLooping(false);
                mediaPlayer.start();
            }
        });

    }


    public void setqueryname(List<Memorandbeen> memorandbeens) {
        if(memorandbeens.size()>0) {
            if (!window.isShowing()) {
                window.showAsDropDown(searchQueryByname, BOTTOM, 10, 0);
            }
            adapter.notifyDataSetChanged();
        }else {
            window.dismiss();
        }
    }

    private void routeWalkPlanWithParam() {
        WalkNavigateHelper.getInstance().routePlanWithRouteNode(walkParam, new IWRoutePlanListener() {
            @Override
            public void onRoutePlanStart() {
            }

            @Override
            public void onRoutePlanSuccess() {
                Intent intent = new Intent();
                intent.setClass(ScenicspotresultsActivity.this, WNaviGuideActivity.class);
                startActivity(intent);

            }

            @Override
            public void onRoutePlanFail(WalkRoutePlanError error) {
                Toast.makeText(ScenicspotresultsActivity.this, "距离太远，无法步行导航", Toast.LENGTH_SHORT).show();
            }

        });

    }


    private void startWalkNavi() {
        try {
            WalkNavigateHelper.getInstance().initNaviEngine(this, new IWEngineInitListener() {
                @Override
                public void engineInitSuccess() {
                    routeWalkPlanWithParam();
                }

                @Override
                public void engineInitFail() {
                    WalkNavigateHelper.getInstance().unInitNaviEngine();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaPlayer!=null){
            mediaPlayer.stop();
        }
        mSuggestionSearch.destroy();
    }



    private void initpop() {
        View contentView=getLayoutInflater().inflate(R.layout.item_query_userlist, null);
        window=new PopupWindow(contentView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        window.setOutsideTouchable(false);
        window.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        recyclerView = contentView.findViewById(R.id.recy_content_link);
        TextView closewindow = contentView.findViewById(R.id.text_close);
        closewindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                window.dismiss();
            }
        });
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new QuerybynameAdapter(resultlist,getApplicationContext());
        recyclerView.setAdapter(adapter);

    }


}
