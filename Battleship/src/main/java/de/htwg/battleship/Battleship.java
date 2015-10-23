// Battleship.java
package de.htwg.battleship;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.htwg.battleship.aview.gui.GUI;
import de.htwg.battleship.aview.tui.TUI;
import de.htwg.battleship.controller.IMasterController;
import org.apache.log4j.PropertyConfigurator;

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
     * The location of the log4j-property-file.
     */
    private static final String LOG4J_PROPERTY_CONFIG = "log4j.properties";
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
     * Saves GUI.
     */
    private static GUI gui;
    /**
     * Scanner to read from stdin.
     */
    private static Scanner scanner;
    /**
     * Saves a instance of this game. Is used in a Singleton-Pattern.
     */
    private static Battleship instance;

    /**
     * Private Constructor.
     */
    private Battleship() {
        PropertyConfigurator.configure(LOG4J_PROPERTY_CONFIG);
        injector = Guice.createInjector(new BattleshipModule());
        masterController = injector.getInstance(IMasterController.class);
        tui = injector.getInstance(TUI.class);
        gui = injector.getInstance(GUI.class);
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
     * Main-Method.
     *
     * @param args not used
     */
    public static void main(final String[] args) {
        @SuppressWarnings("unused") Battleship bs = Battleship.getInstance();
        scanner = new Scanner(System.in);
        boolean done = false;
        while (!done) {
            done = tui.processInputLine(scanner.nextLine());
        }
        // exit because TUI detected 'exit' as input string
        // exit that the GUI closes too
        System.exit(0);
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
     * Getter for GUI.
     *
     * @return the GUI of the current instance
     */
    public GUI getGui() {
        return gui;
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
}
