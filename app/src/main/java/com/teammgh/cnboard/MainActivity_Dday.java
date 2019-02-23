package com.teammgh.cnboard;

import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity_Dday extends AppCompatActivity {

    Button button_add_dday;
    ListView listView;
    ListViewAdapter adapter;
    boolean checking;
    final int NEW_DDAY = 21;
    String shared = "file";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ddaymain);

        button_add_dday = (Button) findViewById(R.id.button_add_dday);
        listView = (ListView) findViewById(R.id.listview);

        adapter = new ListViewAdapter();
        listView.setAdapter(adapter);

/* 데이터베이스 부분 오류 --------------------------------------------------------------------------------
        SharedPreferences sharedPreferences = getSharedPreferences(shared,0);
        int count = sharedPreferences.getInt("count", 0);

        for(int i = 1 ; i <= count ; i++) {
            setData(i);
        }*/

        button_add_dday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity_Dday.this, DdayActivity.class);
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
                        adapter.deleteitem(position);
                        listView.clearChoices();
                        adapter.notifyDataSetChanged();
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
                    adapter.additem(title, year, month, day, dday);
                    adapter.notifyDataSetChanged();

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
/* 데이터베이스 부분 오류 --------------------------------------------------------------------------------

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences sharedPreferences = getSharedPreferences(shared, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        int dataCount = adapter.getCount();
        editor.putInt("count",dataCount);
        for(int i = 1 ; i <= dataCount ; i++) {
            saveData(i);
        }
    }

    public void saveData(int pos) {
        SharedPreferences sharedPreferences = getSharedPreferences(shared, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        ListViewItem listViewItem = (ListViewItem) adapter.getItem(pos);
        String title = listViewItem.getTitle();

        int year = listViewItem.getYear();
        int month = listViewItem.getMonth();
        int day = listViewItem.getDay();
        int dday = listViewItem.getDday();
        editor.putString("title" + Integer.toString(pos), title);
        editor.putInt("year" + Integer.toString(pos) , year);
        editor.putInt("month" + Integer.toString(pos) , month);
        editor.putInt("day" + Integer.toString(pos) , day);
        editor.putInt("dday" + Integer.toString(pos) , dday);
        editor.commit();
    }

    public void setData(int pos) {
        SharedPreferences sharedPreferences = getSharedPreferences(shared,0);
        String title = sharedPreferences.getString("title" + Integer.toString(pos),"");
        int year = sharedPreferences.getInt("year" + Integer.toString(pos), 0);
        int month = sharedPreferences.getInt("month" + Integer.toString(pos), 0);
        int day = sharedPreferences.getInt("day" + Integer.toString(pos), 0);
        int dday = sharedPreferences.getInt("dday" + Integer.toString(pos), 0);

        adapter.additem(title,year,month,day,dday);
        adapter.notifyDataSetChanged();
    }*/
}