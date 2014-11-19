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
        }
        Ship[] shipList = board.getShipList();
        for (Ship ship : shipList) {
            if (hit(ship, x, y)) {
                return true;
            }
        }
        return false;
    }

    private boolean hit(Ship ship, int x, int y) {
        int[] pos = ship.getPositionStart();
        int size = ship.getSize();
        if (ship.isOrientation()) {
            int xupp = pos[0] + size;
            if (x > pos[0] && x < xupp && y == pos[1]) {
                return true;
            }
        } else {
            int yupp = pos[1] + size;
            if (y > pos[1] && y < yupp && x == pos[0]) {
                return true;
            }
        }
        return false;
    }
}
