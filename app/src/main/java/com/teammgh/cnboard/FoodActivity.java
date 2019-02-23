package com.teammgh.cnboard;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import java.net.URLDecoder;

public class FoodActivity extends AppCompatActivity {

    TextView tv_time, tv_when, tv_food;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R .layout.activity_food);

        tv_time = findViewById(R.id.tv_time);
        tv_food = findViewById(R.id.tv_food);
        tv_when = findViewById(R.id.tv_when);

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.d("test", "response : " + response);
                    String[] temp;
                    temp = response.split("%%");

                    tv_time.setText(temp[0]);
                    tv_when.setText(temp[1]);
                    tv_food.setText(URLDecoder.decode(temp[2], "UTF-8"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        RefreshFood refreshFood = new RefreshFood(responseListener);
        RequestQueue queue = Volley.newRequestQueue(FoodActivity.this);
        queue.add(refreshFood);
    }
}
