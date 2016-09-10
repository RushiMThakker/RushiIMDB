package com.example.astoundrushi.rushiimdb.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.astoundrushi.rushiimdb.activity.Actor;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;


/**
 * Created by Astound Rushi on 9/10/2016.
 */
public class ActorImagesAdapter extends PagerAdapter
{
    /*ArrayList<String> actorImagesURL;*/
    Actor actor;

    public ActorImagesAdapter(Actor actor)
    {
        this.actor = actor;
    }

    @Override
    public int getCount()
    {
        return Actor.descriptionURL.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position)
    {
        ImageView view = new ImageView(actor.getApplicationContext());
        ImageLoader.getInstance().displayImage(Actor.descriptionURL.get(position), view);
        container.addView(view);
/*
        view.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent selectedActor=new Intent(context, Actor.class);
                selectedActor.putExtra("ActorModelClass name",movieActors.get(position));
                context.startActivity(selectedActor);
            }
        });*/
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
