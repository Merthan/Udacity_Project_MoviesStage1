package com.example.merthane.merthan_movies;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by MerthanE on 16.02.2018.
 */

public class Movie {

    private final static String POSTER_PATH="poster_path" ;
    private final static String TITLE="title" ;
    private final static String RELEASE_DATE="release_date" ;
    private final static String VOTE_AVERAGE="vote_average" ;
    private final static String OVERVIEW ="overview" ;



    private String imageString;
    private String title;
    private String date;
    private String voteAverage;
    private String plotSynopsis;

    private final static String BASE_URL="https://image.tmdb.org/t/p/";
    private final static String SIZE="w185";

    private Movie(String imageString, String title, String date, String voteAverage, String plotSynopsis){
        this.imageString=BASE_URL+SIZE+imageString;
        this.title=title;
        this.date=date;
        this.voteAverage = voteAverage;
        this.plotSynopsis = plotSynopsis;
    }



     Movie(JSONObject json) throws JSONException {

        //calls other constructor
        this(
                json.getString(POSTER_PATH),
                json.getString(TITLE),
                json.getString(RELEASE_DATE),
                json.getString(VOTE_AVERAGE),
                json.getString(OVERVIEW)
        );
    }

    //Getters Setters
    public String getImageString() {
        return imageString;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDate() {
        return date;
    }
    public String getVoteAverage() {
        return voteAverage;
    }
    public String getPlotSynopsis() {
        return plotSynopsis;
    }

}
