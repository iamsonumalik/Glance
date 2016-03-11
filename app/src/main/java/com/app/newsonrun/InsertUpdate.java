package com.app.newsonrun;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

/**
 * Created by malik on 8/3/16.
 */
public class InsertUpdate extends AsyncTask<String ,String,String>{
    private final String newsPost;
    private static final String MY_PREFS_NAME = "MySetting";
    private final String breakit;
    private final String body;
    private SavingPublicId savingPublicId;
    private Context baseContext;
    private String result;
    private InputStream is;
    private String line;
    private String title;
    private String icon;

    public InsertUpdate(String newsPost, Context baseContext, String s, String title, String body,String icon) {
        this.newsPost = newsPost;
        this.baseContext = baseContext;
        this.breakit = s;
        this.title = title;
        this.body = body;
        this.icon = icon;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        savingPublicId = new SavingPublicId(baseContext);
        try {
            savingPublicId.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected String doInBackground(String... params) {
        try {

            SharedPreferences prefs = baseContext.getSharedPreferences(MY_PREFS_NAME, 0);
            String gettoken = prefs.getString("token", "");
            String strUrl = "http://52.25.155.157:8080/api/v1/news/post/" + newsPost + "?apiKey=" + gettoken;
            strUrl = strUrl.replaceAll(" ", "%20");
            URL url = new URL(strUrl);
            HttpURLConnection urlConnection = null;
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            is = urlConnection.getInputStream();
            Log.e("pass 1", "connection success ");
            BufferedReader reader = new BufferedReader
                    (new InputStreamReader(is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");

            }
            is.close();
            result = sb.toString();
            Log.e("pass 2", "connection success ");


            JSONObject json_data = new JSONObject(result);
            SavingYoutubeLink savingYoutubeLink;
            savingYoutubeLink = new SavingYoutubeLink(baseContext);
            savingYoutubeLink.open();


            //if (array.length()>0) {
                //Log.e("L", String.valueOf(i));

                JSONObject publish = json_data.getJSONObject("publish");
                JSONObject portrait = publish.getJSONObject("portrait");
                JSONObject url_id = portrait.getJSONObject("url");
                JSONObject attributes = json_data.getJSONObject("attributes");
                JSONObject tags = json_data.getJSONObject("tags");
                JSONArray others = tags.getJSONArray("other");
                String othertags = "";
                for (int j = 0; j < others.length(); j++) {
                    String temptag = others.getString(j);
                    if (others.length() == 1) {
                        othertags += temptag;
                    } else if (j == 0) {
                        othertags += temptag;
                    } else if (j == (others.length() - 1)) {
                        othertags += " and " + temptag;
                    } else {
                        othertags += ", " + temptag;

                    }
                }
                //Log.e("Tags",othertags);
                //Temp Variables
                String _id = json_data.getString("_id");
                String p_id = url_id.getString("public_id");
                String timestamp = json_data.getString("timestampCreated");
                String category = tags.getString("category");
                int editorRating = attributes.getInt("editorRating");
                String state = attributes.getString("state");
                String breakingNews = String.valueOf(attributes.getBoolean("breakingNews"));
                String enabled = String.valueOf(attributes.getBoolean("enabled"));
                String linkedToNews = "n";
                String issimplified = "false";
                String isFact = "false";
                String isviral = "false";
                try{
                    isFact = String.valueOf(tags.getBoolean("isFact"));
                }catch (Exception e){

                }
                try {
                    issimplified = String.valueOf(tags.getBoolean("isSimplified"));
                    isviral = String.valueOf(tags.getBoolean("isViral"));
                } catch (Exception e) {

                }
                try {
                    linkedToNews = json_data.getString("linkedToNews");
                } catch (Exception e) {

                }
                //Saving YouTubeLink
                try {
                    if (!(json_data.getString("youtubeVideoId").equals("") || json_data.getString("youtubeVideoId").equals(null))) {
                        String youtubeVideoId = json_data.getString("youtubeVideoId");
                        savingYoutubeLink.createEntry(1, p_id, youtubeVideoId);
                    }
                } catch (Exception e) {

                }

                //Saving Recent Posts
                savingPublicId.createEntry(0,
                        _id,
                        p_id,
                        timestamp,
                        category,
                        issimplified,
                        isviral,
                        editorRating,
                        state,
                        breakingNews,
                        enabled,
                        othertags,
                        linkedToNews,
                        isFact
                );
                Log.e("Updated",newsPost);
            savingYoutubeLink.close();
            if (breakit.equals("breaking.wav")){
                final RemoteViews remoteViews = new RemoteViews(baseContext.getPackageName(),
                        R.layout.customnotification);
                Intent intent = new Intent(baseContext, AllCategory.class);

                String pid="";

                pid = savingPublicId.getp_id(newsPost);

                intent.putExtra("moveto",pid);
                intent.putExtra("isnews",true);
                PendingIntent pIntent = PendingIntent.getActivity(baseContext, 0, intent,
                        PendingIntent.FLAG_UPDATE_CURRENT);

                NotificationCompat.Builder builder = new NotificationCompat.Builder(baseContext)
                        // Set Icon
                        .setSmallIcon(R.drawable.splash)
                                // Set Ticker Message
                        .setTicker(body)
                                // Dismiss Notification
                        .setAutoCancel(true)
                                // Set PendingIntent into Notification
                        .setContentIntent(pIntent)
                                // Set RemoteViews into Notification
                        .setContent(remoteViews);

                builder.setPriority(Notification.PRIORITY_HIGH);
                if (Build.VERSION.SDK_INT >= 21) builder.setVibrate(new long[0]);
                remoteViews.setImageViewResource(R.id.imagenotileft, R.drawable.breaking);

                try {
                    Bitmap remote_picture = BitmapFactory.decodeStream(
                            (InputStream) new URL(icon).getContent());
                    remoteViews.setImageViewBitmap(R.id.imagenotiright, remote_picture);

                } catch (IOException e) {
                    e.printStackTrace();
                }


                remoteViews.setTextViewText(R.id.title, "");
                remoteViews.setTextViewText(R.id.text, body);
                remoteViews.setTextColor(R.id.title, Color.BLACK);
                remoteViews.setTextColor(R.id.text, Color.rgb(209,211,212));
                // Create Notification Manager
                NotificationManager notificationmanager = (NotificationManager) baseContext.getSystemService(baseContext.NOTIFICATION_SERVICE);
                Random r = new Random();
                Notification notification = builder.build();
                int x = r.nextInt(1000000);
                notification.bigContentView = remoteViews;
                notificationmanager.notify(x,notification );


            }
        } catch (Exception e) {
            // TODO: handle exception
            Log.e("log_tag", "Error parsing data " + e.toString());

        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        try {
            Uri no = Uri.parse("android.resource://" + baseContext.getPackageName() + "/" + R.raw.notification_tone);
            Ringtone rp = RingtoneManager.getRingtone(baseContext.getApplicationContext(), no);
            rp.play();
            Vibrator v = (Vibrator) baseContext.getSystemService(Context.VIBRATOR_SERVICE);
            // Vibrate for 500 milliseconds
            v.vibrate(500);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            savingPublicId.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
