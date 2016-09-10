package com.example.astoundrushi.rushiimdb.wiki;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Astound Rushi on 9/10/2016.
 */
public class WikiClient
{
    public static String BASEURL="https://en.wikipedia.org";

    public static Retrofit adapter=null;

    public static Retrofit getAdapter()
    {
        if (adapter == null)
        {
            adapter=new Retrofit.Builder()
                            .baseUrl(BASEURL)
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
        }
        return adapter;
    }
}
