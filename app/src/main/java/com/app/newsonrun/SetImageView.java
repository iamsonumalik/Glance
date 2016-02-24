package com.app.newsonrun;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.Toast;

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

    SetImageView(final ImageView imageView, String name, Context getcontext, final File file){
        this.imageView = imageView;
        this.name = name;
        this.getcontext = getcontext;
        this.file = file;

        new  SaveImage().execute();



    }
    class SaveImage extends AsyncTask<String ,String,String>{

        @Override
        protected String doInBackground(String... params) {
            final String imgageUrl = "http://res.cloudinary.com/innox-technologies/image/upload/c_scale,h_764,q_85/" + name + ".jpg";
            try {
                InputStream in = new java.net.URL(imgageUrl).openStream();
                Bitmap mIcon11 = BitmapFactory.decodeStream(in);
                try {
                    imageView.setImageBitmap(mIcon11);
                }catch (Exception e){

                }

                FileOutputStream ourstream = new FileOutputStream(file);
                mIcon11.compress(Bitmap.CompressFormat.JPEG, 85, ourstream);
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
