package com.app.newsonrun;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by malik on 20/2/16.
 */
public class Tag_View extends ArrayAdapter {

    private final Context context;
    private final ArrayList<String> tagget;
    TextView tags;
    public Tag_View(Context context,
                    ArrayList<String> tagget
                       ) {
        super(context, R.layout.timeline_layout, tagget);
        this.context = context;
        this.tagget = tagget;
    }

    @Override
    public View getView(final int view_pos, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        final View rowView = inflater.inflate(R.layout.timeline_tags, parent, false);
        tags = (TextView) rowView.findViewById(R.id.tags);
        String item_tag = tagget.get(view_pos);
        tags.setText(item_tag);
        Typeface cont = Typeface.createFromAsset(context.getAssets(), "lodingfont.ttf");
        tags.setTypeface(cont);
        return rowView;


    }


}