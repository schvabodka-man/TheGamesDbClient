package apps.scvh.com.thegamesdbclient.backend.gamesdbapi;


import android.content.Context;

import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import apps.scvh.com.thegamesdbclient.R;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.rawmodels.GameRawData;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.rawmodels.images.RawScreenshot;
import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.retrievers.MetadataRetriever;
import apps.scvh.com.thegamesdbclient.backend.models.GameData;

public class RawDataConverter {

    private Context context;
    private MetadataRetriever retriever;

    public RawDataConverter(Context context, MetadataRetriever retriever) {
        this.context = context;
        this.retriever = retriever;
    }

    public GameData convertRawData(GameRawData rawData) {
        GameData data = new GameData();
        data.setId(rawData.getId());
        data.setName(rawData.getName());
        data.setPopularity(rawData.getPopularity());
        data.setRating(rawData.getRating());
        if (rawData.getStoryline() != null) {
            data.setStoryline(rawData.getStoryline());
        }
        if (rawData.getSummary() != null) {
            data.setSummary(rawData.getSummary());
        }
        if (rawData.getUrl() != null) {
            data.setUrl(rawData.getUrl());
        }
        if (rawData.getScreenshots() != null) {
            Iterator<RawScreenshot> iterator = rawData.getScreenshots().iterator();
            RawScreenshot screenshot;
            data.initScreenshotsArray();
            while (iterator.hasNext()) {
                screenshot = iterator.next();
                data.addScreenshot(screenshot.getScreenshotUrl().replace(context.getString(R.string
                        .to_replace), context.getString(R.string.repalce_with)));
            }
        }
        if (rawData.getCover() != null) {
            data.setCoverURL(rawData.getCover().getCoverUrl().replace(context.getString(R.string
                    .to_replace), context.getString(R.string.repalce_with)));
        }
        if (rawData.getReleasedTime() != 0) {
            data.setReleaseTime(new DateTime(rawData.getReleasedTime()));
        }
        if (rawData.getTime() != null) {
            if (rawData.getTime().getNormally() != 0) {
                data.setTimeToComplete(new DateTime(rawData.getTime().getNormally() * 1000));
            }
        }
        if (rawData.getEsrb() != null) {
            if (rawData.getEsrb().getSynopsis() != null) {
                if (rawData.getEsrb().getSynopsis().length() != 0) { //this is here because some of
                    // the game returns only ratings which i don't need
                    data.setEsrb(rawData.getEsrb().getSynopsis());
                }
            }
        }
        if (rawData.getPegi() != null) {
            if (rawData.getPegi().getSynopsis() != null) { //same as esrb
                if (rawData.getPegi().getSynopsis().length() != 0) {
                    data.setPegi(rawData.getPegi().getSynopsis());
                }
            }
        }
        if (rawData.getGenres() != null) {
            data.setGenres(retriever.getGenres(rawData.getGenres()));
        }
        if (rawData.getEngines() != null) {
            data.setGameEngines(retriever.getEngines(rawData.getEngines()));
        }
        return data;
    }

    public ArrayList<GameData> convertRawSearch(List<GameRawData> rawDatas) {
        ArrayList<GameData> data = new ArrayList<>();
        Iterator<GameRawData> iterator = rawDatas.iterator();
        GameRawData iterable;
        while (iterator.hasNext()) {
            iterable = iterator.next();
            data.add(convertRawData(iterable));
        }
        return data;
    }

}
