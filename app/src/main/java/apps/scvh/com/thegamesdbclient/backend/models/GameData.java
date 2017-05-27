package apps.scvh.com.thegamesdbclient.backend.models;

import org.joda.time.DateTime;

import java.util.ArrayList;

public class GameData {

    private int id;
    private String name;
    private String url;
    private String summary;
    private String storyline;
    private ArrayList<String> imageURLs;
    private double rating;
    private double popularity;
    private DateTime timeToComplete;
    private DateTime releaseTime;
    private String coverURL;
    private String pegi;
    private String esrb;
    private ArrayList<String> gameEngines;
    private ArrayList<String> genres;

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

    public DateTime getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(DateTime releaseTime) {
        this.releaseTime = releaseTime;
    }

    public ArrayList<String> getScreenshots() {
        return imageURLs;
    }

    public void addScreenshot(String imageURL) {
        imageURLs.add(imageURL);
    }

    public void initScreenshotsArray() {
        imageURLs = new ArrayList<>();
    }

    public ArrayList<String> getImageURLs() {
        return imageURLs;
    }

    public void setImageURLs(ArrayList<String> imageURLs) {
        this.imageURLs = imageURLs;
    }

    public String getCoverURL() {
        return coverURL;
    }

    public void setCoverURL(String coverURL) {
        this.coverURL = coverURL;
    }

    public DateTime getTimeToComplete() {
        return timeToComplete;
    }

    public void setTimeToComplete(DateTime timeToComplete) {
        this.timeToComplete = timeToComplete;
    }

    public String getPegi() {
        return pegi;
    }

    public void setPegi(String pegi) {
        this.pegi = pegi;
    }

    public String getEsrb() {
        return esrb;
    }

    public void setEsrb(String esrb) {
        this.esrb = esrb;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public ArrayList<String> getGameEngines() {
        return gameEngines;
    }

    public void setGameEngines(ArrayList<String> gameEngines) {
        this.gameEngines = gameEngines;
    }
}
