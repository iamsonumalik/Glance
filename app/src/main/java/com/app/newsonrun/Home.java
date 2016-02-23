package com.app.newsonrun;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


public class Home extends Activity implements View.OnClickListener{


    Button button;
    private ViewPager viewPager;
    //private Utils utils;
    private FullScreenImageAdapter adapter;

    static int fetchno=12;
    ArrayList<String> headline;
    ArrayList<String> content;
    private String gettoken;
    ArrayList<String> timeline_ids;
    ArrayList<String> public_idlist;
    ArrayList<String> _ids;
    private ArrayList<String> timeline_public_ids;
    private Button sharebutton;
    private ArrayList<String> timeline_date;

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_layout_one);
        viewPager = (ViewPager) findViewById(R.id.pager);

        try {
            SavingToken token = new SavingToken(this);
            token.open();
            gettoken = token.getDataString();
            token.close();
        }catch (Exception e){

        }

        //utils = new Utils(getActivity());
        Intent i = getIntent();

        public_idlist = i.getStringArrayListExtra("publicids");



        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //Toast.makeText(getBaseContext(), "THis->" + viewPager.getCurrentItem(), Toast.LENGTH_SHORT).show();



            }

            @Override
            public void onPageScrollStateChanged(int state) {


            }
        });




        button = (Button) findViewById(R.id.oneviewbutton);
        sharebutton =(Button) findViewById(R.id.oneviewshare);


        sharebutton.setOnClickListener(this);
        button.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.oneviewshare:
                int position = viewPager.getCurrentItem();
                String name = public_idlist.get(position);
                ImageView imgview = new ImageView(this);
                Picasso.with(this)
                        .load("http://res.cloudinary.com/innox-technologies/image/upload/c_scale,h_764,q_85/" + name + ".jpg?_=4")
                        .into(imgview);
                Uri bmpUri = getLocalBitmapUri(imgview,name);
                if (bmpUri != null) {
                    // Construct a ShareIntent with link to image
                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.putExtra(Intent.EXTRA_STREAM, bmpUri);
                    shareIntent.setType("image/*");
                    // Launch sharing dialog for image
                    startActivity(Intent.createChooser(shareIntent, "Share Image"));
                } else {
                    // ...sharing failed, handle error
                }
                break;
            case R.id.oneviewbutton:
                final Dialog dialog = new Dialog(Home.this, android.R.style.Theme_Translucent_NoTitleBar);


                dialog.setContentView(R.layout.menu_dialog);
                RelativeLayout close = (RelativeLayout) dialog.findViewById(R.id.backlay);
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
                break;
        }
    }



    public Uri getLocalBitmapUri(ImageView imageView, String name) {
        // Extract Bitmap from ImageView drawable
        Drawable drawable = imageView.getDrawable();
        Bitmap bmp = null;
        if (drawable instanceof BitmapDrawable){
            bmp = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        } else {
            return null;
        }
        // Store image to default external storage directory
        Uri bmpUri = null;
        try {
            File file =  new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS), "share_image_" + name + ".png");
            file.getParentFile().mkdirs();
            FileOutputStream out = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.close();
            bmpUri = Uri.fromFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmpUri;
    }
}
