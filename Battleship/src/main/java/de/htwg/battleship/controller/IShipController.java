//IShipController.java

package de.htwg.battleship.controller;

import de.htwg.battleship.model.IShip;
import de.htwg.battleship.observer.IObservable;

/**
 * IShipController is an Utility-Interface.
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-10
 */
public interface IShipController extends IObservable {

    /**
     * Method to place a Ship on the board.
     *
     * @param ship to add on the board
     * @param player true for the first, false for the second player
     * @return true if it is possible and set, false if not
     */
    boolean placeShip(IShip ship, boolean player);
}
