package com.teammgh.cnboard;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

import static com.teammgh.cnboard.Global.categoryNo;
import static com.teammgh.cnboard.Global.grade;
import static com.teammgh.cnboard.Global.subjectIndex;


public class InputSubject extends AppCompatActivity {

    Button igrade1_btn, igrade2_btn, igrade3_btn, SubAdd_btn, SubDel_btn,  AddEditDelSave_btn, SubrangeSave_btn;
    EditText AddEditDel_edtxt, Subrange_edtxt;
    Spinner Category_spin, Subject_spin;
    public ArrayAdapter<String> Categpry, Subject;

    String [] arrCate = new String[3];

    String [] arrSubject = new String[3];
    String [] arrSubjectMemo1 = new String[11];	// 1학년 과목별 시험범위
    String [] arrSubjectMemo2 = new String[28];	// 2학년 과목별 시험범위
    String [] arrSubjectMemo3 = new String[41];	// 3학년 과목별 시험범위

    int subjectMemoIndex=0;
    String subjectIndex1;


    ArrayList<Integer> arrKey = new ArrayList<Integer>();       // 특정학년의 과목의 키번호 배얼
    ArrayList<String> arrData = new ArrayList<String>();        // 특정학년의 과목이름 배열

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.enrolment_input);


        igrade1_btn = findViewById(R.id.igrade1_btn);
        igrade2_btn= findViewById(R.id.igrade2_btn) ;
        igrade3_btn= findViewById(R.id.igrade3_btn);
        SubAdd_btn= findViewById(R.id.SubAdd_btn);
        SubDel_btn = findViewById(R.id.SubDel_btn);
        AddEditDelSave_btn = findViewById(R.id.AddEditDelSave_btn);
        SubrangeSave_btn = findViewById(R.id.SubRange_btn);

        AddEditDel_edtxt = findViewById(R.id.AddEditDel_edtxt);
        Subrange_edtxt= findViewById(R.id.SubRange_edtxt);

        Category_spin = findViewById(R.id.Category_spin);
        Subject_spin = findViewById(R.id.Subject_spin);

        Subrange_edtxt.setVisibility(View.INVISIBLE);
        AddEditDelSave_btn.setVisibility(View.INVISIBLE);

        arrSubject [0] = "01:01:국어,01:02:영어,01:03:일본어1,01:04:중국어1,01:05:통합사회,01:06:한국사,02:07:수학,02:08:통합과학,02:09:기술가정,03:10:음악연주,03:11:체육";	// 1학년 과목
        arrSubject [1] = "01:01:철학,01:02:언어와 매체,01:03:문예 창작 입문,01:04:문학 개론,01:05:영어1,01:06:실용영어,01:07:심화 영어 회화1,01:08:영어권 문화,01:09:중국어2,02:10:사회 탐구 방법,02:11:사회 문제 탐구,02:12:사회문화,02:13:세계사,02:14:윤리와 사상,02:15:정치와 법,02:16:한국지리,02:17:경제,03:18:수학1,03:19:수학2,03:20:화학1,03:21:물리학1,03:22:생명과학1,04:23:정보과학,04:24:공학일반,05:25:미술,05:26:운동과 건강,05:27:음악 이론,05:28:체육과 진로탐구";		// 2학년 카테고리
        arrSubject [2] = "01:01:교육학,01:02:논리학논술,01:03:심화 영어 독해1,01:04:영어회화,01:05:일본어회화1,01:06:국어작문,01:07:화법과 작문,02:08:국제경제,02:09:국제법,02:10:비교문화,02:11:사회문화,02:12:생활과윤리,02:13:세계지리,02:14:한국의사회와문화,03:15:고급물리,03:16:고급생명과학,03:17:고급수학,03:18:고급화학,03:19:고급물리,03:20:생명과학2,03:21:생명과학실험,03:22:심화물리,03:23:심화수학1,03:24:심화수학2,03:25:심화화학,03:26:화학실험,03:27:환경과학,04:28:공학기술,04:29:정보통신,04:30:정보과학(3학년),04:31:로봇제작,05:32:마케팅과 광고,05:33:시창청음,05:34:미술사,05:35:스포츠과학,05:36:영화감상과비평,05:37:육상운동,03:38:입체조형,03:39:제품디자인,03:40:진로와직업,03:41:체력운동";		// 3학년 카테고리

        arrCate[0] = "문과,이과,예체";		// 1학년 카테고리
        arrCate[1] = "국제인문,사회과학,자연과학,it,예체";		// 2학년 카테고리
        arrCate[2] = "국제인문,사회과학,자연과학,it,예체";		// 3학년 카테고리


        //=====================================================================================
        // 1. 학년버튼을 만든다.
        //=====================================================================================


        //=====================================================================================
        // 2. 사용자가 학년 버튼을 클릭하면 해당 학년의 카테고리(주제) 스피너를 생성한다.
        //    - 사용자가 학년버튼을 클릭하면 학년정보(grade) 설정한다.
        //=====================================================================================
        // 1. 카테고리 스피너 생성

        igrade1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grade = 0;
            }

        });

        igrade2_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grade = 1;
            }

        });

        igrade3_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grade = 2;
            }

        });

        String[]arrItem = arrCate[grade].split(",");// 특정 학년의 카테고리 목록


        // arrItem 을 스피너에 추가
        Categpry = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrItem);
        Category_spin.setAdapter(Categpry);


        //=====================================================================================
        // 3. 사용자가 카테고리를 선택하면 해당 학년의 해당 카테고리의 과목 스피너를 생성한다.
        //    - 사용자가 카테고리를 선택했을때 카테고리 번호를 설정한다.
        //=====================================================================================

             Category_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                categoryNo = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        String[] arrItem1 = arrSubject[grade].split(",");	 // A001 특정학년의 과목 목록    - [0]01:01:국어,[1]01:02:영어....[11]03:11:체욱
        arrKey = new ArrayList<Integer>();       // 특정학년의 과목의 키번호 배얼
        arrData = new ArrayList<String>();        // 특정학년의 과목이름 배열

        // 사용자가 선택한 학년의 과목배열 전체를 루프 실행
        for (int i=0; i< arrItem1.length;i++)
        {
            String[]arrItem2 = arrItem1[i].split(":");	// [0]01, [1]01, [2]국어, [3]01, [4]01, [5]영어

            // 과목의 카테고리가 학생이 선택한 카테고리와 같으면 배열에 추가
            if (Integer.parseInt(arrItem2[0]) == categoryNo) 				// A003  index는 스피너에 들어있는 원소의 번호
            {
                arrKey.add(Integer.parseInt(arrItem2[1]));	// 04, 05, 06
                arrData.add(arrItem2[2]);	// 컴퓨터, 수학, 기계
            }
        }
        // arrData를 과목 스피너에 추가 : 컴퓨터, 수학, 기계

        Subject = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrData);
        Subject_spin.setAdapter(Subject);

        //=====================================================================================
        // 4. 사용자가  과목을 선택하면 선생님이 과목설명을 입력하는 란을 생성한다.
        //    - 과목 스피너를 선택햇을때 과목 index를 설정한다.
        //=====================================================================================

        Subject_spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                subjectIndex = position;   //subjectIndex = spinnerSubject.SelectedIndex;
                Subrange_edtxt.setVisibility(View.VISIBLE);
                AddEditDelSave_btn.setVisibility(View.VISIBLE);

                AddEditDelSave_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        arrData.remove(subjectIndex);
                        Subject.notifyDataSetChanged();
                    }
                    });



            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        SubrangeSave_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //int subjectIndex = 2;
                subjectMemoIndex = arrKey.get(subjectIndex);// arrKey[2] => 06
                subjectIndex1 = arrData.get(subjectIndex);

                switch(grade){

                    case 0:
                        arrSubjectMemo1[subjectMemoIndex] = Subrange_edtxt.getText().toString();
                        uploadToServer();

                        break;

                    case 1:
                        arrSubjectMemo2[subjectMemoIndex] =  Subrange_edtxt.getText().toString();
                        uploadToServer();

                        break;
                    case 2:
                         arrSubjectMemo3[subjectMemoIndex] = Subrange_edtxt.getText().toString();
                        //arrSubjectMemo3[subjectMemoIndex].uploadToServer();

                }

                Toast.makeText(getApplicationContext(),subjectIndex1+"과목의 시험범위가 저장되었습니다.",Toast.LENGTH_SHORT).show();
            }

        });

    }

    private void uploadToServer() {


     switch(grade){
         case 0 :
             //arrSubjectMemo1[subjectMemoIndex]을 서버에 올림
             break;

         case 1 :
             //arrSubjectMemo2[subjectMemoIndex]을 서버에 올림
             break;

         case 2 :
             //arrSubjectMemo3[subjectMemoIndex]을 서버에 올림
             break;

     }

    }
}
