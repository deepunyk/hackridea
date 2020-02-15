package com.example.hackridea;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class leaderAdapter extends RecyclerView.Adapter<leaderAdapter.ViewHolder> {
    private ArrayList<String> lid = new ArrayList<String>();
    private ArrayList<String> lname = new ArrayList<String>();
    private ArrayList<String> lscore = new ArrayList<String>();

    private Context mContext;
    private String dtitle,ddate;
    SharedPreferences sharedPreferences;

    public leaderAdapter(ArrayList<String> lid,ArrayList<String> lname,ArrayList<String> lscore, Context mContext) {
        this.lid = lid;
        this.lname = lname;
        this.lscore = lscore;
        this.mContext = mContext;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.leaderboard_layout, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, final int i) {
        int j=i+1;
        viewHolder.tvlid.setText(""+j);
        viewHolder.tvlname.setText(lname.get(i));
        viewHolder.tvlscore.setText(lscore.get(i));

        if(i==3) {
            viewHolder.parent_layout.setBackgroundColor(Color.parseColor("#01ff90"));
        }
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
        return lid.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvlid, tvlname, tvlscore;

        ConstraintLayout parent_layout;

        public ViewHolder(View itemView) {
            super(itemView);

            this.tvlid = (TextView) itemView.findViewById(R.id.tvlid);
            this.tvlname = (TextView) itemView.findViewById(R.id.tvlname);
            this.tvlscore = (TextView) itemView.findViewById(R.id.tvlscore);
            parent_layout = (ConstraintLayout) itemView.findViewById(R.id.parent_layoutlead);
        }
    }
}
