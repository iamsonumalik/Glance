package com.app.newsonrun;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Build;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

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

        try {
                SimpleDateFormat dateFormatGmt = new SimpleDateFormat("d MMM yyyy");
                String dateget = item_timeline_date;
                Date date = null;

                    date = sdf.parse(dateget);

                dateFormatGmt.setTimeZone(TimeZone.getDefault());
                String formattedDate = dateFormatGmt.format(date);
                datetv.setText(formattedDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
                headlinestv.setTypeface(head);
                contentstv.setTypeface(cont);
                datetv.setTypeface(cont);

        final String finalItem_timeline_public_id = item_timeline_public_id;
        timelinelayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Dialog dialog = new Dialog(_activity, android.R.style.Theme_Translucent_NoTitleBar);


                        dialog.setContentView(R.layout.timeline_dialog);
                        final ImageButton show = (ImageButton) dialog.findViewById(R.id.timelinedialogimageView);
                        LinearLayout close = (LinearLayout) dialog.findViewById(R.id.timelinedialogbackground);
                        close.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });

                        //Log.e("Prints", finalItem_headline);

                        final String imgageUrl = "http://res.cloudinary.com/innox-technologies/image/upload/c_scale,h_764,q_85/" + finalItem_timeline_public_id + ".jpg?_=4";
                        Picasso.with(context)
                                .load(imgageUrl)
                                .networkPolicy(NetworkPolicy.OFFLINE)
                                .into(show, new Callback.EmptyCallback() {
                                    @Override
                                    public void onSuccess() {

                                        Log.e("ERROR", "Its OFFLIne");
                                    }

                                    @Override
                                    public void onError() {
                                        //Try again online if cache failed
                                        Log.e("ERROR", "Its Online");
                                        Picasso.with(context)
                                                .load(imgageUrl)
                                                        //.error(R.drawable.header)
                                                .into(show, new Callback() {
                                                    @Override
                                                    public void onSuccess() {

                                                    }

                                                    @Override
                                                    public void onError() {
                                                        Log.v("Picasso", "Could not fetch image");
                                                    }
                                                });
                                    }
                                });

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