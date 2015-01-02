package de.htwg.battleship.model.impl;

import de.htwg.battleship.model.IShip;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * ShipTest tests implementation of ships.
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 */
public class ShipTest {

    /**
     * Saves a ship.
     */
    private IShip ship;

    /**
     * Set-Up.
     */
    @Before
    public final void setUp() {
        int x = 2;
        int y = 3;
        ship = new Ship(5, true, x, y);
    }

    /**
     * Test of isOrientation method, of class Ship.
     */
    @Test
    public final void testIsOrientation() {
        boolean expResult = true;
        boolean result = ship.isOrientation();
        assertEquals(expResult, result);
        expResult = false;
        this.ship.setOrientation(expResult);
        result = ship.isOrientation();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPositionStart method, of class Ship.
     */
    @Test
    public final void testNormalSetPositionStart() {
        int expResX = 2;
        int expResY = 3;
        int resultX = ship.getX();
        int resultY = ship.getY();
        assertEquals(expResX, resultX);
        assertEquals(expResY, resultY);
        Ship s = new Ship(1, true, 4, 5);
        expResX = 4;
        expResY = 5;
        resultX = s.getX();
        resultY = s.getY();
        assertEquals(expResX, resultX);
        assertEquals(expResY, resultY);
    }


    /**
     * Test of getSize method, of class Ship.
     */
    @Test
    public final void testValidGetSize() {
        int expRes = 5;
        int result = ship.getSize();
        assertEquals(expRes, result);
        expRes = 19;
        ship.setSize(expRes);
        result = ship.getSize();
        assertEquals(expRes, result);
    }

    /**
     * Test of setSize method, of class Ship.
     */
    @Test
    public final void testSetSize() {
        int expRes = 5;
        int falseEnter = -4;
        ship.setSize(falseEnter);
        int result = this.ship.getSize();
        assertEquals(expRes, result);
        falseEnter = -1;
        ship.setSize(falseEnter);
        result = this.ship.getSize();
        assertEquals(expRes, result);
    }
}