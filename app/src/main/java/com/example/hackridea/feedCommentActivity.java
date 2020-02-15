package com.example.hackridea;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
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
import com.mikhaellopez.circularimageview.CircularImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class feedCommentActivity extends AppCompatActivity {

    TextView nameTxt, dateTxt, descTxt, loadTxt;
    CircularImageView photoImg;
    SharedPreferences sp;
    String usn;
    String title, photolink, postid,name;
    String url = "http://smvitmapp.xtoinfinity.tech/php/Hackridea/getfeedComments.php?postid=";
    String posturl = "http://smvitmapp.xtoinfinity.tech/php/Hackridea/postfeedComments.php";
    private ArrayList<String> post_id = new ArrayList<>();
    private ArrayList<String> disc = new ArrayList<>();
    private ArrayList<String> comm_id = new ArrayList<>();
    private ArrayList<String> put_id = new ArrayList<>();
    private ArrayList<String> date = new ArrayList<>();
    com.example.hackridea.feedCommentAdapter adapter;
    RecyclerView recyclerView;
    EditText postTxt;
    ImageButton postBut;
    String author="7259331064",post;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_comment);
        title = getIntent().getStringExtra("title1");
        photolink = getIntent().getStringExtra("imgurl");
        postid = getIntent().getStringExtra("pid");
        name = getIntent().getStringExtra("name");
        postBut = (ImageButton) findViewById(R.id.postBut);
        postTxt = (EditText) findViewById(R.id.postTxt);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarcf);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getResources().getString(R.string.app_name));
//        nameTxt = (TextView) findViewById(R.id.nameTxt);
//        dateTxt = (TextView) findViewById(R.id.dateTxt);
//        descTxt = (TextView) findViewById(R.id.descTxt);
//        photoImg = (CircularImageView)findViewById(R.id.photoImg);

//        Glide.with(this).load(photolink).into(photoImg);
//        nameTxt.setText(name);
//        descTxt.setText(title);
        postBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post = postTxt.getText().toString().trim();
                Toast.makeText(feedCommentActivity.this, ""+post, Toast.LENGTH_SHORT).show();

                if (post.equals("")) {
                    Toast.makeText(feedCommentActivity.this, "Please enter something", Toast.LENGTH_SHORT).show();
                } else {
                    postTxt.setText("");
                    hideKeyboard(feedCommentActivity.this);
                    comment();
                    post_id.clear();
                    put_id.clear();
                    date.clear();
                    disc.clear();
                    comm_id.clear();
                    getClassroomFeed();
                }
            }
        });
        post_id.clear();
        put_id.clear();
        date.clear();
        disc.clear();
        comm_id.clear();
        getClassroomFeed();
    }

    public void getClassroomFeed() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url+postid ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

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
            JSONArray jarray = jobj.getJSONArray("class");

            for (int i = 0; i < jarray.length(); i++) {
                JSONObject jo = jarray.getJSONObject(i);
                post_id.add(jo.optString("post_id"));
                date.add(jo.optString("date"));
                put_id.add(jo.optString("put_id"));
                comm_id.add(jo.optString("comm_id"));
                disc.add(jo.optString("disc"));
            }
            initRecyclerView();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void initRecyclerView() {
        recyclerView = findViewById(R.id.recyclerviewcom);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new feedCommentAdapter(disc, date, post_id, put_id, this);
        recyclerView.setAdapter(adapter);
    }
//    private void comment() {
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, posturl,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        if(response.equals("success")){
//                        getClassroomFeed();}
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(feedCommentActivity.this, "" + error, Toast.LENGTH_SHORT).show();
//                    }
//                }
//        ) {
//            @Override
//            protected Map<String, String> getParams() {
//                Map<String, String> params = new HashMap<>();
//               // params.put("id", authid);
//                params.put("description", "afssagf");
//                params.put("pid", postid);
//                return params;
//            }
//
//
//        };
//        int socketTimeOut = 50000;
//        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
//        stringRequest.setRetryPolicy(retryPolicy);
//        RequestQueue queue = Volley.newRequestQueue(this);
//        queue.add(stringRequest);
//    }
    public void comment(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, posturl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Toast.makeText(feedCommentActivity.this, ""+response, Toast.LENGTH_SHORT).show();
                        if(response.equals("success")){
                            getClassroomFeed();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(feedCommentActivity.this, ""+error, Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();
                params.put("author",author);
                params.put("postid",postid);
                params.put("post",post);

                return params;
            };

        };
        int socketTimeOut = 50000;
        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
