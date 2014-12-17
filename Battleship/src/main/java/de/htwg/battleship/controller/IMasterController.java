//IMasterController.java
package de.htwg.battleship.controller;

import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.observer.IObservable;

/**
 * IMasterController is an Utility-Interface.
 * it uses intern several Controller
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-16
 */
public interface IMasterController extends IObservable {

    /**
     * Method to shoot on a board.
     *
     * @param x x-Coordinate where to shoot.
     * @param y y-Coordinate where to shoot.
     * @param player true if the first Player shoots on the second Player false
     * if the other way round
     */
    void shoot(int x, int y, IPlayer player);

    /**
     * Method to place a Ship on the board.
     * @param x x-Coordinate for the start point
     * @param y y-Coordinate for the start point
     * @param orientation of the ship, true for horizontal, false for vertical
     * @param player true for the first, false for the second player
     */
    void placeShip(int x, int y, boolean orientation, IPlayer player);

    /**
     * Checs if someone has won.
     */
    void win();

    /**
     * To get a String - Representation of something the Viewer has to
     * implement.
     * @return String
     */
    String getString();

    /**
     * Setter for a specified Viewer to change the presentation.
     * @param view new Viewer
     */
    void setViewer(Viewer view);

    /**
     * Getter for the first Player.
     * @return first Player
     */
    IPlayer getPlayer1();

    /**
     * Getter for the second Player.
     * @return second Player
     */
    IPlayer getPlayer2();
}
