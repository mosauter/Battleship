// WinPlayer1.java

package de.htwg.battleship.aview.tui.impl;

import de.htwg.battleship.aview.tui.Viewer;
import de.htwg.battleship.model.IPlayer;

/**
 * WinPlayer1 presents a view which player has won.
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-11
 */
public class WinPlayer implements Viewer {

    /**
     * Saves the winner.
     */
    private final IPlayer player;

    /**
     * Public Constructor.
     * @param player the winner
     */
    public WinPlayer(final IPlayer player) {
        this.player = player;
    }

    @Override
    public final String getString() {
        StringBuilder sb = new StringBuilder();
        sb.append(player.getName());
        sb.append(" has won!\n\n");
        return sb.toString();
    }
}
