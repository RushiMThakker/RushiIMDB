package com.example.astoundrushi.rushiimdb.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import com.example.astoundrushi.rushiimdb.activity.Actor;
import com.example.astoundrushi.rushiimdb.activity.Movie;
import com.example.astoundrushi.rushiimdb.cinemalytics.CinemalyticsActorsByMovie;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Astound Rushi on 9/7/2016.
 */
public class MyActorsPagerAdapter extends PagerAdapter
{
    ArrayList<CinemalyticsActorsByMovie> movieActors;
    Movie movie;
    Context context;

    public MyActorsPagerAdapter(Movie movie, ArrayList<CinemalyticsActorsByMovie> movieActors,Context context)
    {
        this.movieActors = movieActors;
        this.movie=movie;
        this.context=context;
    }

    @Override
    public int getCount()
    {
        return movieActors.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position)
    {
        CircleImageView view = new CircleImageView(movie.getApplicationContext());
        ImageLoader.getInstance().displayImage(movieActors.get(position).getProfilePath(),view);
        container.addView(view);

        view.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent selectedActor=new Intent(context, Actor.class);
                selectedActor.putExtra("Actor name",movieActors.get(position));
                context.startActivity(selectedActor);
            }
        });
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object)
    {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object)
    {
        return (view==object);
    }
}
