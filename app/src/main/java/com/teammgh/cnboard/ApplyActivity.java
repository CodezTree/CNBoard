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

    TextView tv_student_number;
    EditText et_apply_part;
    TextInputEditText tiet_apply;
    Button bt_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply);

        tv_student_number = (TextView) findViewById(R.id.tv_student_number);
        et_apply_part = (EditText) findViewById(R.id.et_apply_part);
        tiet_apply = (TextInputEditText) findViewById(R.id.tiet_apply);
        bt_submit = (Button) findViewById(R.id.bt_submit);

        tv_student_number.setText(Integer.toString(student_num));
        bt_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(view.getContext());
                dlg.setTitle("제출 확인");
                dlg.setMessage("지원서를 제출 하시겠습니까?");
                dlg.setIcon(R.drawable.icon);
                dlg.setNegativeButton("취소", null);
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Submit();
                    }
                });
                dlg.show();
            }
        });
    }

    public void Submit() {
        if (et_apply_part.getText() != null && tiet_apply.getText() != null) {
            Response.Listener<String> responseListener = new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Toast.makeText(getApplicationContext(), "제출되었습니다", Toast.LENGTH_SHORT);
                }
            };

            ApplyRequest applyRequest = new ApplyRequest(responseListener, student_num, et_apply_part.getText().toString(), tiet_apply.getText().toString());
            RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
            queue.add(applyRequest);
        } else {
            AlertDialog.Builder dlg = new AlertDialog.Builder(getApplicationContext());
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
        AlertDialog.Builder dlg = new AlertDialog.Builder(getApplicationContext());
        dlg.setTitle("");
        dlg.setMessage("돌아가시겠습니까?");
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