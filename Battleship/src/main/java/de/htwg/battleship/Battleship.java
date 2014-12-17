// Battleship.java
package de.htwg.battleship;

import de.htwg.battleship.aview.tui.TUI;
import de.htwg.battleship.controller.IMasterController;
import de.htwg.battleship.controller.impl.MasterController;
import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.model.impl.Player;
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
     * Main-Method.
     * @param args not used
     */
    public static void main(final String[] args) {
//        GUI gui = new GUI();
        player1 = new Player();
        player2 = new Player();
        master = new MasterController(player1, player2);
        TUI tui = new TUI(master);
        tui.initialize();
        tui.startGame();
    }
}
