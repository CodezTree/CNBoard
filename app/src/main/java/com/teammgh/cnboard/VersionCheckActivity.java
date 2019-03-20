package com.teammgh.cnboard;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import java.util.List;

public class VersionCheckActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_version_check);

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equals("0001")) {
                    Intent intent = new Intent(VersionCheckActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    AlertDialog.Builder dlg = new AlertDialog.Builder(getApplicationContext());
                    dlg.setTitle("버전오류");
                    dlg.setMessage("앱 버전이 구 버전입니다. 최신버전을 다운해주세요!");
                    dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finishAffinity();
                        }
                    });
                    dlg.show();
                }
            }
        };

        VersionRequest versionRequest = new VersionRequest(responseListener);
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(versionRequest);
    }
}
