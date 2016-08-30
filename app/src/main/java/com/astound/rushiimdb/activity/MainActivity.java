package com.astound.rushiimdb.activity;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;

import com.astound.rushiimdb.R;
import com.astound.rushiimdb.cinemalytics.CinemalyticsClient;
import com.astound.rushiimdb.cinemalytics.CinemalyticsConstants;
import com.astound.rushiimdb.cinemalytics.CinemalyticsInterface;
import com.astound.rushiimdb.cinemalytics.CinemalyticsMovieByYear;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.astound.rushiimdb.Controller.Utils.convertDate;


public class MainActivity extends AppCompatActivity implements PagerMovie.OnFragmentInteractionListener
{
    @BindView(R.id.currentMoviesViewPager) ViewPager currentMoviesViewPager;
    private ArrayList<CinemalyticsMovieByYear> currentMovies=new ArrayList<CinemalyticsMovieByYear>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initUI();
    }

    void initUI()
    {
//------------------------------VIEWPAGER GET MOVIES BY YEAR ---------------------------------//

        CinemalyticsInterface cinemalyticsInterface=CinemalyticsClient.getAdapter().create(CinemalyticsInterface.class);

        Call<ArrayList<CinemalyticsMovieByYear>> callMoviesByYear=cinemalyticsInterface.getMoviesByYear(2016, CinemalyticsConstants.TOKEN);
        callMoviesByYear.enqueue(new Callback<ArrayList<CinemalyticsMovieByYear>>()
        {
            @Override
            public void onResponse(Call<ArrayList<CinemalyticsMovieByYear>> call, Response<ArrayList<CinemalyticsMovieByYear>> response)
            {
                generateCurrentMovies(response.body());

                MoviesPagerAdapter currentMoviesPagerAdapter=new MoviesPagerAdapter(getSupportFragmentManager());
                currentMoviesViewPager.setAdapter(currentMoviesPagerAdapter);

            }

            @Override
            public void onFailure(Call<ArrayList<CinemalyticsMovieByYear>> call, Throwable t)
            {
                System.out.println("LOL");
            }
        });
    }

    private void generateCurrentMovies(ArrayList<CinemalyticsMovieByYear> allMovies)
    {
        for(int i=0;i<allMovies.size();++i)
        {
            Date currentDate=new Date();
            Calendar calendar=Calendar.getInstance();
            calendar.setTime(currentDate);
            calendar.add(Calendar.DATE,-14);
            Date previousWeek=calendar.getTime();
            Date indexMovieDate=convertDate(allMovies.get(i).getReleaseDate());
            if((indexMovieDate.before(currentDate))&&(indexMovieDate.after(previousWeek)))
            {
                if(allMovies.get(i).getImdbId()!=null)
                {
                    currentMovies.add(allMovies.get(i));
                }
            }
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri)
    {

    }

    public class MoviesPagerAdapter extends FragmentPagerAdapter
    {

        public MoviesPagerAdapter(FragmentManager fm)
        {
            super(fm);
        }

        @Override
        public Fragment getItem(int position)
        {
            return PagerMovie.newInstance(currentMovies.get(position).getPosterPath().toString(),currentMovies.get(position));
        }

        @Override
        public int getCount()
        {
            return currentMovies.size();
        }
    }
}
