
package com.example.astoundrushi.rushiimdb.wiki.actor_extract;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class Query {

    @SerializedName("pages")
    @Expose
    private Map<String,ActorModelClass> pages;

    public Map<String, ActorModelClass> getPages()
    {
        return pages;
    }

    public void setPages(Map<String, ActorModelClass> pages)
    {
        this.pages = pages;
    }
}
