package apps.scvh.com.thegamesdbclient.backend.gamesdbapi;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import apps.scvh.com.thegamesdbclient.backend.models.GameData;

public class RawDataConverter {

    GameData convertRawData(GameRawData rawData) {
        GameData data = new GameData();
        data.setId(rawData.getId());
        data.setName(rawData.getName());
        data.setPopularity(rawData.getPopularity());
        data.setRating(rawData.getRating());
        if (data.getStoryline() != null) {
            data.setStoryline(rawData.getStoryline());
        }
        if (data.getSummary() != null) {
            data.setSummary(rawData.getSummary());
        }
        if (data.getUrl() != null) {
            data.setUrl(rawData.getUrl());
        }
        return data;
    }

    ArrayList<GameData> convertRawSearch(List<GameRawData> rawDatas) {
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
