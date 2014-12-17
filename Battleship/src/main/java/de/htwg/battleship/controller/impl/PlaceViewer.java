// PlaceViewer.java

package de.htwg.battleship.controller.impl;

import de.htwg.battleship.controller.Viewer;
import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.util.StatCollection;

/**
 * PlaceViewer
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-15
 */
public class PlaceViewer implements Viewer {

    private IPlayer player;
    private Viewer fieldViewer;
    public PlaceViewer(IPlayer player) {
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
