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
import android.text.Html;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

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
    private ArrayList<Boolean> timelines3;
    private ArrayList<String> timelinecredits;
    private ArrayList<String> timelinedate;
    private ArrayList<String> headlineslist;
    private ArrayList<String> isS3lis;
    private int width;
    private int height;
    private String getting_id;
    boolean doubleBackToExitPressedOnce = false;
    private int scrollposition=0;
    private File directory;
    private FrameLayout newborading;
    private ArrayList<String> temp;
    private boolean isviral;
    private String currentCategory;
    private Typeface face;
    private ProgressBar firstBar;
    private VerticalPager pager;
    private RelativeLayout close2;
    private String moveto="";
    private boolean isnews;
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
    private Button goupbutton;
    private boolean videopost;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;
    private int videopostCounter;
    private TextView loadingtimelinetv;
    private LinearLayout infolayout;
    private ArrayList<String> timestamplist;
    private boolean onceTime=true;
    private LinearLayout imagecreditlayout;
    private TextView imagecredit;
    private boolean handleft;
    private boolean handdown;
    private ImageView handdownimgv;
    private ImageView handupimgv;
    private ImageView handleftimgv;
    private RelativeLayout swipedown;
    private RelativeLayout swipeleft;
    private ArrayList<String> categorylist;
    private Button moreonbutton;
    private RelativeLayout tagslayout;
    private ListView taglist;


    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_category);
        Intent i = getIntent();
        moveto = i.getStringExtra("moveto");
        isnews = i.getBooleanExtra("isnews", true);
        boolean fromnoti = i.getBooleanExtra("fromnoti", false);
        if (fromnoti){
            Controller.getInstance().trackEvent("BreakingNews", "Clicked", "user");

        }
        //Directory
        MyDirectory myDirectory = new MyDirectory();
        directory = myDirectory.getDirectory();
        isviral = false;
        currentCategory = "ALL";
        Controller.getInstance().trackScreenView("All");

        //Intialize ArrayLists
        initializeArrayLists();


        //Intialize Views
        initializeviews();


       pager = (VerticalPager) findViewById(R.id.verticalpager);

        //SharedPreferences
        editor = getSharedPreferences(MY_PREFS_NAME, 0).edit();
        buttonlayout.setVisibility(View.VISIBLE);
        editor.putBoolean("isfirst", true);
        editor.commit();
        editor.apply();

         prefs = getSharedPreferences(MY_PREFS_NAME, 0);
        final boolean isfirst = prefs.getBoolean("isfirst", false);
        final boolean israted = prefs.getBoolean("israted",false);
        final boolean didyes = prefs.getBoolean("didyes",false);
        handleft = prefs.getBoolean("handleft",false);
        handdown = prefs.getBoolean("handdown",false);
        final int[] count = {prefs.getInt("count", 0)};
        videopost = prefs.getBoolean("videopost", false);
        videopostCounter = prefs.getInt("videopostCounter",0);

        //Button Clicks
        share.setOnClickListener(this);
        refresh.setOnClickListener(this);
        watch.setOnClickListener(this);
        goupbutton.setOnClickListener(this);
        infolayout.setOnClickListener(this);
        moreonbutton.setOnClickListener(this);
        tagslayout.setOnClickListener(this);

        //ListView Setting
        listview.setVisibility(View.GONE);
        if (!(CheckNetworkConnection.isConnectionAvailable(getBaseContext()))){
            showCustomAlert();
        }

        //Fetching Accesstoken
        gettoken = prefs.getString("token", "");

        //Setting font
        Typeface cont = Typeface.createFromAsset(getAssets(), "headline.otf");
        face = Typeface.createFromAsset(getAssets(), "lodingfont.ttf");
        loadingtimelinetv.setTypeface(face);
        youareoffline.setTypeface(face);
        imagecredit.setTypeface(cont);


        new CheckUpdate(gettoken,AllCategory.this).execute();

        Display display = getWindowManager().getDefaultDisplay();
        width = display.getWidth();  // deprecated
        height = display.getHeight();  // deprecated
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(width, height);
        viewPager.setLayoutParams(layoutParams);
        setSwitchContent(isnews);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currenPosition = position;
                onceTime=true;
                handleft=true;
                removeswip();

                if (!handdown) {
                    countit.start();
                }
                videopost = prefs.getBoolean("videopost",false);
                videopostCounter = prefs.getInt("videopostCounter",0);
                RelativeLayout.LayoutParams layoutParamss = new RelativeLayout.LayoutParams(width, 0);
                listview.setLayoutParams(layoutParamss);
                if (count[0] > 100 && !israted) {
                    new MakeRatingDialog(AllCategory.this, editor, didyes);
                    count[0] = 0;
                    editor.putInt("count", count[0]);
                    editor.commit();
                    editor.apply();
                } else if (!israted) {
                    count[0] = count[0] + 1;
                    editor.putInt("count", count[0]);
                }
                Controller.getInstance().trackScreenView(getBaseContext().getResources().getString(R.string.url) + name + ".png");
                name = public_idlist.get(position);
                //setOthertags(position);
                if ((!(CheckNetworkConnection.isConnectionAvailable(getBaseContext())) && position%3==0)) {
                    showCustomAlert();
                }
                removeOptions();
                listview.setVisibility(View.GONE);
                getLink();
                setVisiblityofwatchbutton();
                if (position > public_idlist.size() - 7) {
                    new ExtendCategory().execute();
                }


            }

            @Override
            public void onPageScrollStateChanged(int state) {


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
                    countit.cancel();
                    handdown = true;
                    removeswip();
                } else if (scrollX < 0 && scrollX > -100) {
                    //idown[0] = false;
                }

            }

            public void onViewScrollFinished(int currentPage) {
                moveto = "";
                if (currentPage == 1) {
                    if (!(CheckNetworkConnection.isConnectionAvailable(getBaseContext()))) {
                        showCustomAlert();
                    }
                    if (onceTime) {
                        onceTime = false;
                        Log.e("Fetching", "Timeline");
                        loadingtimelinetv.setVisibility(View.VISIBLE);
                        new FetchTimeline().execute();

                    }
                    countit.cancel();
                    removeswip();
                    //goupbutton.setVisibility(View.VISIBLE);
                    moreonbutton.setVisibility(View.VISIBLE);
                    removeOptions();
                } else {
                    if (!handdown) {
                        countit.start();
                    }
                    //goupbutton.setVisibility(View.GONE);
                    moreonbutton.setVisibility(View.GONE);

                }
            }
        });

    }

    private void initializeArrayLists() {
        temp = new ArrayList<String>();
        othertags = new ArrayList<String>();
        public_idlist = new ArrayList<String>();
        _id = new ArrayList<String>();
        linktonews = new ArrayList<String>();
        contents = new ArrayList<String>();
        headlines = new ArrayList<String>();
        timelinedate = new ArrayList<String>();
        timelinepublicid = new ArrayList<String>();
        timelinecredits = new ArrayList<String>();
        timelines3 = new ArrayList<Boolean>();
        timestamplist = new ArrayList<String>();
        headlineslist= new ArrayList<String>();
        isS3lis= new ArrayList<String>();
        categorylist= new ArrayList<String>();
    }

    private void removeswip() {
        swipedown.setVisibility(View.GONE);
        swipeleft.setVisibility(View.GONE);
    }

    private void initializeviews() {
        close2 = (RelativeLayout) findViewById(R.id.backlay2);
        taglist = (ListView) findViewById(R.id.timlinetagslistView);
        share = (RelativeLayout) findViewById(R.id.sharelayout);
        loadingtimelinetv = (TextView) findViewById(R.id.loadingtimelinetv);
        imagecredit = (TextView) findViewById(R.id.imagecredit);
        refresh = (RelativeLayout) findViewById(R.id.refreshlayout);
        watch = (LinearLayout) findViewById(R.id.allviewwatchvideolayout);
        buttonlayout = (RelativeLayout) findViewById(R.id.allviewbuttonlayout);
        viewPager = (ViewPager) findViewById(R.id.allpager);
        listview = (ListView) findViewById(R.id.alllistview);
        //scrollview = (ScrollView) findViewById(R.id.allscrollView);
        newborading = (FrameLayout)findViewById(R.id.newboarding);
        goupbutton = (Button) findViewById(R.id.goupbutton);
        moreonbutton = (Button) findViewById(R.id.moreonbutton);

        all = (LinearLayout) findViewById(R.id.allmenu);
        science = (LinearLayout) findViewById(R.id.sciencemenu);
        entertainment = (LinearLayout) findViewById(R.id.entertainmentmenu);
        simplified = (LinearLayout) findViewById(R.id.simplifiedmenu);
        viral = (LinearLayout) findViewById(R.id.viralmenu);
        india = (LinearLayout) findViewById(R.id.indiamenu);
        sports = (LinearLayout) findViewById(R.id.sportsmenu);
        business = (LinearLayout) findViewById(R.id.businessmenu);
        world = (LinearLayout) findViewById(R.id.worldmenu);
        infolayout = (LinearLayout) findViewById(R.id.infolayout);
        imagecreditlayout = (LinearLayout) findViewById(R.id.imagecreditlayout);
        youareoffline = (TextView) findViewById(R.id.youareoffline);
        handdownimgv = (ImageView) findViewById(R.id.handdown);
        handleftimgv = (ImageView) findViewById(R.id.handleft);
        swipedown = (RelativeLayout) findViewById(R.id.downlayout);
        swipeleft = (RelativeLayout) findViewById(R.id.leftlayout);
        tagslayout =(RelativeLayout) findViewById(R.id.tagslayout);
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

    }

    private void removeOptions() {
        if (buttonlayout.getVisibility() == View.VISIBLE) {
            imagecreditlayout.setVisibility(View.GONE);
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
            timestamplist.removeAll(timestamplist);
            headlineslist.removeAll(headlineslist);
            isS3lis.removeAll(isS3lis);
            categorylist.removeAll(categorylist);
        }
        int index=0;
        ArrayList<String> tempPid = new ArrayList<String>();
        ArrayList<String> tempothertags = new ArrayList<String>();
        ArrayList<String> temp_id = new ArrayList<String>();
        ArrayList<String> templinktonews = new ArrayList<String>();
        ArrayList<String> temptimestamplist = new ArrayList<String>();
        ArrayList<String> tempheadlineslist = new ArrayList<String>();
        ArrayList<String> tempcategorylist = new ArrayList<String>();
        ArrayList<String> tempisS3lis = new ArrayList<String>();
        for (int t = 0;t<temp.size();t++){
            tempPid.add(index,temp.get(t++));
            tempothertags.add(index,temp.get(t++));
            temp_id.add(index,temp.get(t++));
            templinktonews.add(index,temp.get(t++));
            temptimestamplist.add(index,temp.get(t++));
            tempheadlineslist.add(index,temp.get(t++));
            tempcategorylist.add(index,temp.get(t++));
            tempisS3lis.add(index++,temp.get(t));

        }
        this.temp.removeAll(this.temp);

        int t=0;
            while (tempPid.size()>0){
            if(templinktonews.get(t).equals("n")){
                setArraylists(t,tempPid,tempothertags,temp_id,
                        templinktonews,temptimestamplist,
                        tempheadlineslist,tempcategorylist,tempisS3lis);
            }else {
                searchLink(tempPid, tempothertags, temp_id, templinktonews.get(t),
                        templinktonews,temptimestamplist,
                        tempheadlineslist,tempcategorylist,tempisS3lis);
                setArraylists(t, tempPid, tempothertags, temp_id,
                        templinktonews,temptimestamplist,
                        tempheadlineslist, tempcategorylist, tempisS3lis);
            }
                //t++;
        }


    }

    private void setArraylists(int t, ArrayList<String> tempPid, ArrayList<String> tempothertags,
                               ArrayList<String> temp_id, ArrayList<String> templinktonews,
                               ArrayList<String> temptimestamplist, ArrayList<String> tempheadlineslist, ArrayList<String> tempcategorylist, ArrayList<String> tempisS3lis) {
        public_idlist.add(tempPid.get(t));
        othertags.add(tempothertags.get(t));
        _id.add(temp_id.get(t));
        linktonews.add(templinktonews.get(t));
        timestamplist.add(temptimestamplist.get(t));
        headlineslist.add(tempheadlineslist.get(t));
        categorylist.add(tempcategorylist.get(t));
        isS3lis.add(tempisS3lis.get(t));
        tempPid.remove(t);
        tempothertags.remove(t);
        temp_id.remove(t);
        templinktonews.remove(t);
        temptimestamplist.remove(t);
        tempheadlineslist.remove(t);
        tempcategorylist.remove(t);
        tempisS3lis.remove(t);
    }

    private void searchLink(ArrayList<String> tempPid, ArrayList<String> tempothertags, ArrayList<String> temp_id, String s, ArrayList<String> templinktonews, ArrayList<String> temptimestamplist, ArrayList<String> tempheadlineslist, ArrayList<String> tempcategorylist, ArrayList<String> tempisS3lis) {

        for (int t= 0 ; t<temp_id.size();t++){
            if (temp_id.get(t).equals(s)) {
                if (templinktonews.get(t).equals("")) {
                    setArraylists(t, tempPid, tempothertags, temp_id, templinktonews, temptimestamplist, tempheadlineslist, tempcategorylist, tempisS3lis);
                } else {
                    searchLink(tempPid, tempothertags, temp_id, templinktonews.get(t), templinktonews, temptimestamplist, tempheadlineslist, tempcategorylist, tempisS3lis);
                    setArraylists(t, tempPid, tempothertags, temp_id, templinktonews, temptimestamplist, tempheadlineslist, tempcategorylist, tempisS3lis);
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
                isviral,
                timestamplist,
                imagecreditlayout,
                headlineslist,categorylist,
                swipedown,swipeleft,isS3lis

        );
        viewPager.setAdapter(adapter);
        if (public_idlist.size()>0) {
            if (public_idlist.size()==1){
                new ExtendCategory().execute();
            }
            if (moveto.equals("")) {
                viewPager.setCurrentItem(currenPosition);
                name = public_idlist.get(currenPosition);
                //setOthertags(currenPosition);
            } else {
                for (int move = 0; move < public_idlist.size(); move++) {
                    if (public_idlist.get(move).equals(moveto)) {
                        viewPager.setCurrentItem(move);
                        name = public_idlist.get(move);
                        moveto="";
                        break;
                    }
                }
            }

        }else {
            new ExtendCategory().execute();
        }
        //Log.e("Name ", name);
        Controller.getInstance().trackScreenView(getBaseContext().getResources().getString(R.string.url) + name + ".png");


        getLink();
        setVisiblityofwatchbutton();
        countit.start();
    }

    private void setOthertags(int tagPosition) {

        if (othertags.get(tagPosition).equals("")){
            //timelineheader.setText("No Timeline for this News.");
            ArrayList<String> tagsji = new ArrayList<String>();
            tagsji.add(0,"No Timeline for this News.");

            Tag_View tag_view = new Tag_View(getBaseContext(),tagsji);
            taglist.setAdapter(tag_view);
            tagslayout.setVisibility(View.VISIBLE);
        }else {
            String[] temptags = othertags.get(tagPosition).split(",");
            //timelineheader.setText("More on " + othertags.get(tagPosition) + ".");
            ArrayList<String> tagsji = new ArrayList<String>();
            tagsji.add(0,"Timeline on:");
            for (int i=0;i<temptags.length;i++){
                tagsji.add(i+1,temptags[i]);
            }
            Tag_View tag_view = new Tag_View(getBaseContext(),tagsji);
            taglist.setAdapter(tag_view);
            tagslayout.setVisibility(View.VISIBLE);
        }

    }

    private void setVisiblityofwatchbutton() {
        if (youtubelink.equals(null)||youtubelink.equals("")){
            watch.setVisibility(View.GONE);
        } else {
            watch.setVisibility(View.VISIBLE);
            Log.e("out", String.valueOf(videopost));

            if (!videopost){
                if(videopostCounter<1) {
                    Log.e("in", String.valueOf(videopost));
                    //youareoffline.setBackgroundResource(R.drawable.video_guide);
                    youareoffline.setText("Tap screen to watch video.");
                    youareoffline.setVisibility(View.VISIBLE);
                    youareofflineC.start();
                    editor.putInt("videopostCounter", videopostCounter+1);
                    editor.commit();
                    editor.apply();
                }else {
                    //Toast.makeText(getBaseContext(),"Tap to watch Video.",Toast.LENGTH_SHORT).show();
                    editor.putBoolean("videopost", true);
                    editor.commit();
                    editor.apply();
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tagslayout:
                tagslayout.setVisibility(View.GONE);
                break;
            case R.id.moreonbutton:
                setOthertags(currenPosition);
                break;
            case R.id.sharelayout:
                new DownloadImageTask(AllCategory.this,name);
                break;
            case R.id.refreshlayout:

                removeOptions();
                callMenu();
                break;
            case R.id.allviewwatchvideolayout:
                Intent k = new Intent(AllCategory.this,VideoPlayer.class);
                k.putExtra("watch", youtubelink);
                startActivity(k);
                //Toast.makeText(getBaseContext(),"Watch:" + name,Toast.LENGTH_SHORT).show();
                break;
            case  R.id.goupbutton:
                pager.setCurrentPage(0);
                break;
            case R.id.infolayout:
                imagecreditlayout.setVisibility(View.VISIBLE);
                try{
                    SavingPublicId savingPublicId =new SavingPublicId(AllCategory.this);
                    savingPublicId.open();
                    String crts = savingPublicId.getCredits(public_idlist.get(currenPosition));
                    savingPublicId.close();
                    imagecredit.setText(Html.fromHtml("<b>Image Courtesy- </b>"+crts));
                }catch (Exception e){
                    imagecredit.setText(Html.fromHtml("<b>Image Courtesy- </b>"));
                }

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
            timelines3.remove(timelines3);
            timelinecredits.removeAll(timelinecredits);
            cv = new Custom_view(getBaseContext(), headlines,
                    contents, timelinedate, timelinepublicid, getResources(), AllCategory.this,
                    timelines3,timelinecredits,name);
            listview.setAdapter(cv);
                  }

        @Override
        protected String doInBackground(String... params) {

            getting_id = _id.get(currenPosition);
            String _idl = getting_id;
            InputStream is = null;
            String line;
            try {

                String strUrl = getResources().getString(R.string.apiurl)+"/api/v1/news/related/"+_idl+"?apiKey="+gettoken;
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
                JSONArray array = json_data.getJSONArray("data");
                int count = json_data.getInt("count");

                for (int i=0;i<count;i++){
                        JSONObject data = array.getJSONObject(i);
                        JSONObject publish = data.getJSONObject("publish");
                        JSONObject portrait = publish.getJSONObject("portrait");
                        JSONObject url_id = portrait.getJSONObject("url");
                        JSONObject content = data.getJSONObject("content");
                        JSONObject source = data.getJSONObject("source");
                        JSONObject image = source.getJSONObject("image");
                        String h = data.getString("headline");
                        headlines.add(i, h);
                        String com = content.getString("html");
                        contents.add(i,com );
                        String p_id ="";
                        try{
                        p_id= url_id.getString("public_id");
                            timelines3.add(false);
                        }catch (Exception e){
                        p_id= url_id.getString("filename");
                            timelines3.add(true);
                        }
                        timelinepublicid.add(i, p_id);
                        timelinecredits.add(image.getString("name"));
                        timelinedate.add(i, data.getString("timestampCreated"));

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

                cv.notifyDataSetChanged();
                setListViewHeightBasedOnChildren(listview, height);
                listview.setVisibility(View.VISIBLE);
                loadingtimelinetv.setVisibility(View.GONE);



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
            //if (listItem.getMeasuredHeight()>totalHeight)

            totalHeight = listItem.getMeasuredHeight();
            break;
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight * (listAdapter.getCount()+5);
        listView.setLayoutParams(params);
        listView.requestLayout();
    }

    private void callMenu() {
        //final Dialog dialog = new Dialog(AllCategory.this, android.R.style.Theme_Translucent_NoTitleBar);
        //dialog.setContentView(R.layout.menu_dialog);
        final GridLayout close = (GridLayout) findViewById(R.id.backlay);
        close2.setVisibility(View.VISIBLE);


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
                Controller.getInstance().trackEvent("ALL", "Menu", "user");
                Controller.getInstance().trackScreenView("All");
                isviral = false;
                currentCategory = "ALL";
                currenPosition = 0;moveto="";
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
                Controller.getInstance().trackEvent("Science", "Menu", "user");
                Controller.getInstance().trackScreenView("Science");
                isviral = false;
                currentCategory = "SCIENCE_TECH";
                currenPosition = 0;moveto="";
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
                Controller.getInstance().trackEvent("Sports", "Menu", "user");
                Controller.getInstance().trackScreenView("Sports");
                isviral = false;
                currentCategory = "SPORTS";
                currenPosition = 0;moveto="";
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
                isviral = false;moveto="";
                currentCategory = "ENTERTAINMENT";
                currenPosition = 0;
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
                currentCategory = "WORLD";
                currenPosition = 0;moveto="";
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
                currentCategory = "INDIA";
                currenPosition = 0;moveto="";
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
                currentCategory = "SIMPLIFIED";
                currenPosition = 0;moveto="";
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
                currentCategory = "BUSINESS";
                currenPosition = 0;moveto="";
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

                currentCategory = "VIRAL";
                currenPosition = 0;moveto="";
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
        scrollposition=0;
        onceTime=true;
        fromdatabase(temp);
        countit.cancel();
        settingViewPager();
        removeOptions();
    }

    @Override
    public void onBackPressed() {
        if (tagslayout.getVisibility()==View.VISIBLE){
            tagslayout.setVisibility(View.GONE);
        }else
        if (moreonbutton.getVisibility()==View.VISIBLE) {
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
                Toast.makeText(this, "Press again to exit.", Toast.LENGTH_SHORT).show();

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
    CountDownTimer countit = new CountDownTimer(2* 1000, 500) {

        public void onTick(long millisUntilFinished) {
            //Some code
        }

        public void onFinish() {
            //Logout
            //new FetchTimeline().execute();
            Animation an1;

            swipedown.setVisibility(View.GONE);
            swipeleft.setVisibility(View.GONE);

            if (handleft){
                    if (handdown){
                                newborading.setVisibility(View.GONE);
                    }else {
                        swipedown.setVisibility(View.VISIBLE);
                        an1 = AnimationUtils.loadAnimation(AllCategory.this, R.anim.top_down);
                        an1.setRepeatCount(5);  // animation repeat count
                        an1.setRepeatMode(2);
                        setCustmAnimation(handdownimgv, an1);
                        editor.putBoolean("handdown", true);
                        editor.commit();
                        editor.apply();
                    }
            }else {
                    swipeleft.setVisibility(View.VISIBLE);
                    an1 = AnimationUtils.loadAnimation(AllCategory.this, R.anim.right_left);
                    an1.setRepeatCount(5);  // animation repeat count
                    an1.setRepeatMode(2);
                    setCustmAnimation(handleftimgv, an1);
                    editor.putBoolean("handleft",true);
                    editor.commit();
                    editor.apply();
                    //setCustmAnimation(handleftimgv, an1);
            }
        }

    };

    private void floatAnimation(ImageView swp) {
        TranslateAnimation animation = new TranslateAnimation(0.0f,0.0f,0.0f,20.0f);
        animation.setDuration(1500);
        animation.setRepeatMode(2);
        animation.setRepeatCount(Animation.INFINITE);
        swp.startAnimation(animation);
    }

    private void setCustmAnimation(ImageView img, Animation an1) {
        img.startAnimation(an1);
    }

    CountDownTimer timer = new CountDownTimer(5 *60 * 1000, 5000) {

        public void onTick(long millisUntilFinished) {
            //Some code
        }

        public void onFinish() {
            //Logout
            finish();
        }
    };
    private class ExtendCategory extends AsyncTask<String,String,String> {

        SavingPublicId savingPublicId;
        String gettime;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            savingPublicId = new SavingPublicId(AllCategory.this);
            try {
                savingPublicId.open();
            }catch (Exception e){

            }
            if (public_idlist.size()>0) {
                String tstamp = timestamplist.get(public_idlist.size()-1);
                Log.e("timeStamp ", tstamp);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
                String dateget = tstamp;

                try {
                    Date date = sdf.parse(dateget);
                    long st = date.getTime();
                    gettime = String.valueOf(st);
                } catch (ParseException e) {
                    e.printStackTrace();
                    //gettime="-1";
                }

            }else {
                gettime="-1";
            }
            Log.e(gettime, String.valueOf(public_idlist.size() - 1));
        }

        @Override
        protected String doInBackground(String... params) {
            try{
                String strUrl = getResources().getString(R.string.apiurl)+"/api/v1/news/category/"+currentCategory+"/feed/"+gettime+"?apiKey="+gettoken;
                strUrl = strUrl.replaceAll(" ", "%20");
                URL url = new URL(strUrl);
                HttpURLConnection urlConnection = null;
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.connect();
                InputStream is = urlConnection.getInputStream();
                Log.e("pass 1", "connection success ");

                BufferedReader reader = new BufferedReader
                        (new InputStreamReader(is, "UTF-8"), 8);
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
                        JSONObject source = data.getJSONObject("source");
                        JSONObject image = source.getJSONObject("image");
                        JSONObject tags = data.getJSONObject("tags");
                        JSONArray others = tags.getJSONArray("other");
                        String othert = "";
                        for (int j = 0; j < others.length(); j++) {
                            String temptag = others.getString(j);
                            if (others.length() == 1) {
                                othert += temptag;
                            } else if (j == 0) {
                                othert += temptag;
                            } else if (j == (others.length() - 1)) {
                                othert += "," + temptag;
                            } else {
                                othert += "," + temptag;

                            }
                        }
                        Log.e("Tagi", othert);
                        //Temp Variables
                        String _id = data.getString("_id");
                        String p_id ="";
                        String isS3;
                        try{
                            p_id= url_id.getString("public_id");
                            isS3 = "false";
                        }catch (Exception e){
                            p_id= url_id.getString("filename");
                            isS3 = "true";
                        }
                        String heading = data.getString("headline");
                        String creditname = image.getString("name");
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
                        try {
                            isFact = String.valueOf(tags.getBoolean("isFact"));
                        } catch (Exception e) {

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
                        temp.add(p_id);
                        temp.add(othert);
                        temp.add(_id);
                        temp.add(linkedToNews);
                        temp.add(timestamp);
                        temp.add(heading);
                        temp.add(category);
                        temp.add(isS3);
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
                                othert,
                                linkedToNews,
                                isFact,
                                heading, isS3, creditname);
                    }

                }else {
                }
                savingYoutubeLink.close();
            }catch (Exception e){
                Log.e("Ex ",e.toString());
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (temp.size()>0) {
                Log.e("Size", String.valueOf(temp.size()));
               resetViewPager();
            }
            try {
                savingPublicId.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void showCustomAlert()
    {

        youareoffline.setVisibility(View.VISIBLE);
        youareofflineC.cancel();
        youareofflineC.start();

    }
    CountDownTimer youareofflineC = new CountDownTimer(2*1000, 1000) {

        public void onTick(long millisUntilFinished) {
            //Some code
        }

        public void onFinish() {
            //Logout
            youareoffline.setVisibility(View.GONE);
            //youareoffline.setBackgroundResource(Color.parseColor("#000000"));
            youareoffline.setText(getResources().getString(R.string.interneterror));
        }
    };



}

