// Battleship.java
package de.htwg.battleship;

import de.htwg.battleship.aview.InputMaskPlayer1;
import de.htwg.battleship.aview.InputMaskPlayer2;
import de.htwg.battleship.aview.PlaceViewer;
import de.htwg.battleship.aview.Viewer;
import de.htwg.battleship.aview.impl.FieldViewer;
import de.htwg.battleship.aview.impl.StartMenu;
import de.htwg.battleship.controller.IShipController;
import de.htwg.battleship.controller.impl.ShipController;
import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.model.impl.Player;
import de.htwg.battleship.model.impl.Ship;
import static de.htwg.battleship.util.StatCollection.SHIP_NUMBER_MAX;
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
    private static Viewer menu;
    private static IShipController sc;

    public static void main(final String[] args) {
        input = new Scanner(System.in);
        menu = new StartMenu();
        System.out.print(menu.getString());
        boolean right = true;
        while (right) {
            int i = input.nextInt();
            switch (i) {
                case 1:
                    right = false;
                    menu = new InputMaskPlayer1();
                    break;
                case 2:
                    System.exit(0);
                    break;
                default:
                    System.out.print("Bitte einen richtigen Wert eingeben!");
                    break;
            }
        }

        System.out.print(menu.getString());
        String name = input.next();
        IPlayer player1 = new Player();
        player1.setName(name);
        menu = new InputMaskPlayer2();
        System.out.print(menu.getString());
        name = input.next();
        IPlayer player2 = new Player();
        player2.setName(name);
        sc = new ShipController(player1, player2);
        while (player1.getOwnBoard().getShips() < SHIP_NUMBER_MAX) {
            menu = new PlaceViewer(player1);
            System.out.print(menu.getString());
            int x = input.nextInt();
            int y = input.nextInt();
            if (!sc.placeShip(new Ship(player1.getOwnBoard().getShips() + 2, true, x, y), true)) {
                System.err.println("Falsche Eingabe oder Kollision!");
            }
            menu = new FieldViewer(player1, player2);
            System.out.println(menu.getString());
        }
        while (player2.getOwnBoard().getShips() < SHIP_NUMBER_MAX) {
            menu = new PlaceViewer(player2);
            System.out.print(menu.getString());
            int x = input.nextInt();
            int y = input.nextInt();
            if (!sc.placeShip(new Ship(player2.getOwnBoard().getShips() + 2, true, x, y), false)) {
                System.err.println("Falsche Eingabe oder Kollision!");
            }
            menu = new FieldViewer(player1, player2);
            System.out.println(menu.getString());
        }
    }
}
