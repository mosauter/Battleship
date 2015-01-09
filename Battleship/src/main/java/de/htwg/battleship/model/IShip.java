//IShip.java

package de.htwg.battleship.model;

/**
 * IShip is an Utility-Interface.
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-10
 */
public interface IShip {

    /**
     * Getter for the Orientation of the ship.
     * @return true if horizontal, false if vertical.
     */
    boolean isOrientation();
    /**
     * Setter for the Orientation of the ship.
     * @param orientation true if horizontal, false if vertical.
     */
    void setOrientation(boolean orientation);
    /**
     * Getter for the X-Coordinate.
     * @return X-Coordinate as integer
     */
    int getX();
    /**
     * Getter for the Y-Coordinate.
     * @return Y-Coordinate as integer
     */
    int getY();
    /**
     * Getter for the size of the ship.
     * @return size as an int.
     */
    int getSize();
    /**
     * Setter for the size of the ship.
     * @param size > 0
     */
    void setSize(int size);
    /**
     * Setter for the x-coordinate where the ship starts.
     * @param x the new x-coordinate
     */
    void setX(int x);
    /**
     * Setter for the y-coordinate where the ship starts.
     * @param y the new y-coordinate
     */
    void setY(int y);
}
