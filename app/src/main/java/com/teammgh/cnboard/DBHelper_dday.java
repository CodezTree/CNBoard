package com.teammgh.cnboard;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHelper_dday extends SQLiteOpenHelper {

    private Context context;

    public DBHelper_dday(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context,name,factory,version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuffer sb = new StringBuffer();
        sb.append(" CREATE TABLE TEST_TABLE ( ");
        sb.append(" _ID INTEGER PRIMARY KEY AUTOINCREMENT, ");
        sb.append(" TITLE, ");
        sb.append(" YEAR, ");
        sb.append(" MONTH, ");
        sb.append(" DAY, ");
        sb.append(" DDAY, ");
        sb.append(" CHECKING ) ");

        db.execSQL(sb.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void addData(Ddaydatabase ddaydatabase) {
        SQLiteDatabase db = getWritableDatabase();

        StringBuffer sb = new StringBuffer();
        sb.append(" INSERT INTO TEST_TABLE ( ");
        sb.append(" TITLE, YEAR, MONTH, DAY, DDAY, CHECKING ) ");
        sb.append(" VALUES (?, ?, ?, ?, ?, ? ) ");

        db.execSQL(sb.toString(),
                new Object[] {
                        ddaydatabase.getTitle(),
                        ddaydatabase.getYear(),
                        ddaydatabase.getMonth(),
                        ddaydatabase.getDay(),
                        ddaydatabase.getDday(),
                        ddaydatabase.getChecking()});
    }

    public List getAllData() {
        StringBuffer sb = new StringBuffer();
        sb.append(" SELECT _ID, TITLE, YEAR, MONTH, DAY, DDAY, CHECKING FROM TEST_TABLE ");

        SQLiteDatabase db= getReadableDatabase();

        Cursor cursor = db.rawQuery(sb.toString(), null);

        List data = new ArrayList();
        Ddaydatabase ddaydatabase = null;

        while( cursor.moveToNext() ) {

            ddaydatabase = new Ddaydatabase();
            ddaydatabase.set_id(cursor.getInt(0));
            ddaydatabase.setTitle(cursor.getString(1));
            ddaydatabase.setYear(cursor.getInt(2));
            ddaydatabase.setMonth(cursor.getInt(3));
            ddaydatabase.setDay(cursor.getInt(4));
            ddaydatabase.setDday(cursor.getInt(5));
            ddaydatabase.setChecking(cursor.getInt(6));

            data.add(ddaydatabase);
        }
        return data;
    }

    public void removeData(int index){

        String sql = "delete from " + "TEST_TABLE" + " where _ID = "+index+";";
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);

    }
}
