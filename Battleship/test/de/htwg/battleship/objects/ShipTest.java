package de.htwg.battleship.objects;

import de.htwg.battleship.objects.Ship;
import org.junit.After;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 */
public class ShipTest {
    
    Ship ship;
    
    @Before
    public void setUp() {
        int[] xy = { 2, 3 };
        ship = new Ship(5, true, xy);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of isOrientation method, of class Ship.
     */
    @Test
    public void testIsOrientation() {
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
    public void testNormalSetPositionStart() {
        int[] expRes = {2, 3};
        int[] result = ship.getPositionStart();
        assertArrayEquals(expRes, result);
        expRes = new int[] {4, 5};
        ship.setPositionStart(expRes);
        result = ship.getPositionStart();
        assertArrayEquals(expRes, result);
    }

    @Test
    public void testFalseSetPositionStart() {
        int[] expRes = {2, 3};
        int[] falseEnter = {-1, -3};
        this.ship.setPositionStart(falseEnter);
        int[] result = ship.getPositionStart();
        assertArrayEquals(expRes, result);
        falseEnter = new int[] {1, -1};
        ship.setPositionStart(falseEnter);
        result = ship.getPositionStart();
        assertArrayEquals(expRes, result);
    }

    @Test
    public void testFalseLengthSetPositionStart() {
        int[] expRes = {2, 3};
        int[] falseEnter = {0, 3, 5};
        ship.setPositionStart(falseEnter);
        int[] result = ship.getPositionStart();
        assertArrayEquals(expRes, result);
    }

    /**
     * Test of getSize method, of class Ship.
     */
    @Test
    public void testValidGetSize() {
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
    public void testSetSize() {
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
