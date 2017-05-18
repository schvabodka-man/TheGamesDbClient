package apps.scvh.com.thegamesdbclient.backend.gamesdbapi;


import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(name = "Game", strict = false)
public class GameRawData {

    @Element(name = "id")
    private int id;
    @Element(name = "GameTitle")
    private String gameTitle;
    @Element(name = "PlatformId")
    private String platform;
    @Element(name = "Overview")
    private String overview;
    @Element(name = "ESRB")
    private String ESRB;
    @Element(name = "ReleaseDate")
    private String releaseDate;
    @Element(name = "Players")
    private String players;
    @Element(name = "Co-op")
    private String coop;
    @Element(name = "Youtube")
    private String youtube;
    @Element(name = "Publisher")
    private String publisher;
    @Element(name = "Developer")
    private String developer;
    @Element(name = "Rating")
    private double rating;
//    private ArrayList<GameRawData> simillarGames;
//    private ArrayList<String> alternativeNames;
//    private ArrayList<String> genres;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGameTitle() {
        return gameTitle;
    }

    public void setGameTitle(String gameTitle) {
        this.gameTitle = gameTitle;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getESRB() {
        return ESRB;
    }

    public void setESRB(String ESRB) {
        this.ESRB = ESRB;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPlayers() {
        return players;
    }

    public void setPlayers(String players) {
        this.players = players;
    }

    public String getCoop() {
        return coop;
    }

    public void setCoop(String coop) {
        this.coop = coop;
    }

    public String getYoutube() {
        return youtube;
    }

    public void setYoutube(String youtube) {
        this.youtube = youtube;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

}
