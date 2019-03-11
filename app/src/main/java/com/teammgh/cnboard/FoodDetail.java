package com.teammgh.cnboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class FoodDetail extends AppCompatActivity {
    TextView date;
    TextView menu;
    TextView time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        Intent intent = getIntent();
        menu = findViewById(R.id.food_menu);
        menu.setText(intent.getExtras().getString("menu"));
        date = findViewById(R.id.food_date);
        date.setText(intent.getExtras().getString("date"));
        time = findViewById(R.id.food_time);

        switch (intent.getExtras().getInt("time")){
            case 0:
                time.setText("조식");
                break;
            case 1:
                time.setText("중식");
                break;
            case 2:
                time.setText("조식");
                break;
        }
    }
}
