// FieldViewer.java

package de.htwg.battleship.controller.impl;

import de.htwg.battleship.controller.IMasterController;
import de.htwg.battleship.controller.Viewer;
import de.htwg.battleship.model.IBoard;
import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.model.IShip;
import static de.htwg.battleship.util.StatCollection.FIELD_IS_HIT;
import static de.htwg.battleship.util.StatCollection.FIELD_NON_HIT;
import static de.htwg.battleship.util.StatCollection.HEIGTH_LENGTH;
import static de.htwg.battleship.util.StatCollection.SHIP_IS_HIT;
import static de.htwg.battleship.util.StatCollection.SHIP_NON_HIT;
import static de.htwg.battleship.util.StatCollection.getSet;
import de.htwg.battleship.util.State;
import static de.htwg.battleship.util.State.SHOOT1;
import static de.htwg.battleship.util.State.SHOOT2;
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
public class ShootFieldViewer implements Viewer {

    /**
     * Saves Player one.
     * Has not coersive necessary that it has to be the first player.
     */
    private final IPlayer player1;
    /**
     * Saves Player two.
     * Has not coersive necessary that it has to be the second player.
     */
    private final IPlayer player2;
    /**
     * True if player1 is the first player,
     * False if player2 is the second player.
     */
    private final boolean firstPlayer;

    /**
     * Public - Constructor.
     * @param player player one
     * @param master master controller
     */
    public ShootFieldViewer(final IPlayer player,
            final IMasterController master) {
        this.player1 = player;
        if (player.equals(master.getPlayer1())) {
            this.firstPlayer = true;
            this.player2 = master.getPlayer2();
        } else {
            this.firstPlayer = false;
            this.player2 = master.getPlayer1();
        }
    }

    @Override
    public final String getString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n ");
        Map<Integer, Set<Integer>> mapPlayer1 = new TreeMap<>();
        Map<Integer, Set<Integer>> mapPlayer2 = new TreeMap<>();
        for (int i = 0; i < HEIGTH_LENGTH; i++) {
            mapPlayer1.put(i, new TreeSet<Integer>());
            mapPlayer2.put(i, new TreeSet<Integer>());
        }
        IBoard boardPlayer1 = player1.getOwnBoard();
        IBoard boardPlayer2 = player2.getOwnBoard();
        IShip[] listPlayer1 = player1.getOwnBoard().getShipList();
        IShip[] listPlayer2 = player2.getOwnBoard().getShipList();
        for (int i = 0; i < player1.getOwnBoard().getShips(); i++) {
            mapPlayer1 = getSet(listPlayer1[i], mapPlayer1);
        }
        for (int i = 0; i < player2.getOwnBoard().getShips(); i++) {
            mapPlayer2 = getSet(listPlayer2[i], mapPlayer2);
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
                            sb.append(FIELD_NON_HIT);
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
                            sb.append(FIELD_NON_HIT);
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
        sb.append("\n\t" + FIELD_IS_HIT + " -> Field "
                + "is hit and it was a Miss!");
        sb.append("\n\t" + SHIP_NON_HIT + " -> A ship take place on "
                + "the Field!");
        sb.append("\n\t" + SHIP_IS_HIT + " -> A ship is on the Field "
                + "and it is destroyed!\n\n");
        return sb.toString();
    }

    @Override
    public final State getCurrentState() {
        if (firstPlayer) {
            return SHOOT1;
        }
        return SHOOT2;
    }
}
