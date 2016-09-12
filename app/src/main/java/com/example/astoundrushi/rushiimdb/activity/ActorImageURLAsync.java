package com.example.astoundrushi.rushiimdb.activity;

import android.os.AsyncTask;

import com.example.astoundrushi.rushiimdb.wiki.WikiInterface;
import com.example.astoundrushi.rushiimdb.wiki.actor_images_url_collection.ActorImagesURLCollection;
import com.example.astoundrushi.rushiimdb.wiki.actor_images_url_collection.Imageinfo;
import com.example.astoundrushi.rushiimdb.wiki.actor_images_url_collection.PageDetail;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Astound Rushi on 9/10/2016.
 */
public class ActorImageURLAsync extends AsyncTask
{
    WikiInterface wikiInterface;
    public AsyncResponse delegateActorImageAsync=null;
    public ActorImageURLAsync(WikiInterface wikiInterface)
    {
        this.wikiInterface=wikiInterface;
    }

    @Override
    protected Object doInBackground(Object[] params)
    {
        for (int i = 0; i < Actor.imageCollection.size(); ++i)
        {
            Call<ActorImagesURLCollection> actorImagesURLCollectionCall = wikiInterface.getAllImagesURL("json", "query", "imageinfo", "Image:" + Actor.imageCollection.get(i), "url");
            actorImagesURLCollectionCall.enqueue(new Callback<ActorImagesURLCollection>()
            {
                @Override
                public void onResponse(Call<ActorImagesURLCollection> call, Response<ActorImagesURLCollection> response)
                {
                    ActorImagesURLCollection actorImagesURLCollection = response.body();
                    Map<String, PageDetail> pageDetailMap = actorImagesURLCollection.getQuery().getPages();
                    PageDetail pageDetail = pageDetailMap.get(pageDetailMap.keySet().toArray()[0]);
                    Imageinfo imageinfo = pageDetail.getImageinfo().get(0);
                    Actor.descriptionURL.add(imageinfo.getUrl());
                }

                @Override
                public void onFailure(Call<ActorImagesURLCollection> call, Throwable t)
                {

                }
            });
        }

        return null;
    }

    @Override
    protected void onPostExecute(Object o)
    {
        super.onPostExecute(o);
        delegateActorImageAsync.processFinish(false);
    }
}
