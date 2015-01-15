// FieldViewer.java

package de.htwg.battleship.aview.tui;

import de.htwg.battleship.controller.IMasterController;
import de.htwg.battleship.model.IBoard;
import de.htwg.battleship.model.IPlayer;
import de.htwg.battleship.model.IShip;
import static de.htwg.battleship.util.StatCollection.createMap;
import java.util.Map;
import java.util.Set;

/**
 * FieldViewer to print the Field.
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-09
 */
public class ShootFieldViewer extends AbstractFieldViewer implements Viewer {

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
     * Saves IMasterController.
     */
    private final IMasterController master;

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
        this.master = master;
    }

    @Override
    public final String getString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        Map<Integer, Set<Integer>> mapPlayer1 = createMap();
        Map<Integer, Set<Integer>> mapPlayer2 = createMap();
        IBoard boardPlayer1 = player1.getOwnBoard();
        IBoard boardPlayer2 = player2.getOwnBoard();
        IShip[] listPlayer1 = player1.getOwnBoard().getShipList();
        IShip[] listPlayer2 = player2.getOwnBoard().getShipList();
        master.fillMap(listPlayer1, mapPlayer1, player1.getOwnBoard().getShips());
        master.fillMap(listPlayer2, mapPlayer2, player2.getOwnBoard().getShips());
        sb = fillEntire(mapPlayer1, boardPlayer1, sb,
                mapPlayer2, boardPlayer2, FIELD_NON_HIT);
        sb = addLegend(sb);
        return sb.toString();
    }
}
