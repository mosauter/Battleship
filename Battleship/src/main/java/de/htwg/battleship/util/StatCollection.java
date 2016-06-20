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

    private StatCollection() {
    }

    public static String LOCAL_PLAYER_ID = "local_player_id";

    /**
     * Utility-Method to check if a value is between.
     *
     * @param upper upper border
     * @param lower lower border
     * @param value value
     *
     * @return true if the value is between
     */
    public static boolean isBetween(final int upper, final int lower, final int value) {
        return value >= lower && value <= upper;
    }

    /**
     * Method to create the legend of both fields.
     *
     * @param sb        StringBuilder on which the method has to add a legend
     * @param boardSize the size of the board which is represented with this border
     *
     * @return the sb mentioned above with a legend
     */
    public static StringBuilder createBorder(final StringBuilder sb, int boardSize) {
        sb.append(" ");
        for (int i = 0; i < boardSize; i++) {
            char c = (char) ('a' + i);
            sb.append(" ").append(c);
        }
        return sb;
    }

    /**
     * Method to initialize the map for all the ships.
     *
     * @param boardSize the size of the board which should be represented with this map
     *
     * @return Map empty with HEIGHT_LENGTH of empty sets
     */
    public static Map<Integer, Set<Integer>> createMap(int boardSize) {
        Map<Integer, Set<Integer>> map = new TreeMap<>();
        for (int i = 0; i < boardSize; i++) {
            map.put(i, new TreeSet<>());
        }
        return map;
    }
}
