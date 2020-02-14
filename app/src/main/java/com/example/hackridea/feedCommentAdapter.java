package com.example.hackridea;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class feedCommentAdapter extends RecyclerView.Adapter<feedCommentAdapter.ViewHolder>{


    public feedCommentAdapter(ArrayList<String> descAr, ArrayList<String> date, ArrayList<String> postid, ArrayList<String> putid, Context mContext) {
        this.descAr = descAr;
        this.date = date;
        this.postid = postid;
        this.putid = putid;
        this.mContext = mContext;
    }

    private ArrayList<String> descAr = new ArrayList<>();
    private ArrayList<String> date = new ArrayList<>();
    private ArrayList<String> postid = new ArrayList<>();
    private ArrayList<String> putid = new ArrayList<>();
    String fname, fphoto;
    private Context mContext;



    @NonNull
    @Override
    public feedCommentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.classroom_comment_layout, viewGroup, false);
        feedCommentAdapter.ViewHolder holder = new feedCommentAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final feedCommentAdapter.ViewHolder viewHolder, final int i) {
            viewHolder.nameTxt.setText(putid.get(i));
        viewHolder.descTxt.setText(descAr.get(i));
        viewHolder.dateTxt.setText(date.get(i));
//            viewHolder.deleteBut.setVisibility(View.VISIBLE);
//            viewHolder.editBut.setVisibility(View.VISIBLE);
        viewHolder.deleteBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Deleting your comment. Please wait.", Toast.LENGTH_SHORT).show();
               // deletePost(descAr.get(i),date.get(i), viewHolder.parent_layout);
            }
        });

    }

    @Override
    public int getItemCount() {
        return descAr.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView dateTxt, nameTxt, descTxt;
        CircularImageView photoImg;
        ConstraintLayout parent_layout;
        ImageView editBut, deleteBut;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTxt = itemView.findViewById(R.id.nameTxt);
            dateTxt = itemView.findViewById(R.id.dateTxt);
            descTxt = itemView.findViewById(R.id.descTxt);
            photoImg = itemView.findViewById(R.id.photoImg);
            parent_layout = itemView.findViewById(R.id.parent_layout2);
            editBut = itemView.findViewById(R.id.editBut);
            deleteBut = itemView.findViewById(R.id.deleteBut);

        }
    }
    private void deletePost(final String desc, final String date, final ConstraintLayout layoutView){
        String deleteUrl = "http://smvitmapp.xtoinfinity.tech/php/Hackridea/deleteFeedComment.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, deleteUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ((Activity)mContext).finish();
                        ((Activity)mContext).overridePendingTransition(0, 0);
                        ((Activity)mContext).startActivity(((Activity)mContext).getIntent());
                        ((Activity)mContext).overridePendingTransition(0, 0);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(mContext, ""+error, Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();
                params.put("desc",desc);
                params.put("date",date);
                return params;
            };

        };
        int socketTimeOut = 50000;
        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);
        RequestQueue queue = Volley.newRequestQueue(mContext);
        queue.add(stringRequest);
    }

}
