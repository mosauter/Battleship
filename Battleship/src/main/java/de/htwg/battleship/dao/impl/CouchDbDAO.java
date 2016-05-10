package de.htwg.battleship.dao.impl;

import com.google.inject.Inject;
import com.google.inject.Injector;
import de.htwg.battleship.controller.IMasterController;
import de.htwg.battleship.dao.IDAO;
import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.model.persistence.IGameSave;
import de.htwg.battleship.model.persistence.impl.CouchDbGameSave;
import de.htwg.battleship.persistence.CouchDbUtil;
import org.ektorp.CouchDbConnector;
import org.ektorp.ViewQuery;
import org.ektorp.ViewResult;

import java.util.*;

public class  CouchDbDAO implements IDAO {

    private final CouchDbConnector db;
    private final Injector injector;

    @Inject
    public CouchDbDAO(Injector injector) {
        db = CouchDbUtil.getDB();
        this.injector = injector;
    }

    @Override
    public void saveOrUpdateGame(IMasterController masterController) {
        CouchDbGameSave
                gamesave = new CouchDbGameSave();
        gamesave.saveGame(masterController);
        if(isGameExisting(masterController.getPlayer1(), masterController.getPlayer2())){
            //TODO: db.update(gamesave); needs id as second param
        } else {
            db.create(gamesave);
        }
    }

    @Override
    public boolean isGameExisting(IPlayer player1, IPlayer player2) {
        ViewQuery query = new ViewQuery()
                .allDocs()
                .includeDocs(true)
                .designDocId("_design/IGameSave")
                .viewName("find_game_by_players")
                .keys(Arrays.asList(player1.getID(), player2.getID()))
                .limit(1);

        ViewResult result = db.queryView(query);
        return result.getSize() > 0;
    }

    @Override
    public IMasterController loadGame(IPlayer player1, IPlayer player2) {
        if(isGameExisting(player1, player2)) {
            ViewQuery query = new ViewQuery()
                    .allDocs()
                    .includeDocs(true)
                    .designDocId("_design/IGameSave")
                    .viewName("find_game_by_players")
                    .keys(Arrays.asList(player1.getID(), player2.getID()))
                    .limit(1);

            ViewResult result = db.queryView(query);
            IGameSave gameSave = (IGameSave) result.getRows().get(0);
            return gameSave.restoreGame(injector);
        }

        return null;
    }

    @Override
    public List<IMasterController> listAllGames(IPlayer player) {
        List<IMasterController> list = new LinkedList<>();

        ViewQuery query = new ViewQuery()
                .allDocs()
                .includeDocs(true)
                .designDocId("_design/IGameSave")
                .viewName("find_all_by_player")
                .key(player.getID());

        for (Object o : db.queryView(query, injector.getInstance(IGameSave.class).getClass())) {
            IGameSave gameSave = (IGameSave) o;
            list.add(gameSave.restoreGame(injector));
        }

        return list;
    }
}