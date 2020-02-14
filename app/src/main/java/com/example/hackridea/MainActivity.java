package com.example.hackridea;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class MainActivity extends AppCompatActivity {

    farmMainPA pageAdapter;
    BottomNavigationViewEx farmBN;
    ViewPager vp,hvp;
    boolean doubleBackToExitPressedOnce = false;
    Toolbar toolbar;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = this.getSharedPreferences("com.example.hackridea",MODE_PRIVATE);

        farmBN = (BottomNavigationViewEx)findViewById(R.id.farmBN);
        vp = (ViewPager)findViewById(R.id.farmVP);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        pageAdapter = new farmMainPA(getSupportFragmentManager(),farmBN.getItemCount());
        vp.setAdapter(pageAdapter);

        farmBN.setupWithViewPager(vp);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tool_menu, menu);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.navProf:
                Intent go = new Intent(MainActivity.this, profActivity.class);
                startActivity(go);
                return true;
            case R.id.navSign:
                sp.edit().clear().apply();
                Intent go1 = new Intent(MainActivity.this, splashActivity.class);
                startActivity(go1);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
