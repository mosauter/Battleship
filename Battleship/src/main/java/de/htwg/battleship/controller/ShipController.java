// ShipController.java

package de.htwg.battleship.controller;

import de.htwg.battleship.objects.Player;
import de.htwg.battleship.objects.Ship;
import de.htwg.battleship.util.StatCollection;

/**
 * ShipController to place the ships and test if there are collisions.
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
     * Public Constructor.
     * @param player1 first player
     * @param player2 second player
     */
    public ShipController(final Player player1, final Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    /**
     * Method to place a Ship on the board.
     * @param ship to add on the board
     * @param player true for the first, false for the second player
     * @return true if it is possible and set, false if not
     */
    public boolean placeShip(final Ship ship, final boolean player) {
        if (player) {
            return playerShip(ship, player1);
        }
        return playerShip(ship, player2);
    }

    /**
     * Utility-Method to set a ship for a specified player.
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
     * @param ship on the board
     * @param shipIn to place
     * @return true if they collide
     */
    private boolean isCollision(Ship ship, Ship shipIn) {
        if (shipIn.isOrientation() && ship.isOrientation()) {
            return bothOriTrue(ship, shipIn);
        }
        if (!shipIn.isOrientation() && !ship.isOrientation()) {
            return bothOriFalse(ship, shipIn);
        }
        if (shipIn.isOrientation()) {
            return oriDiff(shipIn, ship);
        }
        return oriDiff(ship, shipIn);
    }

    /**
     * Utility if both ships are horicontal.
     * @param ship on board
     * @param shipIn to place
     * @return if collision
     */
    private boolean bothOriTrue(final Ship ship, final Ship shipIn) {
        int xinlow = shipIn.getX();
        int xinupp = xinlow + shipIn.getSize();
        int yin = shipIn.getY();
        int xlow = ship.getX();
        int xupp = xlow + ship.getSize();
        int y = ship.getY();
        for (int i = xlow; i <= xupp; i++) {
            if (StatCollection.isBetween(xinlow, xinupp, i) && y == yin) {
                return true;
            }
        }
        return false;
    }

    /**
     * Utility if both ships are vertical.
     * @param ship on board
     * @param shipIn to place
     * @return if collision
     */
    private boolean bothOriFalse(final Ship ship, final Ship shipIn) {
        int yinlow = shipIn.getY();
        int yinupp = yinlow + shipIn.getSize();
        int xin = shipIn.getX();
        int ylow = ship.getY();
        int yupp = ylow + ship.getSize();
        int x = ship.getX();
        for (int i = ylow; i <= yupp; i++) {
            if (StatCollection.isBetween(yinlow, yinupp, i) && x == xin) {
                return true;
            }
        }
        return false;
    }

    /**
     * Utility if the ships have different Orientations.
     * @param shipX orientation true
     * @param shipY orientation false
     * @return if collision
     */
    private boolean oriDiff(final Ship shipX, final Ship shipY) {
        int xinlow = shipX.getX();
        int xinupp = xinlow + shipX.getSize();
        int yin = shipX.getY();
        int ylow = shipY.getY();
        int yupp = ylow + shipY.getSize();
        int x = shipY.getX();
        if (StatCollection.isBetween(ylow, yupp, yin)) {
            if (StatCollection.isBetween(xinlow, xinupp, x)) {
                return true;
            }
        }
        return false;
    }
}
