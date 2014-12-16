//IShootController.java

package de.htwg.battleship.controller;

import de.htwg.battleship.observer.IObservable;

/**
 * IShootController is an Utility-Interface.
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-10
 */
public interface IShootController extends IObservable {

    /**
     * Method to shoot on a board.
     * @param x x-Coordinate where to shoot.
     * @param y y-Coordinate where to shoot.
     * @param player true if the first Player shoots on the second Player
     *               false if the other way round
     * @return boolean if hit or not
     */
    boolean shoot(int x, int y, boolean player);
}
