package com.teammgh.cnboard;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class DetailedNoticeActivity extends AppCompatActivity {
    Spinner gradeSpinner, kindSpinner;
    ArrayList<String> gradeList, kindList;

    int selectedGrade, selectedKind;

    ArrayList<NoticeData> noticeDataList;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    NoticeAdapter noticeAdapter;

    Toolbar myToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_notice);

        // INIT

        selectedGrade = 0;
        selectedKind = 0; // 0 is ALL RANGE
        noticeDataList = new ArrayList<>();

        // INIT END

        // 툴바입니다 건들 ㄴㄴ

        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true); // 커스터마이징
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼

        // 툴바입니다 건들 ㄴㄴ


        // SPINNER START

        gradeSpinner = (Spinner) findViewById(R.id.spinner_grade);
        kindSpinner = (Spinner) findViewById(R.id.spinner_kind);

        gradeList = new ArrayList<>();
        kindList = new ArrayList<>();
        String[] _gradeList = new String[]{"전체", "1학년", "2학년", "3학년"};
        String[] _kindList = new String[]{"전체", "일정", "대회", "행사", "기타"};
        gradeList.addAll(Arrays.asList(_gradeList));
        kindList.addAll(Arrays.asList(_kindList));

        final ArrayAdapter<String> gradeSpinnerAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, gradeList);
        ArrayAdapter<String> kindSpinnerAdapter = new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, kindList);

        gradeSpinner.setAdapter(gradeSpinnerAdapter);
        kindSpinner.setAdapter(kindSpinnerAdapter);

        gradeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                selectedGrade = position;
                requestDetailedNotice();
                Log.d("DetailedTest", Integer.toString(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        kindSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                selectedKind = position;
                requestDetailedNotice();
                Log.d("DetailedTest", Integer.toString(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // SPINNER END

        // Notice Recycler

        mRecyclerView = findViewById(R.id.notice_recycler);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        requestDetailedNotice();

        noticeAdapter = new NoticeAdapter(noticeDataList, getApplicationContext());

        mRecyclerView.setAdapter(noticeAdapter);

        // Notice Recycler END
    }

    public void requestDetailedNotice() {
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
        RefreshDetailedNotice refreshUserData = new RefreshDetailedNotice(responseListener, selectedKind, selectedGrade);
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(refreshUserData);
    }
}
