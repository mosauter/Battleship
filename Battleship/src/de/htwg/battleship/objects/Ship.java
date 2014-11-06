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
     * Correct size.
     */
    private static final int CORRECT_FIELD_SIZE = 2;
    /**
     * Size of the ship.
     */
    private int size;
    /**
     * Where the ship starts in the 2D-Field.
     */
    private int[] positionStart;
    /**
     * If it is vertical or horizontal.
     * True if horizontal, False if vertical.
     */
    private boolean orientation;

    /**
     * Public-Constructor with all parameters.
     * @param size of the ship.
     * @param orientation true if horizontal.
     * @param positionStart there the ship starts.
     */
    public Ship(final int size, final boolean orientation,
            final int[] positionStart) {
        this.size = size;
        this.orientation = orientation;
        this.positionStart = positionStart;
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
     * Setter for the position where the ship starts.
     * @param positionStart array with the length = 2,
     *                      with a x- and a y-Coordinate.
     */
    public final void setPositionStart(final int[] positionStart) {
        if (positionStart.length != CORRECT_FIELD_SIZE) {
            return;
        }
        if (positionStart[0] < 0 || positionStart[1] < 0) {
            return;
        }
        this.positionStart = positionStart;
    }

    /**
     * Getter for the position where the ship starts.
     * @return an array of a x- and a y-Coordinate.
     */
    public final int[] getPositionStart() {
        return positionStart;
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
