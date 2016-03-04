package com.app.newsonrun;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Environment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
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

/**
 * Created by malik on 20/2/16.
 */
public class Custom_view extends ArrayAdapter {

    private final Context context;
    private final ArrayList<String> contents ;
    private final ArrayList<String> headlines;
    private final ArrayList<String> timelinedate;
    private final ArrayList<String> timelinepublicid;
    private final int width;
    private final int height;
    private File directory = null;
    private int prev;
    TextView headlinestv;
    TextView contentstv;
    LinearLayout timelinelayout;
    TextView datetv;
    Button abovebox;
    Button belowbox;
    LinearLayout bottomlayout;
    LinearLayout box;

    ReturnColor  returnColor ;
    private Resources getres;
    private Activity _activity;
    private long currenttime;


    public Custom_view(Context context,
                       ArrayList<String> getheadline,
                       ArrayList<String> getcontents,
                       ArrayList<String> gettimelinedate,
                       ArrayList<String> gettimelinepublicid,
                       Resources ress,
                       Activity act
                        ) {
        super(context, R.layout.timeline_layout, getheadline);
        this.context = context;
        this.getres = ress;
        this._activity = act;
        contents = getcontents;
        headlines = getheadline;
        timelinedate = gettimelinedate;
        timelinepublicid = gettimelinepublicid;
        returnColor = new
                ReturnColor();
        prev =0;
        Display display = _activity.getWindowManager().getDefaultDisplay();
        width = display.getWidth();  // deprecated
        height = display.getHeight();  // deprecated

        if (Environment.getExternalStorageState() == null) {
            //create new file directory object
            directory = new File(Environment.getDataDirectory()
                    + "/Android/data/com.app.newsonrun/cache");

            if (!directory.exists()) {
                directory.mkdirs();
            }

            // if phone DOES have sd card
        } else if (Environment.getExternalStorageState() != null) {
            // search for directory on SD card
            directory = new File(Environment.getExternalStorageDirectory()
                    + "/Android/data/com.app.newsonrun/cache");


            if (!directory.exists()) {
                directory.mkdirs();
            }
        }// end of SD card checking

        // TODO Auto-generated constructor stub
    }






    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public View getView(final int view_pos, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View rowView = inflater.inflate(R.layout.timeline_layout, parent, false);

        datetv = (TextView) rowView.findViewById(R.id.timelinedatetv);
        headlinestv = (TextView) rowView.findViewById(R.id.timelineTitletv);
        contentstv = (TextView) rowView.findViewById(R.id.timelinecontenettv);
        timelinelayout = (LinearLayout) rowView.findViewById(R.id.timelinelayout);
        abovebox = (Button) rowView.findViewById(R.id.smalllineabovetimline);
        belowbox = (Button) rowView.findViewById(R.id.smalllinebelowtimline);
        box = (LinearLayout) rowView.findViewById(R.id.leftstyle);
        bottomlayout = (LinearLayout) rowView.findViewById(R.id.bottomlayout);
        final boolean[] chck = {false};
        int col = (view_pos)%9;
        datetv.setTextColor(getres.getColor(returnColor.colorint(col)));
        box.setBackgroundResource(returnColor.colorint(col));
        belowbox.setBackgroundResource(returnColor.colorint(col));

                timelinelayout.setVisibility(View.VISIBLE);
        String item_content="";
        String item_headline="";
        String item_timeline_public_id="";
        String item_timeline_date="";
        try {
            item_content = contents.get(view_pos);
            item_headline = headlines.get(view_pos);
            item_timeline_public_id = timelinepublicid.get(view_pos);
            item_timeline_date = timelinedate.get(view_pos);
        }catch (Exception e){

        }

                Typeface cont = Typeface.createFromAsset(_activity.getAssets(), "content.otf");
                Typeface head = Typeface.createFromAsset(_activity.getAssets(), "headline.otf");
                headlinestv.setText(item_headline);
                contentstv.setText(item_content);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
                Date date = null;
        try {
                SimpleDateFormat dateFormatGmt = new SimpleDateFormat("d MMM yyyy");
                String dateget = item_timeline_date;

                date = sdf.parse(dateget);

            //Log.e("Get TIme" , String.valueOf(st));
                dateFormatGmt.setTimeZone(TimeZone.getTimeZone("UTC"));
                String formattedDate = dateFormatGmt.format(date);
                datetv.setText(formattedDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }
                headlinestv.setTypeface(head);
                contentstv.setTypeface(cont);
                datetv.setTypeface(cont);

        final String finalItem_timeline_public_id = item_timeline_public_id;
        final Date finalDate = date;
        timelinelayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String t = "1 Feb 2016";
                        currenttime = finalDate.getTime();

                        SimpleDateFormat format  = new SimpleDateFormat("d MMM yyyy");
                        long customtime = 0;
                        try {
                            Date tempdate = format.parse(t);
                             customtime = tempdate.getTime();
                        } catch (ParseException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }


                        Controller.getInstance().trackEvent("TimelineClicked", finalItem_timeline_public_id, "user");
                        final Dialog dialog = new Dialog(_activity, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);
                        String youtubelink = "";
                        final File file = new File(directory, finalItem_timeline_public_id + ".jpg");
                        try{
                            SavingYoutubeLink savingYoutubeLink = new SavingYoutubeLink(_activity);
                            savingYoutubeLink.open();
                            youtubelink= savingYoutubeLink.get_youtubeVideoId(finalItem_timeline_public_id);
                            savingYoutubeLink.close();
                        }catch (Exception e){

                        }
                        dialog.setContentView(R.layout.timeline_dialog);

                        final ImageButton show = (ImageButton) dialog.findViewById(R.id.timelinedialogimageView);
                        final RelativeLayout sharevideolayout = (RelativeLayout) dialog.findViewById(R.id.dialogviewbuttonlayout);
                        FrameLayout close = (FrameLayout) dialog.findViewById(R.id.timelinedialogbackground);
                        Button shareit = (Button) dialog.findViewById(R.id.dialogshare);
                        ImageView logohide = (ImageView) dialog.findViewById(R.id.logohide);
                        if (currenttime<customtime){
                            logohide.setVisibility(View.VISIBLE);
                        }else {
                            logohide.setVisibility(View.GONE);

                        }
                        LinearLayout dialogviewwatchvideolayout = (LinearLayout) dialog.findViewById(R.id.dialogviewwatchvideolayout);
                        if (youtubelink.equals(null)||youtubelink.equals("")){
                            dialogviewwatchvideolayout.setVisibility(View.GONE);
                        }else {
                            dialogviewwatchvideolayout.setVisibility(View.VISIBLE);
                        }
                        close.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                        final String finalYoutubelink = youtubelink;
                        dialogviewwatchvideolayout.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent k = new Intent(_activity,VideoPlayer.class);
                                k.putExtra("watch", finalYoutubelink);
                                _activity.startActivity(k);
                            }
                        });
                        show.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                    if (sharevideolayout.getVisibility()==View.VISIBLE){
                                        sharevideolayout.setVisibility(View.GONE);
                                    }else {
                                        sharevideolayout.setVisibility(View.VISIBLE);
                                    }                                }


                        });
                        //Log.e("Prints", finalItem_headline);
                        shareit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                new DownloadImageTask(_activity,finalItem_timeline_public_id);
                            }
                        });

                        if (file.exists()) {

                            String imgPath = file.getAbsolutePath();
                            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                            Bitmap bp = BitmapFactory.decodeFile(imgPath, bmOptions);
                            show.setImageBitmap(bp);
                        }else {
                            final String imgageUrl = "http://res.cloudinary.com/innox-technologies/image/upload/c_scale,h_764,q_85/" + finalItem_timeline_public_id + ".jpg";
                            Picasso.with(context)
                                    .load(imgageUrl)
                                    .into(show);
                        }
                        //final String imgageUrl = "http://res.cloudinary.com/innox-technologies/image/upload/c_scale,h_764,q_85/" + finalItem_timeline_public_id + ".jpg?_=4";

                        dialog.show();

                    }
                });

                if (view_pos == 0 && headlines.size() == 1) {

                    belowbox.setVisibility(View.GONE);
                    RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(width, height/2);
                    bottomlayout.setLayoutParams(layoutParams);
                    bottomlayout.setVisibility(View.VISIBLE);
                    abovebox.setVisibility(View.VISIBLE);

                } else if (view_pos == 0) {
                    abovebox.setVisibility(View.VISIBLE);

                } else if (view_pos == headlines.size()-1){
                    belowbox.setBackgroundResource(returnColor.colorint(col));
                    belowbox.setVisibility(View.GONE);
                    //RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(width, height/2);
                    //bottomlayout.setLayoutParams(layoutParams);
                    //bottomlayout.setVisibility(View.VISIBLE);

                }




        //setCurrentImage(imgDisplay);
        return rowView;


    }


}