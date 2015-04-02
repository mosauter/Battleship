// Battleship.java
package de.htwg.battleship;

import java.util.Scanner;

import org.apache.log4j.PropertyConfigurator;

import com.google.inject.Guice;
import com.google.inject.Injector;

import de.htwg.battleship.aview.gui.GUI;
import de.htwg.battleship.aview.tui.TUI;

/**
 * Battleship start file.
 * 
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-04
 */
public final class Battleship {

    /**
     * Private Constructor.
     */
    private Battleship() {}

    /**
     * Saves TUI.
     */
    private static TUI     tui;
    /**
     * Scanner to read from stdin.
     */
    private static Scanner scanner;

    /**
     * Main-Method.
     * 
     * @param args
     *            not used
     */
    public static void main(final String[] args) {
        PropertyConfigurator.configure("log4j.properties");
        Injector injector = Guice.createInjector(new BattleshipModule());
        tui = injector.getInstance(TUI.class);
        injector.getInstance(GUI.class);
        scanner = new Scanner(System.in);
        while (true) {
            tui.processInputLine(scanner.nextLine());
        }
    }
}
