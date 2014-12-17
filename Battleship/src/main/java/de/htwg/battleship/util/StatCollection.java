// StatCollection.java

package de.htwg.battleship.util;

import de.htwg.battleship.model.IShip;
import java.util.Map;
import java.util.Set;

/**
 * Set of static util Methods.
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-03
 */
public final class StatCollection {

    /**
     * Private Constructor.
     */
    private StatCollection() { }
    /**
     * The heigth and the length of the field.
     */
    public static final int HEIGTH_LENGTH = 5;
    /**
     * The max number of ships that could be on the field.
     */
    public static final int SHIP_NUMBER_MAX = 1;
    /**
     * Constant for the conversion of the ASCII table.
     */
    public static final int ASCII_LOW_CASE = 97;
    /**
     * Presentation of a ship which is Hit.
     */
    public static final String SHIP_IS_HIT = " X";
    /**
     * Presentation of a ship which is not hit.
     */
    public static final String SHIP_NON_HIT = " S";
    /**
     * Presentation of a Field with no ship on it but which is hit.
     */
    public static final String FIELD_IS_HIT = " M";
    /**
     * Presentation of a Field with no ship on it and which is not hit.
     */
    public static final String FIELD_NON_HIT = " O";
    /**
     * The check string for the place a Ship input in the tui.
     */
    public static final String HORIZONTAL = "horizontal";

    /**
     * Utility-Method to check if a value is between.
     * @param xupp upper border
     * @param xlow lower border
     * @param x value
     * @return true if the value is between
     */
    public static boolean isBetween(final int xupp,
            final int xlow, final int x) {
        return (x >= xlow && x <= xupp);
    }

    /**
     * Utility Method to create a Map where ships take place.
     * @param ship specified ship
     * @param map specified map
     * @return the new Map
     */
    public static Map<Integer, Set<Integer>> getSet(final IShip ship,
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
