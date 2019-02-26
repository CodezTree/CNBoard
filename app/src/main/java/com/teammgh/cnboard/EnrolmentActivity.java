package com.teammgh.cnboard;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
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
import android.widget.Toast;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;
import static com.teammgh.cnboard.Global.grade;
import static com.teammgh.cnboard.Global.mySubData1;
import static com.teammgh.cnboard.Global.mySubject;
import static com.teammgh.cnboard.Global.mySubjectList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


public class EnrolmentActivity extends AppCompatActivity {

    Button save_btn,btn1_grade,btn2_grade,btn3_grade,del_btn;
    Spinner sc_spin,scgroup_spin,lan_spin,social_spin,it_spin,sport_spin,langroup_spin;
    LinearLayout entire_lin,sc_lin,scgroup_lin,lan_lin,social_lin,it_lin,sport_lin,langroup_lin,Linear;
    public ArrayAdapter<String> Ap_langroup,Ap_scgroup,Ap_sport,Ap_lan,Ap_sc,Ap_it,Ap_social; //과목 어뎁터
    ArrayAdapter<String> adapter;
    private ListView listView;
    private Gson gson;


    List<String> langroup,scgroup,sport,lan,sc,social,it,langroupN,scgroupN,sportN,lanN,scN,socialN,itN;
    String [] arrSubject = new String[3];
    String [][] arrSubjectMemo = new String[3][]; 	// 1학년 과목별 시험범위 -GetDataFromServer()로 값 서버에서 불러와야함

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

        arrSubject[0] = "국어,영어,일본어I,중국어I,통합사회,한국사,수학,통합과학,기술가정,음악연주,체육";
        arrSubject[1] = "철학,언어와 매체,문예 창작 입문,문학 개론,영어1,실용영어,심화 영어 회화1,영어권 문화,중국어2,사회 탐구 방법,사회 문제 탐구,사회문화,세계사,윤리와 사상,정치와 법,한국지리,경제,수학1,수학2,화학1,물리학1,생명과학1,정보과학,공학일반,미술,운동과 건강,음악 이론,체육과 진로탐구";
        arrSubject[2] = "교육학,논리학논술,심화 영어 독해1,영어회화,일본어회화1,국어작문,화법과 작문,국제경제,국제법,비교문화,사회문화,생활과윤리,세계지리,한국의사회와문화,고급물리,고급생명과학,고급수학,고급화학,고급물리,생명과학2,생명과학실험,심화물리,심화수학1,심화수학2,심화화학,화학실험,환경과학,공학기술,정보통신,정보과학(3학년),로봇제작,마케팅과 광고,시창청음,:미술사,스포츠과학,영화감상과비평,육상운동,입체조형,제품디자인,진로와직업,체력운동";

        GetDataFromServer();
        First();
        init();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mySubject);

        entire_lin.setVisibility(INVISIBLE);
        sc_lin.setVisibility(INVISIBLE);
        sport_lin.setVisibility(INVISIBLE);
        lan_lin.setVisibility(INVISIBLE);
        social_lin.setVisibility(INVISIBLE);
        it_lin.setVisibility(INVISIBLE);
        scgroup_lin.setVisibility(INVISIBLE);
        langroup_lin.setVisibility(INVISIBLE);


        listView = (ListView)findViewById(R.id.listview);
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

                ++Global.a;
                grade = 0;
                setSubject();
                Warning();
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

                ++Global.a;
                grade = 1;
                setSubject();
                Warning();

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

                ++Global.a;
                grade = 2;
                setSubject();
                Warning();

            }
        });





        lan_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

                String retValLan = lan.get(position);
                String SubjectN = lanN.get(position);
                mySubject.add(SubjectN+" : " +retValLan);
                mySubjectList.get(grade).add(retValLan);
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
                mySubjectList.get(grade).add(retValsocial);
                adapter.notifyDataSetChanged();
                listView.setVisibility(VISIBLE);
                del_btn.setVisibility(VISIBLE);
                save_btn.setVisibility(VISIBLE);


                final int subjectnum = position;

                del_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mySubject.remove(subjectnum);
                        mySubjectList.remove(subjectnum);
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
                mySubjectList.get(grade).add(retValsc);
                adapter.notifyDataSetChanged();
                listView.setVisibility(VISIBLE);
                del_btn.setVisibility(VISIBLE);
                save_btn.setVisibility(VISIBLE);

                final int subjectnum = position;

                del_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mySubject.remove(subjectnum);
                        mySubjectList.remove(subjectnum);
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
                mySubjectList.get(grade).add(retValit);
                adapter.notifyDataSetChanged();
                listView.setVisibility(VISIBLE);
                del_btn.setVisibility(VISIBLE);
                save_btn.setVisibility(VISIBLE);

                final int subjectnum = position;

                del_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mySubject.remove(subjectnum);
                        mySubjectList.remove(subjectnum);
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
                mySubjectList.get(grade).add(retValsport);          //인풋서브젝트 코딩할때 참고 지워도댐
                adapter.notifyDataSetChanged();
                listView.setVisibility(VISIBLE);
                del_btn.setVisibility(VISIBLE);
                save_btn.setVisibility(VISIBLE);

                final int subjectnum = position;

                del_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mySubject.remove(subjectnum);
                        mySubjectList.remove(subjectnum);
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
                mySubjectList.get(grade).add(retValLangroup);
                adapter.notifyDataSetChanged();
                listView.setVisibility(VISIBLE);
                del_btn.setVisibility(VISIBLE);
                save_btn.setVisibility(VISIBLE);

                final int subjectnum = position;

                del_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mySubject.remove(subjectnum);
                        mySubjectList.remove(subjectnum);
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
                mySubjectList.get(grade).add(retValscgroup);
                adapter.notifyDataSetChanged();
                listView.setVisibility(VISIBLE);
                del_btn.setVisibility(VISIBLE);
                save_btn.setVisibility(VISIBLE);

                final int subjectnum = position;

                del_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mySubject.remove(subjectnum);
                        mySubjectList.remove(subjectnum);
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

                Toast.makeText(getApplicationContext(),"시험범위가 저장되었습니다.",Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void setSubject(){

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

                Ap_scgroup = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, langroupN); //스피너 과목이름으로 채움  *Ap_scgroup = 과목 배열
                Ap_langroup = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, scgroupN);
                Ap_sport = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, sportN);
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

                Ap_lan = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lanN);
                Ap_social = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, socialN);
                Ap_sport = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, sportN);
                Ap_it = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itN);
                Ap_sc = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, scN);

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
                Ap_lan = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, lanN);
                Ap_social = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, socialN);
                Ap_sport = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, sportN);
                Ap_it = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, itN);
                Ap_sc = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, scN);

                lan_spin.setAdapter(Ap_lan);
                social_spin.setAdapter(Ap_social);
                it_spin.setAdapter(Ap_it);
                sc_spin.setAdapter(Ap_sc);
                sport_spin.setAdapter(Ap_sport);
                break;

        }


    }

    private void GetDataFromServer(){

        //arrSubjectMemo 를 서버에서 가져온다.
        }

    private void saveData() {

        SharedPreferences mySubData = getSharedPreferences("mySubData", MODE_PRIVATE);
        SharedPreferences.Editor editor = mySubData.edit();
        Gson gson = new Gson();
        // JSON 으로 변환
        String strmySub = gson.toJson(mySubject, EnrolmentActivity.class);
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
        mySubject = gson.fromJson(strmySub,type);  //mySubject는 adapter의 내용
        adapter.notifyDataSetChanged();
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
                AlertDialog.Builder dialog = new AlertDialog.Builder(EnrolmentActivity.this);
                dialog.setTitle("초기화 경고")
                        .setMessage("시험범위가 저장되지 않았습니다. 시험범위를 저장하지 않은 채 학년 버튼을 다시 누르면 선택한 것이 모두 초기화 됩니다. 그래도 계속 진행하시겠습니까?")
                        .setPositiveButton("계속 진행합니다", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(EnrolmentActivity.this, "현재까지의 선택이 모두 초기화 되었습니다. 처음부터 다시 진행해주십시요", Toast.LENGTH_SHORT).show();
                                //OnClick 리스너 실행, 초기화
                                init();
                            }
                        })
                        .setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(EnrolmentActivity.this, "계속 진행하지 않습니다.", Toast.LENGTH_SHORT).show();
                                //OnClick 리스너 실행하지 않음, 초기화 하지 않음
                            }
                        }).create().show();
            }
        }
    }
    public void onBackPressed() {

        // Alert을 이용해 종료시키기
        if(!mySubData1.contains("mySubData")){
        AlertDialog.Builder dialog = new AlertDialog.Builder(EnrolmentActivity.this);
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
                        Toast.makeText(EnrolmentActivity.this, "취소했습니다. 저장 버튼을 눌러 선택된 시험범위를 저장해 주십시오.", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(EnrolmentActivity.this, "종료하지 않습니다. 저장 버튼을 눌러 선택된 시험범위를 저장해 주십시오", Toast.LENGTH_SHORT).show();
                    }
                }).create().show();
        }
    }
    private void init(){
        langroup  = new ArrayList<>();
        scgroup = new ArrayList<>();
        sport = new ArrayList<>();
        lan = new ArrayList<>();
        sc = new ArrayList<>();
        social = new ArrayList<>();
        it = new ArrayList<>();
        langroupN = new ArrayList<>();
        scgroupN = new ArrayList<>();
        sportN = new ArrayList<>();
        lanN = new ArrayList<>();
        scN = new ArrayList<>();
        socialN = new ArrayList<>();
        itN = new ArrayList<>();
        Global.subjectIndex = -1;
        Global.categoryNo = -1;
        Global.a = 0;
    }
}
