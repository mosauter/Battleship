// CollisionOrientationFirstFalseTest.java

package de.htwg.battleship.controller.impl;

import de.htwg.battleship.model.IShip;
import de.htwg.battleship.model.impl.Ship;
import de.htwg.battleship.util.StatCollection;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * CollisionOrientationFirstFalseTest tests an implementation of the chain.
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-04
 */
public class CollisionOrientationFirstFalseTest {

    /**
     * Saves the implementation.
     */
    private final CollisionOrientationFirstFalse cc;
    /**
     * Saves first ship.
     */
    private final IShip ship1;
    /**
     * Saves second ship.
     */
    private final IShip ship2;
    /**
     * Saves third ship.
     */
    private final IShip ship3;

    /**
     * Public Constructor.
     */
    public CollisionOrientationFirstFalseTest() {
        StatCollection.HEIGTH_LENGTH = 10;
        StatCollection.SHIP_NUMBER_MAX = 5;
        cc = new CollisionOrientationFirstFalse();
        ship1 = new Ship(2, false, 4, 0);
        ship2 = new Ship(2, true, 2, 0);
        ship3 = new Ship(3, true, 2, 0);
    }

    /**
     * Test of isCollision method, of class CollisionOrientationFirstFalse.
     */
    @Test
    public final void testIsCollision() {
        boolean expRes = false;
        boolean result = cc.isCollision(ship1, ship2);
        assertEquals(expRes, result);
        expRes = true;
        result = cc.isCollision(ship1, ship3);
        assertEquals(expRes, result);
    }
}