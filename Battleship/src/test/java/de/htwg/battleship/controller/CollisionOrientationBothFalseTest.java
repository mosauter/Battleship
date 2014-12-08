// CollisionOrientationBothFalseTest.java

package de.htwg.battleship.controller;

import de.htwg.battleship.model.Ship;
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

    CollisionOrientationBothFalse cc;
    Ship ship1;
    Ship ship2;
    Ship ship3;

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