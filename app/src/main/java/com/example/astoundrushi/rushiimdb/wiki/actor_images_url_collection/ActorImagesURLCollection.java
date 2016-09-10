
package com.example.astoundrushi.rushiimdb.wiki.actor_images_url_collection;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ActorImagesURLCollection
{

    @SerializedName("batchcomplete")
    @Expose
    private String batchcomplete;
    @SerializedName("query")
    @Expose
    private Query query;

    /**
     * @return The batchcomplete
     */
    public String getBatchcomplete()
    {
        return batchcomplete;
    }

    /**
     * @param batchcomplete The batchcomplete
     */
    public void setBatchcomplete(String batchcomplete)
    {
        this.batchcomplete = batchcomplete;
    }

    /**
     * @return The query
     */
    public Query getQuery()
    {
        return query;
    }

    /**
     * @param query The query
     */
    public void setQuery(Query query)
    {
        this.query = query;
    }

}
