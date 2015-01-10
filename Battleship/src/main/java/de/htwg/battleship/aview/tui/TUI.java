// TUI.java

package de.htwg.battleship.aview.tui;

import de.htwg.battleship.controller.IMasterController;
import de.htwg.battleship.observer.IObserver;
import static de.htwg.battleship.util.State.END;
import static de.htwg.battleship.util.State.GETNAME1;
import static de.htwg.battleship.util.State.PLACE1;
import static de.htwg.battleship.util.State.START;
import static de.htwg.battleship.util.State.WRONGINPUT;
import org.apache.log4j.Logger;

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
     * Constant string for a double newline.
     */
    private static final String NEWLINE = "\n\n";
    /**
     * Saves the MasterController.
     */
    private final IMasterController master;
    /**
     * Saves the Logger.
     */
    private final Logger logger =
            Logger.getLogger("de.htwg.battleship.aview.tui");
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
        Viewer view = new WrongInputViewer();
        switch (master.getCurrentState()) {
            case START:
                view = new StartMenu();
                break;
            case GETNAME1:
                view = new InputMaskPlayer1();
                break;
            case GETNAME2:
                view = new InputMaskPlayer2();
                break;
            case PLACE1:
                view = new PlaceViewer(master.getPlayer1());
                break;
            case PLACE2:
                view = new PlaceViewer(master.getPlayer2());
                break;
            case FINALPLACE1:
                view = new PlaceFieldViewer(master.getPlayer1());
                break;
            case FINALPLACE2:
                view = new PlaceFieldViewer(master.getPlayer2());
                break;
            case PLACEERR:
                view = new PlaceErrorViewer();
                break;
            case SHOOT1:
                view = new ShootMenu(master.getPlayer1(), master);
                break;
            case SHOOT2:
                view = new ShootMenu(master.getPlayer2(), master);
                break;
            case HIT:
                view = new HitMissViewer(true);
                break;
            case MISS:
                view = new HitMissViewer(false);
                break;
            case WIN1:
                view = new WinPlayer(master.getPlayer1(), master);
                break;
            case WIN2:
                view = new WinPlayer(master.getPlayer2(), master);
                break;
            case WRONGINPUT:
                break;
            case END:
                view = new EndMenuViewer();
                break;
            default:
                break;
        }
        logger.info(NEWLINE + view.getString());
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
            processStartEndMenu(field);
            return;
        }
//        Place Menu
        if (field.length == PLACE_STATEMENT_LENGTH) {
            processPlaceMenu(field);
            return;
        }
//        Shoot Menu
        if (field.length == SHOOT_STATEMENT_LENGTH) {
            processShootMenu(field);
            return;
        }
//        Exit Statement
        if (field.length == SET_NAME_STATEMENT_LENGTH) {
            processExitPlayerNameMenu(field);
        }
    }

    /**
     * Utility method to process the input line in the start end menu.
     * @param line the input line as array
     */
    private void processStartEndMenu(final String[] line) {
        if (line.length != 1) {
            this.master.setCurrentState(WRONGINPUT);
            return;
        }
        if (line[0].equals("1")) {
            this.master.startGame();
        } else {
            System.exit(0);
        }
    }

    /**
     * Utility method to process the input line in the place menu.
     * @param line the input line as array
     */
    private void processPlaceMenu(final String[] line) {
        if (!line[0].matches("[a-z]")) {
            master.setCurrentState(WRONGINPUT);
            return;
        }
        if (!line[1].matches("[0-9]")) {
            master.setCurrentState(WRONGINPUT);
            return;
        }
        int x = (int) line[0].charAt(0) - ASCII_LOW_CASE;
        int y = Integer.parseInt(line[1]);
        if (line[2].equals("horizontal")) {
            master.placeShip(x, y, true);
        } else {
            master.placeShip(x, y, false);
        }
    }

    /**
     * Utility method to process the input line in the shoot menu.
     * @param line the input line as array
     */
    private void processShootMenu(final String[] line) {
        if (!line[0].matches("[a-z]")) {
            master.setCurrentState(WRONGINPUT);
            return;
        }
        if (!line[1].matches("[0-9]")) {
            master.setCurrentState(WRONGINPUT);
            return;
        }
        int x = (int) line[0].charAt(0) - ASCII_LOW_CASE;
        int y = Integer.parseInt(line[1]);
        master.shoot(x, y);
    }

    /**
     * Utility method to process the input line in the exit menu
     * and the setPlayerName menu.
     * @param line the input line as array
     */
    private void processExitPlayerNameMenu(final String[] line) {
        if (line[0].equals("Exit") || line[0].equals("exit")) {
            System.exit(0);
        }
        master.setPlayerName(line[0]);
    }
}
