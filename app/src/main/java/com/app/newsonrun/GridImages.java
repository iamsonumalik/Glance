package com.app.newsonrun;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import java.util.ArrayList;

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

        image_clips.add(53, BitmapFactory.decodeResource(getBaseContext().getResources(),
                R.drawable.a53));



        //Collections.shuffle(image_clips, new Random(seed));
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

    public Uri getUriPath(int i) {
        if (i==0)
        return Uri.parse("android.resource://" + baseContext.getPackageName() + "/" + R.drawable.a1);
        if (i==1)
            return Uri.parse("android.resource://" + baseContext.getPackageName() + "/" + R.drawable.a2);
        if (i==2)
            return Uri.parse("android.resource://" + baseContext.getPackageName() + "/" + R.drawable.a3);
        if (i==3)
            return Uri.parse("android.resource://" + baseContext.getPackageName() + "/" + R.drawable.a4);
        if (i==4)
            return Uri.parse("android.resource://" + baseContext.getPackageName() + "/" + R.drawable.a5);
        if (i==5)
            return Uri.parse("android.resource://" + baseContext.getPackageName() + "/" + R.drawable.a6);
        if (i==6)
            return Uri.parse("android.resource://" + baseContext.getPackageName() + "/" + R.drawable.a7);
        if (i==7)
            return Uri.parse("android.resource://" + baseContext.getPackageName() + "/" + R.drawable.a8);
        if (i==8)
            return Uri.parse("android.resource://" + baseContext.getPackageName() + "/" + R.drawable.a9);
        if (i==9)
            return Uri.parse("android.resource://" + baseContext.getPackageName() + "/" + R.drawable.a10);
        if (i==10)
            return Uri.parse("android.resource://" + baseContext.getPackageName() + "/" + R.drawable.a11);
        if (i==11)
            return Uri.parse("android.resource://" + baseContext.getPackageName() + "/" + R.drawable.a12);
        if (i==12)
            return Uri.parse("android.resource://" + baseContext.getPackageName() + "/" + R.drawable.a13);
        if (i==13)
            return Uri.parse("android.resource://" + baseContext.getPackageName() + "/" + R.drawable.a14);
        if (i==14)
            return Uri.parse("android.resource://" + baseContext.getPackageName() + "/" + R.drawable.a15);
        if (i==15)
            return Uri.parse("android.resource://" + baseContext.getPackageName() + "/" + R.drawable.a16);
        if (i==16)
            return Uri.parse("android.resource://" + baseContext.getPackageName() + "/" + R.drawable.a17);
        if (i==17)
            return Uri.parse("android.resource://" + baseContext.getPackageName() + "/" + R.drawable.a18);
        if (i==18)
            return Uri.parse("android.resource://" + baseContext.getPackageName() + "/" + R.drawable.a19);
        if (i==19)
            return Uri.parse("android.resource://" + baseContext.getPackageName() + "/" + R.drawable.a20);
        if (i==20)
            return Uri.parse("android.resource://" + baseContext.getPackageName() + "/" + R.drawable.a21);
        if (i==21)
            return Uri.parse("android.resource://" + baseContext.getPackageName() + "/" + R.drawable.a22);
        if (i==22)
            return Uri.parse("android.resource://" + baseContext.getPackageName() + "/" + R.drawable.a23);
        if (i==23)
            return Uri.parse("android.resource://" + baseContext.getPackageName() + "/" + R.drawable.a24);
        if (i==25)
            return Uri.parse("android.resource://" + baseContext.getPackageName() + "/" + R.drawable.a25);
        if (i==26)
            return Uri.parse("android.resource://" + baseContext.getPackageName() + "/" + R.drawable.a26);
        if (i==27)
            return Uri.parse("android.resource://" + baseContext.getPackageName() + "/" + R.drawable.a27);
        if (i==28)
            return Uri.parse("android.resource://" + baseContext.getPackageName() + "/" + R.drawable.a28);
        if (i==29)
            return Uri.parse("android.resource://" + baseContext.getPackageName() + "/" + R.drawable.a29);
        if (i==30)
            return Uri.parse("android.resource://" + baseContext.getPackageName() + "/" + R.drawable.a30);
        if (i==31)
            return Uri.parse("android.resource://" + baseContext.getPackageName() + "/" + R.drawable.a31);
        if (i==32)
            return Uri.parse("android.resource://" + baseContext.getPackageName() + "/" + R.drawable.a32);
        if (i==33)
            return Uri.parse("android.resource://" + baseContext.getPackageName() + "/" + R.drawable.a33);
        if (i==34)
            return Uri.parse("android.resource://" + baseContext.getPackageName() + "/" + R.drawable.a34);
        if (i==35)
            return Uri.parse("android.resource://" + baseContext.getPackageName() + "/" + R.drawable.a35);
        if (i==36)
            return Uri.parse("android.resource://" + baseContext.getPackageName() + "/" + R.drawable.a36);
        if (i==37)
            return Uri.parse("android.resource://" + baseContext.getPackageName() + "/" + R.drawable.a37);
        if (i==38)
            return Uri.parse("android.resource://" + baseContext.getPackageName() + "/" + R.drawable.a38);
        if (i==39)
            return Uri.parse("android.resource://" + baseContext.getPackageName() + "/" + R.drawable.a39);
        if (i==40)
            return Uri.parse("android.resource://" + baseContext.getPackageName() + "/" + R.drawable.a40);
        if (i==40)
            return Uri.parse("android.resource://" + baseContext.getPackageName() + "/" + R.drawable.a41);
        if (i==42)
            return Uri.parse("android.resource://" + baseContext.getPackageName() + "/" + R.drawable.a42);
        if (i==43)
            return Uri.parse("android.resource://" + baseContext.getPackageName() + "/" + R.drawable.a43);
        if (i==44)
            return Uri.parse("android.resource://" + baseContext.getPackageName() + "/" + R.drawable.a44);
        if (i==45)
            return Uri.parse("android.resource://" + baseContext.getPackageName() + "/" + R.drawable.a45);
        if (i==46)
            return Uri.parse("android.resource://" + baseContext.getPackageName() + "/" + R.drawable.a46);
        if (i==47)
            return Uri.parse("android.resource://" + baseContext.getPackageName() + "/" + R.drawable.a47);
        if (i==48)
            return Uri.parse("android.resource://" + baseContext.getPackageName() + "/" + R.drawable.a48);
        if (i==49)
            return Uri.parse("android.resource://" + baseContext.getPackageName() + "/" + R.drawable.a49);
        if (i==50)
            return Uri.parse("android.resource://" + baseContext.getPackageName() + "/" + R.drawable.a50);
        if (i==51)
            return Uri.parse("android.resource://" + baseContext.getPackageName() + "/" + R.drawable.a51);
        if (i==52)
            return Uri.parse("android.resource://" + baseContext.getPackageName() + "/" + R.drawable.a52);
        if (i==53)
            return Uri.parse("android.resource://" + baseContext.getPackageName() + "/" + R.drawable.a53);
        else
            return Uri.parse("android.resource://" + baseContext.getPackageName() + "/" + R.drawable.a54);
    }
}
