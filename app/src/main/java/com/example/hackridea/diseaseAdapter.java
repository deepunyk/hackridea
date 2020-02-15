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

public class diseaseAdapter extends RecyclerView.Adapter<diseaseAdapter.ViewHolder> {
    private ArrayList<String> title = new ArrayList<String>();
    private ArrayList<String> disc = new ArrayList<String>();
    private ArrayList<String> photolink = new ArrayList<String>();
    private ArrayList<String> authname = new ArrayList<String>();
    private ArrayList<String> authid = new ArrayList<String>();
    private ArrayList<String> author = new ArrayList<String>();



    //    private ArrayList<String> type = new ArrayList<String>();
    private Context mContext;
    private String dtitle,ddate;
    SharedPreferences sharedPreferences;

    public diseaseAdapter(ArrayList<String> title,ArrayList<String> photolink,ArrayList<String> authname,ArrayList<String> author,ArrayList<String> authid, Context mContext) {
        this.title = title;
        this.photolink = photolink;
        this.authname = authname;
        this.authid = authid;
        this.author = author;
        this.mContext = mContext;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.feed_card, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.title1.setText(title.get(i));
        viewHolder.tvheader.setText(authname.get(i));
        viewHolder.authorname.setText(author.get(i));
        Glide.with(mContext)
                .load(photolink.get(i))
                .placeholder(R.drawable.background)
                .into(viewHolder.imgview);
        viewHolder.comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(mContext, diseaseCommentActivity.class);

                myIntent.putExtra("rep_id",authid.get(i));
                myIntent.putExtra("plink",photolink.get(i));
                mContext.startActivity(myIntent);
            }
        });
        viewHolder.parent_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String scode=subcode.get(i);
//                String sname=subname.get(i);
//                Intent myIntent = new Intent(mContext,study_meterialsActivity.class);
//                myIntent.putExtra("subname",sname);
//                myIntent.putExtra("subcode",scode);
//                mContext.startActivity(myIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return title.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView title1, authorname, likes,tvheader;
        public ImageView imgview, like, comment, likefill;
        LinearLayout parent_layout;

        public ViewHolder(View itemView) {
            super(itemView);

            this.title1 = (TextView) itemView.findViewById(R.id.tvtitle);
            this.authorname = (TextView) itemView.findViewById(R.id.tvauthorname);
            this.likes = (TextView) itemView.findViewById(R.id.likes);
            this.tvheader = (TextView) itemView.findViewById(R.id.tvHeader);
            this.imgview = (ImageView) itemView.findViewById(R.id.imgview);
            this.comment = (ImageView) itemView.findViewById(R.id.comment);
            parent_layout = (LinearLayout) itemView.findViewById(R.id.parent_layout1);
        }
    }
}