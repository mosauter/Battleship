// ShipController.java
package de.htwg.battleship.controller.impl;

import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.model.IShip;
import de.htwg.battleship.observer.impl.Observable;

/**
 * ShipController to place the ships and test if there are collisions.
 * to test that it uses another controller which uses a
 * chain-of-responsibility-pattern
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-11-27
 */
public class ShipController extends Observable {

    /**
     * Controller with a chain of responsibility.
     */
    private final CollisionController collisionController;

    /**
     * Controller with a chain of responsibility.
     */
    private final BorderController borderController;

    /**
     * Public Constructor.
     */
    public ShipController() {
        this.collisionController = new CollisionOrientationBothTrue();
        this.borderController = new BorderTrueController();
    }

    /**
     * Method to place a Ship on a field.
     * @param ship ship which should be placed
     * @param player on which board the ship should be placed
     * @return true if the ship was placed false if there was a
     *         collision or the ship is out of the field
     */
    public final boolean placeShip(final IShip ship, final IPlayer player) {
        if (!borderController.responsibility(ship)) {
            return false;
        }
        return playerShip(ship, player);
    }

    /**
     * Utility-Method to set a ship for a specified player.
     * @param ship to place
     * @param player which player
     * @return true if it is set, false if not
     */
    private boolean playerShip(final IShip ship, final IPlayer player) {
        IShip[] shipList = player.getOwnBoard().getShipList();
        for (int i = 0; i < player.getOwnBoard().getShips(); i++) {
            if (isCollision(ship, shipList[i])) {
                return false;
            }
        }
        player.getOwnBoard().addShip(ship);
        notifyObserver();
        return true;
    }

    /**
     * Tests if two ships collide.
     * @param ship on the board
     * @param shipIn to place
     * @return true if they collide
     */
    private boolean isCollision(final IShip ship, final IShip shipIn) {
        return collisionController.responsibility(shipIn, ship);
    }
}
