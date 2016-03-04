package com.app.newsonrun;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

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
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.TimeZone;

public class LoadingActivity extends Activity {

    private static final String MY_PREFS_NAME = "MySetting";
    private RelativeLayout backgroundlayout;
    private ImageView backgroundimageview;


    private TextView datetv;
    private ProgressBar firstBar;

    int trends=0,news=0;
    private String gettoken;
    private GridView gridview;
    ArrayList<Bitmap> image_clips;
    private TextView descrpt;
    private String fetchnew;
    private SavingPublicId savingPublicId;
    private String lat = "0";
    private String lon = "0";
    private String descpt;
    private String temprture;
    private LocationManager locationManager;
    private String bestProvider;
    private Location currentlocation;
    private TextView howdy;
    private TextView totalviral;
    private TextView ampm;
    private TextView time;
    private TextView itstv;
    private TextView degree;
    private TextView numdegree;
    private TextView city;
    private TextView day;
    private TextView newstv;
    private TextView totalnews;
    private TextView viraltv;
    private ImageView viralimageview;
    private ImageView weathericon;
    private String iconname;
    private ArrayList<String> public_ids;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        locationmeter();
        Switch switchCompat = (Switch) findViewById(R.id
                .mySwitch);

        savingPublicId = new SavingPublicId(LoadingActivity.this);
        try {
            savingPublicId.open();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Random random = new Random();
        image_clips = new ArrayList<Bitmap>();

        backgroundlayout = (RelativeLayout) findViewById(R.id.background);
        //backgroundimageview = (ImageView) findViewById(R.id.backgroundimageView);
        descrpt = (TextView) findViewById(R.id.descrpt);
        datetv = (TextView) findViewById(R.id.datetv);
        itstv = (TextView) findViewById(R.id.Its);
        time = (TextView) findViewById(R.id.time);
        ampm = (TextView) findViewById(R.id.ampm);
        degree = (TextView) findViewById(R.id.degree);
        numdegree = (TextView) findViewById(R.id.numdegree);
        day = (TextView) findViewById(R.id.day);
        city = (TextView) findViewById(R.id.city);
        newstv = (TextView) findViewById(R.id.newstv);
        totalnews = (TextView) findViewById(R.id.getnews);
        viraltv = (TextView) findViewById(R.id.viral);
        totalviral = (TextView) findViewById(R.id.getviral);
        viralimageview = (ImageView) findViewById(R.id.viralimageview);
        weathericon = (ImageView) findViewById(R.id.weathericon);
        final ImageView newsimageview = (ImageView) findViewById(R.id.newsimageview);
        firstBar = (ProgressBar) findViewById(R.id.firstBar);
        gridview = (GridView) findViewById(R.id.loadinggridview);

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, 0);
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
            } catch (ParseException e) {
                e.printStackTrace();
            }

            long st = date.getTime();
            fetchnew = String.valueOf(st);

        }

        Log.e("Get TIme", String.valueOf(fetchnew));

        gridview.setAdapter(new ImageAdapter(LoadingActivity.this, this, getArrayBitmap()));
        Thread t2 = new Thread() {
            @Override
            public void run() {
                super.run();
                for (; ; ) {
                    try {
                        Thread.sleep(3000);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Random random = new
                                        Random();
                                int one = random.nextInt(54);
                                int two = random.nextInt(54);
                                int three = random.nextInt(54);
                                int four = random.nextInt(54);
                                int five = random.nextInt(54);
                                int six = random.nextInt(54);
                                int seven = random.nextInt(54);
                                int eight = random.nextInt(54);
                                int nine = random.nextInt(54);
                                int ten = random.nextInt(54);
                                int eleven = random.nextInt(54);
                                int twelve = random.nextInt(54);
                                int thirteen = random.nextInt(54);
                                int fourteen = random.nextInt(54);
                                int fifteen = random.nextInt(54);
                                int sixteen = random.nextInt(54);

                                ObjectAnimator anim = (ObjectAnimator) AnimatorInflater.loadAnimator(getBaseContext(), R.animator.flip);
                                anim.setTarget(((ImageView) gridview.getChildAt(one)));
                                //anim.setDuration(3000);
                                anim.setDuration(500);
                                anim.start();

                                ObjectAnimator anim2 = (ObjectAnimator) AnimatorInflater.loadAnimator(getBaseContext(), R.animator.flip);
                                anim2.setTarget(((ImageView) gridview.getChildAt(two)));
                                //anim2.setDuration(3000);
                                anim2.setDuration(500);
                                anim2.start();

                                ObjectAnimator anim3 = (ObjectAnimator) AnimatorInflater.loadAnimator(getBaseContext(), R.animator.flip);
                                anim3.setTarget(((ImageView) gridview.getChildAt(three)));
                                //anim3.setDuration(3000);
                                anim3.setDuration(500);
                                anim3.start();

                                ObjectAnimator anim4 = (ObjectAnimator) AnimatorInflater.loadAnimator(getBaseContext(), R.animator.flip);
                                anim4.setTarget(((ImageView) gridview.getChildAt(four)));
                                //anim4.setDuration(3000);
                                anim4.setDuration(500);
                                anim4.start();

                                ObjectAnimator anim5 = (ObjectAnimator) AnimatorInflater.loadAnimator(getBaseContext(), R.animator.flip);
                                anim5.setTarget(((ImageView) gridview.getChildAt(five)));
                                //anim5.setDuration(3000);
                                anim5.setDuration(500);
                                anim5.start();

                                ObjectAnimator anim6 = (ObjectAnimator) AnimatorInflater.loadAnimator(getBaseContext(), R.animator.flip);
                                anim6.setTarget(((ImageView) gridview.getChildAt(six)));
                                //anim6.setDuration(3000);
                                anim6.setDuration(500);
                                anim6.start();

                                ObjectAnimator anim7 = (ObjectAnimator) AnimatorInflater.loadAnimator(getBaseContext(), R.animator.flip);
                                anim7.setTarget(((ImageView) gridview.getChildAt(seven)));
                                anim7.setDuration(3000);
                                anim7.setDuration(500);
                                anim7.start();

                                ObjectAnimator anim8 = (ObjectAnimator) AnimatorInflater.loadAnimator(getBaseContext(), R.animator.flip);
                                anim8.setTarget(((ImageView) gridview.getChildAt(eight)));
                                //anim8.setDuration(3000);
                                anim8.setDuration(500);
                                anim8.start();

                                ObjectAnimator anim9 = (ObjectAnimator) AnimatorInflater.loadAnimator(getBaseContext(), R.animator.flip);
                                anim9.setTarget(((ImageView) gridview.getChildAt(nine)));
                                //anim9.setDuration(3000);
                                anim9.setDuration(500);
                                anim9.start();

                                ObjectAnimator anim10 = (ObjectAnimator) AnimatorInflater.loadAnimator(getBaseContext(), R.animator.flip);
                                anim10.setTarget(((ImageView) gridview.getChildAt(ten)));
                                //anim10.setDuration(3000);
                                anim10.setDuration(500);
                                anim10.start();

                                ObjectAnimator anim11 = (ObjectAnimator) AnimatorInflater.loadAnimator(getBaseContext(), R.animator.flip);
                                anim11.setTarget(((ImageView) gridview.getChildAt(eleven)));
                                //anim11.setDuration(3000);
                                anim11.setDuration(500);
                                anim11.start();

                                ObjectAnimator anim12 = (ObjectAnimator) AnimatorInflater.loadAnimator(getBaseContext(), R.animator.flip);
                                anim12.setTarget(((ImageView) gridview.getChildAt(twelve)));
                                //anim12.setDuration(3000);
                                anim12.setDuration(500);
                                anim12.start();

                                ObjectAnimator anim13 = (ObjectAnimator) AnimatorInflater.loadAnimator(getBaseContext(), R.animator.flip);
                                anim13.setTarget(((ImageView) gridview.getChildAt(thirteen)));
                                //anim13.setDuration(3000);
                                anim13.setDuration(500);
                                anim13.start();

                                ObjectAnimator anim14 = (ObjectAnimator) AnimatorInflater.loadAnimator(getBaseContext(), R.animator.flip);
                                anim14.setTarget(((ImageView) gridview.getChildAt(fourteen)));
                                //anim14.setDuration(3000);
                                anim14.setDuration(500);
                                anim14.start();

                                ObjectAnimator anim15 = (ObjectAnimator) AnimatorInflater.loadAnimator(getBaseContext(), R.animator.flip);
                                anim15.setTarget(((ImageView) gridview.getChildAt(fifteen)));
                                //anim15.setDuration(3000);
                                anim15.setDuration(500);
                                anim15.start();

                                ObjectAnimator anim16 = (ObjectAnimator) AnimatorInflater.loadAnimator(getBaseContext(), R.animator.flip);
                                anim16.setTarget(((ImageView) gridview.getChildAt(sixteen)));
                                //anim16.setDuration(3000);
                                anim16.setDuration(500);
                                anim16.start();

                                Bitmap temp = ((BitmapDrawable) ((ImageView) gridview.getChildAt(one)).getDrawable()).getBitmap();
                                ((ImageView) gridview.getChildAt(one))
                                        .setImageBitmap(((BitmapDrawable) ((ImageView) gridview.getChildAt(two)).getDrawable()).getBitmap());
                                ((ImageView) gridview.getChildAt(two)).setImageBitmap(temp);

                                temp = ((BitmapDrawable) ((ImageView) gridview.getChildAt(three)).getDrawable()).getBitmap();
                                ((ImageView) gridview.getChildAt(three))
                                        .setImageBitmap(((BitmapDrawable) ((ImageView) gridview.getChildAt(four)).getDrawable()).getBitmap());
                                ((ImageView) gridview.getChildAt(four)).setImageBitmap(temp);

                                temp = ((BitmapDrawable) ((ImageView) gridview.getChildAt(five)).getDrawable()).getBitmap();
                                ((ImageView) gridview.getChildAt(five))
                                        .setImageBitmap(((BitmapDrawable) ((ImageView) gridview.getChildAt(six)).getDrawable()).getBitmap());
                                ((ImageView) gridview.getChildAt(six)).setImageBitmap(temp);

                                temp = ((BitmapDrawable) ((ImageView) gridview.getChildAt(seven)).getDrawable()).getBitmap();
                                ((ImageView) gridview.getChildAt(seven))
                                        .setImageBitmap(((BitmapDrawable) ((ImageView) gridview.getChildAt(eight)).getDrawable()).getBitmap());
                                ((ImageView) gridview.getChildAt(eight)).setImageBitmap(temp);

                                temp = ((BitmapDrawable) ((ImageView) gridview.getChildAt(nine)).getDrawable()).getBitmap();
                                ((ImageView) gridview.getChildAt(nine))
                                        .setImageBitmap(((BitmapDrawable) ((ImageView) gridview.getChildAt(ten)).getDrawable()).getBitmap());
                                ((ImageView) gridview.getChildAt(ten)).setImageBitmap(temp);

                                temp = ((BitmapDrawable) ((ImageView) gridview.getChildAt(eleven)).getDrawable()).getBitmap();
                                ((ImageView) gridview.getChildAt(eleven))
                                        .setImageBitmap(((BitmapDrawable) ((ImageView) gridview.getChildAt(twelve)).getDrawable()).getBitmap());
                                ((ImageView) gridview.getChildAt(twelve)).setImageBitmap(temp);

                                temp = ((BitmapDrawable) ((ImageView) gridview.getChildAt(thirteen)).getDrawable()).getBitmap();
                                ((ImageView) gridview.getChildAt(thirteen))
                                        .setImageBitmap(((BitmapDrawable) ((ImageView) gridview.getChildAt(fourteen)).getDrawable()).getBitmap());
                                ((ImageView) gridview.getChildAt(fourteen)).setImageBitmap(temp);

                                temp = ((BitmapDrawable) ((ImageView) gridview.getChildAt(fifteen)).getDrawable()).getBitmap();
                                ((ImageView) gridview.getChildAt(fifteen))
                                        .setImageBitmap(((BitmapDrawable) ((ImageView) gridview.getChildAt(sixteen)).getDrawable()).getBitmap());
                                ((ImageView) gridview.getChildAt(sixteen)).setImageBitmap(temp);


                                /*((ImageView) gridview.getChildAt(one)).startAnimation(anim);
                                ((ImageView) gridview.getChildAt(two)).startAnimation(anim);
                                ((ImageView) gridview.getChildAt(three)).startAnimation(anim);
                                ((ImageView) gridview.getChildAt(four)).startAnimation(anim);
                                ((ImageView) gridview.getChildAt(five)).startAnimation(anim);
                                ((ImageView) gridview.getChildAt(six)).startAnimation(anim);
                                ((ImageView) gridview.getChildAt(seven)).startAnimation(anim);
                                ((ImageView) gridview.getChildAt(eight)).startAnimation(anim);*/


                            }
                        });

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t2.start();

        switchCompat.setSwitchPadding(40);
        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    newsimageview.setImageDrawable(getResources().getDrawable(R.drawable.newsled));
                    viralimageview.setImageDrawable(getResources().getDrawable(R.drawable.offled));
                } else {
                    newsimageview.setImageDrawable(getResources().getDrawable(R.drawable.offled));
                    viralimageview.setImageDrawable(getResources().getDrawable(R.drawable.otherled));
                }
            }
        });
        Typeface face = Typeface.createFromAsset(getAssets(), "lodingfont.ttf");
        itstv.setTypeface(face);
        time.setTypeface(face);
        ampm.setTypeface(face);
        numdegree.setTypeface(face);
        degree.setTypeface(face);
        datetv.setTypeface(face);
        descrpt.setTypeface(face);
        day.setTypeface(face);
        city.setTypeface(face);
        newstv.setTypeface(face);
        totalnews.setTypeface(face);
        viraltv.setTypeface(face);
        totalviral.setTypeface(face);

        SimpleDateFormat dateFormatGmt = new SimpleDateFormat("dd MMMM");
        dateFormatGmt.setTimeZone(TimeZone.getDefault());
        String dateget = dateFormatGmt.format(new Date());
        datetv.setText(dateget);
        SimpleDateFormat dayFormatGmt = new SimpleDateFormat("EEEE");
        dayFormatGmt.setTimeZone(TimeZone.getDefault());
        String dayget = dayFormatGmt.format(new Date());
        day.setText(dayget);
        final int[] i = {0};
        Drawable draw = getResources().getDrawable(R.drawable.custom_progressbar);
// set the drawable as progress drawable
        firstBar.setProgressDrawable(draw);

        gettoken = prefs.getString("token", "");


        Runnable myRunnableThread = new CountDownRunner();
        Thread myThread = new Thread(myRunnableThread);
        myThread.start();
        address();
        new WheatherReport().execute();
        new task().execute();
        Thread t = new Thread() {
            @Override
            public void run() {
                super.run();
                for (i[0] = 0; i[0] < 150; ) {
                    if (i[0] == 0 || i[0] == 10) {
                        //make the progress bar visible
                        firstBar.setVisibility(View.VISIBLE);
                        firstBar.setMax(150);
                    } else if (i[0] < firstBar.getMax()) {
                        //Set first progress bar value
                        firstBar.setProgress(i[0]);
                        //Set the second progress bar value
                        firstBar.setSecondaryProgress(i[0] + 10);

                    } else {
                        firstBar.setProgress(0);
                        firstBar.setSecondaryProgress(0);
                        i[0] = 0;
                        firstBar.setVisibility(View.GONE);
                    }
                    i[0]++;

                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }


            }
        };
        t.start();

    }

    public ArrayList<Bitmap> getArrayBitmap() {

        long seed = System.nanoTime();


        image_clips.add(BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a1));
        image_clips.add(BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a2));
        image_clips.add(BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a3));

        image_clips.add(BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a4));

        image_clips.add(BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a5));
        image_clips.add(BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a6));
        image_clips.add(BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a7));

        image_clips.add(BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a8));

        image_clips.add(BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a9));
        image_clips.add(BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a10));
        image_clips.add(BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a11));
        image_clips.add(BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a12));
        image_clips.add(BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a13));
        image_clips.add(BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a14));

        image_clips.add(BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a15));

        image_clips.add(BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a16));

        image_clips.add(BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a17));

        image_clips.add(BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a18));

        image_clips.add(BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a19));

        image_clips.add(BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a20));

        image_clips.add(BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a21));

        image_clips.add(BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a22));

        image_clips.add(BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a23));

        image_clips.add(BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a24));

        image_clips.add(BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a25));

        image_clips.add(BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a26));

        image_clips.add(BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a27));
        image_clips.add(BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a28));

        image_clips.add(BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a29));

        image_clips.add(BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a30));

        image_clips.add(BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a31));

        image_clips.add(BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a32));

        image_clips.add(BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a33));

        image_clips.add(BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a34));

        image_clips.add(BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a35));

        image_clips.add(BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a36));

        image_clips.add(BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a37));

        image_clips.add(BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a38));

        image_clips.add(BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a39));

        image_clips.add(BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a40));

        image_clips.add(BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a41));

        image_clips.add(BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a42));

        image_clips.add(BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a43));

        image_clips.add(BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a44));

        image_clips.add(BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a45));

        image_clips.add(BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a46));

        image_clips.add(BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a47));

        image_clips.add(BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a48));

        image_clips.add(BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a49));

        image_clips.add(BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a50));

        image_clips.add(BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a51));
        image_clips.add(BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a52));

        image_clips.add(BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a53));

        image_clips.add(BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a54));

        Collections.shuffle(image_clips, new Random(seed));
        return image_clips;
    }

    class task extends AsyncTask<String, String, Void> {
        private String line;
        private InputStream is;
        private String result;

        protected void onPreExecute() {


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
                savingYoutubeLink = new SavingYoutubeLink(LoadingActivity.this);
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
                    String isviral = "false";
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
                            linkedToNews
                    );

                    if (issimplified.equals("true")||isviral.equals("true")){
                        trends++;
                    }else {
                        news++;
                    }
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            totalviral.setText(String.valueOf(trends));
                            totalnews.setText(String.valueOf(news));
                        }
                    });


                    //if (i < 1) {
                    //saveImage(p_id);
                    //}
                    //Log.e("end", String.valueOf(i));

                }
                //}else {
                // String pi = savingPublicId.getPublicID();
                //saveImage(pi);

                //}
                savingYoutubeLink.close();
            } catch (Exception e) {
                // TODO: handle exception
                Log.e("log_tag", "Error parsing data " + e.toString());

            }
            return null;

        }

        protected void onPostExecute(Void v) {
            //String pi = savingPublicId.getPublicID();
            //saveImage(pi);
            public_ids = savingPublicId.getthree();
            new saveImageClass().execute();
            try {
                savingPublicId.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    class WheatherReport extends AsyncTask<String, String, String> {

        private InputStream is;

        protected String doInBackground(String... params) {


            try {

                String appid = getResources().getString(R.id.appid);
                String strUrl = "http://api.openweathermap.org/data/2.5/weather?lat=" + lat + "&lon=" + lon + "&appid=" + appid;
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

                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");

                }
                is.close();
                String result = sb.toString();
                Log.e("pass 2", "connection success ");


                JSONObject json_data = new JSONObject(result);
                if (json_data.getString("cod").equals("200")) {
                    JSONArray weather = json_data.getJSONArray("weather");
                    JSONObject data = weather.getJSONObject(0);
                    JSONObject main = json_data.getJSONObject("main");
                    descpt = data.getString("description");
                    iconname = data.getString("icon");
                    temprture = main.getString("temp");
                } else {
                    descpt = "error";
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
            Picasso.with(getBaseContext())
                    .load("http://openweathermap.org/img/w/"+iconname+".png")
                    .into(weathericon);
            String sett = descpt.substring(0, 1).toUpperCase() + descpt.substring(1, descpt.length());
            descrpt.setText(sett);
            numdegree.setText(String.valueOf(
                            ((int) Double.parseDouble(temprture)) - 273)
            );
        }
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
                Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                Toast.makeText(getBaseContext(), e.toString(), Toast.LENGTH_LONG).show();
            } catch (Exception e) {

            }
        }
    }

    public void locationmeter() {

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        criteria.setPowerRequirement(Criteria.POWER_LOW);
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setSpeedRequired(true);

        bestProvider = locationManager.getBestProvider(criteria, true);
        currentlocation = locationManager.getLastKnownLocation(bestProvider);
        if (currentlocation != null) {

            try {
                lat = String.valueOf(currentlocation.getLatitude());
                lon = String.valueOf(currentlocation.getLongitude());

            } catch (Exception e) {
                criteria.setAccuracy(Criteria.ACCURACY_COARSE);
                bestProvider = locationManager.getBestProvider(criteria, true);
                currentlocation = locationManager.getLastKnownLocation(bestProvider);
                lat = String.valueOf(currentlocation.getLatitude());
                lon = String.valueOf(currentlocation.getLongitude());
            }
        }
    }


    public void doWork() {
        runOnUiThread(new Runnable() {
            public void run() {
                try {
                    //TextView txtCurrentTime= (TextView)findViewById(R.id.myText);
                    SimpleDateFormat dateFormatGmt = new SimpleDateFormat("hh:mm a");
                    dateFormatGmt.setTimeZone(TimeZone.getDefault());
                    String dateget = dateFormatGmt.format(new Date());
                    time.setText(dateget.substring(0,dateget.length()-3));
                    ampm.setText(dateget.substring(dateget.length()-2,dateget.length()).toUpperCase());
                } catch (Exception e) {
                }
            }
        });
    }


    class CountDownRunner implements Runnable {
        // @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    doWork();
                    Thread.sleep(100); // Pause of 1 Second
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } catch (Exception e) {
                }
            }
        }
    }


    private void address() {
        String display = "";
        try {

        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(this, Locale.getDefault());
            addresses = geocoder.getFromLocation(Double.parseDouble(lat), Double.parseDouble(lon), 1);


        if (addresses.size() > 0) {

                city.setText(addresses.get(0).getLocality());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private class saveImageClass extends  AsyncTask<String ,String,String>{
        @Override
        protected String doInBackground(String... params) {
            for (int X=0;X<public_ids.size();X++){
                saveImage(public_ids.get(X));
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Intent i = new Intent(getBaseContext(), AllCategory.class);
            startActivity(i);
            finish();
        }
    }
}
