package com.app.newsonrun;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;

import com.app.newsonrun.gcm.GCMBaseIntentService;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Random;

public class GCMIntentService extends GCMBaseIntentService {

	private static final String TAG = "GCMIntentService";
    private static final String MY_PREFS_NAME = "MySetting";
	private Controller aController = null;

    public GCMIntentService() {
    	// Call extended class Constructor GCMBaseIntentService
        super(Config.GOOGLE_SENDER_ID);
    }

    /**
     * Method called on device registered
     **/
    @Override
    protected void onRegistered(Context context, String registrationId) {
    	
    	//Get Global Controller Class object (see application tag in AndroidManifest.xml)
    	if(aController == null)
           aController = (Controller) getApplicationContext();
    	
        Log.i(TAG, "Device registered: regId = " + registrationId);
        aController.displayMessageOnScreen(context, "Your device registred with GCM");
        //Log.d("NAME", GCMMainActivity.name);
        String accesstoken = null;
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, 0);

        accesstoken  = prefs.getString("token", "");
        aController.register(context, accesstoken, registrationId);
    }

    /**
     * Method called on device unregistred
     * */
    @Override
    protected void onUnregistered(Context context, String registrationId) {
    	if(aController == null)
            aController = (Controller) getApplicationContext();
        Log.i(TAG, "Device unregistered");
        aController.displayMessageOnScreen(context, getString(R.string.gcm_unregistered));
        String accesstoken = null;


        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, 0);

        accesstoken  = prefs.getString("token", "");

        aController.unregister(context, accesstoken, registrationId);
    }

    /**
     * Method called on Receiving a new message from GCM server
     * */
    @Override
    protected void onMessage(Context context, Intent intent) {

        if (aController == null)
            aController = (Controller) getApplicationContext();

        Log.i(TAG, "Received message");
        String title = intent.getExtras().getString("title");
        String body = intent.getExtras().getString("body");
        String sound = intent.getExtras().getString("sound");
        String clickAction = intent.getExtras().getString("clickAction");
        String icon = intent.getExtras().getString("icon");
        String tag = intent.getExtras().getString("tag");
        String color = intent.getExtras().getString("color");



        aController.displayMessageOnScreen(context, title);
        // notifies user

        try {
            JSONObject json_data = new JSONObject(icon);
            icon = json_data.getString("secure_url");
        }catch (Exception e){

        }

        try {
            Log.e("body", body);
            if (sound.contains("breaking.wav")) {

                new InsertUpdate(clickAction,getBaseContext(),"breaking.wav",title,body,icon).execute();

            }else {
                generateNotificationNews(context, title, body, sound, icon, clickAction, tag, color);
                try {
                    Uri no = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.notification_tone);
                    Ringtone rp = RingtoneManager.getRingtone(getApplicationContext(), no);
                    rp.play();
                    Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                    // Vibrate for 500 milliseconds
                    v.vibrate(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }catch (Exception e){
            try {
                String type = intent.getExtras().getString("type");
                String newsPost = intent.getExtras().getString("newsPost");
                generateNotificationUpdate(context, title, type, newsPost);
            }catch (Exception e2){
                Log.e("Notification",e2.toString());
            }
        }

    }

    private void generateNotificationUpdate(Context context, String title, String type, String newsPost) {
        deletePost(newsPost);
        if (type.equals("UPDATE")){
            new InsertUpdate(newsPost,getBaseContext(), "", title, "","").execute();
        }
    }

    private void deletePost(String newsPost) {
        SavingPublicId savingPublicId = new SavingPublicId(getBaseContext());
        SavingYoutubeLink savingYoutubeLink = new SavingYoutubeLink(getBaseContext());
        try {
            //Delete From Database
            savingYoutubeLink.open();
            savingPublicId.open();
            String p_id = savingPublicId.getp_id(newsPost);
            savingPublicId.deletePost(newsPost);
            savingYoutubeLink.deleteLink(p_id);
            savingPublicId.close();
            savingYoutubeLink.close();

            //Delete Cache image if exists
            MyDirectory myDirectory = new MyDirectory();
            File directory = myDirectory.getDirectory();
            try {
                new File(directory, p_id+".png").delete();
                Log.e("Deleted", p_id);
            }catch (Exception e){

            }
        }catch (Exception e){

        }
    }

    /**
     * Method called on receiving a deleted message
     * */
    @Override
    protected void onDeletedMessages(Context context, int total) {
    	
    	if(aController == null)
            aController = (Controller) getApplicationContext();
    	
        Log.i(TAG, "Received deleted messages notification");
        String message = getString(R.string.gcm_deleted, total);
        aController.displayMessageOnScreen(context, message);
        // notifies user

    }

    /**
     * Method called on Error
     * */
    @Override
    public void onError(Context context, String errorId) {
    	
    	if(aController == null)
            aController = (Controller) getApplicationContext();

        Log.i(TAG, "Received error: " + errorId);
        aController.displayMessageOnScreen(context, getString(R.string.gcm_error, errorId));
    }

    @Override
    protected boolean onRecoverableError(Context context, String errorId) {
    	
    	if(aController == null)
            aController = (Controller) getApplicationContext();
    	
        // log message
        Log.i(TAG, "Received recoverable error: " + errorId);
        aController.displayMessageOnScreen(context, getString(R.string.gcm_recoverable_error,
                errorId));
        return super.onRecoverableError(context, errorId);
    }

    /**
     * Create a notification to inform the user that server has sent a message.
     */
    private void generateNotificationNews(Context context, String title, String body, String sound, String icon, String clickAction, String tag, String color) {


        // Open NotificationView Class on Notification Click


            RemoteViews remoteViews = new RemoteViews(getPackageName(),
                    R.layout.customnotification);
            Intent intent;
            intent = new Intent(this, MainActivity.class);
            intent.putExtra("fromnoti",true);

            PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent,
                    PendingIntent.FLAG_UPDATE_CURRENT);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                    // Set Icon
                    .setSmallIcon(R.drawable.splash)
                            // Set Ticker Message
                    .setTicker(title)
                            // Dismiss Notification
                    .setAutoCancel(true)
                            // Set PendingIntent into Notification
                    .setContentIntent(pIntent)
                                    // Set RemoteViews into Notification
                    .setContent(remoteViews);

            builder.setPriority(Notification.PRIORITY_HIGH);
            if (Build.VERSION.SDK_INT >= 21) builder.setVibrate(new long[0]);

            remoteViews.setImageViewResource(R.id.imagenotileft, R.drawable.digest);
            try {
                Bitmap remote_picture = BitmapFactory.decodeStream(
                        (InputStream) new URL(icon).getContent());
                remoteViews.setImageViewBitmap(R.id.imagenotiright, remote_picture);

            } catch (IOException e) {
                e.printStackTrace();
            }
            remoteViews.setTextViewText(R.id.title,"");
            remoteViews.setTextViewText(R.id.text, body);
            remoteViews.setTextColor(R.id.title, Color.BLACK);
            remoteViews.setTextColor(R.id.text, Color.rgb(209,211,212));
            // Create Notification Manager
            NotificationManager notificationmanager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            Random r = new Random();
            Notification notification = builder.build();
            int x = r.nextInt(1000000);
            notification.bigContentView = remoteViews;
            notificationmanager.notify(x,notification );
        // Send data to NotificationView Class

        // Open MainActivity.java Activity


    }


}
