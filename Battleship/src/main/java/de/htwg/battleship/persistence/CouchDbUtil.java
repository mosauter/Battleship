package de.htwg.battleship.persistence;

import org.apache.log4j.Logger;
import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbInstance;

import java.net.MalformedURLException;

public class CouchDbUtil {

    private static final CouchDbConnector db;
    private static final Logger LOGGER = Logger.getLogger(CouchDbUtil.class);

    static {
        HttpClient client = null;
        try {
            client = new StdHttpClient.Builder()
                .url("http://couchdb-phasex.herokuapp.com")
                .username("phasexgame").password("webtech_ws1516")
                .socketTimeout(10000).build();

        } catch (MalformedURLException e) {
            LOGGER.error("Malformed URL", e);
        }
        CouchDbInstance dbInstance = new StdCouchDbInstance(client);
        db = dbInstance.createConnector("battleship_mf", true);
        db.createDatabaseIfNotExists();
    }

    private CouchDbUtil() {
    }

    public static CouchDbConnector getDB() {
        return db;
    }
}
