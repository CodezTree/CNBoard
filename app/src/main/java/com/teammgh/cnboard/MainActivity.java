package com.teammgh.cnboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

public class    MainActivity extends AppCompatActivity {

    Toolbar myToolbar;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    Button foodButton;

    ArrayList<NoticeData> noticeDataList;
    NoticeAdapter noticeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        mRecyclerView = findViewById(R.id.notice_recycler);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        noticeDataList = new ArrayList<NoticeData>();
        noticeDataList.add(new NoticeData("2018-11-10", "notice1_20181110.png"));
        noticeDataList.add(new NoticeData("2018-11-16", "notice2_20181116.png"));
        noticeDataList.add(new NoticeData("2018-11-20", "notice3_20181120.jpeg"));
        //http://45.32.49.247/notice/notice2_20181116.png

        foodButton = findViewById(R.id.bt_food);
        foodButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FoodActivity.class);
                startActivity(intent);
            }
        });

        requestNoticeList();

        noticeAdapter = new NoticeAdapter(noticeDataList, MainActivity.this);

        mRecyclerView.setAdapter(noticeAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.menu_refresh:
                requestNoticeList();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        queue.add(refreshUserData);
        Log.d("test", "refreshRequest");
    }
}
