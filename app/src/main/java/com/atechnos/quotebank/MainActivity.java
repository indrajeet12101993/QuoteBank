package com.atechnos.quotebank;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 5000;
    ProgressBar progressBar;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        progressBar = new ProgressBar(this);
        sessionManager = new SessionManager(this);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                progressBar.setVisibility(View.INVISIBLE);
                if (sessionManager.isLoggedIn() == true) {
                    Intent i = new Intent(MainActivity.this, FinalActivity.class);
                    i.putExtra("quotes", sessionManager.getQuotesSleep());
                    startActivity(i);
                } else {
                    Intent i = new Intent(MainActivity.this, Setting_Select_Activity.class);
                     i.putExtra("act", "time");
                    startActivity(i);
                }
                // close this activity
                finish();

            }
        }, SPLASH_TIME_OUT);
    }
}
