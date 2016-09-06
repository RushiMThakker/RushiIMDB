package com.example.astoundrushi.rushiimdb.activity;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.astoundrushi.rushiimdb.R;
import com.example.astoundrushi.rushiimdb.cinemalytics.*;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

/**
 * Created by Astound Rushi on 8/30/2016.
 */

public class MoviesPager extends Fragment
{
    private static final String ARG_PARAM1 = "posterURL";
    private DisplayImageOptions options;
    ImageView poster;

    // TODO: Rename and change types of parameters
    private String posterURL;
    private OnFragmentInteractionListener mListener;

    public static MoviesPager newInstance(String URL, CinemalyticsMovieByYear movie)
    {
        MoviesPager fragment = new MoviesPager();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, URL);
        args.putSerializable("movie",movie);
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        options = new DisplayImageOptions.Builder().cacheInMemory(true)
                .cacheOnDisk(true).considerExifParams(true)
                .showImageOnFail(android.R.drawable.ic_dialog_alert)
                .showImageForEmptyUri(android.R.drawable.ic_dialog_alert)
                .displayer(new RoundedBitmapDisplayer(0)).build();

        View v = inflater.inflate(R.layout.pager_fragment, container, false);
        poster = (ImageView) v.findViewById(R.id.selectedMoviePoster);
        final View sharedView=poster;
        CinemalyticsMovieByYear y=(CinemalyticsMovieByYear)getArguments().getSerializable("movie");
        final String transitionName="selectedMoviePoster";
        final ProgressBar progressBar = (ProgressBar) v.findViewById(R.id.loadPosterProgress);
        v.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent sendMovie=new Intent(getContext(),Movie.class);
                sendMovie.putExtra("movie",getArguments().getSerializable("movie"));
                ActivityCompat.startActivity(getActivity(),sendMovie, ActivityOptions.makeSceneTransitionAnimation(getActivity(),sharedView,transitionName).toBundle());
                /*startActivity(sendMovie);*/
            }
        });
        ImageLoader.getInstance().displayImage(posterURL, poster, options, new SimpleImageLoadingListener()
        {

            @Override
            public void onLoadingStarted(String imageUri, View view)
            {

                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage)
            {

                progressBar.setVisibility(View.GONE);
            }
        });
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        posterURL = getArguments().getString(ARG_PARAM1);
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener)
        {
            mListener = (OnFragmentInteractionListener) context;
            App.initImageLoader(context);
        } else
        {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        mListener=null;
    }
    public interface OnFragmentInteractionListener
    {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri)
    {
        if (mListener != null)
        {
            mListener.onFragmentInteraction(uri);
        }
    }
}
