package com.app.newsonrun;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Random;

public class LoadingActivity extends Activity {

    private static final String MY_PREFS_NAME = "MySetting";
    private RelativeLayout backgroundlayout;
    private TextView datetv;
    private ProgressBar firstBar;
    private String gettoken;
    private GridView gridview;
    static ArrayList<Bitmap> image_clips;
    private TextView time;
    private TextView totalnews;
    private GridImages gridImages;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        image_clips = new ArrayList<Bitmap>();
        backgroundlayout = (RelativeLayout) findViewById(R.id.background);
        time = (TextView) findViewById(R.id.time);
        totalnews = (TextView) findViewById(R.id.getnews);
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
                        Thread.sleep(100);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Random random = new
                                        Random();
                                int one = random.nextInt(54);
                                int two = random.nextInt(54);


                                ObjectAnimator anim = (ObjectAnimator) AnimatorInflater.loadAnimator(getBaseContext(), R.animator.flip);
                                anim.setTarget(((ImageView) gridview.getChildAt(one)));
                                anim.setDuration(500);
                                anim.start();

                                ObjectAnimator anim2 = (ObjectAnimator) AnimatorInflater.loadAnimator(getBaseContext(), R.animator.flip);
                                anim2.setTarget(((ImageView) gridview.getChildAt(two)));
                                anim2.setDuration(500);
                                anim2.start();

                                try {
                                    Picasso.with(getBaseContext())
                                            .load(gridImages.getUriPath(two))
                                            .into(((ImageView) gridview.getChildAt(one)));
                                    Picasso.with(getBaseContext())
                                            .load(gridImages.getUriPath(one))
                                            .into(((ImageView) gridview.getChildAt(two)));
                                }catch (Exception e){

                                }
                                /*Bitmap temp = ((BitmapDrawable) ((ImageView) gridview.getChildAt(one)).getDrawable()).getBitmap();
                                ((ImageView) gridview.getChildAt(one))
                                        .setImageBitmap(((BitmapDrawable) ((ImageView) gridview.getChildAt(two)).getDrawable()).getBitmap());
                                ((ImageView) gridview.getChildAt(two)).setImageBitmap(temp);*/

                            }
                        });

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t2.start();

        Typeface greeting = Typeface.createFromAsset(getAssets(), "treench.otf");
        Typeface face = Typeface.createFromAsset(getAssets(), "lodingfont.ttf");
        time.setTypeface(greeting);
        totalnews.setTypeface(face);

        final int[] i = {0};
        Drawable draw = getResources().getDrawable(R.drawable.custom_progressbar);
        firstBar.setProgressDrawable(draw);
        gettoken = prefs.getString("token", "");
        Random r = new Random();
        Quotes quotes = new Quotes();
        time.setText(quotes.getQuoteslist(r.nextInt(12)));

        new GettingPosts(LoadingActivity.this,gettoken,
                totalnews).execute();
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



}
