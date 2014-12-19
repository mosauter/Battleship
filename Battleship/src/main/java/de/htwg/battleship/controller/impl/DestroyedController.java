// DestroyedController.java

package de.htwg.battleship.controller.impl;

import de.htwg.battleship.model.IBoard;
import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.model.IShip;

/**
 * DestroyedController to check if a player is completely destroyed.
 * implements Chain-of-Responsibility
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-11
 */
public abstract class DestroyedController {

    /**
     * Saves the orientation of the ship.
     * used to check if the implementation is responsible for the specific case
     */
    boolean ship;
    /**
     * Reference to the next implementation of the controller.
     */
    DestroyedController next;

    /**
     * Method to check if a ship is destroyed of a player.
     * @param ship specified ship
     * @param player specified player, owner of the ship
     * @return true if the ship is completely destroyed
     *         false if not
     */
    protected abstract boolean isDestroyed(IShip ship, IPlayer player);

    /**
     * Method to check if the implementation is responsible for the case.
     * @param ship specified ship
     * @param player specified player, owner of the ship
     * @return true if the ship is completely destroyed
     *         false if not
     */
    public final boolean responsibility(final IShip ship,
            final IPlayer player) {
        if (ship.isOrientation() == this.ship) {
            return isDestroyed(ship, player);
        }
        return next.responsibility(ship, player);
    }
}

/**
 * Implementation for the Destroyed Controller.
 * responsible for the case the ships orientation is true
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 */
class DestroyedTrueController extends DestroyedController {

    /**
     * Public constructor.
     * implements also the next controller in the chain
     */
    public DestroyedTrueController() {
        this.ship = true;
        this.next = new DestroyedFalseController();
    }

    @Override
    protected boolean isDestroyed(final IShip ship, final IPlayer player) {
        int xlow = ship.getX();
        int xupp = xlow + ship.getSize();
        int y = ship.getY();
        IBoard board = player.getOwnBoard();
        for (int i = xlow; i < xupp; i++) {
            if (!board.isHit(i, y)) {
                return false;
            }
        }
        return true;
    }
}

/**
 * Implementation for the Destroyed Controller.
 * responsible for the case the ships orientation is false
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 */
class DestroyedFalseController extends DestroyedController {

    /**
     * Public Constructor.
     * last controller in the chain
     */
    public DestroyedFalseController() {
        this.ship = false;
    }

    @Override
    protected boolean isDestroyed(final IShip ship, final IPlayer player) {
        int ylow = ship.getY();
        int yupp = ylow + ship.getSize();
        int x = ship.getX();
        IBoard board = player.getOwnBoard();
        for (int i = ylow; i < yupp; i++) {
            if (!board.isHit(x, i)) {
                return false;
            }
        }
        return true;
    }
}
