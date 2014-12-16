// FieldViewer.java

package de.htwg.battleship.aview.impl;

import de.htwg.battleship.aview.Viewer;
import de.htwg.battleship.model.IBoard;
import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.model.IShip;
import static de.htwg.battleship.util.StatCollection.FIELD_IS_HIT;
import static de.htwg.battleship.util.StatCollection.FIELD_NON_HIT;
import static de.htwg.battleship.util.StatCollection.HEIGTH_LENGTH;
import static de.htwg.battleship.util.StatCollection.SHIP_IS_HIT;
import static de.htwg.battleship.util.StatCollection.SHIP_NON_HIT;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * FieldViewer to print the Field.
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-09
 */
public class FieldViewer implements Viewer {

    /**
     * Saves Player one.
     */
    private final IPlayer player1;
    /**
     * Saves Player two.
     */
    private final IPlayer player2;

    /**
     * Public - Constructor.
     * @param player1 player one
     * @param player2 player two
     */
    public FieldViewer(final IPlayer player1, final IPlayer player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    @Override
    public final String getString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        Map<Integer, Set<Integer>> mapPlayer1 =
                new TreeMap<Integer, Set<Integer>>();
        Map<Integer, Set<Integer>> mapPlayer2 =
                new TreeMap<Integer, Set<Integer>>();
        for (int i = 0; i < HEIGTH_LENGTH; i++) {
            mapPlayer1.put(i, new TreeSet<Integer>());
            mapPlayer2.put(i, new TreeSet<Integer>());
        } 
        IBoard boardPlayer1 = player1.getOwnBoard();
        IBoard boardPlayer2 = player2.getOwnBoard();
        IShip[] listPlayer1 = player1.getOwnBoard().getShipList();
        IShip[] listPlayer2 = player2.getOwnBoard().getShipList();
        for (int i = 0; i < player1.getOwnBoard().getShips(); i++) {
            mapPlayer1 = this.getSet(listPlayer1[i], mapPlayer1);
        }
        for (int i = 0; i < player2.getOwnBoard().getShips(); i++) {
            mapPlayer2 = this.getSet(listPlayer2[i], mapPlayer2);
        }
        for (int i = 0; i < HEIGTH_LENGTH; i++) {
            char c = (char) ('a' + i);
            sb.append(" ").append(c);
        }
        sb.append("\t  ");
        for (int i = 0; i < HEIGTH_LENGTH; i++) {
            char c = (char) ('a' + i);
            sb.append(" ").append(c);
        }
        sb.append("\n");
        for (int y = 0; y < HEIGTH_LENGTH; y++) {
            sb.append(y);
            for (int x = 0; x < HEIGTH_LENGTH; x++) {
                boolean isShip = false;
                for (Integer value : mapPlayer1.get(y)) {
                    if (value == x) {
                        if (boardPlayer1.getField(x, y).isHit()) {
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
                if (boardPlayer1.getField(x, y).isHit()) {
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
                        if (boardPlayer2.getField(x, y).isHit()) {
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
                if (boardPlayer2.getField(x, y).isHit()) {
                    sb.append(FIELD_IS_HIT);
                } else {
                    sb.append(FIELD_NON_HIT);
                }
            }
            sb.append("\n");
        }
        sb.append("Legende:\n\t" + FIELD_NON_HIT + " -> Field is not hit!");
        sb.append("\n\t" + FIELD_IS_HIT + " -> Field is hit and it was a Miss!");
        sb.append("\n\t" + SHIP_NON_HIT + " -> A ship take place on "
                + "the Field!");
        sb.append("\n\t" + SHIP_IS_HIT + " -> A ship is on the Field "
                + "and it is destroyed!");
        return sb.toString();
    }

    /**
     * Utility Method to create a Map where ships take place.
     * @param ship specified ship
     * @param map specified map
     * @return the new Map
     */
    private Map<Integer, Set<Integer>> getSet(final IShip ship,
            final Map<Integer, Set<Integer>> map) {
        if (ship.isOrientation()) {
            int xlow = ship.getX();
            int xupp = xlow + ship.getSize();
            Set<Integer> set = map.get(ship.getY());
            for (int i = xlow; i < xupp; i++) {
                set.add(i);
            }
            return map;
        } else {
            int ylow = ship.getY();
            int yupp = ylow + ship.getSize();
            int x = ship.getX();
            for (int i = ylow; i < yupp; i++) {
                map.get(i).add(x);
            }
            return map;
        }
    }
}
