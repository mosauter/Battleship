// WinPlayer1.java

package de.htwg.battleship.controller.impl;

import de.htwg.battleship.controller.IMasterController;
import de.htwg.battleship.controller.Viewer;
import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.util.State;
import static de.htwg.battleship.util.State.WIN1;
import static de.htwg.battleship.util.State.WIN2;

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
     * True if the winner is the first player,
     * false if the winner is the second player.
     */
    private final boolean firstPlayer;

    /**
     * Public Constructor.
     * @param player the winner
     * @param master the master controller
     */
    public WinPlayer(final IPlayer player, final IMasterController master) {
        this.player = player;
        firstPlayer = this.player.equals(master.getPlayer1());
    }

    @Override
    public final String getString() {
        StringBuilder sb = new StringBuilder();
        sb.append(player.getName());
        sb.append(" has won!\n\n");
        return sb.toString();
    }

    @Override
    public final State getCurrentState() {
        if (firstPlayer) {
            return WIN1;
        }
        return WIN2;
    }
}
