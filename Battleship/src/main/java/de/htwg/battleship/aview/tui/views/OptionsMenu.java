package de.htwg.battleship.aview.tui.views;

import de.htwg.battleship.aview.tui.Viewer;

/**
 * @author moritz
 */
public class OptionsMenu implements Viewer {

    @Override
    public String getString() {
        return "1. Set the Boardsize: Format: 1 [ 4 - 15 ]\n" +
               "2. Set the maximum number of ships: Format: 2 [ 2 - 10 ]\n" +
               "3. Set the Game Mode: Format: 3 [ NORMAL | EXTREME ]\n" +
               "4. start the game: Format: 4\n" +
               "\t-->\t";
    }
}
