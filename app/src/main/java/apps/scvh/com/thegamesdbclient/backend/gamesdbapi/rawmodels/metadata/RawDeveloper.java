package apps.scvh.com.thegamesdbclient.backend.gamesdbapi.rawmodels.metadata;


import com.google.gson.annotations.SerializedName;

import java.util.List;

import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.rawmodels.images.RawCover;

public class RawDeveloper {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("website")
    private String website;
    @SerializedName("description")
    private String description;
    @SerializedName("developed")
    private List<Integer> games;
    @SerializedName("start_date")
    private long date;
    @SerializedName("logo")
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

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Integer> getGames() {
        return games;
    }

    public void setGames(List<Integer> games) {
        this.games = games;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public RawCover getCover() {
        return cover;
    }

    public void setCover(RawCover cover) {
        this.cover = cover;
    }
}
