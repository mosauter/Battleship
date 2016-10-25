package de.htwg.battleship.dao.impl;

import com.google.inject.Inject;
import com.google.inject.Injector;
import de.htwg.battleship.controller.IMasterController;
import de.htwg.battleship.dao.IDAO;
import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.model.persistence.IGameSave;
import de.htwg.battleship.model.persistence.impl.CouchDbGameSave;
import de.htwg.battleship.persistence.CouchDbUtil;
import de.htwg.battleship.persistence.CouchRepository;
import org.ektorp.CouchDbConnector;

import java.util.LinkedList;
import java.util.List;

public class CouchDbDAO implements IDAO {

    private final CouchDbConnector db;
    private final Injector injector;
    private final CouchRepository repo;

    @Inject
    public CouchDbDAO(Injector injector) {
        db = CouchDbUtil.getDB();
        this.injector = injector;
        repo = new CouchRepository(CouchDbGameSave.class, db, true);
    }

    @Override
    public void saveOrUpdateGame(IMasterController masterController) {
        CouchDbGameSave gamesave = new CouchDbGameSave();
        gamesave.saveGame(masterController);
        db.create(gamesave);
    }

    @Override
    public boolean isGameExisting(IPlayer player1, IPlayer player2) {
        return !repo.findByPlayers(player1, player2).isEmpty();
    }

    @Override
    public IMasterController loadGame(IPlayer player1, IPlayer player2) {
        List<CouchDbGameSave> result = repo.findByPlayers(player1, player2);
        if (result == null || result.isEmpty()) {
            return null;
        }

        return result.get(0).restoreGame(injector);
    }

    @Override
    public List<IGameSave> listAllGames(IPlayer player) {
        List<IGameSave> saves = new LinkedList<>();
        for (CouchDbGameSave save : repo.findByPlayer(player)) {
            saves.add(save);
        }
        return saves;
    }
}
