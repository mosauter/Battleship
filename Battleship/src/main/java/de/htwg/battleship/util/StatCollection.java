// StatCollection.java

package de.htwg.battleship.util;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Set of static util Methods.
 *
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-03
 */
public final class StatCollection {

    /**
     * Constant for 10.
     */
    private static final int STANDARD_HEIGHT_LENGTH = 10;
    /**
     * Constant for 4.
     */
    private static final int STANDARD_SHIP_NUMBER_MAX = 5;
    /**
     * The heigth and the length of the field.
     */
    @SuppressWarnings("squid:S1444,squid:ClassVariableVisibilityCheck")
    public static int heightLenght = STANDARD_HEIGHT_LENGTH;

    /**
     * The max number of ships that could be on the field.
     */
    @SuppressWarnings("squid:S1444,squid:ClassVariableVisibilityCheck")
    public static int shipNumberMax = STANDARD_SHIP_NUMBER_MAX;

    private StatCollection() {
    }

    /**
     * Utility-Method to check if a value is between.
     *
     * @param upper upper border
     * @param lower lower border
     * @param value value
     *
     * @return true if the value is between
     */
    public static boolean isBetween(final int upper, final int lower,
                                    final int value) {
        return value >= lower && value <= upper;
    }

    /**
     * Method to create the legend of both fields.
     *
     * @param sb StringBuilder on which the method has to add a legend
     *
     * @return the sb mentioned above with a legend
     */
    public static StringBuilder createBorder(final StringBuilder sb) {
        sb.append(" ");
        for (int i = 0; i < heightLenght; i++) {
            char c = (char) ('a' + i);
            sb.append(" ").append(c);
        }
        return sb;
    }

    /**
     * Method to initialize the map for all the ships.
     *
     * @return Map empty with HEIGHT_LENGTH of empty sets
     */
    public static Map<Integer, Set<Integer>> createMap() {
        Map<Integer, Set<Integer>> map = new TreeMap<>();
        for (int i = 0; i < heightLenght; i++) {
            map.put(i, new TreeSet<>());
        }
        return map;
    }
}
