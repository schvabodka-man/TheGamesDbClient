package apps.scvh.com.thegamesdbclient.backend.gamesdbapi.rawmodels;


import com.google.gson.annotations.SerializedName;

public class GameRawData {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("url")
    private String url;
    @SerializedName("summary")
    private String summary;
    @SerializedName("storyline")
    private String storyline;
    @SerializedName("aggregated_rating")
    private double rating;
    @SerializedName("popularity")
    private double popularity;
    @SerializedName("first_release_date")
    private long releasedTime;
    @SerializedName("cover")
    private RawCover cover;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getStoryline() {
        return storyline;
    }

    public void setStoryline(String storyline) {
        this.storyline = storyline;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public long getReleasedTime() {
        return releasedTime;
    }

    public void setReleasedTime(long releasedTime) {
        this.releasedTime = releasedTime;
    }

    public RawCover getCover() {
        return cover;
    }

    public void setCover(RawCover cover) {
        this.cover = cover;
    }
}