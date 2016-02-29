package com.app.newsonrun;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by malik on 26/2/16.
 */
public class CheckUpdate  extends AsyncTask<String,Boolean,Boolean>{
    private final String gettoken;
    private final Activity activity;
    private String versionName = "";
    private InputStream is;
    private String result;
    private String line;
    private boolean forceUpdate;
    private String description;

    public CheckUpdate(String gettoken, Activity allCategory) {
        this.gettoken = gettoken;
        this.activity = allCategory;
        PackageManager manager = activity.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(activity.getPackageName(), 0);
            versionName = info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("Update Checker 3", "Error parsing data " + e.toString());
        }

    }

    @Override
    protected Boolean doInBackground(String... params) {
        try {

            String strUrl = "http://52.25.155.157:8080/api/v1/version/ANDROID/latest?apiKey="+gettoken;
            strUrl = strUrl.replaceAll(" ", "%20");
            URL url = new URL(strUrl);
            HttpURLConnection urlConnection = null;
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            is = urlConnection.getInputStream();
            Log.e("pass 1", "connection success ");

            BufferedReader reader = new BufferedReader
                    (new InputStreamReader(is, "UTF-8"), 8);
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");

            }
            is.close();
            result = sb.toString();
            Log.e("pass 2", "connection success ");


            JSONObject json_data = new JSONObject(result);
            description = json_data.getString("description");
            Log.e("Description" ,description);
            String version = json_data.getString("version");
            Log.e("Version" ,version);
            forceUpdate = json_data.getBoolean("forceUpdate");
            if (!versionName.equals(version)){
                return true;
            }

        } catch (Exception e) {
            // TODO: handle exception
            Log.e("Update Checker", "Error parsing data " + e.toString());

        }

        return false;
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        if (aBoolean){
            AlertDialog.Builder dialog = new AlertDialog.Builder(activity);
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            Typeface head = Typeface.createFromAsset(activity.getAssets(), "lodingfont.ttf");
            View someLayout = inflater.inflate(R.layout.updatedialog, null);
            TextView des = (TextView) someLayout.findViewById(R.id.description);
            TextView later = (TextView) someLayout.findViewById(R.id.later);
            TextView dailogtitle = (TextView) someLayout.findViewById(R.id.dialogtitle);
            TextView update = (TextView) someLayout.findViewById(R.id.update);
            des.setText(description);

            dailogtitle.setTypeface(head);
            update.setTypeface(head);
            later.setTypeface(head);
            des.setTypeface(head);
            dialog.setView(someLayout);
            dialog.setCancelable(false);
            if (forceUpdate){
                later.setVisibility(View.GONE);
            }
            final AlertDialog alertDialog = dialog.create();
            update.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String appPackageName = activity.getPackageName(); // getPackageName() from Context or Activity object
                    try {
                        activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                    } catch (android.content.ActivityNotFoundException anfe) {
                        activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                    }
                }
            });
            later.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog.dismiss();

                }
            });


            alertDialog.show();


        }
    }
}
