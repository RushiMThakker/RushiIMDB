
package com.example.astoundrushi.rushiimdb.wiki.actor_images_url_collection;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PageDetail
{

    @SerializedName("pageid")
    @Expose
    private Integer pageid;
    @SerializedName("ns")
    @Expose
    private Integer ns;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("imagerepository")
    @Expose
    private String imagerepository;
    @SerializedName("imageinfo")
    @Expose
    private List<Imageinfo> imageinfo = new ArrayList<Imageinfo>();

    /**
     * 
     * @return
     *     The pageid
     */
    public Integer getPageid() {
        return pageid;
    }

    /**
     * 
     * @param pageid
     *     The pageid
     */
    public void setPageid(Integer pageid) {
        this.pageid = pageid;
    }

    /**
     * 
     * @return
     *     The ns
     */
    public Integer getNs() {
        return ns;
    }

    /**
     * 
     * @param ns
     *     The ns
     */
    public void setNs(Integer ns) {
        this.ns = ns;
    }

    /**
     * 
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The imagerepository
     */
    public String getImagerepository() {
        return imagerepository;
    }

    /**
     * 
     * @param imagerepository
     *     The imagerepository
     */
    public void setImagerepository(String imagerepository) {
        this.imagerepository = imagerepository;
    }

    /**
     * 
     * @return
     *     The imageinfo
     */
    public List<Imageinfo> getImageinfo() {
        return imageinfo;
    }

    /**
     * 
     * @param imageinfo
     *     The imageinfo
     */
    public void setImageinfo(List<Imageinfo> imageinfo) {
        this.imageinfo = imageinfo;
    }

}
