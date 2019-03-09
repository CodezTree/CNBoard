package com.teammgh.cnboard;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.util.Log;

import java.util.Calendar;
import java.util.List;

import androidx.core.app.NotificationCompat;

public class DdayService extends Service {

    long mday = 0;
    Thread thread;
    private DBHelper_dday dbHelper;
    int DAY_MILLIS = 86400000;
    DdayListViewAdapter adapter;
    private Calendar calendar = Calendar.getInstance();

    public DdayService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mday = calendar.getTimeInMillis();
        thread = new Thread() {
            public void run() {
                while (true) {
                    try {
                        thread.sleep(240000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mday = mday + 240000;     // mday = calendar.getTimeInMillis();

                    if (mday >= DAY_MILLIS) {       //DAY_MILLIS = 86400000 (하루)

                        if(dbHelper == null) {
                            dbHelper = new DBHelper_dday(DdayService.this,"TEST",null,1);
                        }

                        List data = dbHelper.getAllData();
                        adapter = new DdayListViewAdapter(data,getApplicationContext());
                        int count = adapter.getCount();

                        for (int i = 0; i < count; i++) {
                            Ddaydatabase database = (Ddaydatabase) adapter.getItem(i);

                            if (database.getChecking() == 1) {  //알림 재설정
                                createNotification(database.getTitle(),setDday(i),database.get_id());
                            }
                        }
                    }
                }
            }
        };
        thread.start();
        return START_STICKY;
    }

    private void createNotification(String title, String dday, int id) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"default");

        builder.setContentTitle(title)
                .setContentText(dday)
                .setAutoCancel(false)
                .setSmallIcon(R.drawable.icon)
                .setOngoing(true);

        NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(new NotificationChannel("default", "기본 채널", NotificationManager.IMPORTANCE_DEFAULT));
        }
        notificationManager.notify(id,builder.build());
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


