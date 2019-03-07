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
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    Toolbar myToolbar;
    LinearLayout setting;

    FragmentManager fm;
    FragmentTransaction fragmentTransaction;
    FrameLayout pageLayout;

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

        /*foodButton = findViewById(R.id.bt_food);
        foodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FoodActivity.class);
                startActivity(intent);
            }
        });*/ // FOOD BUTTON

        mRecyclerView = findViewById(R.id.notice_recycler);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        //FAKE DATA FAKE DATA --------------- TEST
        noticeDataList = new ArrayList<NoticeData>();
        noticeDataList.add(new NoticeData("2018-11-10 월요일", "정보공지"));
        noticeDataList.add(new NoticeData("2018-11-16 토요일", "테스트공지2"));
        noticeDataList.add(new NoticeData("2018-11-20 수요일", "테스트공지3"));
        //http://45.32.49.247/notice/notice2_20181116.png

        requestNoticeList();

        noticeAdapter = new NoticeAdapter(noticeDataList, getApplicationContext());

        mRecyclerView.setAdapter(noticeAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_detailed_notice) {
            Intent intent = new Intent(this, NoticeDetailActivity.class);
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
                    String[] items = response.split("\n");
                    noticeDataList.clear();

                    String[] temp;
                    for(String tmpString : items) {
                        temp = tmpString.split("%%");

                        noticeDataList.add(new NoticeData(temp[0], temp[1]));
                        Log.d("test","time : "+temp[0] + "   URL : "+temp[1]);
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
