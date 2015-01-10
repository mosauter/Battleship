// CollisionOrientationBothFalseTest.java

package de.htwg.battleship.controller.impl;

import de.htwg.battleship.model.IShip;
import de.htwg.battleship.model.impl.Ship;
import de.htwg.battleship.util.StatCollection;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * CollisionOrientationBothFalseTest tests a implementation of the chain.
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-04
 */
public class CollisionOrientationBothFalseTest {

    /**
     * Saves the implementation.
     */
    private CollisionController cc;
    /**
     * Saves first ship.
     */
    private IShip ship1;
    /**
     * Saves second ship.
     */
    private IShip ship2;
    /**
     * Saves third ship.
     */
    private IShip ship3;

    /**
     * Set-Up.
     */
    @Before
    public final void setUp() {
        StatCollection.heightLenght = 10;
        StatCollection.shipNumberMax = 5;
        cc = new CollisionOrientationBothFalse();
        ship1 = new Ship(3, false, 1, 1);
        ship2 = new Ship(5, false, 3, 5);
        ship3 = new Ship(3, false, 1, 1);
    }

    /**
     * Test of isCollision method, of class CollisionOrientationBothFalse.
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
    public final void testIsCollisionFalseUnder() {
        Ship sh = new Ship(1, false, -1, 1);
        Assert.assertFalse(cc.isCollision(ship1, sh));
    }
}