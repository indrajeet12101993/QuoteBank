package com.atechnos.quotebank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class FinalActivity extends AppCompatActivity {
    String quotes, author;
    TextView tvQuotes, tvAuthor;
    ImageView ivTiming, ivInterest, ivAbout;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_final);
        Intent intent = getIntent();
        sessionManager = new SessionManager(this);
        quotes = intent.getStringExtra("quotes");
        // author = intent.getStringExtra("author");
        tvAuthor = (TextView) findViewById(R.id.tvAuthor);
        tvQuotes = (TextView) findViewById(R.id.tvQuotes);
        ivTiming = (ImageView) findViewById(R.id.ivTiming);
        ivInterest = (ImageView) findViewById(R.id.ivInterest);
        ivAbout = (ImageView) findViewById(R.id.ivAbout);
        sessionManager.setAuthorSleep("set");
        Log.e("Hello", "niti----" + quotes);
//        StringTokenizer tokens = new StringTokenizer(quotes, "|");
//        String first = tokens.nextToken();// this will contain "Fruit"
//        String second = tokens.nextToken();
        String ap[] = quotes.split(":");
        tvQuotes.setText("\"" + ap[0] + "\"");
        tvAuthor.setText("-" + ap[1] + "-");



        final ArrayList<String> quotesAray;
        quotesAray = AppController.getInstance().getQuotes();
        AppController.getInstance().setQutoes(quotesAray);


        //authorArray = AppController.getInstance().getAuthor();

        ivTiming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(FinalActivity.this, TimerActivity.class);
//                in.putExtra("SELECTED_LETTER", quotesAray);
//                in.putExtra("time", quotes);
                // in.putExtra("arrayAuthor", authorArray);
                startActivity(in);
                finish();
            }
        });
        ivInterest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FinalActivity.this, Setting_Select_Activity.class);
                i.putExtra("act", quotes);
                startActivity(i);
                finish();
            }
        });

        ivAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FinalActivity.this, AboutActivity.class);
                startActivity(i);
            }
        });
    }
}
