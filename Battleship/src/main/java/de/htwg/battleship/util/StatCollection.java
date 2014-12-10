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
