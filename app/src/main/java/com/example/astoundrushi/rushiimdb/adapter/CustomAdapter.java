/*
package com.example.astoundrushi.rushiimdb.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.astoundrushi.rushiimdb.R;

import java.util.ArrayList;

*/
/**
 * Created by Astound Rushi on 9/5/2016.
 *//*

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.Holder>
{
    ArrayList songs, songDuration;
    LayoutInflater inflater;
    Context context;

    public CustomAdapter(Context context, ArrayList<String> songs, ArrayList<Double> songDuration)
    {
        this.context = context;
        this.songs = songs;
        this.songDuration = songDuration;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.custom_viewpager_layout, null);
        Holder holder=new Holder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position)
    {
        holder.songName.setText(Html.fromHtml(songs.get(position).toString()));
        holder.songDuration.setText(songDuration.get(position).toString());
    }

    @Override
    public long getItemId(int position)
    {
        return position;
    }

    @Override
    public int getItemCount()
    {
        return songs.size();
    }

    public class Holder extends RecyclerView.ViewHolder
    {
        TextView songName;
        TextView songDuration;
        public Holder(View itemView)
        {
            super(itemView);

            songName = (TextView) itemView.findViewById(R.id.textSongName);
            songName.setClickable(true);
            songName.setMovementMethod(LinkMovementMethod.getInstance());

            songDuration = (TextView) itemView.findViewById(R.id.textSongName);
        }
    }
}
*/
