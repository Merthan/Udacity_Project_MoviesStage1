package com.example.merthane.merthan_movies;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class MainActivity extends AppCompatActivity implements MyAdapter.CardClickListener {

    MyAdapter adapter;




    final static String sort_rating ="sort_rating";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final SharedPreferences sharedPreferences= getPreferences(MODE_PRIVATE);
        final RecyclerView recyclerView=findViewById(R.id.rv);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                boolean b=sharedPreferences.getBoolean(sort_rating,false);
                //if true, it was set to sorting by rating so group picture, because now it will be by popular
                fab.setImageResource(b?R.drawable.ic_group_black_24dp:R.drawable.ic_thumb_up_black_24dp);
                sharedPreferences.edit().putBoolean(sort_rating,!b).apply();

                FetchMovies fm = new FetchMovies(adapter,!b);
                fm.execute();

                Snackbar.make(view, b?getString(R.string.set_to_popular):getString(R.string.set_to_rating), Snackbar.LENGTH_SHORT)
                        .setAction("Action", null).show();


                recyclerView.smoothScrollToPosition(0);
            }
        });


        boolean sort_by_rating=sharedPreferences.getBoolean(sort_rating,false);
        fab.setImageResource(sort_by_rating?R.drawable.ic_thumb_up_black_24dp:R.drawable.ic_group_black_24dp   );



       // startActivity(new Intent(this,Main2Activity.class));




        int num_colums=3;
        recyclerView.setLayoutManager(new GridLayoutManager(this,num_colums));
        adapter=new MyAdapter(this,new String[]{""});
        adapter.setCardClickListener(this);
        recyclerView.setAdapter(adapter);

        FetchMovies fm = new FetchMovies(adapter,sort_by_rating);
        fm.execute();
    }

    @Override
    public void onItemClick(View view, int position,Movie[] movies) {
        Movie m=movies[position];
        Intent i=new Intent(this,DetailActivity.class);

        i.putExtra("imageString",m.getImageString());
        i.putExtra("title",m.getTitle());
        i.putExtra("date",m.getDate());
        i.putExtra("vote_average",m.getVoteAverage());
        i.putExtra("plot_synopsis",m.getPlotSynopsis());
        startActivity(i);

    }
}
