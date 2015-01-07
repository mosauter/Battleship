// FieldViewer.java

package de.htwg.battleship.aview.tui.impl;

import de.htwg.battleship.aview.tui.Viewer;
import de.htwg.battleship.model.IBoard;
import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.model.IShip;
import static de.htwg.battleship.util.StatCollection.heightLenght;
import static de.htwg.battleship.util.StatCollection.createBorder;
import static de.htwg.battleship.util.StatCollection.createMap;
import static de.htwg.battleship.util.StatCollection.fillMap;
import java.util.Map;
import java.util.Set;

/**
 * FieldViewer to represents the Field.
 * Acts like a viewer without an own state.
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-09
 */
public class PlaceFieldViewer implements Viewer {

    /**
     * Saves Player one.
     */
    private final IPlayer player1;

    /**
     * Public - Constructor.
     * @param player player one
     */
    public PlaceFieldViewer(final IPlayer player) {
        this.player1 = player;
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
        sb.append("\n ");
        Map<Integer, Set<Integer>> mapPlayer1 = createMap();
        IBoard boardPlayer1 = player1.getOwnBoard();
        IShip[] listPlayer1 = player1.getOwnBoard().getShipList();
        fillMap(listPlayer1, mapPlayer1, player1.getOwnBoard().getShips());
        createBorder(sb);
        sb.append("\t  ");
        createBorder(sb);
        sb.append("\n");
        for (int y = 0; y < heightLenght; y++) {
            sb.append(y);
            for (int x = 0; x < heightLenght; x++) {
                boolean isShip = false;
                for (Integer value : mapPlayer1.get(y)) {
                    if (value == x) {
                        if (boardPlayer1.isHit(x, y)) {
                            sb.append(SHIP_IS_HIT);
                        } else {
                            sb.append(SHIP_NON_HIT);
                        }
                        isShip = true;
                    }
                }
                if (isShip) {
                    continue;
                }
                if (boardPlayer1.isHit(x, y)) {
                    sb.append(FIELD_IS_HIT);
                } else {
                    sb.append(FIELD_NON_HIT);
                }
            }
            sb.append("\t ").append(y);
            for (int x = 0; x < heightLenght; x++) {
                sb.append(FIELD_NON_HIT);
            }
            sb.append("\n");
        }
        sb.append("Legende:\n\t" + FIELD_NON_HIT + " -> Field is not hit!");
        sb.append("\n\t" + FIELD_IS_HIT + " -> Field "
                + "is hit and it was a Miss!");
        sb.append("\n\t" + SHIP_NON_HIT + " -> A ship take place on "
                + "the Field!");
        sb.append("\n\t" + SHIP_IS_HIT + " -> A ship is on the Field "
                + "and it is destroyed!\n\n");
        return sb.toString();
    }
}
