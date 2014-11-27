// ShipController.java

package de.htwg.battleship.controller;

import de.htwg.battleship.objects.Player;
import de.htwg.battleship.objects.Ship;

/**
 * ShipController
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-11-27
 */
public class ShipController {

    private final Player player1;
    private final Player player2;
    
    public ShipController(final Player player1, final Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }
    
    public boolean placeShip(Ship ship, boolean player) {
        if (player) {
            return playerShip(ship, player1);
        }
        return playerShip(ship, player2);
    }

    private boolean playerShip(Ship ship, Player player) {
        Ship[] shipList = player.getOwnBoard().getShipList();
        for (Ship shipIn : shipList) {
            if (isCollision(ship, shipIn)) {
                return false;
            }
        }
        return true;
    }

    private boolean isCollision(Ship ship, Ship shipIn) {
        int xinlow, xinupp, yin;
        //if (shipIn.isOrientation()) {
            xinlow = shipIn.getX();
            xinupp = xinlow + shipIn.getSize();
            yin = shipIn.getY();
        //}
        int xlow, xupp, y;
        //if (ship.isOrientation()) {
            xlow = ship.getX();
            xupp = xlow + ship.getSize();
            y = ship.getY();
        //}
        
        for (int i = xlow; i < xupp; i++) {
            if (i <= xinupp && i >= xinlow && y == yin) {
                return true;
            }
        }
        return false;
    }
}
