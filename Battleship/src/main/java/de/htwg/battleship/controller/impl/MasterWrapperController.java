// MasterWrapperController.java

package de.htwg.battleship.controller.impl;

import com.google.inject.Inject;
import com.google.inject.Injector;
import de.htwg.battleship.controller.IMasterController;
import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.observer.IObserver;
import de.htwg.battleship.util.State;
import org.apache.log4j.Logger;

/**
 * MasterWrapperController is a decorator for the real MasterController.
 * used for timing measures and for debuging
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2015-01-10
 */
public class MasterWrapperController implements IMasterController {

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
    public MasterWrapperController(final IPlayer player1,
            final IPlayer player2) {
        this.master = new MasterController(player1, player2);
        logger = Logger.getLogger("de.htwg.battleship.controller.impl");
    }

    @Override
    public final void shoot(final int x, final int y) {
        logger.info("shoot() is called: x = " + x + " ; y = " + y);
        master.shoot(x, y);
        logger.info("shoot() is finished: x = " + x + " ; y = " + y);
    }

    @Override
    public final void placeShip(final int x, final int y,
            final boolean orientation) {
        logger.info("placeShip() is called: x = " + x
                + " ; y = " + y + " ; orientation = " + orientation);
        master.placeShip(x, y, orientation);
        logger.info("placeShip() is finished: x = " + x
                + " ; y = " + y + " ; orientation = " + orientation);
    }

    @Override
    public final State getCurrentState() {
        logger.info("getCurrentState() is called");
        State tmp = master.getCurrentState();
        logger.info("getCurrentState() is finished");
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
        logger.info("getPlayer1() is finished");
        return tmp;
    }

    @Override
    public final IPlayer getPlayer2() {
        logger.info("getPlayer2() is called");
        IPlayer tmp = master.getPlayer2();
        logger.info("getPlayer2() is finished");
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
                + injector.toString());
        master.setInjector(injector);
        logger.info("setInjector() is finished: injector = "
                + injector.toString());
    }

    @Override
    public final void addObserver(final IObserver observer) {
        logger.info("addObserver() is called: observer = "
                + observer.toString());
        master.addObserver(observer);
        logger.info("addObserver() is finished: observer = "
                + observer.toString());
    }

    @Override
    public final void notifyObserver() {
        logger.info("notifyObserver() is called");
        master.notifyObserver();
        logger.info("notifyObserver() is finished");
    }
}