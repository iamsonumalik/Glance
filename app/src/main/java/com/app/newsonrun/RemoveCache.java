package com.app.newsonrun;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.squareup.picasso.Picasso;

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

                            SavingCache savingCache = new SavingCache(RemoveCache.this);
                        try {
                            savingCache.open();

                        }catch (Exception e){
                            Log.e("Error inCaching", e.toString());
                        }

                            int total = savingCache.getTotal();
                            Log.e("Total", String.valueOf(total));
                            if ((total)>99){

                                ArrayList<String> saveditems = new ArrayList<String>();
                                saveditems = savingCache.getAll();
                                int i =0;
                                Log.e("Array Size", String.valueOf(saveditems.size()));
                                while (saveditems.size()>99){
                                    String name = saveditems.get(i);
                                    String imgageUrl;
                                    if (name.contains(".png")){
                                        imgageUrl = "http://d2vwmcbs3lyudp.cloudfront.net/"+name;

                                    }else {
                                        imgageUrl =getBaseContext().getResources().getString(R.string.url) + name + ".png";
                                    }

                                    Picasso.with(getBaseContext()).invalidate(imgageUrl);
                                    savingCache.deleteItem(name);
                                    saveditems.remove(i);
                                    Log.e("Deleted", formattedDate);
                                }

                            }
                        try {
                            savingCache.close();

                        }catch (Exception e){
                            Log.e("Error inCaching", e.toString());
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
