package com.teammgh.cnboard;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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

    Toolbar myToolbar;

    public static ArrayList<Meal> MealList;
    boolean active = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lakeview);

        // 툴바입니다 건들 ㄴㄴ

        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true); // 커스터마이징
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼

        // 툴바입니다 건들 ㄴㄴ

        MealList = new ArrayList<Meal>();
        /*cf>MealList.add(new Meal("2019년 03월 04일", "월요일 조식 메뉴", 0));
        MealList.add(new Meal("2019년 03월 04일", "월요일 중식 메뉴", 1));
        MealList.add(new Meal("2019년 03월 04일", "월요일 석식 메뉴", 2));
        MealList.add(new Meal("2019년 03월 05일", "화요일 조식 메뉴", 0));
        MealList.add(new Meal("2019년 03월 05일", "화요일 중식 메뉴", 1));
        MealList.add(new Meal("2019년 03월 05일", "화요일 석식 메뉴", 2));
        MealList.add(new Meal("2019년 03월 06일", "수요일 조식 메뉴", 0));
        */


        serverDataReceive();

      txt_mon1 = findViewById(R.id.mon1);
//        txt_mon1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (active) {
//
//                    Intent intent = new Intent(LakeViewActivity.this, FoodDetail.class);
//                    intent.putExtra("menu", MealList.get(0).meal_txt);
//                    intent.putExtra("date", MealList.get(0).meal_date);
//                    intent.putExtra("time", MealList.get(0).meal_time_part);
//                    startActivity(intent);
//                }
//            }
//        });
//
        txt_mon2 = findViewById(R.id.mon2);
//        txt_mon2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(LakeViewActivity.this, FoodDetail.class);
//                intent.putExtra("menu", MealList.get(7).meal_txt);
//                intent.putExtra("date", MealList.get(7).meal_date);
//                intent.putExtra("time", MealList.get(7).meal_time_part);
//                startActivity(intent);
//            }
//        });
//
        txt_mon3 = findViewById(R.id.mon3);
//        txt_mon3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(LakeViewActivity.this, FoodDetail.class);
//                intent.putExtra("menu", MealList.get(14).meal_txt);
//                intent.putExtra("date", MealList.get(14).meal_date);
//                intent.putExtra("time", MealList.get(14).meal_time_part);
//                startActivity(intent);
//            }
//        });
//
        txt_tue1 = findViewById(R.id.tue1);
//        txt_tue1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(LakeViewActivity.this, FoodDetail.class);
//                intent.putExtra("menu", MealList.get(1).meal_txt);
//                intent.putExtra("date", MealList.get(1).meal_date);
//                intent.putExtra("time", MealList.get(1).meal_time_part);
//                startActivity(intent);
//            }
//        });
//
        txt_tue2 = findViewById(R.id.tue2);
//        txt_tue2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(LakeViewActivity.this, FoodDetail.class);
//                intent.putExtra("menu", MealList.get(8).meal_txt);
//                intent.putExtra("date", MealList.get(8).meal_date);
//                intent.putExtra("time", MealList.get(8).meal_time_part);
//                startActivity(intent);
//            }
//        });
//
        txt_tue3 = findViewById(R.id.tue3);
//        txt_tue3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(LakeViewActivity.this, FoodDetail.class);
//                intent.putExtra("menu", MealList.get(15).meal_txt);
//                intent.putExtra("date", MealList.get(15).meal_date);
//                intent.putExtra("time", MealList.get(15).meal_time_part);
//                startActivity(intent);
//            }
//        });
//
        txt_wed1 = findViewById(R.id.wed1);
//        txt_wed1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(LakeViewActivity.this, FoodDetail.class);
//                intent.putExtra("menu", MealList.get(2).meal_txt);
//                intent.putExtra("date", MealList.get(2).meal_date);
//                intent.putExtra("time", MealList.get(2).meal_time_part);
//                startActivity(intent);
//            }
//        });
//
        txt_wed2 = findViewById(R.id.wed2);
//
//        txt_wed2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(LakeViewActivity.this, FoodDetail.class);
//                intent.putExtra("menu", MealList.get(9).meal_txt);
//                intent.putExtra("date", MealList.get(9).meal_date);
//                intent.putExtra("time", MealList.get(9).meal_time_part);
//                startActivity(intent);
//            }
//        });
//
        txt_wed3 = findViewById(R.id.wed3);
//
//        txt_wed3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(LakeViewActivity.this, FoodDetail.class);
//                intent.putExtra("menu", MealList.get(16).meal_txt);
//                intent.putExtra("date", MealList.get(16).meal_date);
//                intent.putExtra("time", MealList.get(16).meal_time_part);
//                startActivity(intent);
//            }
//        });
//
        txt_thu1 = findViewById(R.id.thu1);
//
//        txt_thu1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(LakeViewActivity.this, FoodDetail.class);
//                intent.putExtra("menu", MealList.get(3).meal_txt);
//                intent.putExtra("date", MealList.get(3).meal_date);
//                intent.putExtra("time", MealList.get(3).meal_time_part);
//                startActivity(intent);
//            }
//        });
//
        txt_thu2 = findViewById(R.id.thu2);
//
//        txt_thu2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(LakeViewActivity.this, FoodDetail.class);
//                intent.putExtra("menu", MealList.get(10).meal_txt);
//                intent.putExtra("date", MealList.get(10).meal_date);
//                intent.putExtra("time", MealList.get(10).meal_time_part);
//                startActivity(intent);
//            }
//        });
//
        txt_thu3 = findViewById(R.id.thu3);
//
//        txt_thu3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(LakeViewActivity.this, FoodDetail.class);
//                intent.putExtra("menu", MealList.get(17).meal_txt);
//                intent.putExtra("date", MealList.get(17).meal_date);
//                intent.putExtra("time", MealList.get(17).meal_time_part);
//                startActivity(intent);
//            }
//        });
//
        txt_fri1 = findViewById(R.id.fri1);
//
//        txt_fri1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(LakeViewActivity.this, FoodDetail.class);
//                intent.putExtra("menu", MealList.get(4).meal_txt);
//                intent.putExtra("date", MealList.get(4).meal_date);
//                intent.putExtra("time", MealList.get(4).meal_time_part);
//                startActivity(intent);
//            }
//        });
//
        txt_fri2 = findViewById(R.id.fri2);
//
//        txt_fri2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(LakeViewActivity.this, FoodDetail.class);
//                intent.putExtra("menu", MealList.get(11).meal_txt);
//                intent.putExtra("date", MealList.get(11).meal_date);
//                intent.putExtra("time", MealList.get(11).meal_time_part);
//                startActivity(intent);
//            }
//        });
//
        txt_fri3 = findViewById(R.id.fri3);
//
//        txt_fri3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(LakeViewActivity.this, FoodDetail.class);
//                intent.putExtra("menu", MealList.get(18).meal_txt);
//                intent.putExtra("date", MealList.get(18).meal_date);
//                intent.putExtra("time", MealList.get(18).meal_time_part);
//                startActivity(intent);
//            }
//        });
//
        txt_sat1 = findViewById(R.id.sat1);
//
//        txt_sat1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(LakeViewActivity.this, FoodDetail.class);
//                intent.putExtra("menu", MealList.get(5).meal_txt);
//                intent.putExtra("date", MealList.get(5).meal_date);
//                intent.putExtra("time", MealList.get(5).meal_time_part);
//                startActivity(intent);
//            }
//        });
//
        txt_sat2 = findViewById(R.id.sat2);
//
//        txt_sat2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(LakeViewActivity.this, FoodDetail.class);
//                intent.putExtra("menu", MealList.get(12).meal_txt);
//                intent.putExtra("date", MealList.get(12).meal_date);
//                intent.putExtra("time", MealList.get(12).meal_time_part);
//                startActivity(intent);
//            }
//        });
//
        txt_sat3 = findViewById(R.id.sat3);
//
//        txt_sat3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(LakeViewActivity.this, FoodDetail.class);
//                intent.putExtra("menu", MealList.get(19).meal_txt);
//                intent.putExtra("date", MealList.get(19).meal_date);
//                intent.putExtra("time", MealList.get(19).meal_time_part);
//                startActivity(intent);
//            }
//        });
//
        txt_sun1 = findViewById(R.id.sun1);
//
//        txt_sun1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(LakeViewActivity.this, FoodDetail.class);
//                intent.putExtra("menu", MealList.get(6).meal_txt);
//                intent.putExtra("date", MealList.get(6).meal_date);
//                intent.putExtra("time", MealList.get(6).meal_time_part);
//                startActivity(intent);
//            }
//        });
//
        txt_sun2 = findViewById(R.id.sun2);
//
//        txt_sun2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(LakeViewActivity.this, FoodDetail.class);
//                intent.putExtra("menu", MealList.get(13).meal_txt);
//                intent.putExtra("date", MealList.get(13).meal_date);
//                intent.putExtra("time", MealList.get(13).meal_time_part);
//                startActivity(intent);
//            }
//        });
//
        txt_sun3 = findViewById(R.id.sun3);
//
//        txt_sun3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(LakeViewActivity.this, FoodDetail.class);
//                intent.putExtra("menu", MealList.get(20).meal_txt);
//                intent.putExtra("date", MealList.get(20).meal_date);
//                intent.putExtra("time", MealList.get(20).meal_time_part);
//                startActivity(intent);
//            }
//        });
    }

    public void serverDataReceive() {
        //String serverData = 서버에서 가져온 데이터 (json 형)
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.d("Debug", response);
                    MealList = ParseMealJson(response);
                    txt_mon1.setText(MealList.get(0).meal_txt);
                    txt_mon2.setText(MealList.get(7).meal_txt);
                    txt_mon3.setText(MealList.get(14).meal_txt);
                    txt_tue1.setText(MealList.get(1).meal_txt);
                    txt_tue2.setText(MealList.get(8).meal_txt);
                    txt_tue3.setText(MealList.get(15).meal_txt);
                    txt_wed1.setText(MealList.get(2).meal_txt);
                    txt_wed2.setText(MealList.get(9).meal_txt);
                    txt_wed3.setText(MealList.get(16).meal_txt);
                    txt_thu1.setText(MealList.get(3).meal_txt);
                    txt_thu2.setText(MealList.get(10).meal_txt);
                    txt_thu3.setText(MealList.get(17).meal_txt);
                    txt_fri1.setText(MealList.get(4).meal_txt);
                    txt_fri2.setText(MealList.get(11).meal_txt);
                    txt_fri3.setText(MealList.get(18).meal_txt);
                    txt_sat1.setText(MealList.get(5).meal_txt);
                    txt_sat2.setText(MealList.get(12).meal_txt);
                    txt_sat3.setText(MealList.get(19).meal_txt);
                    txt_sun1.setText(MealList.get(6).meal_txt);
                    txt_sun2.setText(MealList.get(13).meal_txt);
                    txt_sun3.setText(MealList.get(20).meal_txt);

                    active = true;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        RefreshMeal refreshMeal = new RefreshMeal(responseListener);
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(refreshMeal);
        Log.d("Debug","added queue");


    }

    private ArrayList<Meal> ParseMealJson(String response) {

        ArrayList<Meal> MealList = new ArrayList<>();

        JsonParser jsonParser = new JsonParser();
        JsonArray jsonArray = (JsonArray) jsonParser.parse(response);

        for (int i = 0; i < jsonArray.size(); i++) {

            JsonObject object = (JsonObject) jsonArray.get(i);

            int time = object.get("meal_time_part").getAsInt();
            int id = object.get("id").getAsInt();
            String meal = object.get("meal_txt").getAsString();
            String meal2 = meal.replaceFirst("\n", "")+"\n"; //줄바꿈 삭제+줄바꿈 추가
            String date = object.get("meal_date").getAsString();

            Log.d("시간",String.valueOf(time));
            Log.d("아이디",String.valueOf(id));
            Log.d("식단",meal);
            Log.d("날짜",date);

            MealList.add(new Meal(date, meal2, time, id));
            Log.d("디버그,",MealList.get(0).meal_date);

        }
        return MealList;
    }
}
