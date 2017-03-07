package com.ak.lkp.friendgift.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ak.lkp.friendgift.Entity.CommonBean;
import com.ak.lkp.friendgift.R;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by likunpeng on 2017/1/17.
 */

public class UserItemRecyclerViewAdapter extends RecyclerView.Adapter<UserItemRecyclerViewAdapter.MyViewHolder>{

    private LayoutInflater mLayoutInflater;
    private List<CommonBean> mDatas;
    private Context mContext;

    public UserItemRecyclerViewAdapter(Context context, List<CommonBean> datas) {
        this.mContext = context;
        this.mDatas = datas;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(mLayoutInflater.inflate(R.layout.home_list_item, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.author_name.setText(mDatas.get(position).getNickname());
        holder.author_intruduce.setText(mDatas.get(position).getIntroduction());
        holder.home_content_text1.setText(mDatas.get(position).getDescription());
        holder.title_num.setText(mDatas.get(position).getLikes_count());
        holder.lanmu.setText("栏目");
        Glide.with(mContext).load(mDatas.get(position).getAvatar_url()).into(holder.author_image);
        Glide.with(mContext).load(mDatas.get(position).getCover_image_url()).into(holder.home_content_image);
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
    TextView author_name, author_intruduce, home_content_text1, title_num,lanmu;
    ImageView author_image,home_content_image;
    public MyViewHolder(View view) {
        super(view);
        author_image= (ImageView) view.findViewById(R.id.author_image);
        home_content_image= (ImageView) view.findViewById(R.id.home_content_image);
        author_name = (TextView) view.findViewById(R.id.author_name);
        author_intruduce = (TextView) view.findViewById(R.id.author_intruduce);
        home_content_text1 = (TextView) view.findViewById(R.id.home_content_text1);
        title_num = (TextView) view.findViewById(R.id.title_num);
        lanmu = (TextView) view.findViewById(R.id.lanmu);

    }
}
}
