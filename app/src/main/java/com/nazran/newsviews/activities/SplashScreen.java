package com.nazran.newsviews.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

import com.github.paolorotolo.appintro.AppIntro;
import com.nazran.newsviews.R;
import com.nazran.newsviews.app.MyAppPrefsManager;
import com.nazran.newsviews.fragments.SplashOne;
import com.nazran.newsviews.fragments.SplashThree;
import com.nazran.newsviews.fragments.SplashTwo;


/**
 * SplashScreen activity, appears only when the App starts for the very first time
 **/


public class SplashScreen extends AppIntro {

    MyAppPrefsManager myAppPrefsManager;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    
    
        myAppPrefsManager = new MyAppPrefsManager(SplashScreen.this);
        
    
        addSlide(new SplashOne());
        addSlide(new SplashTwo());
        addSlide(new SplashThree());
    
    
        // Hide StatusBar
        showStatusBar(false);
        showSkipButton(true);
        setProgressButtonEnabled(true);
    
        setBarColor(ContextCompat.getColor(SplashScreen.this, R.color.white));
        setSeparatorColor(ContextCompat.getColor(SplashScreen.this, R.color.colorPrimaryLight));
        
        setColorDoneText(ContextCompat.getColor(SplashScreen.this, R.color.colorPrimary));
        setColorSkipButton(ContextCompat.getColor(SplashScreen.this, R.color.colorPrimary));
        setNextArrowColor(ContextCompat.getColor(SplashScreen.this, R.color.colorPrimary));
        
        setIndicatorColor(ContextCompat.getColor(SplashScreen.this, R.color.colorPrimary),
                            ContextCompat.getColor(SplashScreen.this, R.color.iconsLight));
    }
    
    
    
    //*********** Called when the Skip Button pressed on SplashScreen ********//
    
    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
    
        if (myAppPrefsManager.isFirstTimeLaunch()) {
            // Navigate to HomeActivity
            startActivity(new Intent(SplashScreen.this, HomeActivity.class));
            myAppPrefsManager.setFirstTimeLaunch(false);
            finish();
            overridePendingTransition(R.anim.enter_from_right, R.anim.exit_out_right);
        }
        else {
            // Finish this Activity
            finish();
        }
        
    }
    
    
    
    //*********** Called when the Done Button pressed on SplashScreen ********//
    
    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        
        if (myAppPrefsManager.isFirstTimeLaunch()) {
            // Navigate to HomeActivity
            startActivity(new Intent(SplashScreen.this, HomeActivity.class));
            myAppPrefsManager.setFirstTimeLaunch(false);
            finish();
            overridePendingTransition(R.anim.enter_from_right, R.anim.exit_out_right);
        }
        else {
            // Finish this Activity
            finish();
        }
    }
    
    
    
    //*********** Called when the active Slide Changes ********//
    
    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
        // Do something when the slide changes.
    }
    
    
    
    //*********** Called when the Activity has detected the User pressed the Back key ********//
    
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    
        if (myAppPrefsManager.isFirstTimeLaunch()) {
            // Navigate to HomeActivity
            startActivity(new Intent(SplashScreen.this, HomeActivity.class));
            myAppPrefsManager.setFirstTimeLaunch(false);
            finish();
            overridePendingTransition(R.anim.enter_from_right, R.anim.exit_out_right);
        }
        else {
            // Finish this Activity
            finish();
        }
        
    }
    
}

