package com.app.newsonrun;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by malik on 23/2/16.
 */
public class SetImageView {
    private final ImageView imageView;
    private final String name;
    private final Context getcontext;
    private final File file;

    SetImageView(final ImageView imageView, final String name, final Context getcontext, final File file){
        this.imageView = imageView;
        this.name = name;
        this.getcontext = getcontext;
        this.file = file;

        final String imgageUrl =getcontext.getResources().getString(R.string.url) + name + ".png";
        Picasso.with(getcontext)
                .load(imgageUrl)
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom loadedFrom) {
                        try {
                            FileOutputStream ourstream = new FileOutputStream(file);
                            bitmap.compress(Bitmap.CompressFormat.PNG, 2, ourstream);
                            ourstream.flush();
                            ourstream.close();

                            try {
                            SavingPublicId savingPublicId = new SavingPublicId(getcontext);
                                SavingCache savingCache = new SavingCache(getcontext);
                                savingPublicId.open();
                                savingCache.open();
                                savingCache.createEntry(1, name, savingPublicId.gettimestampcreated(name));
                                savingCache.close();
                                savingPublicId.close();
                                Log.e("Saved", "Done");
                            }catch (Exception e){

                            }
                        }catch (Exception e){

                        }
                    }

                    @Override
                    public void onBitmapFailed(Drawable drawable) {

                    }

                    @Override
                    public void onPrepareLoad(Drawable drawable) {

                    }
                });
        //new  SaveImage().execute();



    }
    class SaveImage extends AsyncTask<String ,String,String>{

        @Override
        protected String doInBackground(String... params) {
            final String imgageUrl = getcontext.getResources().getString(R.string.url) + name + ".png";
            try {
                InputStream in = new java.net.URL(imgageUrl).openStream();
                Bitmap mIcon11 = BitmapFactory.decodeStream(in);
                try {
                    imageView.setImageBitmap(mIcon11);
                }catch (Exception e){

                }

                FileOutputStream ourstream = new FileOutputStream(file);
                mIcon11.compress(Bitmap.CompressFormat.PNG, 2, ourstream);
                ourstream.flush();
                ourstream.close();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                Toast.makeText(getcontext, e.toString(), Toast.LENGTH_LONG).show();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                Toast.makeText(getcontext, e.toString(), Toast.LENGTH_LONG).show();
            } catch (Exception e) {

            }
            return null;
        }
    }
}
