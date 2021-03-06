package com.app.newsonrun;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends Activity {

    private String deviceId;
    private String gettoken;
    private File directory;
    private SharedPreferences prefs;
    private static final String MY_PREFS_NAME = "MySetting";
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tv = (TextView) findViewById(R.id.textView);
        Typeface face = Typeface.createFromAsset(getAssets(), "lodingfont.ttf");
        tv.setTypeface(face);
        prefs = getSharedPreferences(MY_PREFS_NAME, 0);
        editor = getSharedPreferences(MY_PREFS_NAME, 0).edit();
        Controller.getInstance().trackEvent("OpenApp", "Splash Screen", "user");
        if (Environment.getExternalStorageState() == null) {
            //create new file directory object
            directory = new File(Environment.getDataDirectory()
                    + "/Android/data/com.app.newsonrun/cache");
            File file = new File(directory, ".nomedia");

            /*
             * this checks to see if there are any previous test photo files
             * if there are any photos, they are deleted for the sake of
             * memory
             */
            // if no directory exists, create new directory
            if (!directory.exists()) {
                try {
                    directory.mkdirs();
                    FileOutputStream out = new FileOutputStream(file);
                    out.flush();
                    out.close();
                }catch (Exception e){
                    Log.e("Make Dir main",e.toString());
                }
                Log.e("Path",file.getAbsolutePath());
            }

            // if phone DOES have sd card
        } else if (Environment.getExternalStorageState() != null) {
            // search for directory on SD card
            directory = new File(Environment.getExternalStorageDirectory()
                    + "/Android/data/com.app.newsonrun/cache");
            File file = new File(directory, ".nomedia");

            // if no directory exists, create new directory to store test
            // results
            if (!directory.exists()) {
                try {
                    directory.mkdirs();
                    FileOutputStream out = new FileOutputStream(file);
                    out.flush();
                    out.close();
                }catch (Exception e){
                    Log.e("Make Dir Main",e.toString());
                }
                Log.e("Path", file.getAbsolutePath());
            }
        }// end of SD card checking

        deviceId = Settings.Secure.getString(this.getContentResolver(),
                Settings.Secure.ANDROID_ID);

        startService(new Intent(this, RemoveCache.class));

        new AuthIt().execute();
        Intent i = getIntent();
        boolean fromnoti = i.getBooleanExtra("fromnoti", false);
        if (fromnoti){
            Controller.getInstance().trackEvent("DigestNews", "Clicked", "user");

        }
    }

    protected void sendJson(final String email, final String pwd) {
        try {
            String url = getResources().getString(R.string.apiurl)+"/api/v1/user/authorize";
            URL object = new URL(url);
            HttpURLConnection con = (HttpURLConnection) object.openConnection();
            con.setDoOutput(true);
            con.setDoInput(true);
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setRequestMethod("POST");

            JSONObject cred = new JSONObject();

            cred.put("deviceId", deviceId);

            OutputStreamWriter wr = new OutputStreamWriter(con.getOutputStream());
            wr.write(cred.toString());
            wr.flush();

//display what returns the POST request

            StringBuilder sb = new StringBuilder();
            int HttpResult = con.getResponseCode();
            if (HttpResult == HttpURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream(), "utf-8"));
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }

                br.close();

                System.out.println("bta" + sb.toString());
                JSONObject json_data = new JSONObject(String.valueOf(sb));
                gettoken=(json_data.getString("accessToken"));
                editor.putString("token", gettoken);
                editor.commit();
                editor.apply();
                new Storinggcm(MainActivity.this,gettoken);

            } else {
                System.out.println("else"+con.getResponseMessage());
            }

        }catch (Exception e){

            Log.e("Err",e.toString());
        }
        startActivity(new Intent(getBaseContext(), LoadingActivity.class));
        finish();
    }

    private class AuthIt extends AsyncTask<String,String,String>{
        @Override
        protected String doInBackground(String... params) {
            sendJson("", "");

            return null;
        }
    }
}
