// CollisionOrientationFirstFalseTest.java

package de.htwg.battleship.controller;

import de.htwg.battleship.model.Ship;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * CollisionOrientationFirstFalseTest
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-04
 */
public class CollisionOrientationFirstFalseTest {

    CollisionOrientationFirstFalse cc;
    Ship ship1;
    Ship ship2;
    Ship ship3;

    public CollisionOrientationFirstFalseTest() {
        cc = new CollisionOrientationFirstFalse();
        ship1 = new Ship(2, false, 4, 0);
        ship2 = new Ship(2, true, 2, 0);
        ship3 = new Ship(3, true, 2, 0);
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of isCollision method, of class CollisionOrientationFirstFalse.
     */
    @Test
    public void testIsCollision() {
        boolean expRes = false;
        boolean result = cc.isCollision(ship1, ship2);
        assertEquals(expRes, result);
        expRes = false;
        result = cc.isCollision(ship1, ship3);
        assertEquals(expRes, result);
    }

}