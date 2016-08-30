package com.astound.rushiimdb.cinemalytics;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Astound Rushi on 8/30/2016.
 */

public interface CinemalyticsInterface
{
    @GET("movie/year/{year}/")
    Call<ArrayList<CinemalyticsMovieByYear>> getMoviesByYear(@Path("year") int year, @Query("auth_token") String token);
}
