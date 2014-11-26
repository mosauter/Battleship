// ShootController.java

package de.htwg.battleship.controller;

import de.htwg.battleship.objects.Board;
import de.htwg.battleship.objects.Player;
import de.htwg.battleship.objects.Ship;

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
        Board board;
        if (first) {
            board = player2.getOwnBoard();
        } else {
            board = player1.getOwnBoard();
        }
        if (board.getField(x, y).isHit()) {
            return false;
        } else {
            board.getField(x, y).setHit(true);
        }
        Ship[] shipList = board.getShipList();
        for (int i = 0; i < board.getShips(); i++) {
            if (hit(shipList[i], x, y)) {
                return true;
            }
        }
        return false;
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
            if (x >= shipX && x <= xupp && y == shipY) {
                return true;
            }
        } else {
            int yupp = shipY + size;
            if (y >= shipY && y <= yupp && x == shipX) {
                return true;
            }
        }
        return false;
    }
}
