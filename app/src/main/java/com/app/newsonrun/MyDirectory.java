package com.app.newsonrun;

import android.os.Environment;

import java.io.File;

/**
 * Created by malik on 24/2/16.
 */
public class MyDirectory {

    public File getDirectory(){
        File directory = null;
        if (Environment.getExternalStorageState() == null) {
            //create new file directory object
            directory = new File(Environment.getDataDirectory()
                    + "/Android/data/com.app.newsonrun/cache");


            /*
             * this checks to see if there are any previous test photo files
             * if there are any photos, they are deleted for the sake of
             * memory
             */
            // if no directory exists, create new directory
            if (!directory.exists()) {
                try {
                    directory.mkdirs();
                }catch (Exception e){

                }
            }

            // if phone DOES have sd card
        } else if (Environment.getExternalStorageState() != null) {
            // search for directory on SD card
            directory = new File(Environment.getExternalStorageDirectory()
                    + "/Android/data/com.app.newsonrun/cache");


            // if no directory exists, create new directory to store test
            // results
            if (!directory.exists()) {
                try {
                    directory.mkdirs();
                }catch (Exception e){

                }

            }
        }// end of SD card checking

        return directory;
    }
}
