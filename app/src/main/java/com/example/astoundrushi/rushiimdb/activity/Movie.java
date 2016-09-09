package com.example.astoundrushi.rushiimdb.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import android.widget.TextView;

import com.example.astoundrushi.rushiimdb.PagerContainer;
import com.example.astoundrushi.rushiimdb.R;
import com.example.astoundrushi.rushiimdb.adapter.CustomAdapter;
import com.example.astoundrushi.rushiimdb.adapter.MyActorsPagerAdapter;
import com.example.astoundrushi.rushiimdb.cinemalytics.CinemalyticsActorsByMovie;
import com.example.astoundrushi.rushiimdb.cinemalytics.CinemalyticsClient;
import com.example.astoundrushi.rushiimdb.cinemalytics.CinemalyticsConstants;
import com.example.astoundrushi.rushiimdb.cinemalytics.CinemalyticsInterface;
import com.example.astoundrushi.rushiimdb.cinemalytics.CinemalyticsMovieByYear;
import com.example.astoundrushi.rushiimdb.cinemalytics.CinemalyticsSongsByMovie;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.ArrayList;

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
    public static ArrayList<String> songs;
    PagerContainer actorStripPager,songStripPager;
    ArrayList<Double> songsDuration;
    CinemalyticsInterface cinemalyticsInterface;
    ArrayList<CinemalyticsActorsByMovie> movieActors;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);/*
        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
        getWindow().setEnterTransition(new Fade());
        getWindow().setSharedElementEnterTransition(new ChangeImageTransform());*/
        setContentView(R.layout.activity_movie);
        cinemalyticsInterface = CinemalyticsClient.getAdapter().create(CinemalyticsInterface.class);
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

        /*recyclerView=(RecyclerView) findViewById(R.id.recyclerViewSongs);*/

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


        Call<ArrayList<CinemalyticsActorsByMovie>> callActorsByMovie=cinemalyticsInterface.getActorsOfMovie(selectedMovie.getId(),CinemalyticsConstants.TOKEN);
        callActorsByMovie.enqueue(new Callback<ArrayList<CinemalyticsActorsByMovie>>()
        {
            @Override
            public void onResponse(Call<ArrayList<CinemalyticsActorsByMovie>> call, Response<ArrayList<CinemalyticsActorsByMovie>> response)
            {
                movieActors = response.body();
                actorStripPager = (PagerContainer) findViewById(R.id.pager_container_actors);
                ViewPager pagerActors=actorStripPager.getViewPager();
                PagerAdapter adapterActors= new MyActorsPagerAdapter(Movie.this,movieActors,Movie.this);
                pagerActors.setAdapter(adapterActors);
                pagerActors.setOffscreenPageLimit(adapterActors.getCount());
                pagerActors.setPageMargin(15);
                pagerActors.setClipChildren(false);

            }

            @Override
            public void onFailure(Call<ArrayList<CinemalyticsActorsByMovie>> call, Throwable t)
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

/*            recyclerView = (RecyclerView) findViewById(R.id.recyclerViewSongs);
            recyclerViewLayoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(recyclerViewLayoutManager);
            recyclerViewAdapter = new CustomAdapter(Movie.this,links,songsDuration);
            recyclerView.setAdapter(recyclerViewAdapter);*/

            songStripPager = (PagerContainer) findViewById(R.id.pager_container_songs);

            ViewPager pagerSongs = songStripPager.getViewPager();

            PagerAdapter adapterSongs = new MySongsPagerAdapter(Movie.this);
            pagerSongs.setAdapter(adapterSongs);
            //Necessary or the pagerSongs will only have one extra page to show
            // make this at least however many pages you can see
            pagerSongs.setOffscreenPageLimit(adapterSongs.getCount());
            //A little space between pages
            pagerSongs.setPageMargin(15);
            //If hardware acceleration is enabled, you should also remove
            // clipping on the pagerSongs for its children.
            pagerSongs.setClipChildren(false);

        }
    }

    private class MySongsPagerAdapter extends PagerAdapter
    {
        Context context;
        MySongsPagerAdapter(Context context)
        {
            this.context=context;
        }
        @Override
        public Object instantiateItem(final ViewGroup container, final int position)
        {
            LayoutInflater inflater = (LayoutInflater) container.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            int resID=R.layout.custom_viewpager_layout;

            View relativeLayoutViewPager=inflater.inflate(resID,container,false);
            ImageView view = (ImageView)relativeLayoutViewPager.findViewById(R.id.imgThumbNail);
            ImageLoader.getInstance().displayImage(YoutubeSearch.THUMBNAILURL.get(position),view);
/*
            RelativeLayout.LayoutParams layoutParams=(RelativeLayout.LayoutParams)view.getLayoutParams();
            *//*TextView textSongName=(TextView)actorStripPager.findViewById(R.id.vpCurrentSong);*//*
            layoutParams.addRule(RelativeLayout.BELOW, view.getId());*/
            TextView textSongName=(TextView)relativeLayoutViewPager.findViewById(R.id.textSongName);
            textSongName.setText(songs.get(position));
            /*textSongName.setLayoutParams(layoutParams);*/
            container.addView(relativeLayoutViewPager);

            System.out.println("<<SONG TEXT>>"+textSongName.getText().toString());
            relativeLayoutViewPager.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.setData(Uri.parse(YoutubeSearch.DIRECTYOUTUBELINKS.get(position)));
                    context.startActivity(intent);
                }
            });
            return relativeLayoutViewPager;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object)
        {
            container.removeView((View) object);
        }

        @Override
        public int getCount()
        {
            return YoutubeSearch.THUMBNAILURL.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object)
        {
            return (view == object);
        }
    }

}