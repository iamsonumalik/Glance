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
import android.os.Build;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

public class FullScreenImageAdapter extends PagerAdapter {

    private final boolean isviral;
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

    // constructor
    public FullScreenImageAdapter(Activity activity,
                                  String gettoken,
                                  ArrayList<String> imagePaths,

                                  Resources resources, Context context, RelativeLayout menu, boolean isviral) {

        this._activity = activity;
        this._imagePaths = imagePaths;
        getres = resources;
        getcontext = context;
            currentImage = new ImageView(_activity);
        this.gettoken = gettoken;
        size = new ArrayList<String>();
        size.add("1");
        getmenu = menu;
        getmenu.setVisibility(View.VISIBLE);
        this.isviral = isviral;

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
        final PullToRefreshListView timeline;
        inflater = (LayoutInflater) _activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View viewLayout = inflater.inflate(R.layout.layout_fullscreen_image, container,
                false);

        imgDisplay = (ImageView) viewLayout.findViewById(R.id.imgDisplay);
        Button playit = (Button) viewLayout.findViewById(R.id.playit);

        if (isviral){
            playit.setVisibility(View.VISIBLE);
        }else {
            playit.setVisibility(View.GONE);
        }

        imgDisplay.setScaleType(ImageView.ScaleType.FIT_XY);
        //imgDisplay.setTag("header");
        Display display = _activity.getWindowManager().getDefaultDisplay();
        int width = display.getWidth();  // deprecated
        int height = display.getHeight();  // deprecated
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(width, height);
        imgDisplay.setLayoutParams(layoutParams);
        imgDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getmenu.getVisibility() == View.VISIBLE) {
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
            }
        });
        final String imagename = _imagePaths.get(position);
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
        file = new File(directory, imagename+".jpg");
        if (file.exists()) {

            String imgPath = file.getAbsolutePath();
            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
            Bitmap bp = BitmapFactory.decodeFile(imgPath, bmOptions);
            imgDisplay.setImageBitmap(bp);
        }else {
            final String imgageUrl = "http://res.cloudinary.com/innox-technologies/image/upload/c_scale,h_764,q_85/" + imagename + ".jpg";
            Picasso.with(getcontext)
                    .load(imgageUrl)
                    .into(imgDisplay);
           //new SetImageView(imgDisplay,imagename,getcontext,file);
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
