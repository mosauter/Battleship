// Ship.java

package de.htwg.battleship.model.impl;

import de.htwg.battleship.model.IShip;

/**
 * Ship for Battleship.
 *
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-10-29
 */
public class Ship implements IShip {

    /**
     * Size of the ship.
     */
    private int size;
    /**
     * X-Coordinate where the ship starts.
     */
    private int x;
    /**
     * Y-Coordinate where the ship starts.
     */
    private int y;
    /**
     * If it is vertical or horizontal. True if horizontal, False if vertical.
     */
    private boolean orientation;

    /**
     * Public Constructor with no parameters. initializes all integer values
     * with -1 and orientation with true only for use with dependency injection
     */
    public Ship() {
        this(-1, true, -1, -1);
    }

    /**
     * Public-Constructor with all parameters.
     *
     * @param size        of the ship.
     * @param orientation true if horizontal.
     * @param x           X-Coordinate where the ship starts
     * @param y           Y-Coordinate where the ship starts
     */
    public Ship(final int size, final boolean orientation, final int x,
                final int y) {
        this.size = size;
        this.orientation = orientation;
        this.x = x;
        this.y = y;
    }

    @Override
    public final boolean isOrientation() {
        return orientation;
    }

    @Override
    public final void setOrientation(final boolean orientation) {
        this.orientation = orientation;
    }

    @Override
    public final int getX() {
        return this.x;
    }

    @Override
    public final void setX(final int x) {
        this.x = x;
    }

    @Override
    public final int getY() {
        return this.y;
    }

    @Override
    public final void setY(final int y) {
        this.y = y;
    }

    @Override
    public final int getSize() {
        return size;
    }

    @Override
    public final void setSize(final int size) {
        if (size <= 0) {
            return;
        }
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Ship)) {
            return false;
        }

        Ship ship = (Ship) o;

        return getSize() == ship.getSize() && getX() == ship.getX() &&
               getY() == ship.getY() && isOrientation() == ship.isOrientation();

    }

    @Override
    public int hashCode() {
        int result = getSize();
        result = 31 * result + getX();
        result = 31 * result + getY();
        result = 31 * result + (isOrientation() ? 1 : 0);
        return result;
    }
}
