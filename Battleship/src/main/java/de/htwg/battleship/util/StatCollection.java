// StatCollection.java

package de.htwg.battleship.util;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Set of static util Methods.
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-03
 */
public final class StatCollection {

    /**
     * Constant for 10.
     */
    public static final int STANDARD_HEIGHT_LENGTH = 10;
    /**
     * Constant for 4.
     */
    public static final int STANDARD_SHIP_NUMBER_MAX = 5;
    /**
     * The heigth and the length of the field.
     */
    public static int heightLenght = STANDARD_HEIGHT_LENGTH;

    /**
     * The max number of ships that could be on the field.
     */
    public static int shipNumberMax = STANDARD_SHIP_NUMBER_MAX;


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
     * Method to create the legend of both fields.
     * @param sb StringBuilder on which the method has to add a legend
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
     * @return Map<Integer, Set<Integer>> empty with
     *         HEIGHT_LENGTH of empty sets
     */
    public static Map<Integer, Set<Integer>> createMap() {
        TreeMap<Integer, Set<Integer>> map = new TreeMap<>();
        for (int i = 0; i < heightLenght; i++) {
            map.put(i, new TreeSet<Integer>());
        }
        return map;
    }
}
