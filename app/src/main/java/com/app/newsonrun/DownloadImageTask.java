package com.app.newsonrun;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by malik on 18/9/15.
 */
public class DownloadImageTask{
    String phone;
    Activity get;
    private ImageView bmImage;
    String name;
    private String filepath;

    public DownloadImageTask(Activity getactivit,String item_time) {
        this.phone = item_time;
        this.get  = getactivit;


        Uri bmpUri = null;
        try {
            MyDirectory myDirectory = new MyDirectory();
            File SDCardRoot = myDirectory.getDirectory();
            String filename=phone+".jpg";
            Log.i("Local filename:",""+filename);
            File file = new File(SDCardRoot,filename);
            if (!file.exists()){
               //downloadnow();
            }else {
                bmpUri = Uri.fromFile(file);
                if (bmpUri != null) {
                    // Construct a ShareIntent with link to image
                    Intent shareIntent = new Intent();
                    shareIntent.setAction(Intent.ACTION_SEND);
                    shareIntent.putExtra(Intent.EXTRA_STREAM, bmpUri);
                    shareIntent.setType("image/*");
                    // Launch sharing dialog for image
                    get.startActivity(Intent.createChooser(shareIntent, "Share Image"));
                } else {
                    Log.e("EC", "Empf");
                    // ...sharing failed, handle error
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void downloadnow() {

        Picasso.with(get)
                .load("http://res.cloudinary.com/innox-technologies/image/upload/c_scale,h_764,q_85/" + phone + ".jpg?_=4")
                .into(new Target() {
                    @Override
                    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom loadedFrom) {
                        try {
                            File SDCardRoot = Environment.getExternalStorageDirectory().getAbsoluteFile();
                            String filename = phone + ".png";
                            Log.i("Local filename:", "" + filename);
                            File file = new File(SDCardRoot, filename);
                            FileOutputStream out = new FileOutputStream(file);
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
                            out.flush();
                            out.close();
                            Uri bmpUri = Uri.fromFile(file);
                            if (bmpUri != null) {
                                // Construct a ShareIntent with link to image
                                Intent shareIntent = new Intent();
                                shareIntent.setAction(Intent.ACTION_SEND);
                                shareIntent.putExtra(Intent.EXTRA_STREAM, bmpUri);
                                shareIntent.setType("image/*");
                                // Launch sharing dialog for image
                                get.startActivity(Intent.createChooser(shareIntent, "Share Image"));
                            } else {
                                Log.e("EC", "Empf");
                                // ...sharing failed, handle error
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
    }

}