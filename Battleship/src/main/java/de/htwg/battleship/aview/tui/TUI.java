// TUI.java

package de.htwg.battleship.aview.tui;

import de.htwg.battleship.aview.tui.impl.EndMenuViewer;
import de.htwg.battleship.aview.tui.impl.HitMissViewer;
import de.htwg.battleship.aview.tui.impl.InputMaskPlayer1;
import de.htwg.battleship.aview.tui.impl.InputMaskPlayer2;
import de.htwg.battleship.aview.tui.impl.PlaceErrorViewer;
import de.htwg.battleship.aview.tui.impl.PlaceFieldViewer;
import de.htwg.battleship.aview.tui.impl.PlaceViewer;
import de.htwg.battleship.aview.tui.impl.ShootMenu;
import de.htwg.battleship.aview.tui.impl.StartMenu;
import de.htwg.battleship.aview.tui.impl.WinPlayer;
import de.htwg.battleship.aview.tui.impl.WrongInputViewer;
import de.htwg.battleship.controller.IMasterController;
import de.htwg.battleship.observer.IObserver;
import static de.htwg.battleship.util.State.END;
import static de.htwg.battleship.util.State.GETNAME1;
import static de.htwg.battleship.util.State.PLACE1;
import static de.htwg.battleship.util.State.START;
import static de.htwg.battleship.util.State.WRONGINPUT;

/**
 * Textual User Interface.
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-10
 */
public class TUI implements IObserver {

    /**
     * Constant for the conversion of the ASCII table.
     */
    public static final int ASCII_LOW_CASE = 97;
    /**
     * Constant for the length of a set name statement.
     */
    private static final int SET_NAME_STATEMENT_LENGTH = 1;
    /**
     * Constant for the length of a place statement.
     */
    private static final int PLACE_STATEMENT_LENGTH = 3;
    /**
     * Constant for the length of a shoot statement.
     */
    private static final int SHOOT_STATEMENT_LENGTH = 2;
    /**
     * Saves the MasterController.
     */
    private final IMasterController master;
    /**
     * Viewer which provides the current state of the game as a String.
     */
    private Viewer view;
    /**
     * Public constructor.
     * @param master master controller
     */
    public TUI(final IMasterController master) {
        this.master = master;
        this.master.addObserver(this);
        this.printTUI();
    }

    /**
     * Method to print the current TUI.
     * detects what to do at the current state of the game
     */
    public final void printTUI() {
        switch (master.getCurrentState()) {
            case START:
                this.view = new StartMenu();
                break;
            case GETNAME1:
                this.view = new InputMaskPlayer1();
                break;
            case GETNAME2:
                this.view = new InputMaskPlayer2();
                break;
            case PLACE1:
                this.view = new PlaceViewer(master.getPlayer1());
                break;
            case PLACE2:
                this.view = new PlaceViewer(master.getPlayer2());
                break;
            case FINALPLACE1:
                this.view = new PlaceFieldViewer(master.getPlayer1());
                break;
            case FINALPLACE2:
                this.view = new PlaceFieldViewer(master.getPlayer2());
                break;
            case PLACEERR:
                this.view = new PlaceErrorViewer();
                break;
            case SHOOT1:
                this.view = new ShootMenu(master.getPlayer1(), master);
                break;
            case SHOOT2:
                this.view = new ShootMenu(master.getPlayer2(), master);
                break;
            case HIT:
                this.view = new HitMissViewer(true);
                break;
            case MISS:
                this.view = new HitMissViewer(false);
                break;
            case WIN1:
                this.view = new WinPlayer(master.getPlayer1(), master);
                break;
            case WIN2:
                this.view = new WinPlayer(master.getPlayer2(), master);
                break;
            case WRONGINPUT:
                this.view = new WrongInputViewer();
                break;
            case END:
                this.view = new EndMenuViewer();
                break;
            default:
                break;
        }
        System.out.print(this.view.getString());
    }

    @Override
    public final void update() {
        printTUI();
    }

    /**
     * Method to detect what statement is in the line.
     * is a transmitter between the user and the master controller
     * @param line input of stdin
     */
    public final void processInputLine(final String line) {
        String[] field = line.split(" ");
//        Start End Menu
        if (master.getCurrentState() == START
                || master.getCurrentState() == END) {
            if (field.length != 1) {
                this.master.setCurrentState(WRONGINPUT);
                return;
            }
            if (field[0].equals("1")) {
                this.master.startGame();
            } else {
                System.exit(0);
            }
            return;
        }
//        Place Menu
        if (field.length == PLACE_STATEMENT_LENGTH) {
            if (!field[0].matches("[a-z]")) {
                master.setCurrentState(WRONGINPUT);
                return;
            }
            if (!field[1].matches("[0-9]")) {
                master.setCurrentState(WRONGINPUT);
                return;
            }
            int x = (int) field[0].charAt(0) - ASCII_LOW_CASE;
            int y = Integer.parseInt(field[1]);
            if (field[2].equals("horizontal")) {
                master.placeShip(x, y, true);
            } else {
                master.placeShip(x, y, false);
            }
            return;
        }
//        Shoot Menu
        if (field.length == SHOOT_STATEMENT_LENGTH) {
            if (!field[0].matches("[a-z]")) {
                master.setCurrentState(WRONGINPUT);
                return;
            }
            if (!field[1].matches("[0-9]")) {
                master.setCurrentState(WRONGINPUT);
                return;
            }
            int x = (int) field[0].charAt(0) - ASCII_LOW_CASE;
            int y = Integer.parseInt(field[1]);
            master.shoot(x, y);
            return;
        }
//        Exit Statement
        if (field.length == SET_NAME_STATEMENT_LENGTH) {
            if (field[0].equals("Exit") || field[0].equals("exit")) {
                System.exit(0);
            }
            master.setPlayerName(field[0]);
        }
    }
}
