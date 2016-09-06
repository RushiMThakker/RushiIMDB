package com.example.astoundrushi.rushiimdb.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.transition.ChangeImageTransform;
import android.transition.Fade;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import android.widget.TextView;

import com.example.astoundrushi.rushiimdb.R;
import com.example.astoundrushi.rushiimdb.adapter.CustomAdapter;
import com.example.astoundrushi.rushiimdb.cinemalytics.CinemalyticsClient;
import com.example.astoundrushi.rushiimdb.cinemalytics.CinemalyticsConstants;
import com.example.astoundrushi.rushiimdb.cinemalytics.CinemalyticsInterface;
import com.example.astoundrushi.rushiimdb.cinemalytics.CinemalyticsMovieByYear;
import com.example.astoundrushi.rushiimdb.cinemalytics.CinemalyticsSongsByMovie;
import com.google.api.services.youtube.YouTube;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.core.process.BitmapProcessor;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Movie extends AppCompatActivity implements AsyncResponse
{
    ImageView selectedMoviePoster;
    private DisplayImageOptions options;
    CinemalyticsMovieByYear selectedMovie;
    RecyclerView recyclerView;
    CustomAdapter recyclerViewAdapter;
    RecyclerView.LayoutManager recyclerViewLayoutManager;
    ArrayList<String> songs;
    ArrayList<Double> songsDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);/*
        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
        getWindow().setEnterTransition(new Fade());
        getWindow().setSharedElementEnterTransition(new ChangeImageTransform());*/
        setContentView(R.layout.activity_movie);
        ButterKnife.bind(this);
        initUI();
    }

    private void initUI()
    {
        Intent i=getIntent();

        options = new DisplayImageOptions.Builder().cacheInMemory(true)
                .cacheOnDisk(true).considerExifParams(true)
                .showImageOnFail(android.R.drawable.ic_dialog_alert)
                .showImageForEmptyUri(android.R.drawable.ic_dialog_alert)
                .displayer(new RoundedBitmapDisplayer(0))
                .build();
/*        .postProcessor(new BitmapProcessor() {
        @Override
        public Bitmap process(Bitmap bmp) {
            return Bitmap.createScaledBitmap(bmp, 300, 300, true);
        }
    })*/
        selectedMovie=(CinemalyticsMovieByYear)i.getSerializableExtra("movie");
        selectedMoviePoster=(ImageView)findViewById(R.id.selectedMoviePoster);
        String URL=selectedMovie.getPosterPath().toString();
/*App.initImageLoader(this);*/

        ImageLoader.getInstance().displayImage(selectedMovie.getPosterPath().toString(),selectedMoviePoster,options,new SimpleImageLoadingListener()
        {

            @Override
            public void onLoadingStarted(String imageUri, View view)
            {
                super.onLoadingStarted(imageUri, view);
                System.out.println("<<<<<LOADING IMAGE FROM VIEWPAGER>>>> LOADING STARTED");
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason)
            {
                super.onLoadingFailed(imageUri, view, failReason);
                System.out.println("<<<<<LOADING IMAGE FROM VIEWPAGER>>>> LOADING FAILED");
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage)
            {
                super.onLoadingComplete(imageUri, view, loadedImage);
                System.out.println("<<<<<LOADING IMAGE FROM VIEWPAGER>>>> LOADING COMPLETE");
            }
        });

        final Toolbar movieNameToolbar=(Toolbar)findViewById(R.id.toolbar);
        movieNameToolbar.setTitle(selectedMovie.getTitle());

        AppBarLayout appBarLayoutImage=(AppBarLayout)findViewById(R.id.appbar);
        if (appBarLayoutImage != null)
        {
            appBarLayoutImage.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener()
            {

                @Override
                public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset)
                {
                    if((verticalOffset==0))
                    {
                        View line=findViewById(R.id.viewLine);
                        line.setVisibility(View.GONE);
                        movieNameToolbar.setVisibility(View.VISIBLE);
                    }
                }
            });
        }

        TextView overView=(TextView)findViewById(R.id.textMovieOverview);
        overView.setText(selectedMovie.getDescription());

        TextView trailerLink=(TextView)findViewById(R.id.trailerLink);
        trailerLink.setClickable(true);
        trailerLink.setMovementMethod(LinkMovementMethod.getInstance());
        String link="<a href="+selectedMovie.getTrailerLink()+">"+selectedMovie.getTitle()+"Trailer</a>";
        /*trailerLink.setText("<a href="+selectedMovie.getTrailerLink()+">"+selectedMovie.getTitle()+"Trailer</a>");*/
        trailerLink.setText(Html.fromHtml(link));

        recyclerView=(RecyclerView) findViewById(R.id.recyclerViewSongs);

        CinemalyticsInterface cinemalyticsInterface = CinemalyticsClient.getAdapter().create(CinemalyticsInterface.class);

        Call<ArrayList<CinemalyticsSongsByMovie>> callMoviesByYear = cinemalyticsInterface.getSongsOfMovie(selectedMovie.getId(),CinemalyticsConstants.TOKEN);
        callMoviesByYear.enqueue(new Callback<ArrayList<CinemalyticsSongsByMovie>>()
        {

            @Override
            public void onResponse(Call<ArrayList<CinemalyticsSongsByMovie>> call, Response<ArrayList<CinemalyticsSongsByMovie>> response)
            {
                ArrayList<CinemalyticsSongsByMovie> receivedSongs;
                songs=new ArrayList<String>();
                songsDuration=new ArrayList<Double>();
                /*receivedSongs=new ArrayList<CinemalyticsSongsByMovie>();*/
                receivedSongs=response.body();

                for(int i=0;i<receivedSongs.size();++i)
                {
                    songs.add(i,receivedSongs.get(i).getTitle());
                    songsDuration.add(i,receivedSongs.get(i).getDuration());
                }

                generateYoutubeLinks(songs);
            }

            @Override
            public void onFailure(Call<ArrayList<CinemalyticsSongsByMovie>> call, Throwable t)
            {

            }
        });
    }

    private void generateYoutubeLinks(ArrayList<String> movieSongs)
    {
        ArrayList<String> querySongs=new ArrayList<String>();
        int i=0;

        for (String song:movieSongs)
        {
           StringBuffer query;
           query=new StringBuffer(song);
           query.append(","+selectedMovie.getTitle());
           querySongs.add(i,query.toString());
            ++i;
        }

        YoutubeSearch searchSongs=new YoutubeSearch();
        searchSongs.delegate=this;
        searchSongs.execute(querySongs);

    }

    @Override
    public void processFinish(Boolean output)
    {
        ArrayList<String> links = new ArrayList<String>();

        if(output==true)
        {
            int i=0;
            for(String link:YoutubeSearch.LINKS)
            {
                StringBuffer linkFormatter=new StringBuffer(link);
                linkFormatter.append(songs.get(i)+"</a>");
                links.add(i,linkFormatter.toString());
                ++i;
            }

            recyclerView = (RecyclerView) findViewById(R.id.recyclerViewSongs);
            recyclerViewLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(recyclerViewLayoutManager);
            recyclerViewAdapter = new CustomAdapter(Movie.this,links,songsDuration);
            recyclerView.setAdapter(recyclerViewAdapter);
        }
    }
}