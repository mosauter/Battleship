// EndMenuViewer.java

package de.htwg.battleship.aview.tui.views;

import de.htwg.battleship.aview.tui.Viewer;

/**
 * EndMenuViewer provides a menu after a game has won by a player.
 *
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-29
 */
public class EndMenuViewer extends Viewer {

    @Override
    public final String getString() {
        return "1. Start a new Game\n" +
               "2. Options\n" +
               "3. End Game\n" +
               "\t--->\t";
    }
}
