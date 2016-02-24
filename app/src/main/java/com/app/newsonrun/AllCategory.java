package com.app.newsonrun;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
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
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.handmark.pulltorefresh.library.PullToRefreshScrollView;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class AllCategory extends Activity implements View.OnClickListener {

    Button readmore,share,refresh;
    LinearLayout watch;
    private ViewPager viewPager;
    private String gettoken;
    private FullScreenImageAdapter adapter;
    private ArrayList<String> public_idlist;
    private String name;
    private RelativeLayout buttonlayout;
    private String youtubelink;
    private PullToRefreshListView listview;
    private String result;
    private ArrayList<String> headlines;
    private ArrayList<String> contents;
    private ArrayList<String> timelinepublicid;
    private ArrayList<String> timelinedate;
    private Custom_view cv;
    private int width;
    private int height;
    private String getting_id;
    private PullToRefreshScrollView scrollview;
    boolean doubleBackToExitPressedOnce = false;
    private int scrollposition=5;
    private File directory;
    private FrameLayout onborading;
    private Button allgo;

    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_category);
        MyDirectory myDirectory = new MyDirectory();
        directory = myDirectory.getDirectory();
        //Intialize
        contents = new ArrayList<String>();
        headlines = new ArrayList<String>();
        timelinedate = new ArrayList<String>();
        timelinepublicid = new ArrayList<String>();


        readmore = (Button) findViewById(R.id.allviewbutton);
        share = (Button) findViewById(R.id.allviewshare);
        refresh = (Button) findViewById(R.id.allviewrefresh);
        watch = (LinearLayout) findViewById(R.id.allviewwatchvideolayout);
        buttonlayout = (RelativeLayout) findViewById(R.id.allviewbuttonlayout);
        viewPager = (ViewPager) findViewById(R.id.allpager);
        listview = (PullToRefreshListView) findViewById(R.id.alllistview);
        scrollview = (PullToRefreshScrollView) findViewById(R.id.allscrollView);
        onborading = (FrameLayout)findViewById(R.id.onboarding);
        allgo = (Button) findViewById(R.id.allgo);
        allgo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonlayout.setVisibility(View.VISIBLE);
                onborading.setVisibility(View.GONE);
            }
        });
        //BUtton Clicks
        readmore.setOnClickListener(this);
        share.setOnClickListener(this);
        refresh.setOnClickListener(this);
        watch.setOnClickListener(this);

        //ListView Setting
        listview.setVisibility(View.GONE);
        listview.setPullLabel("Pull down for News");
        listview.setReleaseLabel("Release for News");
        if (!(CheckNetworkConnection.isConnectionAvailable(getBaseContext()))){

        //}else {
            Toast.makeText(getBaseContext(),"you are offline",Toast.LENGTH_SHORT)
                    .show();
        }
        //Fetching assesstoken
        try {
            SavingToken token = new SavingToken(this);
            token.open();
            gettoken = token.getDataString();
            token.close();
        }catch (Exception e){

        }

        try{
            SavingPublicId savingPublicId = new SavingPublicId(AllCategory.this);
            savingPublicId.open();
            public_idlist = savingPublicId.getAll();
            savingPublicId.close();
        }catch (Exception e){

        }
        adapter = new FullScreenImageAdapter(this,
                gettoken,
                public_idlist,
                getResources(),this,
                buttonlayout

        );

        viewPager.setAdapter(adapter);
        Display display = getWindowManager().getDefaultDisplay();
        width = display.getWidth();  // deprecated
        height = display.getHeight();  // deprecated
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(width, height);
        viewPager.setLayoutParams(layoutParams);
        viewPager.setCurrentItem(0);
        name = public_idlist.get(0);
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
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                name = public_idlist.get(position);
                if (!(CheckNetworkConnection.isConnectionAvailable(getBaseContext()))) {
                    Toast.makeText(getBaseContext(), "you are offline", Toast.LENGTH_SHORT).show();
                }
                if (buttonlayout.getVisibility() == View.VISIBLE) {
                    buttonlayout.setVisibility(View.GONE);
                    Animation anim = AnimationUtils.loadAnimation(
                            AllCategory.this, R.anim.slide_out_to_bottom
                    );
                    anim.setDuration(500);

                    buttonlayout.setAnimation(anim);
                }
                //listview.removeAllViews();
                listview.setVisibility(View.GONE);
                getLink();
                setVisiblityofwatchbutton();
                /*
                if (scrollposition < public_idlist.size()) {
                    File file = new File(directory, public_idlist.get(scrollposition) + ".png");
                    if (!file.exists())
                        new SetImageView(null, public_idlist.get(scrollposition++), getBaseContext(), file);
                    else
                        scrollposition++;
                }
                if (scrollposition < public_idlist.size()) {
                    File file = new File(directory, public_idlist.get(scrollposition) + ".png");
                    if (!file.exists())
                        new SetImageView(null, public_idlist.get(scrollposition++), getBaseContext(), file);
                    else
                        scrollposition++;
                }*/
            }

            @Override
            public void onPageScrollStateChanged(int state) {


            }
        });

        //viewPager.setPageTransformer(false, new FlipPageViewTransformer());

        setVisiblityofwatchbutton();
        scrollview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ScrollView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                callMenu();
                scrollview.onRefreshComplete();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ScrollView> refreshView) {
                if (listview.getVisibility()==View.GONE) {
                    FetchTimeline fetchTimeline = new FetchTimeline();
                    fetchTimeline.start();
                }else {
                    scrollview.setVisibility(View.GONE);
                    scrollview.onRefreshComplete();
                }


            }
        });

        listview.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                scrollview.setVisibility(View.VISIBLE);
                listview.onRefreshComplete();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
            }
        });

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
            case R.id.allviewbutton:
                Toast.makeText(getBaseContext(),"Read More:" + name,Toast.LENGTH_SHORT).show();
                break;
            case R.id.allviewshare:
                new DownloadImageTask(AllCategory.this,name);
                break;
            case R.id.allviewrefresh:
                startActivity(new Intent(AllCategory.this,LoadingActivity.class));
                finish();
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


    private class FetchTimeline extends Thread {

        @Override
        public synchronized void  run() {
            super.run();

            try {
                SavingPublicId savingPublicId = new SavingPublicId(AllCategory.this);
                savingPublicId.open();
                getting_id = savingPublicId.get_id(name);
                Log.e("_id after", getting_id);
                savingPublicId.close();
            } catch (Exception e) {
                Log.e("_id inAdapter", e.toString());

            }


            headlines.removeAll(headlines);
            contents.removeAll(contents);
            timelinedate.removeAll(timelinedate);
            timelinepublicid.removeAll(timelinepublicid);
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

                    String com = Jsoup.parse(content.getString("html")).text().replaceAll("\\[b\\]", "");
                    com = com.replaceAll("\\[/b\\]","");
                    com = com.replaceAll("\\[/i\\]","");
                    com = com.replaceAll("\\[i\\]","");
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


                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        cv = new Custom_view(getBaseContext(), headlines, contents, timelinedate, timelinepublicid, getResources(), AllCategory.this);
                        listview.setVisibility(View.VISIBLE);
                        listview.setAdapter(cv);
                        scrollview.setVisibility(View.GONE);
                        scrollview.onRefreshComplete();

                    }
                });
        }



    }

    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            if (listItem instanceof ViewGroup) {
                listItem.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
            }
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();

        //int sizeextra = (height / 160) * 20;
        //params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1)) + sizeextra*(listAdapter.getCount());
       // listView.setLayoutParams(params);
    }

    private void callMenu() {
        final Dialog dialog = new Dialog(AllCategory.this, android.R.style.Theme_Translucent_NoTitleBar);


        dialog.setContentView(R.layout.menu_dialog);
        GridLayout close = (GridLayout) dialog.findViewById(R.id.backlay);
        RelativeLayout close2 = (RelativeLayout) dialog.findViewById(R.id.backlay2);
        LinearLayout all = (LinearLayout) dialog.findViewById(R.id.allmenu);
        LinearLayout simplified = (LinearLayout) dialog.findViewById(R.id.simplifiedmenu);
        LinearLayout india = (LinearLayout) dialog.findViewById(R.id.indiamenu);
        LinearLayout world = (LinearLayout) dialog.findViewById(R.id.worldmenu);
        LinearLayout entertainment = (LinearLayout) dialog.findViewById(R.id.entertainmentmenu);
        LinearLayout sports = (LinearLayout) dialog.findViewById(R.id.sportsmenu);
        LinearLayout science = (LinearLayout) dialog.findViewById(R.id.sciencemenu);
        LinearLayout business = (LinearLayout) dialog.findViewById(R.id.businessmenu);

        all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllCategory.this, AllCategory.class));
                finish();
            }
        });
        science.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllCategory.this, ScienceCategory.class));
                finish();
            }
        });
        sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllCategory.this, SportsCategory.class));
                finish();
            }
        });
        entertainment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllCategory.this, EntertainmentCategory.class));
                finish();
            }
        });
        world.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllCategory.this, WorldCategory.class));
                finish();
            }
        });
        india.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllCategory.this, IndiaCategory.class));
                finish();
            }
        });
        simplified.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AllCategory.this, SimplifiedCategory.class));
                finish();
            }
        });
        business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(AllCategory.this, BusinessCategory.class));
                finish();
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        close2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    @Override
    public void onBackPressed() {
        if (scrollview.getVisibility()==View.GONE) {
            scrollview.setVisibility(View.VISIBLE);
        }else  if (!(viewPager.getCurrentItem() > 0)) {
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
    CountDownTimer timer = new CountDownTimer(5 *60 * 1000, 1000) {

        public void onTick(long millisUntilFinished) {
            //Some code
        }

        public void onFinish() {
            //Logout
            finish();
        }
    };
}

