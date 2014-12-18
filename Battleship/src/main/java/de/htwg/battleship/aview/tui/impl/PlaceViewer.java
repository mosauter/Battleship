// PlaceViewer.java

package de.htwg.battleship.aview.tui.impl;

import de.htwg.battleship.aview.tui.Viewer;
import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.util.StatCollection;

/**
 * PlaceViewer presents the place-Menu with a presentation of the field.
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-15
 */
public class PlaceViewer implements Viewer {

    /**
     * Saves th player.
     */
    private final IPlayer player;
    /**
     * Internal States for a presentation of the field.
     */
    private final PlaceFieldViewer fieldViewer;

    /**
     * Public Constructor.
     * @param player which player is at the turn to place a ship
     */
    public PlaceViewer(final IPlayer player) {
        this.player = player;
        this.fieldViewer = new PlaceFieldViewer(player);
    }

    @Override
    public final String getString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.fieldViewer.getString());
        sb.append(player.getName()).append(" Place Ship:").append("\n");
        sb.append("Insert the start point of your ship and the orientation: "
                + "(Format: [a - j] [0 - 9] ["
                + StatCollection.HORIZONTAL + " | vertical]):\n");
        sb.append("Length of the ship: ");
        sb.append((player.getOwnBoard().getShips() + 2));
        sb.append("\n");
        sb.append("\t\t-->\t");
        return sb.toString();
    }
}
