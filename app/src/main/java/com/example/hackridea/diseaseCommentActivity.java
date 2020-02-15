package com.example.hackridea;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
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
import java.util.HashMap;
import java.util.Map;

public class diseaseCommentActivity extends AppCompatActivity {
    ArrayList<String> descri = new ArrayList<>();
    ArrayList<String> author = new ArrayList<>();
    ArrayList<String> reportid = new ArrayList<>();
    ImageView postBut;
    EditText postTxt;
    String post;
    String url= "http://smvitmapp.xtoinfinity.tech/php/Hackridea/getdiseaseComments.php?reportid=";
    String posturl= "http://smvitmapp.xtoinfinity.tech/php/Hackridea/postDiseaseComments.php";

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    TextView loadTxt;
    String rep_id,authorr="7259331064",descrip,report_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_comment);
        rep_id=getIntent().getStringExtra("rep_id");
//        Toast.makeText(this, ""+rep_id, Toast.LENGTH_SHORT).show();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);
        postBut = (ImageButton) findViewById(R.id.postBut);
        postTxt = (EditText) findViewById(R.id.postTxt);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getResources().getString(R.string.app_name));
        postBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                descrip = postTxt.getText().toString().trim();
                Toast.makeText(diseaseCommentActivity.this, ""+descrip, Toast.LENGTH_SHORT).show();

                if (descrip.equals("")) {
                    Toast.makeText(diseaseCommentActivity.this, "Please enter something", Toast.LENGTH_SHORT).show();
                } else {
                    postTxt.setText("");
                    hideKeyboard(diseaseCommentActivity.this);
                    comment();
                   descri.clear();
                   author.clear();
                   reportid.clear();
                   getDetails();
                }
            }
        });
        descri.clear();
        author.clear();
        reportid.clear();
          getDetails();
    }
    private void getDetails() {
        //progressdialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET,url+rep_id,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Toast.makeText(diseaseCommentActivity.this,""+response,Toast.LENGTH_LONG).show();

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
                String title = jo.getString("descri");
                String imgurl = jo.getString("author");
                String authname = jo.getString("report_id");
                descri.add(title);
                author.add(imgurl);
                reportid.add(authname);
            }
            recyclerView =(RecyclerView)findViewById(R.id.recyclerView2);
            commentAdapter adapter = new commentAdapter(descri,author,reportid,this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void comment(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, posturl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(diseaseCommentActivity.this, ""+response, Toast.LENGTH_SHORT).show();
                        if(response.equals("success")){
                            getDetails();

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(diseaseCommentActivity.this, ""+error, Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();
                params.put("author",authorr);
                params.put("descri",descrip);
                params.put("report_id",rep_id);

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
