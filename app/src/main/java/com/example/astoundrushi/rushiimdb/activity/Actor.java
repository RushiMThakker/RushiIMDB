package com.example.astoundrushi.rushiimdb.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.astoundrushi.rushiimdb.R;
import com.example.astoundrushi.rushiimdb.cinemalytics.CinemalyticsActorsByMovie;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.Serializable;

public class Actor extends AppCompatActivity
{
    CinemalyticsActorsByMovie selectedActor;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actor);

        initUI();
    }

    private void initUI()
    {
        Intent receiveSelectedActor = getIntent();
        ImageView imgActor = (ImageView) findViewById(R.id.imageActor);
        TextView actorDescription=(TextView)findViewById(R.id.textActorDescription);

        selectedActor = (CinemalyticsActorsByMovie) receiveSelectedActor.getSerializableExtra("Actor name");
        ImageLoader.getInstance().displayImage(selectedActor.getProfilePath(), imgActor);

    }
}
