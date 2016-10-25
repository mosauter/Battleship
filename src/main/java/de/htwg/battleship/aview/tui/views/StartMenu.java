// StartMenu.java

package de.htwg.battleship.aview.tui.views;

import de.htwg.battleship.aview.tui.Viewer;

/**
 * StartMenu presents a start Menu for the game.
 *
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-10
 */
public class StartMenu extends Viewer {

    @Override
    public final String getString() {
        return "1. Start Game\n" + "2. Options\n" +
               "Type 'Exit' or 'exit' at any time to close the game.\n" +
               "\t--->\t";
    }
}
