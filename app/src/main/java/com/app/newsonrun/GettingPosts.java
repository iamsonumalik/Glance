package com.app.newsonrun;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by malik on 7/3/16.
 */
public   class GettingPosts extends AsyncTask<String, String, Void> {
    private final Activity activity;
    private final TextView totalviral;
    private final TextView totalnews;
    private final Switch switchCompat;
    private String line;
    private static final String MY_PREFS_NAME = "MySetting";
    private InputStream is;
    private String result;
    private int trends=0;
    private int news=0;
    private String gettoken;
    private String fetchnew;
    private SavingPublicId savingPublicId;
    private ArrayList<String> public_ids;
    private boolean isnews=true;

    public GettingPosts(final Activity loadingActivity, String gettoken, TextView totalviral,
                        TextView totalnews, Switch switchCompat,
                        final ImageView newsimageview, final ImageView viralimageview
                        ) {
        this.activity = loadingActivity;
        this.gettoken = gettoken;
        this.totalviral = totalviral;
        this.totalnews = totalnews;
        this.switchCompat = switchCompat;
        if (totalviral!=null) {
            switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        newsimageview.setImageDrawable(loadingActivity.getResources().getDrawable(R.drawable.newsled));
                        viralimageview.setImageDrawable(loadingActivity.getResources().getDrawable(R.drawable.offled));
                        isnews = true;
                    } else {
                        newsimageview.setImageDrawable(loadingActivity.getResources().getDrawable(R.drawable.offled));
                        viralimageview.setImageDrawable(loadingActivity.getResources().getDrawable(R.drawable.otherled));
                        isnews = false;
                    }
                }
            });
        }
    }

    protected void onPreExecute() {


        public_ids = new ArrayList<String>();
        savingPublicId = new SavingPublicId(activity);
        try {
            savingPublicId.open();
        } catch (Exception e) {
            e.printStackTrace();
        }
        SharedPreferences prefs = activity.getSharedPreferences(MY_PREFS_NAME, 0);
        final boolean isfirst = prefs.getBoolean("isfirst", false);
        if (!isfirst) {
            fetchnew = "-1";
        } else {

            String timest = savingPublicId.getLatetsTime();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            String dateget = timest;
            Date date = null;
            try {
                date = sdf.parse(dateget);
                long st = date.getTime();
                fetchnew = String.valueOf(st);
            } catch (ParseException e) {
                fetchnew=timest;
                e.printStackTrace();
            }



        }

    }

    @Override
    protected Void doInBackground(String... params) {


        try {

            String strUrl = "http://52.25.155.157:8080/api/v1/news/feed/" + fetchnew + "?apiKey=" + gettoken;
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
            JSONArray array = json_data.getJSONArray("data");
            SavingYoutubeLink savingYoutubeLink;
            savingYoutubeLink = new SavingYoutubeLink(activity);
            savingYoutubeLink.open();


            //if (array.length()>0) {
            for (int i = 0; i < array.length(); i++) {
                //Log.e("L", String.valueOf(i));
                JSONObject data = array.getJSONObject(i);
                JSONObject publish = data.getJSONObject("publish");
                JSONObject portrait = publish.getJSONObject("portrait");
                JSONObject url_id = portrait.getJSONObject("url");
                JSONObject attributes = data.getJSONObject("attributes");
                JSONObject tags = data.getJSONObject("tags");
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
                String _id = data.getString("_id");
                String p_id = url_id.getString("public_id");
                String timestamp = data.getString("timestampCreated");
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
                    linkedToNews = data.getString("linkedToNews");
                } catch (Exception e) {

                }
                //Saving YouTubeLink
                try {
                    if (!(data.getString("youtubeVideoId").equals("") || data.getString("youtubeVideoId").equals(null))) {
                        String youtubeVideoId = data.getString("youtubeVideoId");
                        savingYoutubeLink.createEntry(1, p_id, youtubeVideoId);
                    }
                } catch (Exception e) {

                }

                if (i<3) {
                    public_ids.add(i, p_id);
                }
                //Saving Recent Posts
                savingPublicId.createEntry(i,
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
                Log.e("Save","Post");
                if (issimplified.equals("true")||isviral.equals("true")){
                    trends++;
                }else {
                    news++;
                }
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (totalviral != null) {
                            totalviral.setText(String.valueOf(trends));
                            totalnews.setText(String.valueOf(news));
                        }
                    }
                });

            }
            savingYoutubeLink.close();
        } catch (Exception e) {
            // TODO: handle exception
            Log.e("log_tag", "Error parsing data " + e.toString());

        }
        return null;

    }

    protected void onPostExecute(Void v) {
         //public_ids = savingPublicId.getthree();
        for (int X=0;X<public_ids.size();X++){
            saveImage(public_ids.get(X));
        }
        try {
            savingPublicId.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Intent i = new Intent(activity, AllCategory.class);
        i.putExtra("moveto","");
        i.putExtra("isnews",isnews);
        activity.startActivity(i);
        activity.finish();

    }

    private void saveImage(String name) {

        File directory;
        File file = null;
        MyDirectory myDirectory = new MyDirectory();
        directory = myDirectory.getDirectory();
        file = new File(directory, name + ".jpg");
        if (!file.exists()) {

            String imgageUrl = "http://res.cloudinary.com/innox-technologies/image/upload/c_scale,h_764,q_85/" + name + ".jpg";

            try {
                InputStream in = new java.net.URL(imgageUrl).openStream();
                Bitmap mIcon11 = BitmapFactory.decodeStream(in);
                FileOutputStream ourstream = new FileOutputStream(file);
                mIcon11.compress(Bitmap.CompressFormat.JPEG, 85, ourstream);
                ourstream.flush();
                ourstream.close();
                Log.e("Saved","Loading");
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                Toast.makeText(activity, e.toString(), Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                Toast.makeText(activity, e.toString(), Toast.LENGTH_LONG).show();
            } catch (Exception e) {

            }
        }
    }

    private class saveImageClass extends  AsyncTask<String ,String,String>{
        @Override
        protected String doInBackground(String... params) {

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);


        }
    }

}
