// FieldViewer.java

package de.htwg.battleship.aview.tui.impl;

import de.htwg.battleship.aview.tui.Viewer;
import de.htwg.battleship.controller.IMasterController;
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
     * Public - Constructor.
     * @param player player one
     * @param master master controller
     */
    public ShootFieldViewer(final IPlayer player,
            final IMasterController master) {
        this.player1 = player;
        if (player.equals(master.getPlayer1())) {
            this.player2 = master.getPlayer2();
        } else {
            this.player2 = master.getPlayer1();
        }
    }

    @Override
    public final String getString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n ");
        Map<Integer, Set<Integer>> mapPlayer1 = createMap();
        Map<Integer, Set<Integer>> mapPlayer2 = createMap();
        IBoard boardPlayer1 = player1.getOwnBoard();
        IBoard boardPlayer2 = player2.getOwnBoard();
        IShip[] listPlayer1 = player1.getOwnBoard().getShipList();
        IShip[] listPlayer2 = player2.getOwnBoard().getShipList();
        fillMap(listPlayer1, mapPlayer1, player1.getOwnBoard().getShips());
        fillMap(listPlayer2, mapPlayer2, player2.getOwnBoard().getShips());
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
                            sb.append(FIELD_NON_HIT);
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
                boolean isShip = false;
                for (Integer value : mapPlayer2.get(y)) {
                    if (value == x) {
                        if (boardPlayer2.isHit(x, y)) {
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
