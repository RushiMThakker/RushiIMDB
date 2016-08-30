package com.astound.rushiimdb.cinemalytics;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Astound Rushi on 8/30/2016.
 */

public class CinemalyticsClient
{
    public static Retrofit adapter=null;

    public static Retrofit getAdapter()
    {
        if(adapter==null)
        {
            adapter=new Retrofit.Builder()
                    .baseUrl(CinemalyticsConstants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return adapter;
    }
}
