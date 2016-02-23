package com.app.newsonrun;

import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Random;
import java.util.TimeZone;

public class LoadingActivity extends Activity {

    private RelativeLayout backgroundlayout;
    private ImageView backgroundimageview;
    private TextView quotestv;
    private TextView quotesbytv;
    private TextView datetv;
    private ProgressBar firstBar;


    private String gettoken;
    private GridView gridview;
    ArrayList<Bitmap> image_clips ;
    private TextView loadingglance;
    private TextView loadingmoto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        Random random = new Random();
        image_clips = new ArrayList<Bitmap>();

        backgroundlayout = (RelativeLayout) findViewById(R.id.background);
        //backgroundimageview = (ImageView) findViewById(R.id.backgroundimageView);
        quotestv = (TextView) findViewById(R.id.quotes);
        quotesbytv = (TextView) findViewById(R.id.quotesby);
        datetv = (TextView) findViewById(R.id.datetv);
        firstBar = (ProgressBar) findViewById(R.id.firstBar);
        gridview = (GridView) findViewById(R.id.loadinggridview);
        loadingglance = (TextView) findViewById(R.id.loadingglace);
        loadingmoto = (TextView) findViewById(R.id.loadingmoto);



        gridview.setAdapter(new ImageAdapter(LoadingActivity.this,this,getArrayBitmap()));
        Thread t2 = new Thread(){
            @Override
            public void run() {
                super.run();
                for (;;){
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

                                ObjectAnimator anim = (ObjectAnimator) AnimatorInflater.loadAnimator(getBaseContext(), R.animator.flip);
                                anim.setTarget(((ImageView) gridview.getChildAt(one)));
                                anim.setDuration(3000);
                                anim.setDuration(500);
                                anim.start();

                                ObjectAnimator anim2 = (ObjectAnimator) AnimatorInflater.loadAnimator(getBaseContext(), R.animator.flip);
                                anim2.setTarget(((ImageView) gridview.getChildAt(two)));
                                anim2.setDuration(3000);
                                anim2.setDuration(500);
                                anim2.start();

                                ObjectAnimator anim3 = (ObjectAnimator) AnimatorInflater.loadAnimator(getBaseContext(), R.animator.flip);
                                anim3.setTarget(((ImageView) gridview.getChildAt(three)));
                                anim3.setDuration(3000);
                                anim3.setDuration(500);
                                anim3.start();

                                ObjectAnimator anim4 = (ObjectAnimator) AnimatorInflater.loadAnimator(getBaseContext(), R.animator.flip);
                                anim4.setTarget(((ImageView) gridview.getChildAt(four)));
                                anim4.setDuration(3000);
                                anim4.setDuration(500);
                                anim4.start();

                                ObjectAnimator anim5 = (ObjectAnimator) AnimatorInflater.loadAnimator(getBaseContext(), R.animator.flip);
                                anim5.setTarget(((ImageView) gridview.getChildAt(five)));
                                anim5.setDuration(3000);
                                anim5.setDuration(500);
                                anim5.start();

                                ObjectAnimator anim6 = (ObjectAnimator) AnimatorInflater.loadAnimator(getBaseContext(), R.animator.flip);
                                anim6.setTarget(((ImageView) gridview.getChildAt(six)));
                                anim6.setDuration(3000);
                                anim6.setDuration(500);
                                anim6.start();

                                ObjectAnimator anim7 = (ObjectAnimator) AnimatorInflater.loadAnimator(getBaseContext(), R.animator.flip);
                                anim7.setTarget(((ImageView) gridview.getChildAt(seven)));
                                anim7.setDuration(3000);
                                anim7.setDuration(500);
                                anim7.start();

                                ObjectAnimator anim8 = (ObjectAnimator) AnimatorInflater.loadAnimator(getBaseContext(), R.animator.flip);
                                anim8.setTarget(((ImageView) gridview.getChildAt(eight)));
                                anim8.setDuration(3000);
                                anim8.setDuration(500);
                                anim8.start();


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
        };t2.start();
         //RandomImage

        //int x = random.nextInt(6);
        //RandomBackground randomBackground = new RandomBackground(getBaseContext());
        //Bitmap bitmap = randomBackground.getBitmap(x);
        //backgroundimageview.setImageBitmap(bitmap);

        //Random Quotes
        int y = random.nextInt(10);
        Quotes q = new Quotes();
        quotestv.setText("\"" + q.getQuoteslist(y) + "\"");
        quotesbytv.setText("- " + q.getAutherlist(y));
        Typeface face = Typeface.createFromAsset(getAssets(), "lodingfont.ttf");
        quotestv.setTypeface(face);
        quotesbytv.setTypeface(face);
        datetv.setTypeface(face);
        loadingglance.setTypeface(face);
        loadingmoto.setTypeface(face);

        SimpleDateFormat dateFormatGmt = new SimpleDateFormat("dd MMMM yyyy");
        dateFormatGmt.setTimeZone(TimeZone.getDefault());

        String dateget = dateFormatGmt.format(new Date());
        datetv.setText(dateget);
        final int[] i = {0};
        Drawable draw = getResources().getDrawable(R.drawable.custom_progressbar);
// set the drawable as progress drawable
        firstBar.setProgressDrawable(draw);
        try {
            SavingToken token = new SavingToken(this);
            token.open();
            gettoken = token.getDataString();
            token.close();
        }catch (Exception e){

        }

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

                String strUrl = "http://52.25.155.157:8080/api/v1/news/feed/-1?apiKey="+gettoken;
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
                for (int i=0;i<array.length();i++){
                    JSONObject data = array.getJSONObject(i);
                    JSONObject publish =data.getJSONObject("publish");
                    JSONObject portrait =publish.getJSONObject("portrait");
                    JSONObject url_id =portrait.getJSONObject("url");
                    JSONObject attributes =data.getJSONObject("attributes");
                    JSONObject tags =data.getJSONObject("tags");
                    String issimplified="false";
                    try{
                        issimplified = String.valueOf(tags.getBoolean("isSimplified"));
                    }catch (Exception e){

                    }
                    try{
                        SavingYoutubeLink savingYoutubeLink = new SavingYoutubeLink(LoadingActivity.this);
                        savingYoutubeLink.open();
                        if (!(data.getString("youtubeVideoId").equals("")||data.getString("youtubeVideoId").equals(null)))
                        savingYoutubeLink.createEntry(1,url_id.getString("public_id"),data.getString("youtubeVideoId"));
                        savingYoutubeLink.close();
                    }catch (Exception e){

                    }
                    try{
                        SavingPublicId savingPublicId = new SavingPublicId(LoadingActivity.this);
                        savingPublicId.open();
                        savingPublicId.createEntry(i,
                                data.getString("_id"),
                                url_id.getString("public_id"),
                                data.getString("timestampCreated"),
                                tags.getString("category"),
                                issimplified,
                                attributes.getInt("editorRating"),
                                attributes.getString("state"),
                                String.valueOf(attributes.getBoolean("breakingNews")),
                                String.valueOf(attributes.getBoolean("enabled"))
                        );
                        savingPublicId.close();
                    }catch (Exception e){
                            Log.e("Error Sql",e.toString());
                            //break;
                    }

                }
                //System.out.println(result);
                //startActivity(i);
                //finish();

            } catch (Exception e) {
                // TODO: handle exception
                Log.e("log_tag", "Error parsing data " + e.toString());

            }
            return null;

        }

        protected void onPostExecute(Void v) {
            Intent i = new Intent(getBaseContext(), AllCategory.class);
            startActivity(i);
            finish();
        }
    }

}
