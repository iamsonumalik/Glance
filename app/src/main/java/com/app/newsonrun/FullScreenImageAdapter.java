package com.app.newsonrun;

/**
 * Created by malik on 27/1/16.
 */


import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
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

public class FullScreenImageAdapter extends PagerAdapter {

    private final boolean isviral;
    private final ArrayList<String> timestamplist;
    private final ArrayList<String> currentCategory;
    private final ArrayList<String> headlineslist;
    private final RelativeLayout swipedown;
    private final RelativeLayout swipeleft;
    private final ArrayList<String> isS3lis;
    ArrayList<String> size;
    private final Context getcontext;
    private Activity _activity;
    private ArrayList<String> _imagePaths;
    private LayoutInflater inflater;
    Resources getres;
    private String bitmap;
    private String gettoken;
    private RelativeLayout timelinebackground;
    public  ImageView currentImage;
    Custom_view cv;
    String[] sizi = new String[100];
    RelativeLayout getmenu;
    private String result;
    private BitmapFactory.Options bmOptions;
    private Bitmap bp;
    private LinearLayout imagecreditlayout;

    // constructor
    public FullScreenImageAdapter(Activity activity,
                                  String gettoken,
                                  ArrayList<String> imagePaths,
                                  Resources resources, Context context,
                                  RelativeLayout menu, boolean isviral,
                                  ArrayList<String> timestamplist, LinearLayout imagecreditlayout,
                                  ArrayList<String> headlineslist, ArrayList<String> currentCategory,
                                  RelativeLayout swipedown, RelativeLayout swipeleft, ArrayList<String> isS3lis) {

        this._activity = activity;
        this._imagePaths = imagePaths;
        this.timestamplist = timestamplist;
        getres = resources;
        getcontext = context;
        currentImage = new ImageView(_activity);
        this.gettoken = gettoken;
        size = new ArrayList<String>();
        size.add("1");
        getmenu = menu;
        this.isviral = isviral;
        bmOptions = new BitmapFactory.Options();
        this.imagecreditlayout = imagecreditlayout;
        this.headlineslist = headlineslist;
        this.currentCategory = currentCategory;
        this.swipedown =swipedown;
        this.swipeleft =swipeleft;
        this.isS3lis =isS3lis;
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
        View viewLayout = inflater.inflate(R.layout.layout_fullscreen_image, container,
                false);


        imgDisplay = (ImageView) viewLayout.findViewById(R.id.imgDisplay);
        Display display = _activity.getWindowManager().getDefaultDisplay();
        int width = display.getWidth();  // deprecated
        int height = display.getHeight();  // deprecated
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(width, height);
        imgDisplay.setLayoutParams(layoutParams);


        Button playit = (Button) viewLayout.findViewById(R.id.playit);
        ImageView mainlogohide = (ImageView) viewLayout.findViewById(R.id.mainlogohide);
        TextView headingadapter = (TextView) viewLayout.findViewById(R.id.headingadapter);
        TextView dateadapter = (TextView) viewLayout.findViewById(R.id.dateadapter);
        TextView categoryadapter = (TextView) viewLayout.findViewById(R.id.categoryadapter);
        TextView loadingadapter = (TextView) viewLayout.findViewById(R.id.loadingadapter);
        final String imagename = _imagePaths.get(position);
        final String dateget = timestamplist.get(position);
        final String heading = headlineslist.get(position);
        final String cCAt = currentCategory.get(position);
        final String isS3 = isS3lis.get(position);
        Typeface hd = Typeface.createFromAsset(getcontext.getAssets(), "headline.otf");
        headingadapter.setTypeface(hd);
        dateadapter.setTypeface(hd);
        categoryadapter.setTypeface(hd);
        loadingadapter.setTypeface(hd);

        if (CheckNetworkConnection.isConnectionAvailable(getcontext)) {
            loadingadapter.setText("Loading");
        }else {
            loadingadapter.setText(getcontext.getResources().getString(R.string.interneterror));
        }

        headingadapter.setText(heading);
        if (cCAt.contains("SIMPLIFIED")){
            categoryadapter.setText("EXPLAINER");
        }else
        categoryadapter.setText(cCAt.replace("_","-"));



        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
            Date date = null;
            try {
                SimpleDateFormat dateFormatGmt = new SimpleDateFormat("d MMM yyyy");

                date = sdf.parse(dateget);
                dateadapter.setText(dateFormatGmt.format(date));
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
        if (isviral){
            playit.setVisibility(View.VISIBLE);
        }else {
            playit.setVisibility(View.GONE);
        }

        imgDisplay.setScaleType(ImageView.ScaleType.FIT_XY);


        imgDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getmenu.getVisibility() == View.VISIBLE) {
                    imagecreditlayout.setVisibility(View.GONE);
                    getmenu.setVisibility(View.GONE);
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
                swipedown.setVisibility(View.GONE);
                swipeleft.setVisibility(View.GONE);
            }
        });

        playit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String youtubelink = "";
                try{
                    SavingYoutubeLink savingYoutubeLink = new SavingYoutubeLink(_activity);
                    savingYoutubeLink.open();
                    youtubelink= savingYoutubeLink.get_youtubeVideoId(imagename);
                    savingYoutubeLink.close();
                }catch (Exception e){

                }
                Intent k = new Intent(_activity,VideoPlayer.class);
                k.putExtra("watch", youtubelink);
                _activity.startActivity(k);
            }
        });

        File directory = null;
        final File file;
        MyDirectory myDirectory = new MyDirectory();
        directory = myDirectory.getDirectory();
        if (imagename.contains(".png"))
            file = new File(directory, imagename);
        else
        file = new File(directory, imagename+".png");
        if (file.exists()) {

            String imgPath = file.getAbsolutePath();
            Picasso.with(getcontext)
                    .load(file)
                    .resize(width, height)
                    .into(imgDisplay);
        }else {
            String imgageUrl;
            Log.e("isS3",isS3);
            if (isS3.equals("true")){
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
