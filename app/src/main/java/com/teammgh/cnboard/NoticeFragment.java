package com.teammgh.cnboard;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

public class NoticeFragment extends Fragment {

    ArrayList<NoticeData> noticeDataList;
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    NoticeAdapter noticeAdapter;

    private static final String ARG_PARAM1 = "param1";

    Context context;

    public NoticeFragment() {
        // Required empty Constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            context = (Context) getArguments().get(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.notice_fragment, container, false);

        context = getContext();

        mRecyclerView = view.findViewById(R.id.notice_recycler);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        //FAKE DATA FAKE DATA --------------- TEST
        noticeDataList = new ArrayList<NoticeData>();
        noticeDataList.add(new NoticeData("2018-11-10", "notice1_20181110.png"));
        noticeDataList.add(new NoticeData("2018-11-16", "notice2_20181116.png"));
        noticeDataList.add(new NoticeData("2018-11-20", "notice3_20181120.jpeg"));
        //http://45.32.49.247/notice/notice2_20181116.png

        requestNoticeList();

        noticeAdapter = new NoticeAdapter(noticeDataList, context);

        mRecyclerView.setAdapter(noticeAdapter);

        return view;
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
        RequestQueue queue = Volley.newRequestQueue(context);
        queue.add(refreshUserData);
        Log.d("test", "refreshRequest");
    }

}
