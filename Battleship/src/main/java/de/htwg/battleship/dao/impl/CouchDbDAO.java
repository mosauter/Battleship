package de.htwg.battleship.dao.impl;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.htwg.battleship.BattleshipModule;
import de.htwg.battleship.dao.IDAO;
import de.htwg.battleship.controller.IMasterController;
import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.model.persistence.IGameSave;
import de.htwg.battleship.model.persistence.impl.CouchDbGameSave;
import de.htwg.battleship.persistence.CouchDbUtil;
import org.ektorp.CouchDbConnector;
import org.ektorp.ViewQuery;

import java.util.List;

public class CouchDbDAO implements IDAO {

    private final CouchDbConnector db;
    private final Injector injector;

    public CouchDbDAO() {
        db = CouchDbUtil.getDB();
        injector = Guice.createInjector(new BattleshipModule());
    }

    @Override
    public void saveOrUpdateGame(IMasterController masterController) {
        IGameSave gamesave = new CouchDbGameSave();
        gamesave.saveGame(masterController);
        if(isGameExisting(masterController.getPlayer1(), masterController.getPlayer2())){
            db.update(gamesave);
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
        return null;
    }

    @Override
    public List<IMasterController> listAllGames(IPlayer player) {
        return null;
    }
}