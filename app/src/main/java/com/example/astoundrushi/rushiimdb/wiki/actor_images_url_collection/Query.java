
package com.example.astoundrushi.rushiimdb.wiki.actor_images_url_collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Query
{

    @SerializedName("normalized")
    @Expose
    private List<Normalized> normalized = new ArrayList<Normalized>();
    @SerializedName("pages")
    @Expose
    private Map<String,PageDetail> pages;

    /**
     * @return The normalized
     */
    public List<Normalized> getNormalized()
    {
        return normalized;
    }

    /**
     * @param normalized The normalized
     */
    public void setNormalized(List<Normalized> normalized)
    {
        this.normalized = normalized;
    }

    /**
     * @return The pages
     */
    public Map<String,PageDetail> getPages()
    {
        return pages;
    }

    /**
     * @param pages The pages
     */
    public void setPages(Map<String,PageDetail> pages)
    {
        this.pages = pages;
    }

}
