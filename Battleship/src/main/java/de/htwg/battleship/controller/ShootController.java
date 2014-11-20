// ShootController.java

package de.htwg.battleship.controller;

import de.htwg.battleship.objects.Board;
import de.htwg.battleship.objects.Player;
import de.htwg.battleship.objects.Ship;

/**
 * ShootController
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-11-19
 */
public class ShootController {

    private Player player;
    
    public ShootController(Player player) {
        this.player = player;
    }
    
    public boolean shoot(int x, int y) {
        Board board = player.getShootBoard();
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

    private boolean hit(Ship ship, int x, int y) {
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
