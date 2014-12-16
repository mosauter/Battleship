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
    public static final int HEIGTH_LENGTH = 5;
    /**
     * The max number of ships that could be on the field.
     */
    public static final int SHIP_NUMBER_MAX = 2;
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
