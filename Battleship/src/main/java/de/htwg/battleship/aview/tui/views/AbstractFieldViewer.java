// AbstractFieldViewer.java
package de.htwg.battleship.aview.tui.views;

import de.htwg.battleship.aview.tui.Viewer;
import de.htwg.battleship.model.IBoard;

import java.util.Map;
import java.util.Set;

import static de.htwg.battleship.util.StatCollection.createBorder;
import static de.htwg.battleship.util.StatCollection.heightLenght;

/**
 * AbstractFieldViewer implements some help methods which are used by severel
 * viewers. All viewer representing some sort of the field.
 *
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2015-01-15
 */
abstract class AbstractFieldViewer extends Viewer {

    /**
     * Utility Method which adds a presentation of one line in the field of one
     * player.
     *
     * @param mapPlayer1   map which is filled with y-coordinate as key and a
     *                     set of the x coordinates
     * @param y            specified line
     * @param boardPlayer1 the board of the specified player
     * @param sb           specified string builder
     * @param shipNonHit   presentation of a field which is not hit but where a
     *                     ship takes place
     *
     * @return the new StringBuilder
     */
    final StringBuilder fillX(final Map<Integer, Set<Integer>> mapPlayer1,
                              final int y, final IBoard boardPlayer1,
                              final StringBuilder sb, final String shipNonHit) {
        for (int x = 0; x < heightLenght; x++) {
            boolean isShip = false;
            for (Integer value : mapPlayer1.get(y)) {
                if (value == x) {
                    if (boardPlayer1.isHit(x, y)) {
                        sb.append(SHIP_IS_HIT);
                    } else {
                        sb.append(shipNonHit);
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
        return sb;
    }

    /**
     * Utility method which fills an entire line of the field.
     *
     * @param mapPlayer1   map which is filled with y-coordinate as key and a
     *                     set of the x coordinates by player one
     * @param boardPlayer1 the board of player one
     * @param sb           specified StringBuilder
     * @param mapPlayer2   map which is filled with y-coordinate as key and a
     *                     set of the x coordinates by player two
     * @param boardPlayer2 the board of player two
     * @param shipNonHit   presentation of a field which is not hit but where a
     *                     ship takes place
     *
     * @return the new StringBuilder
     */
    final StringBuilder fillEntire(final Map<Integer, Set<Integer>> mapPlayer1,
                                   final IBoard boardPlayer1,
                                   final StringBuilder sb,
                                   final Map<Integer, Set<Integer>> mapPlayer2,
                                   final IBoard boardPlayer2,
                                   final String shipNonHit) {
        addBorder(sb);
        for (int y = 0; y < heightLenght; y++) {
            sb.append(y);
            fillX(mapPlayer1, y, boardPlayer1, sb, shipNonHit);
            sb.append("\t ").append(y);
            fillX(mapPlayer2, y, boardPlayer2, sb, shipNonHit);
            sb.append("\n");
        }
        return sb;
    }

    /**
     * Utility method to add the legend to the viewer.
     *
     * @param sb the string builder
     *
     * @return the new StringBuilder
     */
    final StringBuilder addLegend(final StringBuilder sb) {
        sb.append("Legende:\n\t" + FIELD_NON_HIT + " -> Field is not hit!");
        sb.append(
            "\n\t" + FIELD_IS_HIT + " -> Field " + "is hit and it was a Miss!");
        sb.append(
            "\n\t" + SHIP_NON_HIT + " -> A ship take place on " + "the Field!");
        sb.append("\n\t" + SHIP_IS_HIT + " -> A ship is on the Field " +
                  "and it is destroyed!\n\n");
        return sb;
    }

    /**
     * Utility method to add the border of both player to the field.
     *
     * @param sb the string builder before
     *
     * @return the new StringBuilder
     */
    final StringBuilder addBorder(final StringBuilder sb) {
        createBorder(sb);
        sb.append("\t ");
        createBorder(sb);
        sb.append("\n");
        return sb;
    }

    @Override
    public abstract String getString();
}
