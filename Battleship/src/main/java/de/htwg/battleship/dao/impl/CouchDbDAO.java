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

import java.util.LinkedList;
import java.util.List;

public class CouchDbDAO implements IDAO {

    private final CouchDbConnector db;
    private final Injector injector;

    @Inject
    public CouchDbDAO(Injector injector) {
        db = CouchDbUtil.getDB();
        this.injector = injector;
    }

    @Override
    public void saveOrUpdateGame(IMasterController masterController) {
        IGameSave gamesave = new CouchDbGameSave();
        gamesave.saveGame(masterController);
        if(isGameExisting(masterController.getPlayer1(), masterController.getPlayer2())){
            //TODO: db.update(gamesave); needs id as second param
        } else {
            db.create(gamesave);
        }
    }

    @Override
    public boolean isGameExisting(IPlayer player1, IPlayer player2) {
        boolean contains = false;
        ViewQuery query = new ViewQuery().allDocs().includeDocs(true);

        for (Object o : db.queryView(query, injector.getInstance(IGameSave.class).getClass())) {
            IGameSave gameSave = (IGameSave) o;
            if (gameSave.getPlayer1ID() == player1.getID() &&
                    gameSave.getPlayer2ID() == player2.getID() ||
                    gameSave.getPlayer1ID() == player2.getID() &&
                    gameSave.getPlayer2ID() == player1.getID()) {
                contains = true;
                break;
            }
        }
        return contains;
    }

    @Override
    public IMasterController loadGame(IPlayer player1, IPlayer player2) {
        for(IMasterController mc : listAllGames(player1)){
            if(mc.getPlayer1().equals(player2) || mc.getPlayer2().equals(player2)){
                return mc;
            }
        }

        return null;
    }

    @Override
    public List<IMasterController> listAllGames(IPlayer player) {
        List<IMasterController> list = new LinkedList<>();
        ViewQuery query = new ViewQuery().allDocs().includeDocs(true);

        for (Object o : db.queryView(query, injector.getInstance(IGameSave.class).getClass())) {
            IGameSave gameSave = (IGameSave) o;
            if (gameSave.getPlayer1ID() == player.getID() || gameSave.getPlayer2ID() == player.getID()) {
                list.add(gameSave.restoreGame(injector));
            }
        }

        return list;
    }
}
