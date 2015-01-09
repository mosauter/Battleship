// Battleship.java
package de.htwg.battleship;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.htwg.battleship.aview.gui.GUI;
import de.htwg.battleship.aview.tui.TUI;
import de.htwg.battleship.controller.IMasterController;
import java.util.Scanner;
/**
 * Battleship start file.
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-04
 */
public final class Battleship {

    /**
     * Private Constructor.
     */
    private Battleship() { }
    /**
     * MasterController of the entire game.
     */
    private static IMasterController master;
    /**
     * Saves TUI.
     */
    private static TUI tui;
    /**
     * Scanner to read from stdin.
     */
    private static Scanner scanner;

    /**
     * Main-Method.
     * @param args not used
     */
    public static void main(final String[] args) {
        Injector injector = Guice.createInjector(new BattleshipModule());
        master = injector.getInstance(IMasterController.class);
        master.setInjector(injector);
        tui = new TUI(master);
        GUI gui = new GUI(master);
        scanner = new Scanner(System.in);
        while (true) {
            tui.processInputLine(scanner.nextLine());
        }
    }
}
