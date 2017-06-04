package apps.scvh.com.thegamesdbclient.backend.gamesdbapi.converters;


import org.joda.time.DateTime;

import java.io.IOException;

import apps.scvh.com.thegamesdbclient.backend.gamesdbapi.rawmodels.metadata.RawDeveloper;
import apps.scvh.com.thegamesdbclient.backend.models.GameDeveloper;

public class DeveloperConverter {

    public GameDeveloper convertDeveloper(RawDeveloper developer) throws
            IOException {
        GameDeveloper developerFinal = new GameDeveloper();
        developerFinal.setName(developer.getName());
        developerFinal.setId(developer.getId());
        if (developer.getDescription() != null) {
            developerFinal.setDescription(developer.getDescription());
        }
        if (developer.getWebsite() != null) {
            developerFinal.setWebsite(developer.getWebsite());
        }
        if (developer.getCover() != null) {
            developerFinal.setCoverLink(developer.getCover().getCoverUrl());
        }
        if (developer.getDate() != 0) {
            developerFinal.setDate(new DateTime(developer.getDate()));
        }
        return developerFinal;
    }

}
