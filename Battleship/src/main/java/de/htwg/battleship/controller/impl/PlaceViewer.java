// PlaceViewer.java

package de.htwg.battleship.controller.impl;

import de.htwg.battleship.controller.impl.Viewer;
import de.htwg.battleship.model.IPlayer;

/**
 * PlaceViewer
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-15
 */
public class PlaceViewer implements Viewer {

    private IPlayer player;
    
    public PlaceViewer(IPlayer player) {
        this.player = player;
    }
    public String getString() {
        StringBuilder sb = new StringBuilder();
        sb.append(player.getName()).append(" Schiff platzieren:").append("\n");
        sb.append("Geben sie den Startpunkt fuer ihr Schiff an "
                + "(Format: [a - j] [0 - 9] [true | false]):\n");
        sb.append("Laenge: ").append((player.getOwnBoard().getShips() + 2));
        sb.append("\n");
        sb.append("\t\t-->\t");
        return sb.toString();
    }

}
