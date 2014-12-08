// ShipController.java
package de.htwg.battleship.controller;

import de.htwg.battleship.model.Player;
import de.htwg.battleship.model.Ship;

/**
 * ShipController to place the ships and test if there are collisions.
 * to test that it uses another controller which uses a
 * chain-of-responsibility-pattern
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-11-27
 */
public class ShipController {

    /**
     * Player one.
     */
    private final Player player1;
    /**
     * Player two.
     */
    private final Player player2;

    /**
     * Controller with a chain of responsibility.
     */
    private CollisionController cc;

    /**
     * Public Constructor.
     *
     * @param player1 first player
     * @param player2 second player
     */
    public ShipController(final Player player1, final Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        createCC();
    }

    /**
     * Method to place a Ship on the board.
     *
     * @param ship to add on the board
     * @param player true for the first, false for the second player
     * @return true if it is possible and set, false if not
     */
    public final boolean placeShip(final Ship ship, final boolean player) {
        if (player) {
            return playerShip(ship, player1);
        }
        return playerShip(ship, player2);
    }

    /**
     * Utility-Method to set a ship for a specified player.
     *
     * @param ship to place
     * @param player which player
     * @return true if it is set, false if not
     */
    private boolean playerShip(final Ship ship, final Player player) {
        Ship[] shipList = player.getOwnBoard().getShipList();
        for (Ship shipIn : shipList) {
            if (isCollision(ship, shipIn)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Tests if two ships collide.
     *
     * @param ship on the board
     * @param shipIn to place
     * @return true if they collide
     */
    private boolean isCollision(final Ship ship, final Ship shipIn) {
        return cc.responsibility(shipIn, ship);
    }

    /**
     * Utility Method to create the chain-of-responsibility.
     */
    private void createCC() {
        this.cc = new CollisionOrientationBothTrue();
        this.cc.setNext(new CollisionOrientationBothFalse());
        this.cc.next.setNext(new CollisionOrientationFirstTrue());
        this.cc.next.next.setNext(new CollisionOrientationFirstFalse());
    }
}
