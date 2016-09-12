package com.example.astoundrushi.rushiimdb.getty_images;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Astound Rushi on 9/12/2016.
 */
public class GettyImagesClient
{
    public static String BASEURL="https://api.gettyimages.com";
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
