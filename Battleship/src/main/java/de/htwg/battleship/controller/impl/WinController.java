// WinController.java

package de.htwg.battleship.controller.impl;

import de.htwg.battleship.controller.IWinLooseController;
import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.model.IShip;

/**
 * WinController
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-11
 */
public class WinController implements IWinLooseController {

    private final IPlayer player1;
    private final IPlayer player2;
    private final DestroyedController dc;
    
    public WinController(IPlayer player1, IPlayer player2) {
        this.player1 = player1;
        this.player2 = player2;
        dc = new DestroyedTrueController();
    }
    
    public IPlayer win() {
        if (winPlayer(player1)) {
            return player1;
        }
        if (winPlayer(player2)) {
            return player2;
        }
        return null;
    }
    
    private boolean winPlayer(IPlayer player) {
        IShip[] shipList = player.getOwnBoard().getShipList();
        for (IShip ship : shipList) {
            if (!isDestroyed(ship, player)) {
                return false;
            }
        }
        return true;
    }
    
    private boolean isDestroyed(IShip ship, IPlayer player) {
        return dc.responsibility(ship, player);
    }
}
