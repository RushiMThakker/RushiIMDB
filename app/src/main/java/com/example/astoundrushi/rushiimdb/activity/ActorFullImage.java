package com.example.astoundrushi.rushiimdb.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.astoundrushi.rushiimdb.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

public class ActorFullImage extends AppCompatActivity
{

    @Override
    public String[] fileList()
    {
        return super.fileList();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actor_full_image);

        Intent i=getIntent();
        String actorPhotoURL=i.getStringExtra("ActorImageURL");

        ImageView fullView=(ImageView)findViewById(R.id.actorFullImage);
        ImageLoader.getInstance().displayImage(actorPhotoURL,fullView,App.options,new SimpleImageLoadingListener()
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
    }
}
