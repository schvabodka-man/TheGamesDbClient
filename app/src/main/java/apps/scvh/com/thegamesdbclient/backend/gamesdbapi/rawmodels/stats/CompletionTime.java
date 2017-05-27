package apps.scvh.com.thegamesdbclient.backend.gamesdbapi.rawmodels.stats;


import com.google.gson.annotations.SerializedName;

public class CompletionTime {

    @SerializedName("normally")
    private long normally;

    public long getNormally() {
        return normally;
    }

    public void setNormally(long normally) {
        this.normally = normally;
    }

}
