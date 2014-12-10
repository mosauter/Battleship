//ICollisionControler.java

package de.htwg.battleship.controller;

import de.htwg.battleship.model.IShip;

/**
 * ICollisionControler is an Utility-Interface.
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-10
 */
public interface ICollisionController {

    /**
     * Method which checks if the ship collides.
     * @param shipIn first ship
     * @param ship second ship
     * @return true if collides
     */
    boolean isCollision(IShip shipIn, IShip ship);

    /**
     * Checks if the implementation is responsible for the case.
     * @param shipIn first ship
     * @param ship second ship
     * @return true if they collide
     */
    boolean responsibility(IShip shipIn, IShip ship);
}
