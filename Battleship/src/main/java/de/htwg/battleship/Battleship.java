// Battleship.java
package de.htwg.battleship;

import de.htwg.battleship.aview.tui.TUI;
import de.htwg.battleship.controller.IMasterController;
import de.htwg.battleship.controller.impl.MasterController;
import de.htwg.battleship.controller.impl.Viewer;
import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.model.impl.Player;
import java.util.Scanner;
/**
 * Battleship
 *
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-04
 */
public class Battleship {

    private static Scanner input;
//    private static IMasterController master;
//    private static IPlayer player1;
//    private static IPlayer player2;
//    
//    public static void main(final String[] args) {
//        input = new Scanner(System.in);
//        player1 = new Player();
//        player2 = new Player();
//        master = new MasterController(player1, player2);
//        new TUI(master);
//    }
    private static Viewer menu;
    private static IMasterController master;
    private static IPlayer player1;
    private static IPlayer player2;

    public static void main(final String[] args) {
//    	GUI gui = new GUI();
        input = new Scanner(System.in);
        player1 = new Player();
        player2 = new Player();
        master = new MasterController(player1, player2);
        TUI tui = new TUI(master);
        tui.initialize();
        tui.startGame();
    }
}
