// ShootController.java

package de.htwg.battleship.controller;

import de.htwg.battleship.objects.Board;
import de.htwg.battleship.objects.Player;
import de.htwg.battleship.objects.Ship;
import de.htwg.battleship.util.StatCollection;

/**
 * ShootController.
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-11-19
 */
public class ShootController {

    /**
     * First Player.
     */
    private final Player player1;
    /**
     * Second Player.
     */
    private final Player player2;

    /**
     * Public Constructor.
     * @param player1 first Player
     * @param player2 second Player
     */
    public ShootController(final Player player1, final Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    /**
     * Method to shoot on a board.
     * @param x x-Coordinate where to shoot.
     * @param y y-Coordinate where to shoot.
     * @param first true if the first Player shoots on the second Player
     *              false if the other way round
     * @return boolean if hit or not
     */
    public final boolean shoot(final int x, final int y, final boolean first) {
        Board board = getBoard(first);
        if (board.getField(x, y).isHit()) {
            return false;
        } else {
            board.getField(x, y).setHit(true);
        }
        Ship[] shipList = board.getShipList();
        return hitShipList(shipList, x, y, board.getShips());
    }

    /**
     * Utility-Method if a shoot is a hit or not.
     * @param ship which ship should be test.
     * @param x x-Coordinate where to shoot
     * @param y y-Coordinate where to shoot
     * @return true if it is a hit
     */
    private boolean hit(final Ship ship, final int x, final int y) {
        int shipX = ship.getX();
        int shipY = ship.getY();
        int size = ship.getSize();
        if (ship.isOrientation()) {
            int xupp = shipX + size;
            return isHit(xupp, shipX, x, y, shipY);
        } else {
            int yupp = shipY + size;
            return isHit(yupp, shipY, y, x, shipX);
        }
    }

    /**
     * Utility-Method to get the Board of one player.
     * @param first if the board of the first or the second player should
     *              be returned
     * @return Board
     */
    private Board getBoard(final boolean first) {
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
    private boolean hitShipList(final Ship[] shipList, final int x,
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
