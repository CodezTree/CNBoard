package com.teammgh.cnboard;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import static com.teammgh.cnboard.Global.student_num;

public class ApplyActivity extends AppCompatActivity {

    EditText et_student_number;
    EditText et_apply_part;
    TextInputEditText tiet_apply;
    Button bt_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply);

        et_student_number = (EditText) findViewById(R.id.et_student_number);
        et_apply_part = (EditText) findViewById(R.id.et_apply_part);
        tiet_apply = (TextInputEditText) findViewById(R.id.tiet_apply);
        bt_submit = (Button) findViewById(R.id.bt_submit);

        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(ApplyActivity.this);
                dlg.setTitle("제출 확인");
                dlg.setMessage("지원서를 제출 하시겠습니까?");
                dlg.setIcon(R.drawable.icon);
                dlg.setNegativeButton("취소", null);
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d("test", "Submit!!");
                        Submit();
                    }
                });
                dlg.show();
            }
        });
    }

    public void Submit() {
        Log.d("test", "ADD : " + et_apply_part.getText().toString() + tiet_apply.getText().toString());
        Log.d("test", Integer.toString(et_apply_part.getText().length()));
        if (et_apply_part.getText().length() != 0 && tiet_apply.getText().length() != 0 && et_student_number.getText().length() != 0) {
            Response.Listener<String> responseListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(getApplicationContext(), "제출되었습니다", Toast.LENGTH_SHORT);
                }
            };

            ApplyRequest applyRequest = new ApplyRequest(responseListener, Integer.parseInt(et_student_number.getText().toString()), et_apply_part.getText().toString(), tiet_apply.getText().toString());
            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            queue.add(applyRequest);
            Log.d("test","Applyed");
        } else {
            AlertDialog.Builder dlg = new AlertDialog.Builder(ApplyActivity.this);
            dlg.setTitle("오류");
            dlg.setMessage("모든 란을 빠짐없이 채워주세요!");
            dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
            dlg.show();
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder dlg = new AlertDialog.Builder(ApplyActivity.this);
        dlg.setTitle("경고");
        dlg.setMessage("지원을 그만 두시겠습니까?");
        dlg.setIcon(R.drawable.icon);
        dlg.setNegativeButton("취소", null);
        dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        dlg.show();
    }
}