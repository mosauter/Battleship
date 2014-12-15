// Battleship.java
package de.htwg.battleship;

import de.htwg.battleship.aview.Viewer;
import de.htwg.battleship.aview.impl.FieldViewer;
import de.htwg.battleship.aview.impl.InputMaskPlayer1;
import de.htwg.battleship.aview.impl.InputMaskPlayer2;
import de.htwg.battleship.aview.impl.PlaceViewer;
import de.htwg.battleship.aview.impl.ShootMenu;
import de.htwg.battleship.aview.impl.StartMenu;
import de.htwg.battleship.controller.IShipController;
import de.htwg.battleship.controller.IShootController;
import de.htwg.battleship.controller.IWinLooseController;
import de.htwg.battleship.controller.impl.ShipController;
import de.htwg.battleship.controller.impl.ShootController;
import de.htwg.battleship.controller.impl.WinController;
import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.model.impl.Player;
import de.htwg.battleship.model.impl.Ship;
import static de.htwg.battleship.util.StatCollection.ASCII_LOW_CASE;
import static de.htwg.battleship.util.StatCollection.SHIP_NUMBER_MAX;
import java.util.Scanner;
import java.util.regex.Pattern;

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
    private static IShipController shipController;
    private static IWinLooseController winLooseController;
    private static IShootController shootController;

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
        shipController = new ShipController(player1, player2);
        while (player1.getOwnBoard().getShips() < SHIP_NUMBER_MAX) {
            menu = new PlaceViewer(player1);
            System.out.print(menu.getString());
            int x = (int) input.next(Pattern.compile("[a-z]")).charAt(0)
                    - ASCII_LOW_CASE;
            int y = input.nextInt();
            boolean orientation = input.next().equals("true");
            if (!shipController.placeShip(new Ship(player1.getOwnBoard().getShips()
                    + 2, orientation, x, y), true)) {
                System.err.println("Falsche Eingabe oder Kollision!");
            }
            menu = new FieldViewer(player1, player2);
            System.out.println(menu.getString());
        }
        while (player2.getOwnBoard().getShips() < SHIP_NUMBER_MAX) {
            menu = new PlaceViewer(player2);
            System.out.print(menu.getString());
            int x = (int) input.next(Pattern.compile("[a-z]")).charAt(0)
                    - ASCII_LOW_CASE;
            int y = input.nextInt();
            boolean orientation = input.next().equals("true");
            if (!shipController.placeShip(new Ship(player2.getOwnBoard().getShips()
                    + 2, orientation, x, y), false)) {
                System.err.println("Falsche Eingabe oder Kollision!");
            }
            menu = new FieldViewer(player1, player2);
            System.out.println(menu.getString());
        }

        shootController = new ShootController(player1, player2);
        winLooseController = new WinController(player1, player2);
        boolean player = true;
        while (winLooseController.win() == null) {
            if (player) {
                menu = new ShootMenu(player1);
            } else {
                menu = new ShootMenu(player2);
            }
            System.out.print(menu.getString());
            int x = (int) input.next(Pattern.compile("[a-z]")).charAt(0)
                    - ASCII_LOW_CASE;
            int y = input.nextInt();
            if (shootController.shoot(x, y, player)) {
                System.out.println("Your shoot was a Hit!");
            } else {
                System.out.println("You miss your target!");
            }
            menu = new FieldViewer(player1, player2);
            System.out.println(menu.getString());
            player = !player;
        }
    }
}
