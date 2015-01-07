// CollisionOrientationBothTrueTest.java

package de.htwg.battleship.controller.impl;

import de.htwg.battleship.model.impl.Ship;
import de.htwg.battleship.util.StatCollection;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * CollisionOrientationBothTrueTest tests an implementation of the chain.
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-04
 */
public class CollisionOrientationBothTrueTest {

    /**
     * Saves the implementation.
     */
    private CollisionOrientationBothTrue cc;
    /**
     * Saves first ship.
     */
    private Ship ship1;
    /**
     * Saves second ship.
     */
    private Ship ship2;
    /**
     * Saves third ship.
     */
    private Ship ship3;

    /**
     * Set-Up.
     */
    @Before
    public final void setUp() {
        StatCollection.heightLenght = 10;
        StatCollection.shipNumberMax = 5;
        cc = new CollisionOrientationBothTrue();
        ship1 = new Ship(3, true, 4, 4);
        ship2 = new Ship(3, true, 4, 5);
        ship3 = new Ship(3, true, 6, 4);
    }

    /**
     * Test of isCollision method, of class CollisionOrientationBothTrue.
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