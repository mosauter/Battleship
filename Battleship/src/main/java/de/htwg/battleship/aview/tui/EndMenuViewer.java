// EndMenuViewer.java

package de.htwg.battleship.aview.tui;

/**
 * EndMenuViewer provides a menu after a game has won by a player.
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-29
 */
public class EndMenuViewer implements Viewer {

    @Override
    public final String getString() {
        StringBuilder sb = new StringBuilder();
        sb.append("1. Start a new Game\n");
        sb.append("2. End Game\n");
        sb.append("\t--->\t");
        return sb.toString();
    }
}
