package com.teammgh.cnboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LakeViewActivity extends AppCompatActivity {
    TextView txt_mon1;
    TextView txt_mon2;
    TextView txt_mon3;
    TextView txt_tue1;
    TextView txt_tue2;
    TextView txt_tue3;
    TextView txt_wed1;
    TextView txt_wed2;
    TextView txt_wed3;
    TextView txt_thu1;
    TextView txt_thu2;
    TextView txt_thu3;
    TextView txt_fri1;
    TextView txt_fri2;
    TextView txt_fri3;
    TextView txt_sat1;
    TextView txt_sat2;
    TextView txt_sat3;
    TextView txt_sun1;
    TextView txt_sun2;
    TextView txt_sun3;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lakeview);

        txt_mon1 = findViewById(R.id.mon1);
        txt_mon1.setText("mon1");

        txt_mon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LakeViewActivity.this, FoodDetail.class);
                intent.putExtra("menu_data","mon1");
                startActivity(intent);
            }
        });

        txt_mon2 = findViewById(R.id.mon2);
        txt_mon2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        txt_mon3 = findViewById(R.id.mon3);
        txt_mon3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        txt_tue1 = findViewById(R.id.tue1);
        txt_tue1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        txt_tue2 = findViewById(R.id.tue2);
        txt_tue2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        txt_tue3 = findViewById(R.id.tue3);
        txt_tue3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        txt_wed1 = findViewById(R.id.wed1);
        txt_wed1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        txt_wed2 = findViewById(R.id.wed2);
        txt_wed2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        txt_wed3 = findViewById(R.id.wed3);
        txt_wed3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        txt_thu1 = findViewById(R.id.thu1);
        txt_thu1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        txt_thu2 = findViewById(R.id.thu2);
        txt_thu2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        txt_thu3 = findViewById(R.id.thu3);
        txt_thu3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        txt_fri1 = findViewById(R.id.fri1);
        txt_fri1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        txt_fri2 = findViewById(R.id.fri2);
        txt_fri2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        txt_fri3 = findViewById(R.id.fri3);
        txt_fri3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        txt_sat1 = findViewById(R.id.sat1);
        txt_sat1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        txt_sat2 = findViewById(R.id.sat2);
        txt_sat2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        txt_sat3 = findViewById(R.id.sat3);
        txt_sat3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        txt_sun1 = findViewById(R.id.sun1);
        txt_sun1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        txt_sun2 = findViewById(R.id.sun2);
        txt_sun2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        txt_sun3 = findViewById(R.id.sun3);
        txt_sun3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
