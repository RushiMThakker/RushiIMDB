package com.example.astoundrushi.rushiimdb.cinemalytics;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CinemalyticsSongsByMovie
{
    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("Duration")
    @Expose
    private Double duration;

    /**
     * @return The id
     */
    public String getId()
    {
        return id;
    }

    /**
     * @param id The Id
     */
    public void setId(String id)
    {
        this.id = id;
    }

    /**
     * @return The title
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * @param title The Title
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /**
     * @return The duration
     */
    public Double getDuration()
    {
        return duration;
    }

    /**
     * @param duration The Duration
     */
    public void setDuration(Double duration)
    {
        this.duration = duration;
    }

}
