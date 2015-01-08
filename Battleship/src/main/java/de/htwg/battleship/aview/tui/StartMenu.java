// StartMenu.java

package de.htwg.battleship.aview.tui;

/**
 * StartMenu presents a start Menu for the game.
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-10
 */
public class StartMenu implements Viewer {

    @Override
    public final String getString() {
        StringBuilder sb = new StringBuilder();
        sb.append("1. Start Game\n");
        sb.append("2. Exit\n");
        sb.append("Type 'Exit' or 'exit' at any time to close the game.\n");
        sb.append("\t--->\t");
        return sb.toString();
    }
}
