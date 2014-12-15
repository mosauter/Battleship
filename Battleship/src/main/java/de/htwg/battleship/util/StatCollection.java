// StatCollection.java

package de.htwg.battleship.util;

/**
 * Set of static util Methods.
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-03
 */
public final class StatCollection {

    /**
     * The heigth and the length of the field.
     */
    public static final int HEIGTH_LENGTH = 10;
    /**
     * The max number of ships that could be on the field.
     */
    public static final int SHIP_NUMBER_MAX = 5;
    /**
     * Constant for the conversion of the ASCII table.
     */
    public static final int ASCII_LOW_CASE = 97;

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
}
