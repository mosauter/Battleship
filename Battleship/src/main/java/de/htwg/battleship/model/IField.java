//IField.java

package de.htwg.battleship.model;

/**
 * IField is an Utility-Interface.
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-10
 */
public interface IField {

    /**
     * Getter for the y-coordinate.
     * @return the y-coordinate as Integer.
     */
    int getY();
    /**
     * Getter for the x-coordinate.
     * @return the x-coordinate as Integer.
     */
    int getX();
    /**
     * Getter if the Field is now hit.
     * @return boolean if the Field is hit
     */
    boolean isHit();
    /**
     * Setter if the Field is now hit.
     * @param hit had to be true. False would be nonsensical.
     */
    void setHit(boolean hit);
}
