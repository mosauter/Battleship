// ShipControllerTest.java

package de.htwg.battleship.controller;

import de.htwg.battleship.objects.Ship;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * ShipControllerTest
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-11-27
 */
public class ShipControllerTest {

    public ShipControllerTest() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of placeShip method, of class ShipController.
     */
    @Test
    public void testPlaceShip() {
        System.out.println("placeShip");
        Ship ship = null;
        boolean player = false;
        ShipController instance = null;
        boolean expResult = false;
        boolean result = instance.placeShip(ship, player);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}