// BattleshipHibernateModule

package de.htwg.battleship.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import de.htwg.battleship.controller.IMasterController;
import de.htwg.battleship.controller.impl.MasterController;
import de.htwg.battleship.dao.IDAO;
import de.htwg.battleship.dao.impl.HibernateDAO;
import de.htwg.battleship.model.IBoard;
import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.model.IShip;
import de.htwg.battleship.model.impl.BoardField;
import de.htwg.battleship.model.impl.HibernateShip;
import de.htwg.battleship.model.impl.Player;
import de.htwg.battleship.model.persistence.IGameSave;
import de.htwg.battleship.model.persistence.impl.HibernateGameSave;
import de.htwg.battleship.util.IBoardValues;
import de.htwg.battleship.util.impl.BoardValues;

/**
 * BattleshipHibernateModule
 *
 * @author ms
 * @since 2016-04-20
 */
public class BattleshipHibernateModule extends AbstractModule {

    @Override
    protected final void configure() {
        bind(IMasterController.class).to(MasterController.class)
                                     .in(Singleton.class);
        bind(IBoard.class).to(BoardField.class);
        bind(IPlayer.class).to(Player.class);
        bind(IShip.class).to(HibernateShip.class);
        bind(IDAO.class).to(HibernateDAO.class);
        bind(IGameSave.class).to(HibernateGameSave.class);
        bind(IBoardValues.class).to(BoardValues.class).in(Singleton.class);
    }
}
