// DestroyedController.java

package de.htwg.battleship.controller.impl;

import de.htwg.battleship.model.IBoard;
import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.model.IShip;

/**
 * DestroyedController
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-11
 */
public abstract class DestroyedController {

    boolean ship;
    DestroyedController next;
    
    protected abstract boolean isDestroyed(IShip ship, IPlayer player);
    
    public boolean responsibility(IShip ship, IPlayer player) {
        if (ship.isOrientation() == this.ship) {
            return isDestroyed(ship, player);
        }
        return next.responsibility(ship, player);
    }
}

class DestroyedTrueController extends DestroyedController {

    public DestroyedTrueController() {
        this.ship = true;
        this.next = new DestroyedFalseController();
    }

    @Override
    protected boolean isDestroyed(IShip ship, IPlayer player) {
        int xlow = ship.getX();
        int xupp = xlow + ship.getSize();
        int y = ship.getY();
        IBoard board = player.getOwnBoard();
        for (int i = xlow; i < xupp; i++) {
            if (!board.getField(i, y).isHit()) {
                return false;
            }
        }
        return true;
    }
}

class DestroyedFalseController extends DestroyedController {

    public DestroyedFalseController() {
        this.ship = false;
    }

    @Override
    protected boolean isDestroyed(IShip ship, IPlayer player) {
        int ylow = ship.getY();
        int yupp = ylow + ship.getSize();
        int x = ship.getX();
        IBoard board = player.getOwnBoard();
        for (int i = ylow; i < yupp; i++) {
            if (!board.getField(x, i).isHit()) {
                return false;
            }
        }
        return true;
    }
}
