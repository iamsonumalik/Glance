package com.app.newsonrun;

/**
 * Created by malik on 28/6/15.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SavingToken {


    public static final String History_url="accesstoken";
    public static final String DATABASE_NAME="comappnewsonruntokennumber";
    public static final String DATABASE_TABLE1="tokennumber";

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
                           History_url + " VARCHAR2(4000));"
            );
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
            // TODO Auto-generated method stub
            db.execSQL("Drop table if exists " + DATABASE_TABLE1);
            onCreate(db);


        }

    }

    public SavingToken(Context c){
        ourcontext =c;
    }

    public SavingToken open()throws Exception{
        ourHelper = new SHHelper(ourcontext);
        ourdatabase = ourHelper.getWritableDatabase();
        return this;
    }

    public void close() throws Exception{
        // TODO Auto-generated method stub
        ourHelper.close();
    }

    public long createEntry(String accesstoken)throws Exception {
        // TODO Auto-generated method stub
        ContentValues cv = new ContentValues();
        cv.put(History_url,accesstoken);
        return ourdatabase.insert(DATABASE_TABLE1, null, cv);
    }



    public boolean getData() {
        // TODO Auto-generated method stub

        boolean result=false;
        String colums[] = new String[]{History_url};
        Cursor c= ourdatabase.query(DATABASE_TABLE1,colums, null,null, null,  null,null);
        int iurl = c.getColumnIndex(History_url);
        int i = 0;
        for(c.moveToLast();!c.isBeforeFirst();c.moveToPrevious()){
           result=true;
        }
        return result;
    }


    public String getDataString() {
        // TODO Auto-generated method stub

        String colums[] = new String[]{History_url};
        Cursor c= ourdatabase.query(DATABASE_TABLE1,colums, null,null, null,  null,null);
        String result [] = new String[1000];



        int iurl = c.getColumnIndex(History_url);

        int i = 0;
        for(c.moveToLast();!c.isBeforeFirst();c.moveToPrevious()){
            try{
                result[i] = c.getString(iurl);
                i++;}catch(Exception e){
                break;
            }
        }

        String send = result[0];
        return send;
    }

    public void dropdb(){

        ourcontext.deleteDatabase(DATABASE_NAME);
    }

}
