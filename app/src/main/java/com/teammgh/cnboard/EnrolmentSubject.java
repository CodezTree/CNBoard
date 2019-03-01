package com.teammgh.cnboard;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

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
import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import static com.teammgh.cnboard.Global.arrDataS;
import static com.teammgh.cnboard.Global.arrKeyS;
import static com.teammgh.cnboard.Global.arrSubjectMemo;
import static com.teammgh.cnboard.Global.categoryNoS;
import static com.teammgh.cnboard.Global.examRangeList;
import static com.teammgh.cnboard.Global.grade;
import static com.teammgh.cnboard.Global.intMySubject;
import static com.teammgh.cnboard.Global.myGradeNCode;
import static com.teammgh.cnboard.Global.mySubData1;
import static com.teammgh.cnboard.Global.mySubject;
import static com.teammgh.cnboard.Global.mySubjectList;
import static com.teammgh.cnboard.Global.mySubjectListR;
import static com.teammgh.cnboard.Global.subjectIndexL;
import static com.teammgh.cnboard.Global.subjectIndexS;

//Edittext, SubRangeSave_btn 필요 없음
public class EnrolmentSubject extends AppCompatActivity {

    Button grade1_btn, grade2_btn, grade3_btn, Save_btn, Del_btn;
    Spinner Category_spin, Subject_spin;
    public ArrayAdapter<String> Categpry, Subject, adapter;
    private ListView listview;

    String[] arrCate = new String[3];
    String[] arrSubject = new String[3];
    public static ArrayList<MyGradeNcode> mySubject;
    public static ArrayList<ExamData> Examlist;
    Integer arrKey1;

    int subjectMemoIndex = 0;
    String subjectIndex1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enrolment_studentt);

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

        listview.setVisibility(View.INVISIBLE);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, examRangeList);

        Examlist = getDataOnServer();
        First();
        Warning();

        arrSubject[0] = "01:01:국어,01:02:영어,01:03:일본어1,01:04:중국어1,01:05:통합사회,01:06:한국사,02:07:수학,02:08:통합과학,02:09:기술가정,03:10:음악연주,03:11:체육";    // 1학년 과목
        arrSubject[1] = "01:01:철학,01:02:언어와 매체,01:03:문예 창작 입문,01:04:문학 개론,01:05:영어1,01:06:실용영어,01:07:심화 영어 회화1,01:08:영어권 문화,01:09:중국어2,02:10:사회 탐구 방법,02:11:사회 문제 탐구,02:12:사회문화,02:13:세계사,02:14:윤리와 사상,02:15:정치와 법,02:16:한국지리,02:17:경제,03:18:수학1,03:19:수학2,03:20:화학1,03:21:물리학1,03:22:생명과학1,04:23:정보과학,04:24:공학일반,05:25:미술,05:26:운동과 건강,05:27:음악 이론,05:28:체육과 진로탐구";        // 2학년 카테고리
        arrSubject[2] = "01:01:교육학,01:02:논리학논술,01:03:심화 영어 독해1,01:04:영어회화,01:05:일본어회화1,01:06:국어작문,01:07:화법과 작문,02:08:국제경제,02:09:국제법,02:10:비교문화,02:11:사회문화,02:12:생활과윤리,02:13:세계지리,02:14:한국의사회와문화,03:15:고급물리,03:16:고급생명과학,03:17:고급수학,03:18:고급화학,03:19:고급물리,03:20:생명과학2,03:21:생명과학실험,03:22:심화물리,03:23:심화수학1,03:24:심화수학2,03:25:심화화학,03:26:화학실험,03:27:환경과학,04:28:공학기술,04:29:정보통신,04:30:정보과학(3학년),04:31:로봇제작,05:32:마케팅과 광고,05:33:시창청음,05:34:미술사,05:35:스포츠과학,05:36:영화감상과비평,05:37:육상운동,03:38:입체조형,03:39:제품디자인,03:40:진로와직업,03:41:체력운동";        // 3학년 카테고리

        arrCate[0] = "문과,이과,예체";        // 1학년 카테고리
        arrCate[1] = "국제인문,사회과학,자연과학,it,예체";        // 2학년 카테고리
        arrCate[2] = "국제인문,사회과학,자연과학,it,예체";        // 3학년 카테고리

        // 1. 카테고리 스피너 생성

        grade1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grade = 0;

                Toast.makeText(getApplicationContext(), "1학년", Toast.LENGTH_SHORT).show();
                setCategory();
            }
        });

        grade2_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grade = 1;
                Toast.makeText(getApplicationContext(), "2학년", Toast.LENGTH_SHORT).show();
                setCategory();
            }
        });

        grade3_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grade = 2;
                Toast.makeText(getApplicationContext(), "1학년", Toast.LENGTH_SHORT).show();
                setCategory();
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

                arrKey1 = arrKeyS.get(subjectIndexS);// arrKey[2] => 06

                //myGradeNCode.add(grade, String.valueOf(arrKey1));//myGradeNCode는 사용자가 추가한 시험과목의 학년과 과목 코드 값 >> Shared preference로 저장need

                MyGradeNcode A = new MyGradeNcode();
                A.myCode = arrKey1;
                A.myGrade = grade;

                myGradeNCode.add(A);

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

        // 저장//TODO
        Save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //getSharedPreferences 로 mySubject 저장
                saveData();

                Toast.makeText(getApplicationContext(), "저장되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                subjectIndexL = position;
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

        String[] arrItem1 = arrSubject[grade].split(",");     // A001 특정학년의 과목 목록    - [0]01:01:국어,[1]01:02:영어....[11]03:11:체욱

        // 사용자가 선택한 학년의 과목배열 전체를 루프 실행
        for (int i = 0; i < arrItem1.length; i++) {
            String[] arrItem2 = arrItem1[i].split(":");    // [0]01, [1]01, [2]국어

            // 과목의 카테고리가 학생이 선택한 카테고리와 같으면 배열에 추가
            if (Integer.parseInt(arrItem2[0]) - 1 == categoryNoS)                // A003  index는 스피너에 들어있는 원소의 번호
            {
                arrKeyS.add(Integer.parseInt(arrItem2[1]));    // 04, 05, 06
                arrDataS.add(arrItem2[2]);    // 컴퓨터, 수학, 기계

            }
        }
        // arrDataS를 과목 스피너에 추가 : 컴퓨터, 수학, 기계

        Subject = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrDataS);
        Subject_spin.setAdapter(Subject);

    }

    //TODO
    private String serverDataReceive() {

        //String serverData = 서버에서 가져온 데이터 (json 형)
        return "a";
    }

    private void onListItemAdd(int grade, int code) {

        String range, name;

        for (int i = 0; i < Examlist.size(); i++) {

            ExamData tempExam = Examlist.get(i);

            if ((code == tempExam.exam_code) && (grade == tempExam.target_grade)) {

                // 우리가 찾던 시험 데이터

                range = tempExam.exam_range;
                name = tempExam.exam_name;

                examRangeList.add(name + " : " + range); // examRangeLIst로 리스트뷰 구성

                break;
            }

        }
        adapter.notifyDataSetChanged();
    }

    private ArrayList<ExamData> getDataOnServer() {


        ArrayList<ExamData> tempExamArr = new ArrayList<>();
        String response;

        response = serverDataReceive(); //string반환
        JsonParser jsonParser = new JsonParser();
        JsonArray jsonArray = (JsonArray) jsonParser.parse(response);


        for (int i = 0; i < jsonArray.size(); i++) {

            JsonObject object = (JsonObject) jsonArray.get(i);

            int grade = object.get("target_grade").getAsInt();
            int code = object.get("exam_code").getAsInt();
            String name = object.get("exam_name").getAsString();
            String range = object.get("exam_range").getAsString();

            tempExamArr.add(new ExamData(grade, code, name, range));
        }
        return tempExamArr;
    }
    private void saveData() {

        SharedPreferences mySubData = getSharedPreferences("mySubData", MODE_PRIVATE);
        SharedPreferences.Editor editor = mySubData.edit();
        Gson gson = new Gson();
        // JSON 으로 변환
        String strmySub = gson.toJson(myGradeNCode, EnrolmentActivity.class);
        editor.putString("mySubData", strmySub); // JSON으로 변환한 객체를 저장한다.
        editor.apply(); //완료한다.
        init();
        Global.grade = -1;
    }
    protected void getData(){ // 앱 껏다가 켰을 때 자기가 저장한 시험범위 리스트를 잃어버리지 않도록 저장해주는 역할
        mySubData1 = getSharedPreferences("mySubData", MODE_PRIVATE);
        Gson gson = new Gson();
        String strmySub = mySubData1.getString("mySubData", "");
        // 변환
        Type type = new TypeToken<ArrayList<EnrolmentActivity>>() {}.getType();
        mySubject = gson.fromJson(strmySub,type); //String 배열로 변환해야함 ArrayList<ExamData>

        for (int i =0; i<= mySubject.size();i++){

                 MyGradeNcode temp = mySubject.get(i);

                    grade = temp.myGrade;
                    arrKey1 = temp.myCode;

            onListItemAdd(grade,arrKey1);

        }
    }

    private void First(){
        if(mySubData1.contains("mySubData")){
            getData();
        }
    }
    private void Warning(){

        if( Global.a >= 2) {
            if (!mySubData1.contains("mySubData")) {
                init();
                AlertDialog.Builder dialog = new AlertDialog.Builder(EnrolmentSubject.this);
                dialog.setTitle("초기화 경고")
                        .setMessage("시험범위가 저장되지 않았습니다. 시험범위를 저장하지 않은 채 학년 버튼을 다시 누르면 선택한 것이 모두 초기화 됩니다. 그래도 계속 진행하시겠습니까?")
                        .setPositiveButton("계속 진행합니다", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(EnrolmentSubject.this, "현재까지의 선택이 모두 초기화 되었습니다. 처음부터 다시 진행해주십시요", Toast.LENGTH_SHORT).show();
                                //OnClick 리스너 실행, 초기화
                                init();
                            }
                        })
                        .setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(EnrolmentSubject.this, "계속 진행하지 않습니다.", Toast.LENGTH_SHORT).show();
                                //OnClick 리스너 실행하지 않음, 초기화 하지 않음
                            }
                        }).create().show();
            }
        }
    }
    public void onBackPressed() {

        // Alert을 이용해 종료시키기
        if(!mySubData1.contains("mySubData")){
            AlertDialog.Builder dialog = new AlertDialog.Builder(EnrolmentSubject.this);
            dialog .setTitle("부적절한 종료 경고")
                    .setMessage("선택된 시험범위가 저장되지 않았습니다. 그래도 종료하시겠습니까? /n 종료시 선택된 시험범위가 저장되지 않아 오류가 발생 할 수 있습니다.")
                    .setPositiveButton("종료합니다", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setNeutralButton("취소합니다", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(EnrolmentSubject.this, "취소했습니다. 저장 버튼을 눌러 선택된 시험범위를 저장해 주십시오.", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(EnrolmentSubject.this, "종료하지 않습니다. 저장 버튼을 눌러 선택된 시험범위를 저장해 주십시오", Toast.LENGTH_SHORT).show();
                        }
                    }).create().show();
        }
    }
    private void Delete(){
        myGradeNCode.remove(subjectIndexL);
        Toast.makeText(getApplicationContext(),"삭제되었습니다.",Toast.LENGTH_SHORT).show();
    }
}

