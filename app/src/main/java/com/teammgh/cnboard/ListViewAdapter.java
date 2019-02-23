package com.teammgh.cnboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {
    private ArrayList<ListViewItem> listViewItemlist = new ArrayList<>();

    public void additem(String title, int year, int month, int day, int dday){
        ListViewItem item = new ListViewItem();

        item.setTitle(title);
        item.setYear(year);
        item.setMonth(month);
        item.setDay(day);
        item.setDday(dday);

        listViewItemlist.add(item);
    }

    public void deleteitem(int pos) {
        this.listViewItemlist.remove(pos);
    }

    @Override
    public int getCount() {
        return listViewItemlist.size();
    }

    @Override
    public Object getItem(int position) {
        return listViewItemlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Context context = parent.getContext();

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item, parent, false);
        }

        TextView textview_title = (TextView) convertView.findViewById(R.id.textview_title);
        TextView textview_year = (TextView) convertView.findViewById(R.id.textview_year);
        TextView textview_month = (TextView) convertView.findViewById(R.id.textview_month);
        TextView textview_day = (TextView) convertView.findViewById(R.id.textview_day);
        TextView textview_dday = (TextView) convertView.findViewById(R.id.textview_dday);

        ListViewItem listViewItem = (ListViewItem) getItem(position);

        textview_title.setText(listViewItem.getTitle());
        textview_year.setText(Integer.toString(listViewItem.getYear())+ "년");
        textview_month.setText(Integer.toString(listViewItem.getMonth()) + "월");
        textview_day.setText(Integer.toString(listViewItem.getDay())+ "일") ;
        textview_dday.setText("D " + DdatUpdate(listViewItem.getDday()));

        return convertView;
    }

    public String DdatUpdate(int r) {
        if(r >= 0) {
            return "- " + r ;
        }
        else{
            return "+" + ((r - 1) * -1) ;
        }
    }
}