// Ship.java

package de.htwg.battleship.objects;

/**
 * Ship for Battleship.
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-10-29
 */
public class Ship {

    /**
     * Size of the ship.
     */
    private int size;
    /**
     * X-Coordinate where the ship starts.
     */
    private final int x;
    /**
     * Y-Coordinate where the ship starts.
     */
    private final int y;
    /**
     * If it is vertical or horizontal.
     * True if horizontal, False if vertical.
     */
    private boolean orientation;

    /**
     * Public-Constructor with all parameters.
     * @param size of the ship.
     * @param orientation true if horizontal.
     * @param x X-Coordinate where the ship starts
     * @param y Y-Coordinate where the ship starts
     */
    public Ship(final int size, final boolean orientation,
            final int x, final int y) {
        this.size = size;
        this.orientation = orientation;
        this.x = x;
        this.y = y;
    }

    /**
     * Getter for the Orientation of the ship.
     * @return true if horizontal, false if vertical.
     */
    public boolean isOrientation() {
        return orientation;
    }

    /**
     * Setter for the Orientation of the ship.
     * @param orientation true if horizontal, false if vertical.
     */
    public final void setOrientation(final boolean orientation) {
        this.orientation = orientation;
    }

    /**
     * Getter for the X-Coordinate.
     * @return X-Coordinate as integer
     */
    public final int getX() {
        return this.x;
    }

    /**
     * Getter for the Y-Coordinate.
     * @return Y-Coordinate as integer
     */
    public final int getY() {
        return this.y;
    }

    /**
     * Getter for the size of the ship.
     * @return size as an int.
     */
    public final int getSize() {
        return size;
    }

    /**
     * Setter for the size of the ship.
     * @param size > 0
     */
    public final void setSize(final int size) {
        if (size <= 0) {
            return;
        }
        this.size = size;
    }
}
