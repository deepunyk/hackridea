package com.example.hackridea;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class commentAdapter extends RecyclerView.Adapter<commentAdapter.ViewHolder> {
    ArrayList<String> descri = new ArrayList<>();
    ArrayList<String> author = new ArrayList<>();
    ArrayList<String> reportid = new ArrayList<>();



    //    private ArrayList<String> type = new ArrayList<String>();
    private Context mContext;
    private String dtitle,ddate;
    SharedPreferences sharedPreferences;

    public commentAdapter(ArrayList<String> descri,ArrayList<String> author,ArrayList<String> reportid, Context mContext) {
        this.descri = descri;
        this.author = author;
        this.reportid = reportid;
        //this.type = type;
        this.mContext = mContext;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.comment_layout, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.commdesc.setText(descri.get(i));
        viewHolder.commauth.setText(author.get(i));
        viewHolder.parent_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public int getItemCount() {
        return descri.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView commdesc, commauth;
        public ImageView imgview, like, comment, likefill;
        ConstraintLayout parent_layout;

        public ViewHolder(View itemView) {
            super(itemView);

            this.commdesc = (TextView) itemView.findViewById(R.id.tvComments);
            //description = (TextView) itemView.findViewById(R.id.description);
            this.commauth = (TextView) itemView.findViewById(R.id.tvAuthorcomm);
            parent_layout = (ConstraintLayout) itemView.findViewById(R.id.parent_layoutcom);
        }
    }
}