package de.htwg.battleship.model.impl;

import com.google.inject.AbstractModule;
import de.htwg.battleship.AbstractTest;
import de.htwg.battleship.model.IShip;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * ShipTest tests implementation of ships.
 *
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 */
public class ShipTest extends AbstractTest {

    private static final boolean ORIENTATION = true;
    private static final int SHIP_SIZE = 5;
    private static final int X = 2;
    private static final int Y = 3;

    /**
     * Saves a ship.
     */
    private IShip ship;

    public ShipTest(AbstractModule module) {
        this.createInjector(module);
    }

    /**
     * Set-Up.
     */
    @Before
    public final void setUp() {
        // ship = new Ship(5, true, x, y);
        ship = createShip(SHIP_SIZE, ORIENTATION, X, Y);
    }

    /**
     * Test of isOrientation method, of class Ship.
     */
    @Test
    public final void testIsOrientation() {
        boolean result = ship.isOrientation();
        assertTrue(result);
        this.ship.setOrientation(false);
        result = ship.isOrientation();
        assertFalse(result);
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
        // Ship s = new Ship(1, true, 4, 5);
        IShip s = createShip(1, true, 4, 5);
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

    @Test
    public void equalsReference() throws Exception {
        assertTrue(ship.equals(ship));
    }

    @Test
    public void equalsObject() throws Exception {
        assertFalse(ship.equals("NOT_A_SHIP"));
    }

    @Test
    public void equalsTrue() throws Exception {
        IShip s = createShip(SHIP_SIZE, ORIENTATION, X, Y);
        assertTrue(s.equals(ship));
    }

    @Test
    public void equalsFalse() throws Exception {
        IShip s = createShip(SHIP_SIZE + 1, ORIENTATION, X, Y);
        assertFalse(s.equals(ship));
        s = createShip(SHIP_SIZE, !ORIENTATION, X, Y);
        assertFalse(s.equals(ship));
        s = createShip(SHIP_SIZE, ORIENTATION, X + 1, Y);
        assertFalse(s.equals(ship));
        s = createShip(SHIP_SIZE, !ORIENTATION, X, Y + 1);
        assertFalse(s.equals(ship));
    }
}
