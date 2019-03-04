package com.teammgh.cnboard;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.lang.reflect.Type;
import java.util.ArrayList;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import static com.teammgh.cnboard.Global.arrDataS;
import static com.teammgh.cnboard.Global.arrKeyS;
import static com.teammgh.cnboard.Global.arrSubjectMemo;
import static com.teammgh.cnboard.Global.categoryNoS;
import static com.teammgh.cnboard.Global.examRangeList;
import static com.teammgh.cnboard.Global.grade;
import static com.teammgh.cnboard.Global.tempExamArr;
import static com.teammgh.cnboard.Global.myGradeNCode;
import static com.teammgh.cnboard.Global.mySubData1;
import static com.teammgh.cnboard.Global.subjectIndexL;
import static com.teammgh.cnboard.Global.subjectIndexS;

public class EnrolmentSubject extends AppCompatActivity  {

    Button grade1_btn, grade2_btn, grade3_btn, Save_btn, Del_btn,Init_btn;
    Spinner Category_spin, Subject_spin;
    public ArrayAdapter<String> Categpry, Subject, adapter;
    private ListView listview;
    LinearLayout Linear;

    String[] arrCate = new String[3];
    String[] arrSubject = new String[3];
    public static ArrayList<MyGradeNcode> mySubject;
    Integer arrKey1;

    boolean isSaved = false;

    Toolbar myToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enrolment_studentt);

        // 툴바입니다 건들 ㄴㄴ

        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true); // 커스터마이징
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼

        // 툴바입니다 건들 ㄴㄴ

        arrSubjectMemo.add(new ArrayList<String>());
        arrSubjectMemo.add(new ArrayList<String>());
        arrSubjectMemo.add(new ArrayList<String>());

        grade1_btn = findViewById(R.id.grade1_btn);
        grade2_btn = findViewById(R.id.grade2_btn);
        grade3_btn = findViewById(R.id.grade3_btn);
        Category_spin = findViewById(R.id.Category_spin);
        Subject_spin = findViewById(R.id.Subject_spin);
        listview = findViewById(R.id.listview);
        Save_btn = findViewById(R.id.save_btn);
        Linear = findViewById(R.id.Linear);
        Init_btn = findViewById(R.id.init_btn);

        //serverDataReceive();

        listview.setVisibility(View.INVISIBLE);

        arrSubject[0] = "01:01:국어,01:02:영어,01:03:일본어1,01:04:중국어1,01:05:통합사회,01:06:한국사,02:07:수학,02:08:통합과학,02:09:기술가정,03:10:음악연주,03:11:체육";    // 1학년 과목
        arrSubject[1] = "01:01:철학,01:02:언어와 매체,01:03:문예 창작 입문,01:04:문학 개론,01:05:영어1,01:06:실용영어,01:07:심화 영어 회화1,01:08:영어권 문화,01:09:중국어2,02:10:사회 탐구 방법,02:11:사회 문제 탐구,02:12:사회문화,02:13:세계사,02:14:윤리와 사상,02:15:정치와 법,02:16:한국지리,02:17:경제,03:18:수학1,03:19:수학2,03:20:화학1,03:21:물리학1,03:22:생명과학1,04:23:정보과학,04:24:공학일반,05:25:미술,05:26:운동과 건강,05:27:음악 이론,05:28:체육과 진로탐구";        // 2학년 카테고리
        arrSubject[2] = "01:01:교육학,01:02:논리학논술,01:03:심화 영어 독해1,01:04:영어회화,01:05:일본어회화1,01:06:국어작문,01:07:화법과 작문,02:08:국제경제,02:09:국제법,02:10:비교문화,02:11:사회문화,02:12:생활과윤리,02:13:세계지리,02:14:한국의사회와문화,03:15:고급물리,03:16:고급생명과학,03:17:고급수학,03:18:고급화학,03:19:고급물리,03:20:생명과학2,03:21:생명과학실험,03:22:심화물리,03:23:심화수학1,03:24:심화수학2,03:25:심화화학,03:26:화학실험,03:27:환경과학,04:28:공학기술,04:29:정보통신,04:30:정보과학(3학년),04:31:로봇제작,05:32:마케팅과 광고,05:33:시창청음,05:34:미술사,05:35:스포츠과학,05:36:영화감상과비평,05:37:육상운동,03:38:입체조형,03:39:제품디자인,03:40:진로와직업,03:41:체력운동";        // 3학년 카테고리

        arrCate[0] = "문과,이과,예체";                            // 1학년 카테고리
        arrCate[1] = "국제인문,사회과학,자연과학,it,예체";        // 2학년 카테고리
        arrCate[2] = "국제인문,사회과학,자연과학,it,예체";        // 3학년 카테고리

        // 1. 카테고리 스피너 생성

        grade1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                grade = 0;
                Toast.makeText(getApplicationContext(), "1학년", Toast.LENGTH_SHORT).show();
                setCategory();
                grade1_btn.setBackgroundResource(R.drawable.grade1btn_rectangle);
                grade2_btn.setBackgroundResource(R.drawable.gradebtn_rectangle);
                grade3_btn.setBackgroundResource(R.drawable.gradebtn_rectangle);
                grade1_btn.setTextColor(Color.rgb(255, 255, 255));

                Linear.setBackgroundColor(Color.rgb(255, 248, 248));
            }
        });

        grade2_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grade = 1;
                Toast.makeText(getApplicationContext(), "2학년", Toast.LENGTH_SHORT).show();
                setCategory();
                Linear.setBackgroundColor(Color.rgb(241, 255, 241));
                grade2_btn.setTextColor(Color.rgb(255, 255, 255));

                grade2_btn.setBackgroundResource(R.drawable.grade2btn_rectangle);
                grade3_btn.setBackgroundResource(R.drawable.gradebtn_rectangle);
                grade1_btn.setBackgroundResource(R.drawable.gradebtn_rectangle);
            }
        });

        grade3_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grade = 2;
                Toast.makeText(getApplicationContext(), "1학년", Toast.LENGTH_SHORT).show();
                setCategory();
                Linear.setBackgroundColor(Color.rgb(241, 242, 252));
                grade3_btn.setTextColor(Color.rgb(255, 255, 255));

                grade3_btn.setBackgroundResource(R.drawable.grade3btn_rectangle);
                grade1_btn.setBackgroundResource(R.drawable.gradebtn_rectangle);
                grade2_btn.setBackgroundResource(R.drawable.gradebtn_rectangle);

            }
        });

        //=====================================================================================
        // 3. 사용자가 카테고리를 선택하면 해당 학년의 해당 카테고리의 과목 스피너를 생성한다.
        //    - 사용자가 카테고리를 선택했을때 카테고리 번호를 설정한다.
        //=====================================================================================

        Category_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Global.categoryNoS = position;

                Log.d("카테고리넘버",String.valueOf(categoryNoS));

                setSubject();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


        //=====================================================================================
        // 4. 사용자가  과목을 선택하면 선생님이 과목설명을 입력하는 란을 생성한다.
        //    - 과목 스피너를 선택햇을때 과목 index를 설정한다.
        //=====================================================================================

        Subject_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Global.subjectIndexS = position;   //subjectIndex = spinnerSubject.SelectedIndex;
                listview.setVisibility(View.VISIBLE);

                //TODO 밑에서 저장한 arrKeyS 가 여기서 연동이 안됨!!! add한게 적용이 안덴!!!!!!! 연동시켜야함 ㅠㅠ

                arrKey1 = arrKeyS.get(subjectIndexS);// arrKey[2] => 06

                Log.d("subjectIndexS ",String.valueOf(subjectIndexS ));
                Log.d("arrKey1",String.valueOf(arrKey1));
                Log.d("arrKeyS.get(subjectInde",String.valueOf(arrKeyS.get(subjectIndexS)));
                Log.d("arrKeyS.0",String.valueOf(arrKeyS.get(0)));
                Log.d("arrKeyS.1",String.valueOf(arrKeyS.get(1)));

                //myGradeNCode.add(grade, String.valueOf(arrKey1));//myGradeNCode는 사용자가 추가한 시험과목의 학년과 과목 코드 값 >> Shared preference로 저장need

                MyGradeNcode A = new MyGradeNcode();
                A.myCode = arrKey1;
                A.myGrade = grade;
                Log.d("myGrade",String.valueOf(A.myGrade));
                Log.d("mycode",String.valueOf(A.myCode));

                myGradeNCode.add(A);
                //Log.d("myGradeNCode1",String.valueOf(myGradeNCode.get(1).myCode));
                //Log.d("myGradeNCode2",String.valueOf(myGradeNCode.get(1).myGrade));

                onListItemAdd(grade, arrKey1);

                try {
                    JSONArray jArray = new JSONArray();//배열
                    for (int i = 0; i < myGradeNCode.size(); i++) {
                        JSONObject sObject = new JSONObject();//배열 내에 들어갈 json
                        sObject.put("myGrade", myGradeNCode.get(i).myGrade);
                        sObject.put("myCode", myGradeNCode.get(i).myCode);
                        jArray.put(sObject);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

        });
        Init_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder dialog = new AlertDialog.Builder(EnrolmentSubject.this);
                dialog.setTitle("초기화")
                        .setMessage("현재까지 선택된 모든 시험범위 목록이 삭제됩니다. 정말 초기화 하시겠습니까?")
                        .setPositiveButton("초기화", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(EnrolmentSubject.this, "초기화 되었습니다", Toast.LENGTH_SHORT).show();
                                init();
                                myGradeNCode = new ArrayList<>();
                            }
                        })
                        .setNeutralButton("취소합니다", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(EnrolmentSubject.this, "취소했습니다.", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        Save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //getSharedPreferences 로 mySubject 저장
                saveData();
                getData();

                Toast.makeText(getApplicationContext(), "저장되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                subjectIndexL = position;
                Del_btn.setVisibility(View.VISIBLE);
                Del_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Delete();
                    }
                });
            }
        });
    }

    private void init() {
        mySubject = new ArrayList<>();
        grade = -1;
        arrDataS = new ArrayList<>();
        subjectIndexS = -1;
        arrKeyS = new ArrayList<>();
        Global.categoryNoS = -1;
        Global.a = 0;
    }

    private void setCategory() {
        String[] arrItem = arrCate[grade].split(",");// 특정 학년의 카테고리 목록


        // arrItem 을 스피너에 추가
        Categpry = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrItem);
        Category_spin.setAdapter(Categpry);
    }

    private void setSubject() {
        arrDataS = new ArrayList<>();

        String[] arrItem1 = arrSubject[grade].split(",");// A001 특정학년의 과목 목록    - [0]01:01:국어,[1]01:02:영어....[11]03:11:체욱
        Log.d("카테고리넘버",String.valueOf(categoryNoS));

        // 사용자가 선택한 학년의 과목배열 전체를 루프 실행
        for (int i = 0; i < arrItem1.length; i++) {
            String[] arrItem2 = arrItem1[i].split(":");    // [0]01, [1]01, [2]국어

            // 과목의 카테고리가 학생이 선택한 카테고리와 같으면 배열에 추가
            if (Integer.parseInt(arrItem2[0]) - 1 == categoryNoS)// A003  index는 스피너에 들어있는 원소의 번호
            {


                Log.d("key",arrItem2[1]);
                arrKeyS.add(Integer.parseInt(arrItem2[1]));    // 04, 05, 06
                Log.d("arrKeyS",String.valueOf(Integer.parseInt(arrItem2[1])));
                arrDataS.add(arrItem2[2]);    // 컴퓨터, 수학, 기계

                //TODO arrKeyS에 add 하면 이 Add 한 것들이 SubjectSpinner OnItemSelected 메소드의 arrKeyS에 반영이 안됨!!!!



            }
        }

        arrDataS.add("과목");
        // arrDataS를 과목 스피너에 추가 : 컴퓨터, 수학, 기계

        Subject = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrDataS);
        Subject_spin.setAdapter(Subject);

    }
    @Override
    public Context getApplicationContext() {
        return super.getApplicationContext();

    }

    public void serverDataReceive() {
        //String serverData = 서버에서 가져온 데이터 (json 형)
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    ParseExamJson(response);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        RefreshExam refreshExam = new RefreshExam(responseListener);
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        queue.add(refreshExam);
    }

    private ArrayList<ExamData> ParseExamJson(String response) {

        ArrayList<ExamData> tempExamArr = new ArrayList<>();

        JsonParser jsonParser = new JsonParser();
        JsonArray jsonArray = (JsonArray) jsonParser.parse(response);

        for (int i = 0; i < jsonArray.size(); i++) {

            JsonObject object = (JsonObject) jsonArray.get(i);

            int grade = object.get("target_grade").getAsInt();
            int code = object.get("exam_code").getAsInt();
            String name = object.get("exam_name").getAsString();
            String range = object.get("exam_range").getAsString();

            Log.d("학년", String.valueOf(grade));
            Log.d("코드", String.valueOf(code));
            Log.d("이름", name);
            Log.d("범위", range);

            tempExamArr.add(new ExamData(grade, code, name, range));

        }
        return tempExamArr;
    }

    private void onListItemAdd(int grade, int code) {

        String range, name;

        if (tempExamArr == null)
        {
            return;
        }

        for (int i = 0; i < tempExamArr.size(); i++) {

            ExamData tempExam = tempExamArr.get(i);

            if ((code == tempExam.exam_code) && (grade == tempExam.target_grade)) {

                // 우리가 찾던 시험 데이터

                range = tempExam.exam_range;
                name = tempExam.exam_name;

                examRangeList.add(name + " : " + range); // examRangeLIst로 리스트뷰 구성

                break;
            }

        }
        adapter = new SpinnerAdapter(this, android.R.layout.simple_list_item_1, examRangeList);
        adapter.notifyDataSetChanged();
    }

    private void saveData() {

        JSONObject obj = new JSONObject();
        try {
            JSONArray jArray = new JSONArray();//배열이 필요할때
            for (int i = 0; i < myGradeNCode.size(); i++)//배열
            {
                JSONObject sObject = new JSONObject();//배열 내에 들어갈 json
                sObject.put("SelectedGrade", myGradeNCode.get(i).myGrade);
                sObject.put("SelectedCode", myGradeNCode.get(i).myCode);
                jArray.put(sObject);
            }
            obj.put("Arr_myGradeNcode", jArray);//배열을 넣음
            obj.toString();

            SharedPreferences mySubData = getSharedPreferences("mySubData", MODE_PRIVATE);
            SharedPreferences.Editor editor = mySubData.edit();
            Gson gson = new Gson();
            // JSON 으로 변환
            String strmySub = gson.toJson(obj, MyGradeNcode.class);
            editor.putString("mySubData", strmySub); // JSON으로 변환한 객체를 저장한다.
            editor.apply(); //완료한다.
            init();
            Global.grade = -1;

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    protected void getData(){ // 앱 껏다가 켰을 때 자기가 저장한 시험범위 리스트를 잃어버리지 않도록 저장해주는 역할
        mySubData1 = getSharedPreferences("mySubData", MODE_PRIVATE);
        Gson gson = new Gson();
        String strmySub = mySubData1.getString("mySubData", "");
        // 변환
        Type type = new TypeToken<ArrayList<MyGradeNcode>>() {}.getType();
        mySubject = gson.fromJson(strmySub,type); //String 배열로 변환해야함 ArrayList<ExamData>

        for (int i =0; i<= mySubject.size();i++){

                 MyGradeNcode temp = mySubject.get(i);

                    grade = temp.myGrade;
                    arrKey1 = temp.myCode;

            onListItemAdd(grade,arrKey1);

        }
    }

    private void First(){
        if(myGradeNCode.size()>=1){
            getData();
        }
    }
    public void onBackPressed() {

        // Alert을 이용해 종료시키기


        AlertDialog.Builder dialog = new AlertDialog.Builder(EnrolmentSubject.this);
        dialog .setTitle("종료 경고")
                .setMessage("작업하신 내용을 저장하셨습니까? 저장하지 않은 경우 작업내용이 무시됩니다.그래도 종료하시겠습니까?")
                .setPositiveButton("종료합니다", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNeutralButton("취소합니다", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(EnrolmentSubject.this, "취소했습니다.", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(EnrolmentSubject.this, "종료하지 않습니다.", Toast.LENGTH_SHORT).show();
                    }
                }).create().show();


    }
    private void Delete(){
        myGradeNCode.remove(subjectIndexL);
        Toast.makeText(getApplicationContext(),"삭제되었습니다.",Toast.LENGTH_SHORT).show();
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}

