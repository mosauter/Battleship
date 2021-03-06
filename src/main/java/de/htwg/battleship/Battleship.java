// Battleship.java
package de.htwg.battleship;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.htwg.battleship.aview.tui.TUI;
import de.htwg.battleship.controller.IMasterController;
import de.htwg.battleship.modules.BattleshipCouchModule;

import java.util.Scanner;

/**
 * Battleship start file.
 *
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-04
 */
public final class Battleship {

    /**
     * Saves the injector.
     */
    private static Injector injector;
    /**
     * Saves the master controller of all UIs.
     */
    private static IMasterController masterController;
    /**
     * Saves TUI.
     */
    private static TUI tui;
    /**
     * Saves a instance of this game. Is used in a Singleton-Pattern.
     */
    private static Battleship instance;

    /**
     * Private Constructor.
     */
    private Battleship() {
        injector = Guice.createInjector(new BattleshipCouchModule());
        masterController = injector.getInstance(IMasterController.class);
        tui = injector.getInstance(TUI.class);
    }

    /**
     * Singleton to get an instance of this game.
     *
     * @return the same instance of battleship
     */
    public static Battleship getInstance() {
        if (instance == null) {
            instance = new Battleship();
        }
        return instance;
    }

    /**
     * Configurable Singleton to get an instance of the game.
     *
     * @param newInstance is a boolean true:     get always a new instance of the game false:    get the instance of the
     *                    last called singleton
     *
     * @return the new or last instance of battleship
     */
    public static Battleship getInstance(boolean newInstance) {
        if (!newInstance) {
            return Battleship.getInstance();
        }
        instance = new Battleship();
        return instance;
    }

    /**
     * Getter for TUI.
     *
     * @return the TUI of the current instance
     */
    public TUI getTui() {
        return tui;
    }

    /**
     * Getter for the guice injector.
     *
     * @return the injector of the current instance
     */
    public Injector getInjector() {
        return injector;
    }

    /**
     * Getter for the master controller.
     *
     * @return the controller for the current instance
     */
    public IMasterController getMasterController() {
        return masterController;
    }

    /**
     * Main-Method.
     *
     * @param args not used
     */
    @SuppressWarnings("squid:S1147")
    public static void main(final String[] args) {
        Battleship bs = Battleship.getInstance();
        Scanner scanner = new Scanner(System.in);
        boolean done = false;
        while (!done) {
            done = tui.processInputLine(scanner.nextLine());
        }
        // exit because TUI detected 'exit' as input string
        // exit that the GUI closes too
        System.exit(0);
    }
}
