
package com.example.astoundrushi.rushiimdb.wiki.actor_images_title_collection;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Query
{

    @SerializedName("allimages")
    @Expose
    private List<Allimage> allimages = new ArrayList<Allimage>();

    /**
     * @return The allimages
     */
    public List<Allimage> getAllimages()
    {
        return allimages;
    }

    /**
     * @param allimages The allimages
     */
    public void setAllimages(List<Allimage> allimages)
    {
        this.allimages = allimages;
    }

}
