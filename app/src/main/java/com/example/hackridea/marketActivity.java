package com.example.hackridea;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class marketActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    String url =  "http://smvitmapp.xtoinfinity.tech/php/Hackridea/getProduct.php";
    marketActivityRVA adapter;
    private ArrayList<String> name = new ArrayList<>();
    private ArrayList<String> title = new ArrayList<>();
    private ArrayList<String> date = new ArrayList<>();
    private ArrayList<String> price = new ArrayList<>();
    private ArrayList<String> photo = new ArrayList<>();
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);

        fab = (FloatingActionButton)findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go = new Intent(marketActivity.this,marketPostActivity.class);
                startActivity(go);
            }
        });
        getNews();
    }
    private void getNews(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(response.equals("Empty")) {
                            Toast.makeText(marketActivity.this, "No classes", Toast.LENGTH_SHORT).show();
                        }else{
                            parseItems(response);
                        }

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(marketActivity.this, ""+error, Toast.LENGTH_SHORT).show();
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
            JSONArray jarray = jobj.getJSONArray("product");

            for (int i = 0; i < jarray.length(); i++) {
                JSONObject jo = jarray.getJSONObject(i);
                name.add(jo.optString("sell_name"));
                price.add(jo.optString("cost"));
                photo.add(jo.optString("photo_link"));
                date.add(jo.optString("date"));
                title.add(jo.optString("name"));
            }
            initRecyclerView();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private void initRecyclerView(){
        recyclerView = findViewById(R.id.recycle);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new marketActivityRVA(name,date,price,photo,title,this);
        recyclerView.setAdapter(adapter);
    }
}
