package com.example.hackridea;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class farmMainHome extends Fragment {

    View view;
    private String url = "http://smvitmapp.xtoinfinity.tech/php/Hackridea/getNews.php";
    private String tempUrl = "http://smvitmapp.xtoinfinity.tech/php/Hackridea/getTemp.php";
    private ArrayList<String> desc = new ArrayList<>();
    private ArrayList<String> title = new ArrayList<>();
    private ArrayList<String> id = new ArrayList<>();
    private ArrayList<String> photo = new ArrayList<>();
    private ArrayList<String> date = new ArrayList<>();
    private RecyclerView recyclerView;
    farmMainHomeNewsRVA adapter;
    SharedPreferences sp;
    private String dateStr, descStr, titleStr, idStr, photoStr;
    Guideline guideline1, guideline2;
    ImageView imgUp;
    ConstraintLayout constraintLayout;

    public farmMainHome() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.farm_main_home_fragment, container, false);
        recyclerView = view.findViewById(R.id.newsLayout);

        constraintLayout = view.findViewById(R.id.constraintLayout);
        constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgUp.setVisibility(View.VISIBLE);
                guideline1.setGuidelinePercent(1);
                guideline2.setGuidelinePercent(1);
            }
        });
        imgUp = (ImageView)view.findViewById(R.id.upImg);
        guideline1 = (Guideline)view.findViewById(R.id.guideline);
        guideline2 = (Guideline)view.findViewById(R.id.guideline2);
        recyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                imgUp.setVisibility(View.VISIBLE);
                guideline1.setGuidelinePercent(0);
                guideline2.setGuidelinePercent(0);
                return false;
            }
        });

        imgUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgUp.setVisibility(View.GONE);
                guideline1.setGuidelinePercent(0.3f);
                guideline2.setGuidelinePercent(0.6f);
            }
        });
        getNews();
        return view;
    }

    private void getNews(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("Empty")) {
                            Toast.makeText(getActivity(), "No classes", Toast.LENGTH_SHORT).show();
                        }else{
                            parseItems(response);
                        }

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getActivity(), ""+error, Toast.LENGTH_SHORT).show();
                    }
                }
        );
        int socketTimeOut = 50000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(stringRequest);
    }

    private void parseItems(String jsonResposnce) {
        try {
            JSONObject jobj = new JSONObject(jsonResposnce);
            JSONArray jarray = jobj.getJSONArray("news");

            for (int i = 0; i < jarray.length(); i++) {
                JSONObject jo = jarray.getJSONObject(i);
                desc.add(jo.optString("news_desc"));
                title.add(jo.optString("title"));
                photo.add(jo.optString("photo_link"));
                date.add(jo.optString("date"));
                id.add(jo.optString("news_id"));
            }
            initRecyclerView();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void initRecyclerView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new farmMainHomeNewsRVA(desc,title,id,photo,date,getActivity());
        recyclerView.setAdapter(adapter);
    }

}
