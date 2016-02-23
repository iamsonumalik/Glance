package com.app.newsonrun;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.app.newsonrun.gcm.GCMRegistrar;


public class Storinggcm {

    // label to display gcm messages

    Controller aController;

    // Asyntask
    AsyncTask<Void, Void, Void> mRegisterTask;

    String accesstoken;

    Activity getact;
    public Storinggcm(Activity getactivity,String accesstoken) {

        this.accesstoken = accesstoken;
        getact=getactivity;
        aController = (Controller) getactivity.getApplicationContext();


        // Check if Internet present
        if (!aController.isConnectingToInternet()) {
            Toast.makeText(getactivity, "No Internet", Toast.LENGTH_SHORT).show();
            // stop executing code by return
            return;
        }

        // Getting name, email from intent


        // Make sure the device has the proper dependencies.
        GCMRegistrar.checkDevice(getactivity);

        // Make sure the manifest permissions was properly set
        GCMRegistrar.checkManifest(getactivity);


        final String regId = GCMRegistrar.getRegistrationId(getactivity);

        // Check if regid already presents
        if (regId.equals("")) {

            // Register with GCM
            GCMRegistrar.register(getactivity, Config.GOOGLE_SENDER_ID);

        } else {

            // Device is already registered on GCM Server


            // Try to register again, but not in the UI thread.
            // It's also necessary to cancel the thread onDestroy(),
            // hence the use of AsyncTask instead of a raw thread.

            final Context context = getactivity;

                    // Register on our server
                    // On server creates a new user
                    aController.register(context, accesstoken, regId);

        }

    }


}