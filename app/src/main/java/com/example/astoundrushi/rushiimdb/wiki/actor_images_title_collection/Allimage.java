
package com.example.astoundrushi.rushiimdb.wiki.actor_images_title_collection;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Allimage
{

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("size")
    @Expose
    private Integer size;
    @SerializedName("width")
    @Expose
    private Integer width;
    @SerializedName("height")
    @Expose
    private Integer height;
    @SerializedName("mime")
    @Expose
    private String mime;
    @SerializedName("ns")
    @Expose
    private Integer ns;
    @SerializedName("title")
    @Expose
    private String title;

    /**
     * @return The name
     */
    public String getName()
    {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * @return The size
     */
    public Integer getSize()
    {
        return size;
    }

    /**
     * @param size The size
     */
    public void setSize(Integer size)
    {
        this.size = size;
    }

    /**
     * @return The width
     */
    public Integer getWidth()
    {
        return width;
    }

    /**
     * @param width The width
     */
    public void setWidth(Integer width)
    {
        this.width = width;
    }

    /**
     * @return The height
     */
    public Integer getHeight()
    {
        return height;
    }

    /**
     * @param height The height
     */
    public void setHeight(Integer height)
    {
        this.height = height;
    }

    /**
     * @return The mime
     */
    public String getMime()
    {
        return mime;
    }

    /**
     * @param mime The mime
     */
    public void setMime(String mime)
    {
        this.mime = mime;
    }

    /**
     * @return The ns
     */
    public Integer getNs()
    {
        return ns;
    }

    /**
     * @param ns The ns
     */
    public void setNs(Integer ns)
    {
        this.ns = ns;
    }

    /**
     * @return The title
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * @param title The title
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

}
