package com.example.astoundrushi.rushiimdb.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.astoundrushi.rushiimdb.PagerContainer;
import com.example.astoundrushi.rushiimdb.R;
import com.example.astoundrushi.rushiimdb.adapter.ActorImagesAdapter;
import com.example.astoundrushi.rushiimdb.cinemalytics.CinemalyticsActorsByMovie;
import com.example.astoundrushi.rushiimdb.cinemalytics.CinemalyticsClient;
import com.example.astoundrushi.rushiimdb.cinemalytics.CinemalyticsConstants;
import com.example.astoundrushi.rushiimdb.cinemalytics.CinemalyticsInterface;
import com.example.astoundrushi.rushiimdb.cinemalytics.CinemalyticsMoviesByActor;
import com.example.astoundrushi.rushiimdb.getty_images.GettyImagesClient;
import com.example.astoundrushi.rushiimdb.getty_images.GettyImagesInterface;
import com.example.astoundrushi.rushiimdb.wiki.actor_extract.ActorModelClass;
import com.example.astoundrushi.rushiimdb.wiki.actor_extract.WikiActorDesc;
import com.example.astoundrushi.rushiimdb.wiki.WikiClient;
import com.example.astoundrushi.rushiimdb.wiki.WikiInterface;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Actor extends AppCompatActivity implements AsyncResponse,AsyncResponseMoviesParse
{
    CinemalyticsActorsByMovie selectedActor;
    public static ArrayList<String> imageCollection = null;
    public static ArrayList<String> descriptionURL = null;
    WikiInterface wikiInterface;
    GettyImagesInterface gettyImagesInterface;
    ArrayList<CinemalyticsMoviesByActor> cinemalyticsMoviesByActorArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actor);

        descriptionURL = new ArrayList<>();
        imageCollection = new ArrayList<>();

        initUI();
    }

    private void initUI()
    {
        Intent receiveSelectedActor = getIntent();
        ImageView imgActor = (ImageView) findViewById(R.id.imageActor);
        final TextView actorDescription = (TextView) findViewById(R.id.textActorDescription);
        imageCollection = new ArrayList<>();
        selectedActor = (CinemalyticsActorsByMovie) receiveSelectedActor.getSerializableExtra("ActorModelClass name");
        ImageLoader.getInstance().displayImage(selectedActor.getProfilePath(), imgActor);

        Retrofit wikiAdapter = WikiClient.getAdapter();
        wikiInterface = wikiAdapter.create(WikiInterface.class);
        Call<WikiActorDesc> callWikiActorDesc = wikiInterface.getActorDescription("json", "query", "extracts", selectedActor.getName(), "", "");


        callWikiActorDesc.enqueue(new Callback<WikiActorDesc>()
        {
            @Override
            public void onResponse(Call<WikiActorDesc> call, Response<WikiActorDesc> response)
            {
                WikiActorDesc wikiActorDesc = response.body();
                Map<String, ActorModelClass> p = wikiActorDesc.getQuery().getPages();
                ActorModelClass actorModelClass = p.get(p.keySet().toArray()[0]);
                actorDescription.setText(Html.fromHtml(actorModelClass.getExtract()));
            }

            @Override
            public void onFailure(Call<WikiActorDesc> call, Throwable t)
            {

            }
        });

        Retrofit gettyImagesAdapter = GettyImagesClient.getAdapter();
        gettyImagesInterface = gettyImagesAdapter.create(GettyImagesInterface.class);
/*       ActorImageTitleAsync actorImageTitleAsync = new ActorImageTitleAsync(wikiInterface, selectedActor);
        actorImageTitleAsync.delegateActorImageTitle=this;
        actorImageTitleAsync.execute();*/

        GettyImagesActorAsync gettyImagesActorAsync = new GettyImagesActorAsync(selectedActor, gettyImagesInterface);
        gettyImagesActorAsync.delegateActorGettyImage = this;
        gettyImagesActorAsync.execute();

        cinemalyticsMoviesByActorArrayList = new ArrayList<>();

        CinemalyticsInterface cinemalyticsInterface = CinemalyticsClient.getAdapter().create(CinemalyticsInterface.class);

        Call<ArrayList<CinemalyticsMoviesByActor>> cinemalyticsMoviesByActorCall = cinemalyticsInterface.getMoviesOfActor(selectedActor.getId(), CinemalyticsConstants.TOKEN);

        cinemalyticsMoviesByActorCall.enqueue(new Callback<ArrayList<CinemalyticsMoviesByActor>>()
        {
            @Override
            public void onResponse(Call<ArrayList<CinemalyticsMoviesByActor>> call, Response<ArrayList<CinemalyticsMoviesByActor>> response)
            {
                cinemalyticsMoviesByActorArrayList=response.body();

                ArrayList<CinemalyticsMoviesByActor> upcomingMoviesByActor=new ArrayList<CinemalyticsMoviesByActor>();
                ArrayList<CinemalyticsMoviesByActor> currentMoviesByActor=new ArrayList<CinemalyticsMoviesByActor>();
                ArrayList<CinemalyticsMoviesByActor> mostPopularMoviesByActor=new ArrayList<CinemalyticsMoviesByActor>();
            }

            @Override
            public void onFailure(Call<ArrayList<CinemalyticsMoviesByActor>> call, Throwable t)
            {

            }
        });
    }

/*    @Override
    public void processFinish(Boolean output)
    {
        if (output == true)
        {
            ActorImageURLAsync actorImageURLAsync = new ActorImageURLAsync(wikiInterface);
            actorImageURLAsync.delegateActorImageAsync=this;
            actorImageURLAsync.execute();
        }
        else if(output==false)
        {
            PagerContainer actorImagesStriper = (PagerContainer) findViewById(R.id.vpActorPhotosContainer);
            ActorImagesAdapter actorImagesAdapter = new ActorImagesAdapter(this);
            ViewPager actorImagesPager = actorImagesStriper.getViewPager();
            actorImagesPager.setAdapter(actorImagesAdapter);
            actorImagesPager.setOffscreenPageLimit(actorImagesAdapter.getCount());
            actorImagesPager.setPageMargin(15);
            actorImagesPager.setClipChildren(false);
        }
    }*/

    @Override
    public void processFinish(Boolean output)
    {
        PagerContainer actorImagesStriper = (PagerContainer) findViewById(R.id.vpActorPhotosContainer);
        ActorImagesAdapter actorImagesAdapter = new ActorImagesAdapter(Actor.this);
        ViewPager actorImagesPager = actorImagesStriper.getViewPager();
        actorImagesPager.setAdapter(actorImagesAdapter);
        actorImagesPager.setOffscreenPageLimit(actorImagesAdapter.getCount());
        actorImagesPager.setPageMargin(15);
        actorImagesPager.setClipChildren(false);
    }

    @Override
    public void completeProcess(String typeOfMovies)
    {

        if(typeOfMovies.equals("Upcoming"))
        {

        }
        else if(typeOfMovies.equals("Current"))
        {

        }
        else if(typeOfMovies.equals("Most Popular"))
        {

        }
    }
}
