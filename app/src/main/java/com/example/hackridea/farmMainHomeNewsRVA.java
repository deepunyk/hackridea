package com.example.hackridea;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class farmMainHomeNewsRVA extends RecyclerView.Adapter<farmMainHomeNewsRVA.ViewHolder> {

    public farmMainHomeNewsRVA(ArrayList<String> desc, ArrayList<String> title, ArrayList<String> id, ArrayList<String> photo, ArrayList<String> date, Context mContext) {
        this.desc = desc;
        this.title = title;
        this.id = id;
        this.photo = photo;
        this.date = date;
        this.mContext = mContext;
    }

    private ArrayList<String> desc = new ArrayList<>();
    private ArrayList<String> title = new ArrayList<>();
    private ArrayList<String> id = new ArrayList<>();
    private ArrayList<String> photo = new ArrayList<>();
    private ArrayList<String> date = new ArrayList<>();
    private Context mContext;

    @NonNull
    @Override
    public farmMainHomeNewsRVA.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.farm_main_home_news_layout, viewGroup, false);
        farmMainHomeNewsRVA.ViewHolder holder = new farmMainHomeNewsRVA.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final farmMainHomeNewsRVA.ViewHolder viewHolder, final int i) {
        viewHolder.titleTxt.setText(title.get(i));
        viewHolder.descTxt.setText(desc.get(i));
        viewHolder.dateTxt.setText(date.get(i));
        Glide.with(mContext).load(photo.get(i)).into(viewHolder.img);
    }

    @Override
    public int getItemCount() {
        return title.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView descTxt, dateTxt, titleTxt;
        ImageView img;
        ConstraintLayout parent_layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            descTxt = itemView.findViewById(R.id.descTxt);
            dateTxt = itemView.findViewById(R.id.dateTxt);
            titleTxt = itemView.findViewById(R.id.titleTxt);
            img = itemView.findViewById(R.id.img);
            parent_layout = itemView.findViewById(R.id.parent_layout);
        }
    }
}