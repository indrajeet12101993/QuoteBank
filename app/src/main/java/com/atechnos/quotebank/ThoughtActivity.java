package com.atechnos.quotebank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;

import com.atechnos.quotebank.Adapter.ThoughtsGridViewAdpater;

public class ThoughtActivity extends AppCompatActivity {
    ListView lvThought;
    ImageView imageView8, imageView9;
    String[] arrayArtsQuotes, arrayArtsAuthor, arrayFitnessQuotes, arrayMusicQuotes, arrayMusicAuthor, arrayFitnessAuthor, arrayBusiQuotes, arrayBusiAuthor, arrayMovieAuthor,
            arrayMovieQuotes, arrayGeneralQuotes, arrayGeneralAuthor, sendArrayQuotes, sendArrayAuthor, arrayPoiliticsAuthor, arrayPoliticsQuotes,arrayReadWriterQuotes,
            arrayReadWriterAuthor, arraySportQuotes, arraySportAuthor, arrayTechQuotes, arrayTechAuthor, arrayFoodQuotes, arrayFoodAuthor;
    String selectValue, act;
    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_thought);
        sessionManager = new SessionManager(this);
        Intent intent = getIntent();
        selectValue = intent.getStringExtra("select");
        // act = intent.getStringExtra("act");
        setFindView();
        setSetOnClickListeners();
        if (selectValue.equalsIgnoreCase("General")) {
            setListValue(arrayGeneralQuotes, arrayGeneralAuthor);
        } else if (selectValue.equalsIgnoreCase("Sports")) {
            setListValue(arraySportQuotes, arraySportAuthor);
            // ThoughtsGridViewAdpater adapterViewAndroid = new ThoughtsGridViewAdpater(ThoughtActivity.this, arrayFitnessQuotes, arrayFitnessAuthor);
        } else if (selectValue.equalsIgnoreCase("Music")) {
            setListValue(arrayMusicQuotes, arrayMusicAuthor);
            //ThoughtsGridViewAdpater adapterViewAndroid = new ThoughtsGridViewAdpater(ThoughtActivity.this, arrayFitnessQuotes, arrayFitnessAuthor);
        } else if (selectValue.equalsIgnoreCase("Tech")) {
            setListValue(arrayTechQuotes, arrayTechAuthor);
            // ThoughtsGridViewAdpater adapterViewAndroid = new ThoughtsGridViewAdpater(ThoughtActivity.this, arrayFitnessQuotes, arrayFitnessAuthor);
        } else if (selectValue.equalsIgnoreCase("Politics")) {
            setListValue(arrayPoliticsQuotes, arrayPoiliticsAuthor);
            // ThoughtsGridViewAdpater adapterViewAndroid = new ThoughtsGridViewAdpater(ThoughtActivity.this, arrayFitnessQuotes, arrayFitnessAuthor);
        } else if (selectValue.equalsIgnoreCase("Movies")) {
            setListValue(arrayMovieQuotes, arrayMovieAuthor);
        } else if (selectValue.equalsIgnoreCase("Reading/Writing")) {
            setListValue(arrayReadWriterQuotes, arrayReadWriterAuthor);
            //ThoughtsGridViewAdpater adapterViewAndroid = new ThoughtsGridViewAdpater(ThoughtActivity.this, arrayFitnessQuotes, arrayFitnessAuthor);
        } else if (selectValue.equalsIgnoreCase("Food")) {
            //setListValue(arrayFoodQuotes, arrayFoodAuthor);
            //ThoughtsGridViewAdpater adapterViewAndroid = new ThoughtsGridViewAdpater(ThoughtActivity.this, arrayFitnessQuotes, arrayFitnessAuthor);
        } else if (selectValue.equalsIgnoreCase("Art")) {
            setListValue(arrayArtsQuotes, arrayArtsAuthor);
        } else if (selectValue.equalsIgnoreCase("Game")) {
            // ThoughtsGridViewAdpater adapterViewAndroid = new ThoughtsGridViewAdpater(ThoughtActivity.this, arrayFitnessQuotes, arrayFitnessAuthor);
        } else if (selectValue.equalsIgnoreCase("Business")) {
            setListValue(arrayBusiQuotes, arrayBusiAuthor);
        } else if (selectValue.equalsIgnoreCase("Fitness/Health")) {
            setListValue(arrayFitnessQuotes, arrayFitnessAuthor);

        }


    }

    private void setFindView() {
        lvThought = (ListView) findViewById(R.id.lvThought);
        imageView8 = (ImageView) findViewById(R.id.imageView8);
        imageView9 = (ImageView) findViewById(R.id.imageView9);
//        arrayArtsQuotes = getResources().getStringArray(R.array.art_Quotes);
//        arrayArtsAuthor = getResources().getStringArray(R.array.art_author);
//        arrayFitnessQuotes = getResources().getStringArray(R.array.fitness_Quotes);
//        arrayFitnessAuthor = getResources().getStringArray(R.array.fitness_author);
//


    }

    private void setSetOnClickListeners() {
        imageView8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(ThoughtActivity.this, TimerActivity.class);
                in.putExtra("arrayQuotes", sendArrayQuotes);
                in.putExtra("arrayAuthor", sendArrayAuthor);
                startActivity(in);
            }
        });

        imageView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();

            }
        });
    }

    private void setListValue(final String[] arrayQuotes, final String[] arrayAuthor) {

        ThoughtsGridViewAdpater adapterViewAndroid = new ThoughtsGridViewAdpater(ThoughtActivity.this, arrayQuotes, arrayAuthor);
        lvThought.setAdapter(adapterViewAndroid);
        sendArrayAuthor = arrayAuthor;
        sendArrayQuotes = arrayQuotes;


    }

}
