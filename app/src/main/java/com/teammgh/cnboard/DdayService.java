package com.teammgh.cnboard;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import java.util.Calendar;

import androidx.core.app.NotificationCompat;

public class DdayService extends Service {

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String title = intent.getExtras().getString("title");
        int id = intent.getExtras().getInt("id");
        String dday = intent.getExtras().getString("dday");
        if("startForeground".equals(intent.getAction())) {
            startForegroudService(title,dday,id);
        }
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
       return null;
    }

    private void startForegroudService (String title, String dday, int id) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this,"default");

        builder.setContentTitle(title)
                .setContentText(dday)
                .setAutoCancel(false)
                .setSmallIcon(R.drawable.icon)
                .setOngoing(true);

        NotificationManager manager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        manager.notify(id, builder.build());

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//
//            //manager.createNotificationChannel(new NotificationChannel("default", "디데이" , NotificationManager.IMPORTANCE_DEFAULT));
//        }

        //startForeground(id, builder.build());
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

    public String setDday(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();

        Calendar dcalendar = Calendar.getInstance();
        dcalendar.set(year,month-1,day);

        long d_day = dcalendar.getTimeInMillis();
        long m_day = calendar.getTimeInMillis();

        int result = (int)((d_day - m_day) / (24*60*60*1000));
        String dday = DdayUpdate(result);
        return  dday;
    }
}
