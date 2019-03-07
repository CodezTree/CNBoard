package com.teammgh.cnboard;

public class Meal {
    String meal_date;
    String meal_txt;
    int meal_time_part;
    int id;


    public Meal(String meal_date, String meal_txt, int meal_time_part, int id) {
        this.meal_date = meal_date;
        this.meal_txt = meal_txt;
        this.meal_time_part = meal_time_part;
        this.id = id;
    }
}
