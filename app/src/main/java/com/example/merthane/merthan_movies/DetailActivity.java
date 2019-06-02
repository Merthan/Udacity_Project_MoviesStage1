package com.example.merthane.merthan_movies;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        final Intent INTENT= getIntent();

        TextView textView = findViewById(R.id.message);

        ((TextView) findViewById(R.id.title)).setText(INTENT.getStringExtra("title"));
        ((TextView) findViewById(R.id.release_date)).setText(INTENT.getStringExtra("date"));
        ((TextView) findViewById(R.id.rating)).setText(INTENT.getStringExtra("vote_average"));


        if(Double.parseDouble(((TextView) findViewById(R.id.rating)).getText().toString())>7){
            ((TextView) findViewById(R.id.rating)).setTextColor(Color.GREEN);
        }else if(Double.parseDouble(((TextView) findViewById(R.id.rating)).getText().toString())>5){
            ((TextView) findViewById(R.id.rating)).setTextColor(Color.YELLOW);
        }else{
            ((TextView) findViewById(R.id.rating)).setTextColor(Color.RED);
        }


        ((TextView) findViewById(R.id.plot_synopsis)).setText(INTENT.getStringExtra("plot_synopsis"));

        Picasso.with(this).load(INTENT.getStringExtra("imageString")).into((ImageView) findViewById(R.id.poster));



    }


}
