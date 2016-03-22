// TUI.java

package de.htwg.battleship.aview.tui;

import com.google.inject.Inject;
import de.htwg.battleship.aview.tui.views.*;
import de.htwg.battleship.controller.IMasterController;
import de.htwg.battleship.observer.IObserver;
import de.htwg.battleship.util.GameMode;
import de.htwg.battleship.util.StatCollection;
import org.apache.log4j.Logger;

import static de.htwg.battleship.util.State.END;
import static de.htwg.battleship.util.State.OPTIONS;
import static de.htwg.battleship.util.State.START;
import static de.htwg.battleship.util.State.WRONGINPUT;

/**
 * Textual User Interface.
 *
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-10
 */
public class TUI implements IObserver {

    /**
     * Constant for the conversion of the ASCII table.
     */
    private static final int ASCII_LOW_CASE = 97;
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
     * Saves the Logger.
     */
    private final Logger logger = Logger.getLogger(TUI.class);

    /**
     * Public constructor.
     *
     * @param master master controller
     */
    @Inject
    public TUI(final IMasterController master) {
        this.master = master;
        this.master.addObserver(this);
        this.printTUI();
    }

    /**
     * Method to print the current TUI. detects what to do at the current state
     * of the game
     *
     * @return the tui of the current state in a string presentation
     */
    private String printTUI() {
        Viewer view = new WrongInputViewer();
        switch (master.getCurrentState()) {
            case START:
                view = new StartMenu();
                break;
            case OPTIONS:
                view = new OptionsMenu();
                break;
            case GETNAME1:
                view = new InputMaskPlayer1();
                break;
            case GETNAME2:
                view = new InputMaskPlayer2();
                break;
            case PLACE1:
                view = new PlaceViewer(master.getPlayer1(), master);
                break;
            case PLACE2:
                view = new PlaceViewer(master.getPlayer2(), master);
                break;
            case FINALPLACE1:
                view = new PlaceFieldViewer(master.getPlayer1(), master);
                break;
            case FINALPLACE2:
                view = new PlaceFieldViewer(master.getPlayer2(), master);
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
        logger.info(view.getString());
        return view.getString();
    }

    @Override
    public final void update() {
        printTUI();
    }

    /**
     * Method to detect what statement is in the line. is a transmitter between
     * the user and the master controller
     *
     * @param line input of stdin
     *
     * @return true in case the input was 'exit' false if there was unrecognized
     * input or something valid except 'exit'
     */
    public final boolean processInputLine(final String line) {
        String[] field = line.split(" ");
        if (field[0].equalsIgnoreCase("exit")) {
            //        Exit Statement at any time
            return true;
        }
        if (master.getCurrentState() == START ||
            master.getCurrentState() == END) {
            //        Start End Menu
            processStartEndMenu(field);
        } else if (master.getCurrentState() == OPTIONS) {
            //        Options Menu
            processOptionsMenu(field);
        } else if (field.length == PLACE_STATEMENT_LENGTH) {
            //        Place Menu
            processPlaceMenu(field);
        } else if (field.length == SHOOT_STATEMENT_LENGTH) {
            //        Shoot Menu
            processShootMenu(field);
        } else if (field.length == SET_NAME_STATEMENT_LENGTH) {
            //        Player Name Menu
            processPlayerNameMenu(field);
        }
        // should never happen, only if there was a completely false input
        return false;
    }

    /**
     * Utility method to process the input line in the start end menu.
     *
     * @param line the input line as array
     */
    private void processStartEndMenu(final String[] line) {
        if (line.length != 1) {
            this.master.setCurrentState(WRONGINPUT);
            return;
        }
        if (line[0].equals("1")) {
            this.master.startGame();
        } else if (line[0].equals("2")) {
            this.master.configure();
        }
    }

    /**
     * Utility method to process the input line in the options menu.
     *
     * @param line the input line as array
     */
    private void processOptionsMenu(final String[] line) {
        if (line.length != 1 && line.length != 2) {
            this.master.setCurrentState(WRONGINPUT);
            return;
        }
        switch (line[0]) {
            case "1":
                this.master.setBoardSize(Integer.parseInt(line[1]));
                logger.info("The new size of the board is set to " +
                            StatCollection.heightLenght);
                break;
            case "2":
                this.master.setShipNumber(Integer.parseInt(line[1]));
                logger.info("The new number of ships is set to " +
                            StatCollection.shipNumberMax);
                break;
            case "3":
                if (line[1].equalsIgnoreCase("EXTREME")) {
                    this.logger.info("Game Mode is set to " + GameMode.EXTREME);
                    this.master.setGameMode(GameMode.EXTREME);
                } else {
                    this.logger.info("Game Mode is set to " + GameMode.NORMAL);
                    this.master.setGameMode(GameMode.NORMAL);
                }
                break;
            case "4":
                this.master.startGame();
                break;
        }
    }

    /**
     * Utility method to process the input line in the place menu.
     *
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
        if (line[2].equalsIgnoreCase("horizontal")) {
            master.placeShip(x, y, true);
        } else {
            master.placeShip(x, y, false);
        }
    }

    /**
     * Utility method to process the input line in the shoot menu.
     *
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
     * Utility method to process the input line in the setPlayerName menu.
     *
     * @param line the input line as array
     */
    private void processPlayerNameMenu(final String[] line) {
        master.setPlayerName(line[0]);
    }
}
