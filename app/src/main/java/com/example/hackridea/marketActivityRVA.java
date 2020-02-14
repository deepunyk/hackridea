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

public class marketActivityRVA extends RecyclerView.Adapter<marketActivityRVA.ViewHolder> {


    public marketActivityRVA(ArrayList<String> name, ArrayList<String> date, ArrayList<String> price, ArrayList<String> photo, ArrayList<String> title, Context mContext) {
        this.name = name;
        this.date = date;
        this.price = price;
        this.photo = photo;
        this.title = title;
        this.mContext = mContext;
    }

    private ArrayList<String> name = new ArrayList<>();
    private ArrayList<String> date = new ArrayList<>();
    private ArrayList<String> price = new ArrayList<>();
    private ArrayList<String> title = new ArrayList<>();
    private ArrayList<String> photo = new ArrayList<>();
    private Context mContext;

    @NonNull
    @Override
    public marketActivityRVA.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.market_activity_layout, viewGroup, false);
        marketActivityRVA.ViewHolder holder = new marketActivityRVA.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final marketActivityRVA.ViewHolder viewHolder, final int i) {
        viewHolder.nameTxt.setText(name.get(i));
        viewHolder.priceTxt.setText(price.get(i));
        viewHolder.dateTxt.setText(date.get(i));
        viewHolder.titleTxt.setText(title.get(i));
        Glide.with(mContext).load(photo.get(i)).into(viewHolder.img);
    }

    @Override
    public int getItemCount() {
        return name.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nameTxt, dateTxt, priceTxt, titleTxt;
        ImageView img;
        ConstraintLayout parent_layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTxt = itemView.findViewById(R.id.nameTxt);
            dateTxt = itemView.findViewById(R.id.dateTxt);
            priceTxt = itemView.findViewById(R.id.priceTxt);
            titleTxt = itemView.findViewById(R.id.titleTxt);
            img = itemView.findViewById(R.id.img);
            parent_layout = itemView.findViewById(R.id.parent_layout);
        }
    }
}