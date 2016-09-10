
package com.example.astoundrushi.rushiimdb.wiki.actor_images_title_collection;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Continue
{

    @SerializedName("aicontinue")
    @Expose
    private String aicontinue;
    @SerializedName("continue")
    @Expose
    private String _continue;

    /**
     * @return The aicontinue
     */
    public String getAicontinue()
    {
        return aicontinue;
    }

    /**
     * @param aicontinue The aicontinue
     */
    public void setAicontinue(String aicontinue)
    {
        this.aicontinue = aicontinue;
    }

    /**
     * @return The _continue
     */
    public String getContinue()
    {
        return _continue;
    }

    /**
     * @param _continue The continue
     */
    public void setContinue(String _continue)
    {
        this._continue = _continue;
    }

}
