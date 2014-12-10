// FieldViewer.java

package de.htwg.battleship.aview.impl;

import de.htwg.battleship.aview.Viewer;
import de.htwg.battleship.model.IBoard;
import de.htwg.battleship.model.IPlayer;
import static de.htwg.battleship.util.StatCollection.HEIGTH_LENGTH;

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
        IBoard boardPlayer1 = player1.getOwnBoard();
        IBoard boardPlayer2 = player2.getOwnBoard();
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
        for (int i = 0; i < HEIGTH_LENGTH; i++) {
            sb.append(i);
            for (int j = 0; j < HEIGTH_LENGTH; j++) {
                if (boardPlayer1.getField(j, i).isHit()) {
                    sb.append(" X");
                } else {
                    sb.append(" O");
                }
            }
            sb.append("\t ").append(i);
            for (int j = 0; j < HEIGTH_LENGTH; j++) {
                if (boardPlayer2.getField(j, i).isHit()) {
                    sb.append(" X");
                } else {
                    sb.append(" O");
                }
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
