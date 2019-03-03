package com.teammgh.cnboard;

import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
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
    private BroadcastReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ddaymain);

        button = (Button) findViewById(R.id.button_add_dday);
        listView = (ListView) findViewById(R.id.ddaylistview);

        Calendar calendar = Calendar.getInstance();
        mday = calendar.getTimeInMillis();

        if(dbHelper == null) {
            dbHelper = new DBHelper_dday(DdayActivity.this,"TEST",null,1);
        }
        List data = dbHelper.getAllData();
        listView.setAdapter(new DdayListViewAdapter(data, DdayActivity.this));

        thread = new Thread() {
            public void run() {
                while(true) {
                    try {
                        thread.sleep(300000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mday = mday + 300000;     // mday = calendar.getTimeInMillis();

                    if(mday >= DAY_MILLIS) {       //DAY_MILLIS = 86400000 (하루)
                        int count = adapter.getCount();

                        for (int i = 0; i < count ; i++) {
                            Ddaydatabase database = (Ddaydatabase) adapter.getItem(i);

                            if(database.getChecking() == 1) {  //알림 재설정
                                Intent intent = new Intent(DdayActivity.this, DdayService.class);
                                intent.setAction("startForeground");
                                intent.putExtra("title",database.getTitle());
                                intent.putExtra("id",database.get_id());
                                intent.putExtra("dday",setDday(i));

                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                    startForegroundService(intent);
                                }
                                else {
                                    startService(intent);
                                }
                            }
                        }
                        mday = 0;
                    }
                }
            }
        };
        thread.start();

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
                    int checking = data.getExtras().getInt("check");

                    pushdata(title, year, month, day, dday, checking);

                    List tempdata = dbHelper.getAllData();
                    adapter = new DdayListViewAdapter(tempdata,DdayActivity.this);
                    listView.setAdapter(adapter);

                    int count = adapter.getCount();
                    for (int i = 0; i < count ; i++) {
                        Ddaydatabase database = (Ddaydatabase) adapter.getItem(i);

                        if(database.getChecking() == 1) {
                            Intent intent = new Intent(this, DdayService.class);
                            intent.setAction("startForeground");
                            intent.putExtra("title",title);
                            intent.putExtra("id",database.get_id());
                            intent.putExtra("dday",setDday(i));

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                startForegroundService(intent);
                            }
                            else {
                                startService(intent);
                            }
                        }
                    }
                }
            }
        }
    }

    public void pushdata(String title, int year, int month, int day, int dday, int checking) {
        if(dbHelper == null) {
            dbHelper = new DBHelper_dday(DdayActivity.this,"TEST", null,1);
        }

        Ddaydatabase ddaydatabase = new Ddaydatabase();
        ddaydatabase.setTitle(title);
        ddaydatabase.setYear(year);
        ddaydatabase.setMonth(month);
        ddaydatabase.setDay(day);
        ddaydatabase.setDday(dday);
        ddaydatabase.setChecking(checking);

        dbHelper.addData(ddaydatabase);
    }

    public String DdayUpdate(int r) {

        if(r > 0) {
            return "D - " + r ;
        }
        else if(r == 0) {
            return "D - DAY" ;
        }
        else{
            return "D + " + ( r * -1) ;
        }
    }

    public String setDday(int i) {
        long m_day = System.currentTimeMillis();

        Ddaydatabase database = (Ddaydatabase) adapter.getItem(i);
        int year = database.getYear();
        int month = database.getMonth();
        int day = database.getDay();

        Calendar dcalendar = Calendar.getInstance();
        dcalendar.set(year,month-1,day);

        long d_day = dcalendar.getTimeInMillis();

        int result = (int)((d_day - m_day) / (24*60*60*1000));
        String dday = DdayUpdate(result);
        return  dday;
    }
}