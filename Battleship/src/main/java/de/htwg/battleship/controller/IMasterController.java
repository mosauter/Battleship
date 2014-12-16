//IMasterController.java

package de.htwg.battleship.controller;

import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.model.IShip;
import de.htwg.battleship.observer.IObservable;

/**
 * IMasterController is an Utility-Interface.
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-16
 */
public interface IMasterController extends IObservable {

    /**
     * Method to shoot on a board.
     * @param x x-Coordinate where to shoot.
     * @param y y-Coordinate where to shoot.
     * @param player true if the first Player shoots on the second Player
     *               false if the other way round
     * @return boolean if hit or not
     */
    boolean shoot(int x, int y, boolean player);
    /**
     * Method to place a Ship on the board.
     *
     * @param ship to add on the board
     * @param player true for the first, false for the second player
     * @return true if it is possible and set, false if not
     */
    boolean placeShip(IShip ship, boolean player);
    /**
     * Checs if someone has won.
     * @return IPlayer who has won.
     */
    IPlayer win();
    /**
     * To get a String - Representation of something the
     * Viewer has to implement.
     * @return String
     */
    String getString();
}
