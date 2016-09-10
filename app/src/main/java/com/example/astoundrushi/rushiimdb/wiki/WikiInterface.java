package com.example.astoundrushi.rushiimdb.wiki;

import com.example.astoundrushi.rushiimdb.wiki.actor_extract.WikiActorDesc;
import com.example.astoundrushi.rushiimdb.wiki.actor_images_title_collection.ActorImagesCollection;
import com.example.astoundrushi.rushiimdb.wiki.actor_images_url_collection.ActorImagesURLCollection;

import retrofit2.Call;
import retrofit2.http.*;
import retrofit2.http.Query;

/**
 * Created by Astound Rushi on 9/10/2016.
 */
public interface WikiInterface
{
    @GET("/w/api.php")
    Call<WikiActorDesc> getActorDescription(@Query("format") String format,
                                            @Query("action") String action,
                                            @Query("prop") String prop,
                                            @Query("titles") String titles,
                                            @Query("exintro") String exintro,
                                            @Query("exintro") String explaintext);

    @GET("/w/api.php")
    Call<ActorImagesCollection> getAllImagesTitle(@Query("format") String format,
                                                  @Query("action") String action,
                                                  @Query("list") String list,
                                                  @Query("ailimit") String ailimit,
                                                  @Query("aifrom") String aifrom,
                                                  @Query("aiprop") String aiprop);

    @GET("/w/api.php")
    Call<ActorImagesURLCollection> getAllImagesURL(@Query("format") String format,
                                                   @Query("action") String action,
                                                   @Query("prop") String prop,
                                                   @Query("titles") String titles,
                                                   @Query("iiprop") String iiprop);
}
