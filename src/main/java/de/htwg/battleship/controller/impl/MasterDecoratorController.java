// superDecoratorController.java

package de.htwg.battleship.controller.impl;

import com.google.inject.Inject;
import com.google.inject.Injector;
import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.model.IShip;
import de.htwg.battleship.model.persistence.IGameSave;
import de.htwg.battleship.observer.IObserver;
import de.htwg.battleship.util.GameMode;
import de.htwg.battleship.util.IBoardValues;
import de.htwg.battleship.util.State;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;

/**
 * superDecoratorController is a decorator for the real superController. used for timing measures and for debuging
 * implements the decorator pattern
 *
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2015-01-10
 */
@SuppressWarnings("unused")
public class MasterDecoratorController extends MasterController {

    /**
     * String constant for x presentation.
     */
    private static final String X_STRING = " x = ";
    /**
     * String constant for y presentation.
     */
    private static final String Y_STRING = " ; y = ";

    /**
     * Saves the LOGGER for the info messages.
     */
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Public constructor which creates the superController with the injected Players.
     *
     * @param player1 first Player
     * @param player2 second Player
     */
    @Inject
    public MasterDecoratorController(final IPlayer player1, final IPlayer player2, Injector in,
                                     final IBoardValues values) {
        super(player1, player2, in, values);
    }

    @Override
    public final void shoot(final int x, final int y) {
        LOGGER.info("shoot() is called:" + X_STRING + x + Y_STRING + y);
        super.shoot(x, y);
        LOGGER.info("shoot() is finished:" + X_STRING + x + Y_STRING + y);
    }

    @Override
    public final void placeShip(final int x, final int y, final boolean orientation) {
        LOGGER.info("placeShip() is called:" + X_STRING + x + Y_STRING + y +
                    " ; orientation = " + orientation);
        super.placeShip(x, y, orientation);
        LOGGER.info("placeShip() is finished:" + X_STRING + x + Y_STRING + y +
                    " ; orientation = " + orientation);
    }

    @Override
    public final State getCurrentState() {
        LOGGER.info("getCurrentState() is called");
        State tmp = super.getCurrentState();
        LOGGER.info("getCurrentState() is finished: result = " + tmp.toString());
        return tmp;
    }

    @Override
    public final void setCurrentState(final State newState) {
        LOGGER.info("setCurrentState() is called: new State = " + newState.toString());
        super.setCurrentState(newState);
        LOGGER.info("setCurrentState() is finished: new State = " + newState.toString());
    }

    @Override
    public final IPlayer getPlayer1() {
        LOGGER.info("getPlayer1() is called");
        IPlayer tmp = super.getPlayer1();
        LOGGER.info("getPlayer1() is finished: result = " + tmp.toString());
        return tmp;
    }

    @Override
    public final IPlayer getPlayer2() {
        LOGGER.info("getPlayer2() is called");
        IPlayer tmp = super.getPlayer2();
        LOGGER.info("getPlayer2() is finished: result = " + tmp.toString());
        return tmp;
    }

    @Override
    public void setPlayerProfile(String name, String id) {
        LOGGER.info("setPlayersProfile() is called: name = " + name + " id = " + id);
        super.setPlayerProfile(name, id);
        LOGGER.info("setPlayersProfile() is finished: name = " + name + " id = " + id);
    }

    @Override
    public final void setPlayerName(final String name) {
        LOGGER.info("setPlayerName() is called: Name = " + name);
        super.setPlayerName(name);
        LOGGER.info("setPlayerName() is finished: Name = " + name);
    }

    @Override
    public final void startGame() {
        LOGGER.info("startGame() is called");
        super.startGame();
        LOGGER.info("startGame() is finished");
    }

    @Override
    public final void addObserver(final IObserver observer) {
        LOGGER.info("addObserver() is called: observer = " + observer.getClass().toString());
        super.addObserver(observer);
        LOGGER.info("addObserver() is finished: observer = " + observer.getClass().toString());
    }

    @Override
    public final void notifyObserver() {
        LOGGER.info("notifyObserver() is called");
        super.notifyObserver();
        LOGGER.info("notifyObserver() is finished");
    }

    @Override
    public final Map<Integer, Set<Integer>> fillMap(final IShip[] shipList, final Map<Integer, Set<Integer>> map,
                                                    final int ships) {
        LOGGER.info("fillMap() is called: shipList = " + Arrays.toString(shipList) +
                    " ; map = " + map.toString() + " ; ships = " + ships);
        Map<Integer, Set<Integer>> tmp = super.fillMap(shipList, map, ships);
        LOGGER.info("fillMap() is finished: shipList = " + Arrays.toString(shipList) +
                    " ; map = " + map.toString() + " ; ships = " + ships);
        return tmp;
    }

    @Override
    public final void configureGame() {
        LOGGER.info("configureGame() is called");
        super.configureGame();
        LOGGER.info("configureGame() is finished");
    }

    @Override
    public final GameMode getGameMode() {
        LOGGER.info("getGameMode() is called");
        GameMode tmp = super.getGameMode();
        LOGGER.info("getGameMode() is finished: result = " + tmp.toString());
        return tmp;
    }

    @Override
    public final void setGameMode(final GameMode gm) {
        LOGGER.info("setGameMode() is called: new Mode = " + gm.toString());
        super.setGameMode(gm);
        LOGGER.info("setGameMode() is finished: new Mode = " + gm.toString());
    }

    @Override
    public final void configure() {
        LOGGER.info("configure() is called");
        super.configure();
        LOGGER.info("configure() is finished");
    }

    @Override
    public int getBoardSize() {
        LOGGER.info("getBoardSize() is called");
        int boardSize = super.getBoardSize();
        LOGGER.info("getBoardSize() is finished: result = " + boardSize);
        return boardSize;
    }

    @Override
    public void setBoardSize(final int boardSize) {
        LOGGER.info("setBoardSize() is called: newBoardSize = " + boardSize);
        super.setBoardSize(boardSize);
        LOGGER.info("setBoardSize() is finished: newBoardSize = " + boardSize);
    }

    @Override
    public int getShipNumber() {
        LOGGER.info("getShipNumber() is called");
        int max = super.getShipNumber();
        LOGGER.info("getShipNumber() is finished: result = " + max);
        return max;
    }

    public final void setBoardValues(final int boardSize) {
        LOGGER.info("setBoardSize() is called: new size = " + boardSize);
        super.setBoardSize(boardSize);
        LOGGER.info("setBoardSize() is finished: new size = " + boardSize);
    }

    @Override
    public final void setShipNumber(final int shipNumber) {
        LOGGER.info("setShipNumber() is called: new ship number = " + shipNumber);
        super.setShipNumber(shipNumber);
        LOGGER.info("setShipNumber() is finished: new ship number = " + shipNumber);
    }

    @Override
    public void restoreGame(IGameSave save) {
        LOGGER.info("restoreGame() is called");
        super.restoreGame(save);
        LOGGER.info("restoreGame() is finished");
    }
}
