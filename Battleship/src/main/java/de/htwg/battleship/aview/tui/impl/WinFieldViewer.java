// WinFieldViewer.java

package de.htwg.battleship.aview.tui.impl;

import de.htwg.battleship.aview.tui.Viewer;
import de.htwg.battleship.controller.IMasterController;
import de.htwg.battleship.model.IBoard;
import de.htwg.battleship.model.IShip;
import static de.htwg.battleship.util.StatCollection.FIELD_IS_HIT;
import static de.htwg.battleship.util.StatCollection.FIELD_NON_HIT;
import static de.htwg.battleship.util.StatCollection.HEIGTH_LENGTH;
import static de.htwg.battleship.util.StatCollection.SHIP_IS_HIT;
import static de.htwg.battleship.util.StatCollection.SHIP_NON_HIT;
import static de.htwg.battleship.util.StatCollection.createBorder;
import static de.htwg.battleship.util.StatCollection.createMap;
import static de.htwg.battleship.util.StatCollection.fillMap;
import java.util.Map;
import java.util.Set;

/**
 * WinFieldViewer
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-23
 */
public class WinFieldViewer implements Viewer {

    private final IMasterController master;
    private Object player1;
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
        sb.append("\n ");
        Map<Integer, Set<Integer>> mapPlayer1 = createMap();
        Map<Integer, Set<Integer>> mapPlayer2 = createMap();
        IBoard boardPlayer1 = master.getPlayer1().getOwnBoard();
        IShip[] listPlayer1 = master.getPlayer1().getOwnBoard().getShipList();
        fillMap(listPlayer1, mapPlayer1,
                master.getPlayer1().getOwnBoard().getShips());
        IBoard boardPlayer2 = master.getPlayer2().getOwnBoard();
        IShip[] listPlayer2 = master.getPlayer2().getOwnBoard().getShipList();
        fillMap(listPlayer2, mapPlayer2,
                master.getPlayer2().getOwnBoard().getShips());
        sb.append(master.getPlayer1().getName()).append("\t\t");
        sb.append(master.getPlayer2().getName()).append("\n");
        createBorder(sb);
        sb.append("\t  ");
        createBorder(sb);
        sb.append("\n");
        for (int y = 0; y < HEIGTH_LENGTH; y++) {
            sb.append(y);
            for (int x = 0; x < HEIGTH_LENGTH; x++) {
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
            for (int x = 0; x < HEIGTH_LENGTH; x++) {
                boolean isShip = false;
                for (Integer value : mapPlayer2.get(y)) {
                    if (value == x) {
                        if (boardPlayer2.isHit(x, y)) {
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
                if (boardPlayer2.isHit(x, y)) {
                    sb.append(FIELD_IS_HIT);
                } else {
                    sb.append(FIELD_NON_HIT);
                }
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