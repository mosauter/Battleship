// MasterDecoratorController.java

package de.htwg.battleship.controller.impl;

import com.google.inject.Inject;
import com.google.inject.Injector;
import de.htwg.battleship.controller.IMasterController;
import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.model.IShip;
import de.htwg.battleship.observer.IObserver;
import de.htwg.battleship.util.State;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import org.apache.log4j.Logger;

/**
 * MasterDecoratorController is a decorator for the real MasterController.
 * used for timing measures and for debuging
 * implements the decorator pattern
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2015-01-10
 */
public class MasterDecoratorController implements IMasterController {

    /**
     * String constant for x presentation.
     */
    private static final String X_STRING = " x = ";
    /**
     * String constant for y presentation.
     */
    private static final String Y_STRING = " ; y = ";

    /**
     * Saves the real MasterController implementation.
     */
    private final MasterController master;
    /**
     * Saves the logger for the info messages.
     */
    private final Logger logger;

    /**
     * Public constructor which creates the MasterController
     * with the injected Players.
     * @param player1 first Player
     * @param player2 second Player
     */
    @Inject
    public MasterDecoratorController(final IPlayer player1,
            final IPlayer player2) {
        this.master = new MasterController(player1, player2);
        logger = Logger.getLogger("de.htwg.battleship.controller.impl");
    }

    @Override
    public final void shoot(final int x, final int y) {
        logger.info("shoot() is called:" + X_STRING + x + Y_STRING + y);
        master.shoot(x, y);
        logger.info("shoot() is finished:" + X_STRING + x + Y_STRING + y);
    }

    @Override
    public final void placeShip(final int x, final int y,
            final boolean orientation) {
        logger.info("placeShip() is called:" + X_STRING + x
                + Y_STRING + y + " ; orientation = " + orientation);
        master.placeShip(x, y, orientation);
        logger.info("placeShip() is finished:" + X_STRING + x
                + Y_STRING + y + " ; orientation = " + orientation);
    }

    @Override
    public final State getCurrentState() {
        logger.info("getCurrentState() is called");
        State tmp = master.getCurrentState();
        logger.info("getCurrentState() is finished: result = "
                + tmp.toString());
        return tmp;
    }

    @Override
    public final void setCurrentState(final State newState) {
        logger.info("setCurrentState() is called: new State = "
                + newState.toString());
        master.setCurrentState(newState);
        logger.info("setCurrentState() is finished: new State = "
                + newState.toString());
    }

    @Override
    public final IPlayer getPlayer1() {
        logger.info("getPlayer1() is called");
        IPlayer tmp = master.getPlayer1();
        logger.info("getPlayer1() is finished: result = " + tmp.toString());
        return tmp;
    }

    @Override
    public final IPlayer getPlayer2() {
        logger.info("getPlayer2() is called");
        IPlayer tmp = master.getPlayer2();
        logger.info("getPlayer2() is finished: result = " + tmp.toString());
        return tmp;
    }

    @Override
    public final void setPlayerName(final String name) {
        logger.info("setPlayerName() is called: Name = " + name);
        master.setPlayerName(name);
        logger.info("setPlayerName() is finished: Name = " + name);
    }

    @Override
    public final void startGame() {
        logger.info("startGame() is called");
        master.startGame();
        logger.info("startGame() is finished");
    }

    @Override
    public final void setInjector(final Injector injector) {
        logger.info("setInjector() is called: injector = "
                + injector.getClass().toString());
        master.setInjector(injector);
        logger.info("setInjector() is finished: injector = "
                + injector.getClass().toString());
    }

    @Override
    public final void addObserver(final IObserver observer) {
        logger.info("addObserver() is called: observer = "
                + observer.getClass().toString());
        master.addObserver(observer);
        logger.info("addObserver() is finished: observer = "
                + observer.getClass().toString());
    }

    @Override
    public final void notifyObserver() {
        logger.info("notifyObserver() is called");
        master.notifyObserver();
        logger.info("notifyObserver() is finished");
    }

    @Override
    public final Map<Integer, Set<Integer>> fillMap(final IShip[] shipList,
            final Map<Integer, Set<Integer>> map, final int ships) {
        logger.info("fillMap() is called: shipList = "
                + Arrays.toString(shipList)
                + " ; map = " + map.toString() + " ; ships = " + ships);
        Map<Integer, Set<Integer>> tmp = master.fillMap(shipList, map, ships);
        logger.info("fillMap() is finished: shipList = "
                + Arrays.toString(shipList)
                + " ; map = " + map.toString() + " ; ships = " + ships);
        return tmp;
    }
}
