// Field.java

package de.htwg.battleship.model.impl;

/**
 * Field Object for the Fields in a Board.
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-10-29
 */
public class Field {

    /**
     * X-Coordinate of the Field in the board.
     */
    private final int x;
    /**
     * Y-Coordinate of the Field in the board.
     */
    private final int y;
    /**
     * If the field is already hit or not.
     * True if it is hit, false if not.
     */
    private boolean hit;

    /**
     * Public-Constructor.
     * @param x x-Coordinate of the Field.
     * @param y y-Coordinate of the Field.
     */
    public Field(final int x, final int y) {
        this.x = x;
        this.y = y;
        this.hit = false;
    }

    /**
     * Getter for the y-coordinate.
     * @return the y-coordinate as Integer.
     */
    public final int getY() {
        return this.y;
    }

    /**
     * Getter for the x-coordinate.
     * @return the x-coordinate as Integer.
     */
    public final int getX() {
        return this.x;
    }

    /**
     * Getter if the Field is now hit.
     * @return boolean if the Field is hit
     */
    public final boolean isHit() {
        return hit;
    }

    /**
     * Setter if the Field is now hit.
     * @param hit had to be true. False would be nonsensical.
     */
    public final void setHit(final boolean hit) {
        if (this.hit) {
            return;
        }
        this.hit = hit;
    }

}
