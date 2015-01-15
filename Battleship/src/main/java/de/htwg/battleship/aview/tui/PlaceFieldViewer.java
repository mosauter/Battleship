// FieldViewer.java

package de.htwg.battleship.aview.tui;

import de.htwg.battleship.controller.IMasterController;
import de.htwg.battleship.model.IBoard;
import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.model.IShip;
import static de.htwg.battleship.util.StatCollection.createMap;
import static de.htwg.battleship.util.StatCollection.heightLenght;
import java.util.Map;
import java.util.Set;

/**
 * FieldViewer to represents the Field.
 * Acts like a viewer without an own state.
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-09
 */
public class PlaceFieldViewer extends AbstractFieldViewer implements Viewer {

    /**
     * Saves Player one.
     */
    private final IPlayer player1;
    /**
     * Saves MasterController.
     */
    private final IMasterController master;

    /**
     * Public - Constructor.
     * @param player player one
     */
    public PlaceFieldViewer(final IPlayer player,
            final IMasterController master) {
        this.player1 = player;
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
        IBoard boardPlayer1 = player1.getOwnBoard();
        IShip[] listPlayer1 = player1.getOwnBoard().getShipList();
        master.fillMap(listPlayer1,
                mapPlayer1, player1.getOwnBoard().getShips());
        addBorder(sb);
        for (int y = 0; y < heightLenght; y++) {
            sb.append(y);
            sb = fillX(mapPlayer1, y, boardPlayer1, sb, SHIP_NON_HIT);
            sb.append("\t ").append(y);
            for (int x = 0; x < heightLenght; x++) {
                sb.append(FIELD_NON_HIT);
            }
            sb.append("\n");
        }
        sb = addLegend(sb);
        return sb.toString();
    }
}
