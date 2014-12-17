// WinController.java

package de.htwg.battleship.controller.impl;

import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.model.IShip;
import de.htwg.battleship.observer.impl.Observable;

/**
 * WinController checks if someone has won.
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-11
 */
public class WinController extends Observable {

    /**
     * Saves first Player.
     */
    private final IPlayer player1;
    /**
     * Saves second Player.
     */
    private final IPlayer player2;
    /**
     * Saves the Chain-of-Responsibility.
     * Checks if a Ship is destroyed.
     */
    private final DestroyedController dc;

    /**
     * Public Constructor.
     * Initializes intern Chain-of-Responsibility.
     * @param player1 first Player
     * @param player2 second Player
     */
    public WinController(final IPlayer player1, final IPlayer player2) {
        this.player1 = player1;
        this.player2 = player2;
        dc = new DestroyedTrueController();
    }

    /**
     * Method to check if a player wins and who wins.
     * @return the winner
     */
    public final IPlayer win() {
        if (playerDestroyed(player1)) {
            return player2;
        }
        if (playerDestroyed(player2)) {
            return player1;
        }
        return null;
    }

    /**
     * Private Utility-Method to check if a Player is fully Destroyed.
     * @param player which player should be checked
     * @return true if the Player is destroyed, false if not
     */
    private boolean playerDestroyed(final IPlayer player) {
        IShip[] shipList = player.getOwnBoard().getShipList();
        for (int i = 0; i < player.getOwnBoard().getShips(); i++) {
            if (!isDestroyed(shipList[i], player)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Private Utility-Method to pass the responsibility to the chain.
     * The chain checks if a ship is destroyed.
     * @param ship which ship should be checked
     * @param player which player owns the ship
     * @return true if the ship is fully destroyed, false if not
     */
    private boolean isDestroyed(final IShip ship, final IPlayer player) {
        return dc.responsibility(ship, player);
    }
}
