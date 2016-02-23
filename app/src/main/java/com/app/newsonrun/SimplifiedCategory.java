package com.app.newsonrun;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;

public class SimplifiedCategory extends Activity implements View.OnClickListener {

    Button menu,share;
    private ViewPager viewPager;
    private String gettoken;
    private FullScreenImageAdapter adapter;
    private ArrayList<String> public_idlist;
    private String name;
    private RelativeLayout buttonlayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simplified_category);
        menu = (Button) findViewById(R.id.simplifiedviewbutton);
        share = (Button) findViewById(R.id.simplifiedviewshare);
        viewPager = (ViewPager) findViewById(R.id.simplifiedpager);
        buttonlayout = (RelativeLayout) findViewById(R.id.simplifiedviewbuttonlayout);
        //BUtton Clicks
        menu.setOnClickListener(this);
        share.setOnClickListener(this);

        //Fetching assesstoken
        try {
            SavingToken token = new SavingToken(this);
            token.open();
            gettoken = token.getDataString();
            token.close();
        }catch (Exception e){

        }

        try{
            SavingPublicId savingPublicId = new SavingPublicId(SimplifiedCategory.this);
            savingPublicId.open();
            public_idlist = savingPublicId.getSimplified("true");
            savingPublicId.close();
        }catch (Exception e){
            Log.e("Businness", e.toString());
        }
        Log.e("Size " , String.valueOf(public_idlist.get(0)));
        adapter = new FullScreenImageAdapter(this,
                gettoken,
                public_idlist,
                getResources(),this, buttonlayout);

        adapter.notifyDataSetChanged();

        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
        name = public_idlist.get(0);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //Toast.makeText(getBaseContext(), "THis->" + viewPager.getCurrentItem(), Toast.LENGTH_SHORT).show();
                name = public_idlist.get(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {


            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.simplifiedviewbutton:
                final Dialog dialog = new Dialog(SimplifiedCategory.this, android.R.style.Theme_Translucent_NoTitleBar);


                dialog.setContentView(R.layout.menu_dialog);
                RelativeLayout close = (RelativeLayout) dialog.findViewById(R.id.backlay);
                LinearLayout all = (LinearLayout) dialog.findViewById(R.id.allmenu);
                LinearLayout simplified = (LinearLayout) dialog.findViewById(R.id.simplifiedmenu);
                LinearLayout india = (LinearLayout) dialog.findViewById(R.id.indiamenu);
                LinearLayout world = (LinearLayout) dialog.findViewById(R.id.worldmenu);
                LinearLayout entertainment = (LinearLayout) dialog.findViewById(R.id.entertainmentmenu);
                LinearLayout sports = (LinearLayout) dialog.findViewById(R.id.sportsmenu);
                LinearLayout science = (LinearLayout) dialog.findViewById(R.id.sciencemenu);
                LinearLayout business = (LinearLayout) dialog.findViewById(R.id.businessmenu);

                india.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(SimplifiedCategory.this,IndiaCategory.class));
                        finish();
                    }
                });
                sports.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(SimplifiedCategory.this,SportsCategory.class));
                        finish();
                    }
                });
                business.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(SimplifiedCategory.this,BusinessCategory.class));
                        finish();
                    }
                });
                world.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(SimplifiedCategory.this,WorldCategory.class));
                        finish();
                    }
                });
                entertainment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(SimplifiedCategory.this, EntertainmentCategory.class));
                        finish();
                    }
                });
                science.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(SimplifiedCategory.this,ScienceCategory.class));
                        finish();
                    }
                });
                all.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        startActivity(new Intent(SimplifiedCategory.this,AllCategory.class));
                        finish();
                    }
                });
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
                break;
            case R.id.simplifiedviewshare:
                new DownloadImageTask(SimplifiedCategory.this,name);
                break;
        }
    }
}
