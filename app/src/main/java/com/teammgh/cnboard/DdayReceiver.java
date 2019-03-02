package com.teammgh.cnboard;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import java.util.Calendar;

public class DdayReceiver extends BroadcastReceiver {

    DdayListViewAdapter adapter;
    Context context;

    @Override
    public void onReceive(Context context, Intent intent) {

        this.context = context;
        int count = adapter.getCount();

        if(Intent.ACTION_DATE_CHANGED.equals(intent.getAction())) {
            for (int i = 0; i < count ; i++) {
                Ddaydatabase database = (Ddaydatabase) adapter.getItem(i);
                String title = database.getTitle();
                int checking = database.getChecking();
                int id = database.get_id();

                if(checking == 1) {
                    String dday = setDday(i);
                    Intent notifiintent = new Intent(context, DdayService.class);
                    notifiintent.setAction("startForeground");
                    notifiintent.putExtra("title",title);
                    notifiintent.putExtra("id",id);
                    notifiintent.putExtra("dday", dday);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        this.context.startForegroundService(notifiintent);
                    }
                    else {
                        this.context.startService(notifiintent);
                    }
                }
            }
        }
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