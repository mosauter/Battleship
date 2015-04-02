// BattleshipModule.java

package de.htwg.battleship;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

import de.htwg.battleship.controller.IMasterController;
import de.htwg.battleship.controller.impl.MasterDecoratorController;
import de.htwg.battleship.model.IBoard;
import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.model.IShip;
import de.htwg.battleship.model.impl.BoardField;
import de.htwg.battleship.model.impl.Player;
import de.htwg.battleship.model.impl.Ship;

/**
 * BattleshipModule provides a configuration of the game with
 * Dependency Injection managed with guice.
 * 
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-19
 */
public class BattleshipModule extends AbstractModule {

    @Override
    protected final void configure() {
        bind(IMasterController.class).to(MasterDecoratorController.class)
                                     .in(Singleton.class);
        bind(IBoard.class).to(BoardField.class);
        bind(IPlayer.class).to(Player.class);
        bind(IShip.class).to(Ship.class);
    }
}
