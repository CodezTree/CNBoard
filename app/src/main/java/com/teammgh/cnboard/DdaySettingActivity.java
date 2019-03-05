package com.teammgh.cnboard;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class DdaySettingActivity extends AppCompatActivity {

    EditText edittext_title;
    LinearLayout linear_date;
    Switch switch_alert;
    Button button_cancel;
    Button button_ok;
    TextView textview_mday;
    int checking = 0;
    final int DIALOG_DATE = 1;
    private int mYear, mMonth, mDay;
    private int dYear = 1 , dMonth = 1 , dDay = 1;
    private long d;
    private long t;
    private long r;
    private int result;

    Toolbar myToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dday);

        // 툴바입니다 건들 ㄴㄴ

        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true); // 커스터마이징
        actionBar.setDisplayHomeAsUpEnabled(true); // 뒤로가기 버튼

        // 툴바입니다 건들 ㄴㄴ

        edittext_title = findViewById(R.id.edittext_title);
        linear_date = findViewById(R.id.linear_date);
        switch_alert = findViewById(R.id.switch_alert);
        button_cancel = findViewById(R.id.button_cancel);
        button_ok = findViewById(R.id.button_ok);
        textview_mday = findViewById(R.id.textview_mday);

        Calendar cal = Calendar.getInstance();
        mYear = cal.get(Calendar.YEAR);
        mMonth = cal.get(Calendar.MONTH) + 1;
        mDay = cal.get(Calendar.DAY_OF_MONTH);

        textview_mday.setText(mYear + "년 " +mMonth + "월 " + mDay + "일 ");

        Calendar dcal = Calendar.getInstance();
        dcal.set(dYear,dMonth,dDay);

        t = cal.getTimeInMillis();
        d = dcal.getTimeInMillis();
        r = (d-t)/(24*60*60*1000);
        result = (int)r + 1 ;

        linear_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               DatePickerDialog dialog =  new DatePickerDialog(DdaySettingActivity.this, dDateSstListener, mYear, mMonth - 1, mDay);
               dialog.show();
            }
        });

        switch_alert.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    Toast.makeText(getApplicationContext(), "알림이 설정되었습니다.",Toast.LENGTH_SHORT).show();
                    checking = 1;
                }
                else{
                    Toast.makeText(getApplicationContext(), "알림이 해제되었습니다.",Toast.LENGTH_SHORT).show();
                    checking = 0;
                }
            }
        });

        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"취소되었습니다." ,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),DdayActivity.class);
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = edittext_title.getText().toString();

                if(title.isEmpty()) {
                    Toast.makeText(getApplicationContext(),"제목을 설정해주세요." ,Toast.LENGTH_SHORT).show();
                }

                else{
                    if(dYear == 1 && dMonth == 1 && dDay == 1) {
                        Toast.makeText(getApplicationContext(),"날짜를 설정해주세요", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "D-DAY 가 등록되었습니다.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent();
                        intent.putExtra("Dday", result);
                        intent.putExtra("Title", title);
                        intent.putExtra("year", dYear);
                        intent.putExtra("month", dMonth);
                        intent.putExtra("day", dDay);
                        intent.putExtra("check",checking);
                        setResult(RESULT_OK, intent);

                        finish();
                    }
                }
            }
        });
    }
    private DatePickerDialog.OnDateSetListener dDateSstListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
            dYear = i;
            dMonth = i1;
            dDay = i2;

            final Calendar dcal = Calendar.getInstance();
            dcal.set(dYear,dMonth,dDay);

            d = dcal.getTimeInMillis();
            r = (d-t)/(24*60*60*1000);
            result = (int)r;
            dMonth = dMonth + 1;

            textview_mday.setText(dYear + "년 " + dMonth + "월 " + dDay + "일 ");
        }
    };
    /*protected Dialog onCreateDialog (int id) {
        switch (id) {
            case DIALOG_DATE :
                return new DatePickerDialog(this,dDateSstListener,mYear,mMonth,mDay);
            default:
                return null;
        }
    }*/

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