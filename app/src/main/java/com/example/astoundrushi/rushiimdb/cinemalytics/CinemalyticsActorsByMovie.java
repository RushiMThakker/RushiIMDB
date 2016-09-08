package com.example.astoundrushi.rushiimdb.cinemalytics;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CinemalyticsActorsByMovie
{

    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("Gender")
    @Expose
    private String gender;
    @SerializedName("ProfilePath")
    @Expose
    private String profilePath;
    @SerializedName("Rating")
    @Expose
    private Double rating;

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
     * @return The name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name The Name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return The gender
     */
    public String getGender()
    {
        return gender;
    }

    /**
     * @param gender The Gender
     */
    public void setGender(String gender)
    {
        this.gender = gender;
    }

    /**
     * @return The profilePath
     */
    public String getProfilePath()
    {
        return profilePath;
    }

    /**
     * @param profilePath The ProfilePath
     */
    public void setProfilePath(String profilePath)
    {
        this.profilePath = profilePath;
    }

    /**
     * @return The rating
     */
    public Double getRating()
    {
        return rating;
    }

    /**
     * @param rating The Rating
     */
    public void setRating(Double rating)
    {
        this.rating = rating;
    }

}
