package com.teammgh.cnboard;

import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class DdayActivity extends AppCompatActivity {

    Button button;
    ListView listView;
    DdayListViewAdapter adapter;
    boolean checking;
    final int NEW_DDAY = 21;
    private DBHelper_dday dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
        listView = (ListView) findViewById(R.id.listview);

        if(dbHelper == null) {
            dbHelper = new DBHelper_dday(DdayActivity.this,"TEST",null,1);
        }
        List data = dbHelper.getAllData();
        listView.setAdapter(new DdayListViewAdapter(data, DdayActivity.this));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DdayActivity.this, DdaySettingActivity.class);
                startActivityForResult(intent, NEW_DDAY);

            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder dlg = new AlertDialog.Builder(view.getContext());
                dlg.setTitle("삭제확인");
                dlg.setMessage("이 D-DAY를 정말 삭제하시겠습니까?");
                dlg.setIcon(R.drawable.icon);
                dlg.setNegativeButton("취소",null);
                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        List data = dbHelper.getAllData();
                        adapter = new DdayListViewAdapter(data, DdayActivity.this);
                        Ddaydatabase database = (Ddaydatabase) adapter.getItem(position);
                        int ID = database.get_id();
                        dbHelper.removeData(ID);
                        List tempdata = dbHelper.getAllData();
                        listView.setAdapter(new DdayListViewAdapter(tempdata, DdayActivity.this));
                    }
                });
                dlg.show();
                return true;
            }
        });
    }

    @Override
    protected void onActivityResult ( int requestCode, int resultCode, @Nullable Intent data){
        if (requestCode == NEW_DDAY) {
            {
                if (resultCode == RESULT_OK) {
                    int day = data.getExtras().getInt("day");
                    int year = data.getExtras().getInt("year");
                    int month = data.getExtras().getInt("month");
                    String title = data.getExtras().getString("Title");
                    int dday = data.getExtras().getInt("Dday");
                    checking = data.getExtras().getBoolean("check");

                    pushdata(title, year, month, day, dday);

                    List tempdata = dbHelper.getAllData();
                    listView.setAdapter(new DdayListViewAdapter(tempdata, DdayActivity.this));

                    if(checking == true) {
                        notificationshow(title,dday);
                    }
                }
            }
        }
    }

    public void notificationshow(String title, int dday) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"default");

        builder.setContentTitle(title);
        builder.setContentText("D - " + dday);
        builder.setAutoCancel(false);
        builder.setSmallIcon(R.drawable.icon);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            manager.createNotificationChannel(new NotificationChannel("default", "d-day" ,
                    NotificationManager.IMPORTANCE_DEFAULT));
        }
        manager.notify(1,builder.build());
    }

    public void pushdata(String title, int year, int month, int day, int dday) {
        if(dbHelper == null) {
            dbHelper = new DBHelper_dday(DdayActivity.this,"TEST", null,1);
        }

        Ddaydatabase ddaydatabase = new Ddaydatabase();
        ddaydatabase.setTitle(title);
        ddaydatabase.setYear(year);
        ddaydatabase.setMonth(month);
        ddaydatabase.setDay(day);
        ddaydatabase.setDday(dday);

        dbHelper.addData(ddaydatabase);
    }
}