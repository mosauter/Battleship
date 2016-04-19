// Battleship.java
package de.htwg.battleship;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.htwg.battleship.aview.tui.TUI;
import de.htwg.battleship.controller.IMasterController;
import de.htwg.battleship.dao.IDAO;
import de.htwg.battleship.dao.impl.HibernateDAO;
import de.htwg.battleship.model.impl.BoardField;
import de.htwg.battleship.model.impl.Player;
import org.apache.log4j.PropertyConfigurator;

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
     * Saves a instance of this game. Is used in a Singleton-Pattern.
     */
    private static Battleship instance;

    /**
     * Private Constructor.
     */
    private Battleship() {
        PropertyConfigurator
            .configure(getClass().getResource(LOG4J_PROPERTY_CONFIG));
        injector = Guice.createInjector(new BattleshipModule());
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
     * @param newInstance is a boolean true:     get always a new instance of
     *                    the game false:    get the instance of the last called
     *                    singleton
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
        IMasterController mc = bs.getMasterController();
        IDAO idao = new HibernateDAO();
        idao.saveOrUpdateGame(mc);
        idao.listAllGames(new Player(new BoardField()));


        //        Scanner scanner = new Scanner(System.in);
        //        boolean done = false;
        //        while (!done) {
        //            done = tui.processInputLine(scanner.nextLine());
        //        }
        // exit because TUI detected 'exit' as input string
        // exit that the GUI closes too
        System.exit(0);
    }
}
