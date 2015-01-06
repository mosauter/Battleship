//Viewer.java

package de.htwg.battleship.aview.tui;

/**
 * States is an Utility-Interface.
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-09
 */
public interface Viewer {

    /**
     * Presentation of a ship which is Hit.
     */
    String SHIP_IS_HIT = " X";
    /**
     * Presentation of a ship which is not hit.
     */
    String SHIP_NON_HIT = " S";
    /**
     * Presentation of a Field with no ship on it but which is hit.
     */
    String FIELD_IS_HIT = " M";
    /**
     * Presentation of a Field with no ship on it and which is not hit.
     */
    String FIELD_NON_HIT = " O";

    /**
     * To get a String - Representation of something the
     * States has to implement.
     * @return String
     */
    String getString();
}
