package apps.scvh.com.thegamesdbclient.backend.gamesdbapi.rawmodels.stats;


import com.google.gson.annotations.SerializedName;

public class GameAgeClass {

    @SerializedName("synopsis")
    private String synopsis;

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

}
