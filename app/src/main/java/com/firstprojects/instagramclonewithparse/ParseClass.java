package com.firstprojects.instagramclonewithparse;

import android.app.Application;

import com.parse.Parse;
//DON'T FORGET TO DELETE YOUR CODE ABOUT THE PARSE ATTACHED TO MANIFEST!
//WHEN YOUR PROJECT WAS FINISHED..
//DON'T FORGET TO DELETE YOUR CODE ABOUT THE PARSE ATTACHED TO MANIFEST!!
////DON'T FORGET TO DELETE YOUR CODE ABOUT THE PARSE ATTACHED TO MANIFEST!!!
//DON'T FORGET TO DELETE YOUR CODE ABOUT THE PARSE ATTACHED TO MANIFEST!!
//WHEN YOUR PROJECT WAS FINISHED..
//DON'T FORGET TO DELETE YOUR CODE ABOUT THE PARSE ATTACHED TO MANIFEST!
public class ParseClass extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Parse.setLogLevel(Parse.LOG_LEVEL_DEBUG);
        Parse.initialize(new Parse.Configuration.Builder(this)
        .applicationId("qiUll95xyEVYCHD7eoEIgxKH3RHZcMgOAgjMSTla")
        .clientKey("xTuKofEGZYX0uvXOgmBjqhc2XwyjI2PyVJQwJCsz")
        .server("https://parseapi.back4app.com/")
        .build());
    }
}
//DON'T FORGET TO DELETE YOUR CODE ABOUT THE PARSE ATTACHED TO MANIFEST!
//WHEN YOUR PROJECT WAS FINISHED..
//DON'T FORGET TO DELETE YOUR CODE ABOUT THE PARSE ATTACHED TO MANIFEST!!
////DON'T FORGET TO DELETE YOUR CODE ABOUT THE PARSE ATTACHED TO MANIFEST!!!
//DON'T FORGET TO DELETE YOUR CODE ABOUT THE PARSE ATTACHED TO MANIFEST!!
//WHEN YOUR PROJECT WAS FINISHED..
//DON'T FORGET TO DELETE YOUR CODE ABOUT THE PARSE ATTACHED TO MANIFEST!