// ShipController.java
package de.htwg.battleship.controller.impl;

import de.htwg.battleship.controller.IShipController;
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
public class ShipController extends Observable implements IShipController {

    /**
     * Player one.
     */
    private final IPlayer player1;
    /**
     * Player two.
     */
    private final IPlayer player2;

    /**
     * Controller with a chain of responsibility.
     */
    private CollisionController cc;

    /**
     * Controller with a chain of responsibility.
     */
    private BorderController bc;

    /**
     * Public Constructor.
     *
     * @param player1 first player
     * @param player2 second player
     */
    public ShipController(final IPlayer player1, final IPlayer player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.cc = new CollisionOrientationBothTrue();
        this.bc = new BorderTrueController();
    }

    @Override
    public final boolean placeShip(final IShip ship, final boolean player) {
        if (player) {
            return checkXY(ship, player1);
        }
        return checkXY(ship, player2);
    }

    private boolean checkXY(final IShip ship, final IPlayer player) {
        if (!bc.responsibility(ship)) {
            return false;
        }
        return playerShip(ship, player);
    }
            
    /**
     * Utility-Method to set a ship for a specified player.
     *
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
     *
     * @param ship on the board
     * @param shipIn to place
     * @return true if they collide
     */
    private boolean isCollision(final IShip ship, final IShip shipIn) {
        return cc.responsibility(shipIn, ship);
    }
}
