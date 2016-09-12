package com.example.astoundrushi.rushiimdb.getty_images;

import com.example.astoundrushi.rushiimdb.getty_images.actor_images_response.GettyImagesResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by Astound Rushi on 9/12/2016.
 */
public interface GettyImagesInterface
{
    @Headers("Api-Key:b327vt8t42qc4hndpgubdgjz")
    @GET("/v3/search/images/editorial/")
    Call<GettyImagesResult> getActorImages(@Query("editorial_segments") String editorial_segments,
                                           @Query("fields") String fields,
                                           @Query("phrase") String phrase,
                                           @Query("sort_order") String sort_order);
}
