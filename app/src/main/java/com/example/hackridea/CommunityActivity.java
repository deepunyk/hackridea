package com.example.hackridea;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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

public class CommunityActivity extends AppCompatActivity {

    ArrayList<String> title_temp = new ArrayList<>();
    ArrayList<String> desc_temp = new ArrayList<>();
    ArrayList<String> img_url = new ArrayList<>();
    ArrayList<String> auth_name = new ArrayList<>();
    ArrayList<String> auth_id = new ArrayList<>();
    ArrayList<String> author_temp = new ArrayList<>();

    String url= "http://smvitmapp.xtoinfinity.tech/php/Hackridea/getreporteddiseases.php";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    TextView loadTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getResources().getString(R.string.app_name));
        title_temp.clear();
        img_url.clear();
        auth_name.clear();
        auth_id.clear();
        author_temp.clear();
        getDetails();
    }
    private void getDetails() {
        //progressdialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET,url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                //Toast.makeText(CommunityActivity.this,""+response,Toast.LENGTH_LONG).show();

                        parseItems(response);
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
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
            JSONArray jarray = jobj.getJSONArray("student");
            for (int i = 0; i < jarray.length(); i++) {
                JSONObject jo = jarray.getJSONObject(i);
                String title = jo.getString("desc_disease");
                String imgurl = jo.getString("link");
                String authname = jo.getString("name");
                String authid = jo.getString("report_id");
                String author = jo.getString("author");
                title_temp.add(title);
                img_url.add(imgurl);
                auth_name.add(authname);
                auth_id.add(authid);
                author_temp.add(author);
            }
            recyclerView =(RecyclerView)findViewById(R.id.recycleview);
            diseaseAdapter adapter = new diseaseAdapter(title_temp,img_url,auth_name,author_temp,auth_id,this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.add_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_add:
                Intent myIntent = new Intent(CommunityActivity.this, feedPostActivity.class);
                title_temp.clear();
                img_url.clear();
                auth_name.clear();
                auth_id.clear();
                author_temp.clear();
                startActivity(myIntent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
