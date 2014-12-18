//IMasterController.java
package de.htwg.battleship.controller;

import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.observer.IObservable;
import de.htwg.battleship.util.State;

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
     */
    void shoot(int x, int y);

    /**
     * Method to place a Ship on the board.
     * @param x x-Coordinate for the start point
     * @param y y-Coordinate for the start point
     * @param orientation of the ship, true for horizontal, false for vertical
     */
    void placeShip(int x, int y, boolean orientation);

    /**
     * Checs if someone has won.
     */
    void win();

    /**
     * To get the current State of the game.
     * uses the different states in the util Package
     * @return current state
     */
    State getCurrentState();

    /**
     * Setter for a specified Viewer to change the presentation.
     * @param state new Viewer
     */
    void setState(State state);

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

    /**
     * Method to set the players name.
     * Which players name is set is addicted to the current state of the game
     * @param name name of the Player
     */
    void setPlayerName(String name);
}
