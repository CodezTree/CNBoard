package com.teammgh.cnboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class FoodDetail extends AppCompatActivity {
    TextView txt_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        Intent intent = getIntent();
        txt_menu = findViewById(R.id.food_menu);
        txt_menu.setText(intent.getExtras().getString("mon1_data"));

    }
}
