package apps.scvh.com.thegamesdbclient.backend.gamesdbapi.rawmodels.metadata;


import com.google.gson.annotations.SerializedName;

public class RawGenre {

    @SerializedName("name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
