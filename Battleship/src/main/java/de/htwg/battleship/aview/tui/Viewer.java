//Viewer.java

package de.htwg.battleship.aview.tui;

/**
 * States is an Utility-Interface.
 *
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-09
 */
public abstract class Viewer {

    /**
     * Presentation of a ship which is Hit.
     */
    protected static final String SHIP_IS_HIT = " X";
    /**
     * Presentation of a ship which is not hit.
     */
    protected static final String SHIP_NON_HIT = " S";
    /**
     * Presentation of a Field with no ship on it but which is hit.
     */
    protected static final String FIELD_IS_HIT = " M";
    /**
     * Presentation of a Field with no ship on it and which is not hit.
     */
    protected static final String FIELD_NON_HIT = " O";

    /**
     * To get a String - Representation of something the States has to
     * implement.
     *
     * @return String
     */
    public abstract String getString();
}
