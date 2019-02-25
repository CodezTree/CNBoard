package com.teammgh.cnboard;

import android.content.SharedPreferences;

import java.util.ArrayList;

public class Global {

    public static int subjectIndex = -1;
    public static int categoryNo = -1;
    public static int grade = -1;
    public static ArrayList<Integer> arrKey = new ArrayList<Integer>();       // 특정학년의 과목의 키번호 배얼
    public static ArrayList<String> arrData = new ArrayList<String>();        // 특정학년의 과목이름 배열

    public static ArrayList<String> mySubject = new ArrayList<String>(); //학생별 시험범위 리스트 > listview용
    public static ArrayList<ArrayList<String>> mySubjectList = new ArrayList<>();  // 학생별 시험범위 리스트 >> 서버 업로드 ㅜ용

    public static SharedPreferences mySubData1;
    public static int a;



}
