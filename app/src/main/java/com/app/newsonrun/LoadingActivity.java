package com.app.newsonrun;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

public class LoadingActivity extends Activity {

    private static final String MY_PREFS_NAME = "MySetting";
    private RelativeLayout backgroundlayout;
    private TextView datetv;
    private ProgressBar firstBar;
    int trends=0,news=0;
    private String gettoken;
    private GridView gridview;
    static ArrayList<Bitmap> image_clips;
    private LocationManager locationManager;
    private String bestProvider;
    private Location currentlocation;
    private TextView howdy;
    private TextView totalviral;
    private TextView ampm;
    private TextView time;
    private TextView itstv;
    private TextView day;
    private TextView newstv;
    private TextView totalnews;
    private TextView viraltv;
    private ImageView viralimageview;
    private ImageView newsimageview;
    private GridImages gridImages;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);


        Switch switchCompat = (Switch) findViewById(R.id
                .mySwitch);



        image_clips = new ArrayList<Bitmap>();
        backgroundlayout = (RelativeLayout) findViewById(R.id.background);

        datetv = (TextView) findViewById(R.id.datetv);
        itstv = (TextView) findViewById(R.id.Its);
        time = (TextView) findViewById(R.id.time);
        ampm = (TextView) findViewById(R.id.ampm);

        day = (TextView) findViewById(R.id.day);
        newstv = (TextView) findViewById(R.id.newstv);
        totalnews = (TextView) findViewById(R.id.getnews);
        viraltv = (TextView) findViewById(R.id.viral);
        totalviral = (TextView) findViewById(R.id.getviral);
        viralimageview = (ImageView) findViewById(R.id.viralimageview);
        newsimageview = (ImageView) findViewById(R.id.newsimageview);
        firstBar = (ProgressBar) findViewById(R.id.firstBar);
        gridview = (GridView) findViewById(R.id.loadinggridview);
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, 0);
         gridImages = new GridImages(getBaseContext());
        image_clips = gridImages.getArrayBitmap();
        gridview.setAdapter(new ImageAdapter(LoadingActivity.this, this, image_clips));
        Thread t2 = new Thread() {
            @Override
            public void run() {
                super.run();
                for (; ; ) {
                    try {
                        Thread.sleep(250);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Random random = new
                                        Random();
                                int one = random.nextInt(54);
                                int two = random.nextInt(54);
                               /* int three = random.nextInt(54);
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
                                int sixteen = random.nextInt(54);*/

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

                                /*
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
                                anim16.start();*/

                                Bitmap temp = ((BitmapDrawable) ((ImageView) gridview.getChildAt(one)).getDrawable()).getBitmap();
                                ((ImageView) gridview.getChildAt(one))
                                        .setImageBitmap(((BitmapDrawable) ((ImageView) gridview.getChildAt(two)).getDrawable()).getBitmap());
                                ((ImageView) gridview.getChildAt(two)).setImageBitmap(temp);

                                /*temp = ((BitmapDrawable) ((ImageView) gridview.getChildAt(three)).getDrawable()).getBitmap();
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
                                   */

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

        Typeface face = Typeface.createFromAsset(getAssets(), "lodingfont.ttf");
        itstv.setTypeface(face);
        time.setTypeface(face);
        ampm.setTypeface(face);
        datetv.setTypeface(face);
        day.setTypeface(face);
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
        firstBar.setProgressDrawable(draw);

        gettoken = prefs.getString("token", "");


        Runnable myRunnableThread = new CountDownRunner();
        Thread myThread = new Thread(myRunnableThread);
        myThread.start();
        new GettingPosts(LoadingActivity.this,gettoken,totalviral,
                totalnews,switchCompat,newsimageview,viralimageview).execute();
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



}
