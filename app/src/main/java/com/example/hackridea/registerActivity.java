package com.example.hackridea;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.HashMap;
import java.util.Map;


public class registerActivity extends AppCompatActivity {
    EditText etname,etphone,etpass,etloc;
    Button btnreg;
    MaterialSpinner type,role;
    String typeAr[] = {"Agriculture","Horticulture","Sericulture","Animal Husbandry","Fishery"};
    String roleAr[] = {"Farmer","Consumer","Expert"};
    String name,phone,urole,utype,pass,loc;
    int utypeid;
    String url="http://smvitmapp.xtoinfinity.tech/php/Hackridea/registerUser.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etname=(EditText)findViewById(R.id.etregname);
        etphone=(EditText)findViewById(R.id.etregphone);
        etpass=(EditText)findViewById(R.id.etregpass);
        etloc=(EditText)findViewById(R.id.etLocation);
        btnreg=(Button)findViewById(R.id.btnSignup);
        type=(MaterialSpinner)findViewById(R.id.typeSpin);
        role=(MaterialSpinner)findViewById(R.id.roleSpin);
        role.setItems(roleAr);
        type.setItems(typeAr);
        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                urole = roleAr[role.getSelectedIndex()];
                utype = typeAr[type.getSelectedIndex()];
                if(utype.equals("Agriculture"))
                {
                    utype="1";
                }else if(utype.equals("Horticulture"))
                {
                    utype="2";
                }else if(utype.equals("Sericulture"))
                {
                    utype="3";
                }else if(utype.equals("Animal Husbandry"))
                {
                    utype="4";

                }else
                {
                    utype="5";
                }
                name = etname.getText().toString().trim();
                pass = etpass.getText().toString();
                loc = etpass.getText().toString();
                phone=etphone.getText().toString();
                if(name.equals("")||pass.equals("")||phone.equals("")||loc.equals(""))
                {
                    Toast.makeText(registerActivity.this, "Please enter all details!!!!!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                  register();

                }
            }
        });
    }
    public void register(){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(registerActivity.this, ""+response, Toast.LENGTH_SHORT).show();
                        if(response.equals("success")){
                            Intent intent = new Intent(registerActivity.this, loginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(registerActivity.this, ""+error, Toast.LENGTH_SHORT).show();
                    }
                }
        ){
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap<>();
                params.put("name",name);
                params.put("pass",pass);
                params.put("phone",phone);
                params.put("utype",utype);
                params.put("urole",urole);
                params.put("loc",loc);
                return params;
            };

        };
        int socketTimeOut = 50000;
        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(stringRequest);
    }
}
