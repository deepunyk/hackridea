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
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class feedAdapter extends RecyclerView.Adapter<feedAdapter.ViewHolder> {
    private ArrayList<String> title = new ArrayList<String>();
    private ArrayList<String> disc = new ArrayList<String>();
    private ArrayList<String> photolink = new ArrayList<String>();
    private ArrayList<String> authname = new ArrayList<String>();
    private ArrayList<String> authid = new ArrayList<String>();
    private ArrayList<String> postid = new ArrayList<String>();



    //    private ArrayList<String> type = new ArrayList<String>();
    private Context mContext;
    private String dtitle,ddate;
    SharedPreferences sharedPreferences;

    public feedAdapter(ArrayList<String> title, ArrayList<String> disc,ArrayList<String> photolink,ArrayList<String> authname,ArrayList<String> authid,ArrayList<String> postid, Context mContext) {
        this.title = title;
        this.disc = disc;
        this.photolink = photolink;
        this.authname = authname;
        this.authid = authid;
        this.postid = postid;

        //this.type = type;
        this.mContext = mContext;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.feed_cards, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        viewHolder.title1.setText(title.get(i));
        viewHolder.tvheader.setText(disc.get(i));
        viewHolder.tvauthorname.setText(authname.get(i));
        Glide.with(mContext)
                .load(photolink.get(i))
                .placeholder(R.drawable.background)
                .into(viewHolder.imgview);
        viewHolder.comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(mContext, feedCommentActivity.class);
                myIntent.putExtra("title1",title.get(i));
                myIntent.putExtra("imgurl",photolink.get(i));
                myIntent.putExtra("pid",postid.get(i));
                myIntent.putExtra("name",authname.get(i));
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
        public TextView title1, description, likes,tvheader,tvauthorname;
        public ImageView imgview, like, comment, likefill;
        LinearLayout parent_layout;

        public ViewHolder(View itemView) {
            super(itemView);

            this.title1 = (TextView) itemView.findViewById(R.id.tvtitle);
            this.tvheader = (TextView) itemView.findViewById(R.id.tvHeader);
            this.tvauthorname = (TextView) itemView.findViewById(R.id.tvauthorname);
            //description = (TextView) itemView.findViewById(R.id.description);
            this.likes = (TextView) itemView.findViewById(R.id.likes);
            this.imgview = (ImageView) itemView.findViewById(R.id.imgview);
            this.comment = (ImageView) itemView.findViewById(R.id.comment);
            parent_layout = (LinearLayout) itemView.findViewById(R.id.parent_layoutcf);
        }
    }
}