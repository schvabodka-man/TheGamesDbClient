package apps.scvh.com.thegamesdbclient.backend.gamesdbapi.converters;


import android.content.Context;

import org.joda.time.DateTime;

import java.io.IOException;
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
    private MetadataRetriever metadataRetriever;

    public RawDataConverter(Context context, MetadataRetriever metadataRetriever) {
        this.context = context;
        this.metadataRetriever = metadataRetriever;
    }

    public GameData convertRawData(GameRawData rawData) {
        GameData data = new GameData();
        convertBasic(rawData, data);
        convertPictures(rawData, data);
        convertStory(rawData, data);
        convertTime(rawData, data);
        convertAgeRating(rawData, data);
        convertMetadata(rawData, data);
        convertLightGameData(rawData, data);
        return data;
    }

    private void convertBasic(GameRawData rawData, GameData data) {
        data.setId(rawData.getId());
        data.setName(rawData.getName());
        data.setPopularity(rawData.getPopularity());
        data.setRating(rawData.getRating());
        if (rawData.getUrl() != null) {
            data.setUrl(rawData.getUrl());
        }
    }

    private void convertStory(GameRawData rawData, GameData data) {
        if (rawData.getStoryline() != null) {
            data.setStoryline(rawData.getStoryline());
        }
        if (rawData.getSummary() != null) {
            data.setSummary(rawData.getSummary());
        }
    }

    private void convertPictures(GameRawData rawData, GameData data) {
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
    }

    private void convertAgeRating(GameRawData rawData, GameData data) {
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
    }

    private void convertTime(GameRawData rawData, GameData data) {
        if (rawData.getReleasedTime() != 0) {
            data.setReleaseTime(new DateTime(rawData.getReleasedTime()));
        }
        if (rawData.getTime() != null) {
            if (rawData.getTime().getNormally() != 0) {
                data.setTimeToComplete(new DateTime(rawData.getTime().getNormally() * 1000));
            }
        }
    }

    private void convertMetadata(GameRawData rawData, GameData data) {
        if (rawData.getGenres() != null) {
            data.setGenres(metadataRetriever.getGenres(rawData.getGenres()));
        }
        if (rawData.getEngines() != null) {
            data.setGameEngines(metadataRetriever.getEngines(rawData.getEngines()));
        }
        if (rawData.getThemes() != null) {
            data.setThemes(metadataRetriever.getThemes(rawData.getThemes()));
        }
        if (rawData.getGameModes() != null) {
            data.setGameModes(metadataRetriever.getGamemodes(rawData.getGameModes()));
        }
        if (rawData.getPerspectives() != null) {
            data.setPerspective(metadataRetriever.getPerspectives(rawData.getPerspectives()));
        }
        if (rawData.getDevelopers() != null) {
            try {
                metadataRetriever.getLightDeveloperMetadata(rawData.getDevelopers().get(0))
                        .subscribe(data::setDeveloper);// don't need
                // more than 1 developer at time
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void convertLightGameData(GameRawData rawData, GameData data) {
        if (rawData.getSimillarGames() != null) {
            Iterator<Integer> iterator = rawData.getSimillarGames().iterator();
            ArrayList<GameData> similarGamesConverted = new ArrayList<>();
            while (iterator.hasNext()) {
                similarGamesConverted.add(convertRawData(metadataRetriever.getRawSimillarGameData
                        (iterator.next())));
            }
            data.setGameData(similarGamesConverted);
        }
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
