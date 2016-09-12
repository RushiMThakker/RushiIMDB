package com.example.astoundrushi.rushiimdb.activity;

import android.os.AsyncTask;

import com.example.astoundrushi.rushiimdb.cinemalytics.CinemalyticsActorsByMovie;
import com.example.astoundrushi.rushiimdb.getty_images.GettyImagesInterface;
import com.example.astoundrushi.rushiimdb.getty_images.actor_images_response.GettyImagesResult;
import com.example.astoundrushi.rushiimdb.getty_images.actor_images_response.Image;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Astound Rushi on 9/12/2016.
 */
public class GettyImagesActorAsync extends AsyncTask
{
    public AsyncResponse delegateActorGettyImage = null;
    GettyImagesInterface gettyImagesInterface;
    CinemalyticsActorsByMovie selectedActor;

    public GettyImagesActorAsync(CinemalyticsActorsByMovie selectedActor,GettyImagesInterface gettyImagesInterface)
    {
        this.selectedActor=selectedActor;
        this.gettyImagesInterface=gettyImagesInterface;
    }

    @Override
    protected Object doInBackground(Object[] params)
    {
        Call<GettyImagesResult> callActorGettyImages=
                gettyImagesInterface.getActorImages
                        ("entertainment","display_set",selectedActor.getName(),"most_popular");
        callActorGettyImages.enqueue(new Callback<GettyImagesResult>()
        {
            @Override
            public void onResponse(Call<GettyImagesResult> call, Response<GettyImagesResult> response)
            {
                GettyImagesResult gettyImagesResult=response.body();
                List<Image> imageList=gettyImagesResult.getImages();
                for(int i=0;i<imageList.size();++i)
                {
                    if(i>10) break;
                    Actor.descriptionURL.add(i,imageList.get(i).getDisplaySizes().get(0).getUri().toString());
                }

                delegateActorGettyImage.processFinish(true);
            }

            @Override
            public void onFailure(Call<GettyImagesResult> call, Throwable t)
            {

            }
        });
        return null;
    }

    @Override
    protected void onPostExecute(Object o)
    {
        super.onPostExecute(o);
    }
}
