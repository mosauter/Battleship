// Ship.java

package de.htwg.battleship.model.impl;

import de.htwg.battleship.model.IShip;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * HibernateShip for Battleship adapted for Hibernate
 *
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-10-29
 */
public class HibernateShip implements IShip, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    /**
     * BoardValues of the ship.
     */
    @Column(name = "size")
    private int size;
    /**
     * X-Coordinate where the ship starts.
     */
    @Column(name = "x")
    private int x;
    /**
     * Y-Coordinate where the ship starts.
     */
    @Column(name = "y")
    private int y;
    /**
     * If it is vertical or horizontal. True if horizontal, False if vertical.
     */
    @Column(name = "orientation")
    private boolean orientation;

    /**
     * Public Constructor with no parameters. initializes all integer values
     * with -1 and orientation with true only for use with dependency injection
     */
    public HibernateShip() {
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
    public HibernateShip(final int size, final boolean orientation, final int x,
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
}
