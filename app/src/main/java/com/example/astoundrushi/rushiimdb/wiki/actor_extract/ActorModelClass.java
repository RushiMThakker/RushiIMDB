package com.example.astoundrushi.rushiimdb.wiki.actor_extract;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ActorModelClass
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
    @SerializedName("extract")
    @Expose
    private String extract;

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
     *     The extract
     */
    public String getExtract() {
        return extract;
    }

    /**
     * 
     * @param extract
     *     The extract
     */
    public void setExtract(String extract) {
        this.extract = extract;
    }

}