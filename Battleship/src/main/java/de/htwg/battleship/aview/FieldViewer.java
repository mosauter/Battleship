// FieldViewer.java

package de.htwg.battleship.aview;

import de.htwg.battleship.model.Board;
import de.htwg.battleship.model.Player;

/**
 * FieldViewer
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-09
 */
public class FieldViewer {
    private Player player1;
    private Player player2;
    
    public FieldViewer(final Player player1, final Player player2) {
        this.player1 = player1;
        this.player1 = player2;
    }

    public String getBoard() {
        StringBuilder sb = new StringBuilder();
        sb.append(" ");
        Board boardPlayer1 = player1.getOwnBoard();
        for (int i = 0; i < 10; i++) {
            char c = (char) ('a' + i);
            sb.append(" ").append(c);
        }
        sb.append("\n1");
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (boardPlayer1.getField(j, i).isHit()) {
                    sb.append(" X");
                } else {
                    sb.append(" O");
                }
            }
        }
        return sb.toString();
    }
}
