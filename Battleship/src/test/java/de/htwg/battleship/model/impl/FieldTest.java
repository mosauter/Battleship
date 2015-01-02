package de.htwg.battleship.model.impl;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 * FieldTest tests the field implementation.
 * @author Moritz Sauter (SauterMoritz@gmx.de)
 * @version 1.00
 * @since 2014-11-06
 */
public class FieldTest {

    /**
     * Saves field.
     */
    private Field field;

    /**
     * Set-Up.
     */
    @Before
    public final void setUp() {
        field = new Field(0, 0);
    }

    /**
     * Test for getXY method.
     */
    @Test
    public final void testGetXY() {
        this.field = new Field(5, 9);
        assertEquals(5, field.getX());
        assertEquals(9, field.getY());
        this.field = new Field(2, 3);
        assertEquals(2, field.getX());
        assertEquals(3, field.getY());
    }

    /**
     * Test of isHit method, of class Field.
     */
    @Test
    public final void testIsHit() {
        this.field = new Field(0, 0);
        boolean expResult = false;
        boolean result = field.isHit();
        assertEquals(expResult, result);
        this.field = new Field(2, 3);
        result = field.isHit();
        assertEquals(expResult, result);
    }

    /**
     * Test of setHit method, of class Field.
     */
    @Test
    public final void testSetHit() {
        field.setHit(true);
        assertEquals(field.isHit(), true);
        field.setHit(false);                // Doesn't make sense.
        assertEquals(field.isHit(), true);
        field.setHit(true);
        assertEquals(field.isHit(), true);
        field = new Field(0, 0);
        assertEquals(field.isHit(), false);
        field.setHit(false);
        assertEquals(field.isHit(), false);
    }
}
