// CollisionOrientationBothTrueTest.java

package de.htwg.battleship.controller.impl;

import de.htwg.battleship.model.impl.Ship;
import de.htwg.battleship.util.StatCollection;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * CollisionOrientationBothTrueTest
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-04
 */
public class CollisionOrientationBothTrueTest {

    CollisionOrientationBothTrue cc;
    Ship ship1;
    Ship ship2;
    Ship ship3;

    public CollisionOrientationBothTrueTest() {
    }

    @Before
    public void setUp() {
        StatCollection.HEIGTH_LENGTH = 10;
        StatCollection.SHIP_NUMBER_MAX = 5;
        cc = new CollisionOrientationBothTrue();
        ship1 = new Ship(3, true, 4, 4);
        ship2 = new Ship(3, true, 4, 5);
        ship3 = new Ship(3, true, 6, 4);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of isCollision method, of class CollisionOrientationBothTrue.
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