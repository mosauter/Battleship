// WinPlayer1.java

package de.htwg.battleship.aview.tui.views;

import de.htwg.battleship.aview.tui.Viewer;
import de.htwg.battleship.controller.IMasterController;
import de.htwg.battleship.model.IPlayer;

/**
 * WinPlayer1 presents a view which player has won.
 *
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-11
 */
public class WinPlayer extends Viewer {

    /**
     * Saves the winner.
     */
    private final IPlayer player;
    /**
     * Private utility viewer to show a last time the field.
     */
    private final Viewer view;

    /**
     * Public Constructor.
     *
     * @param player the winner
     * @param master saves the MasterController
     */
    public WinPlayer(final IPlayer player, final IMasterController master) {
        this.player = player;
        this.view = new WinFieldViewer(master);
    }

    @Override
    public final String getString() {
        return view.getString() + player.getName() +
               " has won!\n\n";
    }
}
