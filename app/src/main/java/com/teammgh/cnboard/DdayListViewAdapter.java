package com.teammgh.cnboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

public class DdayListViewAdapter extends BaseAdapter {

    private List data;
    private Context context;

    public DdayListViewAdapter(List data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return this.data.size();
    }

    @Override
    public Object getItem(int position) {
        return this.data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textview_title = (TextView) convertView.findViewById(R.id.textview_title);
            viewHolder.textview_year = (TextView) convertView.findViewById(R.id.textview_year);
            viewHolder.textview_month = (TextView) convertView.findViewById(R.id.textview_month);
            viewHolder.textview_day = (TextView) convertView.findViewById(R.id.textview_day);
            viewHolder.textview_dday = (TextView) convertView.findViewById(R.id.textview_dday);
            convertView.setTag(viewHolder);
        }

        else {
            viewHolder = (ViewHolder)convertView.getTag();
        }

        Ddaydatabase database = (Ddaydatabase) getItem(position);

        int year = database.getYear();
        int month = database.getMonth();
        int day = database.getDay();

        viewHolder.textview_title.setText(database.getTitle());
        viewHolder.textview_year.setText(Integer.toString(year) + "년");
        viewHolder.textview_month.setText(Integer.toString(month) + "월");
        viewHolder.textview_day.setText(Integer.toString(day)+"일");
        viewHolder.textview_dday.setText(setDday(year, month, day));

        return convertView;
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

    public class ViewHolder {
        private TextView textview_title;
        private TextView textview_year;
        private TextView textview_month;
        private TextView textview_day;
        private TextView textview_dday;
    }
}