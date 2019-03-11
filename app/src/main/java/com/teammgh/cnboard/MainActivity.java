package com.teammgh.cnboard;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import org.json.*;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    Toolbar myToolbar;
    LinearLayout setting;

    ArrayList<NoticeData> noticeDataList;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    NoticeAdapter noticeAdapter;
    // For main notice

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, myToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        toggle.setDrawerIndicatorEnabled(false);
        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_dehaze_black, getTheme());
        toggle.setHomeAsUpIndicator(drawable);
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(GravityCompat.START);
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



        setting = findViewById(R.id.bt_setting);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // SETTING
            }
        }); // SETTING BUTTON


        // Notice Recycler

        mRecyclerView = findViewById(R.id.notice_recycler);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        noticeDataList = new ArrayList<>();

        requestNoticeList();

        noticeAdapter = new NoticeAdapter(noticeDataList, getApplicationContext());

        mRecyclerView.setAdapter(noticeAdapter);

        // Notice Recycler END

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //return super.onOptionsItemSelected(item);
        switch (item.getItemId()) {
            case R.id.menu_refresh:
                // User chose the "Settings" item, show the app settings UI...
                requestNoticeList();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_detailed_notice) {
            Intent intent = new Intent(this, DetailedNoticeActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_food_table) {
            Intent intent = new Intent(this, LakeViewActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_d_day) {
            // D-DAY
            Intent intent = new Intent(this, DdayActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_test) {
            // TEST
            Intent intent = new Intent(this, EnrolmentSubject.class);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void requestNoticeList() {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.d("test", "refreshResponse");
                    Log.d("test", "response : "+response);

                    noticeDataList.clear();

                    JSONArray tempArr = new JSONArray(response);

                    String[] temp;
                    for(int i = 0; i < tempArr.length(); i++) {
                        JSONObject obj = tempArr.getJSONObject(i);
                            noticeDataList.add(new NoticeData(obj.getString("notice_title"), obj.getString("notice_date"), obj.getString("notice_image"), obj.getInt("id"), obj.getInt("notice_kind"), obj.getInt("target_grade")));
                        Log.d("test","time : "+obj.getString("notice_date") + "   URL : "+obj.getString("notice_image") + " title : "+obj.getString("notice_title"));

                        // {"id": 1, "notice_kind": 1, "notice_title": "Test", "notice_date": "2019-03-06", "notice_image": "notice/logo_3.png", "target_grade": 2
                    }

                    noticeAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        RefreshNotice refreshUserData = new RefreshNotice(responseListener);
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(refreshUserData);
        Log.d("test", "refreshRequest");
    }
}
