package com.teammgh.cnboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class FoodDetail extends AppCompatActivity {
    TextView date;
    TextView menu;
    TextView time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail);

        Intent intent = getIntent();
        menu = findViewById(R.id.food_menu);
        menu.setText(intent.getExtras().getString("menu"));
        date = findViewById(R.id.food_date);
        String date_new = intent.getExtras().getString("date");
        String[] array = date_new.split("-");
        date.setText("  "+array[0]+"년 "+array[1]+"월 "+array[2]+"일");
        time = findViewById(R.id.food_time);

        switch (intent.getExtras().getInt("time")){
            case 0:
                time.setText("조식");
                break;
            case 1:
                time.setText("중식");
                break;
            case 2:
                time.setText("석식");
                break;
        }
    }
}
