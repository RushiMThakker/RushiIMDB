package com.example.astoundrushi.rushiimdb.cinemalytics;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CinemalyticsMovieByYear implements Serializable
{

    @SerializedName("Id")
    @Expose
    private String id;
    @SerializedName("ImdbId")
    @Expose
    private String imdbId;
    @SerializedName("OriginalTitle")
    @Expose
    private String originalTitle;
    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("Description")
    @Expose
    private String description;
    @SerializedName("TrailerLink")
    @Expose
    private String trailerLink;
    @SerializedName("TrailerEmbedCode")
    @Expose
    private String trailerEmbedCode;
    @SerializedName("Country")
    @Expose
    private String country;
    @SerializedName("Region")
    @Expose
    private String region;
    @SerializedName("Genre")
    @Expose
    private String genre;
    @SerializedName("RatingCount")
    @Expose
    private Integer ratingCount;
    @SerializedName("Rating")
    @Expose
    private Double rating;
    @SerializedName("TotalCriticReviews")
    @Expose
    private Integer totalCriticReviews;
    @SerializedName("CensorRating")
    @Expose
    private String censorRating;
    @SerializedName("ReleaseDate")
    @Expose
    private String releaseDate;
    @SerializedName("Runtime")
    @Expose
    private Integer runtime;
    @SerializedName("Budget")
    @Expose
    private Long budget;
    @SerializedName("Revenue")
    @Expose
    private Long revenue;
    @SerializedName("PosterPath")
    @Expose
    private String posterPath;
    @SerializedName("PosterReleased")
    @Expose
    private Integer posterReleased;
    @SerializedName("LastUpdateTime")
    @Expose
    private String lastUpdateTime;

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
     * @return The imdbId
     */
    public String getImdbId()
    {
        return imdbId;
    }

    /**
     * @param imdbId The ImdbId
     */
    public void setImdbId(String imdbId)
    {
        this.imdbId = imdbId;
    }

    /**
     * @return The originalTitle
     */
    public String getOriginalTitle()
    {
        return originalTitle;
    }

    /**
     * @param originalTitle The OriginalTitle
     */
    public void setOriginalTitle(String originalTitle)
    {
        this.originalTitle = originalTitle;
    }

    /**
     * @return The title
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * @param title The Title
     */
    public void setTitle(String title)
    {
        this.title = title;
    }

    /**
     * @return The description
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * @param description The Description
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * @return The trailerLink
     */
    public String getTrailerLink()
    {
        return trailerLink;
    }

    /**
     * @param trailerLink The TrailerLink
     */
    public void setTrailerLink(String trailerLink)
    {
        this.trailerLink = trailerLink;
    }

    /**
     * @return The trailerEmbedCode
     */
    public String getTrailerEmbedCode()
    {
        return trailerEmbedCode;
    }

    /**
     * @param trailerEmbedCode The TrailerEmbedCode
     */
    public void setTrailerEmbedCode(String trailerEmbedCode)
    {
        this.trailerEmbedCode = trailerEmbedCode;
    }

    /**
     * @return The country
     */
    public String getCountry()
    {
        return country;
    }

    /**
     * @param country The Country
     */
    public void setCountry(String country)
    {
        this.country = country;
    }

    /**
     * @return The region
     */
    public String getRegion()
    {
        return region;
    }

    /**
     * @param region The Region
     */
    public void setRegion(String region)
    {
        this.region = region;
    }

    /**
     * @return The genre
     */
    public String getGenre()
    {
        return genre;
    }

    /**
     * @param genre The Genre
     */
    public void setGenre(String genre)
    {
        this.genre = genre;
    }

    /**
     * @return The ratingCount
     */
    public Integer getRatingCount()
    {
        return ratingCount;
    }

    /**
     * @param ratingCount The RatingCount
     */
    public void setRatingCount(Integer ratingCount)
    {
        this.ratingCount = ratingCount;
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

    /**
     * @return The totalCriticReviews
     */
    public Integer getTotalCriticReviews()
    {
        return totalCriticReviews;
    }

    /**
     * @param totalCriticReviews The TotalCriticReviews
     */
    public void setTotalCriticReviews(Integer totalCriticReviews)
    {
        this.totalCriticReviews = totalCriticReviews;
    }

    /**
     * @return The censorRating
     */
    public String getCensorRating()
    {
        return censorRating;
    }

    /**
     * @param censorRating The CensorRating
     */
    public void setCensorRating(String censorRating)
    {
        this.censorRating = censorRating;
    }

    /**
     * @return The releaseDate
     */
    public String getReleaseDate()
    {
        return releaseDate;
    }

    /**
     * @param releaseDate The ReleaseDate
     */
    public void setReleaseDate(String releaseDate)
    {
        this.releaseDate = releaseDate;
    }

    /**
     * @return The runtime
     */
    public Integer getRuntime()
    {
        return runtime;
    }

    /**
     * @param runtime The Runtime
     */
    public void setRuntime(Integer runtime)
    {
        this.runtime = runtime;
    }

    /**
     * @return The budget
     */
    public Long getBudget()
    {
        return budget;
    }

    /**
     * @param budget The Budget
     */
    public void setBudget(Long budget)
    {
        this.budget = budget;
    }

    /**
     * @return The revenue
     */
    public Long getRevenue()
    {
        return revenue;
    }

    /**
     * @param revenue The Revenue
     */
    public void setRevenue(Long revenue)
    {
        this.revenue = revenue;
    }

    /**
     * @return The posterPath
     */
    public String getPosterPath()
    {
        return posterPath;
    }

    /**
     * @param posterPath The PosterPath
     */
    public void setPosterPath(String posterPath)
    {
        this.posterPath = posterPath;
    }

    /**
     * @return The posterReleased
     */
    public Integer getPosterReleased()
    {
        return posterReleased;
    }

    /**
     * @param posterReleased The PosterReleased
     */
    public void setPosterReleased(Integer posterReleased)
    {
        this.posterReleased = posterReleased;
    }

    /**
     * @return The lastUpdateTime
     */
    public String getLastUpdateTime()
    {
        return lastUpdateTime;
    }

    /**
     * @param lastUpdateTime The LastUpdateTime
     */
    public void setLastUpdateTime(String lastUpdateTime)
    {
        this.lastUpdateTime = lastUpdateTime;
    }

}
