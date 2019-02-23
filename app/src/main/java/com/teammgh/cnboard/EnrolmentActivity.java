package com.example.cnboardsubjest;

import android.support.v7.app.AppCompatActivity;
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
import static com.example.cnboardsubjest.Global.grade;

public class MainActivity extends AppCompatActivity {

    Button input_btn,btn1_grade,btn2_grade,btn3_grade;
    Spinner sc_spin,scgroup_spin,lan_spin,social_spin,it_spin,sport_spin,langroup_spin;
    LinearLayout entire_lin,sc_lin,scgroup_lin,lan_lin,social_lin,it_lin,sport_lin,langroup_lin;
    public ArrayAdapter<String> Ap_langroup,Ap_scgroup,Ap_sport,Ap_lan,Ap_sc,Ap_it,Ap_social; //과목 어뎁터
    ArrayAdapter<String> adapter = null;
    private ListView listView;

    List<String> mySubject = new ArrayList<>();
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


    String [] arrSubjectMemo1 = new String[11];	// 1학년 과목별 시험범위 -GetDataFromServer()로 값 서버에서 불러와야함
    String [] arrSubjectMemo3 = new String[41];	// 3학년 과목별 시험범위 -GetDataFromServer()로 값 서버에서 불러와야함
    String [] arrSubjectMemo2 = new String[28];	// 2학년 과목별 시험범위 -GetDataFromServer()로 값 서버에서 불러와야함

    String [] arrSubject = new String[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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


        GetDataFromServer();

        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_single_choice,mySubject) ; //리스트뷰 어뎁터

        entire_lin.setVisibility(INVISIBLE);
        sc_lin.setVisibility(INVISIBLE);
        sport_lin.setVisibility(INVISIBLE);
        lan_lin.setVisibility(INVISIBLE);
        social_lin.setVisibility(INVISIBLE);
        it_lin.setVisibility(INVISIBLE);
        scgroup_lin.setVisibility(INVISIBLE);
        langroup_lin.setVisibility(INVISIBLE);


        listView = (ListView) findViewById(R.id.listview) ;
        listView.setAdapter(adapter) ;

        listView.setVisibility(INVISIBLE);


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


                Global.grade = 0;


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

                Global.grade = 1;

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

                Global.grade = 2;

            }
        });


        String[] arrItem1 = arrSubject[grade].split(",");
        switch(grade) {
            case 0:

                langroup_lin.setVisibility(VISIBLE);
                scgroup_lin.setVisibility(VISIBLE);
                sport_lin.setVisibility(VISIBLE);

                for (int i = 0; i <=5 ;i++){
                langroup.add(arrSubjectMemo1[i]);             //langroup = 시험범위 배열 -- 서버에서 1학년 시험범위를 불러옴          *lan group1 = "국어 시험범위는 100-139페이지","사회 시험범위는 100-399페이지".....
                langroupN.add(arrItem1[i]);                   //langroupN = 과목이름 배열  *langroupR1= "국어","영어","일본어1","중국어1","통합사회","한국사"
            }

            for (int i = 6; i <=8 ;i++){
               scgroup.add(arrSubjectMemo1[i]);
               scgroupN.add(arrItem1[i]);
            }

            for (int i = 9; i <=10 ;i++){
                sport.add(arrSubjectMemo1[i]);
                sportN.add(arrItem1[i]);
            }

            Ap_scgroup = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,langroupN); //스피너 과목이름으로 채움  *Ap_scgroup = 과목 배열
            Ap_langroup = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, scgroupN);
            Ap_sport = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sportN);
            scgroup_spin.setAdapter(Ap_scgroup);
            langroup_spin.setAdapter(Ap_langroup);
            sport_spin.setAdapter(Ap_sport);
            //notifyDataSetChaned 필요?

            break;

            case 1:

                lan_lin.setVisibility(VISIBLE);
                sc_lin.setVisibility(VISIBLE);
                sport_lin.setVisibility(VISIBLE);
                it_lin.setVisibility(VISIBLE);
                social_lin.setVisibility(VISIBLE);

                for (int i = 0; i <=8; i++){
                lan.add(arrSubjectMemo2[i]);
                lanN.add(arrItem1[i]);
            }

            for (int i = 9; i <=16; i++){
                social.add(arrSubjectMemo2[i]);
                socialN.add(arrItem1[i]);
            }

            for (int i = 17; i <=21;i++){
                sc.add(arrSubjectMemo2[i]);
                scN.add(arrItem1[i]);

            }
            for (int i = 22; i <=23;i++){
                it.add(arrSubjectMemo2[i]);
                itN.add(arrItem1[i]);

            }
            for (int i = 24; i <=27;i++){
                sport.add(arrSubjectMemo2[i]);
                sportN.add(arrItem1[i]);
            }

            Ap_lan = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lanN);
            Ap_social = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,socialN);
            Ap_sport = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, sportN);
            Ap_it = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itN);
            Ap_sc = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,scN);

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

                for (int i = 0; i <=6;i++){
                lan.add(arrSubjectMemo3[i]);
                lanN.add(arrItem1[i]);
            }

            for (int i = 7; i <=13;i++){
                social.add(arrSubjectMemo3[i]);
                socialN.add(arrItem1[i]);
            }

            for (int i = 14; i <=26;i++){
                sc.add(arrSubjectMemo3[i]);
                scN.add(arrItem1[i]);
            }
            for (int i = 27; i <=30;i++){
                it.add(arrSubjectMemo3[i]);
                itN.add(arrItem1[i]);
            }
            for (int i = 31; i <=40;i++){
                sport.add(arrSubjectMemo3[i]);
                sportN.add(arrItem1[i]);
            }
            Ap_lan = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,lanN);
            Ap_social = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, socialN);
            Ap_sport = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,sportN);
            Ap_it = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, itN);
            Ap_sc = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, scN);

            lan_spin.setAdapter(Ap_lan);
            social_spin.setAdapter(Ap_social);
            it_spin.setAdapter(Ap_it);
            sc_spin.setAdapter(Ap_sc);
            sport_spin.setAdapter(Ap_sport);
            break;

        }
         final ArrayList<String> mySubject = new ArrayList<String>();

        lan_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {

                String retValLan = lan.get(position);
                mySubject.add(retValLan);
                adapter.notifyDataSetChanged();
                listView.setVisibility(VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        social_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String retValsocial = social.get(position);
                mySubject.add(retValsocial);
                adapter.notifyDataSetChanged();
                listView.setVisibility(VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        sc_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String retValsc = sc.get(position);
                mySubject.add(retValsc);
                adapter.notifyDataSetChanged();
                listView.setVisibility(VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        it_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String retValit = it.get(position);
                mySubject.add(retValit);
                adapter.notifyDataSetChanged();
                listView.setVisibility(VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        sport_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String retValsport = sport.get(position);
                mySubject.add(retValsport);
                adapter.notifyDataSetChanged();
                listView.setVisibility(VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        langroup_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String retValLangroup = langroup.get(position);
                mySubject.add(retValLangroup);
                adapter.notifyDataSetChanged();
                listView.setVisibility(VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        scgroup_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                String retValscgroup = scgroup.get(position);
                mySubject.add(retValscgroup);
                adapter.notifyDataSetChanged();
                listView.setVisibility(VISIBLE);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


    }

    private void GetDataFromServer() {

        /*
     arrSubjectMemo1,arrSubjectMemo2,arrSubjectMemo3을 서버에서 불러옴
         */
    }
}
