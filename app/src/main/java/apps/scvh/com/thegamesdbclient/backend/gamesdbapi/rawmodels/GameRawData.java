package apps.scvh.com.thegamesdbclient.backend.gamesdbapi.rawmodels;


import com.google.gson.annotations.SerializedName;

import java.util.List;

import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.rawmodels.images.RawCover;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.rawmodels.images.RawScreenshot;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.rawmodels.stats.CompletionTime;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.rawmodels.stats.GameAgeClass;

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
    @SerializedName("screenshots")
    private List<RawScreenshot> screenshots;
    @SerializedName("cover")
    private RawCover cover;
    @SerializedName("time_to_beat")
    private CompletionTime time;
    @SerializedName("pegi")
    private GameAgeClass pegi;
    @SerializedName("esrb")
    private GameAgeClass esrb;
    @SerializedName("genres")
    private List<Integer> genres;
    @SerializedName("developers")
    private List<Integer> developers;
    @SerializedName("game_engines")
    private List<Integer> engines;
    @SerializedName("themes")
    private List<Integer> themes;
    @SerializedName("game_modes")
    private List<Integer> gameModes;
    @SerializedName("player_perspectives")
    private List<Integer> perspectives;
    @SerializedName("games")
    private List<Integer> simillarGames;

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

    public List<RawScreenshot> getScreenshots() {
        return screenshots;
    }

    public void setScreenshots(List<RawScreenshot> cover) {
        this.screenshots = cover;
    }

    public RawCover getCover() {
        return cover;
    }

    public void setCover(RawCover cover) {
        this.cover = cover;
    }

    public CompletionTime getTime() {
        return time;
    }

    public void setTime(CompletionTime time) {
        this.time = time;
    }

    public GameAgeClass getPegi() {
        return pegi;
    }

    public void setPegi(GameAgeClass pegi) {
        this.pegi = pegi;
    }

    public GameAgeClass getEsrb() {
        return esrb;
    }

    public void setEsrb(GameAgeClass esrb) {
        this.esrb = esrb;
    }

    public List<Integer> getGenres() {
        return genres;
    }

    public void setGenres(List<Integer> genres) {
        this.genres = genres;
    }

    public List<Integer> getDevelopers() {
        return developers;
    }

    public void setDevelopers(List<Integer> developers) {
        this.developers = developers;
    }

    public List<Integer> getEngines() {
        return engines;
    }

    public void setEngines(List<Integer> engines) {
        this.engines = engines;
    }

    public List<Integer> getThemes() {
        return themes;
    }

    public void setThemes(List<Integer> themes) {
        this.themes = themes;
    }

    public List<Integer> getGameModes() {
        return gameModes;
    }

    public void setGameModes(List<Integer> gameModes) {
        this.gameModes = gameModes;
    }

    public List<Integer> getPerspectives() {
        return perspectives;
    }

    public void setPerspectives(List<Integer> perspectives) {
        this.perspectives = perspectives;
    }

    public List<Integer> getSimillarGames() {
        return simillarGames;
    }

    public void setSimillarGames(List<Integer> simillarGames) {
        this.simillarGames = simillarGames;
    }
}
