package com.teammgh.cnboard;

import androidx.appcompat.app.AppCompatActivity;

//내일 할 일 :arrSubject 과목 배열 순서 인풋꺼 과목 배열 순서랑 같은지 보기,switch문에서 arrItem1[i] 가능한지 보기, 여튼 이차원 배열로 받는걸로 바꼇으니까 그거 잘 적용되는지 보기
//arrSubject 다 채우기 워드 보면서
//이거 스피너 어뎁터 적용하는거 다 메소드로 빼서 스피너 원소 클릭하자마자 다음 스피너 채우도록 하기 -- 인풋도 마찬가지

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.List;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;
import static com.teammgh.cnboard.Global.grade;
import static com.teammgh.cnboard.Global.mySubject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class EnrolmentActivity extends AppCompatActivity {

    Button save_btn,btn1_grade,btn2_grade,btn3_grade,del_btn;
    Spinner sc_spin,scgroup_spin,lan_spin,social_spin,it_spin,sport_spin,langroup_spin;
    LinearLayout entire_lin,sc_lin,scgroup_lin,lan_lin,social_lin,it_lin,sport_lin,langroup_lin,Linear;
    public ArrayAdapter<String> Ap_langroup,Ap_scgroup,Ap_sport,Ap_lan,Ap_sc,Ap_it,Ap_social; //과목 어뎁터
    ArrayAdapter<String> adapter = null;
    private ListView listView;
    private Gson gson;


    List<String> langroup  = new ArrayList<>();
    List<String> scgroup = new ArrayList<>();
    List<String> sport = new ArrayList<>();
    List<String> lan  = new ArrayList<>();
    List<String> sc = new ArrayList<>();
    List<String> social = new ArrayList<>();
    List<String> it  = new ArrayList<>();
    List<String> langroupN  = new ArrayList<>();
    List<String> scgroupN = new ArrayList<>();
    List<String> sportN = new ArrayList<>();
    List<String> lanN  = new ArrayList<>();
    List<String> scN = new ArrayList<>();
    List<String> socialN = new ArrayList<>();
    List<String> itN  = new ArrayList<>();

    String [] arrSubject = new String[3];

    String [][] arrSubjectMemo = new String[3][];	// 1학년 과목별 시험범위 -GetDataFromServer()로 값 서버에서 불러와야함

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enrolment_student);
        sc_spin = findViewById(R.id.sc_spin);
        scgroup_spin = findViewById(R.id.scgroup_spin);
        lan_spin = findViewById(R.id.lan_spin);
        social_spin = findViewById(R.id.social_spin);
        it_spin = findViewById(R.id.it_spin);
        sport_spin = findViewById(R.id.sport_spin);
        langroup_spin = findViewById(R.id.langroup_spin);
        sc_spin.setPrompt("과목을 선택하세요.");
        scgroup_spin.setPrompt("과목을 선택하세요.");
        lan_spin.setPrompt("과목을 선택하세요.");
        it_spin.setPrompt("과목을 선택하세요.");
        sport_spin.setPrompt("과목을 선택하세요.");
        langroup_spin.setPrompt("과목을 선택하세요.");
        entire_lin = findViewById(R.id.entire_linear);
        sc_lin = findViewById(R.id.sc_linear);
        scgroup_lin = findViewById(R.id.scgroup_linear);
        lan_lin = findViewById(R.id.lan_linear);
        social_lin = findViewById(R.id.social_linear);
        it_lin = findViewById(R.id.it_linear);
        sport_lin = findViewById(R.id.sport_linear);
        langroup_lin = findViewById(R.id.langroup_linear);
        Linear = findViewById(R.id.Linear);
        save_btn = findViewById(R.id.save_btn);
        btn1_grade = findViewById(R.id.grade1_btn);
        btn2_grade = findViewById(R.id.grade2_btn);
        btn3_grade = findViewById(R.id.grade3_btn);
        del_btn = findViewById(R.id.del_btn);

        arrSubject[0] = "국어,영어,일본어I,중국어I,통합사회,한국사,기술가정,통합과학,음악연주,체육";

                GetDataFromServer();

        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_single_choice, mySubject); //리스트뷰 어뎁터

        entire_lin.setVisibility(INVISIBLE);
        sc_lin.setVisibility(INVISIBLE);
        sport_lin.setVisibility(INVISIBLE);
        lan_lin.setVisibility(INVISIBLE);
        social_lin.setVisibility(INVISIBLE);
        it_lin.setVisibility(INVISIBLE);
        scgroup_lin.setVisibility(INVISIBLE);
        langroup_lin.setVisibility(INVISIBLE);


        listView = (ListView) findViewById(R.id.listview);
        listView.setAdapter(adapter);

        listView.setVisibility(INVISIBLE);
        del_btn.setVisibility(INVISIBLE);
        save_btn.setVisibility(INVISIBLE);





        btn1_grade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                entire_lin.setVisibility(VISIBLE);
                scgroup_lin.setVisibility(VISIBLE);
                langroup_lin.setVisibility(VISIBLE);
                sport_lin.setVisibility(VISIBLE);
                lan_lin.setVisibility(INVISIBLE);
                social_lin.setVisibility(INVISIBLE);
                it_lin.setVisibility(INVISIBLE);
                sc_lin.setVisibility(INVISIBLE);

                btn1_grade.setBackgroundResource(R.drawable.grade1btn_rectangle);
                btn2_grade.setBackgroundResource(R.drawable.gradebtn_rectangle);
                btn3_grade.setBackgroundResource(R.drawable.gradebtn_rectangle);
                btn1_grade.setTextColor(Color.rgb(255,255,255));

                Linear.setBackgroundColor(Color.rgb(255, 248, 248));
                grade = 0;


            }
        });
        btn2_grade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                entire_lin.setVisibility(VISIBLE);
                scgroup_lin.setVisibility(INVISIBLE);
                langroup_lin.setVisibility(INVISIBLE);
                sc_lin.setVisibility(VISIBLE);
                sport_lin.setVisibility(VISIBLE);
                lan_lin.setVisibility(VISIBLE);
                social_lin.setVisibility(VISIBLE);
                it_lin.setVisibility(VISIBLE);
                Linear.setBackgroundColor(Color.rgb(241, 255, 241));
                btn2_grade.setTextColor(Color.rgb(255,255,255));

                btn2_grade.setBackgroundResource(R.drawable.grade2btn_rectangle);
                btn3_grade.setBackgroundResource(R.drawable.gradebtn_rectangle);
                btn1_grade.setBackgroundResource(R.drawable.gradebtn_rectangle);

                grade = 1;

            }
        });

        btn3_grade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                entire_lin.setVisibility(INVISIBLE);
                scgroup_lin.setVisibility(INVISIBLE);
                langroup_lin.setVisibility(INVISIBLE);
                sc_lin.setVisibility(INVISIBLE);
                sport_lin.setVisibility(INVISIBLE);
                lan_lin.setVisibility(INVISIBLE);
                social_lin.setVisibility(INVISIBLE);
                it_lin.setVisibility(INVISIBLE);
                Linear.setBackgroundColor(Color.rgb(241, 242, 252));
                btn3_grade.setTextColor(Color.rgb(255,255,255));

                btn3_grade.setBackgroundResource(R.drawable.grade3btn_rectangle);
                btn1_grade.setBackgroundResource(R.drawable.gradebtn_rectangle);
                btn2_grade.setBackgroundResource(R.drawable.gradebtn_rectangle);

                grade = 2;

            }
        });


        String[] arrItem1 = arrSubject[grade].split(",");
        switch (grade) {
            case 0:

                langroup_lin.setVisibility(VISIBLE);
                scgroup_lin.setVisibility(VISIBLE);
                sport_lin.setVisibility(VISIBLE);

                for (int i = 0; i <= 5; i++) {
                    langroup.add(arrSubjectMemo[0][i]);             //langroup = 시험범위 배열 -- 서버에서 1학년 시험범위를 불러옴          *lan group1 = "국어 시험범위는 100-139페이지","사회 시험범위는 100-399페이지".....
                    langroupN.add(arrItem1[i]);                   //langroupN = 과목이름 배열  *langroupR1= "국어","영어","일본어1","중국어1","통합사회","한국사"
                }

                for (int i = 6; i <= 8; i++) {
                    scgroup.add(arrSubjectMemo[0][i]);
                    scgroupN.add(arrItem1[i]);
                }

                for (int i = 9; i <= 10; i++) {
                    sport.add(arrSubjectMemo[0][i]);
                    sportN.add(arrItem1[i]);
                }

                Ap_scgroup = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, langroupN); //스피너 과목이름으로 채움  *Ap_scgroup = 과목 배열
                Ap_langroup = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, scgroupN);
                Ap_sport = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sportN);
                scgroup_spin.setAdapter(Ap_scgroup);
                langroup_spin.setAdapter(Ap_langroup);
                sport_spin.setAdapter(Ap_sport);

                break;

            case 1:

                lan_lin.setVisibility(VISIBLE);
                sc_lin.setVisibility(VISIBLE);
                sport_lin.setVisibility(VISIBLE);
                it_lin.setVisibility(VISIBLE);
                social_lin.setVisibility(VISIBLE);

                for (int i = 0; i <= 8; i++) {
                    lan.add(arrSubjectMemo[1][i]);
                    lanN.add(arrItem1[i]);
                }

                for (int i = 9; i <= 16; i++) {
                    social.add(arrSubjectMemo[1][i]);
                    socialN.add(arrItem1[i]);
                }

                for (int i = 17; i <= 21; i++) {
                    sc.add(arrSubjectMemo[1][i]);
                    scN.add(arrItem1[i]);

                }
                for (int i = 22; i <= 23; i++) {
                    it.add(arrSubjectMemo[1][i]);
                    itN.add(arrItem1[i]);

                }
                for (int i = 24; i <= 27; i++) {
                    sport.add(arrSubjectMemo[1][i]);
                    sportN.add(arrItem1[i]);
                }

                Ap_lan = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lanN);
                Ap_social = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, socialN);
                Ap_sport = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sportN);
                Ap_it = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itN);
                Ap_sc = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, scN);

                lan_spin.setAdapter(Ap_lan);
                social_spin.setAdapter(Ap_social);
                it_spin.setAdapter(Ap_it);
                sc_spin.setAdapter(Ap_sc);
                sport_spin.setAdapter(Ap_sport);

                break;

            case 2:

                lan_lin.setVisibility(VISIBLE);
                sc_lin.setVisibility(VISIBLE);
                sport_lin.setVisibility(VISIBLE);
                it_lin.setVisibility(VISIBLE);
                social_lin.setVisibility(VISIBLE);

                for (int i = 0; i <= 6; i++) {
                    lan.add(arrSubjectMemo[2][i]);
                    lanN.add(arrItem1[i]);
                }

                for (int i = 7; i <= 13; i++) {
                    social.add(arrSubjectMemo[2][i]);
                    socialN.add(arrItem1[i]);
                }

                for (int i = 14; i <= 26; i++) {
                    sc.add(arrSubjectMemo[2][i]);
                    scN.add(arrItem1[i]);
                }
                for (int i = 27; i <= 30; i++) {
                    it.add(arrSubjectMemo[2][i]);
                    itN.add(arrItem1[i]);
                }
                for (int i = 31; i <= 40; i++) {
                    sport.add(arrSubjectMemo[2][i]);
                    sportN.add(arrItem1[i]);
                }
                Ap_lan = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lanN);
                Ap_social = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, socialN);
                Ap_sport = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sportN);
                Ap_it = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itN);
                Ap_sc = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, scN);

                lan_spin.setAdapter(Ap_lan);
                social_spin.setAdapter(Ap_social);
                it_spin.setAdapter(Ap_it);
                sc_spin.setAdapter(Ap_sc);
                sport_spin.setAdapter(Ap_sport);
                break;

        }


        lan_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

                String retValLan = lan.get(position);
                String SubjectN = lanN.get(position);
                mySubject.add(SubjectN+" : " +retValLan);
                adapter.notifyDataSetChanged();
                listView.setVisibility(VISIBLE);
                save_btn.setVisibility(VISIBLE);

                final int subjectnum = position;

                del_btn.setVisibility(VISIBLE);

                del_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mySubject.remove(subjectnum);
                        adapter.notifyDataSetChanged();
                    }
                });



            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        social_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String retValsocial = social.get(position);
                String SubjectN = socialN.get(position);
                mySubject.add(SubjectN+" : " +retValsocial);
                adapter.notifyDataSetChanged();
                listView.setVisibility(VISIBLE);
                del_btn.setVisibility(VISIBLE);
                save_btn.setVisibility(VISIBLE);


                final int subjectnum = position;

                del_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mySubject.remove(subjectnum);
                        adapter.notifyDataSetChanged();
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        sc_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String retValsc = sc.get(position);
                String SubjectN = scN.get(position);
                mySubject.add(SubjectN+" : " +retValsc);
                adapter.notifyDataSetChanged();
                listView.setVisibility(VISIBLE);
                del_btn.setVisibility(VISIBLE);
                save_btn.setVisibility(VISIBLE);

                final int subjectnum = position;

                del_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mySubject.remove(subjectnum);
                        adapter.notifyDataSetChanged();
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        it_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String retValit = it.get(position);
                String SubjectN = itN.get(position);
                mySubject.add(SubjectN+" : " +retValit);
                adapter.notifyDataSetChanged();
                listView.setVisibility(VISIBLE);
                del_btn.setVisibility(VISIBLE);
                save_btn.setVisibility(VISIBLE);

                final int subjectnum = position;

                del_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mySubject.remove(subjectnum);
                        adapter.notifyDataSetChanged();
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        sport_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String retValsport = sport.get(position);
                String SubjectN = sportN.get(position);
                mySubject.add(SubjectN+" : " +retValsport);
                adapter.notifyDataSetChanged();
                listView.setVisibility(VISIBLE);
                del_btn.setVisibility(VISIBLE);
                save_btn.setVisibility(VISIBLE);

                final int subjectnum = position;

                del_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mySubject.remove(subjectnum);
                        adapter.notifyDataSetChanged();
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        langroup_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String retValLangroup = langroup.get(position);
                String SubjectN = langroupN.get(position);
                mySubject.add(SubjectN+" : " +retValLangroup);
                adapter.notifyDataSetChanged();
                listView.setVisibility(VISIBLE);
                del_btn.setVisibility(VISIBLE);
                save_btn.setVisibility(VISIBLE);

                final int subjectnum = position;

                del_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mySubject.remove(subjectnum);
                        adapter.notifyDataSetChanged();
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        scgroup_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String retValscgroup = scgroup.get(position);
                String SubjectN = scgroupN.get(position);
                mySubject.add(SubjectN+" : " +retValscgroup);
                adapter.notifyDataSetChanged();
                listView.setVisibility(VISIBLE);
                del_btn.setVisibility(VISIBLE);
                save_btn.setVisibility(VISIBLE);

                final int subjectnum = position;

                del_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mySubject.remove(subjectnum);
                        adapter.notifyDataSetChanged();
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        save_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                saveData();
                getData();
                adapter.notifyDataSetChanged();
            }


        });
    }

    private void GetDataFromServer(){

        //arrSubjectMemo 를 서버에서 가져온다.
        }

    private void saveData() {

        gson = new GsonBuilder().create();
        // JSON 으로 변환
        String strmySub = gson.toJson(mySubject, EnrolmentActivity.class);

        SharedPreferences mySubData = getSharedPreferences("mySubData", MODE_PRIVATE);
        SharedPreferences.Editor editor = mySubData.edit();
        editor.putString("mySubject", strmySub); // JSON으로 변환한 객체를 저장한다.
        editor.commit(); //완료한다.
    }
    protected void getData(){
        SharedPreferences mySubData1 = getSharedPreferences("mySubData", MODE_PRIVATE);
        String strmySub = mySubData1.getString("mySubject", "");

        // 변환
        EnrolmentActivity strmySub1 = gson.fromJson(strmySub, EnrolmentActivity.class); //여기 잘 모르겠다... 그러면 listview adapter에 들어가는 배열을 저장버튼 누른뒤에는 strmySub로 바꿔야하는건가..
                                                                                        //그럼 또 strmySub을 ArrayList로 바꿔야하는것인가ㅜㅜ
        //mySubject = strmySub1;
        adapter.notifyDataSetChanged();


    }


}
