package com.example.hackridea;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

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

public class diseaseActivity extends AppCompatActivity {

    ConstraintLayout oneLay, cropLay, animalLay;
    ImageView cropImg, animalImg, diseaseImg, insectImg, cattleImg, sheepImg, chickImg, prawnImg;
    String type = "";
    RecyclerView recyclerView;
    String url = "http://smvitmapp.xtoinfinity.tech/php/Hackridea/getDisease.php?type=";
    diseaseActivityRVA adapter;
    private ArrayList<String> symp = new ArrayList<>();
    private ArrayList<String> title = new ArrayList<>();
    private ArrayList<String> med = new ArrayList<>();
    private ArrayList<String> img = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease);

        oneLay = (ConstraintLayout)findViewById(R.id.oneLayout);
        cropLay = (ConstraintLayout)findViewById(R.id.twoLayout);
        animalLay = (ConstraintLayout)findViewById(R.id.animalLayout);
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);


        cropImg = (ImageView)findViewById(R.id.cropImg);
        sheepImg = (ImageView)findViewById(R.id.sheepImg);
        animalImg = (ImageView)findViewById(R.id.animalImg);
        diseaseImg = (ImageView)findViewById(R.id.diseaseImg);
        insectImg = (ImageView)findViewById(R.id.insectImg);
        cattleImg = (ImageView)findViewById(R.id.cattleImg);
        chickImg = (ImageView)findViewById(R.id.chickImg);
        prawnImg = (ImageView)findViewById(R.id.prawnImg);

        cropImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oneLay.setVisibility(View.GONE);
                cropLay.setVisibility(View.VISIBLE);
            }
        });

        animalImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                oneLay.setVisibility(View.GONE);
                animalLay.setVisibility(View.VISIBLE);
            }
        });

        chickImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "chicken";
                getDisease();
            }
        });

        cattleImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "Dairy_animals";
                getDisease();
            }
        });

        insectImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "insect";
                getDisease();
            }
        });

        prawnImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "prawns";
                getDisease();
            }
        });

        diseaseImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                type = "chicken";
                getDisease();
            }
        });


    }

    private void getDisease(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url+type,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("Empty")) {
                            Toast.makeText(diseaseActivity.this, "No classes", Toast.LENGTH_SHORT).show();
                        }else{
                            parseItems(response);
                        }

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(diseaseActivity.this, ""+error, Toast.LENGTH_SHORT).show();
                    }
                }
        );
        int socketTimeOut = 50000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }

    private void parseItems(String jsonResposnce) {
        try {
            JSONObject jobj = new JSONObject(jsonResposnce);
            JSONArray jarray = jobj.getJSONArray("disease");

            for (int i = 0; i < jarray.length(); i++) {
                JSONObject jo = jarray.getJSONObject(i);
                title.add(jo.optString("Disease"));
                symp.add(jo.optString("symptoms"));
                med.add(jo.optString("manage"));
                img.add(jo.optString("Image"));
            }
            initRecyclerView();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void initRecyclerView(){
        animalLay.setVisibility(View.GONE);
        cropLay.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new diseaseActivityRVA(symp,title,med,img,this);
        recyclerView.setAdapter(adapter);
    }

}
