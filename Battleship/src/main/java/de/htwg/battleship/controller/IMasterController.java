//IMasterController.java
package de.htwg.battleship.controller;

import com.google.inject.Injector;
import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.model.IShip;
import de.htwg.battleship.observer.IObservable;
import de.htwg.battleship.util.State;
import java.util.Map;
import java.util.Set;

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
     * checks the winner automatically after each shot
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
     * To get the current State of the game.
     * uses the different states in the util Package
     * @return current state
     */
    State getCurrentState();

    /**
     * Setter for a specified Viewer to change the presentation.
     * @param state new Viewer
     */
    void setCurrentState(State state);

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

    /**
     * Method to start a game or if the game is in the END-state to
     * restart the game.
     */
    void startGame();

    /**
     * Setter for the injector.
     * @param injector
     */
    void setInjector(Injector injector);

    /**
     * Method to fill a Map with ship coordinates.
     * @param shipList specified ships
     * @param map Map where to save the ships
     * @param ships how much ships are in the list
     * @return the new Map
     */
    Map<Integer, Set<Integer>> fillMap(IShip[] shipList,
        Map<Integer, Set<Integer>> map, int ships);
}
