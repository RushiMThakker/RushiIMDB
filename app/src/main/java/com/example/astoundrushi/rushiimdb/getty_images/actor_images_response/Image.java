
package com.example.astoundrushi.rushiimdb.getty_images.actor_images_response;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Image
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("display_sizes")
    @Expose
    private List<DisplaySize> displaySizes = new ArrayList<DisplaySize>();

    /**
     * @return The id
     */
    public String getId()
    {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(String id)
    {
        this.id = id;
    }

    /**
     * @return The displaySizes
     */
    public List<DisplaySize> getDisplaySizes()
    {
        return displaySizes;
    }

    /**
     * @param displaySizes The display_sizes
     */
    public void setDisplaySizes(List<DisplaySize> displaySizes)
    {
        this.displaySizes = displaySizes;
    }

}
