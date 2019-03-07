package com.teammgh.cnboard;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import java.util.Calendar;

import androidx.core.app.NotificationCompat;

public class DdayService extends Service {

    long mday;
    Calendar calendar;
    Thread thread;
    int DAY_MILLIS = 86400000;
    DdayListViewAdapter adapter;

    public DdayService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /*@Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mday = calendar.getTimeInMillis();
        thread = new Thread() {
            public void run() {
                while (true) {
                    try {
                        thread.sleep(300000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mday = mday + 300000;     // mday = calendar.getTimeInMillis();

                    if (mday >= DAY_MILLIS) {       //DAY_MILLIS = 86400000 (하루)
                        int count = adapter.getCount();

                        for (int i = 0; i < count; i++) {
                            Ddaydatabase database = (Ddaydatabase) adapter.getItem(i);

                            if (database.getChecking() == 1) {  //알림 재설정
                               //createNotification(database.getTitle(),setDday(i),database.get_id());
                            }
                        }
                    }
                }
            }
        }.start();

        return START_STICKY;
    }*/

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


