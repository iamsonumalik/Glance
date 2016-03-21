package com.app.newsonrun;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.app.newsonrun.ryanharter.viewpager.ViewPager;

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
    private final Timeline_adapter adapter;
    private final ArrayList<Boolean> timelines3;
    private final String name;
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
    private ProgressBar timelineprogressbar;


    public Custom_view(Context context,
                       ArrayList<String> getheadline,
                       ArrayList<String> getcontents,
                       ArrayList<String> gettimelinedate,
                       ArrayList<String> gettimelinepublicid,
                       Resources ress,
                       Activity act,
                       ArrayList<Boolean> timelines3, ArrayList<String> timelinecredits, String name) {
        super(context, R.layout.timeline_layout, getheadline);
        this.context = context;
        this.getres = ress;
        this._activity = act;
        contents = getcontents;
        headlines = getheadline;
        timelinedate = gettimelinedate;
        timelinepublicid = gettimelinepublicid;
        this.timelines3 =timelines3;
        this.name = name;
        adapter = new Timeline_adapter(act,gettimelinepublicid,context,gettimelinedate,timelines3,timelinecredits);
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
        TextView endoftimeline = (TextView) rowView.findViewById(R.id.endoftimeline);
        abovebox = (Button) rowView.findViewById(R.id.smalllineabovetimline);
        belowbox = (Button) rowView.findViewById(R.id.smalllinebelowtimline);
        box = (LinearLayout) rowView.findViewById(R.id.leftstyle);
        bottomlayout = (LinearLayout) rowView.findViewById(R.id.bottomlayout);
        timelineprogressbar = (ProgressBar) rowView.findViewById(R.id.timelineprogressbar);
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

                dateFormatGmt.setTimeZone(TimeZone.getTimeZone("UTC"));
                String formattedDate = dateFormatGmt.format(date);
                datetv.setText(formattedDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }
                headlinestv.setTypeface(head);
                contentstv.setTypeface(cont);
                datetv.setTypeface(cont);
                endoftimeline.setTypeface(head);

        final String finalItem_timeline_public_id = item_timeline_public_id;
        timelinelayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        Controller.getInstance().trackEvent("TimelineClicked", finalItem_timeline_public_id, "user");
                        final Dialog dialog = new Dialog(_activity, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen);

                        dialog.setContentView(R.layout.timeline_dialog);
                        //dialog.getWindow().getAttributes().windowAnimations = R.style.dialog_animation;
                        final ViewPager show = (ViewPager) dialog.findViewById(R.id.timelinedialogimageView);
                        FrameLayout close = (FrameLayout) dialog.findViewById(R.id.timelinedialogbackground);
                        Button closetimelinedialog = (Button) dialog.findViewById(R.id.closetimelinedialog);
                        ImageView logohide = (ImageView) dialog.findViewById(R.id.logohide);

                        show.setAdapter(adapter);
                        show.setCurrentItem(view_pos);

                        closetimelinedialog.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                        close.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });

                        dialog.show();

                    }
                });

                if (item_timeline_public_id.equals(name)){
                    headlinestv.setBackgroundColor(Color.parseColor("#eca140"));
                    headlinestv.setPadding(2, 2, 0, 0);
                    contentstv.setVisibility(View.GONE);
                    datetv.setTextColor(Color.BLACK);
                    box.setBackgroundColor(Color.BLACK);
                    belowbox.setBackgroundColor(Color.BLACK);
                    if (view_pos==0){
                        abovebox.setBackgroundColor(Color.BLACK);
                    }
                }else {

                }
                if (view_pos == 0 && headlines.size() == 1) {

                    belowbox.setVisibility(View.GONE);
                    abovebox.setVisibility(View.VISIBLE);

                } else if (view_pos == 0) {
                    abovebox.setVisibility(View.VISIBLE);

                } else if (view_pos == headlines.size()-1){
                    belowbox.setBackgroundResource(returnColor.colorint(col));
                    belowbox.setVisibility(View.GONE);
                    bottomlayout.setVisibility(View.VISIBLE);
                }

        return rowView;


    }


}