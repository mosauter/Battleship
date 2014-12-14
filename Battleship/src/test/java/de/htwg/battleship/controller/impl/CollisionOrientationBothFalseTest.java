// CollisionOrientationBothFalseTest.java

package de.htwg.battleship.controller.impl;

import de.htwg.battleship.model.IShip;
import de.htwg.battleship.model.impl.Ship;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * CollisionOrientationBothFalseTest
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-04
 */
public class CollisionOrientationBothFalseTest {

    CollisionController cc;
    IShip ship1;
    IShip ship2;
    IShip ship3;

    public CollisionOrientationBothFalseTest() {
    }

    @Before
    public void setUp() {
        cc = new CollisionOrientationBothFalse();
        ship1 = new Ship(3, false, 1, 1);
        ship2 = new Ship(5, false, 3, 5);
        ship3 = new Ship(3, false, 1, 1);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of isCollision method, of class CollisionOrientationBothFalse.
     */
    @Test
    public void testIsCollision() {
        boolean expRes = false;
        boolean result = cc.isCollision(ship1, ship2);
        assertEquals(expRes, result);
        expRes = true;
        result = cc.isCollision(ship1, ship3);
        assertEquals(expRes, result);
    }

}