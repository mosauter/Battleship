// WinFieldViewer.java

package de.htwg.battleship.aview.tui;

import de.htwg.battleship.controller.IMasterController;
import de.htwg.battleship.model.IBoard;
import de.htwg.battleship.model.IShip;
import static de.htwg.battleship.util.StatCollection.createMap;
import java.util.Map;
import java.util.Set;

/**
 * WinFieldViewer presents a view of the entire field that means it
 * shows the ships of both players.
 * implements viewer
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-23
 */
public class WinFieldViewer extends AbstractFieldViewer implements Viewer {

    /**
     * Saves the MasterController.
     */
    private final IMasterController master;

    /**
     * Public Constructor.
     * @param master the MasterController for the entire game
     */
    public WinFieldViewer(final IMasterController master) {
        this.master = master;
    }

    /**
     * Method to get the Field presented as a String.
     * Shows the own Ships on the field. The field which is owned by the
     * Player at the turn is always at the left side.
     * @return String presentation of the Field
     */
    @Override
    public final String getString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        Map<Integer, Set<Integer>> mapPlayer1 = createMap();
        Map<Integer, Set<Integer>> mapPlayer2 = createMap();
        IBoard boardPlayer1 = master.getPlayer1().getOwnBoard();
        IShip[] listPlayer1 = master.getPlayer1().getOwnBoard().getShipList();
        master.fillMap(listPlayer1, mapPlayer1,
                master.getPlayer1().getOwnBoard().getShips());
        IBoard boardPlayer2 = master.getPlayer2().getOwnBoard();
        IShip[] listPlayer2 = master.getPlayer2().getOwnBoard().getShipList();
        master.fillMap(listPlayer2, mapPlayer2,
                master.getPlayer2().getOwnBoard().getShips());
        sb.append("\t").append(master.getPlayer1().getName()).append("\t\t\t");
        sb.append(master.getPlayer2().getName()).append("\n");
        sb = fillEntire(mapPlayer1, boardPlayer1, sb,
                mapPlayer2, boardPlayer2, SHIP_NON_HIT);
        sb = addLegend(sb);
        return sb.toString();
    }
}
