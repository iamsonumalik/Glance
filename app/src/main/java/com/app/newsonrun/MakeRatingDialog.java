package com.app.newsonrun;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by malik on 27/2/16.
 */
public class MakeRatingDialog{
    public MakeRatingDialog(final Activity allCategory, final SharedPreferences.Editor editor, boolean didyes) {

        if (didyes){
            rateUs(allCategory,editor);
        }else {
            AlertDialog.Builder dialog = new AlertDialog.Builder(allCategory);
            LayoutInflater inflater = (LayoutInflater) allCategory.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            Typeface cont = Typeface.createFromAsset(allCategory.getAssets(), "content.otf");
            Typeface head = Typeface.createFromAsset(allCategory.getAssets(), "lodingfont.ttf");
            View someLayout = inflater.inflate(R.layout.satisfactorydailog, null);
            TextView des = (TextView) someLayout.findViewById(R.id.satisfydescription);
            TextView no = (TextView) someLayout.findViewById(R.id.no);
            TextView dailogtitle = (TextView) someLayout.findViewById(R.id.satisfytitle);
            TextView yes = (TextView) someLayout.findViewById(R.id.yes);
            dialog.setCancelable(false);
            dailogtitle.setTypeface(head);
            yes.setTypeface(head);
            no.setTypeface(head);
            des.setTypeface(head);
            dialog.setView(someLayout);

            final AlertDialog alertDialog = dialog.create();
            yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Controller.getInstance().trackEvent("Like the App", "YES", "user");
                    editor.putBoolean("didyes", true);
                    editor.commit();
                    rateUs(allCategory, editor);
                    alertDialog.dismiss();
                }
            });
            no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Controller.getInstance().trackEvent("DisLike the App", "NO", "user");
                    editor.putBoolean("israted", true);
                    editor.commit();
                    alertDialog.dismiss();
                    sumbitFeedback(allCategory,editor);
                }
            });


            alertDialog.show();
        }

    }

    private void sumbitFeedback(Activity allCategory, SharedPreferences.Editor editor) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(allCategory);
        LayoutInflater inflater = (LayoutInflater) allCategory.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Typeface cont = Typeface.createFromAsset(allCategory.getAssets(), "content.otf");
        Typeface head = Typeface.createFromAsset(allCategory.getAssets(), "lodingfont.ttf");
        View someLayout = inflater.inflate(R.layout.sumbitdialog, null);
        TextView submittitle = (TextView) someLayout.findViewById(R.id.submittitle);
        TextView submitdes = (TextView) someLayout.findViewById(R.id.submitdes);
        EditText feed = (EditText) someLayout.findViewById(R.id.feed);
        TextView submitfeedback = (TextView) someLayout.findViewById(R.id.submitfeedback);

        feed.setTypeface(head);
        submitfeedback.setTypeface(head);
        submitdes.setTypeface(head);
        submittitle.setTypeface(head);
        dialog.setView(someLayout);

        final AlertDialog alertDialog = dialog.create();
        submitfeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }

        });



        alertDialog.show();

    }

    private void rateUs(final Activity allCategory,  final SharedPreferences.Editor editor) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(allCategory);
        LayoutInflater inflater = (LayoutInflater) allCategory.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Typeface cont = Typeface.createFromAsset(allCategory.getAssets(), "content.otf");
        Typeface head = Typeface.createFromAsset(allCategory.getAssets(), "lodingfont.ttf");
        View someLayout = inflater.inflate(R.layout.rateusdialog, null);
        TextView des = (TextView) someLayout.findViewById(R.id.rateusdescription);
        TextView rateuslater = (TextView) someLayout.findViewById(R.id.rateuslater);
        TextView dailogtitle = (TextView) someLayout.findViewById(R.id.rateustitle);
        TextView yestakeme = (TextView) someLayout.findViewById(R.id.yestakeme);

        dailogtitle.setTypeface(head);
        yestakeme.setTypeface(head);
        rateuslater.setTypeface(head);
        des.setTypeface(head);
        dialog.setView(someLayout);

        final AlertDialog alertDialog = dialog.create();
        yestakeme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putBoolean("israted",true);
                editor.commit();
                final String appPackageName = allCategory.getPackageName(); // getPackageName() from Context or Activity object
                try {
                    allCategory.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    allCategory.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }
            }

        });
        rateuslater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertDialog.dismiss();

            }
        });


        alertDialog.show();

    }
}
