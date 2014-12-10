// CollisionOrientationFirstTrueTest.java

package de.htwg.battleship.controller.impl;

import de.htwg.battleship.model.impl.Ship;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * CollisionOrientationFirstTrueTest
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-04
 */
public class CollisionOrientationFirstTrueTest {

    CollisionOrientationFirstTrue cc;
    Ship ship1;
    Ship ship2;
    Ship ship3;

    public CollisionOrientationFirstTrueTest() {
    }

    @Before
    public void setUp() {
        cc = new CollisionOrientationFirstTrue();
        ship1 = new Ship(2, true, 4, 4);
        ship2 = new Ship(3, false, 4, 5);
        ship3 = new Ship(3, false, 5, 4);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of isCollision method, of class CollisionOrientationFirstTrue.
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