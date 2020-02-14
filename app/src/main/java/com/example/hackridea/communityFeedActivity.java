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

public class communityFeedActivity extends AppCompatActivity {
    ArrayList<String> title_temp = new ArrayList<>();
    ArrayList<String> desc_temp = new ArrayList<>();
    ArrayList<String> img_url = new ArrayList<>();
    ArrayList<String> auth_name = new ArrayList<>();
    ArrayList<String> auth_id = new ArrayList<>();
    ArrayList<String> post_id = new ArrayList<>();

    String url= "http://smvitmapp.xtoinfinity.tech/php/Hackridea/feedDetails.php";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    TextView loadTxt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_feed);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarcf);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getResources().getString(R.string.app_name));
        getDetails();
    }
    private void getDetails() {
        //progressdialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET,url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                Toast.makeText(communityFeedActivity.this,""+response,Toast.LENGTH_LONG).show();

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
                String title = jo.getString("title");
                String descri = jo.getString("post_desc");
                String imgurl = jo.getString("photo_link");
                String authname = jo.getString("name");
                String authid = jo.getString("put_id");
                String postid = jo.getString("post_id");

                title_temp.add(title);
                desc_temp.add(descri);
                img_url.add(imgurl);
                auth_name.add(authname);
                auth_id.add(authid);
                post_id.add(postid);

            }
            recyclerView =(RecyclerView)findViewById(R.id.recyclerviewcf);
            feedAdapter adapter = new feedAdapter(title_temp,desc_temp,img_url,auth_name,auth_id,post_id,this);
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
                    Intent myIntent = new Intent(communityFeedActivity.this, communityFeedPostActivity.class);
                    startActivity(myIntent);
                    return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
