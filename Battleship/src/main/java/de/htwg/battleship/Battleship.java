// Battleship.java
package de.htwg.battleship;

import de.htwg.battleship.aview.tui.TUI;
import de.htwg.battleship.controller.IMasterController;
import de.htwg.battleship.controller.impl.MasterController;
import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.model.impl.Player;
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
     * Saves Player One.
     */
    private static IPlayer player1;
    /**
     * Saves Player Two.
     */
    private static IPlayer player2;
    /**
     * Scanner to read from stdin.
     */
    private static Scanner scanner;

    /**
     * Main-Method.
     * @param args not used
     */
    public static void main(final String[] args) {
        player1 = new Player();
        player2 = new Player();
        master = new MasterController(player1, player2);
        TUI tui = new TUI(master);
//        GUI gui = new GUI(master);
        scanner = new Scanner(System.in);
        while (true) {
            tui.processInputLine(scanner.nextLine());
        }
    }
}
