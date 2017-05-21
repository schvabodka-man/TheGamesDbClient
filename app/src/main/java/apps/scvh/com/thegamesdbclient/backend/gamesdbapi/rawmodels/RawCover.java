package apps.scvh.com.thegamesdbclient.backend.gamesdbapi.rawmodels;


import com.google.gson.annotations.SerializedName;

public class RawCover {

    @SerializedName("url")
    private String coverUrl;

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }
}
