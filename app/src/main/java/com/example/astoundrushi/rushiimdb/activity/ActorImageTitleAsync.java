package com.example.astoundrushi.rushiimdb.activity;

import android.os.AsyncTask;

import com.example.astoundrushi.rushiimdb.cinemalytics.CinemalyticsActorsByMovie;
import com.example.astoundrushi.rushiimdb.wiki.WikiInterface;
import com.example.astoundrushi.rushiimdb.wiki.actor_images_title_collection.ActorImagesCollection;
import com.example.astoundrushi.rushiimdb.wiki.actor_images_title_collection.Allimage;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Astound Rushi on 9/10/2016.
 */
public class ActorImageTitleAsync extends AsyncTask
{
    public AsyncResponse delegateActorImageTitle = null;
    WikiInterface wikiInterface;
    CinemalyticsActorsByMovie selectedActor;

    public ActorImageTitleAsync(WikiInterface wikiInterface, CinemalyticsActorsByMovie selectedActor)
    {
        super();
        this.wikiInterface = wikiInterface;
        this.selectedActor = selectedActor;
    }

    @Override
    protected Object doInBackground(Object[] params)
    {
        android.os.Debug.waitForDebugger();
        Call<ActorImagesCollection> callActorImagesCollection = wikiInterface.getAllImagesTitle("json", "query", "allimages", "5", selectedActor.getName(), "dimensions%7Cmime");
        callActorImagesCollection.enqueue(new Callback<ActorImagesCollection>()
        {
            @Override
            public void onResponse(Call<ActorImagesCollection> call, Response<ActorImagesCollection> response)
            {
                ActorImagesCollection actorImagesCollection = response.body();

                for (int i = 0; i < actorImagesCollection.getQuery().getAllimages().size(); ++i)
                {
                    Allimage image = actorImagesCollection.getQuery().getAllimages().get(i);
                    String title = image.getTitle();
                    int k = 0;
                    for (String titleDetail : title.split(":"))
                    {
                        if (k == 1)
                        {
                            Actor.imageCollection.add(i, titleDetail);
                        } else
                        {
                            k++;
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ActorImagesCollection> call, Throwable t)
            {

            }
        });

        return null;
    }

    @Override
    protected void onPostExecute(Object o)
    {
        delegateActorImageTitle.processFinish(true);
    }

}
