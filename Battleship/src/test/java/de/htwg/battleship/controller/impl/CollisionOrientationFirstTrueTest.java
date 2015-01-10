// CollisionOrientationFirstTrueTest.java

package de.htwg.battleship.controller.impl;

import de.htwg.battleship.model.IShip;
import de.htwg.battleship.model.impl.Ship;
import de.htwg.battleship.util.StatCollection;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;

/**
 * CollisionOrientationFirstTrueTest tests an implementation of the chain.
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-04
 */
public class CollisionOrientationFirstTrueTest {

    /**
     * Saves the implementation.
     */
    private CollisionOrientationFirstTrue cc;
    /**
     * Saves the first ship.
     */
    private IShip ship1;
    /**
     * Saves the second ship.
     */
    private IShip ship2;
    /**
     * Saves the third ship.
     */
    private IShip ship3;

    /**
     * Set-Up.
     */
    @Before
    public final void setUp() {
        StatCollection.heightLenght = 10;
        StatCollection.shipNumberMax = 5;
        cc = new CollisionOrientationFirstTrue();
        ship1 = new Ship(2, true, 4, 4);
        ship2 = new Ship(3, false, 4, 5);
        ship3 = new Ship(3, false, 5, 4);
    }

    /**
     * Test of isCollision method, of class CollisionOrientationFirstTrue.
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

    @Test
    public final void testIsCollisionFalse() {
        Ship sh = new Ship(1, false, -1, 4);
        assertFalse(cc.isCollision(ship1, sh));
    }
}