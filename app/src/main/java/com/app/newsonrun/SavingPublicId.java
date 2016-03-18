package com.app.newsonrun;

/**
 * Created by malik on 28/6/15.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class SavingPublicId {


    public static final String History_sno="sno";
    public static final String History__id = "id";
    public static final String History_public_id = "publicid";
    public static final String History_timestampcreated = "timestampcreated";
    public static final String History_category = "category";
    public static final String History_issimplified = "issimplified";
    public static final String History_isviral = "isviral";
    public static final String History_editorsrating = "editorRating";
    public static final String History_state = "state";
    public static final String History_breakingNews = "breakingNews";
    public static final String History_enabled = "enabled";
    public static final String History_creditname = "creditname";
    public static final String History_othertags = "othertags";
    public static final String History_isFact = "isFact";
    public static final String History_linkedToNews = "linkedToNews";
    public static final String DATABASE_NAME="comappnewsonrunpublicid";
    public static final String DATABASE_TABLE1="publicid_table";
    public static final String History_headline="headline";
    public static final String History_isS3="isS";

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
                            History_public_id + " VARCHAR2(400), " +
                            History_headline + " VARCHAR2(400), " +
                            History__id + " VARCHAR2(400) PRIMARY KEY , " +
                            History_timestampcreated + " VARCHAR2(400), " +
                            History_category + " VARCHAR2(40), " +
                            History_issimplified + " VARCHAR2(10), " +
                            History_isviral + " VARCHAR2(10), " +
                            History_editorsrating + " INTEGER, " +
                            History_state + " VARCHAR2(20), " +
                            History_breakingNews + " VARCHAR2(10), " +
                            History_enabled + " VARCHAR2(10), " +
                            History_othertags + " VARCHAR2(400), " +
                            History_creditname + " VARCHAR2(400), " +
                            History_isFact + " VARCHAR2(10), " +
                            History_isS3 + " VARCHAR2(10), " +
                            History_linkedToNews + " VARCHAR2(400));"
            );
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
            // TODO Auto-generated method stub
            db.execSQL("Drop table if exists " + DATABASE_TABLE1);
            onCreate(db);


        }

    }

    public int getTotal() {
        // TODO Auto-generated method stub
        String colums[] = new String[]{History_public_id};
        Cursor c= ourdatabase.query(DATABASE_TABLE1, colums, null, null, null, null, History_timestampcreated + " DESC");
        return  c.getCount();
    }
    public SavingPublicId(Context c){
        ourcontext =c;
    }

    public SavingPublicId open()throws Exception{
        ourHelper = new SHHelper(ourcontext);
        ourdatabase = ourHelper.getWritableDatabase();
        return this;
    }

    public void close() throws Exception{
        // TODO Auto-generated method stub
        ourHelper.close();
    }

    public long createEntry(int sno,
                            String _id,
                            String publicid,
                            String timestampcreated,
                            String category,
                            String issimplified,
                            String isviral,
                            int editorRating,
                            String state,
                            String breakingNews,
                            String enabled,
                            String othertags,
                            String linkedToNews,
                            String isFact,
                            String headline, String isS3, String creditname)throws Exception {
        // TODO Auto-generated method stub
        ContentValues cv = new ContentValues();
        cv.put(History_sno,sno);
        cv.put(History_public_id,publicid);
        cv.put(History__id,_id);
        cv.put(History_timestampcreated,timestampcreated);
        cv.put(History_category,category);
        cv.put(History_issimplified,issimplified);
        cv.put(History_isviral,isviral);
        cv.put(History_editorsrating,editorRating);
        cv.put(History_state,state);
        cv.put(History_breakingNews,breakingNews);
        cv.put(History_enabled,enabled);
        cv.put(History_othertags,othertags);
        cv.put(History_linkedToNews,linkedToNews);
        cv.put(History_isFact,isFact);
        cv.put(History_headline,headline);
        cv.put(History_isS3,isS3);
        cv.put(History_creditname,creditname);
        return ourdatabase.insert(DATABASE_TABLE1, null, cv);
    }



    public String get_id(String publicid) {
        String result="";
        String colums[] = new String[]{History__id,History_public_id};
        Cursor c= ourdatabase.query(DATABASE_TABLE1,colums, History_public_id+"='"+publicid+"'",null, null,  null,null);
        int iurl = c.getColumnIndex(History__id);
        int i = 0;
        for(c.moveToLast();!c.isBeforeFirst();c.moveToPrevious()){
            result=c.getString(iurl);
        }
        return  result;
    }
    public String getp_id(String newsPost) {
        String result="";
        String colums[] = new String[]{History__id,History_public_id};
        Cursor c= ourdatabase.query(DATABASE_TABLE1,colums, History__id+"='"+newsPost+"'",null, null,  null,null);
        int iurl = c.getColumnIndex(History_public_id);
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
        Cursor c= ourdatabase.query(DATABASE_TABLE1, colums, null, null, null, null, null);
        int iurl = c.getColumnIndex(History_sno);
        int i = 0;
        for(c.moveToLast();!c.isBeforeFirst();c.moveToPrevious()){
            result=true;
        }
        return result;
    }


    public ArrayList<String> getByCategory(String category) {
        // TODO Auto-generated method stub
        String colums[] = new String[]{History__id,History_public_id,History_isS3,History_category,History_timestampcreated,History_headline,History_isviral,History_othertags,History_linkedToNews};
        Cursor c= ourdatabase.query(DATABASE_TABLE1,colums,History_category+"='"+category+"' AND "+History_isviral+"='false' AND "+History_isFact+"='false'",null, null,  null,History_timestampcreated+" ASC");
        ArrayList<String> result = new ArrayList<String>();
        //int i_id = c.getColumnIndex(History__id);
        int i_id = c.getColumnIndex(History__id);
        int i_pid = c.getColumnIndex(History_public_id);
        int i_othertags = c.getColumnIndex(History_othertags);
        int i_linkedToNews = c.getColumnIndex(History_linkedToNews);
        int i_timestap = c.getColumnIndex(History_timestampcreated);
        int i_head = c.getColumnIndex(History_headline);
        int i_cat = c.getColumnIndex(History_category);
        int i_s3 = c.getColumnIndex(History_isS3);
        int i = 0;

        for(c.moveToLast();!c.isBeforeFirst();c.moveToPrevious()){
            //result.add(c.getString(i_id));
            if (i<200) {
                result.add(c.getString(i_pid));
                result.add(c.getString(i_othertags));
                result.add(c.getString(i_id));
                result.add(c.getString(i_linkedToNews));
                result.add(c.getString(i_timestap));
                result.add(c.getString(i_head));
                result.add(c.getString(i_cat));
                result.add(c.getString(i_s3));
                i++;
            }else {
                break;
            }


        }
        return result;
    }


    public ArrayList<String> getSimplified(String aTrue) {
        String colums[] = new String[]{History__id,History_public_id,History_isS3,History_category,History_issimplified,History_headline,History_timestampcreated,History_isviral,History_othertags,History_linkedToNews};
        Cursor c= ourdatabase.query(DATABASE_TABLE1,colums,History_issimplified+"='"+aTrue+"' AND "+History_isviral+"='false' AND "+History_isFact+"='false'",null, null,  null,History_timestampcreated+" ASC");
        ArrayList<String> result = new ArrayList<String>();
        //int i_id = c.getColumnIndex(History__id);
        int i_id = c.getColumnIndex(History__id);
        int i_pid = c.getColumnIndex(History_public_id);
        int i_othertags = c.getColumnIndex(History_othertags);
        int i_linkedToNews = c.getColumnIndex(History_linkedToNews);
        int i_timestap = c.getColumnIndex(History_timestampcreated);
        int i_head = c.getColumnIndex(History_headline);
        int i_cat = c.getColumnIndex(History_category);
        int i_s3 = c.getColumnIndex(History_isS3);
        int i = 0;

        for(c.moveToLast();!c.isBeforeFirst();c.moveToPrevious()){
            //result.add(c.getString(i_id));
            if (i<200) {
                result.add(c.getString(i_pid));
                result.add(c.getString(i_othertags));
                result.add(c.getString(i_id));
                result.add(c.getString(i_linkedToNews));
                result.add(c.getString(i_timestap));
                result.add(c.getString(i_head));
                result.add(c.getString(i_cat));
                result.add(c.getString(i_s3));
                i++;
            }else {
                break;
            }


        }
        return result;
    }
    public ArrayList<String> getviral() {
        String colums[] = new String[]{History__id,History_public_id,History_isS3,History_category,History_issimplified,History_headline,History_timestampcreated,History_isviral,History_othertags,History_linkedToNews};
        Cursor c= ourdatabase.query(DATABASE_TABLE1, colums, History_isviral+"='true' AND "+History_isFact+"='false'",null, null,  null,History_timestampcreated+" ASC");
        ArrayList<String> result = new ArrayList<String>();
        //int i_id = c.getColumnIndex(History__id);
        int i_id = c.getColumnIndex(History__id);
        int i_pid = c.getColumnIndex(History_public_id);
        int i_othertags = c.getColumnIndex(History_othertags);
        int i_linkedToNews = c.getColumnIndex(History_linkedToNews);
        int i_timestap = c.getColumnIndex(History_timestampcreated);
        int i_head = c.getColumnIndex(History_headline);
        int i_cat = c.getColumnIndex(History_category);
        int i_s3 = c.getColumnIndex(History_isS3);
        int i = 0;

        for(c.moveToLast();!c.isBeforeFirst();c.moveToPrevious()){
            //result.add(c.getString(i_id));
            if (i<200) {
                result.add(c.getString(i_pid));
                result.add(c.getString(i_othertags));
                result.add(c.getString(i_id));
                result.add(c.getString(i_linkedToNews));
                result.add(c.getString(i_timestap));
                result.add(c.getString(i_head));
                result.add(c.getString(i_cat));
                result.add(c.getString(i_s3));
                i++;
            }else {
                break;
            }


        }
        return result;
    }

    public ArrayList<String> getFact() {
        String colums[] = new String[]{History__id,History_public_id,History_issimplified,History_timestampcreated,History_isviral,History_othertags,History_linkedToNews,History_timestampcreated};
        Cursor c= ourdatabase.query(DATABASE_TABLE1, colums, History_isFact+"='true'",null, null,  null,History_timestampcreated+" ASC");
        ArrayList<String> result = new ArrayList<String>();
        //int i_id = c.getColumnIndex(History__id);
        int i_id = c.getColumnIndex(History__id);
        int i_pid = c.getColumnIndex(History_public_id);
        int i_othertags = c.getColumnIndex(History_othertags);
        int i_linkedToNews = c.getColumnIndex(History_linkedToNews);
        int i_timestap = c.getColumnIndex(History_timestampcreated);


        int i = 0;

        for(c.moveToLast();!c.isBeforeFirst();c.moveToPrevious()){
            //result.add(c.getString(i_id));
            result.add(c.getString(i_pid));
            result.add(c.getString(i_othertags));
            result.add(c.getString(i_id));
            result.add(c.getString(i_linkedToNews));
            result.add(c.getString(i_timestap));

        }
        return result;
    }
    public ArrayList<String> getAll() {
        // TODO Auto-generated method stub
        String colums[] = new String[]{History__id,History_public_id,History_isS3,History_category,History_isviral,History_headline,History_othertags,History_linkedToNews,History_timestampcreated};
        Cursor c= ourdatabase.query(DATABASE_TABLE1,colums, History_isviral+"='false' AND "+History_isFact+"='false'",null, null,  null,History_timestampcreated+" ASC");
        ArrayList<String> result = new ArrayList<String>();
        int i_id = c.getColumnIndex(History__id);
        int i_pid = c.getColumnIndex(History_public_id);
        int i_othertags = c.getColumnIndex(History_othertags);
        int i_linkedToNews = c.getColumnIndex(History_linkedToNews);
        int i_timestap = c.getColumnIndex(History_timestampcreated);

        int i_head = c.getColumnIndex(History_headline);
        int i_cat = c.getColumnIndex(History_category);
        int i_s3 = c.getColumnIndex(History_isS3);
        int i = 0;

        for(c.moveToLast();!c.isBeforeFirst();c.moveToPrevious()){
            //result.add(c.getString(i_id));
            if (i<200) {
                result.add(c.getString(i_pid));
                result.add(c.getString(i_othertags));
                result.add(c.getString(i_id));
                result.add(c.getString(i_linkedToNews));
                result.add(c.getString(i_timestap));
                result.add(c.getString(i_head));
                result.add(c.getString(i_cat));
                result.add(c.getString(i_s3));
                i++;
            }else {
                break;
            }


        }
        return result;
    }
    public ArrayList<String> getTrends() {
        String colums[] = new String[]{History__id,History_public_id,History_isviral,History_othertags,History_linkedToNews,History_timestampcreated};
        Cursor c= ourdatabase.query(DATABASE_TABLE1,colums, History_isviral+"='true' or "+History_isFact+"='true'",null, null,  null,History_timestampcreated+" ASC");
        ArrayList<String> result = new ArrayList<String>();
        int i_id = c.getColumnIndex(History__id);
        int i_pid = c.getColumnIndex(History_public_id);
        int i_othertags = c.getColumnIndex(History_othertags);
        int i_linkedToNews = c.getColumnIndex(History_linkedToNews);
        int i_timestap = c.getColumnIndex(History_timestampcreated);


        int i = 0;

        for(c.moveToLast();!c.isBeforeFirst();c.moveToPrevious()){
            //result.add(c.getString(i_id));
            result.add(c.getString(i_pid));
            result.add(c.getString(i_othertags));
            result.add(c.getString(i_id));
            result.add(c.getString(i_linkedToNews));
            result.add(c.getString(i_timestap));

        }

        return result;
    }
    public ArrayList<String> getthree() {
        // TODO Auto-generated method stub
        String colums[] = new String[]{History__id,History_public_id,History_isviral,History_othertags,History_linkedToNews};
        Cursor c= ourdatabase.query(DATABASE_TABLE1, colums, History_isviral + "='false'", null, null, null, History_timestampcreated + " ASC");
        ArrayList<String> result = new ArrayList<String>();

        int i_pid = c.getColumnIndex(History_public_id);




        int i = 0;

        for(c.moveToLast();!c.isBeforeFirst();c.moveToPrevious()){
            //result.add(c.getString(i_id));
            if (i<1) {
                        result.add(c.getString(i_pid));
                        i++;
            }else {
                break;
            }




        }

        return result;
    }

    public String getLatetsTime() {
        // TODO Auto-generated method stub
        String colums[] = new String[]{History__id,History_public_id,History_isviral,History_timestampcreated};
        Cursor c= ourdatabase.query(DATABASE_TABLE1, colums, null, null, null, null, History_timestampcreated + " ASC");
        String result = "-1";
        //int i_id = c.getColumnIndex(History__id);
        int i_pid = c.getColumnIndex(History_timestampcreated);

        int i = 0;
        for(c.moveToLast();!c.isBeforeFirst();c.moveToPrevious()){
            //result.add(c.getString(i_id));
            result=c.getString(i_pid);
            break;

        }
        return result;
    }

    public String getPublicID() {
        // TODO Auto-generated method stub
        String colums[] = new String[]{History__id,History_public_id,History_isviral,History_timestampcreated};
        Cursor c= ourdatabase.query(DATABASE_TABLE1,colums, History_isviral+"='false'",null, null,  null,History_timestampcreated+" ASC");
        String result = "-1";
        //int i_id = c.getColumnIndex(History__id);
        int i_pid = c.getColumnIndex(History_public_id);

        int i = 0;
        for(c.moveToLast();!c.isBeforeFirst();c.moveToPrevious()){
            //result.add(c.getString(i_id));
            result=c.getString(i_pid);
            break;

        }
        return result;
    }
    public String getCredits(String public_id) {
        // TODO Auto-generated method stub
        String colums[] = new String[]{History__id,History_public_id,History_creditname,History_timestampcreated};
        Cursor c= ourdatabase.query(DATABASE_TABLE1,colums, History_public_id+"='"+public_id+"'",null, null,  null,History_timestampcreated+" ASC");
        String result = "-1";
        int i_tstmp = c.getColumnIndex(History_creditname);

        int i = 0;
        for(c.moveToLast();!c.isBeforeFirst();c.moveToPrevious()){
            //result.add(c.getString(i_id));
            result=c.getString(i_tstmp);
            Log.e("Result ", result);
            break;

        }
        return result;
    }
    public String getSelectedCategory(String public_id) {
        // TODO Auto-generated method stub
        String colums[] = new String[]{History_category,History_public_id,History_isviral,History_timestampcreated};
        Cursor c= ourdatabase.query(DATABASE_TABLE1,colums, History_public_id+"='"+public_id+"'",null, null,  null,History_timestampcreated+" ASC");
        String result = "-1";

        int i_tstmp = c.getColumnIndex(History_category);

        int i = 0;
        for(c.moveToLast();!c.isBeforeFirst();c.moveToPrevious()){
            //result.add(c.getString(i_id));
            result=c.getString(i_tstmp);
            Log.e("Result ", result);
            break;

        }
        return result;
    }
    public void deletePost(String _id) {
        ourdatabase.delete(DATABASE_TABLE1,History__id+"='"+_id+"'",null);
    }
    public void dropdb(){

        ourcontext.deleteDatabase(DATABASE_NAME);
    }

}
