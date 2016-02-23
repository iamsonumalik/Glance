package com.app.newsonrun;

/**
 * Created by malik on 28/6/15.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SavingYoutubeLink {


    public static final String History_sno="sno";
    public static final String History_public_id = "publicid";
    public static final String History_youtubeVideoId = "youtubevideoid";
    public static final String DATABASE_NAME="comappnewsonrunyoutubevideoid";
    public static final String DATABASE_TABLE1="youtubevideoid_table";

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
                            History_public_id + " VARCHAR2(400) PRIMARY KEY , " +
                            History_youtubeVideoId + " VARCHAR2(400));"
            );
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
            // TODO Auto-generated method stub
            db.execSQL("Drop table if exists " + DATABASE_TABLE1);
            onCreate(db);


        }

    }

    public SavingYoutubeLink(Context c){
        ourcontext =c;
    }

    public SavingYoutubeLink open()throws Exception{
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
                            String youtubevideoid
    )throws Exception {
        // TODO Auto-generated method stub
        ContentValues cv = new ContentValues();
        cv.put(History_sno,sno);
        cv.put(History_public_id,publicid);
        cv.put(History_youtubeVideoId,youtubevideoid);

        return ourdatabase.insert(DATABASE_TABLE1, null, cv);
    }



    public String get_youtubeVideoId(String publicid) {
        String result="";
        String colums[] = new String[]{History_public_id,History_youtubeVideoId};
        Cursor c= ourdatabase.query(DATABASE_TABLE1,colums, History_public_id+"='"+publicid+"'",null, null,  null,null);
        int iurl = c.getColumnIndex(History_youtubeVideoId);
        int i = 0;
        for(c.moveToLast();!c.isBeforeFirst();c.moveToPrevious()){
            result=c.getString(iurl);
        }
        return  result;
    }
    public boolean getData() {
        // TODO Auto-generated method stub

        boolean result=false;
        String colums[] = new String[]{History_sno};
        Cursor c= ourdatabase.query(DATABASE_TABLE1,colums, null,null, null,  null,null);
        int iurl = c.getColumnIndex(History_sno);
        int i = 0;
        for(c.moveToLast();!c.isBeforeFirst();c.moveToPrevious()){
            result=true;
        }
        return result;
    }


    public void dropdb(){

        ourcontext.deleteDatabase(DATABASE_NAME);
    }

}
