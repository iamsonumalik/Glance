package com.app.newsonrun;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class RemoveCache extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        final Thread t = new Thread(){
            @Override
            public void run() {
                super.run();
                for (;;){

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("H");
                    simpleDateFormat.setTimeZone(TimeZone.getDefault());
                    String formattedDate = simpleDateFormat.format(new Date());
                    //Log.e("Date",formattedDate);
                    if (formattedDate.equals("3")){
                        MyDirectory myDirectory = new MyDirectory();
                        File directory = myDirectory.getDirectory();
                        if (directory.isDirectory())
                        {

                            String[] children = directory.list();
                            int total = children.length;
                            Log.e("Total", String.valueOf(total));
                            if ((total)>100){

                                    //new File(directory, children[i]).delete();
                                ArrayList<String> saveditems = new ArrayList<String>();
                                try {
                                    SavingCache savingCache = new SavingCache(RemoveCache.this);
                                    savingCache.open();
                                    saveditems = savingCache.getAll();
                                    savingCache.close();
                                }catch (Exception e){
                                    Log.e("Error inCaching", e.toString());
                                }
                                int i =0;
                                Log.e("Array Size", String.valueOf(saveditems.size()));
                                while (saveditems.size()>100){
                                    new File(directory, saveditems.get(i)+".png").delete();
                                    saveditems.remove(i);
                                    Log.e("Deleted", formattedDate);
                                }
                            }


                        }
                        break;
                    }
            }


        }};t.start();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    startService(new Intent(this,RemoveCache.class));
    }
}
