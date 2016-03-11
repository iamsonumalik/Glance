package com.app.newsonrun;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by malik on 7/3/16.
 */
public class GridImages {
    private final Context baseContext;
    private final ArrayList<Bitmap> image_clips;

    public GridImages(Context baseContext) {
        this.baseContext = baseContext;
        image_clips = new ArrayList<Bitmap>();
    }

    public ArrayList<Bitmap> getArrayBitmap() {
        long seed = System.nanoTime();


        image_clips.add(0,BitmapFactory.decodeResource(baseContext.getResources(),
                R.drawable.a1));

        image_clips.add(1,BitmapFactory.decodeResource(baseContext.getResources(),
                R.drawable.a2));
        image_clips.add(2,BitmapFactory.decodeResource(baseContext.getResources(),
                R.drawable.a3));

        image_clips.add(3,BitmapFactory.decodeResource(baseContext.getResources(),
                R.drawable.a4));

        image_clips.add(4,BitmapFactory.decodeResource(baseContext.getResources(),
                R.drawable.a5));
        image_clips.add(5,BitmapFactory.decodeResource(baseContext.getResources(),
                R.drawable.a6));
        image_clips.add(6,BitmapFactory.decodeResource(baseContext.getResources(),
                R.drawable.a7));

        image_clips.add(7,BitmapFactory.decodeResource(baseContext.getResources(),
                R.drawable.a8));

        image_clips.add(8,BitmapFactory.decodeResource(baseContext.getResources(),
                R.drawable.a9));
        image_clips.add(9,BitmapFactory.decodeResource(baseContext.getResources(),
                R.drawable.a10));
        image_clips.add(10,BitmapFactory.decodeResource(baseContext.getResources(),
                R.drawable.a11));
        image_clips.add(11,BitmapFactory.decodeResource(baseContext.getResources(),
                R.drawable.a12));
        image_clips.add(12,BitmapFactory.decodeResource(baseContext.getResources(),
                R.drawable.a13));
        image_clips.add(13,BitmapFactory.decodeResource(baseContext.getResources(),
                R.drawable.a14));

        image_clips.add(14,BitmapFactory.decodeResource(baseContext.getResources(),
                R.drawable.a15));

        image_clips.add(15,BitmapFactory.decodeResource(baseContext.getResources(),
                R.drawable.a16));

        image_clips.add(16,BitmapFactory.decodeResource(baseContext.getResources(),
                R.drawable.a17));

        image_clips.add(17,BitmapFactory.decodeResource(baseContext.getResources(),
                R.drawable.a18));

        image_clips.add(18,BitmapFactory.decodeResource(baseContext.getResources(),
                R.drawable.a19));

        image_clips.add(19,BitmapFactory.decodeResource(baseContext.getResources(),
                R.drawable.a20));

        image_clips.add(20,BitmapFactory.decodeResource(baseContext.getResources(),
                R.drawable.a21));

        image_clips.add(21,BitmapFactory.decodeResource(baseContext.getResources(),
                R.drawable.a22));

        image_clips.add(22,BitmapFactory.decodeResource(baseContext.getResources(),
                R.drawable.a23));

        image_clips.add(23,BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a24));
        image_clips.add(24,BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a54));

        image_clips.add(25,BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a25));

        image_clips.add(26,BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a26));

        image_clips.add(27,BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a27));
        image_clips.add(28,BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a28));

        image_clips.add(29,BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a29));

        image_clips.add(30,BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a30));

        image_clips.add(31,BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a31));

        image_clips.add(32,BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a32));

        image_clips.add(33,BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a33));

        image_clips.add(34,BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a34));

        image_clips.add(35,BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a35));

        image_clips.add(36,BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a36));

        image_clips.add(37,BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a37));

        image_clips.add(38,BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a38));

        image_clips.add(39,BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a39));

        image_clips.add(40,BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a40));

        image_clips.add(41,BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a41));

        image_clips.add(42,BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a42));

        image_clips.add(43,BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a43));

        image_clips.add(44,BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a44));

        image_clips.add(45,BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a45));

        image_clips.add(46,BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a46));

        image_clips.add(47,BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a47));

        image_clips.add(48,BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a48));

        image_clips.add(49,BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a49));

        image_clips.add(50,BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a50));

        image_clips.add(51,BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a51));
        image_clips.add(52,BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a52));

        image_clips.add(53,BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a53));



        Collections.shuffle(image_clips, new Random(seed));
        return image_clips;

    }

    public Context getBaseContext() {
        return baseContext;
    }
    public void freeBitmaps() {
        for(Bitmap image: image_clips) {
            // also, it's better to check whether it is recycled or not
            if(image != null && !image.isRecycled()) {
                image.recycle();
                image = null; // null reference
            }
        }
    }

}
