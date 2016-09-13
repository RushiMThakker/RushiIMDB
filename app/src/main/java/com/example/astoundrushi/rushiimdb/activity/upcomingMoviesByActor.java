package com.example.astoundrushi.rushiimdb.activity;

import android.os.AsyncTask;

import com.example.astoundrushi.rushiimdb.cinemalytics.CinemalyticsMoviesByActor;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static com.example.astoundrushi.rushiimdb.controller.Utils.convertDate;

/**
 * Created by Astound Rushi on 9/12/2016.
 */
public class UpcomingMoviesByActor extends AsyncTask<ArrayList<CinemalyticsMoviesByActor>, Void, Void>
{
    public AsyncResponseMoviesParse asyncResponseMoviesParse = null;
    public static ArrayList<CinemalyticsMoviesByActor> upcomingMoviesByActor;

    @Override
    protected Void doInBackground(ArrayList<CinemalyticsMoviesByActor>... params)
    {
        android.os.Debug.waitForDebugger();
        upcomingMoviesByActor = new ArrayList<>();
        ArrayList<CinemalyticsMoviesByActor> allMoviesByActor = new ArrayList<>();

        allMoviesByActor = params[0];

        for (int i = 0; i < allMoviesByActor.size(); ++i)
        {
            Date currentDate = new Date();
            Date nextYearDate = new Date();

            nextYearDate.equals("01/01/2017");
            Date movieDate = convertDate(allMoviesByActor.get(i).getReleaseDate());
            if ((movieDate.after(currentDate))&&(movieDate.before(nextYearDate)))
            {
                if ((allMoviesByActor.get(i).getImdbId() != null) && (allMoviesByActor.get(i).getTrailerLink() != null))
                {
                    upcomingMoviesByActor.add(allMoviesByActor.get(i));
                }
            }
        }
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
        asyncResponseMoviesParse.completeProcess("Current");
    }
}
