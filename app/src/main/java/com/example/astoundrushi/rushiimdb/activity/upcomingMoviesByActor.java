package com.example.astoundrushi.rushiimdb.activity;

import android.os.AsyncTask;

import com.example.astoundrushi.rushiimdb.cinemalytics.CinemalyticsMoviesByActor;

import java.util.ArrayList;

/**
 * Created by Astound Rushi on 9/12/2016.
 */
public class UpcomingMoviesByActor extends AsyncTask<ArrayList<CinemalyticsMoviesByActor>,Void,Void>
{
    @Override
    protected Void doInBackground(ArrayList<CinemalyticsMoviesByActor>... params)
    {
        ArrayList<CinemalyticsMoviesByActor> upcomingMoviesByActor=new ArrayList<>();
        return null;
    }

    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid)
    {
        super.onPostExecute(aVoid);
    }
}
