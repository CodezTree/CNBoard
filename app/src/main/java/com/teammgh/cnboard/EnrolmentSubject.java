package com.teammgh.cnboard;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import static android.view.View.VISIBLE;
import static com.teammgh.cnboard.Global.arrDataS;
import static com.teammgh.cnboard.Global.arrKeyS;
import static com.teammgh.cnboard.Global.categoryNoS;
import static com.teammgh.cnboard.Global.examRangeList;
import static com.teammgh.cnboard.Global.grade;
import static com.teammgh.cnboard.Global.sp;
import static com.teammgh.cnboard.Global.tempExamArr;
import static com.teammgh.cnboard.Global.myGradeNCode;
import static com.teammgh.cnboard.Global.subjectIndexS;

public class EnrolmentSubject extends AppCompatActivity {

    Button grade1_btn, grade2_btn, grade3_btn, Save_btn, Init_btn, getData_btn;
    Spinner Category_spin, Subject_spin;
    public ArrayAdapter<String> Categpry, Subject, adapter;
    private ListView listview;
    LinearLayout Linear,layout_serviceAvailable,layout_serviceNotAvailable;
    private Gson gson;
    boolean Active, GetData = false;
    String serverURL = "http://45.32.49.247:8000/Service/exams/serviceAvailCheck/";
    int serviceAvailablity;
    TextView txt1,txt2;

    String[] arrCate = new String[3];
    String[] arrSubject = new String[3];
    Integer arrKey1;
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

        grade1_btn = findViewById(R.id.grade1_btn);
        grade2_btn = findViewById(R.id.grade2_btn);
        grade3_btn = findViewById(R.id.grade3_btn);
        Category_spin = findViewById(R.id.Category_spin);
        Subject_spin = findViewById(R.id.Subject_spin);
        listview = findViewById(R.id.listview);
        Save_btn = findViewById(R.id.save_btn);
        Linear = findViewById(R.id.LInear);
        Init_btn = findViewById(R.id.init_btn);
        getData_btn = findViewById(R.id.btn_getData);
        layout_serviceAvailable = findViewById(R.id.layout_serviceAvailable);
        layout_serviceNotAvailable = findViewById(R.id.layout_serviceNotAvailable);
        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);

        serverDataReceive();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, examRangeList);
        listview.setAdapter(adapter);

        arrSubject[0] = "01:01:국어,01:02:영어,01:03:일본어1,01:04:중국어1,01:05:통합사회,01:06:한국사,02:07:수학,02:08:통합과학,02:09:기술가정,03:10:음악연주,03:11:체육";    // 1학년 과목
        arrSubject[1] = "01:01:철학,01:02:언어와 매체,01:03:문예 창작 입문,01:04:문학 개론,01:05:영어1,01:06:실용영어,01:07:심화 영어 회화1,01:08:영어권 문화,01:09:중국어2,02:10:사회 탐구 방법,02:11:사회 문제 탐구,02:12:사회문화,02:13:세계사,02:14:윤리와 사상,02:15:정치와 법,02:16:한국지리,02:17:경제,03:18:수학1,03:19:수학2,03:20:화학1,03:21:물리학1,03:22:생명과학1,04:23:정보과학,04:24:공학일반,05:25:미술,05:26:운동과 건강,05:27:음악 이론,05:28:체육과 진로탐구";        // 2학년 카테고리
        arrSubject[2] = "01:01:교육학,01:02:논리학논술,01:03:심화 영어 독해1,01:04:영어회화,01:05:일본어회화1,01:06:국어작문,01:07:화법과 작문,02:08:국제경제,02:09:국제법,02:10:비교문화,02:11:사회문화,02:12:생활과윤리,02:13:세계지리,02:14:한국의사회와문화,03:15:고급물리,03:16:고급생명과학,03:17:고급수학,03:18:고급화학,03:19:고급물리,03:20:생명과학2,03:21:생명과학실험,03:22:심화물리,03:23:심화수학1,03:24:심화수학2,03:25:심화화학,03:26:화학실험,03:27:환경과학,04:28:공학기술,04:29:정보통신,04:30:정보과학(3학년),04:31:로봇제작,05:32:마케팅과 광고,05:33:시창청음,05:34:미술사,05:35:스포츠과학,05:36:영화감상과비평,05:37:육상운동,03:38:입체조형,03:39:제품디자인,03:40:진로와직업,03:41:체력운동";        // 3학년 카테고리

        arrCate[0] = "문과,이과,예체,카테고리 선택";                            // 1학년 카테고리
        arrCate[1] = "국제인문,사회과학,자연과학,it,예체,카테고리 선택";        // 2학년 카테고리
        arrCate[2] = "국제인문,사회과학,자연과학,it,예체,카테고리 선택";        // 3학년 카테고리

        // 1. 카테고리 스피너 생성

        final RequestQueue requestQueue1 = Volley.newRequestQueue(EnrolmentSubject.this);

        StringRequest strRequest = new StringRequest(Request.Method.GET, serverURL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("debug response",response);

                        String retStr = response.trim();
                        serviceAvailablity = Integer.parseInt(retStr);


                        if(serviceAvailablity == 1 && Active) {
                            //서비스 가능
                            layout_serviceAvailable.setVisibility(VISIBLE);
                            layout_serviceNotAvailable.setVisibility(View.GONE);

                        }else if (serviceAvailablity==0){//서비스 불가능
                            layout_serviceAvailable.setVisibility(View.GONE);
                            layout_serviceNotAvailable.setVisibility(View.VISIBLE);

                            txt1.setText("시험기간이 아닙니다");
                            txt2.setText("시험범위가 공개된 후 이용해주세요! 감사합니다");
                        }

                        Log.d("debug,serviceAvail",String.valueOf(serviceAvailablity));
                        requestQueue1.stop();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                txt1.setText("Something went wrong....");
                txt2.setText("Check your internet is connected");
                error.printStackTrace();
                requestQueue1.stop();
            }
        });
        requestQueue1.add(strRequest);


        grade1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                grade = 0;
                Toast.makeText(getApplicationContext(), "1학년 선택", Toast.LENGTH_SHORT).show();
                setCategory();
                grade1_btn.setBackgroundResource(R.drawable.grade1btn_rectangle);
                grade2_btn.setBackgroundResource(R.drawable.gradebtn_rectangle);
                grade3_btn.setBackgroundResource(R.drawable.gradebtn_rectangle);
                grade1_btn.setTextColor(Color.rgb(255, 255, 255));
                grade2_btn.setTextColor(Color.rgb(112, 112, 112));
                grade3_btn.setTextColor(Color.rgb(112, 112, 112));

                Category_spin.setVisibility(VISIBLE);
                Subject_spin.setVisibility(VISIBLE);


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
                grade1_btn.setTextColor(Color.rgb(112, 112, 112));
                grade3_btn.setTextColor(Color.rgb(112, 112, 112));

                grade2_btn.setBackgroundResource(R.drawable.grade2btn_rectangle);
                grade3_btn.setBackgroundResource(R.drawable.gradebtn_rectangle);
                grade1_btn.setBackgroundResource(R.drawable.gradebtn_rectangle);
                Category_spin.setVisibility(VISIBLE);
                Subject_spin.setVisibility(VISIBLE);
            }
        });

        grade3_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grade = 2;
                Toast.makeText(getApplicationContext(), "3학년", Toast.LENGTH_SHORT).show();
                setCategory();
                Linear.setBackgroundColor(Color.rgb(241, 242, 252));
                grade3_btn.setTextColor(Color.rgb(255, 255, 255));
                grade1_btn.setTextColor(Color.rgb(112, 112, 112));
                grade2_btn.setTextColor(Color.rgb(112, 112, 112));

                grade3_btn.setBackgroundResource(R.drawable.grade3btn_rectangle);
                grade1_btn.setBackgroundResource(R.drawable.gradebtn_rectangle);
                grade2_btn.setBackgroundResource(R.drawable.gradebtn_rectangle);
                Category_spin.setVisibility(VISIBLE);
                Subject_spin.setVisibility(VISIBLE);

            }
        });


        //=====================================================================================
        // 3. 사용자가 카테고리를 선택하면 해당 학년의 해당 카테고리의 과목 스피너를 생성한다.
        //    - 사용자가 카테고리를 선택했을때 카테고리 번호를 설정한다.
        //=====================================================================================

        //DONE
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

        //DONE
        Subject_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                Global.subjectIndexS = position;   //subjectIndex = spinnerSubject.SelectedIndex;

                if (position < Subject_spin.getCount()) {
                    //TODO
                    arrKey1 = arrKeyS.get(subjectIndexS);// arrKey[2] => 06

                    Log.d("디버그,subjectIndexS ", String.valueOf(subjectIndexS));

                    //myGradeNCode.add(grade, String.valueOf(arrKey1));//myGradeNCode는 사용자가 추가한 시험과목의 학년과 과목 코드 값 >> Shared preference로 저장need

                    MyGradeNcode A = new MyGradeNcode();
                    A.myCode = arrKey1;
                    A.myGrade = grade;
                    Log.d("디버그,myGrade", String.valueOf(A.myGrade));
                    Log.d("디버그,mycode", String.valueOf(A.myCode));

                    myGradeNCode.add(A);
                    //Log.d("myGradeNCode1",String.valueOf(myGradeNCode.get(1).myCode));
                    //Log.d("myGradeNCode2",String.valueOf(myGradeNCode.get(1).myGrade));

                    onListItemAdd(grade + 1, arrKey1);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }

        });
        //TODO
        Init_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(EnrolmentSubject.this, "이닛버튼", Toast.LENGTH_SHORT).show();

                AlertDialog.Builder builder = new AlertDialog.Builder(EnrolmentSubject.this);
                builder.setTitle("초기화");
                builder.setMessage("현재까지 선택된 모든 시험범위 목록이 삭제됩니다. 정말 초기화 하시겠습니까?");
                builder.setPositiveButton("초기화", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(EnrolmentSubject.this, "초기화 되었습니다", Toast.LENGTH_SHORT).show();
                        examRangeList.clear();
                        myGradeNCode .clear();

                        adapter.notifyDataSetChanged();

                        saveData();
                    }
                });
                builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(EnrolmentSubject.this, "취소하였습니다.", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
            }

        });

        //DONE
        Save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //getSharedPreferences 로 mySubject 저장
                saveData();
                getData();
                Category_spin.setVisibility(View.INVISIBLE);
                Subject_spin.setVisibility(View.INVISIBLE);
                GetData = true;

                Toast.makeText(getApplicationContext(), "저장되었습니다.", Toast.LENGTH_SHORT).show();

            }
        });
        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                android.app.AlertDialog.Builder dlg = new android.app.AlertDialog.Builder(view.getContext());
                dlg.setTitle("삭제확인");
                dlg.setMessage("이 시험범위를 삭제하겠습니까?");
                dlg.setIcon(R.drawable.icon);
                dlg.setNegativeButton("취소", null);
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        myGradeNCode.remove(position);
                        Toast.makeText(getApplicationContext(), "삭제되었습니다.", Toast.LENGTH_SHORT).show();
                        saveData();
                        getData();
                    }
                });
                dlg.show();
                adapter.notifyDataSetChanged();
                return false;
            }
        });
        getData_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sp = getSharedPreferences("shared", MODE_PRIVATE);
                String strContact = sp.getString("myGradeNCode","" );

                if (strContact.equals("")) {
                    Toast.makeText(getApplicationContext(), "저장된 시험범위가 없습니다.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "저장된 시험범위를 불러옵니다.", Toast.LENGTH_SHORT).show();
                    getData();
                    adapter.notifyDataSetChanged();
                }
            }
        });
}


    //DONE
    private void init() {
        grade = -1;
        arrDataS = new ArrayList<>();
        subjectIndexS = -1;
        arrKeyS = new ArrayList<>();
        Global.categoryNoS = -1;
        myGradeNCode = new ArrayList<>();
        examRangeList = new ArrayList<>();
    }

    //DONE
    private void setCategory() {
        String[] arrItem = arrCate[grade].split(",");// 특정 학년의 카테고리 목록

        // for (int i = 0; i < arrItem.length -1 ; i++) {

        // arrItem3.add(arrItem[i]);}
        Categpry = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrItem) {
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                if (position == getCount()) {
                    ((TextView) v.findViewById(android.R.id.text1)).setText("");
                    ((TextView) v.findViewById(android.R.id.text1)).setHint(getItem(getCount()));
                }
                return v;
            }

            public int getCount() {
                return super.getCount() - 1;
            }
        };
        Category_spin.setAdapter(Categpry);
    }

    //DONE
    private void setSubject() {
        arrDataS = new ArrayList<>();
        arrKeyS = new ArrayList<>();

        String[] arrItem1 = arrSubject[grade].split(",");// A001 특정학년의 과목 목록    - [0]01:01:국어,[1]01:02:영어....[11]03:11:체욱

        // 사용자가 선택한 학년의 과목배열 전체를 루프 실행
        for (int i = 0; i < arrItem1.length; i++) {
            String[] arrItem2 = arrItem1[i].split(":");    // [0]01, [1]01, [2]국어

            // 과목의 카테고리가 학생이 선택한 카테고리와 같으면 배열에 추가
            if (Integer.parseInt(arrItem2[0]) - 1 == categoryNoS)// A003  index는 스피너에 들어있는 원소의 번호
            {


                Log.d("디버그,key", arrItem2[1]);
                arrKeyS.add(Integer.parseInt(arrItem2[1]));    // 04, 05, 06
                Log.d("디버그,arrKeyS", String.valueOf(Integer.parseInt(arrItem2[1])));
                arrDataS.add(arrItem2[2]);    // 컴퓨터, 수학, 기계

            }
        }
        arrDataS.add("과목 선택");
        // arrDataS를 과목 스피너에 추가 : 컴퓨터, 수학, 기계

        Subject = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrDataS) {
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                if (position == getCount()) {
                    Log.d("디버그 포지션", String.valueOf(position));
                    ((TextView) v.findViewById(android.R.id.text1)).setText("");
                    ((TextView) v.findViewById(android.R.id.text1)).setHint(getItem(getCount()));
                }
                return v;
            }

            public int getCount() {
                return super.getCount() - 1;
            }
        };
        Subject_spin.setAdapter(Subject);
        Subject_spin.setSelection(Subject.getCount());

    }

    @Override
    public Context getApplicationContext() {
        return super.getApplicationContext();

    }

    //DONE
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

        Active = true;
    }

    //DONE
    private ArrayList<ExamData> ParseExamJson(String response) {

        tempExamArr = new ArrayList<>();

        JsonParser jsonParser = new JsonParser();
        JsonArray jsonArray = (JsonArray) jsonParser.parse(response);

        for (int i = 0; i < jsonArray.size(); i++) {

            JsonObject object = (JsonObject) jsonArray.get(i);

            int grade = object.get("target_grade").getAsInt();
            int code = object.get("exam_code").getAsInt();
            String name = object.get("exam_name").getAsString();
            String range = object.get("exam_range").getAsString();

            Log.d("디버그,서버 학년", String.valueOf(grade));
            Log.d("디버그,서버 코드", String.valueOf(code));
            Log.d("디버그,서버 이름", name);
            Log.d("디버그,서버 범위", range);

            tempExamArr.add(new ExamData(grade, code, name, range));

            Log.d("배열 길이", String.valueOf(tempExamArr.size()));

        }
        return tempExamArr;
    }

    //DONE
    private void onListItemAdd(int grade, int code) {

        String range, name;

        serverDataReceive();

        Log.d("List_grade", String.valueOf(grade));
        Log.d("List_code", String.valueOf(code));

        Log.d("배열 길이2", String.valueOf(tempExamArr.size()));

        for (int i = 0; i < Global.tempExamArr.size(); i++) {

            Log.d("tempExamArr", String.valueOf(tempExamArr.get(i).exam_code));
            Log.d("tempExamArr", String.valueOf(tempExamArr.get(i).target_grade));
            Log.d("tempExamArr", String.valueOf(tempExamArr.get(i).exam_range));
            Log.d("tempExamArr", String.valueOf(tempExamArr.get(i).exam_name));

            ExamData tempExam = tempExamArr.get(i);
            if ((code == tempExam.exam_code) && (grade == tempExam.target_grade)) {
                // 우리가 찾던 시험 데이터
                range = tempExam.exam_range;
                name = tempExam.exam_name;
                examRangeList.add(name + " : " + range); // examRangeLIst로 리스트뷰 구성

                break;
            }

        }
        //adapter.notifyDataSetChanged();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, examRangeList);
        listview.setAdapter(adapter);

        if (tempExamArr.size()==0) {
            return;
        }
    }

    //DONE
    private void saveData() {
        gson = new GsonBuilder().create();
        Type listType = new TypeToken<ArrayList<MyGradeNcode>>() {
        }.getType();
        String json = gson.toJson(myGradeNCode, listType);
        SharedPreferences sp = getSharedPreferences("shared", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("myGradeNCode", json); // JSON으로 변환한 객체를 저장한다.
        editor.commit(); //완료한다

        init();
        Global.grade = -1;
    }

    //DONE
    protected void getData() { // 앱 껏다가 켰을 때 자기가 저장한 시험범위 리스트를 잃어버리지 않도록 저장해주는 역할
        gson = new GsonBuilder().create();
        sp = getSharedPreferences("shared", MODE_PRIVATE);

        String strContact = sp.getString("myGradeNCode", null);

        Type listType = new TypeToken<ArrayList<MyGradeNcode>>() {
        }.getType();
        myGradeNCode = new ArrayList<MyGradeNcode>();
        myGradeNCode = gson.fromJson(strContact, listType);

        examRangeList = new ArrayList<>();

        for (MyGradeNcode data : myGradeNCode) {
            onListItemAdd(data.myGrade + 1, data.myCode);
            Log.d("getData2", String.valueOf(data.myCode));
        }
        if (myGradeNCode.equals("[]")){
            examRangeList = new ArrayList<>();
            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, examRangeList);
        }
    }


    //DONE
    public void onBackPressed() {

        if (serviceAvailablity == 1) {
            //if (serviceAvailablity == 1) {
            // Alert을 이용해 종료시키기
            AlertDialog.Builder dialog = new AlertDialog.Builder(EnrolmentSubject.this);
            dialog.setTitle("종료 경고")
                    .setMessage("작업하신 내용을 저장하지 않은 경우 작업내용이 무시됩니다. 종료하시겠습니까?")
                    .setPositiveButton("네", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setNeutralButton("취소", new DialogInterface.OnClickListener() {
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
            //}
            //if (serviceAvailablity == 0){
            finish();
            //}
        }else {
            finish();
        }
    }


}





