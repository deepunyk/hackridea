package com.example.hackridea;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
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

public class profileActivity extends AppCompatActivity {
    TextView tvname,tvphone,tvloc,tvrole,tvtype;
    String url="http://smvitmapp.xtoinfinity.tech/php/Hackridea/profileDetails.php?phone=";
    String user="7259331064";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        tvname=(TextView)findViewById(R.id.tvpname);
        tvphone=(TextView)findViewById(R.id.tvpphone);
        tvloc=(TextView)findViewById(R.id.tvplocation);
        tvrole=(TextView)findViewById(R.id.tvprole);
        tvtype=(TextView)findViewById(R.id.tvptype);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarp);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getResources().getString(R.string.app_name));
    getDetails();

    }
    private void getDetails() {
        //progressdialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET,url+user,
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
            JSONArray jarray = jobj.getJSONArray("student");
            for (int i = 0; i < jarray.length(); i++) {
                JSONObject jo = jarray.getJSONObject(i);
                String name = jo.getString("name");
                String location = jo.getString("location");
                String role = jo.getString("role");
                String type = jo.getString("type_name");
                tvname.setText(name);
                tvtype.setText(type);
                tvphone.setText(user);
                tvloc.setText(location);
                tvrole.setText(role);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
