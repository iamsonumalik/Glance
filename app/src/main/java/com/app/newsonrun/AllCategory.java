package com.app.newsonrun;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class AllCategory extends Activity implements View.OnClickListener {

    private static final String MY_PREFS_NAME = "MySetting";
    RelativeLayout share;
    RelativeLayout refresh;
    LinearLayout watch;
    TextView timelineheader;
    private ViewPager viewPager;
    private String gettoken;
    private FullScreenImageAdapter adapter;
    private ArrayList<String> public_idlist;
    private ArrayList<String> othertags;
    private ArrayList<String> _id;
    private ArrayList<String> linktonews;
    private String name;
    private RelativeLayout buttonlayout;
    private String youtubelink;
    private ListView listview;
    private String result;
    private ArrayList<String> headlines;
    private ArrayList<String> contents;
    private ArrayList<String> timelinepublicid;
    private ArrayList<String> timelinedate;
    private int width;
    private int height;
    private String getting_id;
    //private ScrollView scrollview;
    boolean doubleBackToExitPressedOnce = false;
    private int scrollposition=0;
    private File directory;
    private FrameLayout onborading;
    private Button allgo;
    private ArrayList<String> temp;
    private boolean isviral;
    private  String currentCategory;
    private TextView newstv;
    private TextView viraltv;
    private Typeface face;
    private ImageView viralimageview;
    private ImageView newsimageview;
    private RelativeLayout loadingnewpost;
    private ProgressBar firstBar;
    private VerticalPager pager;
    private RelativeLayout close2;
    private String moveto="";
    private boolean isnews;
    private Switch switchCompat;
    private LinearLayout viral;
    private LinearLayout business;
    private LinearLayout simplified;
    private LinearLayout entertainment;
    private LinearLayout sports;
    private LinearLayout world;
    private LinearLayout all;
    private LinearLayout india;
    private LinearLayout science;
    private TextView youareoffline;
    private int currenPosition=0;

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_category);
        Intent i = getIntent();
        moveto = i.getStringExtra("moveto");
        isnews = i.getBooleanExtra("isnews",true);
        //Directory
        MyDirectory myDirectory = new MyDirectory();
        directory = myDirectory.getDirectory();
        isviral = false;
        currentCategory = "ALL";
        Controller.getInstance().trackScreenView("All");

        //Intialize ArrayLists
        temp = new ArrayList<String>();
        othertags = new ArrayList<String>();
        public_idlist = new ArrayList<String>();
        _id = new ArrayList<String>();
        linktonews = new ArrayList<String>();
        contents = new ArrayList<String>();
        headlines = new ArrayList<String>();
        timelinedate = new ArrayList<String>();
        timelinepublicid = new ArrayList<String>();
        close2 = (RelativeLayout) findViewById(R.id.backlay2);
        //Intialize Views
        newstv = (TextView) findViewById(R.id.newstv);
        viraltv = (TextView) findViewById(R.id.viral);
        share = (RelativeLayout) findViewById(R.id.sharelayout);
        refresh = (RelativeLayout) findViewById(R.id.refreshlayout);
        watch = (LinearLayout) findViewById(R.id.allviewwatchvideolayout);
        buttonlayout = (RelativeLayout) findViewById(R.id.allviewbuttonlayout);
        viewPager = (ViewPager) findViewById(R.id.allpager);
        listview = (ListView) findViewById(R.id.alllistview);
        //scrollview = (ScrollView) findViewById(R.id.allscrollView);
        onborading = (FrameLayout)findViewById(R.id.onboarding);
        allgo = (Button) findViewById(R.id.allgo);
        timelineheader = (TextView) findViewById(R.id.timelineheader);
        viralimageview = (ImageView) findViewById(R.id.viralimageview);
        newsimageview = (ImageView) findViewById(R.id.newsimageview);
        loadingnewpost = (RelativeLayout) findViewById(R.id.loadingnewpost);
        switchCompat = (Switch) findViewById(R.id.mySwitch);
        all = (LinearLayout) findViewById(R.id.allmenu);
        science = (LinearLayout) findViewById(R.id.sciencemenu);
        entertainment = (LinearLayout) findViewById(R.id.entertainmentmenu);
        simplified = (LinearLayout) findViewById(R.id.simplifiedmenu);
        viral = (LinearLayout) findViewById(R.id.viralmenu);
        india = (LinearLayout) findViewById(R.id.indiamenu);
        sports = (LinearLayout) findViewById(R.id.sportsmenu);
        business = (LinearLayout) findViewById(R.id.businessmenu);
        world = (LinearLayout) findViewById(R.id.worldmenu);
        youareoffline = (TextView) findViewById(R.id.youareoffline);

       pager = (VerticalPager) findViewById(R.id.verticalpager);

        //SharedPreferences
        final SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, 0).edit();
        allgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonlayout.setVisibility(View.VISIBLE);
                onborading.setVisibility(View.GONE);
                editor.putBoolean("isfirst", true);
                editor.commit();
                editor.apply();
            }
        });
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, 0);
        final boolean isfirst = prefs.getBoolean("isfirst", false);
        final boolean israted = prefs.getBoolean("israted",false);
        final boolean didyes = prefs.getBoolean("didyes",false);
        final int[] count = {prefs.getInt("count", 0)};
        if (!isfirst){
            onborading.setVisibility(View.VISIBLE);

        }else {
            onborading.setVisibility(View.GONE);
        }

        //Button Clicks
        share.setOnClickListener(this);
        refresh.setOnClickListener(this);
        watch.setOnClickListener(this);

        //ListView Setting
        listview.setVisibility(View.GONE);
        //listview.setPullLabel("Pull down for News");
        //listview.setReleaseLabel("Release for News");
        if (!(CheckNetworkConnection.isConnectionAvailable(getBaseContext()))){
            showCustomAlert();
        }

        //Fetching Accesstoken
        gettoken = prefs.getString("token", "");

        //Setting font
        Typeface head = Typeface.createFromAsset(getAssets(), "headline.otf");
        face = Typeface.createFromAsset(getAssets(), "lodingfont.ttf");
        newstv.setTypeface(face);
        viraltv.setTypeface(face);
        timelineheader.setTypeface(face);

        youareoffline.setTypeface(head);


        new CheckUpdate(gettoken,AllCategory.this).execute();

        /*try{
            SavingPublicId savingPublicId = new SavingPublicId(AllCategory.this);
            savingPublicId.open();
            temp = savingPublicId.getAll();
            savingPublicId.close();
        }catch (Exception e){

        }*/
       // fromdatabase(temp);

        Display display = getWindowManager().getDefaultDisplay();
        width = display.getWidth();  // deprecated
        height = display.getHeight();  // deprecated
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(width, height);
        viewPager.setLayoutParams(layoutParams);


        //settingViewPager();

        //savingImage();
        switchCompat.setChecked(isnews);
        setSwitchContent(isnews);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currenPosition = position;
                RelativeLayout.LayoutParams layoutParamss = new RelativeLayout.LayoutParams(width, 0);
                listview.setLayoutParams(layoutParamss);
                if (count[0] > 100 && !israted) {
                    new MakeRatingDialog(AllCategory.this, editor, didyes);
                    count[0] = 0;
                    editor.putInt("count", count[0]);
                    editor.commit();
                } else if (!israted) {
                    count[0] = count[0] + 1;
                    editor.putInt("count", count[0]);
                }
                Controller.getInstance().trackScreenView("http://res.cloudinary.com/innox-technologies/image/upload/c_scale,h_764,q_85/" + name + ".jpg");
                name = public_idlist.get(position);
                if (othertags.get(position).equals("")){
                    timelineheader.setText("No Timeline for this News.");
                }else {
                    timelineheader.setText("More on " + othertags.get(position) + ".");
                }
                if (!(CheckNetworkConnection.isConnectionAvailable(getBaseContext()))) {
                    showCustomAlert();
                }
                removeOptions();
                listview.setVisibility(View.GONE);
                getLink();
                setVisiblityofwatchbutton();
                countit.cancel();
                countit.start();
                if (position > public_idlist.size() - 7) {
                    new ExtendCategory().execute();
                }


            }

            @Override
            public void onPageScrollStateChanged(int state) {


            }
        });

        //viewPager.setPageTransformer(false, new FlipPageViewTransformer());

        switchCompat.setSwitchPadding(40);
        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setSwitchContent(isChecked);
            }
        });

        final boolean[] iup = {true};
        final boolean[] idown = {true};
        pager.addOnScrollListener(new VerticalPager.OnScrollListener() {

            public void onScroll(int scrollX) {
                //Log.d("TestActivity", "scrollX=" + scrollX);
                if (scrollX < -100) {
                    iup[0] = false;
                    removeOptions();
                    callMenu();

                } else if (scrollX < 0 && scrollX > -100) {
                    //idown[0] = false;
                }

            }

            public void onViewScrollFinished(int currentPage) {
                moveto="";
                if (currentPage == 1) {
                    timelineheader.setVisibility(View.VISIBLE);
                    removeOptions();
                } else {
                    timelineheader.setVisibility(View.GONE);

                }
            }
        });
    }

    private void setSwitchContent(boolean isChecked) {
        all.setBackground(getResources().getDrawable(R.drawable.remove_border));
        science.setBackground(getResources().getDrawable(R.drawable.remove_border));
        entertainment.setBackground(getResources().getDrawable(R.drawable.remove_border));
        sports.setBackground(getResources().getDrawable(R.drawable.remove_border));
        viral.setBackground(getResources().getDrawable(R.drawable.remove_border));
        business.setBackground(getResources().getDrawable(R.drawable.remove_border));
        india.setBackground(getResources().getDrawable(R.drawable.remove_border));
        world.setBackground(getResources().getDrawable(R.drawable.remove_border));
        simplified.setBackground(getResources().getDrawable(R.drawable.remove_border));

        if (isChecked) {
            newsimageview.setImageDrawable(getResources().getDrawable(R.drawable.newsled));
            viralimageview.setImageDrawable(getResources().getDrawable(R.drawable.offled));
            Controller.getInstance().trackEvent("All", "Menu", "user");
            Controller.getInstance().trackScreenView("All");
            isviral = false;
            currentCategory = "ALL";
            try{
                SavingPublicId savingPublicId = new SavingPublicId(AllCategory.this);
                savingPublicId.open();
                temp.removeAll(temp);
                temp = savingPublicId.getAll();
                savingPublicId.close();
            }catch (Exception e){

            }
            resetViewPager();
            close2.setVisibility(View.GONE);
        } else {
            newsimageview.setImageDrawable(getResources().getDrawable(R.drawable.offled));
            viralimageview.setImageDrawable(getResources().getDrawable(R.drawable.otherled));
            Controller.getInstance().trackEvent("TRENDS", "Menu", "user");
            Controller.getInstance().trackScreenView("All");
            isviral = false;
            currentCategory = "TRENDS";
            try{
                SavingPublicId savingPublicId = new SavingPublicId(AllCategory.this);
                savingPublicId.open();
                temp.removeAll(temp);
                temp = savingPublicId.getTrends();
                savingPublicId.close();
            }catch (Exception e){

            }
            resetViewPager();
            close2.setVisibility(View.GONE);
        }
    }

    private void removeOptions() {
        if (buttonlayout.getVisibility() == View.VISIBLE) {
            buttonlayout.setVisibility(View.GONE);
            Animation anim = AnimationUtils.loadAnimation(
                    AllCategory.this, R.anim.slide_out_to_bottom
            );
            anim.setDuration(500);

            buttonlayout.setAnimation(anim);
        }
    }

    private void fromdatabase(ArrayList<String> temp) {
        if (currenPosition==0) {
            public_idlist.removeAll(public_idlist);
            othertags.removeAll(othertags);
            linktonews.removeAll(linktonews);
            _id.removeAll(_id);
        }
        int index=0;
        ArrayList<String> tempPid = new ArrayList<String>();
        ArrayList<String> tempothertags = new ArrayList<String>();
        ArrayList<String> temp_id = new ArrayList<String>();
        ArrayList<String> templinktonews = new ArrayList<String>();

        for (int t = 0;t<temp.size();t++){
            tempPid.add(index,temp.get(t++));
            tempothertags.add(index,temp.get(t++));
            temp_id.add(index,temp.get(t++));
            templinktonews.add(index++,temp.get(t));
        }
        this.temp.removeAll(this.temp);

        int t=0;
            while (tempPid.size()>0){
            if(templinktonews.get(t).equals("n")){
                setArraylists(t,tempPid,tempothertags,temp_id,templinktonews);
            }else {
                searchLink(tempPid, tempothertags, temp_id, templinktonews.get(t), templinktonews);
                setArraylists(t, tempPid, tempothertags, temp_id, templinktonews);
            }
                //t++;
        }


    }

    private void setArraylists(int t, ArrayList<String> tempPid, ArrayList<String> tempothertags, ArrayList<String> temp_id, ArrayList<String> templinktonews) {
        public_idlist.add(tempPid.get(t));
        othertags.add(tempothertags.get(t));
        _id.add(temp_id.get(t));
        linktonews.add(templinktonews.get(t));
        tempPid.remove(t);
        tempothertags.remove(t);
        temp_id.remove(t);
        templinktonews.remove(t);
    }

    private void searchLink(ArrayList<String> tempPid, ArrayList<String> tempothertags, ArrayList<String> temp_id, String s, ArrayList<String> templinktonews) {

        for (int t= 0 ; t<temp_id.size();t++){
            if (temp_id.get(t).equals(s)) {
                if (templinktonews.get(t).equals("")) {
                    setArraylists(t, tempPid, tempothertags, temp_id, templinktonews);
                } else {
                    searchLink(tempPid, tempothertags, temp_id, templinktonews.get(t), templinktonews);
                    setArraylists(t, tempPid, tempothertags, temp_id, templinktonews);
                }break;
            }
        }
    }


    private void settingViewPager() {
        adapter = new FullScreenImageAdapter(this,
                gettoken,
                public_idlist,
                getResources(),this,
                buttonlayout,
                isviral

        );

        if (public_idlist.size()>0) {
            viewPager.setAdapter(adapter);
            if (moveto.equals("")) {
                viewPager.setCurrentItem(currenPosition);
                name = public_idlist.get(currenPosition);
                setOthertags(currenPosition);
            } else {
                for (int move = 0; move < public_idlist.size(); move++) {
                    if (public_idlist.get(move).equals(moveto)) {
                        viewPager.setCurrentItem(move);
                        name = public_idlist.get(move);
                        setOthertags(currenPosition);
                        moveto="";
                        break;
                    }
                }
            }

        }else {
            new ExtendCategory().execute();
        }
        //Log.e("Name ", name);
        Controller.getInstance().trackScreenView("http://res.cloudinary.com/innox-technologies/image/upload/c_scale,h_764,q_85/" + name + ".jpg");

        try {
            SavingPublicId savingPublicId = new SavingPublicId(this);
            savingPublicId.open();
            getting_id = savingPublicId.get_id(name);
            Log.e("_id after", getting_id);
            savingPublicId.close();
        } catch (Exception e) {
            Log.e("_id inAdapter", e.toString());

        }

        getLink();
        setVisiblityofwatchbutton();
        countit.start();
    }

    private void setOthertags(int tagPosition) {
        if (othertags.get(tagPosition).equals("")){
            timelineheader.setText("No Timeline for this News.");
        }else {
            timelineheader.setText("More on " + othertags.get(tagPosition) + ".");
        }
    }

    private void setVisiblityofwatchbutton() {
        if (youtubelink.equals(null)||youtubelink.equals("")){
            watch.setVisibility(View.GONE);
        }else {
            watch.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sharelayout:
                new DownloadImageTask(AllCategory.this,name);
                break;
            case R.id.refreshlayout:
                final int[] i = {0};
                loadingnewpost.setVisibility(View.VISIBLE);
                new GettingPosts(AllCategory.this,gettoken,null,null, null,null,null).execute();

                break;
            case R.id.allviewwatchvideolayout:
                Intent k = new Intent(AllCategory.this,VideoPlayer.class);
                k.putExtra("watch", youtubelink);
                startActivity(k);
                //Toast.makeText(getBaseContext(),"Watch:" + name,Toast.LENGTH_SHORT).show();
                break;
        }
    }


    public void getLink() {
        try{
            SavingYoutubeLink savingYoutubeLink = new SavingYoutubeLink(AllCategory.this);
            savingYoutubeLink.open();
            youtubelink= savingYoutubeLink.get_youtubeVideoId(name);
            savingYoutubeLink.close();
        }catch (Exception e){

        }
    }


    private class FetchTimeline extends AsyncTask<String,String,String> {

        private Custom_view cv;


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            headlines.removeAll(headlines);
            contents.removeAll(contents);
            timelinedate.removeAll(timelinedate);
            timelinepublicid.removeAll(timelinepublicid);
            cv = new Custom_view(getBaseContext(), headlines, contents, timelinedate, timelinepublicid, getResources(), AllCategory.this);
            listview.setAdapter(cv);
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                SavingPublicId savingPublicId = new SavingPublicId(AllCategory.this);
                savingPublicId.open();
                getting_id = savingPublicId.get_id(name);
                Log.e("_id after", getting_id);
                savingPublicId.close();
            } catch (Exception e) {
                Log.e("_id inAdapter", e.toString());

            }



            String _idl = getting_id;
            InputStream is = null;
            String line;
            try {

                String strUrl = "http://52.25.155.157:8080/api/v1/news/related/"+_idl+"?apiKey="+gettoken;
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

                headlines.removeAll(headlines);
                contents.removeAll(contents);
                timelinedate.removeAll(timelinedate);
                timelinepublicid.removeAll(timelinepublicid);
                JSONObject json_data = new JSONObject(result);
                JSONArray array = json_data.getJSONArray("data");
                int count = json_data.getInt("count");
                for (int i=0;i<count;i++){
                        JSONObject data = array.getJSONObject(i);
                        JSONObject publish = data.getJSONObject("publish");
                        JSONObject portrait = publish.getJSONObject("portrait");
                        JSONObject url_id = portrait.getJSONObject("url");
                        JSONObject content = data.getJSONObject("content");
                        //URLDecoder.decode(data.getString("headline"), "UTF-8");
                        String h = data.getString("headline");
                        headlines.add(i, h);
                        //temp_header = temp_header + data.getString("headline");

                    String com = Jsoup.parse(content.getString("html")).text();
                    contents.add(i,com );
                        //temp_content = temp_content + Html.fromHtml(content.getString("html"));
                        timelinepublicid.add(i, url_id.getString("public_id"));
                        //temp_public_ids = temp_public_ids + url_id.getString("public_id");
                        //Log.e("Public" ,url_id.getString("public_id"));
                        //Log.e("_id", _idl);
                        timelinedate.add(i, data.getString("timestampCreated"));
                        //temp_date = temp_date + data.getString("timestampCreated");
                }

                //currentImage.setImageDrawable(imgDisplay.getDrawable());
                //arrayAdapter.notifyDataSetChanged();
            } catch (Exception e) {
                // TODO: handle exception
                Log.e("log_tag", "Error parsing data " + e.toString());

            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            cv.notifyDataSetChanged();
            listview.setVisibility(View.VISIBLE);
            //RelativeLayout.LayoutParams layoutParamss = new RelativeLayout.LayoutParams(width, headlines.size()*(height/4) + height/2);
            //listview.setLayoutParams(layoutParamss);
            setListViewHeightBasedOnChildren(listview,height);
            savingImage();
        }
    }

    public static void setListViewHeightBasedOnChildren(ListView listView, int height) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            if (listItem.getMeasuredHeight()>totalHeight)
            totalHeight = listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight * (listAdapter.getCount()+5);
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
    private void savingImage() {
        if (scrollposition < public_idlist.size()) {
            File file = new File(directory, public_idlist.get(scrollposition) + ".jpg");
            if (!file.exists())
                new SetImageView(null, public_idlist.get(scrollposition++), getBaseContext(), file);
            else
                scrollposition++;
        }
    }

    private void callMenu() {
        //final Dialog dialog = new Dialog(AllCategory.this, android.R.style.Theme_Translucent_NoTitleBar);
        //dialog.setContentView(R.layout.menu_dialog);
        final GridLayout close = (GridLayout) findViewById(R.id.backlay);
        close2.setVisibility(View.VISIBLE);
        RelativeLayout switcherlayout = (RelativeLayout) findViewById(R.id.switcherlayout);
        RelativeLayout.LayoutParams layoutPs = new RelativeLayout.LayoutParams(width,(int) (width/2.40));
        switcherlayout.setLayoutParams(layoutPs);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width/4,width/4);

        ImageView topiv = (ImageView) findViewById(R.id.topiv);
        topiv.setLayoutParams(layoutParams);
        all.setPadding(width / 24, width / 24, width / 24, width / 24);


        ImageView explainersiv = (ImageView) findViewById(R.id.explainersiv);
        explainersiv.setLayoutParams(layoutParams);
        simplified.setPadding(width / 24, width / 24, width / 24, width / 24);

        india = (LinearLayout) findViewById(R.id.indiamenu);
        ImageView indiaiv = (ImageView) findViewById(R.id.indiaiv);
        indiaiv.setLayoutParams(layoutParams);
        india.setPadding(width / 24, width / 24, width / 24, width / 24);

         world = (LinearLayout) findViewById(R.id.worldmenu);
        ImageView worldiv = (ImageView) findViewById(R.id.worldiv);
        worldiv.setLayoutParams(layoutParams);
        world.setPadding(width / 24, width / 24, width / 24, width / 24);

        entertainment = (LinearLayout) findViewById(R.id.entertainmentmenu);
        ImageView entertainmentiv = (ImageView) findViewById(R.id.entertainmentiv);
        entertainmentiv.setLayoutParams(layoutParams);
        entertainment.setPadding(width / 24, width / 24, width / 24, width / 24);

        sports = (LinearLayout) findViewById(R.id.sportsmenu);
        ImageView sportsiv = (ImageView) findViewById(R.id.sportsiv);
        sportsiv.setLayoutParams(layoutParams);
        sports.setPadding(width / 24, width / 24, width / 24, width / 24);

        science = (LinearLayout) findViewById(R.id.sciencemenu);
        ImageView scienceiv = (ImageView) findViewById(R.id.scienceiv);
        scienceiv.setLayoutParams(layoutParams);
        science.setPadding(width / 24, width / 24, width / 24, width / 24);

        business = (LinearLayout) findViewById(R.id.businessmenu);
        ImageView businessiv = (ImageView) findViewById(R.id.businessiv);
        businessiv.setLayoutParams(layoutParams);
        business.setPadding(width / 24, width / 24, width / 24, width / 24);

        viral = (LinearLayout) findViewById(R.id.viralmenu);
        ImageView viraliv = (ImageView) findViewById(R.id.viraliv);
        viraliv.setLayoutParams(layoutParams);
        viral.setPadding(width / 24, width / 24, width / 24, width / 24);

        TextView indiatv = (TextView) findViewById(R.id.indiatv);
        indiatv.setTypeface(face);
        TextView worldtv = (TextView) findViewById(R.id.worldtv);
        worldtv.setTypeface(face);
        TextView sciencetv = (TextView) findViewById(R.id.sciencetv);
        sciencetv.setTypeface(face);
        TextView sportstv = (TextView) findViewById(R.id.sportstv);
        sportstv.setTypeface(face);
        TextView businesstv = (TextView) findViewById(R.id.businesstv);
        businesstv.setTypeface(face);
        TextView explainertv = (TextView) findViewById(R.id.explainertv);
        explainertv.setTypeface(face);
        TextView entertainmenttv = (TextView) findViewById(R.id.entertainmenttv);
        entertainmenttv.setTypeface(face);
        TextView viraltv = (TextView) findViewById(R.id.viraltv);
        viraltv.setTypeface(face);
        TextView toptv = (TextView) findViewById(R.id.toptv);
        toptv.setTypeface(face);

        Animation anim2 = AnimationUtils.loadAnimation(
                AllCategory.this,R.anim.bounce
        );
        anim2.setDuration(1000);
        viral.setAnimation(anim2);
        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                all.setBackground(getResources().getDrawable(R.drawable.menuborder));
                science.setBackground(getResources().getDrawable(R.drawable.remove_border));
                entertainment.setBackground(getResources().getDrawable(R.drawable.remove_border));
                sports.setBackground(getResources().getDrawable(R.drawable.remove_border));
                viral.setBackground(getResources().getDrawable(R.drawable.remove_border));
                business.setBackground(getResources().getDrawable(R.drawable.remove_border));
                india.setBackground(getResources().getDrawable(R.drawable.remove_border));
                world.setBackground(getResources().getDrawable(R.drawable.remove_border));
                simplified.setBackground(getResources().getDrawable(R.drawable.remove_border));
                moveto="";
                Controller.getInstance().trackEvent("ALL", "Menu", "user");
                Controller.getInstance().trackScreenView("All");
                isviral = false;
                currenPosition = 0;
                scrollposition=0;

                currentCategory = "ALL";
                try{
                    SavingPublicId savingPublicId = new SavingPublicId(AllCategory.this);
                    savingPublicId.open();
                    temp = savingPublicId.getAll();
                    savingPublicId.close();
                }catch (Exception e){

                }
                resetViewPager();

                close2.setVisibility(View.GONE);

            }
        });
        science.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                all.setBackground(getResources().getDrawable(R.drawable.remove_border));
                science.setBackground(getResources().getDrawable(R.drawable.menuborder));
                entertainment.setBackground(getResources().getDrawable(R.drawable.remove_border));
                sports.setBackground(getResources().getDrawable(R.drawable.remove_border));
                viral.setBackground(getResources().getDrawable(R.drawable.remove_border));
                business.setBackground(getResources().getDrawable(R.drawable.remove_border));
                india.setBackground(getResources().getDrawable(R.drawable.remove_border));
                world.setBackground(getResources().getDrawable(R.drawable.remove_border));
                simplified.setBackground(getResources().getDrawable(R.drawable.remove_border));
                moveto="";
                currenPosition = 0;
                scrollposition=0;
                Controller.getInstance().trackEvent("Science", "Menu", "user");
                Controller.getInstance().trackScreenView("Science");
                isviral = false;
                currentCategory = "SCIENCE_TECH";
                try{
                    SavingPublicId savingPublicId = new SavingPublicId(AllCategory.this);
                    savingPublicId.open();
                    temp = savingPublicId.getByCategory("SCIENCE_TECH");
                    savingPublicId.close();
                }catch (Exception e){
                    Log.e("Science", e.toString());
                }
                resetViewPager();
                close2.setVisibility(View.GONE);

            }
        });
        sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                all.setBackground(getResources().getDrawable(R.drawable.remove_border));
                science.setBackground(getResources().getDrawable(R.drawable.remove_border));
                entertainment.setBackground(getResources().getDrawable(R.drawable.remove_border));
                sports.setBackground(getResources().getDrawable(R.drawable.menuborder));
                viral.setBackground(getResources().getDrawable(R.drawable.remove_border));
                business.setBackground(getResources().getDrawable(R.drawable.remove_border));
                india.setBackground(getResources().getDrawable(R.drawable.remove_border));
                world.setBackground(getResources().getDrawable(R.drawable.remove_border));
                simplified.setBackground(getResources().getDrawable(R.drawable.remove_border));
                moveto="";
                Controller.getInstance().trackEvent("Sports", "Menu", "user");
                Controller.getInstance().trackScreenView("Sports");
                isviral = false;
                currenPosition = 0;
                scrollposition=0;
                currentCategory = "SPORTS";
                try{
                    SavingPublicId savingPublicId = new SavingPublicId(AllCategory.this);
                    savingPublicId.open();
                    temp = savingPublicId.getByCategory("SPORTS");
                    savingPublicId.close();
                }catch (Exception e){
                    Log.e("Science", e.toString());
                }
                resetViewPager();
                close2.setVisibility(View.GONE);

            }
        });
        entertainment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                all.setBackground(getResources().getDrawable(R.drawable.remove_border));
                science.setBackground(getResources().getDrawable(R.drawable.remove_border));
                entertainment.setBackground(getResources().getDrawable(R.drawable.menuborder));
                sports.setBackground(getResources().getDrawable(R.drawable.remove_border));
                viral.setBackground(getResources().getDrawable(R.drawable.remove_border));
                business.setBackground(getResources().getDrawable(R.drawable.remove_border));
                india.setBackground(getResources().getDrawable(R.drawable.remove_border));
                world.setBackground(getResources().getDrawable(R.drawable.remove_border));
                simplified.setBackground(getResources().getDrawable(R.drawable.remove_border));
                moveto="";
                Controller.getInstance().trackEvent("Entertainment", "Menu", "user");
                Controller.getInstance().trackScreenView("Entertainment");
                try{
                    SavingPublicId savingPublicId = new SavingPublicId(AllCategory.this);
                    savingPublicId.open();
                    temp = savingPublicId.getByCategory("ENTERTAINMENT");
                    savingPublicId.close();
                }catch (Exception e){
                    Log.e("Science", e.toString());
                }
                isviral = false;
                currenPosition = 0;
                scrollposition=0;
                currentCategory = "ENTERTAINMENT";
                resetViewPager();
                close2.setVisibility(View.GONE);

            }
        });
        world.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                all.setBackground(getResources().getDrawable(R.drawable.remove_border));
                science.setBackground(getResources().getDrawable(R.drawable.remove_border));
                entertainment.setBackground(getResources().getDrawable(R.drawable.remove_border));
                sports.setBackground(getResources().getDrawable(R.drawable.remove_border));
                viral.setBackground(getResources().getDrawable(R.drawable.remove_border));
                business.setBackground(getResources().getDrawable(R.drawable.remove_border));
                india.setBackground(getResources().getDrawable(R.drawable.remove_border));
                world.setBackground(getResources().getDrawable(R.drawable.menuborder));
                simplified.setBackground(getResources().getDrawable(R.drawable.remove_border));
                moveto="";
                Controller.getInstance().trackEvent("World", "Menu", "user");
                Controller.getInstance().trackScreenView("World");
                try{
                    SavingPublicId savingPublicId = new SavingPublicId(AllCategory.this);
                    savingPublicId.open();
                    temp = savingPublicId.getByCategory("WORLD");
                    savingPublicId.close();
                }catch (Exception e){
                    Log.e("Science", e.toString());
                }
                isviral = false;
                currenPosition = 0;
                scrollposition=0;
                currentCategory = "WORLD";
                resetViewPager();
                close2.setVisibility(View.GONE);

            }
        });
        india.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                all.setBackground(getResources().getDrawable(R.drawable.remove_border));
                science.setBackground(getResources().getDrawable(R.drawable.remove_border));
                entertainment.setBackground(getResources().getDrawable(R.drawable.remove_border));
                sports.setBackground(getResources().getDrawable(R.drawable.remove_border));
                viral.setBackground(getResources().getDrawable(R.drawable.remove_border));
                business.setBackground(getResources().getDrawable(R.drawable.remove_border));
                india.setBackground(getResources().getDrawable(R.drawable.menuborder));
                world.setBackground(getResources().getDrawable(R.drawable.remove_border));
                simplified.setBackground(getResources().getDrawable(R.drawable.remove_border));
                moveto="";
                Controller.getInstance().trackEvent("India", "Menu", "user");
                Controller.getInstance().trackScreenView("India");
                try{
                    SavingPublicId savingPublicId = new SavingPublicId(AllCategory.this);
                    savingPublicId.open();
                    temp = savingPublicId.getByCategory("INDIA");
                    savingPublicId.close();
                }catch (Exception e){
                    Log.e("Science", e.toString());
                }
                isviral = false;
                currenPosition = 0;
                scrollposition=0;
                currentCategory = "INDIA";
                resetViewPager();
                close2.setVisibility(View.GONE);

            }
        });
        simplified.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                all.setBackground(getResources().getDrawable(R.drawable.remove_border));
                science.setBackground(getResources().getDrawable(R.drawable.remove_border));
                entertainment.setBackground(getResources().getDrawable(R.drawable.remove_border));
                sports.setBackground(getResources().getDrawable(R.drawable.remove_border));
                viral.setBackground(getResources().getDrawable(R.drawable.remove_border));
                business.setBackground(getResources().getDrawable(R.drawable.remove_border));
                india.setBackground(getResources().getDrawable(R.drawable.remove_border));
                world.setBackground(getResources().getDrawable(R.drawable.remove_border));
                simplified.setBackground(getResources().getDrawable(R.drawable.menuborder));
                moveto="";
                Controller.getInstance().trackEvent("Simplified", "Menu", "user");
                Controller.getInstance().trackScreenView("Simplified");
                try{
                    SavingPublicId savingPublicId = new SavingPublicId(AllCategory.this);
                    savingPublicId.open();
                    temp = savingPublicId.getSimplified("true");
                    savingPublicId.close();
                }catch (Exception e){
                    Log.e("Businness", e.toString());
                }
                isviral = false;
                currenPosition = 0;
                scrollposition=0;
                currentCategory = "SIMPLIFIED";
                resetViewPager();
                close2.setVisibility(View.GONE);

            }
        });
        business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                all.setBackground(getResources().getDrawable(R.drawable.remove_border));
                science.setBackground(getResources().getDrawable(R.drawable.remove_border));
                entertainment.setBackground(getResources().getDrawable(R.drawable.remove_border));
                sports.setBackground(getResources().getDrawable(R.drawable.remove_border));
                viral.setBackground(getResources().getDrawable(R.drawable.remove_border));
                business.setBackground(getResources().getDrawable(R.drawable.menuborder));
                india.setBackground(getResources().getDrawable(R.drawable.remove_border));
                world.setBackground(getResources().getDrawable(R.drawable.remove_border));
                simplified.setBackground(getResources().getDrawable(R.drawable.remove_border));
                moveto="";

                Controller.getInstance().trackEvent("Business", "Menu", "user");
                Controller.getInstance().trackScreenView("Business");
                try{
                    SavingPublicId savingPublicId = new SavingPublicId(AllCategory.this);
                    savingPublicId.open();
                    temp = savingPublicId.getByCategory("BUSINESS");
                    savingPublicId.close();
                }catch (Exception e){
                    Log.e("Science", e.toString());
                }
                isviral = false;
                currenPosition = 0;
                scrollposition=0;
                currentCategory = "BUSINESS";
                resetViewPager();
                close2.setVisibility(View.GONE);

            }
        });
        viral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                all.setBackground(getResources().getDrawable(R.drawable.remove_border));
                science.setBackground(getResources().getDrawable(R.drawable.remove_border));
                entertainment.setBackground(getResources().getDrawable(R.drawable.remove_border));
                sports.setBackground(getResources().getDrawable(R.drawable.remove_border));
                viral.setBackground(getResources().getDrawable(R.drawable.menuborder));
                business.setBackground(getResources().getDrawable(R.drawable.remove_border));
                india.setBackground(getResources().getDrawable(R.drawable.remove_border));
                world.setBackground(getResources().getDrawable(R.drawable.remove_border));
                simplified.setBackground(getResources().getDrawable(R.drawable.remove_border));
                moveto="";
                Controller.getInstance().trackEvent("Viral", "Menu", "user");
                Controller.getInstance().trackScreenView("Viral");
                try{
                    SavingPublicId savingPublicId = new SavingPublicId(AllCategory.this);
                    savingPublicId.open();
                    temp = savingPublicId.getviral();
                    savingPublicId.close();
                }catch (Exception e){
                    Log.e("Businness", e.toString());
                }
                isviral = true;
                currenPosition = 0;
                scrollposition=0;
                currentCategory = "VIRAL";
                resetViewPager();
                close2.setVisibility(View.GONE);
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                close2.setVisibility(View.GONE);
            }
        });
        close2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                close2.setVisibility(View.GONE);
            }
        });

    }

    private void resetViewPager() {
        fromdatabase(temp);
        //adapter.notifyDataSetChanged();
        savingImage();
        countit.cancel();
        settingViewPager();
        removeOptions();
    }

    @Override
    public void onBackPressed() {
        if (timelineheader.getVisibility()==View.VISIBLE) {
            pager.setCurrentPage(0);
        }else if (close2.getVisibility()==View.VISIBLE){
            close2.setVisibility(View.GONE);
        }else
        if (!(viewPager.getCurrentItem() > 0)) {
                if (doubleBackToExitPressedOnce) {
                    super.onBackPressed();
                    return;
                }

                this.doubleBackToExitPressedOnce = true;
                Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();

                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        doubleBackToExitPressedOnce = false;
                    }
                }, 2000);
            } else {
                viewPager.setCurrentItem(0);
            }
    }

    @Override
    protected void onResume() {
        super.onResume();
        timer.cancel();
    }

    @Override
    protected void onPause() {
        super.onPause();
        timer.start() ;
    }
    CountDownTimer countit = new CountDownTimer(1* 1000, 100) {

        public void onTick(long millisUntilFinished) {
            //Some code
        }

        public void onFinish() {
            //Logout
            new FetchTimeline().execute();
        }

    };
    CountDownTimer timer = new CountDownTimer(5 *60 * 1000, 1000) {

        public void onTick(long millisUntilFinished) {
            //Some code
        }

        public void onFinish() {
            //Logout
            finish();
        }
    };
    private class ExtendCategory extends AsyncTask<String,String,String> {
        private ArrayList<String> ex_public_idlist;
        private ArrayList<String> ex_othertags;
        @Override
        protected String doInBackground(String... params) {
            try{
                ex_public_idlist = new ArrayList<String>();
                ex_othertags = new ArrayList<String>();
                SavingPublicId extendC = new SavingPublicId(AllCategory.this);
                extendC.open();
                String gettime;
                if (public_idlist.size()>0) {
                    String tstamp = extendC.gettimestampcreated(public_idlist.get(public_idlist.size() - 1));
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                    sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
                    String dateget = tstamp;
                    Date date = null;
                    try {
                        date = sdf.parse(dateget);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    long st = date.getTime();
                     gettime = String.valueOf(st);
                }else {
                    gettime="-1";
                }
                Log.e(gettime, String.valueOf(public_idlist.size()-1));
                String strUrl = "http://52.25.155.157:8080/api/v1/news/category/"+currentCategory+"/feed/"+gettime+"?apiKey="+gettoken;
                strUrl = strUrl.replaceAll(" ", "%20");
                URL url = new URL(strUrl);
                HttpURLConnection urlConnection = null;
                urlConnection = (HttpURLConnection) url.openConnection();


                urlConnection.connect();


                InputStream is = urlConnection.getInputStream();
                Log.e("pass 1", "connection success ");

                BufferedReader reader = new BufferedReader
                        (new InputStreamReader(is, "iso-8859-1"), 8);
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    sb.append(line + "\n");

                }
                is.close();
                result = sb.toString();
                Log.e("pass 2", "connection success ");


                JSONObject json_data = new JSONObject(result);
                JSONArray array = json_data.getJSONArray("data");
                SavingYoutubeLink savingYoutubeLink;
                savingYoutubeLink = new SavingYoutubeLink(AllCategory.this);
                savingYoutubeLink.open();
                if (array.length()>0) {
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject data = array.getJSONObject(i);
                        JSONObject publish = data.getJSONObject("publish");
                        JSONObject portrait = publish.getJSONObject("portrait");
                        JSONObject url_id = portrait.getJSONObject("url");
                        JSONObject attributes = data.getJSONObject("attributes");
                        JSONObject tags = data.getJSONObject("tags");
                        JSONArray others = tags.getJSONArray("other");
                        String othert="";
                        for (int j=0;j<others.length();j++){
                            String temptag = others.getString(j);
                            if(others.length()==1){
                                othert += temptag;
                            }else if (j==0){
                                othert += temptag;
                            }else if (j==(others.length()-1)){
                                othert +=" and "+ temptag;
                            }else {
                                othert +=","+ temptag;

                            }
                        }
                        Log.e("Tags",othert);
                        //Temp Variables
                        String _id = data.getString("_id");
                        String p_id = url_id.getString("public_id");
                        String timestamp = data.getString("timestampCreated");
                        String category = tags.getString("category");
                        int editorRating= attributes.getInt("editorRating");
                        String state = attributes.getString("state");
                        String breakingNews =String.valueOf(attributes.getBoolean("breakingNews"));
                        String enabled = String.valueOf(attributes.getBoolean("enabled"));
                        String linkedToNews="n";
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
                        try{
                            linkedToNews = data.getString("linkedToNews");
                        }catch (Exception e){

                        }
                        //Saving YouTubeLink
                        try{
                            if (!(data.getString("youtubeVideoId").equals("") || data.getString("youtubeVideoId").equals(null))) {
                                String youtubeVideoId = data.getString("youtubeVideoId");
                                savingYoutubeLink.createEntry(1, p_id, youtubeVideoId);
                            }
                        }catch (Exception e){

                        }
                        temp.add(p_id);
                        temp.add(othert);
                        temp.add(_id);
                        temp.add(linkedToNews);
                        //public_idlist.add(p_id);
                        //othertags.add(othert);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // adapter.notifyDataSetChanged();

                            }
                        });

                        //Saving Recent Posts
                        extendC.createEntry(i,
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
                                othert,
                                linkedToNews,
                                isFact
                        );



                    }

                }else {
                }
                savingYoutubeLink.close();
            }catch (Exception e){

            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (temp.size()>0) {
               resetViewPager();
            }
        }
    }

    public void showCustomAlert()
    {

        youareoffline.setVisibility(View.VISIBLE);
        youareofflineC.cancel();
        youareofflineC.start();
        /*Context context = getApplicationContext();
        // Create layout inflator object to inflate toast.xml file
        LayoutInflater inflater = getLayoutInflater();

        // Call toast.xml file for toast layout
        View toastRoot = inflater.inflate(R.layout.toast, null);

        Toast toast = new Toast(context);

        // Set layout to toast
        toast.setView(toastRoot);
        toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL,
                0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();*/

    }
    CountDownTimer youareofflineC = new CountDownTimer(5*1000, 1000) {

        public void onTick(long millisUntilFinished) {
            //Some code
        }

        public void onFinish() {
            //Logout
            youareoffline.setVisibility(View.GONE);
        }
    };



}

