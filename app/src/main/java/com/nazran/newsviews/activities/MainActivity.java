package com.nazran.newsviews.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;

import com.nazran.newsviews.R;
import com.nazran.newsviews.app.MyAppPrefsManager;
import com.nazran.newsviews.utils.Utilities;

public class MainActivity extends AppCompatActivity {

    MyTask myTask;
    MyAppPrefsManager myAppPrefsManager;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        myAppPrefsManager = new MyAppPrefsManager(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                myTask = new MyTask();
                myTask.execute();
            }
        }, 1000);
    }

    private class MyTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {

            // Check for Internet Connection from the static method of Helper class
            if (Utilities.isNetworkAvailable(MainActivity.this)) {

                return "1";
            } else {
                return "0";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if (result.equalsIgnoreCase("0")) {

                progressBar.setVisibility(View.GONE);

                // No Internet Connection
                Snackbar.make(progressBar, getString(R.string.no_internet), Snackbar.LENGTH_INDEFINITE)
                        .setAction(getString(R.string.retry), new View.OnClickListener() {

                            // Handle the Retry Button Click
                            @Override
                            public void onClick(View v) {

                                progressBar.setVisibility(View.VISIBLE);

                                // Restart MyTask after 3 seconds
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        myTask = new MyTask();
                                        myTask.execute();
                                    }
                                }, 3000);
                            }
                        })
                        .show();

            }
            else {

                if (myAppPrefsManager.isFirstTimeLaunch()) {
                    // Navigate to SplashScreen
                    startActivity(new Intent(getBaseContext(), SplashScreen.class));
                    finish();
                }
                else {
                    // Navigate to HomeActivity
                    startActivity(new Intent(getBaseContext(), HomeActivity.class));
                    finish();
                }
            }
        }
    }
}
