// TUI.java

package de.htwg.battleship.aview.tui;

import de.htwg.battleship.controller.IMasterController;
import de.htwg.battleship.controller.impl.InputMaskPlayer1;
import de.htwg.battleship.controller.impl.InputMaskPlayer2;
import de.htwg.battleship.controller.impl.PlaceViewer;
import de.htwg.battleship.controller.impl.ShootMenu;
import de.htwg.battleship.controller.impl.StartMenu;
import de.htwg.battleship.observer.IObserver;
import static de.htwg.battleship.util.StatCollection.ASCII_LOW_CASE;
import static de.htwg.battleship.util.StatCollection.HORIZONTAL;
import static de.htwg.battleship.util.StatCollection.SHIP_NUMBER_MAX;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Textual User Interface.
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-10
 */
public class TUI implements IObserver {

    /**
     * Saves the MasterController.
     */
    private final IMasterController master;
    /**
     * Scanner.
     */
    private final Scanner input = new Scanner(System.in);

    /**
     * Public constructor.
     * @param master master controller
     */
    public TUI(final IMasterController master) {
        this.master = master;
        this.master.addObserver(this);
    }

    /**
     * TUI - Method to initialize the complete Game.
     */
    public final void initialize() {
        this.master.setViewer(new StartMenu());
        if ((input.nextInt()) == 2) {
            System.exit(0);
        }
        this.master.setViewer(new InputMaskPlayer1());
        this.master.getPlayer1().setName(input.next());
        this.master.setViewer(new InputMaskPlayer2());
        this.master.getPlayer2().setName(input.next());
        this.master.setViewer(new PlaceViewer(
                this.master.getPlayer1(), master));
        while (this.master.getPlayer1().getOwnBoard().getShips()
                < SHIP_NUMBER_MAX) {
            int x = (int) input.next(Pattern.compile("[a-z]")).charAt(0)
                    - ASCII_LOW_CASE;
            int y = input.nextInt();
            boolean orientation = input.next().equals(HORIZONTAL);
            this.master.placeShip(x, y, orientation, this.master.getPlayer1());
        }
        this.master.setViewer(new PlaceViewer(
                this.master.getPlayer2(), master));
        while (this.master.getPlayer2().getOwnBoard().getShips()
                < SHIP_NUMBER_MAX) {
            int x = (int) input.next(Pattern.compile("[a-z]")).charAt(0)
                    - ASCII_LOW_CASE;
            int y = input.nextInt();
            boolean orientation = input.next().equals(HORIZONTAL);
            this.master.placeShip(x, y, orientation, this.master.getPlayer2());
        }
    }

    /**
     * Method to print the current TUI.
     */
    public final void printTUI() {
        System.out.print(master.getString());
    }

    @Override
    public final void update() {
        printTUI();
    }

    /**
     * TUI - Method to start a new Game.
     */
    public final void startGame() {
        while (true) {
            this.master.setViewer(new ShootMenu(master.getPlayer1(), master));
            int x = (int) input.next(Pattern.compile("[a-z]")).charAt(0)
                    - ASCII_LOW_CASE;
            int y = input.nextInt();
            this.master.shoot(x, y, master.getPlayer1());
            this.master.setViewer(new ShootMenu(master.getPlayer2(), master));
            x = (int) input.next(Pattern.compile("[a-z]")).charAt(0)
                    - ASCII_LOW_CASE;
            y = input.nextInt();
            this.master.shoot(x, y, master.getPlayer2());
        }
    }
}
