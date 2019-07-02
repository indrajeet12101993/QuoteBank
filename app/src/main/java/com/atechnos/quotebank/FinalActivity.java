package com.atechnos.quotebank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class FinalActivity extends AppCompatActivity {
    String quotes, author;
    TextView tvQuotes, tvAuthor;
    ImageView ivTiming, ivInterest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_final);
        Intent intent = getIntent();
        quotes = intent.getStringExtra("quotes");
        author = intent.getStringExtra("author");
        tvAuthor = (TextView) findViewById(R.id.tvAuthor);
        tvQuotes = (TextView) findViewById(R.id.tvQuotes);
        ivTiming = (ImageView) findViewById(R.id.ivTiming);
        ivInterest = (ImageView) findViewById(R.id.ivInterest);
        tvAuthor.setText("-" + author + "-");
        tvQuotes.setText("\"" + quotes + "\"");
        final String[] quotesAray, authorArray;
        quotesAray = AppController.getInstance().getQuotes();
        authorArray = AppController.getInstance().getAuthor();

        ivTiming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(FinalActivity.this, TimerActivity.class);
                in.putExtra("arrayQuotes", quotesAray);
                in.putExtra("arrayAuthor", authorArray);
                startActivity(in);
                finish();
            }
        });
        ivInterest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FinalActivity.this, Setting_Select_Activity.class));
                finish();
            }
        });
    }
}
