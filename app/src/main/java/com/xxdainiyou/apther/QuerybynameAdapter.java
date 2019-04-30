package com.xxdainiyou.apther;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xxdainiyou.R;
import com.xxdainiyou.been.Memorandbeen;

import java.util.List;

public class QuerybynameAdapter extends RecyclerView.Adapter<QuerybynameAdapter.Viewholder> {
    private List<Memorandbeen> list;
    private Context context;
    private static OnItemClickListener onItemClick;
    public QuerybynameAdapter(List<Memorandbeen> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public static interface OnItemClickListener {
        void onItemClick(View view, Memorandbeen boss);
    }

    public static void setOnItem(OnItemClickListener myListener) {
        onItemClick = myListener;
    }

    @NonNull
    @Override
    public QuerybynameAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(context==null){
            context = parent.getContext();
        }
        View view = LayoutInflater.from(context).inflate(R.layout.item_search, parent, false);
        final QuerybynameAdapter.Viewholder holder = new QuerybynameAdapter.Viewholder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull QuerybynameAdapter.Viewholder holder, int position) {
        final Memorandbeen memorandbeen = list.get(position);
        holder.text_serach_name.setText(memorandbeen.getAddress());
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.onItemClick(v,memorandbeen);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size()>10?10:list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        TextView text_serach_name;
        LinearLayout linearLayout;
        public Viewholder(@NonNull View itemView) {
            super(itemView);
            linearLayout = (LinearLayout) itemView;
            text_serach_name = itemView.findViewById(R.id.text_serach_name);
        }
    }
}