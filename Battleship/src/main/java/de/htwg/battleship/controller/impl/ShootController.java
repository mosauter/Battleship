// ShootController.java

package de.htwg.battleship.controller.impl;

import de.htwg.battleship.model.IBoard;
import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.model.IShip;
import de.htwg.battleship.observer.impl.Observable;
import de.htwg.battleship.util.StatCollection;

/**
 * ShootController which controlls the shoot method.
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-11-19
 */
public class ShootController extends Observable {

    /**
     * First Player.
     */
    private final IPlayer player1;
    /**
     * Second Player.
     */
    private final IPlayer player2;

    /**
     * Public Constructor.
     * @param player1 first Player
     * @param player2 second Player
     */
    public ShootController(final IPlayer player1, final IPlayer player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    /**
     * Method to shoot on the field of a specific player.
     * @param x x-Coordinate where to shoot
     * @param y y-Coordinate where to shoot
     * @param player indicates which player shoots
     * @return true if it was a hit
     *         false if not
     */
    public final boolean shoot(final int x, final int y, final boolean player) {
        IBoard board = getBoard(player);
        if (board.isHit(x, y)) {
            return false;
        } else {
            board.shoot(x, y);
        }
        IShip[] shipList = board.getShipList();
        boolean ret = hitShipList(shipList, x, y, board.getShips());
        notifyObserver();
        return ret;
    }

    /**
     * Utility-Method if a shoot is a hit or not.
     * @param ship which ship should be test.
     * @param x x-Coordinate where to shoot
     * @param y y-Coordinate where to shoot
     * @return true if it is a hit
     */
    private boolean hit(final IShip ship, final int x, final int y) {
        int shipX = ship.getX();
        int shipY = ship.getY();
        int size = ship.getSize();
        if (ship.isOrientation()) {
            int xupp = shipX + size - 1;
            return isHit(xupp, shipX, x, y, shipY);
        } else {
            int yupp = shipY + size - 1;
            return isHit(yupp, shipY, y, x, shipX);
        }
    }

    /**
     * Utility-Method to get the Board of one player.
     * @param first if the board of the first or the second player should
     *              be returned
     * @return Board
     */
    private IBoard getBoard(final boolean first) {
        if (first) {
            return player2.getOwnBoard();
        } else {
            return player1.getOwnBoard();
        }
    }

    /**
     * Utility method to check if a ship is hit.
     * @param shipList List of all ships that could be hit
     * @param x x-Coordinate where to shoot
     * @param y y-Coordinate where to shoot
     * @param counter how many ships are on the board
     * @return true if one ship is hit
     */
    private boolean hitShipList(final IShip[] shipList, final int x,
            final int y, final int counter) {
        for (int i = 0; i < counter; i++) {
            if (hit(shipList[i], x, y)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Utility-Method to combine the Method isBetween and compare the row.
     * @param xupp upper border
     * @param xlow lower border
     * @param x value x
     * @param y value y
     * @param shipY right row shipY
     * @return true if it is a hit
     */
    private boolean isHit(final int xupp, final int xlow,
            final int x, final int y, final int shipY) {
        return StatCollection.isBetween(xupp, xlow, x) && y == shipY;
    }
}
