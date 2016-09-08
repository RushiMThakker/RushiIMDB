package com.example.astoundrushi.rushiimdb.activity;

import android.support.v4.view.PagerAdapter;
import android.view.View;

/**
 * Created by Astound Rushi on 9/7/2016.
 */
public class MyActorsPagerAdapter extends PagerAdapter
{
    public MyActorsPagerAdapter(Movie movie)
    {
    }

    @Override
    public int getCount()
    {
        return 0;
    }

    @Override
    public boolean isViewFromObject(View view, Object object)
    {
        return false;
    }
}
