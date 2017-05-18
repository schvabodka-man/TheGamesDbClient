package apps.scvh.com.thegamesdbclient.backend.gamesdbapi;


import java.net.URL;
import java.util.ArrayList;

public class Game {

    private int id;
    private String gameTitle;
    private String platform;
    private String overview;
    private String ESRB;
    private ArrayList<String> genres;
    private String players;
    private boolean coop;
    private URL youtube;
    private String developer;
    private double rating;
    private ArrayList<Game> simillarGames;
}
