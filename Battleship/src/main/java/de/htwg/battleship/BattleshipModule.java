// BattleshipModule.java

package de.htwg.battleship;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import de.htwg.battleship.controller.IMasterController;
import de.htwg.battleship.controller.impl.MasterController;
import de.htwg.battleship.model.IBoard;
import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.model.IShip;
import de.htwg.battleship.model.impl.Board;
import de.htwg.battleship.model.impl.Player;
import de.htwg.battleship.model.impl.Ship;

/**
 * BattleshipModule
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-19
 */
public class BattleshipModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(IMasterController.class).to(
                MasterController.class).in(Singleton.class);
        bind(IBoard.class).to(Board.class);
        bind(IShip.class).to(Ship.class);
        bind(IPlayer.class).to(Player.class);
    }
}
