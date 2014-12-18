// CollisionControllerTest.java

package de.htwg.battleship.controller.impl;

import de.htwg.battleship.model.impl.Ship;
import de.htwg.battleship.util.StatCollection;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * CollisionControllerTest
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-12-04
 */
public class CollisionControllerTest {

    CollisionController cc;
    Ship ship1;
    Ship ship2;
    Ship ship3;

    public CollisionControllerTest() {
    }

    @Before
    public void setUp() {
        StatCollection.HEIGTH_LENGTH = 10;
        StatCollection.SHIP_NUMBER_MAX = 5;
        cc = new CollisionOrientationBothTrue();
        ship1 = new Ship(2, false, 4, 0);
        ship2 = new Ship(2, true, 2, 0);
        ship3 = new Ship(3, true, 2, 0);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of responsibility method, of class CollisionController.
     */
    @Test
    public void testResponsibility() {
        boolean expRes = false;
        boolean result = cc.isCollision(ship1, ship2);
        assertEquals(expRes, result);
        expRes = true;
        result = cc.isCollision(ship1, ship3);
        assertEquals(expRes, result);
    }
}