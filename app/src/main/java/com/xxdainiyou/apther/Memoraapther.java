package com.xxdainiyou.apther;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.xxdainiyou.Activity.WNaviGuideActivity;
import com.xxdainiyou.R;
import com.xxdainiyou.been.Memorandbeen;

import java.util.List;

public class Memoraapther extends RecyclerView.Adapter<Memoraapther.ViewHolder>{
    private List<Memorandbeen> list;
    private Context context;
    OnPlayClickListener onItemPlayClick;
    public static interface OnPlayClickListener {
        public void onItemClick(Memorandbeen position);
    }


    public Memoraapther(List<Memorandbeen> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public Memoraapther.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (context == null) {
            context = viewGroup.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.item_jdresult, viewGroup, false);
        final ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Memoraapther.ViewHolder viewHolder, final int i) {
            final Memorandbeen memorandbeen = list.get(i);
            viewHolder.text_jd_title.setText(memorandbeen.getAddress());
            Glide.with(context).load(memorandbeen.getImg()).into(viewHolder.img_jd);
            viewHolder.lin_daohang.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemPlayClick.onItemClick(memorandbeen);
                }
            });
    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView text_jd_title;
        ImageView img_jd;
        LinearLayout lin_daohang,lin_yuying,lin_xiangqing;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lin_daohang = itemView.findViewById(R.id.lin_daohang);
            lin_yuying = itemView.findViewById(R.id.lin_yuying);
            lin_xiangqing = itemView.findViewById(R.id.lin_xiangqing);
            text_jd_title = itemView.findViewById(R.id.text_jd_title);
            img_jd = itemView.findViewById(R.id.img_jd);
        }
    }

    public void setOnPlayClickListener(OnPlayClickListener onItemPlayClick) {
        this.onItemPlayClick = onItemPlayClick;
    }
}
