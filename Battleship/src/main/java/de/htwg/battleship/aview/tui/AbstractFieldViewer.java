// AbstractFieldViewer.java
package de.htwg.battleship.aview.tui;

import static de.htwg.battleship.aview.tui.Viewer.FIELD_IS_HIT;
import static de.htwg.battleship.aview.tui.Viewer.FIELD_NON_HIT;
import static de.htwg.battleship.aview.tui.Viewer.SHIP_IS_HIT;
import de.htwg.battleship.model.IBoard;
import static de.htwg.battleship.util.StatCollection.createBorder;
import static de.htwg.battleship.util.StatCollection.heightLenght;
import java.util.Map;
import java.util.Set;

/**
 * AbstractFieldViewer
 *
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2015-01-15
 */
public abstract class AbstractFieldViewer implements Viewer {

    public StringBuilder fillX(Map<Integer, Set<Integer>> mapPlayer1, int y, IBoard boardPlayer1, StringBuilder sb, String shipNonHit) {
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

    public StringBuilder fillEntire(Map<Integer, Set<Integer>> mapPlayer1,
            IBoard boardPlayer1, StringBuilder sb,
            Map<Integer, Set<Integer>> mapPlayer2, IBoard boardPlayer2,
            String shipNonHit) {
        sb = addBorder(sb);
        for (int y = 0; y < heightLenght; y++) {
            sb.append(y);
            sb = fillX(mapPlayer1, y, boardPlayer1, sb, shipNonHit);
            sb.append("\t ").append(y);
            sb = fillX(mapPlayer2, y, boardPlayer2, sb, shipNonHit);
            sb.append("\n");
        }
        return sb;
    }

    public StringBuilder addLegend(StringBuilder sb) {
        sb.append("Legende:\n\t" + FIELD_NON_HIT + " -> Field is not hit!");
        sb.append("\n\t" + FIELD_IS_HIT + " -> Field "
                + "is hit and it was a Miss!");
        sb.append("\n\t" + SHIP_NON_HIT + " -> A ship take place on "
                + "the Field!");
        sb.append("\n\t" + SHIP_IS_HIT + " -> A ship is on the Field "
                + "and it is destroyed!\n\n");
        return sb;
    }

    public StringBuilder addBorder(StringBuilder sb) {
        createBorder(sb);
        sb.append("\t ");
        createBorder(sb);
        sb.append("\n");
        return sb;
    }

    @Override
    public abstract String getString();
}
