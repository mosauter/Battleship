// IMasterController.java
package de.htwg.battleship.controller;

import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.model.IShip;
import de.htwg.battleship.model.persistence.IGameSave;
import de.htwg.battleship.observer.IObservable;
import de.htwg.battleship.util.GameMode;
import de.htwg.battleship.util.State;

import java.util.Map;
import java.util.Set;

/**
 * IMasterController is an Utility-Interface. it uses intern several Controller
 *
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-16
 */
public interface IMasterController extends IObservable {

    /**
     * Method to shoot on a board.
     *
     * @param x x-Coordinate where to shoot.
     * @param y y-Coordinate where to shoot. checks the winner automatically
     *          after each shot
     */
    void shoot(int x, int y);

    /**
     * Method to place a Ship on the board.
     *
     * @param x           x-Coordinate for the start point
     * @param y           y-Coordinate for the start point
     * @param orientation of the ship, true for horizontal, false for vertical
     */
    void placeShip(int x, int y, boolean orientation);

    /**
     * To get the current State of the game. uses the different states in the
     * util Package
     *
     * @return current state
     */
    State getCurrentState();

    /**
     * Setter for a specified Viewer to change the presentation.
     *
     * @param state new Viewer
     */
    void setCurrentState(State state);

    /**
     * Getter for the first Player.
     *
     * @return first Player
     */
    IPlayer getPlayer1();

    /**
     * Getter for the second Player.
     *
     * @return second Player
     */
    IPlayer getPlayer2();

    /**
     * Method to set the whole players profile. Which players name and id is set
     * is addicted to the current state of the game. This method should be used
     * in the UIs where an ID is given by a specific login method that all users
     * share.
     *
     * @param name of the player which will be displayed
     * @param id   of the player to identify if internally
     */
    void setPlayerProfile(String name, int id);

    /**
     * Method to set the players name. Which players name is set is addicted to
     * the current state of the game
     *
     * @param name name of the Player
     */
    void setPlayerName(String name);

    /**
     * Method to start a game or if the game is in the END-state to restart the
     * game.
     */
    void startGame();

    /**
     * Method to fill a Map with ship coordinates.
     *
     * @param shipList specified ships
     * @param map      Map where to save the ships
     * @param ships    how much ships are in the list
     *
     * @return the new Map
     */
    Map<Integer, Set<Integer>> fillMap(IShip[] shipList,
                                       Map<Integer, Set<Integer>> map,
                                       int ships);

    /**
     * Method to get in the Options state, where you can set the GameMode. The
     * Standard GameMode is the {@link de.htwg.battleship.util.GameMode#NORMAL}
     */
    void configureGame();

    /**
     * Getter for the GameMode.
     *
     * @return the current GameMode
     */
    GameMode getGameMode();

    /**
     * Setter for the GameMode.
     *
     * @param gm the new GameMode
     */
    void setGameMode(GameMode gm);

    /**
     * Method to get in the OPTIONS-state. only valid in the START-state
     */
    void configure();

    /**
     * Method to set the board size. only valid in the OPTIONS-state
     *
     * @param boardSize the new board size
     */
    void setBoardSize(int boardSize);

    /**
     * Method to set the max ship number. only valid in the OPTIONS-state
     *
     * @param shipNumber the new max number of ships
     */
    void setShipNumber(int shipNumber);

    /**
     * Method to restore a whole game with a given state. TODO: make it only
     * valid in specific states
     *
     * @param save the specified {@link IGameSave} which represents a game
     */
    void restoreGame(IGameSave save) throws IllegalArgumentException;
}
