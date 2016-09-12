package com.example.astoundrushi.rushiimdb.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.astoundrushi.rushiimdb.activity.Actor;
import com.example.astoundrushi.rushiimdb.activity.ActorFullImage;
import com.example.astoundrushi.rushiimdb.activity.App;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.ArrayList;


/**
 * Created by Astound Rushi on 9/10/2016.
 */
public class ActorImagesAdapter extends PagerAdapter
{
    /*ArrayList<String> actorImagesURL;*/
    Context actor;

    public ActorImagesAdapter(Context actor)
    {
        this.actor = actor;
    }

    @Override
    public int getCount()
    {
        return Actor.descriptionURL.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position)
    {
        ImageView view = new ImageView(actor);
        ImageLoader.getInstance().displayImage(Actor.descriptionURL.get(position), view, App.options,new SimpleImageLoadingListener()
        {

            @Override
            public void onLoadingStarted(String imageUri, View view)
            {
                super.onLoadingStarted(imageUri, view);
                System.out.println(this.getClass().toString()+" IMAGE LOADING STARTED");
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason)
            {
                super.onLoadingFailed(imageUri, view, failReason);
                System.out.println(this.getClass().toString()+" IMAGE LOADING FAILED");
            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage)
            {
                super.onLoadingComplete(imageUri, view, loadedImage);
                System.out.println(this.getClass().toString()+" IMAGE LOADING COMPLETED");
            }
        });
        container.addView(view);
        System.out.println("We have added image URL which is :"+Actor.descriptionURL.get(position));

        view.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent selectedActor=new Intent(actor, ActorFullImage.class);
                selectedActor.putExtra("ActorImageURL", Actor.descriptionURL.get(position));
                selectedActor.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                actor.startActivity(selectedActor);
            }
        });
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object)
    {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object)
    {
        return (view == object);
    }
}
