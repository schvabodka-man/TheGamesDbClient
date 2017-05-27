package apps.scvh.com.thegamesdbclient.backend.gamesdbapi.rawmodels.images;


import com.google.gson.annotations.SerializedName;

public class RawScreenshot {

    @SerializedName("url")
    private String screenshotUrl;

    public String getScreenshotUrl() {
        return screenshotUrl;
    }

    public void setScreenshotUrl(String screenshotUrl) {
        this.screenshotUrl = screenshotUrl;
    }
}
