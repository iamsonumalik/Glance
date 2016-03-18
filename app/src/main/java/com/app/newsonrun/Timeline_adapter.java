package com.app.newsonrun;

/**
 * Created by malik on 27/1/16.
 */


import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class Timeline_adapter extends PagerAdapter {

    private final ArrayList<String> timestamplist;
    private final ArrayList<Boolean> timelines3;
    private final ArrayList<String> timelinecredits;
    ArrayList<String> size;
    private final Context getcontext;
    private Activity _activity;
    private ArrayList<String> _imagePaths;
    private LayoutInflater inflater;


    public  ImageView currentImage;
    Custom_view cv;
    String[] sizi = new String[100];

    // constructor
    public Timeline_adapter(Activity activity,
                            ArrayList<String> imagePaths,
                            Context context,
                            ArrayList<String> timestamplist, ArrayList<Boolean> timelines3,
                            ArrayList<String> timelinecredits) {

        this._activity = activity;
        this._imagePaths = imagePaths;
        this.timestamplist = timestamplist;
        getcontext = context;
        currentImage = new ImageView(_activity);
        this.timelines3 =timelines3;
        this.timelinecredits = timelinecredits;
        size = new ArrayList<String>();
        size.add("1");
    }




    @Override
    public int getCount() {
        return this._imagePaths.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == ((RelativeLayout) object);
    }


    @TargetApi(Build.VERSION_CODES.M)
    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        final ImageView imgDisplay;
        inflater = (LayoutInflater) _activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewLayout = inflater.inflate(R.layout.timeline_viewpageradapter, container,
                false);

        imgDisplay = (ImageView) viewLayout.findViewById(R.id.timelineadapterimageView);
        Button dialogshare = (Button) viewLayout.findViewById(R.id.dialogshare);
        TextView dialogimagecredit = (TextView) viewLayout.findViewById(R.id.dialogimagecredit);
        LinearLayout playit =(LinearLayout) viewLayout.findViewById(R.id.dialogviewwatchvideolayout);
        ImageView mainlogohide = (ImageView) viewLayout.findViewById(R.id.logohide);
        final RelativeLayout getmenu = (RelativeLayout) viewLayout.findViewById(R.id.dialogviewbuttonlayout);
        final LinearLayout dialogimagecreditlayout = (LinearLayout) viewLayout.findViewById(R.id.dialogimagecreditlayout);
        final LinearLayout dialoginfolayout = (LinearLayout) viewLayout.findViewById(R.id.dialoginfolayout);
        dialogimagecreditlayout.setVisibility(View.GONE);
        getmenu.setVisibility(View.GONE);
        final String imagename = _imagePaths.get(position);
        final String dateget = timestamplist.get(position);
        boolean isS3 = timelines3.get(position);
        final String crts = timelinecredits.get(position);
        Typeface cont = Typeface.createFromAsset(_activity.getAssets(), "headline.otf");
        dialogimagecredit.setTypeface(cont);
        dialogimagecredit.setText(Html.fromHtml("<b>Image Courtesy- </b>"+crts));
        String youtubelink = "";
        try{
            SavingYoutubeLink savingYoutubeLink = new SavingYoutubeLink(_activity);
            savingYoutubeLink.open();
            youtubelink= savingYoutubeLink.get_youtubeVideoId(imagename);
            savingYoutubeLink.close();
        }catch (Exception e){

        }
        if (youtubelink.equals("")){
            playit.setVisibility(View.GONE);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date date = null;
        try {
            SimpleDateFormat dateFormatGmt = new SimpleDateFormat("d MMM yyyy");

            date = sdf.parse(dateget);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String t = "1 Feb 2016";
        long currenttime = date.getTime();

        SimpleDateFormat format  = new SimpleDateFormat("d MMM yyyy");
        long customtime = 0;
        try {
            Date tempdate = format.parse(t);
            customtime = tempdate.getTime();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        if (currenttime<customtime){
            mainlogohide.setVisibility(View.GONE);
        }else {
            mainlogohide.setVisibility(View.GONE);
        }

        imgDisplay.setScaleType(ImageView.ScaleType.FIT_XY);

        Display display = _activity.getWindowManager().getDefaultDisplay();
        int width = display.getWidth();  // deprecated
        int height = display.getHeight();  // deprecated


        imgDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getmenu.getVisibility() == View.VISIBLE) {
                    getmenu.setVisibility(View.GONE);
                    dialogimagecreditlayout.setVisibility(View.GONE);
                    Animation anim = AnimationUtils.loadAnimation(
                            _activity, R.anim.slide_out_to_bottom
                    );
                    anim.setDuration(500);

                    getmenu.setAnimation(anim);
                } else {
                    Animation anim = AnimationUtils.loadAnimation(
                            _activity, R.anim.slide_in_from_bottom
                    );
                    anim.setDuration(500);

                    getmenu.setAnimation(anim);
                    getmenu.setVisibility(View.VISIBLE);
                }
            }
        });

        final String finalYoutubelink = youtubelink;
        playit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent k = new Intent(_activity,VideoPlayer.class);
                k.putExtra("watch", finalYoutubelink);
                _activity.startActivity(k);
            }
        });
        dialoginfolayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogimagecreditlayout.setVisibility(View.VISIBLE);
            }
        });
        dialogshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DownloadImageTask(_activity,imagename);
            }
        });
        File directory = null;
        final File file;
        MyDirectory myDirectory = new MyDirectory();
        directory = myDirectory.getDirectory();
        file = new File(directory, imagename+".png");
        if (file.exists()) {

            String imgPath = file.getAbsolutePath();
            Picasso.with(getcontext)
                    .load(file)
                    .resize(width, height)
                    .into(imgDisplay);
        }else {
            String imgageUrl;
            if (isS3){
                imgageUrl = "http://d2vwmcbs3lyudp.cloudfront.net/"+imagename;
            }else {
                 imgageUrl = getcontext.getResources().getString(R.string.url) + imagename + ".png";
            }

            Picasso.with(getcontext)
                    .load(imgageUrl)
                    .resize(width, height)
                    .into(imgDisplay);
            try {
                SavingCache savingCache = new SavingCache(getcontext);
                savingCache.open();
                savingCache.createEntry(1, imagename, dateget);
                savingCache.close();
                Log.e("Saved Cache",imagename);
            }catch (Exception e){

            }
            //imgDisplay.setLayoutParams(layoutParams);
        }


        ((ViewPager) container).addView(viewLayout);

        return viewLayout;
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        ((ViewPager) container).removeView((RelativeLayout) object);

    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }




}
