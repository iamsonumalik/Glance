package com.app.newsonrun;

/**
 * Created by malik on 28/6/15.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class SavingCache {


    public static final String History_sno="sno";
    public static final String History_cache_publicid = "publicid";
    public static final String History_cache_timestamp = "timestampcreated";
    public static final String DATABASE_NAME="comappnewsonruncache";
    public static final String DATABASE_TABLE1="cache_table";

    public static final int DATABASE_VERSION=1;

    private SHHelper ourHelper;
    private final Context ourcontext;
    private static SQLiteDatabase ourdatabase;



    private static class SHHelper extends SQLiteOpenHelper{

        public SHHelper(Context context){
            super(context,DATABASE_NAME	,null, DATABASE_VERSION);
            // TODO Auto-generated constructor stub
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // TODO Auto-generated method stub
            db.execSQL("CREATE TABLE " + DATABASE_TABLE1 + " (" +
                            History_sno + " INTEGER ," +
                            History_cache_publicid + " VARCHAR2(400) PRIMARY KEY , " +
                            History_cache_timestamp + " VARCHAR2(400));"
            );
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
            // TODO Auto-generated method stub
            db.execSQL("Drop table if exists " + DATABASE_TABLE1);
            onCreate(db);


        }

    }

    public SavingCache(Context c){
        ourcontext =c;
    }

    public SavingCache open()throws Exception{
        ourHelper = new SHHelper(ourcontext);
        ourdatabase = ourHelper.getWritableDatabase();
        return this;
    }

    public void close() throws Exception{
        // TODO Auto-generated method stub
        ourHelper.close();
    }

    public long createEntry(int sno,
                            String publicid,
                            String timestampcreated
                            )throws Exception {
        // TODO Auto-generated method stub
        ContentValues cv = new ContentValues();
        cv.put(History_sno,sno);
        cv.put(History_cache_publicid,publicid);
        cv.put(History_cache_timestamp, timestampcreated);

        return ourdatabase.insert(DATABASE_TABLE1, null, cv);
    }


    public ArrayList<String> getAll() {
        // TODO Auto-generated method stub
        String colums[] = new String[]{History_cache_publicid,History_cache_timestamp};
        Cursor c= ourdatabase.query(DATABASE_TABLE1, colums, null, null, null, null, History_cache_timestamp + " DESC");
        ArrayList<String> result = new ArrayList<String>();
        int i_pid = c.getColumnIndex(History_cache_publicid);
        int i = 0;
        for(c.moveToLast();!c.isBeforeFirst();c.moveToPrevious()){
            result.add(c.getString(i_pid));

        }

        return result;
    }
    public int getTotal() {
        // TODO Auto-generated method stub
        String colums[] = new String[]{History_cache_publicid,History_cache_timestamp};
        Cursor c= ourdatabase.query(DATABASE_TABLE1, colums, null, null, null, null, History_cache_timestamp + " DESC");
        return  c.getCount();
    }
    public void deleteItem(String name) {
        ourdatabase.delete(DATABASE_TABLE1,History_cache_publicid+"='"+name+"'",null);
    }

    public void dropdb(){

        ourcontext.deleteDatabase(DATABASE_NAME);
    }

}
