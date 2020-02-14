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

public class diseaseActivityRVA extends RecyclerView.Adapter<diseaseActivityRVA.ViewHolder> {


    public diseaseActivityRVA(ArrayList<String> symp, ArrayList<String> title, ArrayList<String> med, ArrayList<String> img, Context mContext) {
        this.symp = symp;
        this.title = title;
        this.med = med;
        this.img = img;
        this.mContext = mContext;
    }

    String im,im1, im2,im3;
    private ArrayList<String> symp = new ArrayList<>();
    private ArrayList<String> title = new ArrayList<>();
    private ArrayList<String> med = new ArrayList<>();
    private ArrayList<String> img = new ArrayList<>();
    private Context mContext;

    @NonNull
    @Override
    public diseaseActivityRVA.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.disease_layout, viewGroup, false);
        diseaseActivityRVA.ViewHolder holder = new diseaseActivityRVA.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final diseaseActivityRVA.ViewHolder viewHolder, final int i) {
        viewHolder.titleTxt.setText(title.get(i));
        viewHolder.sympTxt.setText(symp.get(i));
        viewHolder.medTxt.setText(med.get(i));
        //Glide.with(mContext).load(photo.get(i)).into(viewHolder.img1);
        try{
            im =img.get(i);
            im1=(im).substring(0,im.indexOf(","));
            im = (im).substring(im.indexOf(",")+1);
            im2=(im).substring(0,im.indexOf(","));
            im = (im).substring(im.indexOf(",")+1);
            im3=(im).substring(0,im.indexOf("!"));
            Glide.with(mContext).load(im1).into(viewHolder.img1);
            Glide.with(mContext).load(im2).into(viewHolder.img2);
            Glide.with(mContext).load(im3).into(viewHolder.img3);
        }catch(Exception e){

        }
    }

    @Override
    public int getItemCount() {
        return title.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView medTxt, sympTxt, titleTxt;
        ImageView img1,img2, img3;
        ConstraintLayout parent_layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTxt = itemView.findViewById(R.id.titleTxt);
            sympTxt = itemView.findViewById(R.id.sympTxt);
            medTxt = itemView.findViewById(R.id.medTxt);
            img1 = itemView.findViewById(R.id.img1);
            img2 = itemView.findViewById(R.id.img2);
            img3 = itemView.findViewById(R.id.img3);
            parent_layout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
